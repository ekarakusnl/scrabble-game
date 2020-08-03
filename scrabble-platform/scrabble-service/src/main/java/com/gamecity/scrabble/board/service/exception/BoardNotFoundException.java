package com.gamecity.scrabble.board.service.exception;

/**
 * Custom exception type for board not found exception
 * 
 * @author ekarakus
 *
 */
public class BoardNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param message error message
     */
    public BoardNotFoundException(final String message) {
        super(message);
    }

}