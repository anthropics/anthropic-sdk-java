package com.anthropic.internal.telemetry

import com.anthropic.core.ClientOptions
import com.anthropic.core.Params
import com.anthropic.core.jsonMapper
import com.anthropic.core.opentelemetry.ApiCall
import com.anthropic.helpers.FakeHttpClient
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.MessageCreateParams as BetaMessageCreateParams
import com.anthropic.models.messages.Message
import com.anthropic.models.messages.MessageCreateParams
import com.fasterxml.jackson.module.kotlin.readValue
import io.opentelemetry.api.common.AttributeKey
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.api.trace.Span
import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ResponseAttributesTest {

    companion object {

        // A Message with the properties of its usage and its other scalar properties set, so that a
        // response row shows whichever of them it reads. The stable API parses those that it has.
        private const val FULL_MESSAGE_JSON =
            """
            {
              "id": "msg_1", "type": "message", "role": "assistant", "model": "claude-served",
              "content": [{"type": "text", "text": "Hi there"}],
              "container": {"id": "container_1", "expires_at": "2026-01-01T00:00:00Z", "skills": []},
              "context_management": {"applied_edits": []},
              "stop_reason": "stop_sequence",
              "stop_sequence": "END",
              "usage": {
                "input_tokens": 10, "output_tokens": 7,
                "output_tokens_details": {"thinking_tokens": 4},
                "cache_creation_input_tokens": 3, "cache_read_input_tokens": 2,
                "cache_creation": {"ephemeral_5m_input_tokens": 1, "ephemeral_1h_input_tokens": 2},
                "inference_geo": "global",
                "iterations": [],
                "server_tool_use": {"web_fetch_requests": 1, "web_search_requests": 1},
                "service_tier": "standard",
                "speed": "standard"
              }
            }
            """

        // The response rows whose values are final only from `message_delta` on: until then the
        // streamed Message has the placeholders of `message_start` for them.
        private val MESSAGE_DELTA_ATTRIBUTES =
            setOf(
                "gen_ai.usage.output_tokens",
                "gen_ai.usage.reasoning.output_tokens",
                "anthropic.message.stop_reason",
            )

        @JvmStatic
        fun messagesCreate(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    MessageCreateParams.builder()
                        .model("claude-requested")
                        .maxTokens(100)
                        .addUserMessage("hello")
                        .build(),
                    jsonMapper().readValue<Message>(FULL_MESSAGE_JSON),
                ),
                Arguments.of(
                    BetaMessageCreateParams.builder()
                        .model("claude-requested")
                        .maxTokens(100)
                        .addUserMessage("hello")
                        .build(),
                    jsonMapper().readValue<BetaMessage>(FULL_MESSAGE_JSON),
                ),
            )
    }

    /**
     * A tripwire: the generated response rows of `anthropic.messages.create` are each either in
     * `MESSAGE_START_ATTRIBUTES` (`ResponseAttributes.kt`), the only ones on the span of a stream
     * that ends early, or in [MESSAGE_DELTA_ATTRIBUTES]. It sees a row only when
     * [FULL_MESSAGE_JSON] sets what the row reads.
     */
    @ParameterizedTest
    @MethodSource("messagesCreate")
    fun messageStartAttributes_classifyEveryResponseRowOfMessagesCreate(
        params: Params,
        message: Any,
    ) {
        val rows =
            params._spanData().responseAttributes(message).asMap().keys.map { it.key }.toSet()

        assertThat(rows - MESSAGE_START_ATTRIBUTES - MESSAGE_DELTA_ATTRIBUTES)
            .describedAs(
                "Response rows that are not classified. Add each to MESSAGE_START_ATTRIBUTES in " +
                    "ResponseAttributes.kt when `message_start` already has its value for good, " +
                    "and to MESSAGE_DELTA_ATTRIBUTES in this test otherwise"
            )
            .isEmpty()
        assertThat(MESSAGE_START_ATTRIBUTES - rows)
            .describedAs(
                "Entries of MESSAGE_START_ATTRIBUTES in ResponseAttributes.kt that are no " +
                    "response row any more, or that FULL_MESSAGE_JSON in this test does not set. " +
                    "Remove them, or set them"
            )
            .isEmpty()
        assertThat(MESSAGE_DELTA_ATTRIBUTES - rows)
            .describedAs(
                "Entries of MESSAGE_DELTA_ATTRIBUTES in this test that are no response row any " +
                    "more, or that FULL_MESSAGE_JSON does not set. Remove them, or set them"
            )
            .isEmpty()
    }

    @Test
    fun streamedMessageAttributes_untilMessageDelta_leaveOutARowThatIsNotKnownToBeFinal() {
        val id = AttributeKey.stringKey("gen_ai.response.id")
        val call =
            ApiCall(
                Span.getInvalid(),
                ClientOptions.builder().httpClient(FakeHttpClient()).build(),
                responseAttributes = {
                    Attributes.of(id, "msg_1", AttributeKey.stringKey("anthropic.new_row"), "new")
                },
            )

        assertThat(streamedMessageAttributes(call, Any(), complete = false).asMap())
            .containsOnlyKeys(id)
        assertThat(streamedMessageAttributes(call, Any(), complete = true).asMap()).hasSize(2)
    }

    @ParameterizedTest
    @MethodSource("messagesCreate")
    fun streamedMessageAttributes_untilMessageDelta_leaveOutWhatTheEventDelivers(
        params: Params,
        message: Any,
    ) {
        val call =
            ApiCall(
                Span.getInvalid(),
                ClientOptions.builder().httpClient(FakeHttpClient()).build(),
                responseAttributes = params._spanData().responseAttributes,
            )

        fun rows(complete: Boolean) =
            streamedMessageAttributes(call, message, complete).asMap().keys.map { it.key }

        assertThat(rows(complete = false))
            .containsExactlyInAnyOrderElementsOf(MESSAGE_START_ATTRIBUTES)
        assertThat(rows(complete = true))
            .containsExactlyInAnyOrderElementsOf(
                MESSAGE_START_ATTRIBUTES + MESSAGE_DELTA_ATTRIBUTES
            )
    }
}
