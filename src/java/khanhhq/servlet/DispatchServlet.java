/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class DispatchServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String DATA = "PrintCarsServlet";
    private final String LOGIN = "LoginServlet";
    private final String LOGOUT = "LogOutServlet";
    private final String SEARCH_CARS = "SearchCarsServlet";
    private final String CREATE_ACCOUNT = "CreateAccountServlet";
    private final String VERTIFY_ACCOUNT = "VertifyCodeServlet";
    private final String ADD_CARS = "AddCarsServlet";
    private final String VIEW_CART = "viewCart.jsp";
    private final String REMOVE_CARS = "RemoveCarsServlet";
    private final String CONFIRM = "ConFirmServlet";
    private final String CHECK_OUT = "CheckOutServlet";
    private final String CHECK_VOUHER = "CheckVoucherServlet";
    private final String HISTORY = "PrintHistoryServlet";

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
        String url = LOGIN_PAGE;
        String button = request.getParameter("btAction");
        try {
            /* TODO output your page here. You may use following sample code. */
            if (button == null) {
                url = DATA;
            } else if (button.equals("Login")) {
                url = LOGIN;
            } else if (button.equals("LogOut")) {
                url = LOGOUT;
            } else if (button.equals("Search")) {
                url = SEARCH_CARS;
            } else if (button.equals("CreateNewAccount")) {
                url = CREATE_ACCOUNT;
            } else if (button.equals("vertify")) {
                url = VERTIFY_ACCOUNT;
            } else if (button.equals("AddCart")) {
                url = ADD_CARS;
            } else if (button.equals("ViewCart")) {
                url = VIEW_CART;
            } else if (button.equals("Confirm")) {
                url = CONFIRM;
            } else if (button.equals("RemoveSelectedCars")) {
                url = REMOVE_CARS;
            } else if (button.equals("CheckOut")) {
                url = CHECK_OUT;
            }else if(button.equals("Check Vourcher")){
                url = CHECK_VOUHER;
            }else if(button.equals("History")){
                url = HISTORY;
            }
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
