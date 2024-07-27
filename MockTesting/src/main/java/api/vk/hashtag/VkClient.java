package api.vk.hashtag;

import java.time.Instant;

public interface VkClient {
    VkNewsResponse searchTweets(String hashtag, Instant start, Instant end);
}
