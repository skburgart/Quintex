package com.quintex.ajax;

import com.quintex.helpers.Logger;
import com.quintex.objects.MessageDBO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author steve
 */
@WebServlet(name = "PostMessage", urlPatterns = {"/user/post-message"})
public class PostMessage extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Logger.log("Post Message Servlet");
        
        MessageDBO mdbo = new MessageDBO();
        int result = 0;
        
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpSession session = httpReq.getSession(true);
        
        try {
            int userid = (Integer) session.getAttribute("userid");
            int topicid = Integer.parseInt(request.getParameter("topicid"));
            String message = request.getParameter("message");
            
            if (message != null
                    && message.length() >= 5
                    && message.length() <= 1024) {
                result = mdbo.add(topicid, userid, message);
            }
            
            if (result > 0) {
                result = topicid;
            }
        } catch (NumberFormatException exp) {
            
        }
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<messageResponse>" + Integer.toString(result) + "</messageResponse>");
        
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
