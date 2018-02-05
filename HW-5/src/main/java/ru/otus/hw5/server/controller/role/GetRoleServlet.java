package ru.otus.hw5.server.controller.role;

import com.google.gson.Gson;
import ru.otus.hw5.db.model.Role;
import ru.otus.hw5.server.services.LoginService;
import ru.otus.hw5.server.services.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.otus.hw5.util.ConstantHolder.ID_PARAM;
import static ru.otus.hw5.util.ConstantHolder.URL_ROLE_GET;

@WebServlet(URL_ROLE_GET)
public class GetRoleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LoginService.getInstance().setSession(request.getSession());
        if (!LoginService.getInstance().isLogged()) {
            response.setStatus(403);
            return;
        }

        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");

        Role role = RoleService.getInstance().roleByID(Long.valueOf(request.getParameter(ID_PARAM)));
        response.getWriter().print(new Gson().toJson(role));
    }
}
