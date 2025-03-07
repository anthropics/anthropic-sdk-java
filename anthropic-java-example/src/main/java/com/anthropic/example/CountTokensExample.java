package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.MessageCountTokensParams;
import com.anthropic.models.Model;

public final class CountTokensExample {
    private CountTokensExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        MessageCountTokensParams countTokensParams = MessageCountTokensParams.builder()
                .model(Model.CLAUDE_3_5_SONNET_LATEST)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        System.out.println(client.messages().countTokens(countTokensParams).inputTokens());
    }
}
