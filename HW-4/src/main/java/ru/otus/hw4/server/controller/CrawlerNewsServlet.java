package ru.otus.hw4.server.controller;

import ru.otus.hw4.data.CrawledNews;
import ru.otus.hw4.server.services.CrawlerService;

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

import static ru.otus.hw4.util.ConstantHolder.CR_NEWS_SERVLET;
import static ru.otus.hw4.util.ConstantHolder.ENCODING;

@WebServlet(CR_NEWS_SERVLET)
public class CrawlerNewsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<CrawledNews> news = CrawlerService.getInstance().getNews();
        JsonbConfig config = new JsonbConfig().withEncoding(ENCODING).withFormatting(true);
        Jsonb jsonb = JsonbBuilder.create(config);
        String result = jsonb.toJson(news);

        response.getWriter().print(result);
    }
}
