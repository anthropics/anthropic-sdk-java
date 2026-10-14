@file:JvmName("StreamRecordings")

package com.anthropic.internal.telemetry

import com.anthropic.core.ClientOptions
import com.anthropic.core.opentelemetry.ApiCall
import com.anthropic.core.opentelemetry.StreamRecording
import com.fasterxml.jackson.databind.JsonNode
import io.opentelemetry.api.trace.Span

private const val NANOS_PER_SECOND = 1e9

/**
 * The SDK's accumulator of a streamed Message, stable or beta, as the recording of the stream uses
 * it.
 */
internal class StreamedMessage<E>(
    /** Feeds an event to the accumulator, which throws when it rejects it. */
    val accumulate: (E) -> Unit,
    /**
     * Whether the Message's attributes need the event: `message_start` and `message_delta` carry
     * them, and a `content_block_start` can, since the beta accumulator takes the serving model
     * from a fallback block. Every other event only adds content.
     */
    val carriesAttributes: (E) -> Boolean,
    /**
     * Whether the event is `message_delta`, which carries the Message's stop reason and its output
     * tokens.
     */
    val isMessageDelta: (E) -> Boolean,
    val isMessageStop: (E) -> Boolean,
    val isContentBlockDelta: (E) -> Boolean,
    /**
     * Sets on the span what the accumulator has of the Message so far, which is nothing before
     * `message_start`: all its attributes when `ended`, which is once `message_delta` has arrived,
     * and otherwise the ones that `message_start` carries.
     */
    val setMessageAttributes: (Span, ended: Boolean) -> Unit,
    /**
     * The content blocks of the Message as far as the accumulator has them, which is all of them
     * once it has taken `message_stop`, and `null` before `message_start`.
     */
    val content: () -> Any?,
)

/**
 * Records a streamed Message on the span of [call] as the application reads it: the time to its
 * first `content_block_delta` event, if it has one, and what [message], the SDK's accumulator,
 * makes of the events. That is the Message's attributes and, with [content], the request's content,
 * its content blocks, as many as arrived when the stream ends before `message_stop`. Without
 * [content] the accumulator gets only the events that carry attributes, so it holds none of the
 * response's text however long the stream runs. The stream that ends the span
 * (`endApiCallSpanWhenStreamEnds`) hands it each event, and then the end of the read.
 */
internal class MessageStreamRecording<T>(
    private val clientOptions: ClientOptions,
    private val call: ApiCall,
    private val content: RequestContent?,
    private val message: StreamedMessage<T>,
) : StreamRecording<T> {

    private val span = call.span
    private var receivedContentBlockDelta = false
    // Whether the accumulator rejected an event. The accumulated Message then misses a part, so the
    // span goes without the response's content, and the accumulator gets no more of it.
    @Volatile private var missedAnEvent = false

    // Whether `message_delta` arrived, and with it the Message's stop reason and its output tokens.
    @Volatile private var receivedMessageDelta = false

    // Whether the whole Message arrived: the events reached `message_stop`.
    @Volatile private var stopped = false

    /**
     * Records [event]. This runs where the application reads the stream, so a failure stays here:
     * the application gets the event all the same.
     */
    override fun onEvent(event: T) {
        try {
            if (!receivedContentBlockDelta && message.isContentBlockDelta(event)) {
                receivedContentBlockDelta = true
                span.setAttribute(
                    GEN_AI_RESPONSE_TIME_TO_FIRST_CHUNK,
                    (System.nanoTime() - call.startNanoTime) / NANOS_PER_SECOND,
                )
            }
            // Before the accumulator, which may reject the event: the stream is complete all the
            // same.
            if (message.isMessageStop(event)) {
                stopped = true
            }
            val addsContent = content != null && !missedAnEvent
            if (addsContent || message.carriesAttributes(event)) {
                message.accumulate(event)
            }
            // After the accumulator, which has the event's stop reason and output tokens only once
            // it has taken the event.
            if (message.isMessageDelta(event)) {
                receivedMessageDelta = true
            }
        } catch (e: Exception) {
            missedAnEvent = true
            logTelemetryFailure(clientOptions, "Could not record a stream event on its span", e)
        }
    }

    /**
     * The read is over, and the span about to end. Sets the Message's attributes and its content as
     * far as they arrived, and returns whether the whole Message did. The content is fitted beside
     * the request's content, which the span already has.
     */
    override fun onEnd(): Boolean {
        record(clientOptions, span, "Could not record the stream on its span") {
            message.setMessageAttributes(span, receivedMessageDelta)
            if (content != null && !missedAnEvent) {
                message.content()?.let { blocks ->
                    val mapper = clientOptions.jsonMapper
                    setResponseContent(
                        span,
                        mapper,
                        content,
                        contentParts(mapper.valueToTree<JsonNode>(blocks)),
                    )
                }
            }
        }
        return stopped
    }
}
