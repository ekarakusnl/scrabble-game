package com.gamecity.scrabble.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.gamecity.scrabble.entity.User;
import com.gamecity.scrabble.user.validation.exception.EmailValidationException;
import com.gamecity.scrabble.user.validation.exception.PasswordValidationException;
import com.gamecity.scrabble.user.validation.rule.EmailValidator;
import com.gamecity.scrabble.user.validation.rule.PasswordValidator;
import com.gamecity.scrabble.validation.Validator;
import com.gamecity.scrabble.validation.exception.AlphanumericNameValidationException;
import com.gamecity.scrabble.validation.rule.AlphanumericNameValidator;

/**
 * Tests for {@link User} attribute validations
 * 
 * @author ekarakus
 *
 */
@SuppressWarnings("javadoc")
@RunWith(BlockJUnit4ClassRunner.class)
public class UserValidatorTest {

    @Test(expected = AlphanumericNameValidationException.class)
    public void usernameInvalid() {
        final Validator usernameValidator = new AlphanumericNameValidator("friedrich'wilhelm");
        usernameValidator.validate();
    }

    @Test
    public void usernameValid() {
        final Validator usernameValidator = new AlphanumericNameValidator("friedrich.wilhelm");
        usernameValidator.validate();
    }

    @Test(expected = EmailValidationException.class)
    public void emailInvalid() {
        final Validator emailValidator = new EmailValidator("friedrich'wilhelm@prussia");
        emailValidator.validate();
    }

    @Test
    public void emailValid() {
        final Validator emailValidator = new EmailValidator("friedrich.wilhelm@prussia.de");
        emailValidator.validate();
    }

    @Test(expected = PasswordValidationException.class)
    public void passwordInvalid() {
        final Validator passwordValidator = new PasswordValidator("fredrich123");
        passwordValidator.validate();
    }

    @Test
    public void passwordValid() {
        final Validator passwordValidator = new PasswordValidator("Friedrich.123");
        passwordValidator.validate();
    }

}
