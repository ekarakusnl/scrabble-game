package com.gamecity.scrabble.user.service.exception;

/**
 * Custom exception type for existing user exception
 * 
 * @author ekarakus
 *
 */
public class UserExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param message error message
     */
    public UserExistsException(final String message) {
        super(message);
    }

}
