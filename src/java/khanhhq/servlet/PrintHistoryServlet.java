/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanhhq.tbllogin.TblHistoryRentalDAO;
import khanhhq.tbllogin.TblHistoryRentalDTO;
import khanhhq.tbllogin.TblRentalDAO;
import khanhhq.tbllogin.TblRentalDTO;

/**
 *
 * @author Administrator
 */
public class PrintHistoryServlet extends HttpServlet {

    private final String HISTORY = "history.jsp";
    private final String DATA = "search.jsp";

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
        PrintWriter out = response.getWriter();
        String url = DATA;
        try {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            TblRentalDAO daoRental = new TblRentalDAO();
            daoRental.printHistoryRental();
            List<TblRentalDTO> rental = daoRental.getListRentalHistory();
            if (rental != null) {
                session.setAttribute("RENTAL", rental);
            }

            TblHistoryRentalDAO dao = new TblHistoryRentalDAO();
            dao.printHistoryRental();
            List<TblHistoryRentalDTO> history = dao.getListHistoryRental();
            if (history != null) {
                session.setAttribute("HISTORY", history);
                url = HISTORY;
            }

        } catch (SQLException e) {

        } catch (NamingException e) {

        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
