package ru.otus.hw5.server.controller;

import com.google.gson.Gson;
import ru.otus.hw5.server.dto.LoginDTO;
import ru.otus.hw5.server.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.otus.hw5.util.ConstantHolder.URL_LOGIN_SERVLET;

@WebServlet(URL_LOGIN_SERVLET)

public class LoginServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginService.getInstance().setSession(request.getSession());
        if (!LoginService.getInstance().isLogged()) {
            LoginDTO dto = new Gson().fromJson(request.getReader(), LoginDTO.class);
            LoginService.getInstance().login(dto.getLogin(), dto.getPassword());
        }

        int status = LoginService.getInstance().isLogged() ? 200 : 403;
        response.setStatus(status);
    }
}
