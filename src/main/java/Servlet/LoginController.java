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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

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
        response.setContentType("application/json;charset=UTF-8");
        String msg = "";
        String mail = request.getParameter("login");
        String st_id = request.getParameter("pass");
        String adminUser = getInitParameter("login");
        String adminPassword = getInitParameter("pass");
        String adminName = getInitParameter("userName");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = new JsonObject();
        if (mail.equals(adminUser) && st_id.equals(adminPassword)) {

            Student admin = new Student(0, "Admin", adminName,
                    mail, new Date(15, 1, 20), "info", "c", 1);
            //request.getSession().setAttribute("user", admin);
            msg = "Login successful Admin";
            jsonObject.add("student", gson.toJsonTree(admin));
            jsonObject.addProperty("msg", msg);
            try (PrintWriter out = response.getWriter()) {
                out.println(jsonObject);
            }
        } else {
            try {
                int id = 0;
                DAO dao = new DAO(new DataSource().getMySQLDataSource());
                try {
                    id = Integer.parseInt(st_id);
                } catch (NumberFormatException ex) {
                    throw new DAOException("Password must be numeric."+ex.getMessage());
                }

                Student student = dao.Login(mail, id);
                if (student != null) {
                    //request.getSession().setAttribute("user", student);
                    msg = "Login successful";
                    
                    jsonObject.add("student", gson.toJsonTree(student));
                    jsonObject.addProperty("msg", msg);
                    try (PrintWriter out = response.getWriter()) {
                        out.println(jsonObject);
                    }
                } else {
                    throw new DAOException("Your account or password is incorrect.");
                }

            } catch (DAOException | IllegalStateException ex) {
                msg = ex.getMessage();
                try (PrintWriter out = response.getWriter()) {
                    out.println(gson.toJson(msg));
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
