package controller;

import DAO.ShowDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Show;

/**
 *
 * @author Admin
 */
public class chooseShowController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet chooseShowController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet chooseShowController at " + request.getContextPath() + "</h1>");
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
        int movieId = Integer.valueOf(request.getParameter("filmId"));
        LocalDate now = LocalDate.now();// lay ngay hien tai
        Date dateNow = Date.valueOf(now); // ngay hom nay
        List<Date> dateL = new ArrayList<>();
        ShowDao db = new ShowDao();
        for (int i = 0; i < 7; i++) {
            Date sqlDate = Date.valueOf(now); // convert now về date sql
            List<Show> show_test = db.getShow(movieId, sqlDate); // kiẻm tra ngày đó có show nào không
            if(!(show_test.isEmpty())){dateL.add(sqlDate);} // có show thì mới add vào list date
            
            now = now.plusDays(1); // cong now len 1 ngay
        }
      
       

        List<Show> show = db.getShow(movieId, dateNow); // tim show hom nay va 7 ngay tiep theo

        if (show.isEmpty()) {
            if (dateL.isEmpty()) {
                request.getRequestDispatcher("film?film="+movieId).forward(request, response);
            }else{ 
                show = db.getShow(movieId, dateL.get(0));
               
            }
            
           
        } 
        if(!(show.isEmpty())) {
            request.setAttribute("filmId", movieId);
            request.setAttribute("date", dateL); // list date show
            request.setAttribute("show", show);
            request.getRequestDispatcher("view/user/chooseShowMovie.jsp").forward(request, response);
        }

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
        int movieId = Integer.valueOf(request.getParameter("filmId"));
        Date d = Date.valueOf(request.getParameter("date"));
        ShowDao db = new ShowDao();

        LocalDate now = LocalDate.now();
//        Date dateNow = Date.valueOf(now);
        List<Date> dateL = new ArrayList<>();
         for (int i = 0; i < 7; i++) {
            Date sqlDate = Date.valueOf(now); // convert now về date sql
            List<Show> show_test = db.getShow(movieId, sqlDate); // kiẻm tra ngày đó có show nào không
            if(!(show_test.isEmpty())){dateL.add(sqlDate);} // có show thì mới add vào list date
            
            now = now.plusDays(1); // cong now len 1 ngay
        }
        List<Show> show = db.getShow(movieId, d);
        
            request.setAttribute("filmId", movieId);
            request.setAttribute("date", dateL); // list date show
            request.setAttribute("show", show);
            request.getRequestDispatcher("view/user/chooseShowMovie.jsp").forward(request, response);
        
        

        
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
