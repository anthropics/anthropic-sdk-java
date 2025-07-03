package com.anthropic.example;

import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.models.messages.MessageCountTokensParams;
import com.anthropic.models.messages.Model;

public final class CountTokensAsyncExample {
    private CountTokensAsyncExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClientAsync client = AnthropicOkHttpClientAsync.fromEnv();

        MessageCountTokensParams countTokensParams = MessageCountTokensParams.builder()
                .model(Model.CLAUDE_SONNET_4_20250514)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        client.messages()
                .countTokens(countTokensParams)
                .thenAccept(response -> System.out.println(response.inputTokens()))
                .join();
    }
}
