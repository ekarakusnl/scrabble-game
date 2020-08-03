package com.gamecity.scrabble.user.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gamecity.scrabble.entity.User;
import com.gamecity.scrabble.user.service.exception.UserExistsException;
import com.gamecity.scrabble.user.service.exception.UserNotFoundException;

/**
 * Services for {@link User} operations.
 * 
 * @author ekarakus
 *
 */
public interface UserService {

    /**
     * Creates a {@link User} by the given <code>user</code>.
     * 
     * @param username <code>name</code> of the <code>user</code>.
     * @param email    <code>email</code> of the <code>user</code>.
     * @param password <code>password</code> of the <code>user</code>.
     * @return the created {@link User}
     * @throws UserExistsException when a user with the same <code>username</code> exists in the
     *                             platform
     */
    User create(String username, String email, String password) throws UserExistsException;

    /**
     * Gets the {@link User} by the given <code>userId</code>.
     * 
     * @param id unique identifier of the {@link User}
     * @return the {@link User}
     * @throws UserNotFoundException when a {@link User} with the given id doesn't exist in the platform
     */
    User get(Long id) throws UserNotFoundException;

    /**
     * Finds the {@link User} by the given <code>username</code>
     * 
     * @param username name of the {@link User}
     * @return the {@link User}
     * @throws UsernameNotFoundException when a {@link User} with the given id doesn't exist in the
     *                                   platform
     */
    User findUserByUsername(String username) throws UsernameNotFoundException;

}
