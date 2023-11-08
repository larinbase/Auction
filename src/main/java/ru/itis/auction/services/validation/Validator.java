package ru.itis.auction.services.validation;

import java.util.Optional;

public interface Validator {
    Optional<ErrorEntity> validateRegistration(String name, String password);
    Optional<ErrorEntity> validateSignIn(String name, String password);
    Optional<ErrorEntity> validateLotCreate(String name, String description);
    Optional<ErrorEntity> validateLotClose(String name);
    Optional<ErrorEntity> validateBetCreate(Double amount, String name);
}
