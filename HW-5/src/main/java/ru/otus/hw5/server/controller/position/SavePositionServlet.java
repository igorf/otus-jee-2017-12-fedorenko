package ru.otus.hw5.server.controller.position;

import com.google.gson.Gson;
import ru.otus.hw5.db.model.Position;
import ru.otus.hw5.server.services.LoginService;
import ru.otus.hw5.server.services.PositionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.otus.hw5.util.ConstantHolder.URL_POSITION_SAVE;

@WebServlet(URL_POSITION_SAVE)
public class SavePositionServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LoginService.getInstance().setSession(request.getSession());
        if (!LoginService.getInstance().isLogged()) {
            response.setStatus(403);
            return;
        }

        Position constructed = constructPosition(request);
        PositionService.getInstance().save(constructed);
    }

    private Position constructPosition(HttpServletRequest request) throws IOException {
        return new Gson().fromJson(request.getReader(), Position.class);
    }
}
