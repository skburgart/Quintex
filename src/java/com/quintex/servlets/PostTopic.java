package com.quintex.servlets;

import com.quintex.database.TopicDBO;
import com.quintex.utility.Logger;
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
@WebServlet(name = "PostTopic", urlPatterns = {"/user/post-topic"})
public class PostTopic extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Logger.log("Entering Post Topic Servlet");

        TopicDBO tdbo = new TopicDBO();
        int result = 0;

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpSession session = httpReq.getSession(true);

        try {
            int userid = (Integer) session.getAttribute("userid");
            int boardid = Integer.parseInt(request.getParameter("boardid"));
            String title = request.getParameter("title");
            String message = request.getParameter("message");

            if (title != null && message != null
                    && title.length() >= 6
                    && title.length() <= 64
                    && message.length() >= 5
                    && message.length() <= 2048) {
                result = tdbo.create(boardid, userid, title, message);
            }
        } catch (NumberFormatException exp) {
        }

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<topicResponse>" + Integer.toString(result) + "</topicResponse>");

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
