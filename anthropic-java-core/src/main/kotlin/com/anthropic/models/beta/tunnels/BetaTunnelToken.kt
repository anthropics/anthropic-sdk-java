// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.tunnels

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

/** A tunnel's connector token. */
class BetaTunnelToken
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val tunnelToken: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("tunnel_token")
        @ExcludeMissing
        tunnelToken: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(id, tunnelToken, type, mutableMapOf())

    /**
     * Stable identifier for the current token value. Changes when the token is rotated.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * The connector token used to run the tunnel. Treat as a credential.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun tunnelToken(): String = tunnelToken.getRequired("tunnel_token")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("tunnel_token")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [tunnelToken].
     *
     * Unlike [tunnelToken], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tunnel_token")
    @ExcludeMissing
    fun _tunnelToken(): JsonField<String> = tunnelToken

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
         * Returns a mutable builder for constructing an instance of [BetaTunnelToken].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .tunnelToken()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaTunnelToken]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var tunnelToken: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("tunnel_token")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaTunnelToken: BetaTunnelToken) = apply {
            id = betaTunnelToken.id
            tunnelToken = betaTunnelToken.tunnelToken
            type = betaTunnelToken.type
            additionalProperties = betaTunnelToken.additionalProperties.toMutableMap()
        }

        /** Stable identifier for the current token value. Changes when the token is rotated. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** The connector token used to run the tunnel. Treat as a credential. */
        fun tunnelToken(tunnelToken: String) = tunnelToken(JsonField.of(tunnelToken))

        /**
         * Sets [Builder.tunnelToken] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tunnelToken] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun tunnelToken(tunnelToken: JsonField<String>) = apply { this.tunnelToken = tunnelToken }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("tunnel_token")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaTunnelToken].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .tunnelToken()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaTunnelToken =
            BetaTunnelToken(
                checkRequired("id", id),
                checkRequired("tunnelToken", tunnelToken),
                type,
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
    fun validate(): BetaTunnelToken = apply {
        if (validated) {
            return@apply
        }

        id()
        tunnelToken()
        _type().let {
            if (it != JsonValue.from("tunnel_token")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (if (tunnelToken.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("tunnel_token")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTunnelToken &&
            id == other.id &&
            tunnelToken == other.tunnelToken &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(id, tunnelToken, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaTunnelToken{id=$id, tunnelToken=$tunnelToken, type=$type, additionalProperties=$additionalProperties}"
}
