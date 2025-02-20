package com.anthropic.example;

import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.models.MessageCreateParams;
import com.anthropic.models.Model;

public final class MessagesStreamingAsyncExample {
    private MessagesStreamingAsyncExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClientAsync client = AnthropicOkHttpClientAsync.fromEnv();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_3_5_SONNET_LATEST)
                .maxTokens(2048)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        client.messages()
                .createStreaming(createParams)
                .subscribe(event -> event.contentBlockDelta().stream()
                        .flatMap(deltaEvent -> deltaEvent.delta().text().stream())
                        .forEach(textDelta -> System.out.print(textDelta.text())))
                .onCompleteFuture()
                .join();
    }
}
