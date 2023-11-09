package ru.itis.auction.utils.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.itis.auction.repositories.*;
import ru.itis.auction.repositories.impl.*;
import ru.itis.auction.services.AuctionService;
import ru.itis.auction.services.SecurityService;
import ru.itis.auction.services.validation.Validator;
import ru.itis.auction.services.validation.ValidatorImpl;
import ru.itis.auction.utils.ConnectionProvider;
import ru.itis.auction.utils.exceptions.DbException;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

            LotRepository lotRepository = new LotRepositoryJDBCImpl();
            UserRepository userRepository = new UserRepositoryJDBCImpl();
            BetRepository betRepository = new BetRepositoryJDBCImpl();
            AwardRepository awardRepository = new AwardRepositoryJDBCImpl();
            AuctionRepository auctionRepository = new AuctionRepositoryJDBCImpl();

            Validator lotValidator = new ValidatorImpl(lotRepository);
            Validator userValidator = new ValidatorImpl(userRepository);

            sce.getServletContext().setAttribute("lotRepository", lotRepository);
            sce.getServletContext().setAttribute("userRepository", userRepository);
            sce.getServletContext().setAttribute("betRepository", betRepository);
            sce.getServletContext().setAttribute("awardRepository", awardRepository);
            sce.getServletContext().setAttribute("securityService", new SecurityService(userRepository, userValidator));
            sce.getServletContext().setAttribute("auctionService", new AuctionService(lotRepository, lotValidator, betRepository, userRepository, awardRepository, auctionRepository));
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
}