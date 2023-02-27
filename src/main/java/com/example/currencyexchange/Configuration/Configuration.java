package com.example.currencyexchange.Configuration;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public Map<String, String> prices() throws IOException {
        Map<String, String> exchangeRates = new HashMap<>();
        String url = "https://api.nbp.pl/api/exchangerates/tables/a/";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String result = EntityUtils.toString(response.getEntity());
                JSONObject obj = new JSONObject(result.substring(1, result.length() - 1));
                JSONArray statsArray = obj.getJSONArray("rates");
                for (int i = 0; i < statsArray.length() - 20; i++) {
                    exchangeRates.put(statsArray.getJSONObject(i).optString("code"), statsArray.getJSONObject(i).optString("mid"));
                }
            }
        }
        return exchangeRates;
    }
}
