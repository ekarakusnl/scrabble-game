package com.gamecity.scrabble.board.service;

import com.gamecity.scrabble.board.service.exception.BoardExistsException;
import com.gamecity.scrabble.board.service.exception.BoardNotFoundException;
import com.gamecity.scrabble.board.service.exception.JoinBoardException;
import com.gamecity.scrabble.board.service.exception.LeaveBoardException;
import com.gamecity.scrabble.entity.Board;
import com.gamecity.scrabble.entity.User;

/**
 * Services for {@link Board} operations.
 * 
 * @author ekarakus
 *
 */
public interface BoardService {

    /**
     * Creates a {@link Board} by the given <code>board attributes</code>.
     * 
     * @param ownerId     id of the {@link User} who creates the board
     * @param name        name of the {@link Board}
     * @param playerCount required/max allowed player count to start the game
     * @param duration    max duration of a player move in minutes
     * @return the {@link Board}
     * @throws BoardExistsException when a board with the same name exists
     */
    Board create(Long ownerId, String name, Integer playerCount, Integer duration) throws BoardExistsException;

    /**
     * Gets the {@link Board} by the given <code>id</code>.
     * 
     * @param id identifier of the {@link Board}
     * @return the {@link Board}
     * @throws BoardNotFoundException when a {@link Board} with the given id doesn't exist in the
     *                                platform
     */
    Board get(Long id) throws BoardNotFoundException;

    /**
     * Adds a {@link User} to a {@link Board} as a player.
     * 
     * @param id     identifier of the {@link Board}
     * @param userId identifier of the joined {@link User}
     * @return the {@link Board}
     * @throws JoinBoardException when the <code>user</code> isn't able to join the <code>board</code>.
     */
    Board join(Long id, Long userId) throws JoinBoardException;

    /**
     * Removes a {@link User} from a {@link Board}.
     * 
     * @param id     identifier of the {@link Board}
     * @param userId identifier of the left {@link User}
     * @return the {@link Board}
     * @throws LeaveBoardException when the <code>user</code> isn't able to leave the
     *                             <code>board</code>.
     */
    Board leave(Long id, Long userId) throws LeaveBoardException;

}