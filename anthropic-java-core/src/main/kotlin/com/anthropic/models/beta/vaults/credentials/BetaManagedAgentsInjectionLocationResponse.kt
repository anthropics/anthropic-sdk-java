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

/** Where in the outbound request the secret value is substituted. */
class BetaManagedAgentsInjectionLocationResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val body: JsonField<Boolean>,
    private val header: JsonField<Boolean>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("body") @ExcludeMissing body: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("header") @ExcludeMissing header: JsonField<Boolean> = JsonMissing.of(),
    ) : this(body, header, mutableMapOf())

    /**
     * Whether the placeholder is substituted in the request body.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun body(): Boolean = body.getRequired("body")

    /**
     * Whether the placeholder is substituted in request header values.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun header(): Boolean = header.getRequired("header")

    /**
     * Returns the raw JSON value of [body].
     *
     * Unlike [body], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("body") @ExcludeMissing fun _body(): JsonField<Boolean> = body

    /**
     * Returns the raw JSON value of [header].
     *
     * Unlike [header], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("header") @ExcludeMissing fun _header(): JsonField<Boolean> = header

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
         * [BetaManagedAgentsInjectionLocationResponse].
         *
         * The following fields are required:
         * ```java
         * .body()
         * .header()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsInjectionLocationResponse]. */
    class Builder internal constructor() {

        private var body: JsonField<Boolean>? = null
        private var header: JsonField<Boolean>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsInjectionLocationResponse: BetaManagedAgentsInjectionLocationResponse
        ) = apply {
            body = betaManagedAgentsInjectionLocationResponse.body
            header = betaManagedAgentsInjectionLocationResponse.header
            additionalProperties =
                betaManagedAgentsInjectionLocationResponse.additionalProperties.toMutableMap()
        }

        /** Whether the placeholder is substituted in the request body. */
        fun body(body: Boolean) = body(JsonField.of(body))

        /**
         * Sets [Builder.body] to an arbitrary JSON value.
         *
         * You should usually call [Builder.body] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun body(body: JsonField<Boolean>) = apply { this.body = body }

        /** Whether the placeholder is substituted in request header values. */
        fun header(header: Boolean) = header(JsonField.of(header))

        /**
         * Sets [Builder.header] to an arbitrary JSON value.
         *
         * You should usually call [Builder.header] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun header(header: JsonField<Boolean>) = apply { this.header = header }

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
         * Returns an immutable instance of [BetaManagedAgentsInjectionLocationResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .body()
         * .header()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsInjectionLocationResponse =
            BetaManagedAgentsInjectionLocationResponse(
                checkRequired("body", body),
                checkRequired("header", header),
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
    fun validate(): BetaManagedAgentsInjectionLocationResponse = apply {
        if (validated) {
            return@apply
        }

        body()
        header()
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
        (if (body.asKnown().isPresent) 1 else 0) + (if (header.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsInjectionLocationResponse &&
            body == other.body &&
            header == other.header &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(body, header, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsInjectionLocationResponse{body=$body, header=$header, additionalProperties=$additionalProperties}"
}
