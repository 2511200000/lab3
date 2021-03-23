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
import khanhhq.tbllogin.TblLoginDTO;
import khanhhq.vertify.SendMail;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class CreateAccountServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(CreateAccountServlet.class.getName());

    private final String FAIL = "createAccount.jsp";
    private final String SUCCESS = "vertify.jsp";

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
        String txtEmail = request.getParameter("txtEmail");
        String fullName = request.getParameter("txtName");
        String password = request.getParameter("txtPassword");
        String conFirm = request.getParameter("txtComfirm");
        String url = FAIL;
        try {

            /* TODO output your page here. You may use following sample code. */
            if (txtEmail.isEmpty() || password.isEmpty() || conFirm.isEmpty() || fullName.isEmpty()) {
                String msg = "not empty";
                request.setAttribute("CREATERR", msg);
                url = FAIL;
            } else if (!password.equals(conFirm)) {
                String msg = "not match";
                request.setAttribute("CREATERR", msg);
                url = FAIL;
            } else {
                SendMail sm = new SendMail();

                String code = sm.getCodeRandom();
                TblLoginDAO daoLogin = new TblLoginDAO();

                daoLogin.createAccount(txtEmail, fullName, password, "123", "123", code);

                TblLoginDTO user = daoLogin.findEmailByCode(code);
                boolean test = sm.sendVerify(user);
                if (test) {
                    url = SUCCESS;
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                } else {
                    url = FAIL;
                    String msg = "Can not send code to mail";
                    request.setAttribute("CREATERR", msg);
                }
            }

        } catch (SQLException e) {
            String errMSG = e.getMessage();
            log("CreateAccountServlet_SQL " + errMSG);
            if (errMSG.contains("duplicate")) {
                String msg = "Email is exist";
                request.setAttribute("CREATERR", msg);
            }
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
