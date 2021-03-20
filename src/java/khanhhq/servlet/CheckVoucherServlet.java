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
import khanhhq.tbllogin.TblVoucherDAO;
import khanhhq.tbllogin.TblVouchersDTO;

/**
 *
 * @author Administrator
 */
public class CheckVoucherServlet extends HttpServlet {

    private String CONFIRM = "confirm.jsp";

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
        String txtVoucher = request.getParameter("txtDiscount");
        System.out.println("txtVoucher " + txtVoucher);
        String url = CONFIRM;
        try {
            /* TODO output your page here. You may use following sample code. */
            TblVoucherDAO dao = new TblVoucherDAO();
            HttpSession sessoion = request.getSession();
            float totalAll = (float) sessoion.getAttribute("totalAll");

            List<TblVouchersDTO> result = dao.getVoucher();

            float totalDiscount = 0;
            int discount = 0;
            for (TblVouchersDTO voucher : result) {
                if (voucher.getVoucherID().equals(txtVoucher)) {
                    if (voucher.getStatus().equals("456")) {
                        String msg = "Voucher has expired";
                        request.setAttribute("ERRDISCOUNT", msg);
                    } else {
                        discount = voucher.getSale();
                        totalDiscount = totalDiscount + totalAll * voucher.getSale() / 100;
                        totalAll = totalAll - totalDiscount;
                    }
                }
            }
            url = CONFIRM;
            sessoion.setAttribute("totalAllDícount", totalAll);
            sessoion.setAttribute("discount", discount);
            sessoion.setAttribute("totalDiscount", totalDiscount);
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
