package ru.otus.hw3.server.services;

import org.apache.commons.fileupload.FileItem;
import ru.otus.hw3.db.model.Employee;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static ru.otus.hw3.util.ConstantHolder.ENCODING;
import static ru.otus.hw3.util.ConstantHolder.JSON_FORMAT;

public class JsonService {
    private static JsonService instance = null;
    private static final Logger logger = Logger.getLogger(JsonService.class.getName());

    public static JsonService getInstance() {
        if (instance == null) {
            instance = new JsonService();
        }
        return instance;
    }

    private JsonService() {
        super();
    }

    public File marshall(List<Employee> employee) {
        try {
            File file = File.createTempFile(UUID.randomUUID().toString(), JSON_FORMAT);
            JsonbConfig config = new JsonbConfig().withEncoding(ENCODING).withFormatting(true);
            Jsonb jsonb = JsonbBuilder.create(config);
            String result = jsonb.toJson(employee);
            Files.write(file.toPath(), result.getBytes());
            return file;
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
        }
        return null;
    }

    public List<Employee> unmarshal(FileItem fileItem) {
        try {
            JsonbConfig config = new JsonbConfig().withEncoding(ENCODING).withFormatting(true);
            Jsonb jsonb = JsonbBuilder.create(config);
            try (BufferedReader buffer = new BufferedReader(
                    new InputStreamReader(fileItem.getInputStream(), StandardCharsets.UTF_8))) {
                String content = buffer.lines().collect(Collectors.joining( "\n" ));
                Employee[] result = jsonb.fromJson(content, Employee[].class);
                return Arrays.asList(result);
            }
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
        }
        return null;
    }
}