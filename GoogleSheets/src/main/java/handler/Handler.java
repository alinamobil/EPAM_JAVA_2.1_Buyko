package handler;

import operations.SheetsOperations;
import reader.InfoReader;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Handler {
    public void handleOptionChoice(int n) throws IOException, GeneralSecurityException {
        SheetsOperations sheetsOperations = new SheetsOperations();
        InfoReader infoReader = new InfoReader();
        switch (n) {
            case 1:
                String[] records = infoReader.readRecord(System.in);
                sheetsOperations.createGoogleSheets(records);
                System.out.println("Information is added");
                break;
            case 2:
                String range = infoReader.readRange(System.in);
                sheetsOperations.readGoogleSheets(range);
                System.out.println("Information is read");
                break;
            case 3:
                range = infoReader.readRange(System.in);
                records = infoReader.readRecord(System.in);
                sheetsOperations.updateGoogleSheets(range, records);
                System.out.println("Information is updated");
                break;
            case 4:
                System.out.println("Input start of range: ");
                int startRange = infoReader.readNumber(System.in);
                int endRange = 0;
                while(endRange <= startRange) {
                    System.out.println("Input end of range which must be greater than start of range:");
                    endRange = infoReader.readNumber(System.in);
                }
                sheetsOperations.deleteGoogleSheets(startRange, endRange);
                System.out.println("Information is deleted");
                break;
            case 5:
                break;
        }
    }
}
