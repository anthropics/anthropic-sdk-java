package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.helpers.BetaToolRunner;
import com.anthropic.models.beta.messages.BetaMessage;
import com.anthropic.models.beta.messages.MessageCreateParams;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.util.function.Supplier;

public final class BetaToolRunnerExample {
    private BetaToolRunnerExample() {}

    @JsonClassDescription("Get the weather in a given location")
    static class GetWeather implements Supplier<String> {
        @JsonPropertyDescription("The city and state, e.g. San Francisco, CA")
        public String location;

        @Override
        public String get() {
            return "The weather in " + location + " is foggy and 60°F";
        }
    }

    public static void main(String[] args) {
        // Configure by setting the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        BetaToolRunner toolRunner = client.beta()
                .messages()
                .toolRunner(MessageCreateParams.builder()
                        .model("claude-sonnet-4-5")
                        .putAdditionalHeader("anthropic-beta", "structured-outputs-2025-11-13")
                        .maxTokens(1000)
                        .addUserMessage("What is the weather in San Francisco?")
                        .addTool(GetWeather.class)
                        .build());
        for (BetaMessage message : toolRunner) {
            System.out.println(message);
        }
    }
}
