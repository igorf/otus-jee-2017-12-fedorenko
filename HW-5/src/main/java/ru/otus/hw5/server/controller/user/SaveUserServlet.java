package ru.otus.hw5.server.controller.user;

import com.google.gson.Gson;
import ru.otus.hw5.db.model.Employee;
import ru.otus.hw5.server.services.EmployeeService;
import ru.otus.hw5.server.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.otus.hw5.util.ConstantHolder.URL_USER_SAVE;

@WebServlet(URL_USER_SAVE)
public class SaveUserServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LoginService.getInstance().setSession(request.getSession());
        if (!LoginService.getInstance().isLogged()) {
            response.setStatus(403);
            return;
        }

        Employee constructed = constructEmployee(request);
        EmployeeService.getInstance().save(constructed);
    }

    private Employee constructEmployee(HttpServletRequest request) throws IOException {
        return new Gson().fromJson(request.getReader(), Employee.class);
    }
}
