package ru.otus.hw3.server.controller;

import ru.otus.hw3.db.model.Employee;
import ru.otus.hw3.server.services.EmployeeService;
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

import static ru.otus.hw3.util.ConstantHolder.XML_URL;

@WebServlet(XML_URL)
public class XmlMarshallerServlet extends HttpServlet {
    private final static String CONTENT_TYPE = "test/xml";
    private final static String CONTENT_DISPOSITION = "filename=\"data.xml\"";
    private final static String ERROR_MESSAGE = "Data marshalling error";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Employee> employeeList = EmployeeService.getInstance().list();
        File data = XmlService.getInstance().marshall(employeeList);
        ResponseFilePrinter rfp = new ResponseFilePrinter();
        rfp.setResponse(response);
        rfp.setContentType(CONTENT_TYPE);
        rfp.setErrorMessage(ERROR_MESSAGE);
        rfp.setContentDisposition(CONTENT_DISPOSITION);
        rfp.print(data);
    }
}
