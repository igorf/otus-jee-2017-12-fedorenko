package ru.otus.hw3.server.controller;

import ru.otus.hw3.server.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.otus.hw3.util.ConstantHolder.LIST_URL;
import static ru.otus.hw3.util.ConstantHolder.REMOVE_URL;

@WebServlet(REMOVE_URL)
public class RemoveDataServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    Long id = Long.valueOf(request.getParameter("removeId"));
        EmployeeService.getInstance().remove(id);
        response.sendRedirect(LIST_URL);
    }
}
