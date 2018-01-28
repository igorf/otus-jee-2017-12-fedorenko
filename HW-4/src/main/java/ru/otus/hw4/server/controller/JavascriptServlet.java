package ru.otus.hw4.server.controller;

import ru.otus.hw4.server.services.JavascriptService;

import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.otus.hw4.util.ConstantHolder.JS_SERVLET;

@WebServlet(JS_SERVLET)
public class JavascriptServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Object result = null;
        try {
            result = JavascriptService.getInstance().run(request.getParameter("script"));
        } catch (ScriptException e) {
            e.printStackTrace();
            response.getWriter().print("RUN error: " + e.getMessage());
        }
        if (result != null) {
            response.getWriter().print(result.toString());
        }
    }
}
