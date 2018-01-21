package ru.otus.hw3.server.controller;

import ru.otus.hw3.server.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.otus.hw3.util.ConstantHolder.MVE_URL;

@WebServlet(MVE_URL)
public class MveServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.getWriter().print(EmployeeService.getInstance().getMVE());
    }
}
