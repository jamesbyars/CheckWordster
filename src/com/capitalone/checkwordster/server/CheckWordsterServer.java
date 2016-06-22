package com.capitalone.checkwordster.server;

import com.capitalone.checkwordster.core.CheckWordster;
import org.json.JSONObject;

import static spark.Spark.port;
import static spark.Spark.post;


public class CheckWordsterServer {

    public static void main(String[] args) {

        port(9090);
        post("/checkWordster", (request, response) -> {
            JSONObject obj = new JSONObject(request.body());
            String numberInDigits = obj.getString("numberInDigits");
            response.type("text/json");
            return "{\"numberInDigits\": " + "\"" + numberInDigits + "\", \"numberInWords\": \"" + new CheckWordster(numberInDigits).getWords() + "\"}";
        });
    }
}