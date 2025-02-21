package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.MessageCreateParams;
import com.anthropic.vertex.backends.VertexBackend;

/**
 * <p>
 * An example of retrieving messages from an Anthropic model running on the
 * Google Cloud Vertex AI backend.
 * </p>
 * <p>
 * Google OAuth2 credentials must be configured to access Vertex AI. This
 * example resolves the application default credentials (ADC), which may be
 * configured using the Google Cloud CLI. See Google Cloud documentation for
 * details.
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
 * <p>
 * Alternatively, the Google credentials, region and project can be set manually
 * from other sources. See {@link VertexMessagesStreamingAsyncExample} for
 * comparison.
 * </p>
 */
public final class VertexMessagesExample {
    private VertexMessagesExample() {}

    public static void main(String[] args) throws Exception {
        AnthropicClient client = AnthropicOkHttpClient.builder()
                .backend(VertexBackend.fromEnv())
                .build();

        MessageCreateParams createParams = MessageCreateParams.builder()
                .model("claude-3-sonnet")
                .maxTokens(2048)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        System.out.println(client.messages().create(createParams));

        client.messages().create(createParams).content().stream()
                .flatMap(contentBlock -> contentBlock.text().stream())
                .forEach(textBlock -> System.out.println(textBlock.text()));
    }
}
