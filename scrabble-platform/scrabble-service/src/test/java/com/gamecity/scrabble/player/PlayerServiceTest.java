package com.gamecity.scrabble.player;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

import com.gamecity.scrabble.AbstractTestCase;
import com.gamecity.scrabble.board.service.exception.JoinBoardException;
import com.gamecity.scrabble.board.service.exception.LeaveBoardException;
import com.gamecity.scrabble.board.service.exception.UserNotOnBoardException;
import com.gamecity.scrabble.board.service.exception.UserOnBoardException;
import com.gamecity.scrabble.entity.Board;
import com.gamecity.scrabble.entity.Player;
import com.gamecity.scrabble.entity.User;
import com.gamecity.scrabble.player.service.PlayerService;
import com.gamecity.scrabble.player.service.impl.PlayerServiceImpl;
import com.gamecity.scrabble.repository.PlayerRepository;
import com.gamecity.scrabble.repository.UserRepository;

/**
 * Tests for {@link PlayerService} operations
 * 
 * @author ekarakus
 *
 */
@SuppressWarnings("javadoc")
public class PlayerServiceTest extends AbstractTestCase {

    private PlayerService playerService;
    private PlayerRepository playerRepository;
    private UserRepository userRepository;

    @Before
    public void onBefore() {

        playerRepository = mock(PlayerRepository.class);
        userRepository = mock(UserRepository.class);
        playerService = new PlayerServiceImpl(playerRepository, userRepository);

    }

    @Test
    public void playerAdded() {

        final Board board = new Board();
        board.setPlayerCount(4);

        playerService.add(board, mock(User.class));

    }

    @Test(expected = JoinBoardException.class)
    public void playerNotAddedWhenMaximumLimitExceeded() {

        int playerCount = 4;

        final Board board = new Board();
        board.setPlayerCount(playerCount);

        when(playerRepository.findByBoardIdAndStatus(any(), any())).then(invocation -> {
            return IntStream.range(0, playerCount).boxed().map(val -> new Player()).collect(Collectors.toList());
        });

        playerService.add(board, mock(User.class));

    }

    @Test(expected = UserOnBoardException.class)
    public void playerNotAddedWhenPlayerIsOnBoard() {

        final Long userId = 1L;

        final Board board = new Board();
        board.setPlayerCount(4);

        final User user = new User();
        user.setId(userId);

        when(playerRepository.findByBoardIdAndStatus(any(), any())).then(invocation -> {
            final Player player = new Player();
            player.setUser(new User());
            player.getUser().setId(userId);
            return Arrays.asList(player);
        });

        playerService.add(board, user);

    }

    @Test
    public void playerRemoved() {

        final Long userId = 1L;

        final Board board = new Board();
        board.setPlayerCount(4);

        final User user = new User();
        user.setId(userId);

        when(playerRepository.findByBoardIdAndStatus(any(), any())).then(invocation -> {
            final Player player = new Player();
            player.setUser(user);
            return Arrays.asList(player);
        });

        playerService.remove(board, user);

    }

    @Test(expected = LeaveBoardException.class)
    public void playerNotRemovedWhenGameStarted() {

        final Long userId = 1L;

        final Board board = new Board();
        board.setPlayerCount(4);

        final User user = new User();
        user.setId(userId);

        when(playerRepository.findByBoardIdAndStatus(any(), any())).then(invocation -> {
            final Player player = new Player();
            player.setUser(user);
            return Arrays.asList(player, new Player(), new Player(), new Player());
        });

        playerService.remove(board, user);

    }

    @Test(expected = UserNotOnBoardException.class)
    public void playerNotRemovedWhenPlayerIsNotOnBoard() {

        final Board board = new Board();
        board.setPlayerCount(4);

        playerService.remove(board, mock(User.class));

    }

}
