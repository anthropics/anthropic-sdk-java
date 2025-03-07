package com.anthropic.example;

import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.models.MessageCountTokensParams;
import com.anthropic.models.Model;

public final class CountTokensAsyncExample {
    private CountTokensAsyncExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClientAsync client = AnthropicOkHttpClientAsync.fromEnv();

        MessageCountTokensParams countTokensParams = MessageCountTokensParams.builder()
                .model(Model.CLAUDE_3_5_SONNET_LATEST)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        client.messages()
                .countTokens(countTokensParams)
                .thenAccept(response -> System.out.println(response.inputTokens()))
                .join();
    }
}
