package ru.otus.hw4.server.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.otus.hw4.data.CrawledNews;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.otus.hw4.util.ConstantHolder.*;

public class CrawlerService {
    private static CrawlerService ourInstance = new CrawlerService();

    public static CrawlerService getInstance() {
        return ourInstance;
    }

    private CrawlerService() {
        super();
    }

    public synchronized List<CrawledNews> getNews() throws IOException {
        Document jsopNewsDocument  = Jsoup.connect(NEWS_LIST_URL).get();
        Elements elements = jsopNewsDocument.select(NEWS_CONTAINER);
        List<CrawledNews> result = new ArrayList<>();

        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            Element link = element.selectFirst(NEWS_LINK);
            Element preview = element.selectFirst(NEWS_CONTENT);

            if (link != null && preview != null) {
                CrawledNews cn = new CrawledNews();
                cn.setHref(NEWS_BASE + link.attr(HREF_ATTR));
                cn.setHeader(link.text());
                cn.setContent(preview.text());

                result.add(cn);
            }
        }
        return result;
    }
}
