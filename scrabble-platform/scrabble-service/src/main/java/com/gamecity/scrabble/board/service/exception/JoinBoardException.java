package com.gamecity.scrabble.board.service.exception;

/**
 * Custom exception type which happens while joining a board
 * 
 * @author ekarakus
 *
 */
public class JoinBoardException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param message error message
     */
    public JoinBoardException(final String message) {
        super(message);
    }

}