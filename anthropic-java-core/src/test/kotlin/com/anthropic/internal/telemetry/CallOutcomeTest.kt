package com.anthropic.internal.telemetry

import com.anthropic.client.AnthropicClient
import com.anthropic.core.JsonValue
import com.anthropic.core.opentelemetry.OpenTelemetryOptions
import com.anthropic.errors.RateLimitException
import com.anthropic.models.beta.messages.MessageCountTokensParams as BetaMessageCountTokensParams
import com.anthropic.models.beta.messages.MessageCreateParams as BetaMessageCreateParams
import com.anthropic.models.messages.MessageCountTokensParams
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.messages.Model
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.findAll
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.api.OpenTelemetry
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.api.trace.SpanKind
import io.opentelemetry.api.trace.StatusCode
import io.opentelemetry.api.trace.Tracer
import io.opentelemetry.api.trace.TracerProvider
import io.opentelemetry.context.Context
import io.opentelemetry.context.propagation.ContextPropagators
import io.opentelemetry.context.propagation.TextMapGetter
import io.opentelemetry.context.propagation.TextMapPropagator
import io.opentelemetry.context.propagation.TextMapSetter
import io.opentelemetry.sdk.trace.ReadWriteSpan
import io.opentelemetry.sdk.trace.ReadableSpan
import io.opentelemetry.sdk.trace.SdkTracerProvider
import io.opentelemetry.sdk.trace.SpanProcessor
import io.opentelemetry.sdk.trace.data.LinkData
import io.opentelemetry.sdk.trace.samplers.Sampler
import io.opentelemetry.sdk.trace.samplers.SamplingResult
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

