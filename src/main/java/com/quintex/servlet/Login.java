package com.quintex.servlet;

import com.quintex.database.UserDBO;
import com.quintex.utility.Logger;
import com.quintex.value.UserVO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Steven Burgart
 */
@WebServlet(name = "UserLoginServlet", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Logger.log("Entering Login Servlet");

        UserDBO udbo = new UserDBO();
        int result = 0;

        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if (username != null && password != null) {
            result = udbo.validate(username, password);
            if (result == 1) {
                UserVO user = udbo.getFromUsername(username);
                udbo.logLogin(user.getUserid(), request.getRemoteAddr());
                session.setAttribute("userid", user.getUserid());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("flags", user.getFlags());
            }
        }

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<loginResponse>" + Integer.toString(result) + "</loginResponse>");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
