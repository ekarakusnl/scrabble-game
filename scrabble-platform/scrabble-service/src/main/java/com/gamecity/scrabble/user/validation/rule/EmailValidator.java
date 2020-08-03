package com.gamecity.scrabble.user.validation.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gamecity.scrabble.user.validation.exception.EmailValidationException;
import com.gamecity.scrabble.validation.Validator;

/**
 * Validates whether an <code>email</code> matches to a the specified email pattern.
 * 
 * @author ekarakus
 *
 */
public class EmailValidator implements Validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private final String email;

    /**
     * Creates the validator with the given <code>email</code>
     * 
     * @param email
     */
    public EmailValidator(final String email) {
        this.email = email;
    }

    @Override
    public void validate() {
        final Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new EmailValidationException("Invalid email!");
        }
    }
}
