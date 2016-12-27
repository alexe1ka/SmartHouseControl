package com.example.alexe1ka.iotalexe1ka;

import com.example.alexe1ka.iotalexe1ka.model.ReplyToRequest;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by alexe1ka on 12.12.2016.
 */

public class HttpRestHandler {

    public ReplyToRequest makeUrlRequest(String query, String typeOfQuery) {
        String resultString = "";


        JsonParser jsonParser = new JsonParser();
        ReplyToRequest replyToRequest = new ReplyToRequest();


        try {
            URL url = new URL(query);
            HttpURLConnection urlRequest = (HttpURLConnection) url.openConnection();
            urlRequest.setRequestMethod(typeOfQuery);
            urlRequest.connect();
            InputStream in = new BufferedInputStream(urlRequest.getInputStream());
            resultString = convertToString(in);
            replyToRequest = jsonParser.json2String(resultString);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return replyToRequest;
    }


    public String convertToString(InputStream is) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        return new String(total);
    }
}