package com.anthropic.example;

import com.anthropic.bedrock.backends.BedrockBackend;
import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.models.messages.MessageCreateParams;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;

/**
 * <p>
 * An example of retrieving messages from an Anthropic model running on the
 * Amazon Bedrock backend using an asynchronous client.
 * </p>
 * <p>
 * To use the {@link BedrockBackend}, the SDK module for Bedrock must be added
 * to the dependencies. Many of the other examples can be adapted to create a
 * client with the {@code BedrockBackend}. Once a client is created, the rest
 * of the code does not need to be changed (subject to the endpoint being
 * supported). See the SDK documentation for details on dependencies and
 * endpoint support for Bedrock.
 * </p>
 * <p>
 * AWS credentials must be configured to access Amazon Bedrock. This example
 * sets the required credentials explicitly from a combination of environment
 * variables and hard-coded values. Alternatively, the credentials and region
 * can be resolved automatically from other sources. See
 * {@link BedrockMessagesStreamingAsyncExample} for comparison.
 * </p>
 * <p>
 * To run this example, set the following environment variables which will be
 * accessed directly from the code (the region is hard-coded):
 * </p>
 * <ul>
 *   <li>{@code AWS_ACCESS_KEY_ID}:
 *       The AWS access key ID identifying the service user.</li>
 *   <li>{@code AWS_SECRET_ACCESS_KEY}:
 *       The AWS secret access key used to sign the requests to the AWS
 *       service.</li>
 * </ul>
 */
public final class BedrockMessagesAsyncExample {
    private BedrockMessagesAsyncExample() {}

    public static void main(String[] args) throws Exception {
        AwsCredentials awsCredentials =
                AwsBasicCredentials.create(System.getenv("AWS_ACCESS_KEY_ID"), System.getenv("AWS_SECRET_ACCESS_KEY"));

        AnthropicClientAsync client = AnthropicOkHttpClientAsync.builder()
                .backend(BedrockBackend.builder()
                        .awsCredentials(awsCredentials)
                        .region(Region.US_EAST_1)
                        .build())
                .build();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model("anthropic.claude-sonnet-4-20250514-v1:0")
                .maxTokens(2048)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        client.messages()
                .create(createParams)
                .thenAccept(message -> message.content().stream()
                        .flatMap(contentBlock -> contentBlock.text().stream())
                        .forEach(textBlock -> System.out.println(textBlock.text())))
                .join();
    }
}
