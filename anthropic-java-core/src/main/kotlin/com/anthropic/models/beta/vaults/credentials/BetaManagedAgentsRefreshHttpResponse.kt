// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects

/** An HTTP response captured during a credential validation probe. */
class BetaManagedAgentsRefreshHttpResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val body: JsonField<String>,
    private val bodyTruncated: JsonField<Boolean>,
    private val contentType: JsonField<String>,
    private val statusCode: JsonField<Int>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("body") @ExcludeMissing body: JsonField<String> = JsonMissing.of(),
        @JsonProperty("body_truncated")
        @ExcludeMissing
        bodyTruncated: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("content_type")
        @ExcludeMissing
        contentType: JsonField<String> = JsonMissing.of(),
        @JsonProperty("status_code") @ExcludeMissing statusCode: JsonField<Int> = JsonMissing.of(),
    ) : this(body, bodyTruncated, contentType, statusCode, mutableMapOf())

    /**
     * Response body. May be truncated and has sensitive values scrubbed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun body(): String = body.getRequired("body")

    /**
     * Whether `body` was truncated.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun bodyTruncated(): Boolean = bodyTruncated.getRequired("body_truncated")

    /**
     * Value of the `Content-Type` response header.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun contentType(): String = contentType.getRequired("content_type")

    /**
     * HTTP status code.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun statusCode(): Int = statusCode.getRequired("status_code")

    /**
     * Returns the raw JSON value of [body].
     *
     * Unlike [body], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("body") @ExcludeMissing fun _body(): JsonField<String> = body

    /**
     * Returns the raw JSON value of [bodyTruncated].
     *
     * Unlike [bodyTruncated], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("body_truncated")
    @ExcludeMissing
    fun _bodyTruncated(): JsonField<Boolean> = bodyTruncated

    /**
     * Returns the raw JSON value of [contentType].
     *
     * Unlike [contentType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content_type")
    @ExcludeMissing
    fun _contentType(): JsonField<String> = contentType

    /**
     * Returns the raw JSON value of [statusCode].
     *
     * Unlike [statusCode], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status_code") @ExcludeMissing fun _statusCode(): JsonField<Int> = statusCode

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
         * Returns a mutable builder for constructing an instance of
         * [BetaManagedAgentsRefreshHttpResponse].
         *
         * The following fields are required:
         * ```java
         * .body()
         * .bodyTruncated()
         * .contentType()
         * .statusCode()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsRefreshHttpResponse]. */
    class Builder internal constructor() {

        private var body: JsonField<String>? = null
        private var bodyTruncated: JsonField<Boolean>? = null
        private var contentType: JsonField<String>? = null
        private var statusCode: JsonField<Int>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsRefreshHttpResponse: BetaManagedAgentsRefreshHttpResponse
        ) = apply {
            body = betaManagedAgentsRefreshHttpResponse.body
            bodyTruncated = betaManagedAgentsRefreshHttpResponse.bodyTruncated
            contentType = betaManagedAgentsRefreshHttpResponse.contentType
            statusCode = betaManagedAgentsRefreshHttpResponse.statusCode
            additionalProperties =
                betaManagedAgentsRefreshHttpResponse.additionalProperties.toMutableMap()
        }

        /** Response body. May be truncated and has sensitive values scrubbed. */
        fun body(body: String) = body(JsonField.of(body))

        /**
         * Sets [Builder.body] to an arbitrary JSON value.
         *
         * You should usually call [Builder.body] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun body(body: JsonField<String>) = apply { this.body = body }

        /** Whether `body` was truncated. */
        fun bodyTruncated(bodyTruncated: Boolean) = bodyTruncated(JsonField.of(bodyTruncated))

        /**
         * Sets [Builder.bodyTruncated] to an arbitrary JSON value.
         *
         * You should usually call [Builder.bodyTruncated] with a well-typed [Boolean] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun bodyTruncated(bodyTruncated: JsonField<Boolean>) = apply {
            this.bodyTruncated = bodyTruncated
        }

        /** Value of the `Content-Type` response header. */
        fun contentType(contentType: String) = contentType(JsonField.of(contentType))

        /**
         * Sets [Builder.contentType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.contentType] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun contentType(contentType: JsonField<String>) = apply { this.contentType = contentType }

        /** HTTP status code. */
        fun statusCode(statusCode: Int) = statusCode(JsonField.of(statusCode))

        /**
         * Sets [Builder.statusCode] to an arbitrary JSON value.
         *
         * You should usually call [Builder.statusCode] with a well-typed [Int] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun statusCode(statusCode: JsonField<Int>) = apply { this.statusCode = statusCode }

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
         * Returns an immutable instance of [BetaManagedAgentsRefreshHttpResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .body()
         * .bodyTruncated()
         * .contentType()
         * .statusCode()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsRefreshHttpResponse =
            BetaManagedAgentsRefreshHttpResponse(
                checkRequired("body", body),
                checkRequired("bodyTruncated", bodyTruncated),
                checkRequired("contentType", contentType),
                checkRequired("statusCode", statusCode),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaManagedAgentsRefreshHttpResponse = apply {
        if (validated) {
            return@apply
        }

        body()
        bodyTruncated()
        contentType()
        statusCode()
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        (if (body.asKnown().isPresent) 1 else 0) +
            (if (bodyTruncated.asKnown().isPresent) 1 else 0) +
            (if (contentType.asKnown().isPresent) 1 else 0) +
            (if (statusCode.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsRefreshHttpResponse &&
            body == other.body &&
            bodyTruncated == other.bodyTruncated &&
            contentType == other.contentType &&
            statusCode == other.statusCode &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(body, bodyTruncated, contentType, statusCode, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsRefreshHttpResponse{body=$body, bodyTruncated=$bodyTruncated, contentType=$contentType, statusCode=$statusCode, additionalProperties=$additionalProperties}"
}
