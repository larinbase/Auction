package ru.itis.auction.servlets.auction.lot;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.auction.repositories.LotRepository;
import ru.itis.auction.repositories.impl.LotRepositoryJDBCImpl;
import ru.itis.auction.services.AuctionService;
import ru.itis.auction.utils.exceptions.AuctionException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@WebServlet("/lots/*")
public class LotDeleteServlet extends HttpServlet {

    AuctionService auctionService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        auctionService = (AuctionService) getServletContext().getAttribute("auctionService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {

            String artikul = request.getRequestURI().substring(getServletContext().getContextPath().length() + 6); // request.getParameter("delete_artikul");
            try {
                auctionService.closeLot(artikul);
                response.sendRedirect(request.getContextPath() + "/auction");
            } catch (AuctionException e) {
                response.sendRedirect(request.getContextPath() + "/auction");
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            response.setStatus(400);
            response.getWriter().println("Wrong request");
            return;
        }
    }

//    @Override
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        String artikul = request.getRequestURI().substring(getServletContext().getContextPath().length() + 6); // request.getParameter("delete_artikul");
//        System.out.println(artikul);
//        try {
//            auctionService.closeLot(artikul);
//            response.sendRedirect(request.getContextPath() + "/auction");
//        } catch (AuctionException e) {
//            response.sendRedirect(request.getContextPath() + "/auction");
//        }
//    }

}
