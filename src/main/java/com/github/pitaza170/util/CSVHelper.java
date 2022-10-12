package com.github.pitaza170.util;

import com.github.pitaza170.common.Constants;
import com.github.pitaza170.model.News;
import org.apache.commons.csv.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {

    public static List<News> csvToNews(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withHeader());) {

            List<News> newsList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord rec : csvRecords) {
                News news = new News(
                        Long.parseLong(rec.get("id")),
                        rec.get("links"),
                        rec.get("title"),
                        rec.get("text"),
                        rec.get("role")
                );
                newsList.add(news);
            }

            return newsList;
        } catch (IOException e) {
            throw new RuntimeException(Constants.FAILED_TO_PARSE + e.getMessage());
        }
    }

}
