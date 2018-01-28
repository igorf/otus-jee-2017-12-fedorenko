package ru.otus.hw4.data;

import lombok.Data;

@Data
public class CrawledNews {
    String content;
    String header;
    String href;
}
