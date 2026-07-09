package com.anthropic.helpers

import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentMessageEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsStreamSessionEvents
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEventAccumulatorTest {

    private val jsonMapper = jsonMapper()

    private fun sseEvent(json: String): BetaManagedAgentsStreamSessionEvents =
        jsonMapper.readValue(json, jacksonTypeRef<BetaManagedAgentsStreamSessionEvents>())

    private fun eventStart(eventId: String): BetaManagedAgentsStreamSessionEvents =
        sseEvent("""{"type":"event_start","event":{"type":"agent.message","id":"$eventId"}}""")

    private fun eventDelta(
        eventId: String,
        text: String,
        index: Int,
    ): BetaManagedAgentsStreamSessionEvents =
        sseEvent(
            """
            {"type":"event_delta","event_id":"$eventId","delta":{"type":"content_delta","index":$index,"content":{"type":"text","text":"$text"}}}
            """
                .trimIndent()
        )

    private fun feed(
        accumulator: BetaManagedAgentsEventAccumulator,
        vararg events: BetaManagedAgentsStreamSessionEvents,
    ) = events.forEach(accumulator::accumulate)

    @Test
    fun startOpensEmptyPreview() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        feed(accumulator, eventStart("evt_1"))

        val message = accumulator.agentMessages().getValue("evt_1")
        assertThat(message.id()).isEqualTo("evt_1")
        assertThat(message.type()).isEqualTo(BetaManagedAgentsAgentMessageEvent.Type.AGENT_MESSAGE)
        assertThat(message.content()).isEmpty()
    }

    @Test
    fun previewProcessedAtThrowsUntilReconciled() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        feed(accumulator, eventStart("evt_1"))

        // Previews carry no timestamp: `processedAt()` throws until the canonical event arrives.
        val preview = accumulator.agentMessages().getValue("evt_1")
        assertThat(preview._processedAt().isMissing()).isTrue()
        assertThatThrownBy { preview.processedAt() }
            .isInstanceOf(AnthropicInvalidDataException::class.java)

        feed(
            accumulator,
            sseEvent(
                """{"type":"agent.message","id":"evt_1","processed_at":"2024-01-01T00:00:00Z","content":[{"type":"text","text":"complete"}]}"""
            ),
        )

        assertThat(accumulator.agentMessages().getValue("evt_1").processedAt()).isNotNull()
    }

    @Test
    fun accumulateReturnsItsArgument() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        val event = eventStart("evt_1")
        assertThat(accumulator.accumulate(event)).isSameAs(event)
    }

    @Test
    fun startIgnoresNonAgentMessage() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        feed(
            accumulator,
            sseEvent("""{"type":"event_start","event":{"type":"agent.thinking","id":"evt_1"}}"""),
        )

        assertThat(accumulator.agentMessages()).doesNotContainKey("evt_1")
    }

    @Test
    fun deltaForIgnoredPreviewIsNoOp() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        feed(
            accumulator,
            sseEvent("""{"type":"event_start","event":{"type":"agent.thinking","id":"evt_1"}}"""),
            eventDelta("evt_1", "x", 0),
        )

        assertThat(accumulator.agentMessages()).doesNotContainKey("evt_1")
    }

    @Test
    fun deltaAppendsAndInserts() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        feed(
            accumulator,
            eventStart("evt_1"),
            eventDelta("evt_1", "Hel", 0),
            eventDelta("evt_1", "lo", 0),
            eventDelta("evt_1", "World", 1),
        )

        val content = accumulator.agentMessages().getValue("evt_1").content()
        assertThat(content).hasSize(2)
        assertThat(content[0].text()).isEqualTo("Hello")
        assertThat(content[1].text()).isEqualTo("World")
        assertThat(accumulator.agentMessageText("evt_1")).contains("HelloWorld")
    }

    @Test
    fun deltaDefaultsIndexZero() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        feed(
            accumulator,
            eventStart("evt_1"),
            sseEvent(
                """{"type":"event_delta","event_id":"evt_1","delta":{"type":"content_delta","content":{"type":"text","text":"a"}}}"""
            ),
            sseEvent(
                """{"type":"event_delta","event_id":"evt_1","delta":{"type":"content_delta","content":{"type":"text","text":"b"}}}"""
            ),
        )

        assertThat(accumulator.agentMessageText("evt_1")).contains("ab")
    }

    @Test
    fun deltaBeforeStartIsNoOp() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        feed(accumulator, eventDelta("evt_1", "x", 0))

        assertThat(accumulator.agentMessages()).doesNotContainKey("evt_1")
    }

    @Test
    fun outOfRangeIndexIsNoOp() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        feed(
            accumulator,
            eventStart("evt_1"),
            eventDelta("evt_1", "x", 2),
            eventDelta("evt_1", "y", -1),
            // An index that would truncate to 0 if narrowed to Int.
            sseEvent(
                """{"type":"event_delta","event_id":"evt_1","delta":{"type":"content_delta","index":4294967296,"content":{"type":"text","text":"z"}}}"""
            ),
        )

        assertThat(accumulator.agentMessages().getValue("evt_1").content()).isEmpty()
    }

    @Test
    fun bufferedEventReplacesPreview() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        feed(
            accumulator,
            eventStart("evt_1"),
            eventDelta("evt_1", "partial", 0),
            sseEvent(
                """{"type":"agent.message","id":"evt_1","processed_at":"2024-01-01T00:00:00Z","content":[{"type":"text","text":"complete"}]}"""
            ),
        )

        assertThat(accumulator.agentMessageText("evt_1")).contains("complete")
    }

    @Test
    fun modelRequestEndClearsPreviews() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        feed(
            accumulator,
            eventStart("evt_1"),
            eventDelta("evt_1", "x", 0),
            sseEvent(
                """{"type":"span.model_request_end","id":"sevt_2","model_request_start_id":"sevt_1","is_error":true,"processed_at":"2024-01-01T00:00:00Z"}"""
            ),
        )

        assertThat(accumulator.agentMessages()).isEmpty()
    }

    @Test
    fun stragglerDeltaAfterCanonicalEventIsDropped() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        feed(
            accumulator,
            eventStart("evt_1"),
            eventDelta("evt_1", "partial", 0),
            sseEvent(
                """{"type":"agent.message","id":"evt_1","processed_at":"2024-01-01T00:00:00Z","content":[{"type":"text","text":"complete"}]}"""
            ),
            eventDelta("evt_1", "straggler", 0),
        )

        assertThat(accumulator.agentMessageText("evt_1")).contains("complete")
    }

    @Test
    fun duplicateStartDoesNotClobberPreview() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        feed(
            accumulator,
            eventStart("evt_1"),
            eventDelta("evt_1", "partial", 0),
            eventStart("evt_1"),
        )

        assertThat(accumulator.agentMessageText("evt_1")).contains("partial")
    }

    @Test
    fun duplicateStartDoesNotClobberCanonicalMessage() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        feed(
            accumulator,
            eventStart("evt_1"),
            sseEvent(
                """{"type":"agent.message","id":"evt_1","processed_at":"2024-01-01T00:00:00Z","content":[{"type":"text","text":"complete"}]}"""
            ),
            eventStart("evt_1"),
        )

        assertThat(accumulator.agentMessageText("evt_1")).contains("complete")
        assertThat(accumulator.agentMessages().getValue("evt_1")._processedAt().isMissing())
            .isFalse()
    }

    @Test
    fun modelRequestEndKeepsCanonicalMessages() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        feed(
            accumulator,
            eventStart("evt_1"),
            eventDelta("evt_1", "partial", 0),
            sseEvent(
                """{"type":"agent.message","id":"evt_1","processed_at":"2024-01-01T00:00:00Z","content":[{"type":"text","text":"complete"}]}"""
            ),
            eventStart("evt_2"),
            eventDelta("evt_2", "open", 0),
            sseEvent(
                """{"type":"span.model_request_end","id":"sevt_2","model_request_start_id":"sevt_1","is_error":true,"processed_at":"2024-01-01T00:00:00Z"}"""
            ),
        )

        assertThat(accumulator.agentMessageText("evt_1")).contains("complete")
        assertThat(accumulator.agentMessages()).doesNotContainKey("evt_2")
    }

    @Test
    fun otherEventsAreNoOps() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        feed(
            accumulator,
            sseEvent(
                """{"type":"session.status_running","id":"sevt_1","processed_at":"2024-01-01T00:00:00Z"}"""
            ),
        )

        assertThat(accumulator.agentMessages()).isEmpty()
    }

    @Test
    fun multiplePreviews() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        feed(
            accumulator,
            eventStart("evt_a"),
            eventDelta("evt_a", "alpha", 0),
            eventStart("evt_b"),
            eventDelta("evt_b", "beta", 0),
            sseEvent("""{"type":"event_start","event":{"type":"agent.thinking","id":"evt_c"}}"""),
        )

        assertThat(accumulator.agentMessages()).hasSize(2)
        assertThat(accumulator.agentMessageText("evt_a")).contains("alpha")
        assertThat(accumulator.agentMessageText("evt_b")).contains("beta")
    }

    @Test
    fun agentMessagesIterateInInsertionOrder() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()
        for (id in listOf("evt_c", "evt_a", "evt_b")) {
            accumulator.accumulate(eventStart(id))
        }
        // Replacing with the canonical event must not change the id's position.
        accumulator.accumulate(
            sseEvent(
                """{"type":"agent.message","id":"evt_a","processed_at":"2024-01-01T00:00:00Z","content":[{"type":"text","text":"alpha"}]}"""
            )
        )

        assertThat(accumulator.agentMessages().keys).containsExactly("evt_c", "evt_a", "evt_b")
    }

    @Test
    fun emptyAccumulator() {
        val accumulator = BetaManagedAgentsEventAccumulator.create()

        assertThat(accumulator.agentMessageText("evt_1")).isEmpty()
        assertThat(accumulator.agentMessages()).isEmpty()
    }
}
