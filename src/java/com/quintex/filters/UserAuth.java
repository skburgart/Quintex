package com.quintex.filters;

import com.quintex.database.UserDBO;
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
@WebFilter(filterName = "UserAuthFilter", urlPatterns = {"/user/*"})
public class UserAuth implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpSession session = httpReq.getSession(true);

        Object userid = session.getAttribute("userid");

        if (userid == null) {
            ((HttpServletResponse) response).sendRedirect("/Quintex/index.jsp");
            return;
        }

        UserDBO udbo = new UserDBO();
        udbo.updateLastAction((Integer) userid);

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
