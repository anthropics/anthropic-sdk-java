// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.Enum
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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Outcome of a refresh-token exchange attempted during credential validation. */
class BetaManagedAgentsRefreshObject
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val httpResponse: JsonField<BetaManagedAgentsRefreshHttpResponse>,
    private val status: JsonField<Status>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("http_response")
        @ExcludeMissing
        httpResponse: JsonField<BetaManagedAgentsRefreshHttpResponse> = JsonMissing.of(),
        @JsonProperty("status") @ExcludeMissing status: JsonField<Status> = JsonMissing.of(),
    ) : this(httpResponse, status, mutableMapOf())

    /**
     * An HTTP response captured during a credential validation probe.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun httpResponse(): Optional<BetaManagedAgentsRefreshHttpResponse> =
        httpResponse.getOptional("http_response")

    /**
     * Outcome of a refresh-token exchange attempted during credential validation.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun status(): Status = status.getRequired("status")

    /**
     * Returns the raw JSON value of [httpResponse].
     *
     * Unlike [httpResponse], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("http_response")
    @ExcludeMissing
    fun _httpResponse(): JsonField<BetaManagedAgentsRefreshHttpResponse> = httpResponse

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status") @ExcludeMissing fun _status(): JsonField<Status> = status

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
         * [BetaManagedAgentsRefreshObject].
         *
         * The following fields are required:
         * ```java
         * .httpResponse()
         * .status()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsRefreshObject]. */
    class Builder internal constructor() {

        private var httpResponse: JsonField<BetaManagedAgentsRefreshHttpResponse>? = null
        private var status: JsonField<Status>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsRefreshObject: BetaManagedAgentsRefreshObject) = apply {
            httpResponse = betaManagedAgentsRefreshObject.httpResponse
            status = betaManagedAgentsRefreshObject.status
            additionalProperties =
                betaManagedAgentsRefreshObject.additionalProperties.toMutableMap()
        }

        /** An HTTP response captured during a credential validation probe. */
        fun httpResponse(httpResponse: BetaManagedAgentsRefreshHttpResponse?) =
            httpResponse(JsonField.ofNullable(httpResponse))

        /** Alias for calling [Builder.httpResponse] with `httpResponse.orElse(null)`. */
        fun httpResponse(httpResponse: Optional<BetaManagedAgentsRefreshHttpResponse>) =
            httpResponse(httpResponse.getOrNull())

        /**
         * Sets [Builder.httpResponse] to an arbitrary JSON value.
         *
         * You should usually call [Builder.httpResponse] with a well-typed
         * [BetaManagedAgentsRefreshHttpResponse] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun httpResponse(httpResponse: JsonField<BetaManagedAgentsRefreshHttpResponse>) = apply {
            this.httpResponse = httpResponse
        }

        /** Outcome of a refresh-token exchange attempted during credential validation. */
        fun status(status: Status) = status(JsonField.of(status))

        /**
         * Sets [Builder.status] to an arbitrary JSON value.
         *
         * You should usually call [Builder.status] with a well-typed [Status] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun status(status: JsonField<Status>) = apply { this.status = status }

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
         * Returns an immutable instance of [BetaManagedAgentsRefreshObject].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .httpResponse()
         * .status()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsRefreshObject =
            BetaManagedAgentsRefreshObject(
                checkRequired("httpResponse", httpResponse),
                checkRequired("status", status),
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
    fun validate(): BetaManagedAgentsRefreshObject = apply {
        if (validated) {
            return@apply
        }

        httpResponse().ifPresent { it.validate() }
        status().validate()
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
        (httpResponse.asKnown().getOrNull()?.validity() ?: 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0)

    /** Outcome of a refresh-token exchange attempted during credential validation. */
    class Status @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val SUCCEEDED = of("succeeded")

            @JvmField val FAILED = of("failed")

            @JvmField val CONNECT_ERROR = of("connect_error")

            @JvmField val NO_REFRESH_TOKEN = of("no_refresh_token")

            @JvmStatic fun of(value: String) = Status(JsonField.of(value))
        }

        /** An enum containing [Status]'s known values. */
        enum class Known {
            SUCCEEDED,
            FAILED,
            CONNECT_ERROR,
            NO_REFRESH_TOKEN,
        }

        /**
         * An enum containing [Status]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Status] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            SUCCEEDED,
            FAILED,
            CONNECT_ERROR,
            NO_REFRESH_TOKEN,
            /** An enum member indicating that [Status] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                SUCCEEDED -> Value.SUCCEEDED
                FAILED -> Value.FAILED
                CONNECT_ERROR -> Value.CONNECT_ERROR
                NO_REFRESH_TOKEN -> Value.NO_REFRESH_TOKEN
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                SUCCEEDED -> Known.SUCCEEDED
                FAILED -> Known.FAILED
                CONNECT_ERROR -> Known.CONNECT_ERROR
                NO_REFRESH_TOKEN -> Known.NO_REFRESH_TOKEN
                else -> throw AnthropicInvalidDataException("Unknown Status: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
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
        fun validate(): Status = apply {
            if (validated) {
                return@apply
            }

            known()
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
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Status && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsRefreshObject &&
            httpResponse == other.httpResponse &&
            status == other.status &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(httpResponse, status, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsRefreshObject{httpResponse=$httpResponse, status=$status, additionalProperties=$additionalProperties}"
}
