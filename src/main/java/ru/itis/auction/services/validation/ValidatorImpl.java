package ru.itis.auction.services.validation;


import ru.itis.auction.repositories.BetRepository;
import ru.itis.auction.repositories.LotRepository;
import ru.itis.auction.repositories.UserRepository;
import ru.itis.auction.utils.exceptions.AuctionException;

import java.util.Optional;

public class ValidatorImpl implements Validator {
    private UserRepository userRepository;
    private LotRepository lotRepository;
    private BetRepository betRepository;

    public ValidatorImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ValidatorImpl(LotRepository lotRepository) {
        this.lotRepository = lotRepository;
    }

    public ValidatorImpl(BetRepository betRepository, LotRepository lotRepository){
        this.betRepository = betRepository;
        this.lotRepository = lotRepository;
    }

    @Override
    public Optional<ErrorEntity> validateRegistration(String name, String password) {
        if (name == null) {
            return Optional.of(ErrorEntity.INVALID_NAME);
        } else if (password == null) {
            return Optional.of(ErrorEntity.INVALID_PASSWORD);
        } else if (userRepository.findByName(name).isPresent()) {
            return Optional.of(ErrorEntity.NAME_ALREADY_TAKEN);
        } else if (password.length() < 5) {
            return Optional.of(ErrorEntity.PASSWORD_TOO_SIMPLE);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ErrorEntity> validateSignIn(String name, String password) {
        if (name == null) {
            return Optional.of(ErrorEntity.INVALID_NAME);
        } else if (password == null) {
            return Optional.of(ErrorEntity.INVALID_PASSWORD);
        } else if (userRepository.findByName(name).isEmpty()) {
            return Optional.of(ErrorEntity.USER_NOT_FOUND);
        } else if (!userRepository.findByName(name).get().getPassword().equals(password)) {
            return Optional.of(ErrorEntity.INCORRECT_PASSWORD);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ErrorEntity> validateLotCreate(String name, String description) {
        if (name == null) {
            return Optional.of(ErrorEntity.INVALID_LOT_NAME);
        } else if (description == null) {
            return Optional.of(ErrorEntity.INVALID_LOT_DESCRIPTION);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ErrorEntity> validateLotClose(String artikul) {
        if (artikul.isEmpty()) {
            return Optional.of(ErrorEntity.INVALID_LOT_ARTIKUL);
        } else if (lotRepository.findByArtikul(artikul).isEmpty()) {
            return Optional.of(ErrorEntity.LOT_NOT_FOUND);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ErrorEntity> validateBetCreate(Double amount, String artikul) {
        if (artikul.isEmpty()) {
            return Optional.of(ErrorEntity.INVALID_LOT_ARTIKUL);
        } else if (amount == null) {
            return Optional.of(ErrorEntity.INVALID_BET_AMOUNT);
        } else if (lotRepository.findByArtikul(artikul).isEmpty()){
            return Optional.of(ErrorEntity.LOT_NOT_FOUND);
        }
        return Optional.empty();
    }
}
