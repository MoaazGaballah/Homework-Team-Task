package com.moaaz_gaballah.services;

import com.moaaz_gaballah.models.internal.data.Delimiter;
import com.moaaz_gaballah.models.internal.data.export.ExportDataModel;
import com.moaaz_gaballah.repositories.AbstractRepository;
import com.moaaz_gaballah.repositories.CableRepository;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.context.WebApplicationContext;

@Service
@RequiredArgsConstructor
public class DataServise {
    private final WebApplicationContext appContext;
    private final CSVHelper csvHelper;

    public ResponseEntity export(ExportDataModel exportDataModel) throws ClassNotFoundException, IllegalAccessException {
        switch (exportDataModel.getFileType().getName()) {
            case "CSV": {
                String filename = "data.csv";
                InputStreamResource file = new InputStreamResource(this.exportCSV(exportDataModel));
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                        .contentType(MediaType.parseMediaType("application/csv"))
                        .body(file);
            }
            case "EXCEL": {

            }
            case "INSERT_SCRIPT": {

            }
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // export Excel

    // export PostgreSQL script

    private void exportCSVWithOpenCSV(ExportDataModel exportDataModel) throws IOException {

        String filename = "data.csv";
        Writer writer = Files.newBufferedWriter(Paths.get(filename));
        CSVWriter csvWriter = new CSVWriter(writer,
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
    }

    private ByteArrayInputStream exportCSV(ExportDataModel exportDataModel) throws ClassNotFoundException, IllegalAccessException {


        Class<?> dataModel = Class.forName(exportDataModel.getJavaClass());
        // dataModel + "Repository"
        Repositories repositories = new Repositories(appContext);
        AbstractRepository repo = (AbstractRepository) repositories.getRepositoryFor(dataModel).orElse(null);
        List<?> dataList = new ArrayList<>();

        if (repo != null) {
//        List<?> dataList = (List<?>) repo.findAll();
//            dataList = repo.getLimitedRecords(
//                    exportDataModel.getIdRange().getStart(),
//                    exportDataModel.getIdRange().getEnd()
//            );
        }

        ByteArrayInputStream in = this.csvHelper.dataToCSV(dataList,
                this.getDelimiter(exportDataModel.getDelimiter()));

        return in;
    }

    private char getDelimiter(Delimiter delimiter) {
        switch (delimiter.getName()) {
            case "COMMA":
                return ',';
            case "SEMMICOMMA":
                return ';';
            case "PIPE":
                return '|';
        }
        return ';';
    }

    private Long getIdValue(Object objectRecord) throws IllegalAccessException {
        List<Field> fieldList = Arrays.asList(objectRecord.getClass().getDeclaredFields());

        Field idField = fieldList.stream().filter(field -> field.getName().equals("id")).findAny().orElse(null);

        if (idField != null) {
            idField.setAccessible(true);
            Object idValue = idField.get(objectRecord);
            return Long.valueOf(idValue.toString());
        }
        return null;
    }

    private List<?> filterById(Long start, Long end, List<?> dataList) throws IllegalAccessException {
        List<Object> filterdData = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            if (this.getIdValue(dataList.get(i)) >= start && this.getIdValue(dataList.get(i)) < end)
                filterdData.add(dataList.get(i));
        }
        return filterdData;
    }
}
