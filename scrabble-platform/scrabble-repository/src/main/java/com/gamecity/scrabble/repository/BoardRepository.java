package com.gamecity.scrabble.repository;

import org.springframework.data.repository.CrudRepository;

import com.gamecity.scrabble.entity.Board;

/**
 * Repository services for fetching and saving {@link Board} data.
 * 
 * @author ekarakus
 *
 */
public interface BoardRepository extends CrudRepository<Board, Long> {

}