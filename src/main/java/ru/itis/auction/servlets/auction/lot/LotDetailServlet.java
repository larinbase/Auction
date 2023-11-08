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

import java.io.IOException;

@WebServlet("/lot/detail")
public class LotDetailServlet extends HttpServlet {
    private LotRepository lotRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        lotRepository = (LotRepositoryJDBCImpl) getServletContext().getAttribute("lotRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO
        try {
            String id = req.getParameter("id");
            if(id == null){
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Bad request. No id has been provided.");
            }
            //Lot lot = lotRepository.getDetail(Integer.parseInt(id));
            //test
            Lot lot = Lot.builder()
                    .name("testName")
                    .id(1)
                    .description("testDescription")
                    .build();
            //
            if(lot == null){
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
            }
            req.setAttribute("lot", lot);
            getServletContext().getRequestDispatcher("/WEB-INF/view/books/detail.jsp").forward(req, resp);
        } catch (Exception e) { // DbException
            throw new ServletException(e);
        }
    }
}
