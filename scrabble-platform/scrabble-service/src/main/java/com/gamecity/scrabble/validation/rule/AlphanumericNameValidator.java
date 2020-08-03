package com.gamecity.scrabble.validation.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gamecity.scrabble.validation.Validator;
import com.gamecity.scrabble.validation.exception.AlphanumericNameValidationException;

/**
 * Validates whether a <code>name</code> contains only alphanumeric characters in addition to
 * special characters including <code>(.)</code>, <code>(-)</code> and <code>(_)</code> by verifying
 * the minimum length of 3 and the maximum length of 25 characters.
 * 
 * @author ekarakus
 *
 */
public class AlphanumericNameValidator implements Validator {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z0-9.-_]{3,25}$");
    private final String name;

    /**
     * Creates the validator with the given <code>name</code>
     * 
     * @param name
     */
    public AlphanumericNameValidator(final String name) {
        this.name = name;
    }

    @Override
    public void validate() {
        final Matcher matcher = NAME_PATTERN.matcher(name);
        if (!matcher.matches()) {
            throw new AlphanumericNameValidationException("Invalid board name!");
        }
    }
}
