package com.anthropic.bedrock.backends

import com.anthropic.backends.Backend
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.opentelemetry.OpenTelemetryOptions
import com.anthropic.models.messages.MessageCreateParams
import io.opentelemetry.api.trace.Tracer
import io.opentelemetry.api.trace.TracerProvider
import java.util.concurrent.CopyOnWriteArrayList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.regions.Region

/**
 * The spans of a client of a Bedrock backend have the SDK's instrumentation scope with `bedrock`
 * after it.
 */
internal class InstrumentationScopeTest {

    private class Stopped : RuntimeException()

    /** Fails each call of a client of [backend] before the request is sent. */
    private class StoppingBackend(backend: Backend) : Backend by backend {
        override fun authorizeRequest(request: HttpRequest): HttpRequest = throw Stopped()
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

    /** The scope names of the spans of a Messages call by a client of [backend]. */
    private fun scopeNames(backend: Backend): List<String> {
        val recorder = ScopeRecorder()
        val client =
            AnthropicOkHttpClient.builder()
                .backend(StoppingBackend(backend))
                .maxRetries(0)
                .openTelemetry(OpenTelemetryOptions.builder().tracerProvider(recorder).build())
                .build()
        try {
            client
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
        } finally {
            client.close()
        }
        return recorder.scopeNames.distinct()
    }

    private val credentials =
        AwsBasicCredentials.create(
            "AKIAIOSFODNN7EXAMPLE",
            "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY",
        )

    @Test
    fun bedrockClient_hasTheBedrockScope() {
        val backend =
            BedrockBackend.builder().awsCredentials(credentials).region(Region.US_EAST_1).build()

        assertThat(scopeNames(backend)).containsExactly("com.anthropic.sdk.java.bedrock")
    }

    @Test
    fun bedrockMantleClient_hasTheBedrockScope() {
        val backend =
            BedrockMantleBackend.builder()
                .awsCredentials(credentials)
                .region(Region.US_EAST_1)
                .build()

        assertThat(scopeNames(backend)).containsExactly("com.anthropic.sdk.java.bedrock")
    }
}
