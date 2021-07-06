package com.stylight.prettyurl.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stylight.prettyurl.db.InMemoryPrettyUrlMap;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class DbUtils {

    private static final String BASE_PATH = "static";
    private static final String FILE_NAME = "urldata_stub.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private InMemoryPrettyUrlMap inMemoryPrettyUrlMap;


    public void loadDataFromClassPath() throws IOException {
        TypeReference<Map<String, String>> typeReference =
                new TypeReference<Map<String, String>>() {
                };
        ClassPathResource classPathResource = new ClassPathResource(BASE_PATH + File.separator + FILE_NAME);
        Map<String, String> testData = objectMapper.readValue(classPathResource.getInputStream(), typeReference);
        inMemoryPrettyUrlMap.loadList(testData);
    }


}
