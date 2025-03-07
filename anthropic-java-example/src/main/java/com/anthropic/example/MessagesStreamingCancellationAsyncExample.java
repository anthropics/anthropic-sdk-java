package com.anthropic.example;

import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.core.http.AsyncStreamResponse;
import com.anthropic.models.MessageCreateParams;
import com.anthropic.models.Model;
import com.anthropic.models.RawMessageStreamEvent;
import com.anthropic.models.TextDelta;

public final class MessagesStreamingCancellationAsyncExample {
    private MessagesStreamingCancellationAsyncExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClientAsync client = AnthropicOkHttpClientAsync.fromEnv();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_3_5_SONNET_LATEST)
                .maxTokens(2048)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        AsyncStreamResponse<RawMessageStreamEvent> streamResponse =
                client.messages().createStreaming(createParams);
        streamResponse
                .subscribe(event -> event.contentBlockDelta().stream()
                        .flatMap(deltaEvent -> deltaEvent.delta().text().stream())
                        .map(TextDelta::text)
                        .forEach(text -> {
                            System.out.print(text);
                            if (text.contains("API")) {
                                // Close the stream early.
                                streamResponse.close();
                            }
                        }))
                .onCompleteFuture()
                .join();
    }
}
