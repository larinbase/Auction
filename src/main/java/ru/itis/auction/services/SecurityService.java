package ru.itis.auction.services;

import jakarta.servlet.http.HttpServletRequest;
import ru.itis.auction.models.User;
import ru.itis.auction.repositories.LotRepository;
import ru.itis.auction.repositories.UserRepository;
import ru.itis.auction.repositories.impl.UserRepositoryJDBCImpl;
import ru.itis.auction.services.validation.ErrorEntity;
import ru.itis.auction.services.validation.Validator;
import ru.itis.auction.utils.ConnectionProvider;
import ru.itis.auction.utils.exceptions.AuctionException;
import ru.itis.auction.utils.exceptions.SecurityException;

import java.util.Optional;

public class SecurityService {

    private UserRepository userRepository;
    private Validator validator;

    public SecurityService(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    public boolean isSigned(HttpServletRequest req) {
        return req.getSession().getAttribute("userId") != null;
    }

    public void signIn(HttpServletRequest req, String username, String password) throws SecurityException {
        Optional<ErrorEntity> optionalError = validator.validateSignIn(username, password);
        if (optionalError.isPresent()) {
            throw new SecurityException(optionalError.get());
        }
        Optional<User> optionalUser = userRepository.findByName(username);
        req.getSession(true).setAttribute("userId", optionalUser.get().getId());
        req.getSession(true).setAttribute("userName", username);
    }

    public User signUp(String username, String password) throws SecurityException {
        Optional<ErrorEntity> optionalError = validator.validateRegistration(username, password);
        if (optionalError.isPresent()) {
            throw new SecurityException(optionalError.get());
        }
        User user = User.builder()
                .name(username)
                .password(password)

                .build();

        userRepository.save(user);
        return user;
    }


    public void signOut(HttpServletRequest req) {
        req.getSession().removeAttribute("userId");
    }

}
