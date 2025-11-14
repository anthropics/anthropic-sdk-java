package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.beta.messages.*;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.util.List;

public final class BetaMessagesToolsExample {
    private BetaMessagesToolsExample() {}

    enum Unit {
        CELSIUS,
        FAHRENHEIT;

        public String toString() {
            switch (this) {
                case CELSIUS:
                    return "°C";
                case FAHRENHEIT:
                default:
                    return "°F";
            }
        }

        /** Converts a temperature in Kelvin to a temperature in degrees Fahrenheit or Celsius. */
        public double fromKelvin(double temperatureK) {
            switch (this) {
                case CELSIUS:
                    return temperatureK - 273.15;
                case FAHRENHEIT:
                default:
                    return (temperatureK - 273.15) * 1.8 + 32.0;
            }
        }
    }

    @JsonClassDescription("Get the weather in a given location")
    static class GetWeather {
        @JsonPropertyDescription("The city and state, e.g. San Francisco, CA")
        public String location;

        @JsonPropertyDescription("The unit of temperature")
        public Unit unit;

        public Weather execute() {
            double temperatureK;

            switch (location) {
                case "San Francisco, CA":
                    temperatureK = 300.0;
                    break;
                case "New York, NY":
                    temperatureK = 310.0;
                    break;
                case "Dallas, TX":
                    temperatureK = 305.0;
                    break;
                default:
                    temperatureK = 295;
                    break;
            }

            return new Weather(String.format("%.0f%s", unit.fromKelvin(temperatureK), unit));
        }
    }

    static class Weather {
        public String temperature;

        public Weather(String temperature) {
            this.temperature = temperature;
        }
    }

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        MessageCreateParams.Builder createParamsBuilder = MessageCreateParams.builder()
                .model("claude-sonnet-4-5-20250929-structured-outputs")
                .putAdditionalHeader("anthropic-beta", "structured-outputs-2025-09-17")
                .maxTokens(2048)
                .addTool(GetWeather.class)
                .addUserMessage("What's the temperature in New York?");

        client.beta().messages().create(createParamsBuilder.build()).content().stream()
                .flatMap(contentBlock -> contentBlock.toolUse().stream())
                .forEach(toolUseBlock -> createParamsBuilder
                        // Add a message indicating that the tool use was requested.
                        .addAssistantMessageOfBetaContentBlockParams(
                                List.of(BetaContentBlockParam.ofToolUse(BetaToolUseBlockParam.builder()
                                        .name(toolUseBlock.name())
                                        .id(toolUseBlock.id())
                                        .input(toolUseBlock._input())
                                        .build())))
                        // Add a message with the result of the requested tool use.
                        .addUserMessageOfBetaContentBlockParams(
                                List.of(BetaContentBlockParam.ofToolResult(BetaToolResultBlockParam.builder()
                                        .toolUseId(toolUseBlock.id())
                                        .contentAsJson(callTool(toolUseBlock))
                                        .build()))));

        client.beta().messages().create(createParamsBuilder.build()).content().stream()
                .flatMap(contentBlock -> contentBlock.text().stream())
                .forEach(textBlock -> System.out.println(textBlock.text()));
    }

    private static Object callTool(BetaToolUseBlock toolUseBlock) {
        if (!"get_weather".equals(toolUseBlock.name())) {
            throw new IllegalArgumentException("Unknown tool: " + toolUseBlock.name());
        }

        GetWeather tool = toolUseBlock.input(GetWeather.class);
        return tool != null ? tool.execute() : new Weather("unknown");
    }
}
