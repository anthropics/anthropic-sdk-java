package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.googlecloud.backends.GoogleCloudBackend;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;

/**
 * <p>
 * An example of retrieving messages from an Anthropic model via the Google
 * Cloud API gateway backend.
 * </p>
 * <p>
 * To use the {@link GoogleCloudBackend}, the SDK module for Google Cloud must
 * be added to the dependencies. Many of the other examples can be adapted to
 * create a client with the {@code GoogleCloudBackend}. Once a client is
 * created, the rest of the code is the same.
 * </p>
 * <p>
 * Google OAuth2 credentials must be configured to access the gateway. In this
 * example, application default credentials and configuration are resolved
 * automatically from the environment. See the SDK documentation for details on
 * configuring credentials explicitly.
 * </p>
 * <p>
 * The following environment variables are expected to be defined:
 * </p>
 * <ul>
 *   <li>{@code ANTHROPIC_GOOGLE_CLOUD_PROJECT}:
 *       The Google Cloud project ID (or {@code GOOGLE_CLOUD_PROJECT}).</li>
 *   <li>{@code ANTHROPIC_GOOGLE_CLOUD_LOCATION}:
 *       The Google Cloud location.</li>
 *   <li>{@code ANTHROPIC_GOOGLE_CLOUD_WORKSPACE_ID}:
 *       The Anthropic workspace ID.</li>
 * </ul>
 */
public final class GoogleCloudMessagesExample {
    private GoogleCloudMessagesExample() {}

    public static void main(String[] args) {
        AnthropicClient client = AnthropicOkHttpClient.builder()
                .backend(GoogleCloudBackend.fromEnv())
                .build();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_SONNET_4_5)
                .maxTokens(1024)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        client.messages().create(createParams).content().stream()
                .flatMap(contentBlock -> contentBlock.text().stream())
                .forEach(textBlock -> System.out.println(textBlock.text()));
    }
}
