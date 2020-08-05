package com.gamecity.scrabble.board.service.exception;

/**
 * Custom exception type which happens while leaving a board
 * 
 * @author ekarakus
 *
 */
public class LeaveBoardException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param message error message
     */
    public LeaveBoardException(final String message) {
        super(message);
    }

}