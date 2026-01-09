package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.foundry.backends.FoundryBackend;
import com.anthropic.models.messages.MessageCreateParams;

/**
 * <p>
 * An example of retrieving messages from an Anthropic model running on the Microsoft Foundry
 * backend using an API key for authorization.
 * </p>
 * <p>
 * To use the {@link FoundryBackend}, the SDK module for Foundry must be added to the dependencies.
 * Many of the other examples can be adapted to create a client with the {@code FoundryBackend}.
 * Once a client is created, the rest of the code does not need to be changed (subject to the
 * endpoint being supported). See the SDK documentation for details on dependencies and endpoint
 * support for Foundry.
 * </p>
 * <p>
 * An Entra ID token supplier or an API key must be configured to access Microsoft Foundry. This
 * example resolves the required API key automatically from an environment variable. Alternatively,
 * a bearer token supplier and resource (or base URL) can be provided explicitly. See
 * {@link FoundryMessagesBearerTokenExample} for comparison.
 * </p>
 * <p>
 * To run this example, set the following environment variables which will be accessed via
 * {@code FoundryBackend.fromEnv()}:
 * </p>
 * <ul>
 *   <li>{@code ANTHROPIC_FOUNDRY_API_KEY}:
 *       The API key authorizing access to the service.</li>
 *   <li>{@code ANTHROPIC_FOUNDRY_RESOURCE}:
 *       The name of the Foundry resource hosting the service.</li>
 * </ul>
 */
public final class FoundryMessagesApiKeyExample {
    private FoundryMessagesApiKeyExample() {}

    public static void main(String[] args) throws Exception {
        AnthropicClient client = AnthropicOkHttpClient.builder()
                .backend(FoundryBackend.fromEnv())
                .build();

        MessageCreateParams createParams = MessageCreateParams.builder()
                // For Foundry, the `model` name must be set to the name of the Magma deployment,
                // which defaults to the name of the deployed Anthropic model, but the deployment
                // name may be changed to an arbitrary value, such as that shown here.
                .model("claude-sonnet-4-5")
                .maxTokens(2048)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        client.messages().create(createParams).content().stream()
                .flatMap(contentBlock -> contentBlock.text().stream())
                .forEach(textBlock -> System.out.println(textBlock.text()));
    }
}
