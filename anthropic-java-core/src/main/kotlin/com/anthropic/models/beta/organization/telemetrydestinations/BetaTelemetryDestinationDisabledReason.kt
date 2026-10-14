package com.anthropic.models.beta.organization.telemetrydestinations

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
 * Why the destination is disabled: `caller` when a caller disabled it, or the reason the API
 * disabled it automatically (`sustained_failure`, `invalid_address`, or `redirect`). Every
 * automatic disable is reversible with an update that sets `state` to `enabled`. An update that
 * sets `disabled` on a destination that is already disabled keeps its reason.
 */
@JsonDeserialize(using = BetaTelemetryDestinationDisabledReason.Deserializer::class)
@JsonSerialize(using = BetaTelemetryDestinationDisabledReason.Serializer::class)
class BetaTelemetryDestinationDisabledReason
private constructor(
    private val caller: BetaTelemetryDestinationDisabledReasonCaller? = null,
    private val sustainedFailure: BetaTelemetryDestinationDisabledReasonSustainedFailure? = null,
    private val invalidAddress: BetaTelemetryDestinationDisabledReasonInvalidAddress? = null,
    private val redirect: BetaTelemetryDestinationDisabledReasonRedirect? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            caller != null -> Type.CALLER
            sustainedFailure != null -> Type.SUSTAINED_FAILURE
            invalidAddress != null -> Type.INVALID_ADDRESS
            redirect != null -> Type.REDIRECT
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /** A caller disabled the destination with an update that set `state` to `disabled`. */
    fun caller(): Optional<BetaTelemetryDestinationDisabledReasonCaller> =
        Optional.ofNullable(caller)

    /**
     * The API disabled the destination because deliveries to it failed, with no successful delivery
     * in between, for longer than a threshold that Anthropic configures and that is not part of
     * this contract. The reason is `invalid_address` instead when the most recent failed delivery
     * failed with an invalid address.
     */
    fun sustainedFailure(): Optional<BetaTelemetryDestinationDisabledReasonSustainedFailure> =
        Optional.ofNullable(sustainedFailure)

    /**
     * The API disabled the destination because deliveries to it failed for longer than the same
     * threshold as for `sustained_failure`, and the most recent failed delivery failed with an
     * invalid address: the DNS answer says that the endpoint's hostname doesn't exist or doesn't
     * have an address, or the address that the API would connect to isn't publicly routable or is
     * reserved, for example a loopback, link-local, private-network, or cloud-metadata address. The
     * API doesn't connect to such an address, and retries the delivery a bounded number of times as
     * it does after a timeout. A timeout or a server failure of the DNS lookup is a temporary DNS
     * failure instead, not an invalid address.
     */
    fun invalidAddress(): Optional<BetaTelemetryDestinationDisabledReasonInvalidAddress> =
        Optional.ofNullable(invalidAddress)

    /**
     * The API disabled the destination because the endpoint answered a delivery with a 3xx status.
     * Redirects are never followed, and one such answer disables the destination at once.
     */
    fun redirect(): Optional<BetaTelemetryDestinationDisabledReasonRedirect> =
        Optional.ofNullable(redirect)

    fun isCaller(): Boolean = caller != null

    fun isSustainedFailure(): Boolean = sustainedFailure != null

    fun isInvalidAddress(): Boolean = invalidAddress != null

    fun isRedirect(): Boolean = redirect != null

    /** A caller disabled the destination with an update that set `state` to `disabled`. */
    fun asCaller(): BetaTelemetryDestinationDisabledReasonCaller = caller.getOrThrow("caller")

    /**
     * The API disabled the destination because deliveries to it failed, with no successful delivery
     * in between, for longer than a threshold that Anthropic configures and that is not part of
     * this contract. The reason is `invalid_address` instead when the most recent failed delivery
     * failed with an invalid address.
     */
    fun asSustainedFailure(): BetaTelemetryDestinationDisabledReasonSustainedFailure =
        sustainedFailure.getOrThrow("sustainedFailure")

    /**
     * The API disabled the destination because deliveries to it failed for longer than the same
     * threshold as for `sustained_failure`, and the most recent failed delivery failed with an
     * invalid address: the DNS answer says that the endpoint's hostname doesn't exist or doesn't
     * have an address, or the address that the API would connect to isn't publicly routable or is
     * reserved, for example a loopback, link-local, private-network, or cloud-metadata address. The
     * API doesn't connect to such an address, and retries the delivery a bounded number of times as
     * it does after a timeout. A timeout or a server failure of the DNS lookup is a temporary DNS
     * failure instead, not an invalid address.
     */
    fun asInvalidAddress(): BetaTelemetryDestinationDisabledReasonInvalidAddress =
        invalidAddress.getOrThrow("invalidAddress")

    /**
     * The API disabled the destination because the endpoint answered a delivery with a 3xx status.
     * Redirects are never followed, and one such answer disables the destination at once.
     */
    fun asRedirect(): BetaTelemetryDestinationDisabledReasonRedirect =
        redirect.getOrThrow("redirect")

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
     * Optional<String> result = betaTelemetryDestinationDisabledReason.accept(new BetaTelemetryDestinationDisabledReason.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitCaller(BetaTelemetryDestinationDisabledReasonCaller caller) {
     *         return Optional.of(caller.toString());
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
            caller != null -> visitor.visitCaller(caller)
            sustainedFailure != null -> visitor.visitSustainedFailure(sustainedFailure)
            invalidAddress != null -> visitor.visitInvalidAddress(invalidAddress)
            redirect != null -> visitor.visitRedirect(redirect)
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
    fun validate(): BetaTelemetryDestinationDisabledReason = apply {
        if (validated) {
            return@apply
        }

        when {
            caller != null -> caller.validate()
            sustainedFailure != null -> sustainedFailure.validate()
            invalidAddress != null -> invalidAddress.validate()
            redirect != null -> redirect.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaTelemetryDestinationDisabledReason: $_json"
                )
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
            caller != null -> caller.validity()
            sustainedFailure != null -> sustainedFailure.validity()
            invalidAddress != null -> invalidAddress.validity()
            redirect != null -> redirect.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTelemetryDestinationDisabledReason &&
            caller == other.caller &&
            sustainedFailure == other.sustainedFailure &&
            invalidAddress == other.invalidAddress &&
            redirect == other.redirect
    }

    override fun hashCode(): Int = Objects.hash(caller, sustainedFailure, invalidAddress, redirect)

    override fun toString(): String =
        when {
            caller != null -> "BetaTelemetryDestinationDisabledReason{caller=$caller}"
            sustainedFailure != null ->
                "BetaTelemetryDestinationDisabledReason{sustainedFailure=$sustainedFailure}"
            invalidAddress != null ->
                "BetaTelemetryDestinationDisabledReason{invalidAddress=$invalidAddress}"
            redirect != null -> "BetaTelemetryDestinationDisabledReason{redirect=$redirect}"
            _json != null -> "BetaTelemetryDestinationDisabledReason{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaTelemetryDestinationDisabledReason")
        }

    companion object {

        /** A caller disabled the destination with an update that set `state` to `disabled`. */
        @JvmStatic
        fun ofCaller(caller: BetaTelemetryDestinationDisabledReasonCaller) =
            BetaTelemetryDestinationDisabledReason(caller = caller)

        /**
         * The API disabled the destination because deliveries to it failed, with no successful
         * delivery in between, for longer than a threshold that Anthropic configures and that is
         * not part of this contract. The reason is `invalid_address` instead when the most recent
         * failed delivery failed with an invalid address.
         */
        @JvmStatic
        fun ofSustainedFailure(
            sustainedFailure: BetaTelemetryDestinationDisabledReasonSustainedFailure
        ) = BetaTelemetryDestinationDisabledReason(sustainedFailure = sustainedFailure)

        /**
         * The API disabled the destination because deliveries to it failed for longer than the same
         * threshold as for `sustained_failure`, and the most recent failed delivery failed with an
         * invalid address: the DNS answer says that the endpoint's hostname doesn't exist or
         * doesn't have an address, or the address that the API would connect to isn't publicly
         * routable or is reserved, for example a loopback, link-local, private-network, or
         * cloud-metadata address. The API doesn't connect to such an address, and retries the
         * delivery a bounded number of times as it does after a timeout. A timeout or a server
         * failure of the DNS lookup is a temporary DNS failure instead, not an invalid address.
         */
        @JvmStatic
        fun ofInvalidAddress(invalidAddress: BetaTelemetryDestinationDisabledReasonInvalidAddress) =
            BetaTelemetryDestinationDisabledReason(invalidAddress = invalidAddress)

        /**
         * The API disabled the destination because the endpoint answered a delivery with a 3xx
         * status. Redirects are never followed, and one such answer disables the destination at
         * once.
         */
        @JvmStatic
        fun ofRedirect(redirect: BetaTelemetryDestinationDisabledReasonRedirect) =
            BetaTelemetryDestinationDisabledReason(redirect = redirect)
    }

    /**
     * An interface that defines how to map each variant of [BetaTelemetryDestinationDisabledReason]
     * to a value of type [T].
     */
    interface Visitor<out T> {

        /** A caller disabled the destination with an update that set `state` to `disabled`. */
        fun visitCaller(caller: BetaTelemetryDestinationDisabledReasonCaller): T

        /**
         * The API disabled the destination because deliveries to it failed, with no successful
         * delivery in between, for longer than a threshold that Anthropic configures and that is
         * not part of this contract. The reason is `invalid_address` instead when the most recent
         * failed delivery failed with an invalid address.
         */
        fun visitSustainedFailure(
            sustainedFailure: BetaTelemetryDestinationDisabledReasonSustainedFailure
        ): T

        /**
         * The API disabled the destination because deliveries to it failed for longer than the same
         * threshold as for `sustained_failure`, and the most recent failed delivery failed with an
         * invalid address: the DNS answer says that the endpoint's hostname doesn't exist or
         * doesn't have an address, or the address that the API would connect to isn't publicly
         * routable or is reserved, for example a loopback, link-local, private-network, or
         * cloud-metadata address. The API doesn't connect to such an address, and retries the
         * delivery a bounded number of times as it does after a timeout. A timeout or a server
         * failure of the DNS lookup is a temporary DNS failure instead, not an invalid address.
         */
        fun visitInvalidAddress(
            invalidAddress: BetaTelemetryDestinationDisabledReasonInvalidAddress
        ): T

        /**
         * The API disabled the destination because the endpoint answered a delivery with a 3xx
         * status. Redirects are never followed, and one such answer disables the destination at
         * once.
         */
        fun visitRedirect(redirect: BetaTelemetryDestinationDisabledReasonRedirect): T

        /**
         * Maps an unknown variant of [BetaTelemetryDestinationDisabledReason] to a value of type
         * [T].
         *
         * An instance of [BetaTelemetryDestinationDisabledReason] can contain an unknown variant if
         * it was deserialized from data that doesn't match any known variant. For example, if the
         * SDK is on an older version than the API, then the API may respond with new variants that
         * the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaTelemetryDestinationDisabledReason: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaTelemetryDestinationDisabledReason>(
            BetaTelemetryDestinationDisabledReason::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaTelemetryDestinationDisabledReason {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "caller" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaTelemetryDestinationDisabledReasonCaller>(),
                        )
                        ?.let { BetaTelemetryDestinationDisabledReason(caller = it, _json = json) }
                        ?: BetaTelemetryDestinationDisabledReason(_json = json)
                }
                "sustained_failure" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaTelemetryDestinationDisabledReasonSustainedFailure>(),
                        )
                        ?.let {
                            BetaTelemetryDestinationDisabledReason(
                                sustainedFailure = it,
                                _json = json,
                            )
                        } ?: BetaTelemetryDestinationDisabledReason(_json = json)
                }
                "invalid_address" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaTelemetryDestinationDisabledReasonInvalidAddress>(),
                        )
                        ?.let {
                            BetaTelemetryDestinationDisabledReason(
                                invalidAddress = it,
                                _json = json,
                            )
                        } ?: BetaTelemetryDestinationDisabledReason(_json = json)
                }
                "redirect" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaTelemetryDestinationDisabledReasonRedirect>(),
                        )
                        ?.let {
                            BetaTelemetryDestinationDisabledReason(redirect = it, _json = json)
                        } ?: BetaTelemetryDestinationDisabledReason(_json = json)
                }
            }

            return BetaTelemetryDestinationDisabledReason(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaTelemetryDestinationDisabledReason>(
            BetaTelemetryDestinationDisabledReason::class
        ) {

        override fun serialize(
            value: BetaTelemetryDestinationDisabledReason,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.caller != null -> generator.writeObject(value.caller)
                value.sustainedFailure != null -> generator.writeObject(value.sustainedFailure)
                value.invalidAddress != null -> generator.writeObject(value.invalidAddress)
                value.redirect != null -> generator.writeObject(value.redirect)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException("Invalid BetaTelemetryDestinationDisabledReason")
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

            @JvmField val CALLER = Type(JsonField.of("caller"))

            @JvmField val SUSTAINED_FAILURE = Type(JsonField.of("sustained_failure"))

            @JvmField val INVALID_ADDRESS = Type(JsonField.of("invalid_address"))

            @JvmField val REDIRECT = Type(JsonField.of("redirect"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "caller" -> CALLER
                    "sustained_failure" -> SUSTAINED_FAILURE
                    "invalid_address" -> INVALID_ADDRESS
                    "redirect" -> REDIRECT
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            CALLER,
            SUSTAINED_FAILURE,
            INVALID_ADDRESS,
            REDIRECT,
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
            CALLER,
            SUSTAINED_FAILURE,
            INVALID_ADDRESS,
            REDIRECT,
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
                CALLER -> Value.CALLER
                SUSTAINED_FAILURE -> Value.SUSTAINED_FAILURE
                INVALID_ADDRESS -> Value.INVALID_ADDRESS
                REDIRECT -> Value.REDIRECT
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
                CALLER -> Known.CALLER
                SUSTAINED_FAILURE -> Known.SUSTAINED_FAILURE
                INVALID_ADDRESS -> Known.INVALID_ADDRESS
                REDIRECT -> Known.REDIRECT
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
