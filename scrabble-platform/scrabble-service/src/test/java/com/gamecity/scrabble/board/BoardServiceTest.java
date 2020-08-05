package com.gamecity.scrabble.board;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import com.gamecity.scrabble.AbstractTestCase;
import com.gamecity.scrabble.board.service.BoardService;
import com.gamecity.scrabble.board.service.exception.JoinBoardException;
import com.gamecity.scrabble.board.service.exception.LeaveBoardException;
import com.gamecity.scrabble.board.service.impl.BoardServiceImpl;
import com.gamecity.scrabble.entity.Board;
import com.gamecity.scrabble.entity.BoardStatus;
import com.gamecity.scrabble.entity.User;
import com.gamecity.scrabble.player.service.PlayerService;
import com.gamecity.scrabble.repository.BoardRepository;
import com.gamecity.scrabble.user.service.UserService;
import com.gamecity.scrabble.utility.DateUtils;

/**
 * Tests for {@link BoardService} operations
 * 
 * @author ekarakus
 *
 */
@SuppressWarnings("javadoc")
public class BoardServiceTest extends AbstractTestCase {

    private BoardService boardService;
    private BoardRepository boardRepository;
    private UserService userService;
    private PlayerService playerService;

    @Before
    public void onBefore() {

        boardRepository = mock(BoardRepository.class);
        userService = mock(UserService.class);
        playerService = mock(PlayerService.class);
        boardService = new BoardServiceImpl(boardRepository, userService, playerService);
    }

    @Test
    public void boardCreated() {

        final long ownerId = 1L;
        final String name = "APerfectBoard";
        final int playerCount = 2;
        final int duration = 1;

        when(boardRepository.save(any())).then(invocation -> {
            final Board board = new Board();
            board.setName(name);
            return board;
        });

        when(userService.get(any())).thenReturn(mock(User.class));

        final Board board = boardService.create(ownerId, name, playerCount, duration);

        assertThat(board, notNullValue());
        assertThat(board.getName(), equalTo(board.getName()));
        assertThat(board.getStatus(), equalTo(BoardStatus.PENDING));
    }

    @Test
    public void userJoined() {

        final long boardId = 1L;
        final long userId = 2L;

        when(boardRepository.findById(any())).thenReturn(Optional.of(new Board()));
        when(userService.get(any())).thenReturn(mock(User.class));

        final Board board = boardService.join(boardId, userId);

        assertThat(board, notNullValue());
        assertThat(board.getStatus(), equalTo(BoardStatus.PENDING));

    }

    @Test(expected = JoinBoardException.class)
    public void userJoinFailedOnStartedBoard() {

        final long boardId = 1L;
        final long userId = 2L;

        when(boardRepository.findById(any())).then(invocation -> {
            final Board board = new Board();
            board.setStartDate(DateUtils.now());
            board.setStatus(BoardStatus.STARTED);
            return Optional.of(board);
        });

        boardService.join(boardId, userId);

    }

    @Test(expected = JoinBoardException.class)
    public void userJoinFailedOnFinishedBoard() {

        final long boardId = 1L;
        final long userId = 2L;

        when(boardRepository.findById(any())).then(invocation -> {
            final Board board = new Board();
            board.setEndDate(DateUtils.now());
            board.setStatus(BoardStatus.FINISHED);
            return Optional.of(board);
        });

        boardService.join(boardId, userId);

    }

    @Test(expected = JoinBoardException.class)
    public void userJoinFailedOnTerminatedBoard() {

        final long boardId = 1L;
        final long userId = 2L;

        when(boardRepository.findById(any())).then(invocation -> {
            final Board board = new Board();
            board.setStatus(BoardStatus.TERMINATED);
            board.setTerminateDate(DateUtils.now());
            return Optional.of(board);
        });

        boardService.join(boardId, userId);

    }

    @Test
    public void userLeft() {

        final long boardId = 1L;
        final long userId = 2L;

        when(boardRepository.findById(any())).thenReturn(Optional.of(new Board()));
        when(userService.get(any())).thenReturn(mock(User.class));

        final Board board = boardService.leave(boardId, userId);

        assertThat(board, notNullValue());
        assertThat(board.getStatus(), equalTo(BoardStatus.PENDING));

    }

    @Test(expected = LeaveBoardException.class)
    public void userLeaveFailedOnStartedBoard() {

        final long boardId = 1L;
        final long userId = 2L;

        when(boardRepository.findById(any())).then(invocation -> {
            final Board board = new Board();
            board.setStartDate(DateUtils.now());
            board.setStatus(BoardStatus.STARTED);
            return Optional.of(board);
        });

        boardService.leave(boardId, userId);

    }

}