@file:JvmName("RequestAttributes")

package com.anthropic.internal.telemetry

import com.anthropic.core.ClientOptions
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.opentelemetry.startApiCall
import com.anthropic.core.opentelemetry.tracer
import com.anthropic.models.beta.messages.MessageCreateParams as BetaMessageCreateParams
import com.anthropic.models.messages.MessageCreateParams
import com.fasterxml.jackson.databind.JsonNode
import io.opentelemetry.api.common.Attributes
import kotlin.jvm.optionals.getOrNull

/**
 * Starts the span of a `messages.create` API call that the client of [clientOptions] makes with
 * [requestOptions]. The span starts with the request attributes that the span data of [params]
 * gives it and with the output type, and gets, in content mode, the request's messages. [stream]
 * says whether the method asks for a streamed response. Returns the recording that has the call,
 * for the method to send its request in and to hand its parsed response to, or `null` when the
 * client emits no span.
 *
 * A failure to record is logged and never fails the API call.
 */
@JvmSynthetic
internal fun startMessagesCreate(
    clientOptions: ClientOptions,
    requestOptions: RequestOptions,
    params: MessageCreateParams,
    stream: Boolean,
): MessagesCreateRecording? =
    startMessagesCreate(clientOptions, requestOptions, params, params._body(), stream) {
        params._outputConfig().asKnown().getOrNull()?._format()?.asKnown()?.getOrNull()?._type() ==
            JSON_SCHEMA_FORMAT
    }

/** [startMessagesCreate] for the beta API. */
@JvmSynthetic
internal fun startMessagesCreate(
    clientOptions: ClientOptions,
    requestOptions: RequestOptions,
    params: BetaMessageCreateParams,
    stream: Boolean,
): MessagesCreateRecording? =
    startMessagesCreate(clientOptions, requestOptions, params, params._body(), stream) {
        params.setsJsonOutputFormat()
    }

/**
 * [setsJsonOutputFormat] runs for every call of a client that emits spans, sampled or not, so it
 * reads the `type` of the format and never its schema, which can be large. [body] is the params'
 * body, whose `system`, `messages` and `stop_sequences` a span in content mode records.
 */
private fun startMessagesCreate(
    clientOptions: ClientOptions,
    requestOptions: RequestOptions,
    params: Params,
    body: Any,
    stream: Boolean,
    setsJsonOutputFormat: () -> Boolean,
): MessagesCreateRecording? {
    if (tracer(clientOptions) == null) {
        return null
    }
    val attributes = Attributes.builder()
    try {
        if (setsJsonOutputFormat()) {
            attributes.put(GEN_AI_OUTPUT_TYPE, GenAiOutputTypeValues.JSON)
        }
    } catch (e: Exception) {
        logTelemetryFailure(clientOptions, "Could not record the request on its span", e)
    }
    val call =
        startApiCall(
            clientOptions,
            params._spanData(),
            HttpMethod.POST,
            requestOptions,
            attributes.build(),
            streaming = stream,
        ) ?: return null
    if (!call.span.isRecording || !recordsContent(clientOptions)) {
        return MessagesCreateRecording(clientOptions, call, requestContent = null)
    }
    // In content mode the request's content is on the span whatever becomes of the call. The
    // response's content joins it later, and the size limit then counts both.
    return try {
        val mapper = clientOptions.jsonMapper
        val tree = mapper.valueToTree<JsonNode>(body)
        val content =
            setRequestContent(
                call.span,
                mapper,
                tree.get("system"),
                tree.get("messages"),
                tree
                    .get("stop_sequences")
                    ?.takeIf { it.isArray && it.all { item -> item.isTextual } }
                    ?.map { it.asText() },
                contentLimit(clientOptions),
            )
        MessagesCreateRecording(clientOptions, call, content)
    } catch (e: Exception) {
        logTelemetryFailure(clientOptions, "Could not record the request on its span", e)
        MessagesCreateRecording(clientOptions, call, requestContent = null)
    }
}

/** The `type` of a JSON output format. */
private val JSON_SCHEMA_FORMAT = JsonValue.from("json_schema")

/**
 * Whether these params set the output format to JSON: in `output_config.format`, or in the
 * deprecated `output_format`, which only the beta API has.
 */
@JvmSynthetic
internal fun BetaMessageCreateParams.setsJsonOutputFormat(): Boolean =
    _outputConfig().asKnown().getOrNull()?._format()?.asKnown()?.getOrNull()?._type() ==
        JSON_SCHEMA_FORMAT || _outputFormat().asKnown().getOrNull()?._type() == JSON_SCHEMA_FORMAT
