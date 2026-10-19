package com.anthropic.models.organization.workspaces.ratelimits

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.opentelemetry.SpanData
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Set or clear rate-limit overrides for one rate-limit group in a workspace.
 *
 * Only the limiter types named in `limits` change: an integer `value` sets that type's override and
 * `null` clears it. Types not named keep their current value. A value above the organization's own
 * limit is stored, but the organization limit still applies, so the lower of the two is enforced.
 *
 * Returns the targeted group's entry with this request applied, in the shape `GET
 * /v1/organizations/workspaces/{workspace_id}/rate_limits` returns. Changes take effect within
 * about an hour.
 */
class RateLimitUpdateParams
private constructor(
    private val workspaceId: String,
    private val rateLimitId: String?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** The ID of the workspace. */
    fun workspaceId(): String = workspaceId

    /** The `id` of the organization's rate-limit entry to set overrides for. */
    fun rateLimitId(): Optional<String> = Optional.ofNullable(rateLimitId)

    /**
     * Limits to set or clear. Unspecified limit types are unchanged.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun limits(): List<WorkspaceRateLimitUpdateValue> = body.limits()

    /**
     * Returns the raw JSON value of [limits].
     *
     * Unlike [limits], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _limits(): JsonField<List<WorkspaceRateLimitUpdateValue>> = body._limits()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [RateLimitUpdateParams].
         *
         * The following fields are required:
         * ```java
         * .workspaceId()
         * .limits()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [RateLimitUpdateParams]. */
    class Builder internal constructor() {

        private var workspaceId: String? = null
        private var rateLimitId: String? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(rateLimitUpdateParams: RateLimitUpdateParams) = apply {
            workspaceId = rateLimitUpdateParams.workspaceId
            rateLimitId = rateLimitUpdateParams.rateLimitId
            body = rateLimitUpdateParams.body.toBuilder()
            additionalHeaders = rateLimitUpdateParams.additionalHeaders.toBuilder()
            additionalQueryParams = rateLimitUpdateParams.additionalQueryParams.toBuilder()
        }

        /** The ID of the workspace. */
        fun workspaceId(workspaceId: String) = apply { this.workspaceId = workspaceId }

        /** The `id` of the organization's rate-limit entry to set overrides for. */
        fun rateLimitId(rateLimitId: String?) = apply { this.rateLimitId = rateLimitId }

        /** Alias for calling [Builder.rateLimitId] with `rateLimitId.orElse(null)`. */
        fun rateLimitId(rateLimitId: Optional<String>) = rateLimitId(rateLimitId.getOrNull())

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [limits]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** Limits to set or clear. Unspecified limit types are unchanged. */
        fun limits(limits: List<WorkspaceRateLimitUpdateValue>) = apply { body.limits(limits) }

        /**
         * Sets [Builder.limits] to an arbitrary JSON value.
         *
         * You should usually call [Builder.limits] with a well-typed
         * `List<WorkspaceRateLimitUpdateValue>` value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun limits(limits: JsonField<List<WorkspaceRateLimitUpdateValue>>) = apply {
            body.limits(limits)
        }

        /**
         * Adds a single [WorkspaceRateLimitUpdateValue] to [limits].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addLimit(limit: WorkspaceRateLimitUpdateValue) = apply { body.addLimit(limit) }

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            body.additionalProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            body.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                body.putAllAdditionalProperties(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply { body.removeAdditionalProperty(key) }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            body.removeAllAdditionalProperties(keys)
        }

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
         * Returns an immutable instance of [RateLimitUpdateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .workspaceId()
         * .limits()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): RateLimitUpdateParams =
            RateLimitUpdateParams(
                checkRequired("workspaceId", workspaceId),
                rateLimitId,
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> workspaceId
            1 -> rateLimitId ?: ""
            else -> ""
        }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    override fun _spanData(): SpanData =
        SpanData(
            name = "anthropic.organization.workspaces.rate_limits.update",
            route = "/v1/organizations/workspaces/{workspace_id}/rate_limits/{rate_limit_id}",
            attributes =
                mapOf(
                    "anthropic.organization.workspace.id" to _pathParam(0),
                    "anthropic.organization.workspace.rate_limit.id" to _pathParam(1),
                ),
        )

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val limits: JsonField<List<WorkspaceRateLimitUpdateValue>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("limits")
            @ExcludeMissing
            limits: JsonField<List<WorkspaceRateLimitUpdateValue>> = JsonMissing.of()
        ) : this(limits, mutableMapOf())

        /**
         * Limits to set or clear. Unspecified limit types are unchanged.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun limits(): List<WorkspaceRateLimitUpdateValue> = limits.getRequired("limits")

        /**
         * Returns the raw JSON value of [limits].
         *
         * Unlike [limits], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("limits")
        @ExcludeMissing
        fun _limits(): JsonField<List<WorkspaceRateLimitUpdateValue>> = limits

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /**
             * Returns a mutable builder for constructing an instance of [Body].
             *
             * The following fields are required:
             * ```java
             * .limits()
             * ```
             */
            @JvmStatic fun builder() = Builder()

            /**
             * Returns an immutable instance of [Body] with the required [limits] set to the given
             * value.
             */
            @JvmStatic
            fun of(limits: List<WorkspaceRateLimitUpdateValue>) = builder().limits(limits).build()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var limits: JsonField<MutableList<WorkspaceRateLimitUpdateValue>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                limits = body.limits.map { it.toMutableList() }.takeUnless { it.isMissing() }
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /** Limits to set or clear. Unspecified limit types are unchanged. */
            fun limits(limits: List<WorkspaceRateLimitUpdateValue>) = limits(JsonField.of(limits))

            /**
             * Sets [Builder.limits] to an arbitrary JSON value.
             *
             * You should usually call [Builder.limits] with a well-typed
             * `List<WorkspaceRateLimitUpdateValue>` value instead. This method is primarily for
             * setting the field to an undocumented or not yet supported value.
             */
            fun limits(limits: JsonField<List<WorkspaceRateLimitUpdateValue>>) = apply {
                this.limits = limits.map { it.toMutableList() }
            }

            /**
             * Adds a single [WorkspaceRateLimitUpdateValue] to [limits].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addLimit(limit: WorkspaceRateLimitUpdateValue) = apply {
                limits =
                    (limits ?: JsonField.of(mutableListOf())).also {
                        checkKnown("limits", it).add(limit)
                    }
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [Body].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .limits()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("limits", limits).map { it.toImmutable() },
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            limits().forEach { it.validate() }
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: AnthropicInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            (limits.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                limits == other.limits &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(limits, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Body{limits=$limits, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is RateLimitUpdateParams &&
            workspaceId == other.workspaceId &&
            rateLimitId == other.rateLimitId &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(workspaceId, rateLimitId, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "RateLimitUpdateParams{workspaceId=$workspaceId, rateLimitId=$rateLimitId, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
