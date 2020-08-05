package com.gamecity.scrabble.board.service.exception;

/**
 * Custom exception type for a user which hasn't been on a board
 * 
 * @author ekarakus
 *
 */
public class UserNotOnBoardException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param message error message
     */
    public UserNotOnBoardException(final String message) {
        super(message);
    }

}