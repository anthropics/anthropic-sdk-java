package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.Message;
import com.anthropic.models.MessageCreateParams;
import com.anthropic.models.Model;

public final class MessagesConversationExample {
    private MessagesConversationExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        // Use a builder so that we can append more messages to it below.
        // Each time we call .build()` we get an immutable object that's unaffected by future mutations of the builder.
        MessageCreateParams.Builder createParamsBuilder = MessageCreateParams.builder()
                .model(Model.CLAUDE_3_5_SONNET_LATEST)
                .maxTokens(2048)
                .addUserMessage("Tell me a story about building the best SDK!");

        for (int i = 0; i < 4; i++) {
            Message message = client.messages().create(createParamsBuilder.build());

            message.content().stream()
                    .flatMap(contentBlock -> contentBlock.text().stream())
                    .forEach(textBlock -> System.out.println(textBlock.text()));

            System.out.println("\n-----------------------------------\n");

            createParamsBuilder.addMessage(message).addUserMessage("But why?" + "?".repeat(i));
        }
    }
}
