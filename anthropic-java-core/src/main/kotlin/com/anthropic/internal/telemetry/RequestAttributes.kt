@file:JvmName("RequestAttributes")

package com.anthropic.internal.telemetry

import com.anthropic.core.ClientOptions
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.opentelemetry.SpanData
import com.anthropic.core.opentelemetry.startApiCall
import com.anthropic.core.opentelemetry.tracer
import com.anthropic.models.beta.messages.MessageCreateParams as BetaMessageCreateParams
import com.anthropic.models.messages.MessageCreateParams
import com.fasterxml.jackson.databind.JsonNode
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.api.common.AttributesBuilder
import kotlin.jvm.optionals.getOrNull

/**
 * Starts the span of a `messages.create` API call that the client of [clientOptions] makes with
 * [requestOptions], with the request attributes that the method knows from its [params], and sets
 * on it, in content mode, the request's messages. [stream] says whether the method asks for a
 * streamed response. Returns the recording that has the call, for the method to send its request in
 * and to hand its parsed response to, or `null` when the client emits no span.
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
    startMessagesCreate(
        clientOptions,
        requestOptions,
        params,
        params._spanData(),
        requestModel(params._model()),
        params._body(),
        stream,
    ) {
        val thinking = params.thinking().getOrNull()
        MessagesCreateRequest(
            model = params.model().asString(),
            maxTokens = params.maxTokens(),
            temperature = params.temperature().getOrNull(),
            topP = params.topP().getOrNull(),
            topK = params.topK().getOrNull(),
            effort = params.outputConfig().flatMap { it.effort() }.getOrNull()?.asString(),
            setsJsonOutputFormat =
                params.outputConfig().flatMap { it.format() }.getOrNull()?._type() ==
                    JSON_SCHEMA_FORMAT,
            serviceTier = params.serviceTier().getOrNull()?.asString(),
            thinkingType =
                when {
                    thinking == null -> null
                    thinking.isEnabled() -> "enabled"
                    thinking.isDisabled() -> "disabled"
                    thinking.isAdaptive() -> "adaptive"
                    else -> null
                },
            thinkingBudgetTokens = thinking?.enabled()?.getOrNull()?.budgetTokens(),
            stopSequences = { params.stopSequences().getOrNull() },
        )
    }

/** [startMessagesCreate] for the beta API. */
@JvmSynthetic
internal fun startMessagesCreate(
    clientOptions: ClientOptions,
    requestOptions: RequestOptions,
    params: BetaMessageCreateParams,
    stream: Boolean,
): MessagesCreateRecording? =
    startMessagesCreate(
        clientOptions,
        requestOptions,
        params,
        params._spanData(),
        requestModel(params._model()),
        params._body(),
        stream,
    ) {
        val thinking = params.thinking().getOrNull()
        MessagesCreateRequest(
            model = params.model().asString(),
            maxTokens = params.maxTokens(),
            temperature = params.temperature().getOrNull(),
            topP = params.topP().getOrNull(),
            topK = params.topK().getOrNull(),
            effort = params.outputConfig().flatMap { it.effort() }.getOrNull()?.asString(),
            setsJsonOutputFormat = params.setsJsonOutputFormat(),
            serviceTier = params.serviceTier().getOrNull()?.asString(),
            thinkingType =
                when {
                    thinking == null -> null
                    thinking.isEnabled() -> "enabled"
                    thinking.isDisabled() -> "disabled"
                    thinking.isAdaptive() -> "adaptive"
                    else -> null
                },
            thinkingBudgetTokens = thinking?.enabled()?.getOrNull()?.budgetTokens(),
            stopSequences = { params.stopSequences().getOrNull() },
        )
    }

/**
 * What the span of a `messages.create` API call records of its request, as the stable and the beta
 * params both have it.
 */
private class MessagesCreateRequest(
    val model: String,
    val maxTokens: Long,
    val temperature: Double?,
    val topP: Double?,
    val topK: Long?,
    val effort: String?,
    val setsJsonOutputFormat: Boolean,
    val serviceTier: String?,
    val thinkingType: String?,
    val thinkingBudgetTokens: Long?,
    /** Reads the request's stop sequences, which only a span in content mode asks for. */
    val stopSequences: () -> List<String>?,
)

/**
 * The span starts with what [readRequest] reads from the params of either API, with whether the
 * method asks for a [stream] and with the betas in the request's headers, so that a sampler sees
 * them. When the params cannot be read, it starts with [model] alone. [readRequest] runs only for a
 * client that emits spans. [body] is the params' body, whose `system` and `messages` a span in
 * content mode records, with the request's stop sequences.
 */
