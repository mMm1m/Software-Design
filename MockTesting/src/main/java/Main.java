import api.vk.hashtag.VkHashtagManager;
import api.vk.hashtag.VkUrlClient;
import api.vk.reader.UrlReaderImpl;

import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        var manager = new VkHashtagManager(new VkUrlClient("https://api.vk.com", new UrlReaderImpl()));
        var stats = manager.getHashtagStatistics("hello", Instant.now(), 24);
        System.out.println(stats);
    }
}