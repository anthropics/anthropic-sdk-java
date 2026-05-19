// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments.work

import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.models.beta.AnthropicBeta
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Note: these endpoints are called automatically by the pre-built environment worker provided in
 * the SDKs and CLI, for orchestrating sessions with self-hosted sandbox environments. They are
 * included here as a reference; you do not need to invoke them directly.
 *
 * Stop a work item, initiating graceful or forced shutdown.
 */
class WorkStopParams
private constructor(
    private val environmentId: String,
    private val workId: String?,
    private val betas: List<AnthropicBeta>?,
    private val betaSelfHostedWorkStopRequest: BetaSelfHostedWorkStopRequest,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun environmentId(): String = environmentId

    fun workId(): Optional<String> = Optional.ofNullable(workId)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Request to stop a work item. */
    fun betaSelfHostedWorkStopRequest(): BetaSelfHostedWorkStopRequest =
        betaSelfHostedWorkStopRequest

    fun _additionalBodyProperties(): Map<String, JsonValue> =
        betaSelfHostedWorkStopRequest._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [WorkStopParams].
         *
         * The following fields are required:
         * ```java
         * .environmentId()
         * .betaSelfHostedWorkStopRequest()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [WorkStopParams]. */
    class Builder internal constructor() {

        private var environmentId: String? = null
        private var workId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var betaSelfHostedWorkStopRequest: BetaSelfHostedWorkStopRequest? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(workStopParams: WorkStopParams) = apply {
            environmentId = workStopParams.environmentId
            workId = workStopParams.workId
            betas = workStopParams.betas?.toMutableList()
            betaSelfHostedWorkStopRequest = workStopParams.betaSelfHostedWorkStopRequest
            additionalHeaders = workStopParams.additionalHeaders.toBuilder()
            additionalQueryParams = workStopParams.additionalQueryParams.toBuilder()
        }

        fun environmentId(environmentId: String) = apply { this.environmentId = environmentId }

        fun workId(workId: String?) = apply { this.workId = workId }

        /** Alias for calling [Builder.workId] with `workId.orElse(null)`. */
        fun workId(workId: Optional<String>) = workId(workId.getOrNull())

        /** Optional header to specify the beta version(s) you want to use. */
        fun betas(betas: List<AnthropicBeta>?) = apply { this.betas = betas?.toMutableList() }

        /** Alias for calling [Builder.betas] with `betas.orElse(null)`. */
        fun betas(betas: Optional<List<AnthropicBeta>>) = betas(betas.getOrNull())

        /**
         * Adds a single [AnthropicBeta] to [betas].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBeta(beta: AnthropicBeta) = apply {
            betas = (betas ?: mutableListOf()).apply { add(beta) }
        }

        /**
         * Sets [addBeta] to an arbitrary [String].
         *
         * You should usually call [addBeta] with a well-typed [AnthropicBeta] constant instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun addBeta(value: String) = addBeta(AnthropicBeta.of(value))

        /** Request to stop a work item. */
        fun betaSelfHostedWorkStopRequest(
            betaSelfHostedWorkStopRequest: BetaSelfHostedWorkStopRequest
        ) = apply { this.betaSelfHostedWorkStopRequest = betaSelfHostedWorkStopRequest }

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [WorkStopParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .environmentId()
         * .betaSelfHostedWorkStopRequest()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): WorkStopParams =
            WorkStopParams(
                checkRequired("environmentId", environmentId),
                workId,
                betas?.toImmutable(),
                checkRequired("betaSelfHostedWorkStopRequest", betaSelfHostedWorkStopRequest),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): BetaSelfHostedWorkStopRequest = betaSelfHostedWorkStopRequest

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> environmentId
            1 -> workId ?: ""
            else -> ""
        }

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.forEach { put("anthropic-beta", it.toString()) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is WorkStopParams &&
            environmentId == other.environmentId &&
            workId == other.workId &&
            betas == other.betas &&
            betaSelfHostedWorkStopRequest == other.betaSelfHostedWorkStopRequest &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            environmentId,
            workId,
            betas,
            betaSelfHostedWorkStopRequest,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "WorkStopParams{environmentId=$environmentId, workId=$workId, betas=$betas, betaSelfHostedWorkStopRequest=$betaSelfHostedWorkStopRequest, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
