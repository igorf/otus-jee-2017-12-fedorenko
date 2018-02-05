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

import static ru.otus.hw5.util.ConstantHolder.URL_ROLE_SAVE;

@WebServlet(URL_ROLE_SAVE)
public class SaveRoleServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LoginService.getInstance().setSession(request.getSession());
        if (!LoginService.getInstance().isLogged()) {
            response.setStatus(403);
            return;
        }

        Role constructed = constructPosition(request);
        RoleService.getInstance().save(constructed);
    }

    private Role constructPosition(HttpServletRequest request) throws IOException {
        return new Gson().fromJson(request.getReader(), Role.class);
    }
}
