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
import khanhhq.tbllogin.TblCarDAO;
import khanhhq.tbllogin.TblCarDTO;

/**
 *
 * @author Administrator
 */
public class SearchCarsServlet extends HttpServlet {

    private final String SEARCH = "searchCars.jsp";

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
        String txtSearchValue = request.getParameter("txtSearchValue");
        String categoryID = request.getParameter("cboCategory");
        String amountOfCar = request.getParameter("cboAmountPrice");

        String indexString = request.getParameter("txtIndex");
        float priceFloat = Float.parseFloat(amountOfCar);
        String url = SEARCH;
        try {
            TblCarDAO daoCar = new TblCarDAO();
            int count = daoCar.countAllCarsToSearch(txtSearchValue, priceFloat, categoryID);
            if (indexString == null) {
                indexString = "1";
            }
            int index = Integer.parseInt(indexString);
            int pageSize = 3;
            int endPage = 0;
            endPage = count / pageSize;
            if (count % pageSize != 0) {
                endPage++;
            }
            /* TODO output your page here. You may use following sample code. */
            if (!txtSearchValue.isEmpty() || !categoryID.isEmpty() || !amountOfCar.isEmpty()) {
                daoCar.searchCars(txtSearchValue, priceFloat, categoryID, index);
                List<TblCarDTO> result = daoCar.getSearchCarList();
                if (result != null) {
                    request.setAttribute("SEARCHNAME", result);
                    url = SEARCH;
                }
            }
            request.setAttribute("ENDPAGE", endPage);
            request.setAttribute("SEARCHVALUE", txtSearchValue);
            request.setAttribute("INDEX", index);
            request.setAttribute("PRICE", amountOfCar);
            request.setAttribute("categoryID", categoryID);
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
