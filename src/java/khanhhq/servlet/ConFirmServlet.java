/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanhhq.cart.CartObject;
import khanhhq.tbllogin.TblCarDTO;

/**
 *
 * @author Administrator
 */
public class ConFirmServlet extends HttpServlet {

    private final String CONFIRM = "confirm.jsp";

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
        String[] carID = request.getParameterValues("txtItemID");

        String[] quantity = request.getParameterValues("quantity");
//        for (int i = 0; i < quantity.length; i++) {
//            System.out.println("hihih " + quantity[i]);
//        }

        String[] price = request.getParameterValues("price");

        String[] total = request.getParameterValues("total");

        String totalBill = request.getParameter("totalAll");
        float totalAll = Float.parseFloat(totalBill);
        String url = CONFIRM;
        try {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();

            session.setAttribute("carID", carID);
            session.setAttribute("quantity", quantity);
            session.setAttribute("price", price);
            session.setAttribute("total", total);
            session.setAttribute("totalAll", totalAll);
            
            url = CONFIRM;
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