private fun startMessagesCreate(
    clientOptions: ClientOptions,
    requestOptions: RequestOptions,
    params: Params,
    spanData: SpanData,
    model: String?,
    body: Any,
    stream: Boolean,
    readRequest: () -> MessagesCreateRequest,
): MessagesCreateRecording? {
    if (tracer(clientOptions) == null) {
        return null
    }
    val attributes = Attributes.builder()
    attributes.put(GEN_AI_OPERATION_NAME, GenAiOperationNameValues.CHAT)
    model?.let { attributes.put(GEN_AI_REQUEST_MODEL, it) }
    val request =
        try {
            val read = readRequest()
            putMessagesCreateRequestAttributes(attributes, read)
            if (stream) {
                attributes.put(GEN_AI_REQUEST_STREAM, true)
            }
            // The params' headers replace the client's, as when the request is prepared.
            val betaHeaders =
                params._headers().values("anthropic-beta").ifEmpty {
                    clientOptions.headers.values("anthropic-beta")
                }
            val betas =
                betaHeaders.flatMap { it.split(',') }.map { it.trim() }.filter { it.isNotEmpty() }
            if (betas.isNotEmpty()) {
                attributes.put(ANTHROPIC_BETA, betas)
            }
            read
        } catch (e: Exception) {
            logTelemetryFailure(clientOptions, "Could not record the request on its span", e)
            null
        }
    val call =
        startApiCall(clientOptions, spanData, HttpMethod.POST, requestOptions, attributes.build())
            ?: return null
    if (request == null || !call.span.isRecording) {
        return MessagesCreateRecording(clientOptions, call, requestContent = null)
    }
    return try {
        val span = call.span
        // In content mode the request's content is on the span whatever becomes of the call. The
        // response's content joins it later, and the size limit then counts both.
        val content =
            if (recordsContent(clientOptions)) {
                val mapper = clientOptions.jsonMapper
                val tree = mapper.valueToTree<JsonNode>(body)
                setRequestContent(
                    span,
                    mapper,
                    tree.get("system"),
                    tree.get("messages"),
                    request.stopSequences(),
                    contentLimit(clientOptions),
                )
            } else {
                null
            }
        MessagesCreateRecording(clientOptions, call, content)
    } catch (e: Exception) {
        logTelemetryFailure(clientOptions, "Could not record the request on its span", e)
        MessagesCreateRecording(clientOptions, call, requestContent = null)
    }
}

private fun putMessagesCreateRequestAttributes(
    attributes: AttributesBuilder,
    request: MessagesCreateRequest,
) {
    attributes.put(GEN_AI_REQUEST_MODEL, request.model)
    attributes.put(GEN_AI_REQUEST_MAX_TOKENS, request.maxTokens)
    request.temperature?.let { attributes.put(GEN_AI_REQUEST_TEMPERATURE, it) }
    request.topP?.let { attributes.put(GEN_AI_REQUEST_TOP_P, it) }
    request.topK?.let { attributes.put(GEN_AI_REQUEST_TOP_K, it) }
    request.effort?.let { attributes.put(GEN_AI_REQUEST_REASONING_LEVEL, it) }
    if (request.setsJsonOutputFormat) {
        attributes.put(GEN_AI_OUTPUT_TYPE, GenAiOutputTypeValues.JSON)
    }
    request.serviceTier?.let { attributes.put(ANTHROPIC_REQUEST_SERVICE_TIER, it) }
    request.thinkingType?.let { attributes.put(ANTHROPIC_THINKING_TYPE, it) }
    request.thinkingBudgetTokens?.let { attributes.put(ANTHROPIC_THINKING_BUDGET_TOKENS, it) }
}

/** The `type` of a JSON output format. */
private val JSON_SCHEMA_FORMAT = JsonValue.from("json_schema")

/**
 * Whether these params set the output format to JSON: in `output_config.format`, or in the
 * deprecated `output_format`, which only the beta API has.
 */
@JvmSynthetic
internal fun BetaMessageCreateParams.setsJsonOutputFormat(): Boolean =
    outputConfig().flatMap { it.format() }.getOrNull()?._type() == JSON_SCHEMA_FORMAT ||
        outputFormat().getOrNull()?._type() == JSON_SCHEMA_FORMAT
