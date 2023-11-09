package ru.itis.auction.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.auction.models.Auction;
import ru.itis.auction.services.AuctionService;
import ru.itis.auction.services.SecurityService;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@WebServlet("/main")
public class MainPageServlet extends HttpServlet {
    AuctionService auctionService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        auctionService = (AuctionService) getServletContext().getAttribute("auctionService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        auctionService.createAuction();
//        auctionService.getLotListByAuctionId();
//        auctionService.deleteAuction();
        List<Auction> auctionList = auctionService.getAuctionList();

        request.setAttribute("auctionList", auctionList);
        request.getRequestDispatcher("mainpage.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String auctionName = request.getParameter("auctionName");
        UUID userId = (UUID) request.getSession(false).getAttribute("userId");
        Auction auction = Auction.builder()
                .name(auctionName)
                .userId(userId)
                .build();
        auctionService.createAuction(auction);

        response.sendRedirect("main");
    }

}
