package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.http.StreamResponse;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;
import com.anthropic.models.messages.RawContentBlockDeltaEvent;
import com.anthropic.models.messages.RawMessageStreamEvent;
import com.anthropic.models.messages.ThinkingConfigEnabled;

public final class ThinkingStreamingExample {
    private ThinkingStreamingExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_SONNET_4_20250514)
                .maxTokens(2048)
                .thinking(ThinkingConfigEnabled.builder().budgetTokens(1024).build())
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        try (StreamResponse<RawMessageStreamEvent> streamResponse =
                client.messages().createStreaming(createParams)) {
            streamResponse.stream()
                    .flatMap(event -> event.contentBlockDelta().stream())
                    .map(RawContentBlockDeltaEvent::delta)
                    .forEach(delta -> {
                        if (delta.isThinking()) {
                            System.out.print(delta.asThinking().thinking());
                        } else if (delta.isText()) {
                            System.out.print(delta.asText().text());
                        }
                    });
        }
    }
}
