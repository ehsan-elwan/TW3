/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Exceptions.DAOException;
import Models.DAO;
import Models.DataSource;
import Models.Student;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Ehsan
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

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
        String action = request.getParameter("act");
        int connected = (Integer) request.getSession().getServletContext().getAttribute("numberConnected");
        action = (action == null) ? "" : action;
        switch (action) {
            case "out":
                //request.getSession().invalidate();
                request.getSession().getServletContext().setAttribute("numberConnected", connected-1);
                response.sendRedirect(request.getContextPath() + "/Login.jsp");
                break;

            case "in":

                String msg = "";
                String mail = request.getParameter("mail");
                String st_id = request.getParameter("st_id");
                String adminUser = getInitParameter("login");
                String adminPassword = getInitParameter("pass");
                String adminName = getInitParameter("userName");           
                if (mail.equals(adminUser) && st_id.equals(adminPassword)) {

                    Student admin = new Student(0, "Admin", "Admin",
            "Contact@test.com", (java.sql.Date) new Date(), "info",  "c", 1);
                    request.getSession().setAttribute("user", admin);
                    //request.getSession().getServletContext().setAttribute("numberConnected", connected+1);
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                } else {
                    try {
                        int id = 0;
                        DAO dao = new DAO(new DataSource().getMySQLDataSource());
                        try {
                            id = Integer.parseInt(st_id);
                        } catch (NumberFormatException ex) {
                            throw new DAOException("Password must be numeric."+ex.getMessage());
                        }

                        Student customer = dao.Login(mail, id);
                        if (customer != null) {
                            request.getSession().setAttribute("user", customer);
                            request.getSession().getServletContext().setAttribute("numberConnected", connected+1);
                            response.sendRedirect(request.getContextPath() + "/customer.jsp");

                        } else {
                            throw new DAOException("Your account or password is incorrect.");
                        }

                    } catch (DAOException | IllegalStateException ex) {
                        msg = ex.getMessage();
                        request.setAttribute("message", msg);
                        request.getRequestDispatcher("errorView.jsp").forward(request, response);
                        //Logger.getLogger("LoginController").log(Level.SEVERE, "Action en erreur", ex);

                    }
                }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
