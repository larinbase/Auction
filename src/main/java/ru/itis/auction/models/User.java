package ru.itis.auction.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    private UUID id;
    private String name;
    private String password;
    private String role;
    private List<Bet> bets = new ArrayList<>();
}
