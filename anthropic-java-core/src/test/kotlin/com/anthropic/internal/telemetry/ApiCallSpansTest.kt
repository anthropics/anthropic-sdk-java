package com.anthropic.internal.telemetry

import com.anthropic.backends.Backend
import com.anthropic.client.AnthropicClient
import com.anthropic.client.AnthropicClientImpl
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.ClientOptions
import com.anthropic.core.JsonValue
import com.anthropic.core.RequestOptions
import com.anthropic.core.getPackageVersion
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.parseable
import com.anthropic.core.opentelemetry.ApiCall
import com.anthropic.core.opentelemetry.OpenTelemetryContentMode
import com.anthropic.core.opentelemetry.OpenTelemetryOptions
import com.anthropic.core.opentelemetry.OpenTelemetryRequestOptions
import com.anthropic.core.opentelemetry.OpenTelemetryTraces
import com.anthropic.core.opentelemetry.traceApiCall
import com.anthropic.errors.AnthropicException
import com.anthropic.errors.AnthropicIoException
import com.anthropic.errors.InternalServerException
import com.anthropic.errors.RateLimitException
import com.anthropic.helpers.FakeHttpClient
import com.anthropic.models.beta.messages.BetaJsonOutputFormat
import com.anthropic.models.beta.messages.BetaOutputConfig
import com.anthropic.models.beta.messages.MessageCountTokensParams as BetaMessageCountTokensParams
import com.anthropic.models.beta.messages.MessageCreateParams as BetaMessageCreateParams
import com.anthropic.models.beta.messages.batches.BatchCreateParams as BetaBatchCreateParams
import com.anthropic.models.messages.JsonOutputFormat
import com.anthropic.models.messages.MessageCountTokensParams
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.messages.OutputConfig
import com.anthropic.models.messages.batches.BatchCreateParams
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.delete
import com.github.tomakehurst.wiremock.client.WireMock.findAll
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.github.tomakehurst.wiremock.stubbing.Scenario
import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.api.OpenTelemetry
import io.opentelemetry.api.baggage.Baggage
import io.opentelemetry.api.baggage.propagation.W3CBaggagePropagator
import io.opentelemetry.api.common.AttributeKey
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.api.trace.Span
import io.opentelemetry.api.trace.SpanContext
import io.opentelemetry.api.trace.SpanKind
import io.opentelemetry.api.trace.StatusCode
import io.opentelemetry.api.trace.TraceFlags
import io.opentelemetry.api.trace.TraceState
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator
import io.opentelemetry.context.Context
import io.opentelemetry.context.propagation.ContextPropagators
import io.opentelemetry.context.propagation.TextMapGetter
import io.opentelemetry.context.propagation.TextMapPropagator
import io.opentelemetry.context.propagation.TextMapSetter
import io.opentelemetry.sdk.trace.SdkTracerProvider
import io.opentelemetry.sdk.trace.data.LinkData
import io.opentelemetry.sdk.trace.samplers.Sampler
import io.opentelemetry.sdk.trace.samplers.SamplingResult
import java.util.Optional
import java.util.concurrent.CancellationException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.api.parallel.Resources
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullSource
import org.junit.jupiter.params.provider.ValueSource

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class ApiCallSpansTest : TracingTestSupport() {

    @Test
    fun messagesCreate_recordsSpan() {
        stubMessage()
        val tracer = recorder.tracerProvider.get("app")
        val parent = tracer.spanBuilder("handle_order").startSpan()
        val traceState = TraceState.builder().put("vendor", "value").build()
        val parentContext =
            Context.root()
                .with(
                    Span.wrap(
                        SpanContext.create(
                            parent.spanContext.traceId,
                            parent.spanContext.spanId,
                            TraceFlags.getSampled(),
                            traceState,
                        )
                    )
                )

        parentContext.makeCurrent().use { client.messages().create(messageParams()) }
        parent.end()

        val span = recorder.named("anthropic.messages.create")
        assertThat(span.kind).isEqualTo(SpanKind.CLIENT)
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.hasEnded()).isTrue()
        assertThat(span.parentSpanId).isEqualTo(parent.spanContext.spanId)
        assertThat(span.instrumentationScopeInfo.name).isEqualTo("com.anthropic.sdk.java")
        assertThat(span.instrumentationScopeInfo.version).isEqualTo(getPackageVersion())
        assertThat(span.string("gen_ai.operation.name")).isEqualTo("chat")
        assertThat(span.string("gen_ai.provider.name")).isEqualTo("anthropic")
        assertThat(span.string("gen_ai.request.model")).isEqualTo("claude-requested")
        assertThat(span.string("gen_ai.response.model")).isEqualTo("claude-served")
        assertThat(span.string("gen_ai.response.id")).isEqualTo("msg_1")
        assertThat(span.long("gen_ai.request.max_tokens")).isEqualTo(100)
        assertThat(span.double("gen_ai.request.temperature")).isEqualTo(0.5)
        assertThat(span.long("gen_ai.request.top_k")).isEqualTo(3)
        assertThat(span.double("gen_ai.request.top_p")).isNull()
        assertThat(attributesOf(span)).doesNotContainKey("gen_ai.request.stream")
        assertThat(attributesOf(span)).doesNotContainKey("gen_ai.output.type")
        assertThat(span.string("anthropic.message.stop_reason")).isEqualTo("end_turn")
        assertThat(attributesOf(span)).doesNotContainKey("gen_ai.response.finish_reasons")
        assertThat(span.long("gen_ai.usage.input_tokens")).isEqualTo(15)
        assertThat(span.long("gen_ai.usage.output_tokens")).isEqualTo(5)
        assertThat(span.long("gen_ai.usage.cache_write.input_tokens")).isEqualTo(3)
        assertThat(span.long("gen_ai.usage.cache_read.input_tokens")).isEqualTo(2)
        assertThat(span.long("anthropic.usage.cache_creation.ephemeral_5m_input_tokens"))
            .isEqualTo(1)
        assertThat(span.long("anthropic.usage.cache_creation.ephemeral_1h_input_tokens"))
            .isEqualTo(2)
        assertThat(span.string("anthropic.response.service_tier")).isEqualTo("standard")
        assertThat(span.strings("anthropic.beta")).containsExactly("beta-one", "beta-two")
        assertThat(span.long("http.response.status_code")).isEqualTo(200)
        assertThat(span.string("anthropic.request.id")).isEqualTo("req_1")
        assertThat(span.string("anthropic.organization.id")).isEqualTo("org_1")
        assertThat(span.string("anthropic.workspace.id")).isEqualTo("wrkspc_1")
        assertThat(span.string("server.address")).isEqualTo("localhost")
        assertThat(span.long("server.port")).isEqualTo(baseUrl.substringAfterLast(':').toLong())
        assertThat(span.string("http.request.method")).isEqualTo("POST")
        assertThat(span.string("http.route")).isEqualTo("/v1/messages")
        assertThat(span.string("url.scheme")).isEqualTo("http")
        assertThat(span.string("url.path")).isEqualTo("/v1/messages")
        assertThat(span.string("url.full")).isEqualTo("$baseUrl/v1/messages")
        assertThat(attributesOf(span))
            .doesNotContainKeys(
                "url.query",
                "http.request.resend_count",
                "network.peer.address",
                "network.peer.port",
                "network.protocol.version",
                "error.type",
            )

        // The request carries the call span's context, with the trace state of its parent.
        val request = findAll(postRequestedFor(urlPathEqualTo("/v1/messages"))).single()
        assertThat(request.getHeader("traceparent"))
            .startsWith("00-${span.traceId}-${span.spanId}-")
        assertThat(request.getHeader("tracestate")).isEqualTo("vendor=value")
    }

    @Test
    fun messagesCreate_recordsTheRequestAttributesOfTheStableAndTheBetaParams() {
        stubMessage()
        // The client's beta header applies to params that have none of their own.
        val client = clientBuilder().putHeader("anthropic-beta", "client-beta").build()

        client
            .messages()
            .create(
                MessageCreateParams.builder()
                    .model("claude-requested")
                    .maxTokens(2048)
                    .topP(0.9)
                    .enabledThinking(1024)
                    .serviceTier(MessageCreateParams.ServiceTier.AUTO)
                    .outputConfig(OutputConfig.builder().effort(OutputConfig.Effort.HIGH).build())
                    .addUserMessage("hello")
                    .build()
            )
        client
            .beta()
            .messages()
            .create(
                BetaMessageCreateParams.builder()
                    .model("claude-requested")
                    .maxTokens(2048)
                    .topP(0.9)
                    .enabledThinking(1024)
                    .serviceTier(BetaMessageCreateParams.ServiceTier.AUTO)
                    .outputConfig(
                        BetaOutputConfig.builder().effort(BetaOutputConfig.Effort.HIGH).build()
                    )
                    .addBeta("params-beta")
                    .addUserMessage("hello")
                    .build()
            )

        val (stable, beta) = recorder.spans
        assertThat(listOf(stable, beta)).allSatisfy { span ->
            assertThat(span.string("gen_ai.request.model")).isEqualTo("claude-requested")
            assertThat(span.long("gen_ai.request.max_tokens")).isEqualTo(2048)
            assertThat(span.double("gen_ai.request.top_p")).isEqualTo(0.9)
            assertThat(span.double("gen_ai.request.temperature")).isNull()
            assertThat(span.long("gen_ai.request.top_k")).isNull()
            assertThat(span.string("gen_ai.request.reasoning.level")).isEqualTo("high")
            assertThat(span.string("anthropic.request.service_tier")).isEqualTo("auto")
            assertThat(span.string("anthropic.thinking.type")).isEqualTo("enabled")
            assertThat(span.long("anthropic.thinking.budget_tokens")).isEqualTo(1024)
        }
        assertThat(stable.strings("anthropic.beta")).containsExactly("client-beta")
        assertThat(beta.strings("anthropic.beta")).containsExactly("params-beta")
    }

    @Test
    fun messagesCreate_withAnOutputFormat_recordsTheOutputType() {
        stubMessage()
        val schema = mapOf("type" to JsonValue.from("object"))

        // In `output_config.format`.
        client
            .messages()
            .create(
                messageParams()
                    .toBuilder()
                    .outputConfig(
                        OutputConfig.builder()
                            .format(
                                JsonOutputFormat.builder()
                                    .schema(
                                        JsonOutputFormat.Schema.builder()
                                            .additionalProperties(schema)
                                            .build()
                                    )
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
        // In the deprecated `output_format`, which only the beta API has.
        client
            .beta()
            .messages()
            .create(
                betaMessageParams()
                    .toBuilder()
                    .outputFormat(
                        BetaJsonOutputFormat.builder()
                            .schema(
                                BetaJsonOutputFormat.Schema.builder()
                                    .additionalProperties(schema)
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
        // As the structured output helper sets it, from a class.
        client
            .messages()
            .create(messageParams().toBuilder().outputConfig(Forecast::class.java).build())

        assertThat(recorder.spans).hasSize(3).allSatisfy { span ->
            assertThat(span.name).isEqualTo("anthropic.messages.create")
            assertThat(span.string("gen_ai.output.type")).isEqualTo("json")
        }
    }

    @Test
    fun messagesCreate_withAnOutputFormatThatIsNotJson_recordsNoOutputType() {
        stubMessage()
        val schema = mapOf("type" to JsonValue.from("object"))
        val format =
            JsonOutputFormat.builder()
                .schema(JsonOutputFormat.Schema.builder().additionalProperties(schema).build())
                .type(JsonValue.from("xml_schema"))
                .build()
        val betaFormat =
            BetaJsonOutputFormat.builder()
                .schema(BetaJsonOutputFormat.Schema.builder().additionalProperties(schema).build())
                .type(JsonValue.from("xml_schema"))
                .build()

        client
            .messages()
            .create(
                messageParams()
                    .toBuilder()
                    .outputConfig(OutputConfig.builder().format(format).build())
                    .build()
            )
        client
            .beta()
            .messages()
            .create(
                betaMessageParams()
                    .toBuilder()
                    .outputConfig(BetaOutputConfig.builder().format(betaFormat).build())
                    .build()
            )
        client
            .beta()
            .messages()
            .create(betaMessageParams().toBuilder().outputFormat(betaFormat).build())

        assertThat(recorder.spans).hasSize(3).allSatisfy { span ->
            assertThat(span.long("gen_ai.request.max_tokens")).isEqualTo(100)
            assertThat(attributesOf(span)).doesNotContainKey("gen_ai.output.type")
        }
    }

    @Test
    fun messagesCreate_withAnOutputConfigWithoutAFormat_recordsNoOutputType() {
        stubMessage()

        client
            .messages()
            .create(
                messageParams()
                    .toBuilder()
                    .outputConfig(OutputConfig.builder().effort(OutputConfig.Effort.LOW).build())
                    .build()
            )

        val span = recorder.only()
        assertThat(span.string("gen_ai.request.reasoning.level")).isEqualTo("low")
        assertThat(attributesOf(span)).doesNotContainKey("gen_ai.output.type")
    }

    @Test
    fun errorResponse_recordsEnvelopeType() {
        stubFor(
            post(anyUrl())
                .willReturn(
                    aResponse()
                        .withStatus(429)
                        .withHeader("Content-Type", "application/json")
                        .withHeader("request-id", "req_error")
                        .withBody(ERROR_JSON)
                )
        )

        assertThrows<RateLimitException> { client.messages().create(messageParams()) }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.ERROR)
        assertThat(span.status.description).isEmpty()
        assertThat(span.string("error.type")).isEqualTo("rate_limit_error")
        assertThat(span.long("http.response.status_code")).isEqualTo(429)
        assertThat(span.string("anthropic.request.id")).isEqualTo("req_error")
        assertThat(span.string("gen_ai.request.model")).isEqualTo("claude-requested")
        assertThat(span.string("gen_ai.response.model")).isNull()
        assertThat(span.string("anthropic.message.stop_reason")).isNull()
        assertThat(span.events).isEmpty()
    }

    @Test
    fun errorResponseWithoutEnvelope_recordsStatusCode() {
        stubFor(post(anyUrl()).willReturn(aResponse().withStatus(502).withBody("Bad gateway")))

        assertThrows<InternalServerException> { client.messages().create(messageParams()) }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.ERROR)
        assertThat(span.string("error.type")).isEqualTo("502")
    }

    @Test
    fun connectionError_recordsConnectionError() {
        val client = clientBuilder().baseUrl("http://127.0.0.1:1").build()

        assertThrows<AnthropicIoException> { client.messages().create(messageParams()) }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.ERROR)
        assertThat(span.string("error.type")).isEqualTo("connection_error")
        assertThat(span.long("http.response.status_code")).isNull()
        assertThat(span.string("server.address")).isEqualTo("127.0.0.1")
        assertThat(span.long("server.port")).isEqualTo(1)
    }

    @Test
    fun globalOpenTelemetry_registeredAfterCalls_isUsed() {
        stubMessage()
        val globalOnly =
            AnthropicOkHttpClient.builder()
                .baseUrl(baseUrl)
                .apiKey("my-anthropic-api-key")
                .maxRetries(0)
                .build()
        GlobalOpenTelemetry.resetForTest()
        try {
            // Both read the global instance before the application registers one.
            globalOnly.messages().create(messageParams())
            client.messages().create(messageParams())
            assertThat(recorder.spans).hasSize(1)

            // Throws when the calls registered an instance, as `GlobalOpenTelemetry.get` does.
            GlobalOpenTelemetry.set(
                object : OpenTelemetry {
                    override fun getTracerProvider() = recorder.tracerProvider

                    override fun getPropagators() =
                        ContextPropagators.create(W3CTraceContextPropagator.getInstance())
                }
            )
            globalOnly.messages().create(messageParams())

            assertThat(recorder.spans).hasSize(2)
            val requests = findAll(postRequestedFor(urlPathEqualTo("/v1/messages")))
            assertThat(requests.last().getHeader("traceparent"))
                .contains(recorder.spans.last().spanContext.spanId)
        } finally {
            GlobalOpenTelemetry.resetForTest()
            registerGlobal()
        }
    }

    @Test
    fun disabledClient_emitsNoSpanAndNoTraceHeaders() {
        stubMessage()
        val client = clientBuilder().openTelemetry(false).build()
        val parent = recorder.tracerProvider.get("app").spanBuilder("parent").startSpan()

        parent.makeCurrent().use { client.messages().create(messageParams()) }

        assertThat(recorder.spans).isEmpty()
        val request = findAll(postRequestedFor(urlPathEqualTo("/v1/messages"))).single()
        assertThat(request.containsHeader("traceparent")).isFalse()
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun tracesShorthand_enablesOrDisablesTheSpans(enabled: Boolean) {
        stubMessage()
        val client =
            clientBuilder()
                .openTelemetry(
                    OpenTelemetryOptions.builder()
                        .tracerProvider(recorder.tracerProvider)
                        .traces(enabled)
                        .build()
                )
                .build()

        client.messages().create(messageParams())

        assertThat(recorder.spans).hasSize(if (enabled) 1 else 0)
        val request = findAll(postRequestedFor(urlPathEqualTo("/v1/messages"))).single()
        assertThat(request.containsHeader("traceparent")).isEqualTo(enabled)
    }

    @Test
    fun disabledTraces_withItsOtherSettings_emitsNoSpanAndNoTraceHeaders() {
        stubMessage()
        val openTelemetry =
            OpenTelemetryOptions.builder()
                .tracerProvider(recorder.tracerProvider)
                .traces(
                    OpenTelemetryTraces.builder()
                        .enabled(false)
                        .contentMode(OpenTelemetryContentMode.CONTENT)
                        .maxContentBytes(100L)
                        .build()
                )
                .propagation(true)
                .build()
        val client = clientBuilder().openTelemetry(openTelemetry).build()
        val async =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(baseUrl)
                .apiKey("my-anthropic-api-key")
                .maxRetries(0)
                .openTelemetry(openTelemetry)
                .build()
        val parent = recorder.tracerProvider.get("app").spanBuilder("parent").startSpan()

        parent.makeCurrent().use {
            client.messages().create(messageParams())
            async.messages().create(messageParams()).get(10, TimeUnit.SECONDS)
            stubStream()
            client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }
        }

        assertThat(recorder.spans).isEmpty()
        assertThat(findAll(postRequestedFor(urlPathEqualTo("/v1/messages"))))
            .hasSize(3)
            .allSatisfy { assertThat(it.containsHeader("traceparent")).isFalse() }
    }

    /** Injects `x-custom-trace`, and counts the times it is asked to. */
    private class CountingPropagator : TextMapPropagator {
        val injected = AtomicInteger()

        override fun fields(): Collection<String> = listOf("x-custom-trace")

        override fun <C> inject(context: Context, carrier: C?, setter: TextMapSetter<C>) {
            injected.incrementAndGet()
            setter.set(carrier, "x-custom-trace", "custom")
        }

        override fun <C> extract(context: Context, carrier: C?, getter: TextMapGetter<C>): Context =
            context
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(booleans = [true, false])
    fun propagation_decidesWhetherTheRequestsCarryTheTraceContext(propagation: Boolean?) {
        stubMessage()
        val openTelemetry =
            options().toBuilder().apply { propagation?.let { propagation(it) } }.build()
        val client = clientBuilder().openTelemetry(openTelemetry).build()
        val async =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(baseUrl)
                .apiKey("my-anthropic-api-key")
                .maxRetries(0)
                .openTelemetry(openTelemetry)
                .build()
        val withoutTracerProvider =
            clientBuilder()
                .openTelemetry(openTelemetry.toBuilder().tracerProvider(null).build())
                .build()
        val parent = recorder.tracerProvider.get("app").spanBuilder("parent").startSpan()
        val parentContext =
            Context.root()
                .with(
                    Span.wrap(
                        SpanContext.create(
                            parent.spanContext.traceId,
                            parent.spanContext.spanId,
                            TraceFlags.getSampled(),
                            TraceState.builder().put("vendor", "value").build(),
                        )
                    )
                )
                .with(Baggage.builder().put("tenant", "acme").build())
        val custom = CountingPropagator()
        GlobalOpenTelemetry.resetForTest()
        try {
            GlobalOpenTelemetry.set(
                OpenTelemetry.propagating(
                    ContextPropagators.create(
                        TextMapPropagator.composite(
                            W3CTraceContextPropagator.getInstance(),
                            W3CBaggagePropagator.getInstance(),
                            custom,
                        )
                    )
                )
            )

            parentContext.makeCurrent().use {
                client.messages().create(messageParams())
                async.messages().create(messageParams()).get(10, TimeUnit.SECONDS)
                withoutTracerProvider.messages().create(messageParams())
                stubStream()
                client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }
            }
        } finally {
            GlobalOpenTelemetry.resetForTest()
            registerGlobal()
        }
        parent.end()

        val carried = propagation != false
        assertThat(findAll(postRequestedFor(urlPathEqualTo("/v1/messages"))))
            .hasSize(4)
            .allSatisfy { request ->
                assertThat(
                        listOf("traceparent", "tracestate", "baggage", "x-custom-trace").map {
                            request.containsHeader(it)
                        }
                    )
                    .containsOnly(carried)
            }
        if (carried) {
            assertThat(custom.injected.get()).isPositive()
        } else {
            assertThat(custom.injected.get()).isZero()
        }
        assertThat(recorder.await(4).filter { it.kind == SpanKind.CLIENT }).hasSize(3).allSatisfy {
            assertThat(it.hasEnded()).isTrue()
            assertThat(it.status.statusCode).isEqualTo(StatusCode.OK)
            assertThat(it.parentSpanId).isEqualTo(parent.spanContext.spanId)
            assertThat(it.long("http.response.status_code")).isEqualTo(200)
        }
    }

    @Test
    fun propagationOff_spanThatIsSampledOut_isCurrentWhileTheRequestIsSent() {
        val current = AtomicReference<SpanContext>()
        val httpClient =
            FakeHttpClient({
                current.set(Span.current().spanContext)
                MESSAGE_JSON
            })
        val clientOptions =
            ClientOptions.builder()
                .httpClient(httpClient)
                .openTelemetry(
                    OpenTelemetryOptions.builder()
                        .tracerProvider(
                            SdkTracerProvider.builder().setSampler(Sampler.alwaysOff()).build()
                        )
                        .propagation(false)
                        .build()
                )
                .build()

        AnthropicClientImpl(clientOptions).messages().create(messageParams())

        // An HTTP client instrumentation parents its span to this one, and is sampled out with it.
        assertThat(current.get().isValid).isTrue()
        assertThat(current.get().isSampled).isFalse()
        assertThat(httpClient.requests.single().headers.names()).doesNotContain("traceparent")
    }

    @Test
    fun withOptions_propagationOff_appliesToTheDerivedClientOnly() {
        stubMessage()
        val derived =
            client.withOptions {
                it.openTelemetry(options().toBuilder().propagation(false).build())
            }

        derived.messages().create(messageParams())
        client.messages().create(messageParams())

        assertThat(
                findAll(postRequestedFor(urlPathEqualTo("/v1/messages"))).map {
                    it.containsHeader("traceparent")
                }
            )
            .containsExactly(false, true)
        assertThat(recorder.spans).hasSize(2)
    }

    @Test
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    fun fromEnv_appliesThePropagationSystemProperty() {
        System.setProperty("anthropic.openTelemetryPropagation", "false")
        try {
            stubMessage()
            // The property replaces the propagation of an option set before `fromEnv`, and an
            // option set after it replaces what the property set.
            val optionThenFromEnv =
                clientBuilder().fromEnv().baseUrl(baseUrl).apiKey("my-anthropic-api-key").build()
            val fromEnvThenOption =
                AnthropicOkHttpClient.builder()
                    .fromEnv()
                    .baseUrl(baseUrl)
                    .apiKey("my-anthropic-api-key")
                    .maxRetries(0)
                    .openTelemetry(options())
                    .build()

            optionThenFromEnv.messages().create(messageParams())
            fromEnvThenOption.messages().create(messageParams())
            // A client built without `fromEnv` ignores the property.
            client.messages().create(messageParams())

            assertThat(
                    findAll(postRequestedFor(urlPathEqualTo("/v1/messages"))).map {
                        it.containsHeader("traceparent")
                    }
                )
                .containsExactly(false, true, true)
            assertThat(recorder.spans).hasSize(3)
        } finally {
            System.clearProperty("anthropic.openTelemetryPropagation")
        }
    }

    @Test
    fun fromEnv_appliesThePropagationEnvironmentVariable() {
        assumeTrue(
            System.getenv("ANTHROPIC_OPEN_TELEMETRY_PROPAGATION").equals("false", ignoreCase = true)
        )
        stubMessage()
        val fromEnv =
            clientBuilder().fromEnv().baseUrl(baseUrl).apiKey("my-anthropic-api-key").build()

        fromEnv.messages().create(messageParams())
        // A client built without `fromEnv` ignores the variable.
        client.messages().create(messageParams())

        assertThat(
                findAll(postRequestedFor(urlPathEqualTo("/v1/messages"))).map {
                    it.containsHeader("traceparent")
                }
            )
            .containsExactly(false, true)
        assertThat(recorder.spans).hasSize(2)
    }

    @Test
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    fun fromEnv_appliesTheSystemProperty() {
        System.setProperty("anthropic.openTelemetry", "false")
        try {
            stubMessage()
            val fromEnv = clientFromEnv()
            val parent = recorder.tracerProvider.get("app").spanBuilder("parent").startSpan()

            parent.makeCurrent().use { fromEnv.messages().create(messageParams()) }

            val request = findAll(postRequestedFor(urlPathEqualTo("/v1/messages"))).single()
            assertThat(request.containsHeader("traceparent")).isFalse()
            // A client built without `fromEnv` ignores the property.
            client.messages().create(messageParams())
            assertThat(recorder.spans).hasSize(1)
        } finally {
            System.clearProperty("anthropic.openTelemetry")
        }
    }

    @Test
    fun fromEnv_appliesTheEnvironmentVariable() {
        assumeTrue(System.getenv("ANTHROPIC_OPEN_TELEMETRY").equals("false", ignoreCase = true))
        stubMessage()
        val parent = recorder.tracerProvider.get("app").spanBuilder("parent").startSpan()

        parent.makeCurrent().use { clientFromEnv().messages().create(messageParams()) }

        val request = findAll(postRequestedFor(urlPathEqualTo("/v1/messages"))).single()
        assertThat(request.containsHeader("traceparent")).isFalse()
        // A client built without `fromEnv` ignores the variable.
        client.messages().create(messageParams())
        assertThat(recorder.spans).hasSize(1)
    }

    /** A client whose spans, when it emits them, go to the registered tracer provider. */
    private fun clientFromEnv(): AnthropicClient =
        AnthropicOkHttpClient.builder()
            .fromEnv()
            .baseUrl(baseUrl)
            .apiKey("my-anthropic-api-key")
            .maxRetries(0)
            .build()

    @Test
    fun countTokens_recordsInputTokens() {
        stubFor(
            post(urlPathEqualTo("/v1/messages/count_tokens"))
                .willReturn(
                    ok("""{"input_tokens": 12}""").withHeader("Content-Type", "application/json")
                )
        )

        client
            .messages()
            .countTokens(
                MessageCountTokensParams.builder()
                    .model("claude-requested")
                    .addUserMessage("hello")
                    .build()
            )

        val span = recorder.only()
        assertThat(span.name).isEqualTo("anthropic.messages.count_tokens")
        assertThat(span.string("gen_ai.request.model")).isEqualTo("claude-requested")
        assertThat(span.long("anthropic.message.count_tokens.input_tokens")).isEqualTo(12)
        assertThat(span.string("gen_ai.operation.name")).isNull()
        assertThat(span.long("gen_ai.usage.input_tokens")).isNull()
    }

    @Test
    fun countTokensAndBatchesCreate_recordTheirAttributesInEveryClient() {
        stubFor(
            post(urlPathEqualTo("/v1/messages/count_tokens"))
                .willReturn(
                    ok("""{"input_tokens": 12}""").withHeader("Content-Type", "application/json")
                )
        )
        stubFor(
            post(urlPathEqualTo("/v1/messages/batches"))
                .willReturn(
                    ok("""{"id": "msgbatch_2"}""").withHeader("Content-Type", "application/json")
                )
        )
        val countParams =
            MessageCountTokensParams.builder()
                .model("claude-requested")
                .addUserMessage("hello")
                .build()
        val betaCountParams =
            BetaMessageCountTokensParams.builder()
                .model("claude-requested")
                .addUserMessage("hello")
                .build()
        val batchParams =
            BatchCreateParams.builder()
                .addRequest(
                    BatchCreateParams.Request.builder()
                        .customId("a")
                        .params(
                            BatchCreateParams.Request.Params.builder()
                                .model("claude-requested")
                                .maxTokens(1)
                                .addUserMessage("hello")
                                .build()
                        )
                        .build()
                )
                .build()
        val betaBatchParams =
            BetaBatchCreateParams.builder()
                .addRequest(
                    BetaBatchCreateParams.Request.builder()
                        .customId("a")
                        .params(
                            BetaBatchCreateParams.Request.Params.builder()
                                .model("claude-requested")
                                .maxTokens(1)
                                .addUserMessage("hello")
                                .build()
                        )
                        .build()
                )
                .build()
        val async = asyncClient()

        client.messages().countTokens(countParams)
        client.beta().messages().countTokens(betaCountParams)
        async.messages().countTokens(countParams).get(30, TimeUnit.SECONDS)
        async.beta().messages().countTokens(betaCountParams).get(30, TimeUnit.SECONDS)
        client.messages().batches().create(batchParams)
        client.beta().messages().batches().create(betaBatchParams)
        async.messages().batches().create(batchParams).get(30, TimeUnit.SECONDS)
        async.beta().messages().batches().create(betaBatchParams).get(30, TimeUnit.SECONDS)

        val spans = recorder.await(8)
        assertThat(spans.filter { it.name == "anthropic.messages.count_tokens" })
            .hasSize(4)
            .allSatisfy {
                assertThat(it.string("gen_ai.request.model")).isEqualTo("claude-requested")
                assertThat(it.long("anthropic.message.count_tokens.input_tokens")).isEqualTo(12)
                assertThat(it.long("anthropic.message.batch.request_count")).isNull()
            }
        assertThat(spans.filter { it.name == "anthropic.messages.batches.create" })
            .hasSize(4)
            .allSatisfy {
                assertThat(it.string("anthropic.message.batch.id")).isEqualTo("msgbatch_2")
                assertThat(it.long("anthropic.message.batch.request_count")).isEqualTo(1)
            }
    }

    @Test
    fun countTokens_responseWithoutTheCount_stillReturnsIt() {
        stubFor(
            post(urlPathEqualTo("/v1/messages/count_tokens"))
                .willReturn(ok("{}").withHeader("Content-Type", "application/json"))
        )

        // The SDK does not validate a response unless asked to, so recording it must not either.
        client
            .messages()
            .countTokens(
                MessageCountTokensParams.builder()
                    .model("claude-requested")
                    .addUserMessage("hello")
                    .build()
            )

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.long("anthropic.message.count_tokens.input_tokens")).isNull()
    }

    @Test
    fun batches_recordBatchId() {
        stubFor(
            get(urlPathEqualTo("/v1/messages/batches/msgbatch_1"))
                .willReturn(
                    ok("""{"id": "msgbatch_1"}""").withHeader("Content-Type", "application/json")
                )
        )
        stubFor(
            post(urlPathEqualTo("/v1/messages/batches"))
                .willReturn(
                    ok("""{"id": "msgbatch_2"}""").withHeader("Content-Type", "application/json")
                )
        )

        client.messages().batches().retrieve("msgbatch_1")
        client
            .messages()
            .batches()
            .create(
                BatchCreateParams.builder()
                    .addRequest(
                        BatchCreateParams.Request.builder()
                            .customId("a")
                            .params(
                                BatchCreateParams.Request.Params.builder()
                                    .model("claude-requested")
                                    .maxTokens(1)
                                    .addUserMessage("hello")
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        assertThat(
                recorder
                    .named("anthropic.messages.batches.retrieve")
                    .string("anthropic.message.batch.id")
            )
            .isEqualTo("msgbatch_1")
        assertThat(
                recorder
                    .named("anthropic.messages.batches.create")
                    .string("anthropic.message.batch.id")
            )
            .isEqualTo("msgbatch_2")
    }

    @Test
    fun batches_byId_recordTheBatchIdInEveryClient() {
        val batch = ok("""{"id": "msgbatch_1"}""").withHeader("Content-Type", "application/json")
        stubFor(get(urlPathEqualTo("/v1/messages/batches/msgbatch_1")).willReturn(batch))
        stubFor(delete(urlPathEqualTo("/v1/messages/batches/msgbatch_1")).willReturn(batch))
        stubFor(post(urlPathEqualTo("/v1/messages/batches/msgbatch_1/cancel")).willReturn(batch))
        stubBatchResults("""{"custom_id":"a","result":{"type":"expired"}}""")
        val async = asyncClient()

        client.messages().batches().retrieve("msgbatch_1")
        client.messages().batches().delete("msgbatch_1")
        client.messages().batches().cancel("msgbatch_1")
        client.messages().batches().resultsStreaming("msgbatch_1").use { it.stream().count() }
        client.beta().messages().batches().retrieve("msgbatch_1")
        client.beta().messages().batches().delete("msgbatch_1")
        client.beta().messages().batches().cancel("msgbatch_1")
        client.beta().messages().batches().resultsStreaming("msgbatch_1").use {
            it.stream().count()
        }
        async.messages().batches().retrieve("msgbatch_1").get(30, TimeUnit.SECONDS)
        async.messages().batches().delete("msgbatch_1").get(30, TimeUnit.SECONDS)
        async.messages().batches().cancel("msgbatch_1").get(30, TimeUnit.SECONDS)
        async
            .messages()
            .batches()
            .resultsStreaming("msgbatch_1")
            .subscribe {}
            .onCompleteFuture()
            .get(30, TimeUnit.SECONDS)
        async.beta().messages().batches().retrieve("msgbatch_1").get(30, TimeUnit.SECONDS)
        async.beta().messages().batches().delete("msgbatch_1").get(30, TimeUnit.SECONDS)
        async.beta().messages().batches().cancel("msgbatch_1").get(30, TimeUnit.SECONDS)
        async
            .beta()
            .messages()
            .batches()
            .resultsStreaming("msgbatch_1")
            .subscribe {}
            .onCompleteFuture()
            .get(30, TimeUnit.SECONDS)

        val spans = recorder.await(16)
        assertThat(spans.map { it.name }.distinct())
            .containsExactlyInAnyOrder(
                "anthropic.messages.batches.retrieve",
                "anthropic.messages.batches.delete",
                "anthropic.messages.batches.cancel",
                "anthropic.messages.batches.results",
            )
        assertThat(spans).allSatisfy {
            assertThat(it.string("anthropic.message.batch.id")).isEqualTo("msgbatch_1")
            assertThat(it.long("anthropic.message.batch.request_count")).isNull()
            assertThat(it.status.statusCode).isEqualTo(StatusCode.OK)
        }
    }

    private fun stubBatchResults(vararg lines: String) {
        stubFor(
            get(urlPathEqualTo("/v1/messages/batches/msgbatch_1/results"))
                .willReturn(
                    ok(lines.joinToString("\n", postfix = "\n"))
                        .withHeader("Content-Type", "application/x-jsonl")
                )
        )
    }

    @Test
    fun batchResults_spanCoversTheDownload() {
        stubBatchResults(
            """{"custom_id":"a","result":{"type":"expired"}}""",
            """{"custom_id":"b","result":{"type":"canceled"}}""",
        )

        val results =
            client.messages().batches().resultsStreaming("msgbatch_1").use { stream ->
                // The response has arrived, and the span is open until its body has been read.
                assertThat(recorder.spans).isEmpty()
                stream.stream().count()
            }

        assertThat(results).isEqualTo(2)
        val span = recorder.only()
        assertThat(span.name).isEqualTo("anthropic.messages.batches.results")
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.string("anthropic.message.batch.id")).isEqualTo("msgbatch_1")
        assertThat(span.long("http.response.status_code")).isEqualTo(200)
    }

    @Test
    fun batchResults_failureWhileReading_failsTheSpan() {
        stubBatchResults("""{"custom_id":"a","result":{"type":"expired"}}""", "{not json")

        assertThrows<AnthropicException> {
            client.messages().batches().resultsStreaming("msgbatch_1").use { stream ->
                stream.stream().forEach { it.customId() }
            }
        }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.ERROR)
        assertThat(span.string("error.type")).isEqualTo("client_error")
        assertThat(span.string("anthropic.message.batch.id")).isEqualTo("msgbatch_1")
    }

    @Test
    fun callSpan_startsWithTheAttributesASamplerDecidesBy() {
        stubMessage()
        stubFor(
            post(urlPathEqualTo("/v1/messages/count_tokens"))
                .willReturn(
                    ok("""{"input_tokens": 12}""").withHeader("Content-Type", "application/json")
                )
        )
        val seenAtStart = CopyOnWriteArrayList<Attributes>()
        val sampler =
            object : Sampler {
                override fun shouldSample(
                    parentContext: Context,
                    traceId: String,
                    name: String,
                    spanKind: SpanKind,
                    attributes: Attributes,
                    parentLinks: List<LinkData>,
                ): SamplingResult {
                    seenAtStart.add(attributes)
                    return SamplingResult.recordAndSample()
                }

                override fun getDescription(): String = "records what it is given"
            }
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(baseUrl)
                .apiKey("my-anthropic-api-key")
                .maxRetries(0)
                .openTelemetry(
                    OpenTelemetryOptions.builder()
                        .tracerProvider(SdkTracerProvider.builder().setSampler(sampler).build())
                        .build()
                )
                .build()

        val requestOptions =
            RequestOptions.builder()
                .openTelemetry(
                    OpenTelemetryRequestOptions.builder().conversationId("conv_1").build()
                )
                .build()

        client.messages().create(messageParams(), requestOptions)
        client
            .messages()
            .countTokens(
                MessageCountTokensParams.builder()
                    .model("claude-requested")
                    .addUserMessage("hello")
                    .build()
            )
        stubStream()
        client.messages().createStreaming(messageParams(), requestOptions).use { stream ->
            stream.stream().forEach {}
        }
        stubFor(
            get(urlPathEqualTo("/v1/messages/batches/msgbatch_1"))
                .willReturn(
                    ok("""{"id": "msgbatch_1"}""").withHeader("Content-Type", "application/json")
                )
        )
        client.messages().batches().retrieve("msgbatch_1")
        stubFor(
            get(urlPathEqualTo("/v1/models"))
                .willReturn(
                    ok("""{"data": [], "has_more": false, "first_id": null, "last_id": null}""")
                        .withHeader("Content-Type", "application/json")
                )
        )
        client.models().list()
        stubFor(
            post(urlPathEqualTo("/v1/messages/batches"))
                .willReturn(
                    ok("""{"id": "msgbatch_2"}""").withHeader("Content-Type", "application/json")
                )
        )
        client.messages().batches().create(batchParams("a", "b"))
        stubFor(
            get(urlPathEqualTo("/v1/messages/batches"))
                .willReturn(
                    ok("""{"data": [], "has_more": false, "first_id": null, "last_id": null}""")
                        .withHeader("Content-Type", "application/json")
                )
        )
        client.messages().batches().list()

        val started = seenAtStart.map { it.asMap().mapKeys { (key, _) -> key.key } }
        assertThat(started).hasSize(7).allSatisfy {
            assertThat(it)
                .containsEntry("server.address", "localhost")
                .containsEntry("server.port", baseUrl.substringAfterLast(':').toLong())
        }
        val (batchesCreate, batchesList) = started.drop(5)
        assertThat(batchesCreate).containsEntry("anthropic.message.batch.request_count", 2L)
        assertThat(batchesList).doesNotContainKey("anthropic.message.batch.request_count")
        val (create, countTokens, createStreaming) = started
        assertThat(create)
            .containsEntry("gen_ai.provider.name", "anthropic")
            .containsEntry("gen_ai.operation.name", "chat")
            .containsEntry("gen_ai.request.model", "claude-requested")
            .containsEntry("gen_ai.conversation.id", "conv_1")
            .containsEntry("gen_ai.request.max_tokens", 100L)
            .containsEntry("gen_ai.request.temperature", 0.5)
            .containsEntry("gen_ai.request.top_k", 3L)
            .containsEntry("anthropic.beta", listOf("beta-one", "beta-two"))
            .doesNotContainKey("gen_ai.request.stream")
        assertThat(createStreaming)
            .containsEntry("gen_ai.conversation.id", "conv_1")
            .containsEntry("gen_ai.request.max_tokens", 100L)
            .containsEntry("gen_ai.request.stream", true)
            .containsEntry("anthropic.beta", listOf("beta-one", "beta-two"))
        assertThat(countTokens)
            .containsEntry("gen_ai.provider.name", "anthropic")
            .containsEntry("gen_ai.request.model", "claude-requested")
            .doesNotContainKey("gen_ai.operation.name")
    }

    private fun batchParams(vararg customIds: String): BatchCreateParams =
        BatchCreateParams.builder()
            .requests(
                customIds.map {
                    BatchCreateParams.Request.builder()
                        .customId(it)
                        .params(
                            BatchCreateParams.Request.Params.builder()
                                .model("claude-requested")
                                .maxTokens(1)
                                .addUserMessage("hello")
                                .build()
                        )
                        .build()
                }
            )
            .build()

    private fun compaction(input: Long, cacheRead: Long, cacheCreation: Long, output: Long) =
        """{"type":"compaction","input_tokens":$input,"cache_read_input_tokens":$cacheRead,"cache_creation_input_tokens":$cacheCreation,"output_tokens":$output}"""

    /** [MESSAGE_JSON] with [iterations] in its usage. */
    private fun messageWithIterations(vararg iterations: String, json: String = MESSAGE_JSON) =
        json.replace(
            """"usage": {""",
            """"usage": {"iterations": [${iterations.joinToString()}],""",
        )

    /** [STREAM_EVENTS] with [iterations] in the usage of the `message_delta` event. */
    private fun eventsWithIterations(vararg iterations: String): List<String> =
        STREAM_EVENTS.map {
            if (!it.contains("message_delta")) it
            else
                it.replace(
                    """"usage":{""",
                    """"usage":{"iterations":[${iterations.joinToString()}],""",
                )
        }

    private val messageIteration =
        """{"type":"message","input_tokens":10,"cache_read_input_tokens":2,"cache_creation_input_tokens":3,"output_tokens":5}"""

    @Test
    fun betaMessagesCreate_recordsTheTokensOfTheCompactionsInItsUsage() {
        stubMessage(
            messageWithIterations(
                compaction(input = 100, cacheRead = 20, cacheCreation = 3, output = 40),
                messageIteration,
                compaction(input = 50, cacheRead = 0, cacheCreation = 0, output = 10),
            )
        )

        // The counts are metadata, so content mode changes nothing about them.
        for (mode in OpenTelemetryContentMode.values()) {
            clientBuilder(mode).build().beta().messages().create(betaMessageParams())
            asyncClient(mode)
                .beta()
                .messages()
                .create(betaMessageParams())
                .get(30, TimeUnit.SECONDS)
        }

        assertThat(recorder.await(2 * OpenTelemetryContentMode.values().size)).allSatisfy {
            assertThat(it.long("anthropic.usage.compaction.input_tokens")).isEqualTo(173)
            assertThat(it.long("anthropic.usage.compaction.output_tokens")).isEqualTo(50)
            // The usage of the Message stays what its top level says.
            assertThat(it.long("gen_ai.usage.input_tokens")).isEqualTo(15)
            assertThat(it.long("gen_ai.usage.output_tokens")).isEqualTo(5)
        }
    }

    @Test
    fun betaMessagesCreate_usageOfTheCompactionAlone_recordsItsTokens() {
        val noTokens =
            MESSAGE_JSON.replace(
                """"input_tokens": 10, "output_tokens": 5""",
                """"input_tokens": 0, "output_tokens": 0""",
            )
        stubMessage(
            messageWithIterations(
                compaction(input = 100, cacheRead = 20, cacheCreation = 3, output = 40),
                json = noTokens,
            )
        )

        client.beta().messages().create(betaMessageParams())

        val span = recorder.only()
        assertThat(span.long("gen_ai.usage.output_tokens")).isEqualTo(0)
        assertThat(span.long("anthropic.usage.compaction.input_tokens")).isEqualTo(123)
        assertThat(span.long("anthropic.usage.compaction.output_tokens")).isEqualTo(40)
    }

    @Test
    fun messagesCreate_noCompactionInItsUsage_recordsNoCompactionTokens() {
        stubMessage()
        client.messages().create(messageParams())
        client.beta().messages().create(betaMessageParams())
        stubMessage(messageWithIterations())
        client.beta().messages().create(betaMessageParams())
        stubMessage(messageWithIterations(messageIteration))
        client.beta().messages().create(betaMessageParams())

        assertThat(recorder.spans).hasSize(4).allSatisfy {
            assertThat(it.status.statusCode).isEqualTo(StatusCode.OK)
            assertThat(attributesOf(it))
                .doesNotContainKeys(
                    "anthropic.usage.compaction.input_tokens",
                    "anthropic.usage.compaction.output_tokens",
                )
        }
    }

    @Test
    fun betaMessagesCreateStreaming_recordsTheTokensOfTheCompactionsOnceMessageDeltaArrived() {
        val events =
            eventsWithIterations(
                compaction(input = 100, cacheRead = 20, cacheCreation = 3, output = 40),
                messageIteration,
            )
        stubStream(sse(*events.toTypedArray()))

        client.beta().messages().createStreaming(betaMessageParams()).use { it.stream().forEach {} }
        asyncClient()
            .beta()
            .messages()
            .createStreaming(betaMessageParams())
            .subscribe {}
            .onCompleteFuture()
            .get(30, TimeUnit.SECONDS)
        // The server ends the stream before `message_delta`, which carries the iterations.
        stubStream(sse(*events.dropLast(2).toTypedArray()))
        client.beta().messages().createStreaming(betaMessageParams()).use { it.stream().forEach {} }

        val (streamed, streamedAsync, cutShort) = recorder.await(3)
        assertThat(listOf(streamed, streamedAsync)).allSatisfy {
            assertThat(it.long("anthropic.usage.compaction.input_tokens")).isEqualTo(123)
            assertThat(it.long("anthropic.usage.compaction.output_tokens")).isEqualTo(40)
            assertThat(it.long("gen_ai.usage.input_tokens")).isEqualTo(15)
            assertThat(it.long("gen_ai.usage.output_tokens")).isEqualTo(7)
        }
        assertThat(cutShort.status.statusCode).isEqualTo(StatusCode.ERROR)
        assertThat(attributesOf(cutShort))
            .doesNotContainKeys(
                "anthropic.usage.compaction.input_tokens",
                "anthropic.usage.compaction.output_tokens",
            )
    }

    @Test
    fun callEndedByTwoPaths_endsItsSpanOnce() {
        // A span of an implementation that still says it records after it has ended.
        val ends = AtomicInteger()
        val span =
            object : Span by Span.getInvalid() {
                override fun isRecording(): Boolean = true

                override fun end() {
                    ends.incrementAndGet()
                }
            }
        val clientOptions =
            ClientOptions.builder()
                .httpClient(FakeHttpClient("{}"))
                .openTelemetry(options())
                .build()
        val request =
            HttpRequest.builder().method(HttpMethod.GET).baseUrl("http://localhost").build()

        val response =
            traceApiCall(clientOptions, ApiCall(span, clientOptions)) { client ->
                client.execute(request, RequestOptions.none()).parseable<String> {
                    error("The body does not parse")
                }
            }
        // Parsing fails, which ends the call, and so would closing a response not parsed.
        assertThrows<IllegalStateException> { response.parse() }
        response.close()

        assertThat(ends).hasValue(1)
    }

    @Test
    fun rawResponse_closedUnparsed_endsAtTheHeaders() {
        stubMessage()

        client.messages().withRawResponse().create(messageParams()).use { response ->
            assertThat(response.statusCode()).isEqualTo(200)
        }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.long("http.response.status_code")).isEqualTo(200)
        assertThat(span.string("anthropic.request.id")).isEqualTo("req_1")
        assertThat(span.string("gen_ai.request.model")).isEqualTo("claude-requested")
        // The body was not parsed, so nothing of it is on the span.
        assertThat(span.string("gen_ai.response.model")).isNull()
    }

    /** Sends a call for its raw response, and drops the response without parsing or closing it. */
    private fun createRawAndAbandon() {
        assertThat(client.messages().withRawResponse().create(messageParams()).statusCode())
            .isEqualTo(200)
    }

    @Test
    fun rawResponse_abandoned_endsAtTheHeadersOnceReclaimed() {
        stubMessage()

        createRawAndAbandon()

        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(10)
        while (recorder.spans.isEmpty() && System.nanoTime() < deadline) {
            System.gc()
            Thread.sleep(50)
        }
        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.long("http.response.status_code")).isEqualTo(200)
        assertThat(span.string("gen_ai.response.model")).isNull()
    }

    @Test
    fun rawResponse_parsed_recordsTheBody() {
        stubMessage()

        val message = client.messages().withRawResponse().create(messageParams()).use { it.parse() }

        assertThat(message.id()).isEqualTo("msg_1")
        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.string("gen_ai.response.model")).isEqualTo("claude-served")
    }

    @Test
    fun binaryDownload_endsAtTheHeaders() {
        stubFor(
            get(urlPathEqualTo("/v1/files/file_1/content"))
                .willReturn(ok("%PDF").withHeader("Content-Type", "application/pdf"))
        )

        val response = client.beta().files().download("file_1")
        // The application reads the body itself, so the span has ended by now.
        val span = recorder.only()
        response.use { it.body().readBytes() }

        assertThat(span.name).isEqualTo("anthropic.files.download")
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.long("http.response.status_code")).isEqualTo(200)
        assertThat(recorder.spans).hasSize(1)
    }

    @Test
    fun laterPage_emitsItsOwnSpan() {
        stubFor(
            get(urlPathEqualTo("/v1/models"))
                .inScenario("pages")
                .whenScenarioStateIs(Scenario.STARTED)
                .willSetStateTo("second")
                .willReturn(
                    ok(
                            """{"data": [{"id": "m1", "type": "model", "display_name": "M1", "created_at": "2024-01-01T00:00:00Z"}], "has_more": true, "first_id": "m1", "last_id": "m1"}"""
                        )
                        .withHeader("Content-Type", "application/json")
                )
        )
        stubFor(
            get(urlPathEqualTo("/v1/models"))
                .inScenario("pages")
                .whenScenarioStateIs("second")
                .willReturn(
                    ok("""{"data": [], "has_more": false, "first_id": null, "last_id": null}""")
                        .withHeader("Content-Type", "application/json")
                )
        )
        val tracer = recorder.tracerProvider.get("app")

        val page = client.models().list()
        val parent = tracer.spanBuilder("iterate").startSpan()
        parent.makeCurrent().use { page.nextPage() }
        parent.end()

        val spans = recorder.spans.filter { it.name == "anthropic.models.list" }
        assertThat(spans).hasSize(2)
        assertThat(spans[0].parentSpanContext.isValid).isFalse()
        assertThat(spans[1].parentSpanId).isEqualTo(parent.spanContext.spanId)
        val requests = findAll(getRequestedFor(urlPathEqualTo("/v1/models")))
        assertThat(requests.map { it.getHeader("traceparent").substringBeforeLast('-') })
            .containsExactly(
                "00-${spans[0].traceId}-${spans[0].spanId}",
                "00-${spans[1].traceId}-${spans[1].spanId}",
            )
    }

    @Test
    fun providerName_comesFromTheBackend() {
        stubMessage()
        val backend =
            object : Backend {
                override fun baseUrl(): String = baseUrl

                override fun openTelemetryProviderName(): String = "aws.bedrock"

                override fun close() {}
            }
        val client =
            AnthropicOkHttpClient.builder()
                .backend(backend)
                .maxRetries(0)
                .openTelemetry(options())
                .build()

        client.messages().create(messageParams())

        assertThat(recorder.only().string("gen_ai.provider.name")).isEqualTo("aws.bedrock")
    }

    @Test
    fun instrumentationScope_hasThePlatformOfTheBackend() {
        stubMessage()
        val backend =
            object : Backend {
                override fun baseUrl(): String = baseUrl

                override fun openTelemetryPlatform(): Optional<String> = Optional.of("bedrock")

                override fun close() {}
            }
        val client =
            AnthropicOkHttpClient.builder()
                .backend(backend)
                .maxRetries(0)
                .openTelemetry(options())
                .build()
        val asyncClient =
            AnthropicOkHttpClientAsync.builder()
                .backend(backend)
                .maxRetries(0)
                .openTelemetry(options())
                .build()

        client.messages().create(messageParams())
        asyncClient.messages().create(messageParams()).get(10, TimeUnit.SECONDS)

        assertThat(recorder.await(2).map { it.instrumentationScopeInfo.name })
            .containsOnly("com.anthropic.sdk.java.bedrock")
        assertThat(recorder.spans.map { it.instrumentationScopeInfo.version })
            .containsOnly(getPackageVersion())
    }

    @Test
    fun serverAttributes_comeFromTheBaseUrlOfThePlatform() {
        val servers =
            listOf(
                "https://bedrock-runtime.us-east-1.amazonaws.com",
                "https://us-east5-aiplatform.googleapis.com",
                "http://[::1]:8080/proxy",
                "http://gateway.internal",
            )
        for (server in servers) {
            val clientOptions =
                ClientOptions.builder()
                    .httpClient(FakeHttpClient(MESSAGE_JSON))
                    .baseUrl(server)
                    .openTelemetry(options())
                    .build()
            AnthropicClientImpl(clientOptions).messages().create(messageParams())
        }

        assertThat(recorder.spans.map { it.string("server.address") to it.long("server.port") })
            .containsExactly(
                "bedrock-runtime.us-east-1.amazonaws.com" to 443L,
                "us-east5-aiplatform.googleapis.com" to 443L,
                "::1" to 8080L,
                "gateway.internal" to 80L,
            )
    }

    @Test
    fun serverAttributes_areNotSetAfterTheSpanStarts() {
        val setAfterStart = CopyOnWriteArrayList<String>()
        val span =
            object : Span by Span.getInvalid() {
                override fun isRecording(): Boolean = true

                override fun <T : Any> setAttribute(key: AttributeKey<T>, value: T?): Span {
                    setAfterStart.add(key.key)
                    return this
                }
            }
        val clientOptions =
            ClientOptions.builder()
                .httpClient(FakeHttpClient("{}"))
                .openTelemetry(options())
                .build()
        val request =
            HttpRequest.builder().method(HttpMethod.GET).baseUrl("http://localhost").build()

        traceApiCall(clientOptions, ApiCall(span, clientOptions)) { client ->
                client.execute(request, RequestOptions.none()).parseable { "parsed" }
            }
            .parse()

        assertThat(setAfterStart)
            .contains("http.response.status_code")
            .doesNotContain("server.address", "server.port")
    }

    @Test
    fun concurrentCallsSharingRequestOptions_getSeparateSpans() {
        stubMessage()
        val requestOptions =
            RequestOptions.builder()
                .openTelemetry(
                    OpenTelemetryRequestOptions.builder().conversationId("conv_1").build()
                )
                .build()
        val calls = 8
        val executor = Executors.newFixedThreadPool(calls)
        val start = CountDownLatch(1)
        try {
            val futures =
                (1..calls).map {
                    executor.submit {
                        start.await()
                        client.messages().create(messageParams(), requestOptions)
                    }
                }
            start.countDown()
            futures.forEach { it.get(30, TimeUnit.SECONDS) }
        } finally {
            executor.shutdown()
        }

        val spans = recorder.await(calls)
        assertThat(spans.map { it.spanId }.toSet()).hasSize(calls)
        assertThat(spans.map { it.traceId }.toSet()).hasSize(calls)
        assertThat(spans).allSatisfy {
            assertThat(it.status.statusCode).isEqualTo(StatusCode.OK)
            assertThat(it.parentSpanContext.isValid).isFalse()
            assertThat(it.string("gen_ai.conversation.id")).isEqualTo("conv_1")
        }
        val traceparents =
            findAll(postRequestedFor(urlPathEqualTo("/v1/messages"))).map {
                it.getHeader("traceparent")
            }
        assertThat(traceparents.toSet()).hasSize(calls)
    }

    @Test
    fun concurrentAsyncCallsSharingRequestOptions_getSeparateSpans() {
        stubMessage()
        val client = asyncClient()
        val requestOptions =
            RequestOptions.builder()
                .openTelemetry(
                    OpenTelemetryRequestOptions.builder().conversationId("conv_1").build()
                )
                .build()
        val calls = 8

        val futures = (1..calls).map { client.messages().create(messageParams(), requestOptions) }
        CompletableFuture.allOf(*futures.toTypedArray()).get(30, TimeUnit.SECONDS)

        val spans = recorder.await(calls)
        assertThat(spans.map { it.spanId }.toSet()).hasSize(calls)
        assertThat(spans).allSatisfy {
            assertThat(it.status.statusCode).isEqualTo(StatusCode.OK)
            assertThat(it.string("gen_ai.response.id")).isEqualTo("msg_1")
            assertThat(it.string("gen_ai.conversation.id")).isEqualTo("conv_1")
        }
    }

    @Test
    fun canceledAsyncCall_leavesStatusUnset() {
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .willReturn(
                    ok(MESSAGE_JSON.trimIndent())
                        .withHeader("Content-Type", "application/json")
                        .withFixedDelay(2000)
                )
        )
        val client = asyncClient()

        val future = client.messages().create(messageParams())
        Thread.sleep(200)
        future.cancel(true)
        assertThrows<CancellationException> { future.get() }

        val span = recorder.await(1).single()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.UNSET)
        assertThat(span.string("error.type")).isNull()
    }
}

/** What the structured output helper derives an output format from. */
internal class Forecast(val summary: String)
