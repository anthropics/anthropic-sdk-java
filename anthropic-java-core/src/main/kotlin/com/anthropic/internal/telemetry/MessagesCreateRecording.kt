package com.anthropic.internal.telemetry

import com.anthropic.core.ClientOptions
import com.anthropic.core.opentelemetry.ApiCall
import com.anthropic.helpers.BetaMessageAccumulator
import com.anthropic.helpers.MessageAccumulator
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaRawMessageStreamEvent
import com.anthropic.models.messages.Message
import com.anthropic.models.messages.RawMessageStreamEvent
import com.fasterxml.jackson.databind.JsonNode
import io.opentelemetry.api.trace.Span

/**
 * The [call] of a `messages.create` method whose request is on its span ([startMessagesCreate]),
 * for the method to record its parsed response with: a Message, or the stream of one. The method
 * does so inside the block that parses its response, which runs before `traceApiCall` ends the
 * span. [requestContent] is the content of the request when the span records content.
 *
 * A failure to record is logged and never fails the API call.
 */
internal class MessagesCreateRecording(
    private val clientOptions: ClientOptions,
    val call: ApiCall,
    private val requestContent: RequestContent?,
) {

    /** Sets what comes from [message], the parsed response. */
    fun recordResponse(message: Message) =
        recordResponse({ setMessageAttributes(it, message) }) { message._content() }

    /** [recordResponse] for the beta API. */
    fun recordResponse(message: BetaMessage) =
        recordResponse({ setMessageAttributes(it, message) }) { message._content() }

    /**
     * The response's content is fitted beside the request's, which the span already has, so that
     * the size limit counts both.
     */
    private fun recordResponse(setMessageAttributes: (Span) -> Unit, content: () -> Any) =
        record(clientOptions, call.span, "Could not record the response on its span") { span ->
            setMessageAttributes(span)
            if (requestContent != null) {
                val mapper = clientOptions.jsonMapper
                setResponseContent(
                    span,
                    mapper,
                    requestContent,
                    contentParts(mapper.valueToTree<JsonNode>(content())),
                )
            }
        }

    /**
     * Records the streamed Message that the method parsed its response into: the SDK's accumulator
     * gets each event that the application reads, and the span gets what the accumulator has once
     * the reading is over.
     */
    fun recordStream() {
        val accumulator = MessageAccumulator.create()
        recordStream(
            StreamedMessage<RawMessageStreamEvent>(
                accumulate = { accumulator.accumulate(it) },
                carriesAttributes = {
                    it.isMessageStart() || it.isMessageDelta() || it.isContentBlockStart()
                },
                isMessageDelta = { it.isMessageDelta() },
                isMessageStop = { it.isMessageStop() },
                isContentBlockDelta = { it.isContentBlockDelta() },
                setMessageAttributes = { span, ended ->
                    accumulator.partialMessage()?.let { setMessageAttributes(span, it, ended) }
                },
                content = { accumulator.partialMessage()?._content() },
            )
        )
    }

    /**
     * [recordStream] for the beta API. [helperAccumulator] is the accumulator of an SDK helper that
     * reads the stream, such as the tool runner, which feeds it with every event after the span's
     * recording has seen it. The span then reads the Message from it once the reading is over, and
     * keeps no accumulator of its own, so that the stream is accumulated once.
     */
    fun recordBetaStream(helperAccumulator: BetaMessageAccumulator? = null) {
        val accumulator = helperAccumulator ?: BetaMessageAccumulator.create()
        recordStream(
            StreamedMessage<BetaRawMessageStreamEvent>(
                accumulate = { if (helperAccumulator == null) accumulator.accumulate(it) },
                carriesAttributes = {
                    it.isMessageStart() || it.isMessageDelta() || it.isContentBlockStart()
                },
                isMessageDelta = { it.isMessageDelta() },
                isMessageStop = { it.isMessageStop() },
                isContentBlockDelta = { it.isContentBlockDelta() },
                setMessageAttributes = { span, ended ->
                    accumulator.partialMessage()?.let { setMessageAttributes(span, it, ended) }
                },
                content = { accumulator.partialMessage()?._content() },
            )
        )
    }

    private fun <E> recordStream(message: StreamedMessage<E>) {
        call.streamRecording = MessageStreamRecording(clientOptions, call, requestContent, message)
    }
}
