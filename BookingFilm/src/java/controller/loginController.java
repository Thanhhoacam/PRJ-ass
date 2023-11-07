/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Admin
 */
public class loginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet loginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<img src=view/images/cinema.jpg/>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/user/loginjsp.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        UserDao db = new UserDao();
        User user = db.findUser(email, pass);
        if (user != null) {
            
            // Tạo và đặt giá trị vào cookies
Cookie usernameCookie = new Cookie("email", email);
Cookie passwordCookie = new Cookie("password", pass);

// Đặt thời gian tồn tại của cookies (ví dụ: 30 ngày)
usernameCookie.setMaxAge(30 * 24 * 60 * 60); // Số giây
passwordCookie.setMaxAge(30 * 24 * 60 * 60); // Số giây

response.addCookie(usernameCookie);
response.addCookie(passwordCookie);
            
            request.getSession().setAttribute("id", user.getId());
            request.getSession().setAttribute("role", user.getRole());
            if (user.getRole() == 1) {
                response.sendRedirect("home");
            } else if (user.getRole() == 0) {
                response.sendRedirect("adminDashboardController");
            }
            
        } else {
            String mess = "sai ten dang nhap hoac mat khau";
            request.setAttribute("error", mess);
            request.getRequestDispatcher("view/user/loginjsp.jsp").forward(request, response);
            
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
