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
 * Record a heartbeat for a work item to maintain the lease.
 */
class WorkHeartbeatParams
private constructor(
    private val environmentId: String,
    private val workId: String?,
    private val desiredTtlSeconds: Long?,
    private val expectedLastHeartbeat: String?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
    private val additionalBodyProperties: Map<String, JsonValue>,
) : Params {

    fun environmentId(): String = environmentId

    fun workId(): Optional<String> = Optional.ofNullable(workId)

    /** Desired TTL in seconds */
    fun desiredTtlSeconds(): Optional<Long> = Optional.ofNullable(desiredTtlSeconds)

    /**
     * Expected last_heartbeat for conditional update (optimistic concurrency). Use literal
     * 'NO_HEARTBEAT' to claim an unclaimed lease (first heartbeat). For subsequent heartbeats, echo
     * the server's previous last_heartbeat value exactly. Returns 412 Precondition Failed if the
     * actual value doesn't match.
     */
    fun expectedLastHeartbeat(): Optional<String> = Optional.ofNullable(expectedLastHeartbeat)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional body properties to send with the request. */
    fun _additionalBodyProperties(): Map<String, JsonValue> = additionalBodyProperties

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [WorkHeartbeatParams].
         *
         * The following fields are required:
         * ```java
         * .environmentId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [WorkHeartbeatParams]. */
    class Builder internal constructor() {

        private var environmentId: String? = null
        private var workId: String? = null
        private var desiredTtlSeconds: Long? = null
        private var expectedLastHeartbeat: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()
        private var additionalBodyProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(workHeartbeatParams: WorkHeartbeatParams) = apply {
            environmentId = workHeartbeatParams.environmentId
            workId = workHeartbeatParams.workId
            desiredTtlSeconds = workHeartbeatParams.desiredTtlSeconds
            expectedLastHeartbeat = workHeartbeatParams.expectedLastHeartbeat
            betas = workHeartbeatParams.betas?.toMutableList()
            additionalHeaders = workHeartbeatParams.additionalHeaders.toBuilder()
            additionalQueryParams = workHeartbeatParams.additionalQueryParams.toBuilder()
            additionalBodyProperties = workHeartbeatParams.additionalBodyProperties.toMutableMap()
        }

        fun environmentId(environmentId: String) = apply { this.environmentId = environmentId }

        fun workId(workId: String?) = apply { this.workId = workId }

        /** Alias for calling [Builder.workId] with `workId.orElse(null)`. */
        fun workId(workId: Optional<String>) = workId(workId.getOrNull())

        /** Desired TTL in seconds */
        fun desiredTtlSeconds(desiredTtlSeconds: Long?) = apply {
            this.desiredTtlSeconds = desiredTtlSeconds
        }

        /**
         * Alias for [Builder.desiredTtlSeconds].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun desiredTtlSeconds(desiredTtlSeconds: Long) =
            desiredTtlSeconds(desiredTtlSeconds as Long?)

        /** Alias for calling [Builder.desiredTtlSeconds] with `desiredTtlSeconds.orElse(null)`. */
        fun desiredTtlSeconds(desiredTtlSeconds: Optional<Long>) =
            desiredTtlSeconds(desiredTtlSeconds.getOrNull())

        /**
         * Expected last_heartbeat for conditional update (optimistic concurrency). Use literal
         * 'NO_HEARTBEAT' to claim an unclaimed lease (first heartbeat). For subsequent heartbeats,
         * echo the server's previous last_heartbeat value exactly. Returns 412 Precondition Failed
         * if the actual value doesn't match.
         */
        fun expectedLastHeartbeat(expectedLastHeartbeat: String?) = apply {
            this.expectedLastHeartbeat = expectedLastHeartbeat
        }

        /**
         * Alias for calling [Builder.expectedLastHeartbeat] with
         * `expectedLastHeartbeat.orElse(null)`.
         */
        fun expectedLastHeartbeat(expectedLastHeartbeat: Optional<String>) =
            expectedLastHeartbeat(expectedLastHeartbeat.getOrNull())

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

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            this.additionalBodyProperties.clear()
            putAllAdditionalBodyProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            additionalBodyProperties.put(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                this.additionalBodyProperties.putAll(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply {
            additionalBodyProperties.remove(key)
        }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalBodyProperty)
        }

        /**
         * Returns an immutable instance of [WorkHeartbeatParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .environmentId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): WorkHeartbeatParams =
            WorkHeartbeatParams(
                checkRequired("environmentId", environmentId),
                workId,
                desiredTtlSeconds,
                expectedLastHeartbeat,
                betas?.toImmutable(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
                additionalBodyProperties.toImmutable(),
            )
    }

    fun _body(): Optional<Map<String, JsonValue>> =
        Optional.ofNullable(additionalBodyProperties.ifEmpty { null })

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

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                desiredTtlSeconds?.let { put("desired_ttl_seconds", it.toString()) }
                expectedLastHeartbeat?.let { put("expected_last_heartbeat", it) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is WorkHeartbeatParams &&
            environmentId == other.environmentId &&
            workId == other.workId &&
            desiredTtlSeconds == other.desiredTtlSeconds &&
            expectedLastHeartbeat == other.expectedLastHeartbeat &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams &&
            additionalBodyProperties == other.additionalBodyProperties
    }

    override fun hashCode(): Int =
        Objects.hash(
            environmentId,
            workId,
            desiredTtlSeconds,
            expectedLastHeartbeat,
            betas,
            additionalHeaders,
            additionalQueryParams,
            additionalBodyProperties,
        )

    override fun toString() =
        "WorkHeartbeatParams{environmentId=$environmentId, workId=$workId, desiredTtlSeconds=$desiredTtlSeconds, expectedLastHeartbeat=$expectedLastHeartbeat, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams, additionalBodyProperties=$additionalBodyProperties}"
}
