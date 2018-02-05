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
import java.util.List;

import static ru.otus.hw5.util.ConstantHolder.URL_ROLE_LIST;

@WebServlet(URL_ROLE_LIST)
public class ListRolesServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LoginService.getInstance().setSession(request.getSession());
        if (!LoginService.getInstance().isLogged()) {
            response.setStatus(403);
            return;
        }

        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");

        List<Role> rolesList = RoleService.getInstance().list();
        response.getWriter().print(new Gson().toJson(rolesList));
    }
}
