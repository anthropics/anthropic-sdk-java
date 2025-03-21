package com.anthropic.example;

import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.models.beta.messages.MessageCreateParams;
import com.anthropic.vertex.backends.VertexBackend;

/**
 * <p>
 * An example of retrieving a stream of "beta" messages asynchronously from an
 * Anthropic model running on the Google Cloud Vertex AI backend.
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
 * Google OAuth2 credentials must be configured to access Vertex AI. This
 * example resolves the application default credentials (ADC), which may be
 * configured using the Google Cloud CLI. See Google Cloud documentation for
 * details. Alternatively, the Google credentials, region and project can be
 * set explicitly from other sources. See {@link VertexMessagesExample} for
 * comparison.
 * </p>
 * <p>
 * In addition to the ADC configuration, the following environment variables are
 * expected to be defined:
 * </p>
 * <ul>
 *   <li>{@code CLOUD_ML_REGION}:
 *       The name of the Google Cloud region hosting the service.</li>
 *   <li>{@code ANTHROPIC_VERTEX_PROJECT_ID}:
 *       The ID of the Google Cloud Vertex AI project.</li>
 * </ul>
 */
public final class VertexBetaMessagesStreamingAsyncExample {
    private VertexBetaMessagesStreamingAsyncExample() {}

    public static void main(String[] args) {
        AnthropicClientAsync client = AnthropicOkHttpClientAsync.builder()
                .backend(VertexBackend.fromEnv())
                .build();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model("claude-3-sonnet")
                .maxTokens(2048)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        client.beta()
                .messages()
                .createStreaming(createParams)
                .subscribe(event -> event.contentBlockDelta().stream()
                        .flatMap(deltaEvent -> deltaEvent.delta().betaText().stream())
                        .forEach(textDelta -> System.out.print(textDelta.text())))
                .onCompleteFuture()
                .join();
    }
}
