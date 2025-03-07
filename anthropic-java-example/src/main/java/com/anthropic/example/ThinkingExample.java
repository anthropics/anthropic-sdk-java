package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.ContentBlock;
import com.anthropic.models.MessageCreateParams;
import com.anthropic.models.Model;
import com.anthropic.models.ThinkingConfigEnabled;
import java.util.List;

public final class ThinkingExample {
    private ThinkingExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_3_7_SONNET_20250219)
                .maxTokens(2048)
                .thinking(ThinkingConfigEnabled.builder().budgetTokens(1024).build())
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        List<ContentBlock> contentBlocks =
                client.messages().create(createParams).content();
        for (ContentBlock block : contentBlocks) {
            if (block.isThinking()) {
                System.out.println("Thinking: " + block.asThinking().thinking());
            } else if (block.isText()) {
                System.out.println("Text: " + block.asText().text());
            }
        }
    }
}
