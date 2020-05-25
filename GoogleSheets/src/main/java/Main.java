import handler.Handler;
import menu.Menu;
import operations.SheetsOperations;
import reader.InfoReader;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        final int EXIT = 5;
        Menu menu = new Menu();
        InfoReader infoReader = new InfoReader();
        Handler handler = new Handler();
        int n = 1;
        while(n != EXIT){
            menu.showMenu();
            n = infoReader.readInt(System.in);
            handler.handleOptionChoice(n);
        }
    }
}
