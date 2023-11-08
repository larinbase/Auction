package ru.itis.auction.utils.exceptions;

import ru.itis.auction.services.validation.ErrorEntity;

public class AuctionException extends Exception{
    private final ErrorEntity entity;
    public AuctionException(ErrorEntity entity) {
        super(entity.getMessage());
        this.entity = entity;
    }

    public AuctionException(ErrorEntity entity, String message) {
        super(message);
        this.entity = entity;
    }
}
