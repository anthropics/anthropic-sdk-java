package com.anthropic.example;

import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.models.messages.ContentBlock;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;
import com.anthropic.models.messages.ThinkingConfigEnabled;
import java.util.List;

public final class ThinkingAsyncExample {
    private ThinkingAsyncExample() {}

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
                .create(createParams)
                .thenAccept(response -> {
                    List<ContentBlock> contentBlocks = response.content();
                    for (ContentBlock block : contentBlocks) {
                        if (block.isThinking()) {
                            System.out.println("Thinking: " + block.asThinking().thinking());
                        } else if (block.isText()) {
                            System.out.println("Text: " + block.asText().text());
                        }
                    }
                })
                .join();
    }
}
