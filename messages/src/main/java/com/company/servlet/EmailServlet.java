package com.company.servlet;//import com.company.utility.EmailUtility;

import com.company.utility.EmailUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/emailServlet")
public class EmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String email = request.getParameter("email");
            String subject = request.getParameter("subject");
            String message = request.getParameter("message");

            String resultMessage = "";

            try {
                EmailUtility.sendEmail(email, subject,
                        message);
                resultMessage = "The e-mail was sent successfully";
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = "There were an error: " + ex.getMessage();
            } finally {
                request.setAttribute("res", resultMessage);
                request.getRequestDispatcher("pages/main.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
