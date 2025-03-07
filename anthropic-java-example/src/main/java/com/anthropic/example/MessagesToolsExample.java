package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.JsonValue;
import com.anthropic.models.MessageCreateParams;
import com.anthropic.models.Model;
import com.anthropic.models.Tool;
import com.anthropic.models.Tool.InputSchema;
import com.anthropic.models.ToolChoiceTool;
import java.util.List;
import java.util.Map;

public final class MessagesToolsExample {
    private MessagesToolsExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        InputSchema schema = InputSchema.builder()
                .properties(JsonValue.from(Map.of(
                        "robustness",
                        Map.of(
                                "type", "integer",
                                "description", "How fast is the SDK on a scale from 1 to 10?"),
                        "polish_level",
                        Map.of(
                                "type", "integer",
                                "description", "How polished is the SDK on a scale from 1 to 10?"))))
                .putAdditionalProperty("required", JsonValue.from(List.of("robustness", "polish_level")))
                .build();
        MessageCreateParams createParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_3_5_SONNET_LATEST)
                .maxTokens(2048)
                .addTool(Tool.builder().name("describe_sdk").inputSchema(schema).build())
                .toolChoice(ToolChoiceTool.builder().name("describe_sdk").build())
                .addUserMessage("How amazing is the Anthropic Java SDK?")
                .build();

        client.messages().create(createParams).content().stream()
                .flatMap(contentBlock -> contentBlock.toolUse().stream())
                .forEach(toolUseBlock -> System.out.println(toolUseBlock._input()));
    }
}
