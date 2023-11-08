package ru.itis.auction.servlets;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.auction.models.Lot;
import ru.itis.auction.services.AuctionService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    AuctionService auctionService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        auctionService  = (AuctionService) getServletContext().getAttribute("auctionService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        UUID userId = (UUID) request.getSession(true).getAttribute("userId");
        List<Lot> lots = auctionService.getAwards(userId);

        String userName = (String) request.getSession().getAttribute("userName");

        request.setAttribute("userName", userName);
        request.setAttribute("userId", userId);
        request.setAttribute("lots", lots);

        request.getRequestDispatcher("profile.ftl").forward(request, response);
    }
}
