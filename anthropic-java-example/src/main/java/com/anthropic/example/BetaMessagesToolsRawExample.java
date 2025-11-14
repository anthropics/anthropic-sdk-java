package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.JsonValue;
import com.anthropic.models.beta.messages.*;
import com.anthropic.models.beta.messages.BetaTool.InputSchema;
import java.util.List;
import java.util.Map;

public final class BetaMessagesToolsRawExample {
    private BetaMessagesToolsRawExample() {}

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

    public static void main(String[] args) {
        // Configure using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        InputSchema schema = InputSchema.builder()
                .properties(JsonValue.from(Map.of(
                        "location",
                        Map.of(
                                "type", "string",
                                "description", "The city and state, e.g. San Francisco, CA"),
                        "unit",
                        Map.of(
                                "type", "string",
                                "description", "The unit of temperature",
                                "enum", List.of(Unit.CELSIUS.name(), Unit.FAHRENHEIT.name())))))
                .putAdditionalProperty("required", JsonValue.from(List.of("location", "unit")))
                .putAdditionalProperty("additionalProperties", JsonValue.from(false))
                .build();

        MessageCreateParams.Builder createParamsBuilder = MessageCreateParams.builder()
                .model("claude-sonnet-4-5-20250929-structured-outputs")
                .putAdditionalHeader("anthropic-beta", "structured-outputs-2025-09-17")
                .maxTokens(2048)
                .addTool(BetaTool.builder()
                        .name("get_weather")
                        .description("Gets the weather in a given location")
                        .inputSchema(schema)
                        // `strict` mode ensures that the output will conform to the schema.
                        .strict(true)
                        .build())
                .addUserMessage("What's the temperature in Dallas in Celsius?");

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
                                        .content(callTool(toolUseBlock))
                                        .build()))));

        client.beta().messages().create(createParamsBuilder.build()).content().stream()
                .flatMap(contentBlock -> contentBlock.text().stream())
                .forEach(textBlock -> System.out.println(textBlock.text()));
    }

    private static String callTool(BetaToolUseBlock toolUseBlock) {
        if (!"get_weather".equals(toolUseBlock.name())) {
            throw new IllegalArgumentException("Unknown tool: " + toolUseBlock.name());
        }

        Map<String, JsonValue> tool =
                (Map<String, JsonValue>) toolUseBlock._input().asObject().get();
        String location = tool.get("location").asStringOrThrow();
        Unit unit = Unit.valueOf(tool.get("unit").asStringOrThrow());
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

        return String.format("{\"temperature\":\"%.0f%s\"}", unit.fromKelvin(temperatureK), unit);
    }
}
