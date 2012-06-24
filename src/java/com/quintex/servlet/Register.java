package com.quintex.servlet;

import com.quintex.database.UserDBO;
import com.quintex.utility.Logger;
import com.quintex.utility.Regex;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Steven Burgart
 */
@WebServlet(name = "UserRegisterServlet", urlPatterns = {"/register"})
public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Logger.log("Entering Register Servlet");

        UserDBO udbo = new UserDBO();
        int result = 0;

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");


        if (username != null
                && password != null
                && email != null
                && password.length() >= 6
                && username.length() <= 32
                && username.length() >= 3
                && Regex.match(Regex.email, email)
                && Regex.match(Regex.username, username)) {
            result = udbo.add(username, password, email);
        } 

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<registerResponse>" + Integer.toString(result) + "</registerResponse>");

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
