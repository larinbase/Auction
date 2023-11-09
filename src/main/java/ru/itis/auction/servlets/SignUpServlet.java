package ru.itis.auction.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.auction.models.User;
import ru.itis.auction.repositories.UserRepository;
import ru.itis.auction.repositories.impl.UserRepositoryJDBCImpl;
import ru.itis.auction.services.SecurityService;
import ru.itis.auction.utils.exceptions.SecurityException;


import java.io.IOException;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {

    private SecurityService securityService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signup.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");


        User user;
        try {
            user = securityService.signUp(username, password);
            request.getSession(true).setAttribute("userId", user.getId());
            request.getSession(true).setAttribute("userName", username);
            response.sendRedirect("profile");
        } catch (SecurityException e) {
            response.getWriter().write(e.getMessage());
            response.sendRedirect("sign-up");
        }
    }

}

