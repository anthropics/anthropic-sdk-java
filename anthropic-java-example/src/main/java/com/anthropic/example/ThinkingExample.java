package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.ContentBlock;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;
import com.anthropic.models.messages.OutputConfig;
import com.anthropic.models.messages.ThinkingConfigAdaptive;
import java.util.List;

public final class ThinkingExample {
    private ThinkingExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

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
