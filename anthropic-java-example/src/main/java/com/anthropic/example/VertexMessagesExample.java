package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.MessageCreateParams;
import com.anthropic.vertex.backends.VertexBackend;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;

/**
 * <p>
 * An example of retrieving messages from an Anthropic model running on the
 * Google Cloud Vertex AI backend.
 * </p>
 * <p>
 * To use the {@link VertexBackend}, the SDK module for Vertex must be added
 * to the dependencies. Many of the other examples can be adapted to create
 * a client with the {@code VertexBackend} (subject to the endpoint being
 * supported). Once a client is created, the rest of the code is the same. See
 * the SDK documentation for details on dependencies and endpoint support for
 * Vertex AI.
 * </p>
 * <p>
 * Google OAuth2 credentials must be configured to access Vertex AI. In this
 * example, the OAuth2 _access token_ is retrieved from an environment variable
 * along with the project ID. The region is hard-coded. Alternatively, the
 * Google credentials, region and project can be resolved automatically. See
 * {@link VertexBetaMessagesStreamingAsyncExample} for comparison.
 * </p>
 * <p>
 * The following environment variables are expected to be defined:
 * </p>
 * <ul>
 *   <li>{@code GOOGLE_APPLICATION_CREDENTIALS}:
 *       The value of the access token for Google Cloud Vertex AI.</li>
 *   <li>{@code ANTHROPIC_VERTEX_PROJECT_ID}:
 *       The ID of the Google Cloud Vertex AI project.</li>
 * </ul>
 */
public final class VertexMessagesExample {
    private VertexMessagesExample() {}

    public static void main(String[] args) throws Exception {
        String accessToken = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
        String project = System.getenv("ANTHROPIC_VERTEX_PROJECT_ID");

        GoogleCredentials googleCredentials = GoogleCredentials.create(
                AccessToken.newBuilder().setTokenValue(accessToken).build());

        AnthropicClient client = AnthropicOkHttpClient.builder()
                .backend(VertexBackend.builder()
                        .googleCredentials(googleCredentials)
                        .region("us-central1")
                        .project(project)
                        .build())
                .build();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model("claude-3-sonnet")
                .maxTokens(2048)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        client.messages().create(createParams).content().stream()
                .flatMap(contentBlock -> contentBlock.text().stream())
                .forEach(textBlock -> System.out.println(textBlock.text()));
    }
}
