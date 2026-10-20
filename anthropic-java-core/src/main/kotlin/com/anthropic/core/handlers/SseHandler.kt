@file:JvmName("SseHandler")

package com.anthropic.core.handlers

import com.anthropic.core.JsonMissing
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.SseMessage
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.http.map
import com.anthropic.errors.SseException
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef

@JvmSynthetic
internal fun sseHandler(jsonMapper: JsonMapper): Handler<StreamResponse<SseMessage>> =
    streamHandler { response, lines ->
        val state = SseState(jsonMapper)
        for (line in lines) {
            val message = state.decode(line) ?: continue

            when (message.event) {
                "ping",
                null -> continue
                "error" -> {
                    throw SseException.builder()
                        .statusCode(response.statusCode())
                        .headers(response.headers())
                        .body(
                            try {
                                jsonMapper.readValue(message.data, jacksonTypeRef())
                            } catch (e: Exception) {
                                JsonMissing.of()
                            }
                        )
                        .build()
                }
                else -> {
                    if (message.data.isEmpty()) {
                        continue
                    }

                    yield(message)
                }
            }
        }
    }

/**
 * Like [sseHandler], but yields every decoded [SseMessage] — no event-name filtering, no `ping`
 * dropping, no `error` throwing — with each message's original wire lines retained
 * ([SseMessage.rawLines]). For consumers that fully replace a stream's body and must forward events
 * they don't model verbatim, like an interceptor splicing streams.
 */
@JvmSynthetic
internal fun rawSseHandler(jsonMapper: JsonMapper): Handler<StreamResponse<SseMessage>> =
    streamHandler { _, lines ->
        val state = SseState(jsonMapper, collectRawLines = true)
        for (line in lines) {
            state.decode(line)?.let { yield(it) }
        }
        // A stream ending without a trailing blank line still flushes its final message.
        state.decode("")?.let { yield(it) }
    }

private class SseState(
    val jsonMapper: JsonMapper,
    /** Only [rawSseHandler] reads [SseMessage.rawLines]; skip the per-event copies otherwise. */
    val collectRawLines: Boolean = false,
    var event: String? = null,
    val data: MutableList<String> = mutableListOf(),
    var lastId: String? = null,
    var retry: Int? = null,
    val rawLines: MutableList<String> = mutableListOf(),
) {
    // https://html.spec.whatwg.org/multipage/server-sent-events.html#event-stream-interpretation
    fun decode(line: String): SseMessage? {
        if (line.isEmpty()) {
            return flush()
        }

        if (collectRawLines) {
            rawLines.add(line)
        }

        if (line.startsWith(':')) {
            return null
        }

        val fieldName: String
        var value: String

        val colonIndex = line.indexOf(':')
        if (colonIndex == -1) {
            fieldName = line
            value = ""
        } else {
            fieldName = line.substring(0, colonIndex)
            value = line.substring(colonIndex + 1)
        }

        if (value.startsWith(' ')) {
            value = value.substring(1)
        }

        when (fieldName) {
            "event" -> event = value
            "data" -> data.add(value)
            "id" -> {
                if (!value.contains('\u0000')) {
                    lastId = value
                }
            }
            "retry" -> value.toIntOrNull()?.let { retry = it }
        }

        return null
    }

    private fun flush(): SseMessage? {
        if (isEmpty()) {
            // Keep any raw lines (e.g. a comment-only block): they attach to the next message.
            return null
        }

        val message =
            SseMessage.builder()
                .jsonMapper(jsonMapper)
                .event(event)
                .data(data.joinToString("\n"))
                .id(lastId)
                .retry(retry)
                .rawLines(rawLines.toList())
                .build()

        // NOTE: Per the SSE spec, do not reset lastId.
        event = null
        data.clear()
        retry = null
        rawLines.clear()

        return message
    }

    private fun isEmpty(): Boolean =
        event.isNullOrEmpty() && data.isEmpty() && lastId.isNullOrEmpty() && retry == null
}

@JvmSynthetic
internal inline fun <reified T> Handler<StreamResponse<SseMessage>>.mapJson():
    Handler<StreamResponse<T>> =
    object : Handler<StreamResponse<T>> {
        override fun handle(response: HttpResponse): StreamResponse<T> =
            this@mapJson.handle(response).map { it.json<T>() }
    }
