package ru.otus.hw3.server.controller;

import ru.otus.hw3.db.model.Employee;
import ru.otus.hw3.server.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.otus.hw3.util.ConstantHolder.*;

@WebServlet(LIST_URL)
public class ListDataServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Employee> employeeList = EmployeeService.getInstance().list();
        request.setAttribute(EMPLOYEE_LIST_ATTRIBUTE, employeeList);
        request.getRequestDispatcher(LIST_JSP).forward(request, response);
    }
}
