/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanhhq.cart.CartObject;
import khanhhq.tbllogin.TblCarDAO;
import khanhhq.tbllogin.TblCarDTO;

/**
 *
 * @author Administrator
 */
public class AddCarsServlet extends HttpServlet {

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
        String urlRewriting = "PrintCarsServlet";
        String [] quantity;
        try {
            /* TODO output your page here. You may use following sample code. */
            TblCarDAO daoCar = new TblCarDAO();
            HttpSession session = request.getSession();
            String fullname = (String) session.getAttribute("FULLNAMEUSER");
            if (fullname != null) {
                String item = request.getParameter("txtItemID");
                TblCarDTO dto = daoCar.findItemByID(item);
                 CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                if (cart == null) {
                    cart = new CartObject();

                }
                cart.addtemToCart(dto);
             
                 session.setAttribute("CUSTCART", cart);
            } else {
                urlRewriting = "login.jsp";
            }
        } catch (SQLException e) {

        } catch (NamingException e) {

        } finally {
            response.sendRedirect(urlRewriting);
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
