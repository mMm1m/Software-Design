package api.vk.hashtag;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class VkHashtagManager {
    private VkClient client;

    public VkHashtagManager(VkClient client){
        this.client = client;
    }
    public List<Long> getHashtagStatistics(String hashtag, Instant start_point, long amountHours){
        List<Long> arr = new ArrayList<>();
        for(long i = amountHours-1; i >= 0; --i){
            Instant end = start_point.minus(Duration.ofHours(i));
            Instant start = end.minus(Duration.ofHours(1));
            VkNewsResponse response = client.searchTweets(hashtag, start, end);
            arr.add(response.tweets());
        }
        return arr;
    }
}
