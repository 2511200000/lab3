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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanhhq.tbllogin.TblLoginDAO;
import khanhhq.tbllogin.TblRoleDAO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class LoginServlet extends HttpServlet {
        private final Logger log = Logger.getLogger(LoginServlet.class.getName());

    private final String INVALID = "login.jsp";
    private final String DATA_USER = "PrintCarsServlet";
    private final String DATA_ADMIN = "invalid.html";

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
        String userID = request.getParameter("txtUsername");
        String Password = request.getParameter("txtPassword");
        String recaptch = request.getParameter("g-recaptcha-response");
        String url = INVALID;
        try {
            /* TODO output your page here. You may use following sample code. */
            TblLoginDAO daoLogin = new TblLoginDAO();

            TblRoleDAO daoRole = new TblRoleDAO();

            HttpSession sesstion = request.getSession();

            String fulname = daoLogin.checklogin(userID, Password);
 
            if (userID.isEmpty() || Password.isEmpty()) {
                String msg = "user or password error";
                request.setAttribute("ERRORLGOIN", msg);
            } else if (recaptch.isEmpty()) {
                String msg = "You must vertify reCAPTCHA";
                request.setAttribute("ERRORLGOIN", msg);
            } else {
                if (!fulname.isEmpty()) {
                    String roleID = daoLogin.getRoleID(userID);
                    String rolename = daoRole.getRoleName(roleID);
                    if (rolename.equals("user")) {
                        sesstion.setAttribute("FULLNAMEUSER", fulname);
                        sesstion.setAttribute("USERID", userID);
                        url = DATA_USER;
                    } else {
                        sesstion.setAttribute("FULLNAMEADMIN", fulname);
                        url = DATA_ADMIN;
                    }
                } else {
                    String msg = "status deActive or user or password error";
                    request.setAttribute("ERRORLGOIN", msg);
                }
            }

        } catch (SQLException e) {
            BasicConfigurator.configure();
            log.error("SQLException");
        } catch (NamingException e) {
             BasicConfigurator.configure();
            log.error("NamingException");
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
