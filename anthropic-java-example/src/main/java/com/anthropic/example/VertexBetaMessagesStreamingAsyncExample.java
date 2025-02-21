package com.anthropic.example;

import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.core.http.AsyncStreamResponse;
import com.anthropic.models.BetaMessageCreateParams;
import com.anthropic.models.BetaRawMessageStreamEvent;
import com.anthropic.vertex.backends.VertexBackend;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * An example of retrieving a stream of "beta" messages asynchronously from an
 * Anthropic model running on the Google Cloud Vertex AI backend.
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
public final class VertexBetaMessagesStreamingAsyncExample {
    private VertexBetaMessagesStreamingAsyncExample() {}

    public static void main(String[] args) {
        AnthropicClientAsync client = AnthropicOkHttpClientAsync.builder()
                .backend(VertexBackend.fromEnv())
                .build();

        BetaMessageCreateParams createParams = BetaMessageCreateParams.builder()
                .model("claude-3-sonnet")
                .maxTokens(2048)
                .addUserMessage("Tell me a story about building the best SDK!")
                .build();

        CompletableFuture<Void> onCompleteFuture = new CompletableFuture<>();

        // TODO: Update this example once we support expose an `onCompleteFuture()` method.
        client.beta().messages().createStreaming(createParams)
                .subscribe(new AsyncStreamResponse.Handler<>() {
                    @Override
                    public void onNext(BetaRawMessageStreamEvent event) {
                        event.contentBlockDelta().stream()
                                .flatMap(deltaEvent -> deltaEvent.delta().betaText().stream())
                                .forEach(textDelta -> System.out.print(textDelta.text()));
                    }

                    @Override
                    public void onComplete(Optional<Throwable> error) {
                        onCompleteFuture.complete(null);
                    }
                });

        onCompleteFuture.join();
    }
}
