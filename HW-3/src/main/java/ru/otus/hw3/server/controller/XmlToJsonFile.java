package ru.otus.hw3.server.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.otus.hw3.db.model.Employee;
import ru.otus.hw3.server.services.JsonService;
import ru.otus.hw3.server.services.XmlService;
import ru.otus.hw3.util.ResponseFilePrinter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import static ru.otus.hw3.util.ConstantHolder.XML_TO_JSON_FILE_URL;

@WebServlet(XML_TO_JSON_FILE_URL)
public class XmlToJsonFile extends HttpServlet {
	private final static Logger logger = Logger.getLogger(XmlToJsonFile.class.getName());
    private final static String CONTENT_TYPE = "application/json";
    private final static String CONTENT_DISPOSITION = "filename=\"data.json\"";
    private final static String ERROR_MESSAGE = "Data marshalling error";

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				FileItem file = (FileItem) upload.parseRequest(request).stream().findFirst().get();
                List<Employee> employees = XmlService.getInstance().unmarshal(file);
                File jsonFile = JsonService.getInstance().marshall(employees);
                ResponseFilePrinter rfp = new ResponseFilePrinter();
                rfp.setResponse(response);
                rfp.setContentType(CONTENT_TYPE);
                rfp.setErrorMessage(ERROR_MESSAGE);
                rfp.setContentDisposition(CONTENT_DISPOSITION);
                rfp.print(jsonFile);
			} catch(Exception e) {
				logger.severe(e.getLocalizedMessage());
			}
		}
	}
}
