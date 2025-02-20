package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.http.StreamResponse;
import com.anthropic.models.MessageCreateParams;
import com.anthropic.models.Model;
import com.anthropic.models.RawMessageStreamEvent;
import com.anthropic.models.TextDelta;

public final class MessagesStreamingCancellationExample {
    private MessagesStreamingCancellationExample() {}

    public static void main(String[] args) throws Exception {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_3_5_SONNET_LATEST)
                .maxTokens(2048)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        try (StreamResponse<RawMessageStreamEvent> streamResponse =
                client.messages().createStreaming(createParams)) {
            Iterable<String> textDeltas = streamResponse.stream()
                    .flatMap(event -> event.contentBlockDelta().stream())
                    .flatMap(deltaEvent -> deltaEvent.delta().text().stream())
                    .map(TextDelta::text)::iterator;
            for (String text : textDeltas) {
                System.out.print(text);
                if (text.contains("SDK")) {
                    // Cancel the stream early.
                    streamResponse.close();
                }
            }
        }
    }
}
