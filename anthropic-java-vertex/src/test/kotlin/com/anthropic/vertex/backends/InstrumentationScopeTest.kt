package com.anthropic.vertex.backends

import com.anthropic.backends.Backend
import com.anthropic.client.AnthropicClientImpl
import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.opentelemetry.OpenTelemetryOptions
import com.anthropic.models.messages.MessageCreateParams
import com.google.auth.oauth2.AccessToken
import com.google.auth.oauth2.GoogleCredentials
import io.opentelemetry.api.trace.Tracer
import io.opentelemetry.api.trace.TracerProvider
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CopyOnWriteArrayList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * The spans of a client of a Vertex AI backend have the SDK's instrumentation scope with `vertex`
 * after it.
 */
internal class InstrumentationScopeTest {

    private class Stopped : RuntimeException()

    /** Fails each call before the request is sent. */
    private object StoppingHttpClient : HttpClient {
        override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse =
            throw Stopped()

        override fun executeAsync(
            request: HttpRequest,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> = throw Stopped()

        override fun close() {}
    }

    /** Keeps the instrumentation scope names of the tracers that the client asks for. */
    private class ScopeRecorder : TracerProvider {
        val scopeNames = CopyOnWriteArrayList<String>()

        override fun get(instrumentationScopeName: String): Tracer {
            scopeNames += instrumentationScopeName
            return TracerProvider.noop().get(instrumentationScopeName)
        }

        override fun get(instrumentationScopeName: String, version: String): Tracer {
            scopeNames += instrumentationScopeName
            return TracerProvider.noop().get(instrumentationScopeName, version)
        }
    }

    /**
     * The scope names of the spans of a Messages call by a client that has the options that a
     * client builder takes from [backend].
     */
    private fun scopeNames(backend: Backend): List<String> {
        val recorder = ScopeRecorder()
        val clientOptions =
            ClientOptions.builder()
                .httpClient(StoppingHttpClient)
                .baseUrl(backend.baseUrl())
                .openTelemetryPlatform(backend.openTelemetryPlatform())
                .maxRetries(0)
                .openTelemetry(OpenTelemetryOptions.builder().tracerProvider(recorder).build())
                .build()
        try {
            AnthropicClientImpl(clientOptions)
                .messages()
                .create(
                    MessageCreateParams.builder()
                        .model("claude-sonnet-4-5")
                        .maxTokens(1)
                        .addUserMessage("hello")
                        .build()
                )
        } catch (e: Stopped) {
            // The call asked for its tracer, which is all that the test needs.
        }
        return recorder.scopeNames.distinct()
    }

    @Test
    fun vertexClient_hasTheVertexScope() {
        val backend =
            VertexBackend.builder()
                .googleCredentials(
                    GoogleCredentials.create(
                        AccessToken.newBuilder().setTokenValue("not-a-real-token").build()
                    )
                )
                .region("us-central1")
                .project("vertex-project-12345-b6")
                .build()

        assertThat(scopeNames(backend)).containsExactly("com.anthropic.sdk.java.vertex")
    }
}
