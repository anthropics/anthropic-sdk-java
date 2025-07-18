package com.anthropic.example;

import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;
import com.anthropic.models.messages.RawContentBlockDeltaEvent;
import com.anthropic.models.messages.ThinkingConfigEnabled;

public final class ThinkingStreamingAsyncExample {
    private ThinkingStreamingAsyncExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClientAsync client = AnthropicOkHttpClientAsync.fromEnv();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_SONNET_4_20250514)
                .maxTokens(2048)
                .thinking(ThinkingConfigEnabled.builder().budgetTokens(1024).build())
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        client.messages()
                .createStreaming(createParams)
                .subscribe(event -> event.contentBlockDelta()
                        .map(RawContentBlockDeltaEvent::delta)
                        .ifPresent(delta -> {
                            if (delta.isThinking()) {
                                System.out.print(delta.asThinking().thinking());
                            } else if (delta.isText()) {
                                System.out.print(delta.asText().text());
                            }
                        }))
                .onCompleteFuture()
                .join();
    }
}