/** Whatever its spans do, a call returns or throws what it does without them. */
@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class CallOutcomeTest : TracingTestSupport() {

    // A model that is not a string, which the SDK sends as it is for the API to reject.
    private val model = Model.of(JsonValue.from(42))

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun modelThatIsNotAString_isSentAsItIs(emitsSpans: Boolean) {
        stubFor(
            post(urlPathEqualTo("/v1/messages/count_tokens"))
                .willReturn(
                    ok("""{"input_tokens": 12}""").withHeader("Content-Type", "application/json")
                )
        )
        val client = clientBuilder().apply { if (!emitsSpans) openTelemetry(false) }.build()
        val async = client.async()
        val params =
            MessageCreateParams.builder().model(model).maxTokens(100).addUserMessage("hi").build()
        val betaParams =
            BetaMessageCreateParams.builder()
                .model(model)
                .maxTokens(100)
                .addUserMessage("hi")
                .build()
        val countParams =
            MessageCountTokensParams.builder().model(model).addUserMessage("hi").build()
        val betaCountParams =
            BetaMessageCountTokensParams.builder().model(model).addUserMessage("hi").build()

        stubMessage()
        assertThat(client.messages().create(params).id()).isEqualTo("msg_1")
        assertThat(client.beta().messages().create(betaParams).id()).isEqualTo("msg_1")
        assertThat(async.messages().create(params).get(30, TimeUnit.SECONDS).id())
            .isEqualTo("msg_1")
        assertThat(async.beta().messages().create(betaParams).get(30, TimeUnit.SECONDS).id())
            .isEqualTo("msg_1")
        assertThat(client.messages().countTokens(countParams).inputTokens()).isEqualTo(12)
        assertThat(client.beta().messages().countTokens(betaCountParams).inputTokens())
            .isEqualTo(12)
        assertThat(
                async.messages().countTokens(countParams).get(30, TimeUnit.SECONDS).inputTokens()
            )
            .isEqualTo(12)
        assertThat(
                async
                    .beta()
                    .messages()
                    .countTokens(betaCountParams)
                    .get(30, TimeUnit.SECONDS)
                    .inputTokens()
            )
            .isEqualTo(12)
        stubStream()
        client.messages().createStreaming(params).use { it.stream().forEach {} }
        client.beta().messages().createStreaming(betaParams).use { it.stream().forEach {} }
        async
            .messages()
            .createStreaming(params)
            .subscribe {}
            .onCompleteFuture()
            .get(30, TimeUnit.SECONDS)
        async
            .beta()
            .messages()
            .createStreaming(betaParams)
            .subscribe {}
            .onCompleteFuture()
            .get(30, TimeUnit.SECONDS)

        val created = findAll(postRequestedFor(urlPathEqualTo("/v1/messages")))
        val counted = findAll(postRequestedFor(urlPathEqualTo("/v1/messages/count_tokens")))
        assertThat(created).hasSize(8)
        assertThat(counted).hasSize(4)
        assertThat(created + counted).allSatisfy {
            assertThat(it.bodyAsString).contains("\"model\":42")
        }
        if (emitsSpans) {
            assertThat(recorder.await(12)).allSatisfy {
                assertThat(attributesOf(it)).doesNotContainKey("gen_ai.request.model")
            }
        } else {
            assertThat(recorder.spans).isEmpty()
        }
    }

    @ParameterizedTest
    @EnumSource
    fun failingTelemetry_callReturnsItsResponse(failing: FailingTelemetry) {
        val telemetry = ThrowingTelemetry(failing)
        val client = clientWith(telemetry)
        val async = client.async()

        stubMessage()
        assertThat(client.messages().create(messageParams()).id()).isEqualTo("msg_1")
        assertThat(async.messages().create(messageParams()).get(10, TimeUnit.SECONDS).id())
            .isEqualTo("msg_1")

        assertThat(telemetry.thrown.get()).isPositive()
    }

    @ParameterizedTest
    @EnumSource
    fun failingTelemetry_streamIsReadToItsEnd(failing: FailingTelemetry) {
        val telemetry = ThrowingTelemetry(failing)
        val client = clientWith(telemetry)
        val async = client.async()

        stubStream()
        val events = AtomicInteger()
        client.messages().createStreaming(messageParams()).use { response ->
            response.stream().forEach { events.incrementAndGet() }
        }
        async
            .messages()
            .createStreaming(messageParams())
            .subscribe { events.incrementAndGet() }
            .onCompleteFuture()
            .get(10, TimeUnit.SECONDS)

        assertThat(events.get()).isEqualTo(2 * STREAM_EVENTS.size)
        assertThat(telemetry.thrown.get()).isPositive()
    }

    @ParameterizedTest
    @EnumSource
    fun failingTelemetry_callFailsWithTheErrorOfTheApi(failing: FailingTelemetry) {
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .willReturn(
                    aResponse()
                        .withStatus(429)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ERROR_JSON)
                )
        )
        val telemetry = ThrowingTelemetry(failing)
        val client = clientWith(telemetry)

        val future = client.async().messages().create(messageParams())
        val error = assertThrows<ExecutionException> { future.get(10, TimeUnit.SECONDS) }
        assertThrows<RateLimitException> { client.messages().create(messageParams()) }

        assertThat(error.cause).isInstanceOf(RateLimitException::class.java)
        assertThat(telemetry.thrown.get()).isPositive()
    }

    @Test
    fun failingPropagator_callReturnsItsResponse() {
        stubMessage()
        val thrown = AtomicInteger()
        val propagator =
            object : TextMapPropagator {
                override fun fields(): Collection<String> = emptyList()

                override fun <C> inject(context: Context, carrier: C?, setter: TextMapSetter<C>) {
                    thrown.incrementAndGet()
                    error("The propagator failed")
                }

                override fun <C> extract(
                    context: Context,
                    carrier: C?,
                    getter: TextMapGetter<C>,
                ): Context = context
            }
        GlobalOpenTelemetry.resetForTest()
        try {
            GlobalOpenTelemetry.set(
                OpenTelemetry.propagating(ContextPropagators.create(propagator))
            )

            assertThat(client.messages().create(messageParams()).id()).isEqualTo("msg_1")
            assertThat(
                    client.async().messages().create(messageParams()).get(10, TimeUnit.SECONDS).id()
                )
                .isEqualTo("msg_1")
        } finally {
            GlobalOpenTelemetry.resetForTest()
            registerGlobal()
        }

        assertThat(thrown.get()).isPositive()
        assertThat(recorder.await(2)).allSatisfy {
            assertThat(it.status.statusCode).isEqualTo(StatusCode.OK)
        }
    }

    @ParameterizedTest
    @EnumSource
    fun failingTelemetry_toolRunnerRunsItsLoop(failing: FailingTelemetry) {
        stubToolLoop()
        val telemetry = ThrowingTelemetry(failing)
        val client = clientWith(telemetry)

        val messages = client.beta().messages().toolRunner(toolParams()).toList()

        assertThat(messages.map { it.id() }).containsExactly("msg_1", "msg_2")
        val requests = findAll(postRequestedFor(urlPathEqualTo("/v1/messages")))
        assertThat(requests).hasSize(2)
        assertThat(requests.last().bodyAsString).contains("The weather in Paris is foggy")
        assertThat(telemetry.thrown.get()).isPositive()
    }

    @ParameterizedTest
    @EnumSource
    fun failingTelemetry_toolRunnerFailsWithTheErrorOfTheApi(failing: FailingTelemetry) {
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .willReturn(
                    aResponse()
                        .withStatus(429)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ERROR_JSON)
                )
        )
        val telemetry = ThrowingTelemetry(failing)
        val client = clientWith(telemetry)

        assertThrows<RateLimitException> {
            client.beta().messages().toolRunner(toolParams()).toList()
        }

        assertThat(telemetry.thrown.get()).isPositive()
    }

    private fun clientWith(telemetry: ThrowingTelemetry): AnthropicClient =
        clientBuilder()
            .openTelemetry(
                OpenTelemetryOptions.builder().tracerProvider(telemetry.tracerProvider).build()
            )
            .build()

    /** The parts of the application's telemetry that a call runs. */
    internal enum class FailingTelemetry {
        TRACER_PROVIDER,
        SAMPLER,
        SPAN_PROCESSOR_ON_START,
        SPAN_PROCESSOR_ON_END,
    }

    /** The application's telemetry, whose [failing] part throws each time a call runs it. */
    private class ThrowingTelemetry(private val failing: FailingTelemetry) {
        val thrown = AtomicInteger()

        private val sampler =
            object : Sampler {
                override fun shouldSample(
                    parentContext: Context,
                    traceId: String,
                    name: String,
                    spanKind: SpanKind,
                    attributes: Attributes,
                    parentLinks: List<LinkData>,
                ): SamplingResult {
                    runs(FailingTelemetry.SAMPLER)
                    return SamplingResult.recordAndSample()
                }

                override fun getDescription(): String = "fails when it is the failing part"
            }

        private val spanProcessor =
            object : SpanProcessor {
                override fun onStart(parentContext: Context, span: ReadWriteSpan) =
                    runs(FailingTelemetry.SPAN_PROCESSOR_ON_START)

                override fun isStartRequired(): Boolean = true

                override fun onEnd(span: ReadableSpan) =
                    runs(FailingTelemetry.SPAN_PROCESSOR_ON_END)

                override fun isEndRequired(): Boolean = true
            }

        private val sdkTracerProvider =
            SdkTracerProvider.builder().setSampler(sampler).addSpanProcessor(spanProcessor).build()

        val tracerProvider =
            object : TracerProvider {
                override fun get(instrumentationScopeName: String): Tracer {
                    runs(FailingTelemetry.TRACER_PROVIDER)
                    return sdkTracerProvider.get(instrumentationScopeName)
                }

                override fun get(
                    instrumentationScopeName: String,
                    instrumentationScopeVersion: String,
                ): Tracer {
                    runs(FailingTelemetry.TRACER_PROVIDER)
                    return sdkTracerProvider.get(
                        instrumentationScopeName,
                        instrumentationScopeVersion,
                    )
                }
            }

        private fun runs(part: FailingTelemetry) {
            if (part == failing) {
                thrown.incrementAndGet()
                error("The application's telemetry failed")
            }
        }
    }
}
