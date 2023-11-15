package net.pascalpex.controller;

import com.google.gson.Gson;
import net.pascalpex.model.Response;
import net.pascalpex.model.Result;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkManager {

    private final static String BASE_URL = "http://server:8080/v1/";
    private final static String GET_URL = BASE_URL + "dataset";
    private final static String POST_URL = BASE_URL + "result";

    public String getDataset() throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(GET_URL);
        URLConnection connection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while(bufferedReader.ready()) {
            result.append(bufferedReader.readLine());
        }
        return result.toString();
    }

    public boolean postResults(Map<String, Long> times) {
        try {
            Gson gson = new Gson();
            Result result = new Result();
            List<Map<String, Object>> resultList = new ArrayList<>();
            for(String key : times.keySet()) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("customerId", key);
                resultMap.put("consumption", times.get(key));
                resultList.add(resultMap);
            }
            result.setResult(resultList);

            URL url = new URL(POST_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            String jsonToSend = gson.toJson(result);
            byte[] bytesToSend = jsonToSend.getBytes(StandardCharsets.UTF_8);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.write(bytesToSend);
            outputStream.flush();
            outputStream.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseString = new StringBuilder();
            while (reader.ready()) {
                responseString.append(reader.readLine());
            }
            reader.close();
            Response response = gson.fromJson(responseString.toString(), Response.class);
            return response.getCode() == 200;
        } catch (IOException e) {
            return false;
        }
    }

}
