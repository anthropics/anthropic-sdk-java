package com.anthropic.example;

import com.anthropic.bedrock.backends.BedrockBackend;
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.MessageCreateParams;

/**
 * <p>
 * An example of retrieving messages from an Anthropic model running on the Amazon Bedrock backend
 * using an API key for authorization.
 * </p>
 * <p>
 * To use the {@link BedrockBackend}, the SDK module for Bedrock must be added to the dependencies.
 * Many of the other examples can be adapted to create a client with the {@code BedrockBackend}.
 * Once a client is created, the rest of the code does not need to be changed (subject to the
 * endpoint being supported). See the SDK documentation for details on dependencies and endpoint
 * support for Bedrock.
 * </p>
 * <p>
 * AWS credentials or an API key must be configured to access Amazon Bedrock. This example resolves
 * the required API key automatically from an environment variable. Alternatively, the API key (or
 * AWS credentials) and region can be provided explicitly. See {@link BedrockMessagesAsyncExample}
 * for comparison.
 * </p>
 * <p>
 * To run this example, set the following environment variables which will be accessed via
 * {@code BedrockBackend.Builder.fromEnv()}:
 * </p>
 * <ul>
 *   <li>{@code AWS_REGION}:
 *       The name of the AWS region hosting the service.</li>
 *   <li>{@code AWS_BEARER_TOKEN_BEDROCK}:
 *       The AWS API key authorizing access to the service.</li>
 * </ul>
 */
public final class BedrockMessagesApiKeyExample {
    private BedrockMessagesApiKeyExample() {}

    public static void main(String[] args) throws Exception {
        AnthropicClient client = AnthropicOkHttpClient.builder()
                .backend(BedrockBackend.builder().fromEnv().build())
                .build();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model("us.anthropic.claude-opus-4-20250514-v1:0")
                .maxTokens(2048)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        client.messages().create(createParams).content().stream()
                .flatMap(contentBlock -> contentBlock.text().stream())
                .forEach(textBlock -> System.out.println(textBlock.text()));
    }
}
