package com.anthropic.models.beta.organization.rbacroles

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

@JsonDeserialize(using = BetaRbacRoleSource.Deserializer::class)
@JsonSerialize(using = BetaRbacRoleSource.Serializer::class)
class BetaRbacRoleSource
private constructor(
    private val builtIn: BetaRbacRoleSourceBuiltIn? = null,
    private val custom: BetaRbacRoleSourceCustom? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            builtIn != null -> Type.BUILT_IN
            custom != null -> Type.CUSTOM
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    fun builtIn(): Optional<BetaRbacRoleSourceBuiltIn> = Optional.ofNullable(builtIn)

    fun custom(): Optional<BetaRbacRoleSourceCustom> = Optional.ofNullable(custom)

    fun isBuiltIn(): Boolean = builtIn != null

    fun isCustom(): Boolean = custom != null

    fun asBuiltIn(): BetaRbacRoleSourceBuiltIn = builtIn.getOrThrow("builtIn")

    fun asCustom(): BetaRbacRoleSourceCustom = custom.getOrThrow("custom")

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
     * Optional<String> result = betaRbacRoleSource.accept(new BetaRbacRoleSource.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitBuiltIn(BetaRbacRoleSourceBuiltIn builtIn) {
     *         return Optional.of(builtIn.toString());
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
            builtIn != null -> visitor.visitBuiltIn(builtIn)
            custom != null -> visitor.visitCustom(custom)
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
    fun validate(): BetaRbacRoleSource = apply {
        if (validated) {
            return@apply
        }

        when {
            builtIn != null -> builtIn.validate()
            custom != null -> custom.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaRbacRoleSource: $_json")
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
            builtIn != null -> builtIn.validity()
            custom != null -> custom.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaRbacRoleSource && builtIn == other.builtIn && custom == other.custom
    }

    override fun hashCode(): Int = Objects.hash(builtIn, custom)

    override fun toString(): String =
        when {
            builtIn != null -> "BetaRbacRoleSource{builtIn=$builtIn}"
            custom != null -> "BetaRbacRoleSource{custom=$custom}"
            _json != null -> "BetaRbacRoleSource{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaRbacRoleSource")
        }

    companion object {

        @JvmStatic
        fun ofBuiltIn(builtIn: BetaRbacRoleSourceBuiltIn) = BetaRbacRoleSource(builtIn = builtIn)

        @JvmStatic
        fun ofCustom(custom: BetaRbacRoleSourceCustom) = BetaRbacRoleSource(custom = custom)
    }

    /**
     * An interface that defines how to map each variant of [BetaRbacRoleSource] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        fun visitBuiltIn(builtIn: BetaRbacRoleSourceBuiltIn): T

        fun visitCustom(custom: BetaRbacRoleSourceCustom): T

        /**
         * Maps an unknown variant of [BetaRbacRoleSource] to a value of type [T].
         *
         * An instance of [BetaRbacRoleSource] can contain an unknown variant if it was deserialized
         * from data that doesn't match any known variant. For example, if the SDK is on an older
         * version than the API, then the API may respond with new variants that the SDK is unaware
         * of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaRbacRoleSource: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<BetaRbacRoleSource>(BetaRbacRoleSource::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaRbacRoleSource {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "built_in" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaRbacRoleSourceBuiltIn>())?.let {
                        BetaRbacRoleSource(builtIn = it, _json = json)
                    } ?: BetaRbacRoleSource(_json = json)
                }
                "custom" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaRbacRoleSourceCustom>())?.let {
                        BetaRbacRoleSource(custom = it, _json = json)
                    } ?: BetaRbacRoleSource(_json = json)
                }
            }

            return BetaRbacRoleSource(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BetaRbacRoleSource>(BetaRbacRoleSource::class) {

        override fun serialize(
            value: BetaRbacRoleSource,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.builtIn != null -> generator.writeObject(value.builtIn)
                value.custom != null -> generator.writeObject(value.custom)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaRbacRoleSource")
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

            @JvmField val BUILT_IN = Type(JsonField.of("built_in"))

            @JvmField val CUSTOM = Type(JsonField.of("custom"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "built_in" -> BUILT_IN
                    "custom" -> CUSTOM
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            BUILT_IN,
            CUSTOM,
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
            BUILT_IN,
            CUSTOM,
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
                BUILT_IN -> Value.BUILT_IN
                CUSTOM -> Value.CUSTOM
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
                BUILT_IN -> Known.BUILT_IN
                CUSTOM -> Known.CUSTOM
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
