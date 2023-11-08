package ru.itis.auction.servlets.auction;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.auction.models.Lot;
import ru.itis.auction.models.User;
import ru.itis.auction.repositories.BetRepository;
import ru.itis.auction.repositories.UserRepository;
import ru.itis.auction.repositories.impl.BetRepositoryJDBCImpl;
import ru.itis.auction.repositories.LotRepository;
import ru.itis.auction.repositories.impl.LotRepositoryJDBCImpl;
import ru.itis.auction.repositories.impl.UserRepositoryJDBCImpl;
import ru.itis.auction.services.AuctionService;
import ru.itis.auction.utils.exceptions.AuctionException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/auction")
public class AuctionServlet extends HttpServlet {

    LotRepository lotRepository;
    BetRepository betRepository;
    UserRepository userRepository;
    AuctionService auctionService;
    List<Lot> lots;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        lotRepository = (LotRepositoryJDBCImpl) getServletContext().getAttribute("lotRepository");
        betRepository = (BetRepositoryJDBCImpl) getServletContext().getAttribute("betRepository");
        userRepository = (UserRepositoryJDBCImpl) getServletContext().getAttribute("userRepository");
        auctionService = (AuctionService) getServletContext().getAttribute("auctionService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID userId = (UUID) request.getSession(false).getAttribute("userId");
        User user = userRepository.findById(userId).get();


        lots = auctionService.getLotList();

        request.setAttribute("role", user.getRole());
        request.setAttribute("lots", lots);

        request.getRequestDispatcher("auctionpage.ftl").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
