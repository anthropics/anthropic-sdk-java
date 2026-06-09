// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.models.beta.AnthropicBeta
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** List Deployment Runs */
class DeploymentRunListParams
private constructor(
    private val createdAtGt: OffsetDateTime?,
    private val createdAtGte: OffsetDateTime?,
    private val createdAtLt: OffsetDateTime?,
    private val createdAtLte: OffsetDateTime?,
    private val deploymentId: String?,
    private val hasError: Boolean?,
    private val limit: Int?,
    private val page: String?,
    private val triggerType: BetaManagedAgentsTriggerType?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Return runs created strictly after this time (exclusive). */
    fun createdAtGt(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtGt)

    /** Return runs created at or after this time (inclusive). */
    fun createdAtGte(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtGte)

    /** Return runs created strictly before this time (exclusive). */
    fun createdAtLt(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtLt)

    /** Return runs created at or before this time (inclusive). */
    fun createdAtLte(): Optional<OffsetDateTime> = Optional.ofNullable(createdAtLte)

    /**
     * Filter to a specific deployment. Omit to list across all deployments in the workspace.
     * Filtering by a non-existent deployment_id returns 200 with empty data.
     */
    fun deploymentId(): Optional<String> = Optional.ofNullable(deploymentId)

    /**
     * Filter: true for runs with non-null error, false for runs with non-null session_id. Omit for
     * all.
     */
    fun hasError(): Optional<Boolean> = Optional.ofNullable(hasError)

    /** Maximum results per page. Default 20, maximum 1000. */
    fun limit(): Optional<Int> = Optional.ofNullable(limit)

    /**
     * Opaque pagination cursor. Pass next_page from the previous response. Invalid or expired
     * cursors return 400.
     */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /** Filter runs by what triggered them. Omit to return all runs. */
    fun triggerType(): Optional<BetaManagedAgentsTriggerType> = Optional.ofNullable(triggerType)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): DeploymentRunListParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [DeploymentRunListParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [DeploymentRunListParams]. */
    class Builder internal constructor() {

        private var createdAtGt: OffsetDateTime? = null
        private var createdAtGte: OffsetDateTime? = null
        private var createdAtLt: OffsetDateTime? = null
        private var createdAtLte: OffsetDateTime? = null
        private var deploymentId: String? = null
        private var hasError: Boolean? = null
        private var limit: Int? = null
        private var page: String? = null
        private var triggerType: BetaManagedAgentsTriggerType? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(deploymentRunListParams: DeploymentRunListParams) = apply {
            createdAtGt = deploymentRunListParams.createdAtGt
            createdAtGte = deploymentRunListParams.createdAtGte
            createdAtLt = deploymentRunListParams.createdAtLt
            createdAtLte = deploymentRunListParams.createdAtLte
            deploymentId = deploymentRunListParams.deploymentId
            hasError = deploymentRunListParams.hasError
            limit = deploymentRunListParams.limit
            page = deploymentRunListParams.page
            triggerType = deploymentRunListParams.triggerType
            betas = deploymentRunListParams.betas?.toMutableList()
            additionalHeaders = deploymentRunListParams.additionalHeaders.toBuilder()
            additionalQueryParams = deploymentRunListParams.additionalQueryParams.toBuilder()
        }

        /** Return runs created strictly after this time (exclusive). */
        fun createdAtGt(createdAtGt: OffsetDateTime?) = apply { this.createdAtGt = createdAtGt }

        /** Alias for calling [Builder.createdAtGt] with `createdAtGt.orElse(null)`. */
        fun createdAtGt(createdAtGt: Optional<OffsetDateTime>) =
            createdAtGt(createdAtGt.getOrNull())

        /** Return runs created at or after this time (inclusive). */
        fun createdAtGte(createdAtGte: OffsetDateTime?) = apply { this.createdAtGte = createdAtGte }

        /** Alias for calling [Builder.createdAtGte] with `createdAtGte.orElse(null)`. */
        fun createdAtGte(createdAtGte: Optional<OffsetDateTime>) =
            createdAtGte(createdAtGte.getOrNull())

        /** Return runs created strictly before this time (exclusive). */
        fun createdAtLt(createdAtLt: OffsetDateTime?) = apply { this.createdAtLt = createdAtLt }

        /** Alias for calling [Builder.createdAtLt] with `createdAtLt.orElse(null)`. */
        fun createdAtLt(createdAtLt: Optional<OffsetDateTime>) =
            createdAtLt(createdAtLt.getOrNull())

        /** Return runs created at or before this time (inclusive). */
        fun createdAtLte(createdAtLte: OffsetDateTime?) = apply { this.createdAtLte = createdAtLte }

        /** Alias for calling [Builder.createdAtLte] with `createdAtLte.orElse(null)`. */
        fun createdAtLte(createdAtLte: Optional<OffsetDateTime>) =
            createdAtLte(createdAtLte.getOrNull())

        /**
         * Filter to a specific deployment. Omit to list across all deployments in the workspace.
         * Filtering by a non-existent deployment_id returns 200 with empty data.
         */
        fun deploymentId(deploymentId: String?) = apply { this.deploymentId = deploymentId }

        /** Alias for calling [Builder.deploymentId] with `deploymentId.orElse(null)`. */
        fun deploymentId(deploymentId: Optional<String>) = deploymentId(deploymentId.getOrNull())

        /**
         * Filter: true for runs with non-null error, false for runs with non-null session_id. Omit
         * for all.
         */
        fun hasError(hasError: Boolean?) = apply { this.hasError = hasError }

        /**
         * Alias for [Builder.hasError].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun hasError(hasError: Boolean) = hasError(hasError as Boolean?)

        /** Alias for calling [Builder.hasError] with `hasError.orElse(null)`. */
        fun hasError(hasError: Optional<Boolean>) = hasError(hasError.getOrNull())

        /** Maximum results per page. Default 20, maximum 1000. */
        fun limit(limit: Int?) = apply { this.limit = limit }

        /**
         * Alias for [Builder.limit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun limit(limit: Int) = limit(limit as Int?)

        /** Alias for calling [Builder.limit] with `limit.orElse(null)`. */
        fun limit(limit: Optional<Int>) = limit(limit.getOrNull())

        /**
         * Opaque pagination cursor. Pass next_page from the previous response. Invalid or expired
         * cursors return 400.
         */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /** Filter runs by what triggered them. Omit to return all runs. */
        fun triggerType(triggerType: BetaManagedAgentsTriggerType?) = apply {
            this.triggerType = triggerType
        }

        /** Alias for calling [Builder.triggerType] with `triggerType.orElse(null)`. */
        fun triggerType(triggerType: Optional<BetaManagedAgentsTriggerType>) =
            triggerType(triggerType.getOrNull())

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

        /**
         * Returns an immutable instance of [DeploymentRunListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): DeploymentRunListParams =
            DeploymentRunListParams(
                createdAtGt,
                createdAtGte,
                createdAtLt,
                createdAtLte,
                deploymentId,
                hasError,
                limit,
                page,
                triggerType,
                betas?.toImmutable(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
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
                createdAtGt?.let {
                    put("created_at[gt]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                createdAtGte?.let {
                    put("created_at[gte]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                createdAtLt?.let {
                    put("created_at[lt]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                createdAtLte?.let {
                    put("created_at[lte]", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                deploymentId?.let { put("deployment_id", it) }
                hasError?.let { put("has_error", it.toString()) }
                limit?.let { put("limit", it.toString()) }
                page?.let { put("page", it) }
                triggerType?.let { put("trigger_type", it.toString()) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is DeploymentRunListParams &&
            createdAtGt == other.createdAtGt &&
            createdAtGte == other.createdAtGte &&
            createdAtLt == other.createdAtLt &&
            createdAtLte == other.createdAtLte &&
            deploymentId == other.deploymentId &&
            hasError == other.hasError &&
            limit == other.limit &&
            page == other.page &&
            triggerType == other.triggerType &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            createdAtGt,
            createdAtGte,
            createdAtLt,
            createdAtLte,
            deploymentId,
            hasError,
            limit,
            page,
            triggerType,
            betas,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "DeploymentRunListParams{createdAtGt=$createdAtGt, createdAtGte=$createdAtGte, createdAtLt=$createdAtLt, createdAtLte=$createdAtLte, deploymentId=$deploymentId, hasError=$hasError, limit=$limit, page=$page, triggerType=$triggerType, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
