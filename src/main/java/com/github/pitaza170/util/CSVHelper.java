package com.github.pitaza170.util;

import com.github.pitaza170.common.Constants;
import com.github.pitaza170.dto.request.NewsRequest;
import com.github.pitaza170.model.News;
import org.apache.commons.csv.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {

    public static List<NewsRequest> csvToNews(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withHeader());) {

            List<NewsRequest> newsList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord rec : csvRecords) {
                NewsRequest news = new NewsRequest(
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
