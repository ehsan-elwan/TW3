/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Models.DAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Models.DataSource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Ehsan
 */
@WebServlet(name = "Student", urlPatterns = {"/Student"})
public class GetStudents extends HttpServlet {

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
        String getParm = request.getParameter("set");
        DAO dao = new DAO(new DataSource().getMySQLDataSource());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        switch (getParm) {
            case "getStudent":

                try (PrintWriter out = response.getWriter()) {

                    out.println(gson.toJson(dao.getStudent()));
                }
                break;
            case "getEstablishment":
                try (PrintWriter out = response.getWriter()) {

                    out.println(gson.toJson(dao.getEstablishment()));
                }
                break;
            case "getStudentByEstablishment":
                try (PrintWriter out = response.getWriter()) {

                    out.println(gson.toJson(dao.getStudentByEstablishment(request.getParameter("est_name"))));
                }
                break;
            case "getEtablissementByFormation":
                try (PrintWriter out = response.getWriter()) {

                    out.println(gson.toJson(dao.getEtablissementByFormation(request.getParameter("form_name"))));
                }
                break;
            case "getStudentByFormation":
                try (PrintWriter out = response.getWriter()) {

                    out.println(gson.toJson(dao.getStudentByFormation(request.getParameter("form_name"))));
                }
                break;
            case "getEtablissementByCity":
                try (PrintWriter out = response.getWriter()) {

                    out.println(gson.toJson(dao.getEtablissementByCity(request.getParameter("city_name"))));
                }
                break;
            case "getCitiesFromEtablissement":
                try (PrintWriter out = response.getWriter()) {
                    out.println(gson.toJson(dao.getCitiesFromEtablissement()));
                }
                break;

            case "getFormationByCity":
                try (PrintWriter out = response.getWriter()) {
                    out.println(gson.toJson(dao.getFormationByCity(request.getParameter("city_name"))));
                }
                break;

            case "getCityByFormation":
                try (PrintWriter out = response.getWriter()) {
                    out.println(gson.toJson(dao.getCityByFormation(request.getParameter("form_lab"))));
                }
                break;

            case "getNBOfStudentByFormation":
                try (PrintWriter out = response.getWriter()) {
                    out.println(gson.toJson(dao.getNBOfStudentByFormation(request.getParameter("form_lab"))));
                }
                break;
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
