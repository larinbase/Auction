package ru.itis.auction.utils.exceptions;

import ru.itis.auction.services.validation.ErrorEntity;

public class SecurityException extends Exception{
    private final ErrorEntity entity;
    public SecurityException(ErrorEntity entity) {
        super(entity.getMessage());
        this.entity = entity;
    }

    public SecurityException(ErrorEntity entity, String message) {
        super(message);
        this.entity = entity;
    }
}
