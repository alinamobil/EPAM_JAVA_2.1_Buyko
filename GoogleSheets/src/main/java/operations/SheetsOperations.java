package operations;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SheetsOperations {
    private static Sheets sheetsService;
    private static String APPLICATION_NAME = "Google Sheets";
    private static String SPREADSHEET_ID = "16v7agWn9T8ZdIExEdYGQncBCUTLSLamVMjrT9CiWRaA";

    private static Credential authorize() throws IOException, GeneralSecurityException {
        InputStream inputStream = SheetsOperations.class.getResourceAsStream("/credentials.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), new InputStreamReader(inputStream)
        );
        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver())
                .authorize("user");
        return credential;
    }

    public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        Credential credential = authorize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static void readGoogleSheets(String range) throws IOException, GeneralSecurityException {
        sheetsService = getSheetsService();
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID, range)
                .execute();
        List<List<Object>>values = response.getValues();

        if(values == null || values.isEmpty()) {
            System.out.println("No data found");
        } else {
            for(List row : values) {
                System.out.printf("%s %s %s\n", row.get(0), row.get(1), row.get(2));
            }
        }
    }

    public static void createGoogleSheets(String[] newRecords) throws IOException, GeneralSecurityException {
        sheetsService = getSheetsService();
        ValueRange appendBody = new ValueRange()
                .setValues(Arrays.asList(
                        Arrays.asList((Object[])newRecords)
                ));
        AppendValuesResponse appendResult = sheetsService.spreadsheets().values()
                .append(SPREADSHEET_ID, "We", appendBody)
                .setValueInputOption("USER_ENTERED")
                .setInsertDataOption("INSERT_ROWS")
                .setIncludeValuesInResponse(true)
                .execute();
    }

    public static void updateGoogleSheets(String range, String[] records) throws IOException, GeneralSecurityException {
        sheetsService = getSheetsService();
        ValueRange body = new ValueRange()
                .setValues(Arrays.asList(
                        Arrays.asList((Object[])records)
                ));
        UpdateValuesResponse result = sheetsService.spreadsheets().values()
                .update(SPREADSHEET_ID, range, body)
                .setValueInputOption("RAW")
                .execute();
    }

    public static void deleteGoogleSheets(int startRange, int endRange) throws IOException, GeneralSecurityException {
        sheetsService = getSheetsService();
        DeleteDimensionRequest deleteDimensionRequest = new DeleteDimensionRequest()
                .setRange(
                        new DimensionRange()
                                .setSheetId(0)
                                .setDimension("ROWS")
                                .setStartIndex(startRange)
                                .setEndIndex(endRange)
                );
        List<Request> requests = new ArrayList<>();
        requests.add(new Request().setDeleteDimension(deleteDimensionRequest));
        BatchUpdateSpreadsheetRequest body = new BatchUpdateSpreadsheetRequest().setRequests(requests);
        sheetsService.spreadsheets().batchUpdate(SPREADSHEET_ID, body).execute();
    }
}
