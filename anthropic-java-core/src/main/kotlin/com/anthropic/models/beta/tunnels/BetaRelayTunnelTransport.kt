package com.anthropic.models.beta.tunnels

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
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
 * The tunnel is connected through Anthropic's relay. In the create response `token` is the tunnel's
 * relay token, shown that once (only a hash is kept, so reveal_token refuses a relay tunnel and
 * rotate_token issues a new one); reads never carry it.
 */
class BetaRelayTunnelTransport
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val token: JsonField<BetaTunnelToken>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("token") @ExcludeMissing token: JsonField<BetaTunnelToken> = JsonMissing.of(),
    ) : this(type, token, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("relay")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * The tunnel's relay token. Present only in the create response, which issues it; absent on
     * every read. Store it: Anthropic keeps only a hash, reveal_token refuses a relay tunnel, and
     * rotate_token is the only way to obtain a new one.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun token(): Optional<BetaTunnelToken> = token.getOptional("token")

    /**
     * Returns the raw JSON value of [token].
     *
     * Unlike [token], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("token") @ExcludeMissing fun _token(): JsonField<BetaTunnelToken> = token

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

        /** Returns a mutable builder for constructing an instance of [BetaRelayTunnelTransport]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaRelayTunnelTransport]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("relay")
        private var token: JsonField<BetaTunnelToken> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaRelayTunnelTransport: BetaRelayTunnelTransport) = apply {
            type = betaRelayTunnelTransport.type
            token = betaRelayTunnelTransport.token
            additionalProperties = betaRelayTunnelTransport.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("relay")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * The tunnel's relay token. Present only in the create response, which issues it; absent on
         * every read. Store it: Anthropic keeps only a hash, reveal_token refuses a relay tunnel,
         * and rotate_token is the only way to obtain a new one.
         */
        fun token(token: BetaTunnelToken) = token(JsonField.of(token))

        /**
         * Sets [Builder.token] to an arbitrary JSON value.
         *
         * You should usually call [Builder.token] with a well-typed [BetaTunnelToken] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun token(token: JsonField<BetaTunnelToken>) = apply { this.token = token }

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
         * Returns an immutable instance of [BetaRelayTunnelTransport].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaRelayTunnelTransport =
            BetaRelayTunnelTransport(type, token, additionalProperties.toMutableMap())
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
    fun validate(): BetaRelayTunnelTransport = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("relay")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        token().ifPresent { it.validate() }
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
        type.let { if (it == JsonValue.from("relay")) 1 else 0 } +
            (token.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaRelayTunnelTransport &&
            type == other.type &&
            token == other.token &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(type, token, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRelayTunnelTransport{type=$type, token=$token, additionalProperties=$additionalProperties}"
}
