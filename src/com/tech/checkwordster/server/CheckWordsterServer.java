package com.tech.checkwordster.server;

import com.tech.checkwordster.core.CheckWordster;
import org.json.JSONObject;

import static spark.Spark.*;


public class CheckWordsterServer {

    public static void main(String[] args) {

        port(9090);
        post("/checkWordster", (request, response) -> {
            if (request.body().equals("STOP")) stop();
            JSONObject obj = new JSONObject(request.body());
            String numberInDigits = obj.getString("numberInDigits");
            response.type("text/json");
            return "{\"numberInDigits\": " + "\"" + numberInDigits + "\", " +
                    "\"numberInWords\": \"" + new CheckWordster(numberInDigits).getWords() + "\"}";
        });

        get("/", (request, response) -> "{'hello, world'}");
    }
}