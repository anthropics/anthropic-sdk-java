package com.anthropic.helpers

import com.anthropic.core.JsonMissing
import com.anthropic.models.beta.sessions.BetaManagedAgentsDeltaContent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsAgentMessageEvent
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsStreamSessionEvents
import java.util.Collections
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Folds `event_start` / `event_delta` preview events from a session stream into per-event-id
 * `agent.message` snapshots. Deltas are lossy previews; the buffered `agent.message` event is
 * canonical and replaces the preview. `span.model_request_end` discards open previews; reconciled
 * canonical messages survive.
 *
 * Not thread-safe, like the other helpers in this package.
 */
class BetaManagedAgentsEventAccumulator private constructor() {

    private val agentMessages: MutableMap<String, BetaManagedAgentsAgentMessageEvent> =
        linkedMapOf()

    companion object {
        @JvmStatic fun create() = BetaManagedAgentsEventAccumulator()
    }

    /**
     * Accumulates one streamed session event and returns it unchanged. Deltas that cannot be
     * applied (unknown event id, out-of-range index, unknown fragment type, arriving after the
     * canonical event) are ignored, as is an `event_start` for an id that is already present.
     */
    fun accumulate(
        event: BetaManagedAgentsStreamSessionEvents
    ): BetaManagedAgentsStreamSessionEvents {
        event.eventStart().getOrNull()?.let { start ->
            // `agent.thinking` previews are start-only today; only `agent.message` accumulates.
            val preview = start.event().agentMessage().getOrNull() ?: return event
            // Seed only when absent: a duplicate `event_start` must not clobber accumulated
            // preview content or a reconciled canonical message.
            if (agentMessages.containsKey(preview.id())) {
                return event
            }
            agentMessages[preview.id()] =
                BetaManagedAgentsAgentMessageEvent.builder()
                    .id(preview.id())
                    .content(listOf())
                    .processedAt(JsonMissing.of())
                    .type(BetaManagedAgentsAgentMessageEvent.Type.AGENT_MESSAGE)
                    .build()
            return event
        }

        event.eventDelta().getOrNull()?.let { delta ->
            val message = agentMessages[delta.eventId()] ?: return event
            // A present `processed_at` marks the canonical event: straggler deltas
            // must not append onto canonical content.
            if (!message._processedAt().isMissing()) {
                return event
            }
            val fragment = delta.delta()
            if (fragment.type() != BetaManagedAgentsDeltaContent.Type.CONTENT_DELTA) {
                return event
            }
            val content = message.content().toMutableList()
            val index = fragment.index().getOrNull() ?: 0L
            when {
                index < 0L || index > content.size -> return event
                index == content.size.toLong() -> content.add(fragment.content())
                else -> {
                    val i = index.toInt()
                    content[i] =
                        content[i]
                            .toBuilder()
                            .text(content[i].text() + fragment.content().text())
                            .build()
                }
            }
            agentMessages[delta.eventId()] = message.toBuilder().content(content).build()
            return event
        }

        event.agentMessage().getOrNull()?.let { message ->
            agentMessages[message.id()] = message
            return event
        }

        if (event.isSpanModelRequestEnd()) {
            // No buffered events are coming for open previews; canonical messages
            // (present `processed_at`) were delivered and survive.
            agentMessages.values.removeIf { it._processedAt().isMissing() }
        }

        return event
    }

    /**
     * An unmodifiable live view of the current snapshots, keyed by event id in insertion order.
     * Preview snapshots have no `processed_at` until reconciled — check
     * [BetaManagedAgentsAgentMessageEvent._processedAt] before calling the typed accessor.
     */
    fun agentMessages(): Map<String, BetaManagedAgentsAgentMessageEvent> =
        Collections.unmodifiableMap(agentMessages)

    /** The joined text of the snapshot with the given event id, or empty if unknown. */
    fun agentMessageText(eventId: String): Optional<String> =
        Optional.ofNullable(agentMessages[eventId]).map { message ->
            message.content().joinToString("") { it.text() }
        }
}
