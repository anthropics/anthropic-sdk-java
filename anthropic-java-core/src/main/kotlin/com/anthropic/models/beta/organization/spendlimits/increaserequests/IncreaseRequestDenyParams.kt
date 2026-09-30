package com.anthropic.models.beta.organization.spendlimits.increaserequests

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
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
 * Deny a pending spend limit increase request.
 *
 * Idempotent on `denied`; denying an already-`approved` request returns
 * 400. Anthropic emails the requester unless `suppress_notification` is set.
 */
class IncreaseRequestDenyParams
private constructor(
    private val spendLimitIncreaseRequestId: String?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** ID of the spend limit increase request. */
    fun spendLimitIncreaseRequestId(): Optional<String> =
        Optional.ofNullable(spendLimitIncreaseRequestId)

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun suppressNotification(): Optional<Boolean> = body.suppressNotification()

    /**
     * Returns the raw JSON value of [suppressNotification].
     *
     * Unlike [suppressNotification], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    fun _suppressNotification(): JsonField<Boolean> = body._suppressNotification()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): IncreaseRequestDenyParams = builder().build()

        /**
         * Returns a mutable builder for constructing an instance of [IncreaseRequestDenyParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [IncreaseRequestDenyParams]. */
    class Builder internal constructor() {

        private var spendLimitIncreaseRequestId: String? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(increaseRequestDenyParams: IncreaseRequestDenyParams) = apply {
            spendLimitIncreaseRequestId = increaseRequestDenyParams.spendLimitIncreaseRequestId
            body = increaseRequestDenyParams.body.toBuilder()
            additionalHeaders = increaseRequestDenyParams.additionalHeaders.toBuilder()
            additionalQueryParams = increaseRequestDenyParams.additionalQueryParams.toBuilder()
        }

        /** ID of the spend limit increase request. */
        fun spendLimitIncreaseRequestId(spendLimitIncreaseRequestId: String?) = apply {
            this.spendLimitIncreaseRequestId = spendLimitIncreaseRequestId
        }

        /**
         * Alias for calling [Builder.spendLimitIncreaseRequestId] with
         * `spendLimitIncreaseRequestId.orElse(null)`.
         */
        fun spendLimitIncreaseRequestId(spendLimitIncreaseRequestId: Optional<String>) =
            spendLimitIncreaseRequestId(spendLimitIncreaseRequestId.getOrNull())

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [suppressNotification]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        fun suppressNotification(suppressNotification: Boolean) = apply {
            body.suppressNotification(suppressNotification)
        }

        /**
         * Sets [Builder.suppressNotification] to an arbitrary JSON value.
         *
         * You should usually call [Builder.suppressNotification] with a well-typed [Boolean] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun suppressNotification(suppressNotification: JsonField<Boolean>) = apply {
            body.suppressNotification(suppressNotification)
        }

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
         * Returns an immutable instance of [IncreaseRequestDenyParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): IncreaseRequestDenyParams =
            IncreaseRequestDenyParams(
                spendLimitIncreaseRequestId,
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> spendLimitIncreaseRequestId ?: ""
            else -> ""
        }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val suppressNotification: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("suppress_notification")
            @ExcludeMissing
            suppressNotification: JsonField<Boolean> = JsonMissing.of()
        ) : this(suppressNotification, mutableMapOf())

        /**
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun suppressNotification(): Optional<Boolean> =
            suppressNotification.getOptional("suppress_notification")

        /**
         * Returns the raw JSON value of [suppressNotification].
         *
         * Unlike [suppressNotification], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("suppress_notification")
        @ExcludeMissing
        fun _suppressNotification(): JsonField<Boolean> = suppressNotification

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

            /** Returns a mutable builder for constructing an instance of [Body]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var suppressNotification: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                suppressNotification = body.suppressNotification
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            fun suppressNotification(suppressNotification: Boolean) =
                suppressNotification(JsonField.of(suppressNotification))

            /**
             * Sets [Builder.suppressNotification] to an arbitrary JSON value.
             *
             * You should usually call [Builder.suppressNotification] with a well-typed [Boolean]
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun suppressNotification(suppressNotification: JsonField<Boolean>) = apply {
                this.suppressNotification = suppressNotification
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
             */
            fun build(): Body = Body(suppressNotification, additionalProperties.toMutableMap())
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

            suppressNotification()
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
        internal fun validity(): Int = (if (suppressNotification.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                suppressNotification == other.suppressNotification &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(suppressNotification, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{suppressNotification=$suppressNotification, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is IncreaseRequestDenyParams &&
            spendLimitIncreaseRequestId == other.spendLimitIncreaseRequestId &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(spendLimitIncreaseRequestId, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "IncreaseRequestDenyParams{spendLimitIncreaseRequestId=$spendLimitIncreaseRequestId, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
