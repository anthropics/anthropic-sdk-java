package com.anthropic.internal.telemetry

import com.anthropic.client.AnthropicClient
import com.anthropic.core.JsonNumber
import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.core.opentelemetry.OpenTelemetryContentMode
import com.anthropic.core.opentelemetry.OpenTelemetryOptions
import com.anthropic.models.beta.messages.BetaJsonOutputFormat
import com.anthropic.models.beta.messages.BetaOutputConfig
import com.anthropic.models.beta.sessions.BetaManagedAgentsAgentWithOverridesParams
import com.anthropic.models.beta.sessions.SessionCreateParams
import com.anthropic.models.messages.JsonOutputFormat
import com.anthropic.models.messages.OutputConfig
import com.fasterxml.jackson.databind.DeserializationFeature
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import io.opentelemetry.api.common.AttributeKey
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.api.trace.SpanKind
import io.opentelemetry.context.Context
import io.opentelemetry.sdk.trace.SdkTracerProvider
import io.opentelemetry.sdk.trace.data.LinkData
import io.opentelemetry.sdk.trace.samplers.Sampler
import io.opentelemetry.sdk.trace.samplers.SamplingResult
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicInteger
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/** The attributes that the SDK works out from a request or a response. */
@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class ComputedAttributesTest : TracingTestSupport() {

    /** A number that counts its reads. Writing the JSON that it is part of reads it. */
    private class CountedNumber : Number() {
        val reads = AtomicInteger()

        private fun read(): Int {
            reads.incrementAndGet()
            return 1
        }

        override fun toString(): String = read().toString()

        override fun toByte(): Byte = read().toByte()

        override fun toShort(): Short = read().toShort()

        override fun toInt(): Int = read()

        override fun toLong(): Long = read().toLong()

        override fun toFloat(): Float = read().toFloat()

        override fun toDouble(): Double = read().toDouble()
    }

    /** Samples no span, and keeps the [attribute] and the reads of [number] as each span starts. */
    private class DroppingSampler(
        private val attribute: AttributeKey<String>,
        private val number: CountedNumber,
    ) : Sampler {
        val seenAtStart = CopyOnWriteArrayList<Pair<String?, Int>>()

        override fun shouldSample(
            parentContext: Context,
            traceId: String,
            name: String,
            spanKind: SpanKind,
            attributes: Attributes,
            parentLinks: List<LinkData>,
        ): SamplingResult {
            seenAtStart.add(attributes.get(attribute) to number.reads.get())
            return SamplingResult.drop()
        }

        override fun getDescription(): String = "samples nothing"
    }

    private fun clientSampledBy(sampler: Sampler): AnthropicClient =
        clientBuilder()
            .openTelemetry(
                OpenTelemetryOptions.builder()
                    .tracerProvider(SdkTracerProvider.builder().setSampler(sampler).build())
                    .build()
            )
            .build()

    /**
     * Checks that [call] reads [number] as often with [client], which samples no span, as with a
     * client whose spans are off, which reads it to send the request's body.
     */
    private fun assertReadsOnlyToSend(
        number: CountedNumber,
        client: AnthropicClient,
        call: (AnthropicClient) -> Unit,
    ) {
        number.reads.set(0)
        call(clientBuilder().openTelemetry(false).build())
        val readsToSend = number.reads.getAndSet(0)
        assertThat(readsToSend).isPositive()

        call(client)

        assertThat(number.reads).hasValue(readsToSend)
    }

    @Test
    fun messagesCreate_withAnOutputFormat_readsTheSchemaOnlyToSendIt() {
        stubMessage()
        val number = CountedNumber()
        val schema = mapOf("maxItems" to JsonNumber.of(number))
        val format =
            JsonOutputFormat.builder()
                .schema(JsonOutputFormat.Schema.builder().additionalProperties(schema).build())
                .build()
        val betaFormat =
            BetaJsonOutputFormat.builder()
                .schema(BetaJsonOutputFormat.Schema.builder().additionalProperties(schema).build())
                .build()
        val sampler = DroppingSampler(AttributeKey.stringKey("gen_ai.output.type"), number)
        val client = clientSampledBy(sampler)
        val calls =
            listOf<(AnthropicClient) -> Unit>(
                {
                    it.messages()
                        .create(
                            messageParams()
                                .toBuilder()
                                .outputConfig(OutputConfig.builder().format(format).build())
                                .build()
                        )
                },
                {
                    it.beta()
                        .messages()
                        .create(
                            betaMessageParams()
                                .toBuilder()
                                .outputConfig(BetaOutputConfig.builder().format(betaFormat).build())
                                .build()
                        )
                },
                {
                    it.beta()
                        .messages()
                        .create(betaMessageParams().toBuilder().outputFormat(betaFormat).build())
                },
            )

        for (call in calls) {
            assertReadsOnlyToSend(number, client, call)
        }

        assertThat(sampler.seenAtStart).containsExactly("json" to 0, "json" to 0, "json" to 0)
    }

    @Test
    fun sessionsCreate_withAnAgentObject_readsTheRestOfTheAgentOnlyToSendIt() {
        stubFor(
            post(urlPathEqualTo("/v1/sessions"))
                .willReturn(
                    ok("""{"id": "sesn_1"}""").withHeader("Content-Type", "application/json")
                )
        )
        val number = CountedNumber()
        val sampler = DroppingSampler(AttributeKey.stringKey("gen_ai.agent.id"), number)

        assertReadsOnlyToSend(number, clientSampledBy(sampler)) {
            it.beta()
                .sessions()
                .create(
                    SessionCreateParams.builder()
                        .agent(
                            BetaManagedAgentsAgentWithOverridesParams.builder()
                                .id("agent_1")
                                .putAdditionalProperty("override", JsonNumber.of(number))
                                .build()
                        )
                        .environmentId("env_1")
                        .build()
                )
        }

        assertThat(sampler.seenAtStart).containsExactly("agent_1" to 0)
    }

    @ParameterizedTest
    @ValueSource(
        strings =
            [
                """"input_tokens": 10.5""",
                """"input_tokens": 10000000000000000000000""",
                """"cache_read_input_tokens": 2.5""",
                """"cache_creation_input_tokens": "3"""",
            ]
    )
    fun messagesCreate_withInputTokensThatAreNoCount_recordsNoSumOfThem(property: String) {
        val name = property.substringBefore(':')
        stubMessage(MESSAGE_JSON.replace(Regex("""$name: \d+"""), property))

        client.messages().create(messageParams())
        client.beta().messages().create(betaMessageParams())

        assertThat(recorder.spans).hasSize(2).allSatisfy { span ->
            assertThat(span.string("gen_ai.response.id")).isEqualTo("msg_1")
            assertThat(span.long("gen_ai.usage.output_tokens")).isEqualTo(5)
            assertThat(attributesOf(span)).doesNotContainKey("gen_ai.usage.input_tokens")
        }
    }

    @Test
    fun messagesCreate_withCachedInputTokensThatAreNull_sumsTheOthers() {
        stubMessage(
            MESSAGE_JSON.replace(
                "\"cache_read_input_tokens\": 2",
                "\"cache_read_input_tokens\": null",
            )
        )

        client.messages().create(messageParams())
        client.beta().messages().create(betaMessageParams())

        assertThat(recorder.spans).hasSize(2).allSatisfy { span ->
            assertThat(span.long("gen_ai.usage.input_tokens")).isEqualTo(13)
        }
    }

    @Test
    fun messagesCreate_withOutputTokensThatALongCannotHold_recordsNone() {
        stubMessage(
            MESSAGE_JSON.replace(
                "\"output_tokens\": 5",
                "\"output_tokens\": 10000000000000000000000",
            )
        )

        client.messages().create(messageParams())
        client.beta().messages().create(betaMessageParams())

        assertThat(recorder.spans).hasSize(2).allSatisfy { span ->
            assertThat(span.long("gen_ai.usage.input_tokens")).isEqualTo(15)
            assertThat(attributesOf(span)).doesNotContainKey("gen_ai.usage.output_tokens")
        }
    }

    @Test
    fun messagesCreate_withFractionsThatTheJsonMapperReadsAsBigDecimals_recordsNone() {
        stubMessage(
            MESSAGE_JSON.replace("\"input_tokens\": 10,", "\"input_tokens\": 10.5,")
                .replace("\"output_tokens\": 5,", "\"output_tokens\": 5.5,")
        )
        val client =
            clientBuilder()
                .jsonMapper(
                    jsonMapper()
                        .rebuild()
                        .enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                        .build()
                )
                .build()

        client.messages().create(messageParams())
        client.beta().messages().create(betaMessageParams())

        assertThat(recorder.spans).hasSize(2).allSatisfy { span ->
            assertThat(span.string("gen_ai.response.id")).isEqualTo("msg_1")
            assertThat(attributesOf(span))
                .doesNotContainKeys("gen_ai.usage.input_tokens", "gen_ai.usage.output_tokens")
        }
    }

    private fun stubMessageWithIterations(vararg iterations: String) =
        stubMessage(
            MESSAGE_JSON.replace(
                "\"usage\": {",
                "\"usage\": {\"iterations\": [${iterations.joinToString()}],",
            )
        )

    @Test
    fun messagesCreate_withIterations_sumsTheTokensOfTheCompactions() {
        stubMessageWithIterations(
            """"not an iteration"""",
            """{"type": "message", "input_tokens": 1000, "output_tokens": 1000}""",
            """{"type": "compaction", "input_tokens": 100, "cache_read_input_tokens": 20, "cache_creation_input_tokens": 3, "output_tokens": 40}""",
            """{"type": "compaction", "input_tokens": 50, "cache_read_input_tokens": null, "output_tokens": 10}""",
        )

        client.messages().create(messageParams())
        client.beta().messages().create(betaMessageParams())

        assertThat(recorder.spans).hasSize(2).allSatisfy { span ->
            assertThat(span.long("anthropic.usage.compaction.input_tokens")).isEqualTo(173)
            assertThat(span.long("anthropic.usage.compaction.output_tokens")).isEqualTo(50)
        }
    }

    @Test
    fun messagesCreate_withACompactionWhoseInputTokensAreNoCount_recordsNoSumOfThem() {
        stubMessageWithIterations(
            """{"type": "compaction", "input_tokens": 100, "output_tokens": 40}""",
            """{"type": "compaction", "input_tokens": 50, "cache_read_input_tokens": "20", "output_tokens": 10}""",
        )

        client.messages().create(messageParams())
        client.beta().messages().create(betaMessageParams())

        assertThat(recorder.spans).hasSize(2).allSatisfy { span ->
            assertThat(attributesOf(span))
                .doesNotContainKey("anthropic.usage.compaction.input_tokens")
            assertThat(span.long("anthropic.usage.compaction.output_tokens")).isEqualTo(50)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["", """, "usage": null"""])
    fun messagesCreate_withoutUsage_recordsTheRestOfTheMessage(usage: String) {
        stubMessage(
            """
            {
              "id": "msg_1", "type": "message", "role": "assistant", "model": "claude-served",
              "content": [{"type": "text", "text": "Hi there"}],
              "stop_reason": "end_turn"$usage
            }
            """
        )

        client.messages().create(messageParams())
        client.beta().messages().create(betaMessageParams())

        assertThat(recorder.spans).hasSize(2).allSatisfy { span ->
            assertThat(span.string("gen_ai.response.id")).isEqualTo("msg_1")
            assertThat(span.string("gen_ai.response.model")).isEqualTo("claude-served")
            assertThat(span.string("anthropic.message.stop_reason")).isEqualTo("end_turn")
            assertThat(attributesOf(span).keys).noneMatch { it.contains(".usage.") }
        }
    }

    @Test
    fun messagesCreate_withFieldsSetAsRawJson_recordsTheOtherFields() {
        stubMessage()
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val raw = JsonValue.from(mapOf("raw" to 1))

        client
            .messages()
            .create(messageParams().toBuilder().serviceTier(raw).outputConfig(raw).build())
        client
            .beta()
            .messages()
            .create(
                betaMessageParams()
                    .toBuilder()
                    .serviceTier(raw)
                    .outputConfig(raw)
                    .putAdditionalHeader("anthropic-beta", "beta-one, beta-two")
                    .build()
            )

        assertThat(recorder.spans).hasSize(2).allSatisfy { span ->
            assertThat(span.string("gen_ai.request.model")).isEqualTo("claude-requested")
            assertThat(span.long("gen_ai.request.max_tokens")).isEqualTo(100)
            assertThat(span.strings("anthropic.beta")).containsExactly("beta-one", "beta-two")
            assertThat(attributesOf(span))
                .containsKeys("gen_ai.input.messages", "gen_ai.output.messages")
                .doesNotContainKeys("anthropic.request.service_tier", "gen_ai.output.type")
        }
    }

    @Test
    fun messagesCreate_withStopSequencesThatAreNotAListOfStrings_recordsNone() {
        stubMessage()
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        for (stopSequences in listOf(listOf("A", 1, "B"), mapOf("first" to "A"))) {
            client
                .messages()
                .create(
                    messageParams().toBuilder().stopSequences(JsonValue.from(stopSequences)).build()
                )
        }

        assertThat(recorder.spans).hasSize(2).allSatisfy { span ->
            assertThat(span.string("gen_ai.input.messages")).isNotNull()
            assertThat(attributesOf(span)).doesNotContainKey("gen_ai.request.stop_sequences")
        }
    }
}
