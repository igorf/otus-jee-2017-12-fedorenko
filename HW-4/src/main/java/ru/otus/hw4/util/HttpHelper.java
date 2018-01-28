package ru.otus.hw4.util;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.concurrent.TimeUnit;

import static ru.otus.hw4.util.ConstantHolder.REQUEST_TIMEOUT;

public class HttpHelper {
    public String GET(String url) {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);
            HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
            OkHttpClient client = builder.build();
            Request request = new Request.Builder()
                    .header("Connection", "close")
                    .url(httpBuilder.build()).build();
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    return response.body().string();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "";
    }
}
