package com.gamecity.scrabble.user.service.exception;

/**
 * Custom exception type for user not found exception
 * 
 * @author ekarakus
 *
 */
public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param message error message
     */
    public UserNotFoundException(final String message) {
        super(message);
    }

}
