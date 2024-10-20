/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import context.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author Jay
 */
public class LoginServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        try {
            HttpSession session = request.getSession();
            String accountName = request.getParameter("accountName");
            String password = request.getParameter("accountPass");
            String remember = request.getParameter("remember");

            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.getAccount(accountName, password);
            if (account != null && account.getStatus() == 0) {
                request.setAttribute("loginError", "Your account is in active proccessing. Please wait!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            // edited 26-10
            if (account != null && remember == null) {
                session.setAttribute("account", account);
                request.getRequestDispatcher("home.jsp").forward(request, response);
                request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
            } else if(account != null && remember != null ){
                Cookie name = new Cookie("name", accountName);
                name.setMaxAge(60 * 60);
                response.addCookie(name);
                Cookie pass = new Cookie("pass", password);
                pass.setMaxAge(60 * 60);
                response.addCookie(pass);
                session.setAttribute("account", account);
                request.getRequestDispatcher("home.jsp").forward(request, response);
                request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
            }else{
                request.setAttribute("loginError", "Username or Password is wrong");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
