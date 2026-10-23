package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.http.StreamResponse;
import com.anthropic.helpers.BetaMessageThread;
import com.anthropic.models.beta.messages.BetaMessage;
import com.anthropic.models.beta.messages.BetaRawMessageStreamEvent;
import com.anthropic.models.beta.messages.ThreadCreateParams;
import com.anthropic.models.messages.Model;

public final class BetaMessageThreadExample {
    private BetaMessageThreadExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        // Nothing is sent until the first turn.
        BetaMessageThread thread = client.beta()
                .messages()
                .thread(ThreadCreateParams.builder()
                        .model(Model.CLAUDE_SONNET_5_5)
                        .maxTokens(2048)
                        .system("You are a storyteller. Keep every answer under a hundred words.")
                        .build());

        // The first turn sends the whole conversation and starts the thread.
        print(thread.send("Tell me a story about building the best SDK!"));

        // Each later turn sends only its new message.
        print(thread.send("But why?"));

        // A turn can be streamed. The reply is recorded when the stream has delivered its last event.
        try (StreamResponse<BetaRawMessageStreamEvent> stream = thread.sendStreaming("And then?")) {
            stream.stream()
                    .flatMap(event -> event.contentBlockDelta().stream())
                    .flatMap(deltaEvent -> deltaEvent.delta().text().stream())
                    .forEach(textDelta -> System.out.print(textDelta.text()));
        }
        System.out.println("\n\n-----------------------------------\n");

        // The thread keeps the conversation as well.
        System.out.println("Messages in the conversation: " + thread.messages().size());
        System.out.println(
                "The next turn continues from: " + thread.previousMessageId().orElse("(a new thread)"));
    }

    private static void print(BetaMessage message) {
        message.content().stream()
                .flatMap(contentBlock -> contentBlock.text().stream())
                .forEach(textBlock -> System.out.println(textBlock.text()));
        System.out.println("\n-----------------------------------\n");
    }
}
