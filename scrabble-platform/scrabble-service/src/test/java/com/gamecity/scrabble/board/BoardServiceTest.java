package com.gamecity.scrabble.board;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import com.gamecity.scrabble.AbstractTestCase;
import com.gamecity.scrabble.board.service.BoardService;
import com.gamecity.scrabble.board.service.impl.BoardServiceImpl;
import com.gamecity.scrabble.entity.Board;
import com.gamecity.scrabble.entity.BoardStatus;
import com.gamecity.scrabble.entity.User;
import com.gamecity.scrabble.repository.BoardRepository;
import com.gamecity.scrabble.user.service.UserService;

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

    @Before
    public void onBefore() {

        boardRepository = mock(BoardRepository.class);
        userService = mock(UserService.class);
        boardService = new BoardServiceImpl(boardRepository, userService);
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

}