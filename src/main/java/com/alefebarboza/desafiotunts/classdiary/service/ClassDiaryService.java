package com.alefebarboza.desafiotunts.classdiary.service;

import com.alefebarboza.desafiotunts.classdiary.utils.ClassDiaryUtils;
import com.alefebarboza.desafiotunts.googlespreadsheet.service.GoogleSheetsService;
import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassDiaryService {

    @Autowired
    @Value("${google.spreadsheet.id}")
    private String spreadsheetId;
    private final GoogleSheetsService googleSheetsService;
    private final ClassDiaryUtils classDiaryUtils;

    public void readAndWrite() throws GeneralSecurityException, IOException {
        String range = "engenharia_de_software!A4:H";
        List<List<Object>> values = googleSheetsService.getSpreadsheetValues(range, spreadsheetId);

        List<ValueRange> data = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {
            List<String> media = classDiaryUtils.getStatus(
                    Float.parseFloat(values.get(i).get(3).toString()),
                    Float.parseFloat(values.get(i).get(4).toString()),
                    Float.parseFloat(values.get(i).get(5).toString()),
                    Integer.parseInt(values.get(i).get(2).toString()));

            data.add(new ValueRange()
                    .setRange(String.format("G%s", i + 4))
                    .setValues(List.of(
                            Arrays.asList(media.get(0), media.get(1)))));
        }
        googleSheetsService.setSpreadsheetValues(spreadsheetId, "RAW", data);
    }
}
