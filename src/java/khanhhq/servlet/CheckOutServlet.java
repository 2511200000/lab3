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
import khanhhq.tbllogin.TblCarsRentDetailsDAO;
import khanhhq.tbllogin.TblCustomerDAO;
import khanhhq.tbllogin.TblHistoryRentalDAO;
import khanhhq.tbllogin.TblRentalDAO;
import khanhhq.tbllogin.TblRentalDTO;
import khanhhq.tbllogin.TblVoucherDAO;

/**
 *
 * @author Administrator
 */
public class CheckOutServlet extends HttpServlet {

    private final String SEARCH = "search.jsp";
    private final String CHECK_OUT = "checkOut.jsp";

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

        String customerName = request.getParameter("txtCustomer");
        String address = request.getParameter("txtAddress");
        String phoneNumber = request.getParameter("txtPhonenumber");
        String rentalDate = request.getParameter("txtRentalDate");
        String returnDate = request.getParameter("txtReturnDate");
        String discount = request.getParameter("txtDiscount");

        String button = request.getParameter("btAction");
        float totalAllFloat = 0;
        float pricePromotion = 0;
        int count = 0;
        String url = SEARCH;
        try {
            HttpSession session = request.getSession();
            String[] carID = (String[]) session.getAttribute("carID");

            String[] quantity = (String[]) session.getAttribute("quantity");

            String[] price = (String[]) session.getAttribute("price");

            Float totalALl = (Float) session.getAttribute("totalAll");

            Float totalDiscount = (Float) session.getAttribute("totalAllDícount");

            String userID = (String) session.getAttribute("USERID");

            TblCustomerDAO daoCustomer = new TblCustomerDAO();
            int idCustomer = daoCustomer.printIdCustomer();
            if (idCustomer == 0) {
                idCustomer++;
                daoCustomer.createCustomer(idCustomer, customerName, address, phoneNumber, rentalDate, returnDate);
            } else {
                idCustomer++;
                daoCustomer.createCustomer(idCustomer, customerName, address, phoneNumber, rentalDate, returnDate);
            }

            TblRentalDAO daoRental = new TblRentalDAO();
            int idRentalID = daoRental.printIdOrder();
            if (idRentalID == 0) {
                idRentalID++;
            } else {
                idRentalID++;
            }

            if (totalDiscount != null) {
                daoRental.createOrder(idRentalID, idCustomer, rentalDate, returnDate, totalDiscount, discount);
            } else if (totalDiscount == null) {
                daoRental.createOrder(idRentalID, idCustomer, rentalDate, returnDate, totalALl, null);

            }
            TblCarsRentDetailsDAO daoRentalDetail = new TblCarsRentDetailsDAO();
            for (int i = 0; i < carID.length; i++) {
                daoRentalDetail.createOrderDetails(idRentalID, carID[i], Integer.parseInt(quantity[i]), Float.parseFloat(price[i]), Float.parseFloat(price[i]) * Float.parseFloat(quantity[i]));
            }

            TblVoucherDAO daoVoucher = new TblVoucherDAO();
            daoVoucher.updateVoucher("456", discount);

            TblHistoryRentalDAO daoHistory = new TblHistoryRentalDAO();
            int idHistory = daoRental.printIdOrder();
            if (idHistory == 0) {
                idHistory++;
            } else {
                idHistory++;
            }

            boolean ressult = daoHistory.createHistoryRental(idHistory, idRentalID, "123", button, userID);
                    System.out.println("ressult  " + ressult);
            System.out.println("khohohoho");
            daoRental.printOrder(idRentalID, idCustomer);
            List<TblRentalDTO> result = daoRental.getListRental();
            if (result != null) {
                session.setAttribute("DISPLAYORDER", result);
                url = CHECK_OUT;
            }
            session.removeAttribute("CUSTCART");
            session.removeAttribute("totalAllDícount");
            session.removeAttribute("discount");
            session.removeAttribute("totalDiscount");
            /* TODO output your page here. You may use following sample code. */
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
