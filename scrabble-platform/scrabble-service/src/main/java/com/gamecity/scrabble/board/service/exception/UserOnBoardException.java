package com.gamecity.scrabble.board.service.exception;

/**
 * Custom exception type for a user which has already been on a board
 * 
 * @author ekarakus
 *
 */
public class UserOnBoardException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param message error message
     */
    public UserOnBoardException(final String message) {
        super(message);
    }

}