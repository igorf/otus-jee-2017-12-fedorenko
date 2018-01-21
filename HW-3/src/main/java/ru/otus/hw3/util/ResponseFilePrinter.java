package ru.otus.hw3.util;

import lombok.Setter;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ResponseFilePrinter {
    @Setter private String contentType;
    @Setter private String contentDisposition;
    @Setter private HttpServletResponse response;
    @Setter private String errorMessage;

    public void print(File file) throws IOException {
        if (file != null) {
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", contentDisposition);
            response.setContentLength((int) file.length());
            IOUtils.copy(new FileInputStream(file), response.getOutputStream());
        } else {
            response.getWriter().print(errorMessage);
        }
    }
}
