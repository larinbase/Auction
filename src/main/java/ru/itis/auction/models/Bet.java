package ru.itis.auction.models;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bet {
    private int id;
    // Пользователь, сделавший ставку
    private UUID userId;
    // идентификатор лота, по которому сделана ставка
    private int lotId;
    // Размер ставки;
    private double amount;
    // Дата ставки
    private Date dateTime = Date.valueOf(LocalDate.now());

}
