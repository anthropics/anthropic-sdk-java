package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.JsonValue;
import com.anthropic.models.messages.JsonOutputFormat;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;
import com.anthropic.models.messages.OutputConfig;
import java.util.List;
import java.util.Map;

/**
 * An example of structured outputs using the low-level ("raw") API to define the JSON schema for
 * the output format. Compare to {@link StructuredOutputsExample} where the same JSON schema is
 * derived from the structure of Java classes.
 */
public final class StructuredOutputsRawExample {

    private StructuredOutputsRawExample() {}

    public static void main(String[] args) {
        // Configure by setting the `ANTHROPIC_API_KEY` environment variable.
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        JsonOutputFormat.Schema schema = JsonOutputFormat.Schema.builder()
                // The "$schema" property can be omitted.
                .putAdditionalProperty("type", JsonValue.from("object"))
                .putAdditionalProperty(
                        "properties",
                        JsonValue.from(Map.of(
                                "books",
                                Map.of(
                                        "minItems",
                                        1,
                                        "type",
                                        "array",
                                        "items",
                                        Map.of(
                                                "type",
                                                "object",
                                                "properties",
                                                Map.of(
                                                        "author",
                                                                Map.of(
                                                                        "type",
                                                                        "object",
                                                                        "properties",
                                                                        Map.of(
                                                                                "birthYear", Map.of("type", "integer"),
                                                                                "deathYear",
                                                                                        Map.of(
                                                                                                "type",
                                                                                                "string",
                                                                                                "description",
                                                                                                "The year the person"
                                                                                                    + " died, or"
                                                                                                    + " 'present' if"
                                                                                                    + " the person is"
                                                                                                    + " living."),
                                                                                "name",
                                                                                        Map.of(
                                                                                                "type",
                                                                                                "string",
                                                                                                "description",
                                                                                                "The first"
                                                                                                        + " name"
                                                                                                        + " and surname"
                                                                                                        + " of the"
                                                                                                        + " person.")),
                                                                        "required",
                                                                        List.of("birthYear", "deathYear", "name"),
                                                                        "additionalProperties",
                                                                        false),
                                                        "genre", Map.of("type", "string"),
                                                        "publicationYear",
                                                                Map.of(
                                                                        "type",
                                                                        "integer",
                                                                        "description",
                                                                        "The year in which the book was first"
                                                                                + " published. No earlier than"
                                                                                + " 1500."),
                                                        "title", Map.of("type", "string")),
                                                "required",
                                                List.of("author", "genre", "publicationYear", "title"),
                                                "additionalProperties",
                                                false)))))
                .putAdditionalProperty("required", JsonValue.from(List.of("books")))
                .putAdditionalProperty("additionalProperties", JsonValue.from(false))
                .build();

        OutputConfig outputConfig = OutputConfig.builder()
                .format(JsonOutputFormat.builder().schema(schema).build())
                .build();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_SONNET_4_5_20250929)
                .maxTokens(2048)
                .outputConfig(outputConfig)
                .addUserMessage("List some famous late twentieth century novels.")
                .build();

        client.messages().create(createParams).content().stream()
                .flatMap(contentBlock -> contentBlock.text().stream())
                .forEach(textBlock -> System.out.println("JSON output: " + textBlock.text()));
    }
}
