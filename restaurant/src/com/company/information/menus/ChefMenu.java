package com.company.information.menus;

import com.company.information.PriceMenu;
import com.company.operations.Operations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class ChefMenu {
    static Logger logger = LogManager.getLogger();

    public void showChefMenu() {
        System.out.println("----------");
        System.out.println("Choose an option:");
        System.out.println("1. Show current menu");
        System.out.println("2. Add dish to menu");
        System.out.println("3. Show dishes by key word");
        System.out.println("4. Show dishes in particular price range");
        System.out.println("0. Exit");
        System.out.print(">> ");
    }

    public void makeOperations(int n) throws SQLException {
        Operations operation = new Operations();
        PriceMenu priceMenu = new PriceMenu();
        int id;
        switch (n) {
            case 1:
                priceMenu.showMenu();
                break;
            case 2:
                operation.addDishToPriceMenu();
                priceMenu.showMenu();
                logger.info("Dish was successfully added");
                break;
            case 3:
                priceMenu.showByKeyWord();
                break;
            case 4:
                priceMenu.showInPriceRange();
                break;
            case 0:
                logger.info("Changes saved");
                operation.closeConnection();
                break;
        }
    }
}
