@file:JvmName("ApiCallSpans")

package com.anthropic.internal.telemetry

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.opentelemetry.ApiCall
import com.anthropic.core.opentelemetry.startApiCall
import com.anthropic.core.opentelemetry.tracer
import com.anthropic.models.beta.messages.batches.BatchCreateParams as BetaBatchCreateParams
import com.anthropic.models.beta.sessions.SessionCreateParams
import com.anthropic.models.messages.batches.BatchCreateParams
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.api.trace.Span
import kotlin.jvm.optionals.getOrNull

/**
 * Starts the span of a `messages.batches.create` API call that the client of [clientOptions] makes
 * with [requestOptions], with the number of requests that its [params] have. Returns `null` when
 * the client emits no span.
 */
@JvmSynthetic
internal fun startBatchesCreate(
    clientOptions: ClientOptions,
    requestOptions: RequestOptions,
    params: BatchCreateParams,
): ApiCall? =
    startApiCall(
        clientOptions,
        params._spanData(),
        HttpMethod.POST,
        requestOptions,
        requestCountAttributes(params._requests().asKnown().getOrNull()?.size),
    )

/** [startBatchesCreate] for the beta API. */
@JvmSynthetic
internal fun startBatchesCreate(
    clientOptions: ClientOptions,
    requestOptions: RequestOptions,
    params: BetaBatchCreateParams,
): ApiCall? =
    startApiCall(
        clientOptions,
        params._spanData(),
        HttpMethod.POST,
        requestOptions,
        requestCountAttributes(params._requests().asKnown().getOrNull()?.size),
    )

/** The attributes that the span of a batch of [count] requests starts with. */
private fun requestCountAttributes(count: Int?): Attributes =
    if (count == null) Attributes.empty()
    else Attributes.of(ANTHROPIC_MESSAGE_BATCH_REQUEST_COUNT, count.toLong())

/**
 * Starts the span of a `sessions.create` API call that the client of [clientOptions] makes with
 * [requestOptions], with the agent that its [params] name beside what their span data gives it. The
 * agent is an ID, or an object with one. Returns `null` when the client emits no span.
 */
@JvmSynthetic
internal fun startSessionsCreate(
    clientOptions: ClientOptions,
    requestOptions: RequestOptions,
    params: SessionCreateParams,
): ApiCall? {
    if (tracer(clientOptions) == null) {
        return null
    }
    val attributes = Attributes.builder()
    try {
        val agent = params._agent().asKnown().getOrNull()
        (agent?.string()?.getOrNull() ?: agent?.id()?.getOrNull())?.let {
            attributes.put(GEN_AI_AGENT_ID, it)
        }
    } catch (e: Exception) {
        logTelemetryFailure(clientOptions, "Could not record the request on its span", e)
    }
    return startApiCall(
        clientOptions,
        params._spanData(),
        HttpMethod.POST,
        requestOptions,
        attributes.build(),
    )
}

/**
 * Runs [set] on [span] when it records. A method calls this with its params before it sends its
 * request, or with its response while it parses it, so a failure to record is logged as [failure]
 * and never fails the API call.
 */
@JvmSynthetic
internal inline fun record(
    clientOptions: ClientOptions,
    span: Span?,
    failure: String,
    set: (Span) -> Unit,
) {
    if (span == null || !span.isRecording) {
        return
    }
    try {
        set(span)
    } catch (e: Exception) {
        logTelemetryFailure(clientOptions, failure, e)
    }
}
