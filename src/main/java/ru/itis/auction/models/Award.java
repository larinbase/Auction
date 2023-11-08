package ru.itis.auction.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Award {
    private Integer id;
    private UUID userId;
    private Integer lotId;
}
