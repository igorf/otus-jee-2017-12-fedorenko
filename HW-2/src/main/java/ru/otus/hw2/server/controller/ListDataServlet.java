package ru.otus.hw2.server.controller;

import ru.otus.hw2.db.model.Employee;
import ru.otus.hw2.server.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.otus.hw2.util.ConstantHolder.EMPLOYEE_LIST_ATTRIBUTE;
import static ru.otus.hw2.util.ConstantHolder.LIST_JSP;
import static ru.otus.hw2.util.ConstantHolder.LIST_URL;

@WebServlet(LIST_URL)
public class ListDataServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Employee> employeeList = EmployeeService.getInstance().list();
        request.setAttribute(EMPLOYEE_LIST_ATTRIBUTE, employeeList);
        request.getRequestDispatcher(LIST_JSP).forward(request, response);
    }
}
