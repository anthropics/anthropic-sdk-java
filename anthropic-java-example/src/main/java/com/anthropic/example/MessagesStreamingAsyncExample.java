package com.anthropic.example;

import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.core.http.AsyncStreamResponse;
import com.anthropic.models.MessageCreateParams;
import com.anthropic.models.MessageParam;
import com.anthropic.models.MessageParam.Role;
import com.anthropic.models.Model;
import com.anthropic.models.RawMessageStreamEvent;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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

        CompletableFuture<Void> onCompleteFuture = new CompletableFuture<>();

        // TODO: Update this example once we support expose an `onCompleteFuture()` method.
        client.messages().createStreaming(createParams).subscribe(new AsyncStreamResponse.Handler<>() {
            @Override
            public void onNext(RawMessageStreamEvent event) {
                event.contentBlockDelta().stream()
                        .flatMap(deltaEvent -> deltaEvent.delta().text().stream())
                        .forEach(textDelta -> System.out.print(textDelta.text()));
            }

            @Override
            public void onComplete(Optional<Throwable> error) {
                onCompleteFuture.complete(null);
            }
        });

        onCompleteFuture.join();
    }
}
