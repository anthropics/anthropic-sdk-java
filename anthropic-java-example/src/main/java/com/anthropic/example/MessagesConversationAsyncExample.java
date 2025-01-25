package com.anthropic.example;

import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.models.MessageCreateParams;
import com.anthropic.models.MessageParam;
import com.anthropic.models.Model;
import java.util.concurrent.CompletableFuture;

public final class MessagesConversationAsyncExample {
    private MessagesConversationAsyncExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClientAsync client = AnthropicOkHttpClientAsync.fromEnv();

        // Use a builder so that we can append more messages to it below.
        // Each time we call .build()` we get an immutable object that's unaffected by future mutations of the builder.
        MessageCreateParams.Builder createParamsBuilder = MessageCreateParams.builder()
                .model(Model.CLAUDE_3_5_SONNET_LATEST)
                .maxTokens(2048)
                .addMessage(MessageParam.builder()
                        .role(MessageParam.Role.USER)
                        .content("Tell me a story about building the best SDK!")
                        .build());

        CompletableFuture<Void> future = CompletableFuture.completedFuture(null);
        for (int i = 0; i < 4; i++) {
            final int index = i;
            future = future.thenComposeAsync(unused -> client.messages().create(createParamsBuilder.build()))
                    .thenAccept(message -> {
                        message.content().stream()
                                .flatMap(contentBlock -> contentBlock.text().stream())
                                .forEach(textBlock -> System.out.println(textBlock.text()));

                        System.out.println("\n-----------------------------------\n");

                        createParamsBuilder
                                .addMessage(message)
                                .addMessage(MessageParam.builder()
                                        .role(MessageParam.Role.USER)
                                        .content("But why?" + "?".repeat(index))
                                        .build());
                    });
        }

        future.join();
    }
}
