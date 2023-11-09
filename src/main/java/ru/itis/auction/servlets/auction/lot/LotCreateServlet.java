package ru.itis.auction.servlets.auction.lot;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.auction.models.Lot;
import ru.itis.auction.repositories.LotRepository;
import ru.itis.auction.repositories.impl.LotRepositoryJDBCImpl;
import ru.itis.auction.services.AuctionService;
import ru.itis.auction.utils.exceptions.AuctionException;

import java.io.IOException;


@WebServlet("/lot/create")
public class LotCreateServlet extends HttpServlet {
    private AuctionService auctionService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        auctionService = (AuctionService) getServletContext().getAttribute("auctionService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String description = request.getParameter("description");

        try {
            Integer auctionId = (Integer) request.getSession(false).getAttribute("auctionId");
            auctionService.createLot(name, description, auctionId);
        } catch (AuctionException e) {
            response.getWriter().write(e.getMessage());
        }
        response.sendRedirect( request.getContextPath() + "/auction");
    }
}
