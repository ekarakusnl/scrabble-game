package com.gamecity.scrabble.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gamecity.scrabble.entity.Board;
import com.gamecity.scrabble.entity.Player;
import com.gamecity.scrabble.entity.PlayerStatus;

/**
 * Repository services for fetching and saving {@link Player} data.
 * 
 * @author ekarakus
 *
 */
public interface PlayerRepository extends CrudRepository<Player, Long> {

    /**
     * Finds the players on a board by the specified values
     * 
     * @param boardId id of the {@link Board}
     * @param status  of the player
     * @return {@link List} of boards
     */
    List<Player> findByBoardIdAndStatus(Long boardId, PlayerStatus status);

    /**
     * Finds the corresponding player regarding of the turn by the specified values
     * 
     * @param boardId id of the {@link Board}
     * @param status  of the player
     * @param turn    number of the player turn
     * @return the {@link Player}
     */
    Player findByBoardIdAndStatusAndTurn(Long boardId, PlayerStatus status, int turn);

}
