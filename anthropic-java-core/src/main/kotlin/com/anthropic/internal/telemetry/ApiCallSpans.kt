@file:JvmName("ApiCallSpans")

package com.anthropic.internal.telemetry

import com.anthropic.core.ClientOptions
import com.anthropic.core.JsonField
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.opentelemetry.ApiCall
import com.anthropic.core.opentelemetry.ApiCallOutcome
import com.anthropic.core.opentelemetry.startApiCall
import com.anthropic.models.beta.messages.BetaMessageTokensCount
import com.anthropic.models.beta.messages.MessageCountTokensParams as BetaMessageCountTokensParams
import com.anthropic.models.beta.messages.batches.BatchCreateParams as BetaBatchCreateParams
import com.anthropic.models.beta.sessions.BetaManagedAgentsSession
import com.anthropic.models.beta.sessions.SessionCreateParams
import com.anthropic.models.messages.MessageCountTokensParams
import com.anthropic.models.messages.MessageTokensCount
import com.anthropic.models.messages.Model
import com.anthropic.models.messages.batches.BatchCreateParams
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.api.trace.Span
import kotlin.jvm.optionals.getOrNull

private val RESPONSE_HEADER_ATTRIBUTES =
    listOf(
        ANTHROPIC_REQUEST_ID to "request-id",
        ANTHROPIC_ORGANIZATION_ID to "anthropic-organization-id",
        ANTHROPIC_WORKSPACE_ID to "anthropic-workspace-id",
    )

/**
 * Sets on [span] the attributes that every API call has once it has an [outcome]: the provider and
 * the IDs in the response headers.
 */
@JvmSynthetic
internal fun setCommonApiCallAttributes(
    span: Span,
    clientOptions: ClientOptions,
    outcome: ApiCallOutcome,
) =
    record(clientOptions, span) {
        span.setAttribute(GEN_AI_PROVIDER_NAME, clientOptions.openTelemetryProviderName)
        outcome.headers?.let { setResponseHeaderAttributes(span, it) }
    }

/**
 * Starts the span of a `messages.countTokens` API call that the client of [clientOptions] makes
 * with [requestOptions], with the model that its [params] ask for. Returns `null` when the client
 * emits no span.
 */
@JvmSynthetic
internal fun startCountTokens(
    clientOptions: ClientOptions,
    requestOptions: RequestOptions,
    params: MessageCountTokensParams,
): ApiCall? =
    startApiCall(
        clientOptions,
        params._spanData(),
        HttpMethod.POST,
        requestOptions,
        requestModelAttributes(requestModel(params._model())),
    )

/** [startCountTokens] for the beta API. */
@JvmSynthetic
internal fun startCountTokens(
    clientOptions: ClientOptions,
    requestOptions: RequestOptions,
    params: BetaMessageCountTokensParams,
): ApiCall? =
    startApiCall(
        clientOptions,
        params._spanData(),
        HttpMethod.POST,
        requestOptions,
        requestModelAttributes(requestModel(params._model())),
    )

/** The attributes that a span starts with when all its method reads from its params is [model]. */
private fun requestModelAttributes(model: String?): Attributes =
    if (model == null) Attributes.empty() else Attributes.of(GEN_AI_REQUEST_MODEL, model)

/**
 * The model that [model], a field of a method's params, names, or `null` when it is not a string.
 * It never throws, since it runs whether or not the client emits spans.
 */
@JvmSynthetic
internal fun requestModel(model: JsonField<Model>): String? =
    model.asKnown().getOrNull()?._value()?.asString()?.getOrNull()

/**
 * Sets on [call], the span of a `messages.countTokens` API call, the token count of its parsed
 * response.
 */
@JvmSynthetic
internal fun recordCountTokensResponse(
    clientOptions: ClientOptions,
    call: ApiCall?,
    count: MessageTokensCount,
) =
    record(clientOptions, call?.span) {
        it.setAttribute(ANTHROPIC_MESSAGE_COUNT_TOKENS_INPUT_TOKENS, count.inputTokens())
    }

/** [recordCountTokensResponse] for the beta API. */
@JvmSynthetic
internal fun recordCountTokensResponse(
    clientOptions: ClientOptions,
    call: ApiCall?,
    count: BetaMessageTokensCount,
) =
    record(clientOptions, call?.span) {
        it.setAttribute(ANTHROPIC_MESSAGE_COUNT_TOKENS_INPUT_TOKENS, count.inputTokens())
    }

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
 * [requestOptions], with the agent and the environment that its [params] name. The agent is an ID,
 * or an object with one. Returns `null` when the client emits no span.
 */
@JvmSynthetic
internal fun startSessionsCreate(
    clientOptions: ClientOptions,
    requestOptions: RequestOptions,
    params: SessionCreateParams,
): ApiCall? {
    val attributes = Attributes.builder()
    val agent = params._agent().asKnown().getOrNull()
    val agentId =
        agent?.string()?.getOrNull()
            ?: (agent?.betaManagedAgentsAgentParams()?.getOrNull()?._id()
                    ?: agent?.betaManagedAgentsAgentWithOverridesParams()?.getOrNull()?._id())
                ?.asString()
                ?.getOrNull()
    agentId?.let { attributes.put(GEN_AI_AGENT_ID, it) }
    params._environmentId().asString().getOrNull()?.let {
        attributes.put(ANTHROPIC_SESSION_ENVIRONMENT_ID, it)
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
 * Sets on [call], the span of a `sessions.create` API call, the agent of the created [session]: its
 * name, and the model that the Session resolved for it, which is not the one that the request's
 * overrides may name.
 */
@JvmSynthetic
internal fun recordSessionsCreateResponse(
    clientOptions: ClientOptions,
    call: ApiCall?,
    session: BetaManagedAgentsSession,
) =
    record(clientOptions, call?.span) {
        val agent = session.agent()
        it.setAttribute(GEN_AI_AGENT_NAME, agent.name())
        it.setAttribute(GEN_AI_REQUEST_MODEL, agent.model().id().asString())
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
    failure: String = "Could not record the API call on its span",
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

/** Sets the attributes that come from [headers]: each from the first non-empty value. */
private fun setResponseHeaderAttributes(span: Span, headers: Headers) {
    for ((attribute, header) in RESPONSE_HEADER_ATTRIBUTES) {
        headers
            .values(header)
            .firstOrNull { it.isNotEmpty() }
            ?.let { span.setAttribute(attribute, it) }
    }
}
