package com.company.controller;

import com.company.users.Administrator;
import com.company.users.Client;
import com.company.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String nickName = request.getParameter("nickName");
        String password = request.getParameter("password");
        Authorizer auth = new Authorizer();

        User authorizedUser = auth.logIn(nickName, password, request);
        request.setAttribute("id", authorizedUser.getId());
        String path;
        if(authorizedUser instanceof Client){
            path = "/PAGES/client.jsp";
        }
        else if (authorizedUser instanceof Administrator){
            path = "/PAGES/admin.jsp";
        }
        else {
            path = "/PAGES/chef.jsp";
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
