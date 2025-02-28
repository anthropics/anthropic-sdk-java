package com.anthropic.example;

import com.anthropic.bedrock.backends.BedrockBackend;
import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.models.MessageCreateParams;

/**
 * <p>
 * An example of retrieving messages asynchronously in a streaming response from
 * an Anthropic model running on the Amazon Bedrock backend.
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
 * resolves the required credentials from the environment. The credentials can
 * be in system properties, environment variables, AWS CLI configuration files,
 * an AWS SSO configuration, etc. See AWS documentation for details.
 * Alternatively, the credentials and region can be set explicitly from other
 * sources. See {@link BedrockMessagesAsyncExample} for comparison.
 * </p>
 * <p>
 * With an Anthropic model deployed on a Bedrock service, it may be sufficient
 * to set the following environment variables to run this example:
 * </p>
 * <ul>
 *   <li>{@code AWS_REGION}:
 *       The name of the AWS region hosting the service.</li>
 *   <li>{@code AWS_ACCESS_KEY_ID}:
 *       The AWS access key ID identifying the service user.</li>
 *   <li>{@code AWS_SECRET_ACCESS_KEY}:
 *       The AWS secret access key used to sign the requests to the AWS
 *       service.</li>
 * </ul>
 */
public final class BedrockMessagesStreamingAsyncExample {
    private BedrockMessagesStreamingAsyncExample() {}

    public static void main(String[] args) {
        AnthropicClientAsync client = AnthropicOkHttpClientAsync.builder()
                .backend(BedrockBackend.fromEnv())
                .build();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model("anthropic.claude-3-sonnet-20240229-v1:0")
                .maxTokens(2048)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        client.messages()
                .createStreaming(createParams)
                .subscribe(event -> event.contentBlockDelta().stream()
                        .flatMap(deltaEvent -> deltaEvent.delta().text().stream())
                        .forEach(textDelta -> System.out.print(textDelta.text())))
                .onCompleteFuture()
                .join();
    }
}
