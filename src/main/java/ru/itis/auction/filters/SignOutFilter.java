package ru.itis.auction.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.auction.services.SecurityService;

import java.io.IOException;

@WebFilter(urlPatterns = {"/sign-up", "/sign-in"})
public class SignOutFilter implements Filter {
    SecurityService securityService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        securityService = (SecurityService) filterConfig.getServletContext().getAttribute("securityService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        HttpSession session = request.getSession(false);
        String profileURI = request.getContextPath() + "/profile";
        boolean loggedIn = securityService.isSigned(request);
        if (!loggedIn) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(profileURI);
        }
    }

    @Override
    public void destroy() {
        jakarta.servlet.Filter.super.destroy();
    }
}
