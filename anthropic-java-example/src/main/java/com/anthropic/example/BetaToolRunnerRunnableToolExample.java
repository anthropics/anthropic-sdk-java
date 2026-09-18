package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.helpers.BetaRunnableTool;
import com.anthropic.helpers.BetaToolRunner;
import com.anthropic.models.beta.messages.BetaMessage;
import com.anthropic.models.beta.messages.BetaToolResultBlockParam;
import com.anthropic.models.beta.messages.MessageCreateParams;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public final class BetaToolRunnerRunnableToolExample {
    private final String conditions = "foggy and 60°F";

    private BetaToolRunnerRunnableToolExample() {}

    @JsonClassDescription("Get the weather in a given location")
    static class GetWeather {
        @JsonPropertyDescription("The city and state, e.g. San Francisco, CA")
        public String location;
    }

    private BetaRunnableTool getWeatherTool() {
        return BetaRunnableTool.of(GetWeather.class, getWeather -> {
            String weather = "The weather in " + getWeather.location + " is " + conditions;
            return BetaToolResultBlockParam.Content.ofString(weather);
        });
    }

    public static void main(String[] args) {
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        BetaToolRunner toolRunner = client.beta()
                .messages()
                .toolRunner(MessageCreateParams.builder()
                        .model("claude-sonnet-5")
                        .putAdditionalHeader("anthropic-beta", "structured-outputs-2025-11-13")
                        .maxTokens(1000)
                        .addUserMessage("What is the weather in San Francisco?")
                        .addTool(new BetaToolRunnerRunnableToolExample().getWeatherTool())
                        .build());
        for (BetaMessage message : toolRunner) {
            System.out.println(message);
        }
    }
}
