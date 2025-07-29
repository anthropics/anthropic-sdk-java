package com.anthropic.example;

import com.anthropic.bedrock.backends.BedrockBackend;
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.MessageCreateParams;
import software.amazon.awssdk.regions.Region;

/**
 * <p>
 * An example of retrieving messages from an Anthropic model running on the
 * Amazon Bedrock backend using Bearer token authentication (Amazon Bedrock API keys).
 * </p>
 * <p>
 * This example demonstrates the new Bearer token authentication method introduced
 * in AWS SDK Java v2.31.74, which provides a simpler alternative to AWS SigV4
 * signing for Amazon Bedrock API access.
 * </p>
 * <p>
 * To use Bearer token authentication, you need to generate an Amazon Bedrock API key
 * from the AWS console and set it as an environment variable or pass it explicitly
 * to the BedrockBackend builder.
 * </p>
 * <p>
 * To run this example, set the following environment variables:
 * </p>
 * <ul>
 *   <li>{@code AWS_BEARER_TOKEN_BEDROCK}:
 *       The Amazon Bedrock API key for Bearer token authentication.</li>
 *   <li>{@code AWS_REGION} (optional):
 *       The AWS region. If not set, defaults to us-east-1 in this example.</li>
 * </ul>
 * <p>
 * Alternatively, you can set the Bearer token explicitly using the
 * {@code bearerToken()} method on the BedrockBackend builder.
 * </p>
 */
public final class BedrockBearerTokenExample {
    private BedrockBearerTokenExample() {}

    public static void main(String[] args) {
        // Option 1: Use Bearer token from environment variable (AWS_BEARER_TOKEN_BEDROCK)
        // The fromEnv() method will automatically detect and use the Bearer token if available
        AnthropicClient clientFromEnv = AnthropicOkHttpClient.builder()
                .backend(BedrockBackend.fromEnv())
                .build();
        System.out.println("BedrockBackend.fromEnv() invoked which uses AWS_BEARER_TOKEN_BEDROCK first, if not found uses default AWS credentials.");
        invokeClaude(clientFromEnv);

        // Option 2: Set Bearer token explicitly
        String bearerToken = System.getenv("AWS_BEARER_TOKEN_BEDROCK");
        if (bearerToken != null) {
            AnthropicClient clientExplicit = AnthropicOkHttpClient.builder()
                    .backend(BedrockBackend.builder()
                            .bearerToken(bearerToken)
                            .region(Region.US_EAST_1)
                            .build())
                    .build();
            System.out.println("BedrockBackend.builder().bearerToken(bearerToken) invoked which uses AWS_BEARER_TOKEN_BEDROCK explicitly, if not found uses default AWS credentials.");
            invokeClaude(clientExplicit);
        }


    }

    private static void invokeClaude(AnthropicClient clientFromEnv) {
        // Use the client (using the fromEnv() version for this example)
        MessageCreateParams createParams = MessageCreateParams.builder()
                .model("us.anthropic.claude-sonnet-4-20250514-v1:0")
                .maxTokens(2048)
                .addUserMessage("Hello! Can you explain the benefits of using Amazon Bedrock API keys?")
                .build();

        clientFromEnv.messages().create(createParams).content().stream()
                .flatMap(contentBlock -> contentBlock.text().stream())
                .forEach(textBlock -> System.out.println(textBlock.text()));
    }
}
