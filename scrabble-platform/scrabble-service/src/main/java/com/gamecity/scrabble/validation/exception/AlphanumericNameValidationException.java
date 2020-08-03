package com.gamecity.scrabble.validation.exception;

/**
 * Custom exception type for alphanumeric name validation exception
 * 
 * @author ekarakus
 *
 */
public class AlphanumericNameValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param message validation error message
     */
    public AlphanumericNameValidationException(final String message) {
        super(message);
    }

}
