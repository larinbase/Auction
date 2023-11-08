package ru.itis.auction.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@Builder
@ToString
public class Lot {
    private int id;
    // Название лота
    private String name;
    // Описание лота
    private String description;
    // статус лота
    private String status;
    // артикул
    private String artikul;
    // ставки по лоту
    ArrayList<Bet> arrayList;

    private List<Bet> bets;

    public Lot(){
//        arrayList = new ArrayList<>();
//        arrayList.add(new Bet());
//        bets = arrayList;
    }


}
