package com.anthropic.example;

import com.anthropic.bedrock.credentials.BedrockCredentials;
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.MessageCreateParams;
import com.anthropic.models.MessageParam;
import com.anthropic.models.MessageParam.Role;

/**
 * <p>
 * An example of retrieving messages from an Anthropic model running on the
 * Amazon Bedrock platform.
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
public final class BedrockMessagesExample {
    /** Prevent instantiation of this class. */
    private BedrockMessagesExample() {}

    public static void main(String[] args) throws Exception {
        AnthropicClient client = AnthropicOkHttpClient.builder()
                .credentials(BedrockCredentials.fromEnv())
                .build();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model("anthropic.claude-3-sonnet-20240229-v1:0")
                .maxTokens(2048)
                .addMessage(MessageParam.builder()
                        .role(Role.USER)
                        .content("Tell me a story about building the best SDK!")
                        .build())
                .build();

        System.out.println(client.messages().create(createParams));

        client.messages().create(createParams).content().stream()
                .flatMap(contentBlock -> contentBlock.text().stream())
                .forEach(textBlock -> System.out.println(textBlock.text()));
    }
}