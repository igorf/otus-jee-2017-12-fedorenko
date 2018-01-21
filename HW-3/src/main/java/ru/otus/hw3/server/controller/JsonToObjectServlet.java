package ru.otus.hw3.server.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.otus.hw3.db.model.Employee;
import ru.otus.hw3.server.services.JsonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.otus.hw3.util.ConstantHolder.*;

@WebServlet(JSON_TO_DATA_URL)
public class JsonToObjectServlet extends HttpServlet {
	private final static Logger logger = Logger.getLogger(JsonToObjectServlet.class.getName());

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
                FileItem file = (FileItem) upload.parseRequest(request).stream().findFirst().get();
                List<Employee> employeeList = JsonService.getInstance().unmarshal(file);
				request.setAttribute(EMPLOYEE_LIST_ATTRIBUTE,
                                    IntStream.range(0, employeeList.size())
                                    .filter(i -> i % 2 != 0)
                                    .mapToObj(i -> employeeList.get(i))
                                    .collect(Collectors.toList()));
				request.getRequestDispatcher(LIST_JSP).forward(request, response);
			} catch(Exception e) {
				logger.severe(e.getLocalizedMessage());
			}
		}
	}
}