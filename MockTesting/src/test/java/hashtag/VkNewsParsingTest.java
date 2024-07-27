package hashtag;


import api.vk.hashtag.VkNewsParsing;
import org.junit.jupiter.api.Test;
import com.google.gson.JsonSyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class VkNewsParsingTest {
    private final static String CORRECT_RESPONSE = """
            {
              "response": {
                "total_count": 618
              }
            }
            """;

    private final static String INVALID_RESPONSE = """
            {
              "resp....!!%
            }
            """;

    @Test
    public void parseResponse() {
        var parser = new VkNewsParsing();
        var resp = parser.parseResponse(CORRECT_RESPONSE);

        assertThat(resp.tweets()).isEqualTo(618);
    }

    @Test
    public void parseMalformedResponse() {
        var parser = new VkNewsParsing();
        assertThatExceptionOfType(JsonSyntaxException.class)
                .isThrownBy(() -> parser.parseResponse(INVALID_RESPONSE));
    }
}
