package com.company.controller;

import com.company.users.Client;
import com.company.users.User;
import com.company.util.HibernateSessionFactoryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/back")
public class BackController extends HttpServlet {
    static Logger logger = LogManager.getLogger();
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        int id = Integer.parseInt(request.getParameter("id_cl_act"));
        Authorizer auth = new Authorizer();
        Client client = auth.findClientById(id);
        User authorizedUser = auth.logIn(client.getName(), client.getPassword(), request);

        String submitValue = request.getParameter("submit");
        if (submitValue.equals("Pay")) {
            session.createQuery("UPDATE Orders_DB SET paid ='true' WHERE paid = 'false'").executeUpdate();
            logger.info("Payment succeed");
        }

        request.setAttribute("id", authorizedUser.getId());
        request.getRequestDispatcher("/PAGES/client.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
