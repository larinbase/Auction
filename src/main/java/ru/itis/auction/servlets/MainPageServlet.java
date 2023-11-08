package ru.itis.auction.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.auction.services.AuctionService;

import java.io.IOException;

@WebServlet("/main")
public class MainPageServlet extends HttpServlet {
    AuctionService auctionService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        auctionService  = (AuctionService) getServletContext().getAttribute("auctionService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("mainpage.ftl").forward(request, response);
//        auctionService.createAuction();
//        auctionService.getLotListByAuctionId();
//        auctionService.deleteAuction();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String auctionName = request.getParameter("auctionName");
        au
    }

}
