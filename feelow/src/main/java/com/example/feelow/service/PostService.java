package com.example.feelow.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PostService {

    public String sendPostRequest(String url, String jsonData, String requestName) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");

            StringEntity requestEntity = new StringEntity(jsonData, ContentType.APPLICATION_JSON);
            httpPost.setEntity(requestEntity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String responseBody = EntityUtils.toString(entity);
                    String decodedResponse = decodeUnicode(responseBody);
                    return decodedResponse;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fail = "Failed request '" + requestName + "'";
        return fail;
    }

    private String decodeUnicode(String unicodeStr) {
        StringBuilder decodedStr = new StringBuilder();
        Pattern pattern = Pattern.compile("\\\\u([0-9A-Fa-f]{4})");
        Matcher matcher = pattern.matcher(unicodeStr);

        while (matcher.find()) {
            char ch = (char) Integer.parseInt(matcher.group(1), 16);
            matcher.appendReplacement(decodedStr, Character.toString(ch));
        }
        matcher.appendTail(decodedStr);

        return decodedStr.toString();
    }
}
