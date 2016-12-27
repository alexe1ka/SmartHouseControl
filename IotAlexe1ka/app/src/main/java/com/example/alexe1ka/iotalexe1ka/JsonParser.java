package com.example.alexe1ka.iotalexe1ka;

import android.util.Log;

import com.example.alexe1ka.iotalexe1ka.model.ReplyToRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by alexe1ka on 13.12.2016.
 */

public class JsonParser {
    public static ReplyToRequest json2String(String string) throws JSONException {
        JSONObject jObj = new JSONObject(string);
        ReplyToRequest replyToRequest = new ReplyToRequest();

        String temp = jObj.getString("temperature");
        String hum = jObj.getString("humidity");
        //something another params

        //
        String id = jObj.getString("id");
        String name = jObj.getString("name");
        String hardware = jObj.getString("hardware");
        String connectedStatus = jObj.getString("connected");

        replyToRequest.setTemperature(temp);
        replyToRequest.setHumidity(hum);
        replyToRequest.setId(id);
        replyToRequest.setName(name);
        replyToRequest.setName(hardware);
        replyToRequest.setConnectedStatus(connectedStatus);
        return replyToRequest;
    }
}
