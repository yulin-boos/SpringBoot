package edu.imnc.javaweb.springboot.utils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({"/api/users/*"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("userID") != null) {
            chain.doFilter(request, response);
            return;
        }

        ResponseJSON responseJSON = ResponseJSON.error();
        responseJSON.setCode(401);
        responseJSON.setMessage("未登录");

        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write("{\"code\":401,\"message\":\"未登录\",\"data\":null,\"success\":false}");
    }
}
