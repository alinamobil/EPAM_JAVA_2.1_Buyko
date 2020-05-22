package com.company;

import com.company.information.menus.MainMenu;
import com.company.reader.InfoReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        logger.info("Start");
        MainMenu mainMenu = new MainMenu();
        InfoReader reader = new InfoReader();
        int n = 1;
        while(n!=2){
            mainMenu.showMainMenu();

            n = reader.readInt(System.in);
            while (n!=1 && n!=2){
                System.out.println("Choose correct option!");
                mainMenu.showMainMenu();
                n = reader.readInt(System.in);
            }
            mainMenu.makeOperations(n);
        }
    }
}
