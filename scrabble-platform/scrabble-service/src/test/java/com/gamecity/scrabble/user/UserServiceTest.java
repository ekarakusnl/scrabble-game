package com.gamecity.scrabble.user;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gamecity.scrabble.AbstractTestCase;
import com.gamecity.scrabble.entity.User;
import com.gamecity.scrabble.repository.UserRepository;
import com.gamecity.scrabble.user.service.UserService;
import com.gamecity.scrabble.user.service.exception.UserExistsException;
import com.gamecity.scrabble.user.service.exception.UserNotFoundException;
import com.gamecity.scrabble.user.service.impl.UserServiceImpl;

/**
 * Tests for {@link UserService} operations
 * 
 * @author ekarakus
 *
 */
@SuppressWarnings("javadoc")
public class UserServiceTest extends AbstractTestCase {

    private UserService userService;
    private UserRepository userRepository;

    @Before
    public void onBefore() {

        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);

    }

    @Test
    public void userCreated() {

        when(userRepository.save(any())).then(invocation -> {
            final User user = new User();
            user.setUsername(TEST_USERNAME);
            return user;
        });

        final User user = userService.create(TEST_USERNAME, TEST_EMAIL, TEST_PASSWORD);

        assertThat(user, notNullValue());
        assertThat(user.getUsername(), equalTo(TEST_USERNAME));

    }

    @Test(expected = UserExistsException.class)
    public void existingUserNotCreated() {

        when(userRepository.save(any())).thenThrow(ConstraintViolationException.class);

        userService.create(TEST_USERNAME, TEST_EMAIL, TEST_PASSWORD);

    }

    @Test
    public void userByIdFound() {

        when(userRepository.findById(any())).thenReturn(Optional.of(mock(User.class)));

        final User user = userService.get(1L);

        assertThat(user, notNullValue());

    }

    @Test(expected = UserNotFoundException.class)
    public void userByIdNotFound() {

        userService.get(1L);

    }

    @Test(expected = UsernameNotFoundException.class)
    public void userByUsernameNotFound() {

        userService.findUserByUsername(TEST_USERNAME);

    }

    @Test
    public void userByUsernameFound() {

        when(userRepository.findByUsername(any())).then(invocation -> {
            final User user = new User();
            user.setUsername(TEST_USERNAME);
            return user;
        });

        assertThat(userService.findUserByUsername(TEST_USERNAME).getUsername(), equalTo(TEST_USERNAME));

    }

}
