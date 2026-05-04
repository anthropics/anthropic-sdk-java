package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;

/**
 * Simple example demonstrating zero-config credential resolution via fromEnv().
 *
 * <p>The SDK will resolve credentials in this order:
 * <ol>
 *   <li>ANTHROPIC_API_KEY or ANTHROPIC_AUTH_TOKEN env vars
 *   <li>ANTHROPIC_PROFILE env var (loads from ~/.config/anthropic/configs/{profile}.json)
 *   <li>ANTHROPIC_FEDERATION_RULE_ID + ANTHROPIC_ORGANIZATION_ID + identity token (WIF)
 *   <li>Default profile from ~/.config/anthropic/active_config or "default"
 * </ol>
 *
 * <p>Run with: ./gradlew :anthropic-java-example:run -Pexample=FromEnv
 */
public class FromEnvExample {

    public static void main(String[] args) {
        System.out.println("Creating Anthropic client with fromEnv()...");

        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        System.out.println("Client created successfully. Sending test message...");

        Message message = client.messages()
                .create(MessageCreateParams.builder()
                        .model(Model.CLAUDE_SONNET_4_20250514)
                        .maxTokens(100)
                        .addUserMessage("Say hello in one sentence.")
                        .build());

        String responseText = message.content()
                .get(0)
                .text()
                .map(textBlock -> textBlock.text())
                .orElse("(no text content)");
        System.out.println("Response: " + responseText);
    }
}
