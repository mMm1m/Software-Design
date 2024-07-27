package api.vk.hashtag;

import api.vk.reader.UrlReader;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

public class VkUrlClient implements VkClient {
    private final String API_KEY = "vk1.a.Qzzjw38-PtdJLpvrR4cKjvkFVDk91eCUA9E85Pu0H37wql-nrylVVyuCiTkE5Kda5-MHedoUkXvWVEJj9S06XVFC9F8U8kmdW1Ys3PUn_H3uS--X-RN2YkyA9jngLMVmxR2wqOtJs21ajSaQO_CnqASdX-70N8usYWXkz6hRm8lFb3-pTF6xqENnrWV9EK8J0hy2PrqGSimudzFLm1PmUA";
    private final String host;
    private final UrlReader reader;
    private final VkNewsParsing parser;
    public VkUrlClient(String host, UrlReader reader){
        this.host = host;
        this.reader = reader;
        this.parser = new VkNewsParsing();
    }
    @Override
    public VkNewsResponse searchTweets(String hashtag, Instant start, Instant end) {
        String response = reader.readAsText(createURL(hashtag, start.getEpochSecond(), end.getEpochSecond()));
        return parser.parseResponse(response);
    }

    public String createURL(String hashtag, long start, long end){
        return "%s/method/newsfeed.search?q=%s&start_time=%d&end_time=%d&access_token=%s&v=5.199".
                formatted(host, encodeURL(hashtag), start, end, API_KEY);
    }

    public String encodeURL(String url){
        return URLEncoder.encode(url, StandardCharsets.UTF_8);
    }

}
