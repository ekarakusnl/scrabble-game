package com.gamecity.scrabble.board.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamecity.scrabble.board.service.BoardService;
import com.gamecity.scrabble.board.service.exception.BoardExistsException;
import com.gamecity.scrabble.board.service.exception.BoardNotFoundException;
import com.gamecity.scrabble.board.service.exception.JoinBoardException;
import com.gamecity.scrabble.board.service.exception.LeaveBoardException;
import com.gamecity.scrabble.entity.Board;
import com.gamecity.scrabble.entity.User;
import com.gamecity.scrabble.player.service.PlayerService;
import com.gamecity.scrabble.repository.BoardRepository;
import com.gamecity.scrabble.user.service.UserService;
import com.gamecity.scrabble.validation.Validator;
import com.gamecity.scrabble.validation.rule.AlphanumericNameValidator;

/**
 * Implementation details for the {@link BoardService}
 * 
 * @author ekarakus
 *
 */
@Service
public class BoardServiceImpl implements BoardService {

    private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

    private final BoardRepository boardRepository;
    private final UserService userService;
    private final PlayerService playerService;

    /**
     * Creates the constructor with the wired {@link BoardRepository}, {@link UserService},
     * {@link PlayerService}
     * 
     * @param boardRepository
     * @param userService
     * @param playerService
     */
    @Autowired
    public BoardServiceImpl(final BoardRepository boardRepository, final UserService userService, final PlayerService playerService) {

        this.boardRepository = boardRepository;
        this.userService = userService;
        this.playerService = playerService;

    }

    @Override
    public Board create(Long ownerId, String name, Integer playerCount, Integer duration) throws BoardExistsException {

        final List<Validator> boardValidators = new ArrayList<Validator>();
        boardValidators.add(new AlphanumericNameValidator(name));
        boardValidators.stream().forEach(v -> v.validate());

        final User user = userService.get(ownerId);

        Board board = new Board();
        board.setDuration(duration);
        board.setName(name);
        board.setOwnerId(ownerId);
        board.setPlayerCount(playerCount);

        try {
            board = boardRepository.save(board);
        } catch (ConstraintViolationException e) {
            throw new BoardExistsException("The board with the name " + board.getName() + " has already been created.");
        }

        logger.debug("Board {} has been created by the user {}.", board.getId(), user.getUsername());

        return board;
    }

    @Override
    public Board get(Long id) throws BoardNotFoundException {
        return boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException("Board with the id " + id + " isn't found!"));
    }

    @Override
    public Board join(Long id, Long userId) throws JoinBoardException {

        final User user = userService.get(userId);
        final Board board = get(id);

        switch (board.getStatus()) {
        case STARTED:
            throw new JoinBoardException("The game on board " + board.getId() + " has already been started!");
        case FINISHED:
            throw new JoinBoardException("The game on board " + board.getId() + " has already been finished!");
        case TERMINATED:
            throw new JoinBoardException("The game on board " + board.getId() + " has already ben terminated!");
        case PENDING:
            break;
        }

        playerService.add(board, user);

        logger.debug("User {} joined the board {}.", user.getUsername(), board.getId());

        return board;
    }

    @Override
    public Board leave(Long id, Long userId) throws LeaveBoardException {

        final User user = userService.get(userId);
        final Board board = get(id);

        switch (board.getStatus()) {
        case STARTED:
            throw new LeaveBoardException("The game on board " + board.getId() + " has already been started!");
        case PENDING:
            break;
        default:
            logger.debug("User {} tried to leave the board {} of {} status.", user.getUsername(), board.getId(), board.getStatus());
            throw new LeaveBoardException("An unexpected error has been occured!");
        }

        playerService.remove(board, user);

        logger.debug("User {} left the board {}.", user.getUsername(), board.getId());

        return board;
    }

}