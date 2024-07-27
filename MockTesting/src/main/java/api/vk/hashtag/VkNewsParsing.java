package api.vk.hashtag;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class VkNewsParsing {
    public VkNewsResponse parseResponse(String response) {
        JsonObject parsed = (JsonObject) new JsonParser().parse(response);
        System.out.println(parsed);
        JsonObject p = (JsonObject) parsed.get("response");
        return new VkNewsResponse(p.get("tweets").getAsLong());
    }
}
