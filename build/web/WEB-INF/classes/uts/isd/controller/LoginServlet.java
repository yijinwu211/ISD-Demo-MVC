/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Student;
import uts.isd.model.dao.DBManager;

/**
 *
 * @author george
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        String ID = request.getParameter("ID");
        String password = request.getParameter("password");
        DBManager manager = (DBManager) session.getAttribute("manager");
        Student student = null;
        try {
            student = manager.findStudent(ID, password);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!validator.validateID(ID)) {
            session.setAttribute("IDErr", "Incorrect email format");
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            session.setAttribute("passErr", "Incorrect password format");
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else if (student != null) {
            session.setAttribute("studentLogin", student);
            request.getRequestDispatcher("loginWelcome.jsp").include(request, response);
        } else {
            session.setAttribute("existErr", "User does not exist!");
            request.getRequestDispatcher("main.jsp").include(request, response);
        }
    }

}


