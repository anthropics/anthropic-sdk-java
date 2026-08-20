package com.anthropic.example;

import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;
import com.anthropic.models.messages.OutputConfig;
import com.anthropic.models.messages.RawContentBlockDeltaEvent;
import com.anthropic.models.messages.ThinkingConfigAdaptive;

public final class ThinkingStreamingAsyncExample {
    private ThinkingStreamingAsyncExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClientAsync client = AnthropicOkHttpClientAsync.fromEnv();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_SONNET_5)
                .maxTokens(16000)
                .thinking(ThinkingConfigAdaptive.builder()
                        .display(ThinkingConfigAdaptive.Display.SUMMARIZED)
                        .build())
                .outputConfig(
                        OutputConfig.builder().effort(OutputConfig.Effort.HIGH).build())
                .addUserMessage(
                        "Create a haiku about Anthropic. Think carefully about syllable counts before answering.")
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
