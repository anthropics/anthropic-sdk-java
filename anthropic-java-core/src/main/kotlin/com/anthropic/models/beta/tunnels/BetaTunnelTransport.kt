package com.anthropic.models.beta.tunnels

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * How traffic reaches a tunnel: `{"type": "cloudflare"}` or `{"type": "relay"}`. In the create
 * response a `relay` tunnel's transport also carries its relay `token`; reads never carry a token.
 */
@JsonDeserialize(using = BetaTunnelTransport.Deserializer::class)
@JsonSerialize(using = BetaTunnelTransport.Serializer::class)
class BetaTunnelTransport
private constructor(
    private val cloudflare: BetaCloudflareTunnelTransport? = null,
    private val relay: BetaRelayTunnelTransport? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            cloudflare != null -> Type.CLOUDFLARE
            relay != null -> Type.RELAY
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /**
     * The tunnel is connected through the Cloudflare connector. Its connector token is fetched with
     * reveal_token. `type` is transitional: it reads `relay` for every tunnel once the Cloudflare
     * transport is retired.
     */
    fun cloudflare(): Optional<BetaCloudflareTunnelTransport> = Optional.ofNullable(cloudflare)

    /**
     * The tunnel is connected through Anthropic's relay. In the create response `token` is the
     * tunnel's relay token, shown that once (only a hash is kept, so reveal_token refuses a relay
     * tunnel and rotate_token issues a new one); reads never carry it.
     */
    fun relay(): Optional<BetaRelayTunnelTransport> = Optional.ofNullable(relay)

    fun isCloudflare(): Boolean = cloudflare != null

    fun isRelay(): Boolean = relay != null

    /**
     * The tunnel is connected through the Cloudflare connector. Its connector token is fetched with
     * reveal_token. `type` is transitional: it reads `relay` for every tunnel once the Cloudflare
     * transport is retired.
     */
    fun asCloudflare(): BetaCloudflareTunnelTransport = cloudflare.getOrThrow("cloudflare")

    /**
     * The tunnel is connected through Anthropic's relay. In the create response `token` is the
     * tunnel's relay token, shown that once (only a hash is kept, so reveal_token refuses a relay
     * tunnel and rotate_token issues a new one); reads never carry it.
     */
    fun asRelay(): BetaRelayTunnelTransport = relay.getOrThrow("relay")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    /**
     * Maps this instance's current variant to a value of type [T] using the given [visitor].
     *
     * Note that this method is _not_ forwards compatible with new variants from the API, unless
     * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of the
     * SDK gracefully, consider overriding [Visitor.unknown]:
     * ```java
     * import com.anthropic.core.JsonValue;
     * import java.util.Optional;
     *
     * Optional<String> result = betaTunnelTransport.accept(new BetaTunnelTransport.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitCloudflare(BetaCloudflareTunnelTransport cloudflare) {
     *         return Optional.of(cloudflare.toString());
     *     }
     *
     *     // ...
     *
     *     @Override
     *     public Optional<String> unknown(JsonValue json) {
     *         // Or inspect the `json`.
     *         return Optional.empty();
     *     }
     * });
     * ```
     *
     * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor] and
     *   the current variant is unknown.
     */
    fun <T> accept(visitor: Visitor<T>): T =
        when {
            cloudflare != null -> visitor.visitCloudflare(cloudflare)
            relay != null -> visitor.visitRelay(relay)
            else -> visitor.unknown(_json)
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
    fun validate(): BetaTunnelTransport = apply {
        if (validated) {
            return@apply
        }

        when {
            cloudflare != null -> cloudflare.validate()
            relay != null -> relay.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaTunnelTransport: $_json")
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
        when {
            cloudflare != null -> cloudflare.validity()
            relay != null -> relay.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTunnelTransport &&
            cloudflare == other.cloudflare &&
            relay == other.relay
    }

    override fun hashCode(): Int = Objects.hash(cloudflare, relay)

    override fun toString(): String =
        when {
            cloudflare != null -> "BetaTunnelTransport{cloudflare=$cloudflare}"
            relay != null -> "BetaTunnelTransport{relay=$relay}"
            _json != null -> "BetaTunnelTransport{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaTunnelTransport")
        }

    companion object {

        /**
         * The tunnel is connected through the Cloudflare connector. Its connector token is fetched
         * with reveal_token. `type` is transitional: it reads `relay` for every tunnel once the
         * Cloudflare transport is retired.
         */
        @JvmStatic
        fun ofCloudflare(cloudflare: BetaCloudflareTunnelTransport) =
            BetaTunnelTransport(cloudflare = cloudflare)

        /**
         * The tunnel is connected through Anthropic's relay. In the create response `token` is the
         * tunnel's relay token, shown that once (only a hash is kept, so reveal_token refuses a
         * relay tunnel and rotate_token issues a new one); reads never carry it.
         */
        @JvmStatic fun ofRelay(relay: BetaRelayTunnelTransport) = BetaTunnelTransport(relay = relay)
    }

    /**
     * An interface that defines how to map each variant of [BetaTunnelTransport] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        /**
         * The tunnel is connected through the Cloudflare connector. Its connector token is fetched
         * with reveal_token. `type` is transitional: it reads `relay` for every tunnel once the
         * Cloudflare transport is retired.
         */
        fun visitCloudflare(cloudflare: BetaCloudflareTunnelTransport): T

        /**
         * The tunnel is connected through Anthropic's relay. In the create response `token` is the
         * tunnel's relay token, shown that once (only a hash is kept, so reveal_token refuses a
         * relay tunnel and rotate_token issues a new one); reads never carry it.
         */
        fun visitRelay(relay: BetaRelayTunnelTransport): T

        /**
         * Maps an unknown variant of [BetaTunnelTransport] to a value of type [T].
         *
         * An instance of [BetaTunnelTransport] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaTunnelTransport: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaTunnelTransport>(BetaTunnelTransport::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaTunnelTransport {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "cloudflare" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCloudflareTunnelTransport>())
                        ?.let { BetaTunnelTransport(cloudflare = it, _json = json) }
                        ?: BetaTunnelTransport(_json = json)
                }
                "relay" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaRelayTunnelTransport>())?.let {
                        BetaTunnelTransport(relay = it, _json = json)
                    } ?: BetaTunnelTransport(_json = json)
                }
            }

            return BetaTunnelTransport(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BetaTunnelTransport>(BetaTunnelTransport::class) {

        override fun serialize(
            value: BetaTunnelTransport,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.cloudflare != null -> generator.writeObject(value.cloudflare)
                value.relay != null -> generator.writeObject(value.relay)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaTunnelTransport")
            }
        }
    }

    class Type private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val CLOUDFLARE = Type(JsonField.of("cloudflare"))

            @JvmField val RELAY = Type(JsonField.of("relay"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "cloudflare" -> CLOUDFLARE
                    "relay" -> RELAY
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            CLOUDFLARE,
            RELAY,
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            CLOUDFLARE,
            RELAY,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
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
                CLOUDFLARE -> Value.CLOUDFLARE
                RELAY -> Value.RELAY
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
                CLOUDFLARE -> Known.CLOUDFLARE
                RELAY -> Known.RELAY
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
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
        fun validate(): Type = apply {
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

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }
}
