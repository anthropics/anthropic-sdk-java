// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.resources

import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.sessions.BetaManagedAgentsFileResourceParams
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Add Session Resource */
class ResourceAddParams
private constructor(
    private val sessionId: String?,
    private val betas: List<AnthropicBeta>?,
    private val betaManagedAgentsFileResourceParams: BetaManagedAgentsFileResourceParams,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun sessionId(): Optional<String> = Optional.ofNullable(sessionId)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Mount a file uploaded via the Files API into the session. */
    fun betaManagedAgentsFileResourceParams(): BetaManagedAgentsFileResourceParams =
        betaManagedAgentsFileResourceParams

    fun _additionalBodyProperties(): Map<String, JsonValue> =
        betaManagedAgentsFileResourceParams._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ResourceAddParams].
         *
         * The following fields are required:
         * ```java
         * .betaManagedAgentsFileResourceParams()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ResourceAddParams]. */
    class Builder internal constructor() {

        private var sessionId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var betaManagedAgentsFileResourceParams: BetaManagedAgentsFileResourceParams? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(resourceAddParams: ResourceAddParams) = apply {
            sessionId = resourceAddParams.sessionId
            betas = resourceAddParams.betas?.toMutableList()
            betaManagedAgentsFileResourceParams =
                resourceAddParams.betaManagedAgentsFileResourceParams
            additionalHeaders = resourceAddParams.additionalHeaders.toBuilder()
            additionalQueryParams = resourceAddParams.additionalQueryParams.toBuilder()
        }

        fun sessionId(sessionId: String?) = apply { this.sessionId = sessionId }

        /** Alias for calling [Builder.sessionId] with `sessionId.orElse(null)`. */
        fun sessionId(sessionId: Optional<String>) = sessionId(sessionId.getOrNull())

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

        /** Mount a file uploaded via the Files API into the session. */
        fun betaManagedAgentsFileResourceParams(
            betaManagedAgentsFileResourceParams: BetaManagedAgentsFileResourceParams
        ) = apply { this.betaManagedAgentsFileResourceParams = betaManagedAgentsFileResourceParams }

        /**
         * Alias for calling [betaManagedAgentsFileResourceParams] with the following:
         * ```java
         * BetaManagedAgentsFileResourceParams.builder()
         *     .type(BetaManagedAgentsFileResourceParams.Type.FILE)
         *     .fileId(fileId)
         *     .build()
         * ```
         */
        fun fileBetaManagedAgentsFileResourceParams(fileId: String) =
            betaManagedAgentsFileResourceParams(
                BetaManagedAgentsFileResourceParams.builder()
                    .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                    .fileId(fileId)
                    .build()
            )

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
         * Returns an immutable instance of [ResourceAddParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .betaManagedAgentsFileResourceParams()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ResourceAddParams =
            ResourceAddParams(
                sessionId,
                betas?.toImmutable(),
                checkRequired(
                    "betaManagedAgentsFileResourceParams",
                    betaManagedAgentsFileResourceParams,
                ),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): BetaManagedAgentsFileResourceParams = betaManagedAgentsFileResourceParams

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> sessionId ?: ""
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

        return other is ResourceAddParams &&
            sessionId == other.sessionId &&
            betas == other.betas &&
            betaManagedAgentsFileResourceParams == other.betaManagedAgentsFileResourceParams &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            sessionId,
            betas,
            betaManagedAgentsFileResourceParams,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "ResourceAddParams{sessionId=$sessionId, betas=$betas, betaManagedAgentsFileResourceParams=$betaManagedAgentsFileResourceParams, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
