package com.quintex.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Steven Burgart
 */
@WebFilter(filterName = "AdminAuth", urlPatterns = {"/admin/*"})
public class AdminAuth implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpSession session = httpReq.getSession(true);

        if (session.getAttribute("flags") == null || !((String) session.getAttribute("flags")).contains("a")) {
            ((HttpServletResponse) response).sendRedirect("/Quintex/user/index.jsp");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // nothing
    }

    @Override
    public void destroy() {
        // nothin
    }
}
