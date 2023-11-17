package ru.itis.auction.servlets.auction.bet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.auction.models.Bet;
import ru.itis.auction.models.Lot;
import ru.itis.auction.repositories.BetRepository;
import ru.itis.auction.repositories.impl.BetRepositoryJDBCImpl;
import ru.itis.auction.repositories.LotRepository;
import ru.itis.auction.repositories.impl.LotRepositoryJDBCImpl;
import ru.itis.auction.services.AuctionService;
import ru.itis.auction.utils.exceptions.AuctionException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/bet/create")
public class BetCreateServlet extends HttpServlet {

    BetRepository betRepository;
    LotRepository lotRepository;
    AuctionService auctionService;
    List<Bet> bets;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        betRepository = (BetRepositoryJDBCImpl) getServletContext().getAttribute("betRepository");
        lotRepository = (LotRepositoryJDBCImpl) getServletContext().getAttribute("lotRepository");
        auctionService = (AuctionService) getServletContext().getAttribute("auctionService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Double amount = Double.parseDouble(request.getParameter("bidAmount"));
        String artikul = request.getParameter("lotArtikul");
        System.out.println(amount + " " +  artikul);

        try {
            UUID userId = (UUID) request.getSession().getAttribute("userId");
            auctionService.makeBet(amount, artikul, userId);
//            Integer id = lotRepository.findByArtikul(artikul).get().getAuctionId();
//            List<Lot> lots = auctionService.getLotListByAuctionId(id);
//            getServletContext().setAttribute("lots", lots);
            response.sendRedirect( request.getContextPath() + "/auction");
        } catch (AuctionException | NullPointerException | NumberFormatException e) {
            response.sendRedirect( request.getContextPath() + "/auction");
        }
    }
}
