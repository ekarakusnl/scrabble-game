package com.gamecity.scrabble.board.service.exception;

/**
 * Custom exception type for existing board exception
 * 
 * @author ekarakus
 *
 */
public class BoardExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param message error message
     */
    public BoardExistsException(final String message) {
        super(message);
    }

}