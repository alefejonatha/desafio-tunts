package com.alefebarboza.desafiotunts.googlespreadsheet.service;

import com.alefebarboza.desafiotunts.googlespreadsheet.config.GoogleAuthorizationConfig;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleSheetsService {

    private final GoogleAuthorizationConfig googleAuthorizationConfig;

    public List<List<Object>> getSpreadsheetValues(String range, String spreadsheetId) throws GeneralSecurityException, IOException {
        log.info("reading spreadsheet");

        Sheets service = googleAuthorizationConfig.getSheetsService();

        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();

        List<List<Object>> values = response.getValues();

        if (values == null || values.isEmpty()) {
            throw new IOException("No data found.");
        }
        return values;
    }

    public void setSpreadsheetValues(String spreadsheetId, String valueInputOption, List<ValueRange> data) throws GeneralSecurityException, IOException {
        log.info("writing values");

        Sheets service = googleAuthorizationConfig.getSheetsService();

        BatchUpdateValuesRequest batchBody = new BatchUpdateValuesRequest()
                .setValueInputOption(valueInputOption)
                .setData(data);

        service.spreadsheets().values()
                .batchUpdate(spreadsheetId, batchBody)
                .execute();

    }


}
