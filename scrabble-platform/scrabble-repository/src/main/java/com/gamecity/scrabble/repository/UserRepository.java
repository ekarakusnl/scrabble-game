package com.gamecity.scrabble.repository;

import org.springframework.data.repository.CrudRepository;

import com.gamecity.scrabble.entity.User;

/**
 * Repository services for fetching and saving {@link User} data.
 * 
 * @author ekarakus
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Finds and returns the {@link User} if the username of a <code>user</code> matches with the the
     * given <code>username</code>. Otherwise returns <code>null</code>.
     * 
     * @param username of the {@link User}
     * @return {@link User} with the given <code>username</code>
     */
    User findByUsername(String username);

}
