package com.anthropic.example;

import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.helpers.BetaMessageThreadAsync;
import com.anthropic.models.beta.messages.BetaMessage;
import com.anthropic.models.beta.messages.ThreadCreateParams;
import com.anthropic.models.messages.Model;

public final class BetaMessageThreadAsyncExample {
    private BetaMessageThreadAsyncExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClientAsync client = AnthropicOkHttpClientAsync.fromEnv();

        // Nothing is sent until the first turn.
        BetaMessageThreadAsync thread = client.beta()
                .messages()
                .thread(ThreadCreateParams.builder()
                        .model(Model.CLAUDE_SONNET_5_5)
                        .maxTokens(2048)
                        .system("You are a storyteller. Keep every answer under a hundred words.")
                        .build());

        // The first turn sends the whole conversation and starts the thread. Each later turn sends only its new
        // message. One turn runs at a time, so the next turn starts when the future of the one before it completes.
        thread.send("Tell me a story about building the best SDK!")
                .thenCompose(first -> {
                    print(first);
                    return thread.send("But why?");
                })
                .thenCompose(second -> {
                    print(second);
                    // A turn can be streamed. The reply is recorded when the stream has delivered its last event.
                    return thread.sendStreaming("And then?")
                            .subscribe(event -> event.contentBlockDelta().stream()
                                    .flatMap(deltaEvent -> deltaEvent.delta().text().stream())
                                    .forEach(textDelta -> System.out.print(textDelta.text())))
                            .onCompleteFuture();
                })
                .join();
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
