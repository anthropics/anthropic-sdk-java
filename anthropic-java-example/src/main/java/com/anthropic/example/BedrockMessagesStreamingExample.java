package com.anthropic.example;

import com.anthropic.bedrock.backends.BedrockBackend;
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.http.StreamResponse;
import com.anthropic.models.MessageCreateParams;
import com.anthropic.models.RawMessageStreamEvent;

/**
 * <p>
 * An example of retrieving messages in a streaming response from an Anthropic
 * model running on the Amazon Bedrock backend.
 * </p>
 * <p>
 * AWS credentials must be configured to access Amazon Bedrock. This example
 * resolves the required credentials from the environment. The credentials can
 * be in system properties, environment variables, AWS CLI configuration files,
 * an AWS SSO configuration, etc. See AWS documentation for details.
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
public final class BedrockMessagesStreamingExample {
    /** Prevent instantiation of this class. */
    private BedrockMessagesStreamingExample() {}

    public static void main(String[] args) throws Exception {
        AnthropicClient client = AnthropicOkHttpClient.builder()
                .backend(BedrockBackend.fromEnv())
                .build();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model("anthropic.claude-3-sonnet-20240229-v1:0")
                .maxTokens(2048)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        try (StreamResponse<RawMessageStreamEvent> streamResponse =
                client.messages().createStreaming(createParams)) {
            streamResponse.stream()
                    .flatMap(event -> event.contentBlockDelta().stream())
                    .flatMap(deltaEvent -> deltaEvent.delta().text().stream())
                    .forEach(textDelta -> System.out.print(textDelta.text()));
        }
    }
}
