package com.gamecity.scrabble.player.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamecity.scrabble.board.service.exception.JoinBoardException;
import com.gamecity.scrabble.board.service.exception.LeaveBoardException;
import com.gamecity.scrabble.board.service.exception.UserNotOnBoardException;
import com.gamecity.scrabble.board.service.exception.UserOnBoardException;
import com.gamecity.scrabble.entity.Board;
import com.gamecity.scrabble.entity.Player;
import com.gamecity.scrabble.entity.PlayerStatus;
import com.gamecity.scrabble.entity.User;
import com.gamecity.scrabble.player.service.PlayerService;
import com.gamecity.scrabble.repository.PlayerRepository;
import com.gamecity.scrabble.repository.UserRepository;
import com.gamecity.scrabble.utility.DateUtils;

/**
 * Implementation details for the {@link PlayerService}
 * 
 * @author ekarakus
 *
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    private final static Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;

    /**
     * Creates the constructor with the wired {@link PlayerRepository} and {@link UserRepository}
     * 
     * @param playerRepository
     * @param userRepository
     */
    @Autowired
    public PlayerServiceImpl(final PlayerRepository playerRepository, final UserRepository userRepository) {

        this.playerRepository = playerRepository;
        this.userRepository = userRepository;

    }

    @Override
    public void add(Board board, User user) throws UserOnBoardException, JoinBoardException {

        final List<Player> activePlayers = getPlayersOnBoard(board);

        if (activePlayers.size() >= board.getPlayerCount()) {
            throw new JoinBoardException("The game on board " + board.getId() + " has already been started!");
        }

        boolean userIsOnBoard = activePlayers.stream().anyMatch(player -> player.getUser().getId().equals(user.getId()));
        if (userIsOnBoard) {
            throw new UserOnBoardException("User " + user.getUsername() + " is already on board " + board.getId() + "!");
        }

        final Player player = new Player();
        player.setBoardId(board.getId());
        player.setJoinDate(DateUtils.now());
        player.setStatus(PlayerStatus.JOINED);
        player.setUser(userRepository.findById(user.getId()).orElse(null));
        playerRepository.save(player);

        logger.debug("Player for the user {} has been added to the board {}.", user.getId(), board.getId());
    }

    @Override
    public void remove(Board board, User user) throws UserNotOnBoardException, LeaveBoardException {

        final List<Player> activePlayers = getPlayersOnBoard(board);

        final Player player = activePlayers.stream().filter(p -> p.getUser().getId().equals(user.getId())).findFirst().orElse(null);
        if (player == null) {
            throw new UserNotOnBoardException("User " + user.getUsername() + " isn't on board " + board.getId() + "!");
        }

        if (activePlayers.size() >= board.getPlayerCount()) {
            throw new LeaveBoardException("The game on board " + board.getId() + " is already started!");
        }

        player.setLeaveDate(DateUtils.now());
        player.setStatus(PlayerStatus.LEFT);
        playerRepository.save(player);

        logger.debug("Player for user {} has been removed from the board {}.", user.getId(), board.getId());
    }

    @Override
    public List<Player> getPlayersOnBoard(Board board) {
        return playerRepository.findByBoardIdAndStatus(board.getId(), PlayerStatus.JOINED);
    }

}
