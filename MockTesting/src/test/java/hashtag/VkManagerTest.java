package hashtag;

import api.vk.hashtag.VkClient;
import api.vk.hashtag.VkHashtagManager;

import api.vk.hashtag.VkNewsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


public class VkManagerTest {
    private VkHashtagManager vkNewsfeedManager;

    @Mock
    private VkClient client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vkNewsfeedManager = new VkHashtagManager(client);
    }

    @Test
    public void testSearchNewsSingle() {
        var start = Instant.ofEpochSecond(3600);
        when(client.searchTweets(eq("#test"), eq(Instant.ofEpochSecond(0)), eq(Instant.ofEpochSecond(3600))))
                .thenReturn(new VkNewsResponse(50));

        List<Long> stats = vkNewsfeedManager.getHashtagStatistics("#test", start, 1);

        verify(client, times(1))
                .searchTweets(any(), any(), any());
        assertThat(stats).isEqualTo(List.of(50L));
    }

    @Test
    public void testSearchNewsMultiple() {
        var start = Instant.now();
        when(client.searchTweets(eq("#test"), any(), any()))
                .thenReturn(new VkNewsResponse(1),
                        new VkNewsResponse(2),
                        new VkNewsResponse(3),
                        new VkNewsResponse(4),
                        new VkNewsResponse(5));

        List<Long> stats = vkNewsfeedManager.getHashtagStatistics("#test", start, 5);

        verify(client, times(5)).searchTweets(any(), any(), any());
        assertThat(stats).isEqualTo(List.of(1L, 2L, 3L, 4L, 5L));
    }
}
