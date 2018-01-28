package ru.otus.hw4.server.controller;

import ru.otus.hw4.data.Currency;
import ru.otus.hw4.server.services.CurrencyService;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.otus.hw4.util.ConstantHolder.CURRENCY_SERVLET;
import static ru.otus.hw4.util.ConstantHolder.ENCODING;

@WebServlet(CURRENCY_SERVLET)
public class CurrencyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Currency> currencyList = new CurrencyService().load();
        JsonbConfig config = new JsonbConfig().withEncoding(ENCODING).withFormatting(true);
        Jsonb jsonb = JsonbBuilder.create(config);
        String result = jsonb.toJson(currencyList);

        response.getWriter().print(result);
    }
}
