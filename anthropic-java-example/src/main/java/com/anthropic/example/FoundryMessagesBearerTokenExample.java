package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.foundry.backends.FoundryBackend;
import com.anthropic.models.messages.MessageCreateParams;
import com.azure.identity.AuthenticationUtil;
import com.azure.identity.DefaultAzureCredentialBuilder;
import java.util.function.Supplier;

/**
 * <p>
 * An example of retrieving messages from an Anthropic model running on the Microsoft Foundry
 * backend using Microsoft Entra ID authorization.
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
 * example sets a supplier for authorization bearer tokens using Entra ID. The credentials are
 * configured via environment variables. Alternatively, an API key and resource (or base URL) can be
 * configured in the environment variables and resolved automatically. See
 * {@link FoundryMessagesApiKeyExample} for comparison.
 * </p>
 * <p>
 * To run this example, set the following environment variables which will be accessed by the bearer
 * token supplier (the resource name is hard-coded):
 * </p>
 * <ul>
 *   <li>{@code AZURE_CLIENT_ID}: The Entra ID client ID.</li>
 *   <li>{@code AZURE_TENANT_ID}: The Entra ID tenant ID.</li>
 *   <li>{@code AZURE_CLIENT_SECRET}: The Entra ID secret key value.</li>
 * </ul>
 * <p>
 * Alternatively, configure these values via any other means supported by the Azure Identity
 * {@code DefaultAzureCredentialBuilder}.
 * </p>
 */
public final class FoundryMessagesBearerTokenExample {
    private FoundryMessagesBearerTokenExample() {}

    public static void main(String[] args) throws Exception {
        Supplier<String> bearerTokenSupplier = AuthenticationUtil.getBearerTokenSupplier(
                new DefaultAzureCredentialBuilder().build(), "https://cognitiveservices.azure.com/.default");

        AnthropicClient client = AnthropicOkHttpClient.builder()
                .backend(FoundryBackend.builder()
                        .bearerTokenSupplier(bearerTokenSupplier)
                        .resource("my-foundry-resource")
                        .build())
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
