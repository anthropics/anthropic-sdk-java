package com.anthropic.example;

import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.core.http.AsyncStreamResponse;
import com.anthropic.models.MessageCreateParams;
import com.anthropic.models.RawMessageStreamEvent;
import com.anthropic.vertex.backends.VertexBackend;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * An example of retrieving a stream of messages asynchronously from an
 * Anthropic model running on the Google Cloud Vertex AI backend.
 * </p>
 * <p>
 * Google OAuth2 credentials must be configured to access Vertex AI. This
 * example shows that the application default credentials (ADC) do not need to
 * be used; any valid Google credentials that are available can be used. See
 * Google Cloud documentation for details.
 * </p>
 * <p>
 * For this example, the OAuth2 _access token_ is retrieved from an environment
 * variable along with the project ID. The region is hard-coded. The following
 * environment variables are expected to be defined:
 * </p>
 * <ul>
 *   <li>{@code GOOGLE_APPLICATION_CREDENTIALS}:
 *       The access token required to authorize requests to Vertex AI.</li>
 *   <li>{@code ANTHROPIC_VERTEX_PROJECT_ID}:
 *       The ID of the Google Cloud Vertex AI project.</li>
 * </ul>
 * <p>
 * Alternatively, the Google credentials, region and project can be set
 * automatically from the ADC configuration and environment variables. See
 * {@link VertexMessagesExample} for comparison.
 * </p>
 */
public final class VertexMessagesStreamingAsyncExample {
    private VertexMessagesStreamingAsyncExample() {}

    public static void main(String[] args) {
        String accessToken = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
        String project = System.getenv("ANTHROPIC_VERTEX_PROJECT_ID");

        GoogleCredentials googleCredentials = GoogleCredentials.create(
                AccessToken.newBuilder().setTokenValue(accessToken).build());

        AnthropicClientAsync client = AnthropicOkHttpClientAsync.builder()
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

        CompletableFuture<Void> onCompleteFuture = new CompletableFuture<>();

        // TODO: Update this example once we support expose an `onCompleteFuture()` method.
        client.messages().createStreaming(createParams).subscribe(new AsyncStreamResponse.Handler<>() {
            @Override
            public void onNext(RawMessageStreamEvent event) {
                event.contentBlockDelta().stream()
                        .flatMap(deltaEvent -> deltaEvent.delta().text().stream())
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
