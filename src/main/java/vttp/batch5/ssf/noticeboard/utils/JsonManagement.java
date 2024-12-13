package vttp.batch5.ssf.noticeboard.utils;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.batch5.ssf.noticeboard.models.Notice;

public class JsonManagement {

    public static JsonObject parseToJson(String responseBody){

        JsonReader jsonReader = Json.createReader(new StringReader(responseBody));
        JsonObject jsonObject = jsonReader.readObject();
        
        return jsonObject;

    }

    public static String fetchID(JsonObject jsonObject){

        return jsonObject.getString("id");
    }

    public static long fetchTimestamp(JsonObject jsonObject){
        return jsonObject.getJsonNumber("timestamp").longValue();
    }

    public static JsonObject stringToJsonBuild(Notice notice){

        long dateConverted = DateConversion.dateToEpochMili(notice.getPostDate().toString());

        JsonArrayBuilder jsonCategories = Json.createArrayBuilder();
        for (String category : notice.getCategories()) {
            jsonCategories.add(category);
        }

        JsonObject json = Json.createObjectBuilder()
        .add("title", notice.getTitle())
        .add("poster", notice.getPoster())
        .add("postDate", dateConverted)
        .add("categories", jsonCategories)
        .add("text",notice.getText())
        .build();

        return json;
    }
    
}
