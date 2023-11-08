package ru.itis.auction.services;

import ru.itis.auction.models.Award;
import ru.itis.auction.models.Bet;
import ru.itis.auction.models.Lot;
import ru.itis.auction.models.User;
import ru.itis.auction.repositories.AwardRepository;
import ru.itis.auction.repositories.BetRepository;
import ru.itis.auction.repositories.LotRepository;
import ru.itis.auction.repositories.UserRepository;
import ru.itis.auction.repositories.impl.LotRepositoryJDBCImpl;
import ru.itis.auction.services.validation.ErrorEntity;
import ru.itis.auction.services.validation.Validator;
import ru.itis.auction.services.validation.ValidatorImpl;
import ru.itis.auction.utils.exceptions.AuctionException;

import java.util.*;

public class AuctionService {
    private LotRepository lotRepository;
    private Validator validator;
    private BetRepository betRepository;
    private UserRepository userRepository;
    private AwardRepository awardRepository;

    public AuctionService(LotRepository lotRepository, Validator validator, BetRepository betRepository, UserRepository userRepository, AwardRepository awardRepository) {
        this.lotRepository = lotRepository;
        this.validator = validator;
        this.betRepository = betRepository;
        this.userRepository = userRepository;
        this.awardRepository = awardRepository;
    }

    public void createLot(String name, String description) throws AuctionException {
        Optional<ErrorEntity> optionalError = validator.validateLotCreate(name, description);
        if (optionalError.isPresent()) {
            throw new AuctionException(optionalError.get());
        }
        Lot lot = Lot.builder()
                .name(name)
                .description(description)
                .build();
        lotRepository.save(lot);
    }

    public void makeBet(Double amount, String artikul, Object userId) throws AuctionException {
        //TODO artikul вместо lotName, потому что пока нет проверки на not null и exist artikul
        Optional<ErrorEntity> optionalError = validator.validateBetCreate(amount, artikul);
        if (optionalError.isPresent()) {
            throw new AuctionException(optionalError.get());
        }
        Lot lot = lotRepository.findByArtikul(artikul).get();
        lot.setBets(betRepository.findByLotId(lot.getId()));

        Bet bet = Bet.builder()
                .amount(amount)
                .lotId(lot.getId())
                .userId((UUID) userId)
                .build();

        for (Bet bet1 : lot.getBets()) {
            if (bet1.getUserId().equals(userId)) {
                if (bet.getAmount() > bet1.getAmount()) {
                    bet.setId(bet1.getId());
                    betRepository.update(bet);
                    return;
                } else {
                    return;
                }
            }
        }
        betRepository.save(bet);
    }

    public void closeLot(String artikul) throws AuctionException {
        Optional<ErrorEntity> optionalError = validator.validateLotClose(artikul);
        if (optionalError.isPresent()) {
            throw new AuctionException(optionalError.get());
        }
        Lot lot = lotRepository.findByArtikul(artikul).get();
        List<Bet> bets = betRepository.findByLotId(lot.getId());
        bets.sort((bet1, bet2) -> Double.compare(bet2.getAmount(), bet1.getAmount()));
        lot.setBets(bets);
        boolean flag = false;
        if (lot.getBets() != null) {
            for (Bet bet : lot.getBets()
            ) {
                if (!flag) {
                    awardRepository
                            .save(
                                    Award.builder()
                                            .lotId(lot.getId())
                                            .userId(bet.getUserId())
                                            .build()
                            );
                }
                flag = true;
                betRepository.delete(bet);
            }
        }
        lot.setStatus("close");
        lotRepository.update(lot);
    }

    public List<Lot> getLotList() {
        List<Lot> lots = new ArrayList<>();
        if (lotRepository.findAll() != null) {
            lots = lotRepository.findAll();
            for (Lot lot : lots
            ) {
                List<Bet> bets = betRepository.findByLotId(lot.getId());
                bets.sort((bet1, bet2) -> Double.compare(bet2.getAmount(), bet1.getAmount()));
                lot.setBets(bets);
            }
        }
        return lots;
    }

    public List<Lot> getAwards(UUID userId) {
        Optional<List<Award>> awards = awardRepository.findByUserId(userId);
        List<Lot> lots = new ArrayList<>();
        if (awards.isPresent()) {
            for (Award award : awards.get()) {
                Optional<Lot> optionalLot = lotRepository.findById(award.getLotId());
                if (optionalLot.get().getStatus().equals("close")) {
                    lots.add(optionalLot.get());
                }
            }
        }
        return lots;
    }

}
