package com.gamecity.scrabble.validation;

/**
 * A <code>validator</code> is used to validate a certain validation rule by using the corresponding
 * validation type
 * 
 * @author ekarakus
 *
 */
public interface Validator {

    /**
     * Validates a value by the given validation type
     */
    void validate();

}
