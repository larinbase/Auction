package ru.itis.auction.models;

import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Auction {
    private Integer id;
    private String name;
    private UUID userId;
    private String status;

}