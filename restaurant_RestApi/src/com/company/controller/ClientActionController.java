package com.company.controller;

import com.company.information.PriceMenu;
import com.company.operations.Operations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/client_action")
public class ClientActionController extends HttpServlet {
    Operations op = new Operations();
    PriceMenu priceMenu = new PriceMenu();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String action = request.getParameter("action_cl");

        int id = Integer.parseInt(request.getParameter("id_cl"));
        request.setAttribute("id_act", id);
        String actionResult = "";
        switch (action) {
            case "create":
                if(op.findUnpaidOrder() != 0) {
                    actionResult = "<h3>Pay for your previous order first!</h3>" + op.payForOrder();
                    request.setAttribute("submit", "Pay");
                }
                else {
                    actionResult = priceMenu.showMenu();
                    actionResult += "<form action=\"create\" method=\"post\" autocomplete=\"off\">\n" +
                                        "<div class=\"form-group\">\n" +
                                            "<label for=\"exampleInputName1\">Input new order by template (dishId:amount). Dishes must be separated by spaces</label>\n" +
                                            "<input type=\"text\" class=\"form-control\" id=\"exampleInputName1\" placeholder=\"Enter your order\" name=\"order\" pattern=\"(\\d+:\\d+\\s?)+\">\n" +
                                        "</div>\n" +
                                            "<input type=\"submit\" class=\"btn btn-primary\" value=\"Create\">\n" +
                                            "<input type=\"hidden\" name=\"id_cl_create\" value=\"" + id + "\">" +
                                    "</form>";
                    request.setAttribute("submit", "Back");
                }
                break;
            case "show":
                actionResult = "<h3>Your orders</h3>" +
                                "<table class=\"table-view\">" +
                                    "<thead>\n" +
                                    "<tr style=\"background: lightgray;\">\n" +
                                        "<td><b>id</b></td>\n" +
                                        "<td><b>dishes</b></td>\n" +
                                        "<td><b>total</b></td>\n" +
                                        "<td><b>paid</b></td>\n" +
                                    "</tr>\n" +
                                    "</thead>" +
                                    op.showClientsOrders(id) +
                                "</table>";
                request.setAttribute("submit", "Back");
                break;
            case "add":
                if(op.findUnpaidOrder() != 0) {
                    actionResult = priceMenu.showMenu();
                    actionResult += "<form action=\"update\" method=\"post\" autocomplete=\"off\">\n" +
                            "<div class=\"form-group\">\n" +
                            "<label for=\"exampleInputName1\">Input dishes by template (dishId:amount) to update your order. Dishes must be separated by spaces</label>\n" +
                            "<input type=\"text\" class=\"form-control\" id=\"exampleInputName1\" placeholder=\"Enter your order\" name=\"order\" pattern=\"(\\d+:\\d+\\s?)+\">\n" +
                            "</div>\n" +
                            "<input type=\"submit\" class=\"btn btn-primary\" value=\"Add\">\n" +
                            "<input type=\"hidden\" name=\"id_cl_update\" value=\"" + id + "\">" +
                            "</form>";
                    request.setAttribute("submit", "Back");
                } else {
                    actionResult = "<p>You have no orders to update. Create a new one</p>";
                    request.setAttribute("submit", "Back");
                }
                break;
            case "pay":
                if(op.findUnpaidOrder() != 0) {
                    actionResult = op.payForOrder();
                    request.setAttribute("submit", "Pay");
                } else {
                    actionResult = "<p>You have no orders to pay for</p>";
                    request.setAttribute("submit", "Back");
                }
                break;
        }
        request.setAttribute("action", actionResult);
        request.getRequestDispatcher("/PAGES/client_action.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
