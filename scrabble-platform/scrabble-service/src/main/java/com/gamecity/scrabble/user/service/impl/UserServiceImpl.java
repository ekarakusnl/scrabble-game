package com.gamecity.scrabble.user.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gamecity.scrabble.entity.User;
import com.gamecity.scrabble.entity.UserAuthority;
import com.gamecity.scrabble.repository.UserRepository;
import com.gamecity.scrabble.user.service.UserService;
import com.gamecity.scrabble.user.service.exception.UserExistsException;
import com.gamecity.scrabble.user.service.exception.UserNotFoundException;
import com.gamecity.scrabble.user.validation.rule.EmailValidator;
import com.gamecity.scrabble.user.validation.rule.PasswordValidator;
import com.gamecity.scrabble.validation.Validator;
import com.gamecity.scrabble.validation.rule.AlphanumericNameValidator;

/**
 * Implementation details for the {@link UserService}
 * 
 * @author ekarakus
 *
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Creates the constructor with the wired {@link UserRepository}
     * 
     * @param userRepository
     */
    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(String username, String email, String password) throws UserExistsException {

        final List<Validator> userValidators = new ArrayList<Validator>();
        userValidators.add(new AlphanumericNameValidator(username));
        userValidators.add(new EmailValidator(email));
        userValidators.add(new PasswordValidator(password));
        userValidators.stream().forEach(Validator::validate);

        final User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);

        final User savedUser;
        try {
            savedUser = userRepository.save(user);
        } catch (ConstraintViolationException e) {
            throw new UserExistsException("User with the name " + user.getUsername() + " has already been registered!");
        }

        return savedUser;
    }

    @Override
    public User get(Long id) throws UserNotFoundException {

        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with the id " + id + " doesn't exist!"));

        return user;
    }

    @Override
    public User findUserByUsername(final String username) throws UsernameNotFoundException {

        final User user = userRepository.findByUsername(username);
        if (user == null || !user.isEnabled() || !user.isAccountNonExpired() || !user.isAccountNonLocked()) {
            throw new UsernameNotFoundException("User " + username + " isn't found or allowed to login to the platform.");
        }

        final UserAuthority userAuthority = new UserAuthority();
        userAuthority.setAuthority("ROLE_ADMIN");
        final Set<UserAuthority> authorities = new HashSet<>();
        authorities.add(userAuthority);
        user.setAuthorities(authorities);
        return user;
    }

}
