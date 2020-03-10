package com.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

@WebServlet(urlPatterns = "/controller")
public class Main extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logger logger = LogManager.getLogger();
        logger.log(Level.INFO, "Reading numbers");
        String numberStr1 = request.getParameter("num1");
        String numberStr2 = request.getParameter("num2");
        logger.log(Level.INFO, "Converting strings to number");
        int number1 = Integer.parseInt(numberStr1);
        int number2 = Integer.parseInt(numberStr2);
        int result = number1 + number2;
        request.setAttribute("res", result);
        request.getRequestDispatcher("pages/main.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
