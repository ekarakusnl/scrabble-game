package com.gamecity.scrabble.user.validation.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gamecity.scrabble.user.validation.exception.PasswordValidationException;
import com.gamecity.scrabble.validation.Validator;

/**
 * Validates whether a <code>password</code> contains at least 1 numeric character, 1 lowercase
 * alpabetic character, 1 uppercase alphabetic character and 1 special character by verifying the
 * minimum length of total 8 characters.
 * 
 * @author ekarakus
 *
 */
public class PasswordValidator implements Validator {

    private final static Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[.@#$%^&+=])(?=\\S+$).{8,}$");
    private final String password;

    /**
     * Creates the validator with the given <code>password</code>
     * 
     * @param password
     */
    public PasswordValidator(final String password) {
        this.password = password;
    }

    @Override
    public void validate() {
        final Matcher matcher = PASSWORD_PATTERN.matcher(password);
        if (!matcher.matches()) {
            throw new PasswordValidationException("Invalid password!");
        }
    }

}
