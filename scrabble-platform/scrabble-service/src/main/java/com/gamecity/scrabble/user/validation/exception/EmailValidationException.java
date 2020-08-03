package com.gamecity.scrabble.user.validation.exception;

/**
 * Custom exception type for mail validation exception
 * 
 * @author ekarakus
 *
 */
public class EmailValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param message validation error message
     */
    public EmailValidationException(final String message) {
        super(message);
    }

}
