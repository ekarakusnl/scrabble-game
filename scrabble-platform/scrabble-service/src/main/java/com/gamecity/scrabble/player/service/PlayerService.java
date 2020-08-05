package com.gamecity.scrabble.player.service;

import java.util.List;

import com.gamecity.scrabble.board.service.exception.JoinBoardException;
import com.gamecity.scrabble.board.service.exception.LeaveBoardException;
import com.gamecity.scrabble.board.service.exception.UserNotOnBoardException;
import com.gamecity.scrabble.board.service.exception.UserOnBoardException;
import com.gamecity.scrabble.entity.Board;
import com.gamecity.scrabble.entity.Player;
import com.gamecity.scrabble.entity.User;

/**
 * Services for {@link Player} operations.
 * 
 * @author ekarakus
 *
 */
public interface PlayerService {

    /**
     * Adds the {@link User} to the {@link Board}.
     * 
     * @param board the {@link Board}
     * @param user  the {@link User}
     * @throws UserOnBoardException if the <code>user</code> has already been on the <code>board</code>
     * @throws JoinBoardException   if the <code>user</code> hasn't been able to join the
     *                              <code>board</code>
     */
    void add(Board board, User user) throws UserOnBoardException, JoinBoardException;

    /**
     * Removes the {@link User} from the {@link Board}.
     * 
     * @param board the {@link Board}
     * @param user  the {@link User}
     * @throws UserNotOnBoardException if the <code>user</code> hasn't been on the <code>board</code>
     * @throws LeaveBoardException     if the <code>user</code> hasn't been able to leave the
     *                                 <code>board</code>
     */
    void remove(Board board, User user) throws UserNotOnBoardException, LeaveBoardException;

    /**
     * Gets the active players on a board.
     * 
     * @param board the {@link Board}
     * @return {@link List} of active {@link Player}
     */
    List<Player> getPlayersOnBoard(Board board);

}
