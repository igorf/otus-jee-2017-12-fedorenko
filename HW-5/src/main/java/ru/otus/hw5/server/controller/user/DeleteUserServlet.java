package ru.otus.hw5.server.controller.user;

import ru.otus.hw5.server.services.EmployeeService;
import ru.otus.hw5.server.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.otus.hw5.util.ConstantHolder.ID_PARAM;
import static ru.otus.hw5.util.ConstantHolder.URL_USER_DELETE;

@WebServlet(URL_USER_DELETE)
public class DeleteUserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		LoginService.getInstance().setSession(request.getSession());
		if (!LoginService.getInstance().isLogged()) {
			response.setStatus(403);
			return;
		}
        EmployeeService.getInstance().remove(Long.valueOf(request.getParameter(ID_PARAM)));
    }
}
