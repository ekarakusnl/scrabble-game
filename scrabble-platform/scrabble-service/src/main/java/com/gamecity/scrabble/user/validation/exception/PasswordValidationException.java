package com.gamecity.scrabble.user.validation.exception;

/**
 * Custom exception type for password validation exception
 * 
 * @author ekarakus
 *
 */
public class PasswordValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param message validation error message
     */
    public PasswordValidationException(final String message) {
        super(message);
    }

}
