package ru.otus.hw3.server.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.otus.hw3.server.services.ImportService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static ru.otus.hw3.util.ConstantHolder.IMPORT_URL;
import static ru.otus.hw3.util.ConstantHolder.LIST_URL;

@WebServlet(IMPORT_URL)
public class ImportDataServlet extends HttpServlet {
	private final static Logger logger = Logger.getLogger(ImportDataServlet.class.getName());

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				FileItem file = (FileItem) upload.parseRequest(request).stream().findFirst().get();
				List<String> content;
				try (BufferedReader buffer = new BufferedReader(
						new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
					content = buffer.lines().collect(Collectors.toList());
                    ImportService.getInstance().importData(content);
                    response.sendRedirect(LIST_URL);
				}
			} catch(Exception e) {
				logger.severe(e.getLocalizedMessage());
			}
		}
	}
}
