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
import khanhhq.tbllogin.TblCarDAO;
import khanhhq.tbllogin.TblCarDTO;
import khanhhq.tbllogin.TblCategoryDAO;
import khanhhq.tbllogin.TblCategoryDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class PrintCarsServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(PrintCarsServlet.class.getName());

    private String DATA = "search.jsp";

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
        String txtIndex = request.getParameter("txtIndex");
        String url = DATA;
        boolean status = true;
        try {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();

            TblCarDAO daoCar = new TblCarDAO();

            TblCategoryDAO daoCategory = new TblCategoryDAO();

            int count = daoCar.countAllCars(status);
            if (txtIndex == null) {
                txtIndex = "1";
            }
            int index = Integer.parseInt(txtIndex);
            int pageSize = 5;
            int endPage = 0;
            endPage = count / pageSize;
            if (count % pageSize != 0) {
                endPage++;
            }

            List<TblCategoryDTO> cate = daoCategory.getAllCategory();
            if (cate != null) {
                url = DATA;
                session.setAttribute("CBOCATEGORY", cate);
            }

            List<TblCarDTO> price = daoCar.getAllPrice();
            if (price != null) {
                url = DATA;
                session.setAttribute("ALLPRICE", price);
            }

            daoCar.printData(status, index);
            List<TblCarDTO> result = daoCar.getDataList();
            if (result != null) {
                url = DATA;
                session.setAttribute("DISPLAYCAR", result);
            }
            request.setAttribute("ENDPAGE", endPage);
            request.setAttribute("INDEX", index);
            request.setAttribute("STATUS", status);
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
