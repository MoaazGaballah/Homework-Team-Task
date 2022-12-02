package com.moaaz_gaballah.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CSVHelper {
    public ByteArrayInputStream dataToCSV(
            List<?> dataList,
            char delimiter
//            ,Date dateTimeFormat
    ) {
//        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter(delimiter);
//        final String format = CSVFormat.DEFAULT.format(dateTimeFormat);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            if (dataList.size() > 0){
                // headers will be written just one time
                csvPrinter.printRecord(this.getHeaders(dataList.get(0)));
                for (Object objectClass : dataList) {
                    List<String> data = getRecordData(objectClass);
                    csvPrinter.printRecord(data);
                }
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getHeaders(Object objectRecord){
        List<Field> fieldList = Arrays.asList(objectRecord.getClass().getDeclaredFields());
        List<String> headersRow = new ArrayList<>();
        for (int i = 0; i < fieldList.size(); i++)
            headersRow.add(fieldList.get(i).getName());
        return headersRow;
    }

    private List<String> getRecordData(Object objectRecord) throws InstantiationException, IllegalAccessException {
        List<Field> fieldList = Arrays.asList(objectRecord.getClass().getDeclaredFields());
        List<String> data = new ArrayList<>();

        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            field.setAccessible(true);

            Object value = field.get(objectRecord);

            if (value != null)
                data.add(value.toString());
        }
        return data;
    }

//    private Boolean validateTimeStamp(Data timestamp){
//        Pattern p1 = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3})");
//        Pattern p2 = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3})");
//        Pattern p3 = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3})");
//        Pattern p4 = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3})");
//        Pattern p5 = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3})");
//        Matcher m = p1.matcher(timestamp.toString());
//        return m.find();
//    }
//
//    private Boolean validateTime(){
//
//    }
//
//    private Boolean validateDate(){
//
//    }
}
