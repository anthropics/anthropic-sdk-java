package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.MessageCountTokensParams;
import com.anthropic.models.messages.Model;

public final class CountTokensExample {
    private CountTokensExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        MessageCountTokensParams countTokensParams = MessageCountTokensParams.builder()
                .model(Model.CLAUDE_SONNET_4_20250514)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        System.out.println(client.messages().countTokens(countTokensParams).inputTokens());
    }
}
