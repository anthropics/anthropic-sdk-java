package com.anthropic.models.beta.sessions.events

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
 * Names the resolved permission_policy that produced evaluated_permission, and under auto carries
 * the judgement. Open union: clients must tolerate unknown variants.
 */
@JsonDeserialize(using = BetaManagedAgentsAgentToolEvaluation.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsAgentToolEvaluation.Serializer::class)
class BetaManagedAgentsAgentToolEvaluation
private constructor(
    private val alwaysAllow: BetaManagedAgentsAgentToolEvaluationAlwaysAllow? = null,
    private val alwaysAsk: BetaManagedAgentsAgentToolEvaluationAlwaysAsk? = null,
    private val auto: BetaManagedAgentsAgentToolEvaluationAuto? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitAlwaysAllow(
                    alwaysAllow: BetaManagedAgentsAgentToolEvaluationAlwaysAllow
                ): Type = Type.ALWAYS_ALLOW

                override fun visitAlwaysAsk(
                    alwaysAsk: BetaManagedAgentsAgentToolEvaluationAlwaysAsk
                ): Type = Type.ALWAYS_ASK

                override fun visitAuto(auto: BetaManagedAgentsAgentToolEvaluationAuto): Type =
                    Type.AUTO

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    /**
     * The resolved permission_policy was always_allow; accompanies evaluated_permission "allow".
     */
    fun alwaysAllow(): Optional<BetaManagedAgentsAgentToolEvaluationAlwaysAllow> =
        Optional.ofNullable(alwaysAllow)

    /** The resolved permission_policy was always_ask; accompanies evaluated_permission "ask". */
    fun alwaysAsk(): Optional<BetaManagedAgentsAgentToolEvaluationAlwaysAsk> =
        Optional.ofNullable(alwaysAsk)

    /** The resolved permission_policy was auto: the server judged this invocation individually. */
    fun auto(): Optional<BetaManagedAgentsAgentToolEvaluationAuto> = Optional.ofNullable(auto)

    fun isAlwaysAllow(): Boolean = alwaysAllow != null

    fun isAlwaysAsk(): Boolean = alwaysAsk != null

    fun isAuto(): Boolean = auto != null

    /**
     * The resolved permission_policy was always_allow; accompanies evaluated_permission "allow".
     */
    fun asAlwaysAllow(): BetaManagedAgentsAgentToolEvaluationAlwaysAllow =
        alwaysAllow.getOrThrow("alwaysAllow")

    /** The resolved permission_policy was always_ask; accompanies evaluated_permission "ask". */
    fun asAlwaysAsk(): BetaManagedAgentsAgentToolEvaluationAlwaysAsk =
        alwaysAsk.getOrThrow("alwaysAsk")

    /** The resolved permission_policy was auto: the server judged this invocation individually. */
    fun asAuto(): BetaManagedAgentsAgentToolEvaluationAuto = auto.getOrThrow("auto")

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
     * Optional<String> result = betaManagedAgentsAgentToolEvaluation.accept(new BetaManagedAgentsAgentToolEvaluation.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitAlwaysAllow(BetaManagedAgentsAgentToolEvaluationAlwaysAllow alwaysAllow) {
     *         return Optional.of(alwaysAllow.toString());
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
            alwaysAllow != null -> visitor.visitAlwaysAllow(alwaysAllow)
            alwaysAsk != null -> visitor.visitAlwaysAsk(alwaysAsk)
            auto != null -> visitor.visitAuto(auto)
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
    fun validate(): BetaManagedAgentsAgentToolEvaluation = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitAlwaysAllow(
                    alwaysAllow: BetaManagedAgentsAgentToolEvaluationAlwaysAllow
                ) {
                    alwaysAllow.validate()
                }

                override fun visitAlwaysAsk(
                    alwaysAsk: BetaManagedAgentsAgentToolEvaluationAlwaysAsk
                ) {
                    alwaysAsk.validate()
                }

                override fun visitAuto(auto: BetaManagedAgentsAgentToolEvaluationAuto) {
                    auto.validate()
                }
            }
        )
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
        accept(
            object : Visitor<Int> {
                override fun visitAlwaysAllow(
                    alwaysAllow: BetaManagedAgentsAgentToolEvaluationAlwaysAllow
                ) = alwaysAllow.validity()

                override fun visitAlwaysAsk(
                    alwaysAsk: BetaManagedAgentsAgentToolEvaluationAlwaysAsk
                ) = alwaysAsk.validity()

                override fun visitAuto(auto: BetaManagedAgentsAgentToolEvaluationAuto) =
                    auto.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsAgentToolEvaluation &&
            alwaysAllow == other.alwaysAllow &&
            alwaysAsk == other.alwaysAsk &&
            auto == other.auto
    }

    override fun hashCode(): Int = Objects.hash(alwaysAllow, alwaysAsk, auto)

    override fun toString(): String =
        when {
            alwaysAllow != null -> "BetaManagedAgentsAgentToolEvaluation{alwaysAllow=$alwaysAllow}"
            alwaysAsk != null -> "BetaManagedAgentsAgentToolEvaluation{alwaysAsk=$alwaysAsk}"
            auto != null -> "BetaManagedAgentsAgentToolEvaluation{auto=$auto}"
            _json != null -> "BetaManagedAgentsAgentToolEvaluation{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsAgentToolEvaluation")
        }

    companion object {

        /**
         * The resolved permission_policy was always_allow; accompanies evaluated_permission
         * "allow".
         */
        @JvmStatic
        fun ofAlwaysAllow(alwaysAllow: BetaManagedAgentsAgentToolEvaluationAlwaysAllow) =
            BetaManagedAgentsAgentToolEvaluation(alwaysAllow = alwaysAllow)

        /**
         * The resolved permission_policy was always_ask; accompanies evaluated_permission "ask".
         */
        @JvmStatic
        fun ofAlwaysAsk(alwaysAsk: BetaManagedAgentsAgentToolEvaluationAlwaysAsk) =
            BetaManagedAgentsAgentToolEvaluation(alwaysAsk = alwaysAsk)

        /**
         * The resolved permission_policy was auto: the server judged this invocation individually.
         */
        @JvmStatic
        fun ofAuto(auto: BetaManagedAgentsAgentToolEvaluationAuto) =
            BetaManagedAgentsAgentToolEvaluation(auto = auto)

        /**
         * Returns an immutable instance of [BetaManagedAgentsAgentToolEvaluation] whose [ofAuto]
         * variant is built from the given required [evaluatedPermission].
         */
        @JvmStatic
        fun ofAuto(evaluatedPermission: BetaManagedAgentsAgentAutoEvaluatedPermission) =
            ofAuto(BetaManagedAgentsAgentToolEvaluationAuto.of(evaluatedPermission))
    }

    /**
     * An interface that defines how to map each variant of [BetaManagedAgentsAgentToolEvaluation]
     * to a value of type [T].
     */
    interface Visitor<out T> {

        /**
         * The resolved permission_policy was always_allow; accompanies evaluated_permission
         * "allow".
         */
        fun visitAlwaysAllow(alwaysAllow: BetaManagedAgentsAgentToolEvaluationAlwaysAllow): T

        /**
         * The resolved permission_policy was always_ask; accompanies evaluated_permission "ask".
         */
        fun visitAlwaysAsk(alwaysAsk: BetaManagedAgentsAgentToolEvaluationAlwaysAsk): T

        /**
         * The resolved permission_policy was auto: the server judged this invocation individually.
         */
        fun visitAuto(auto: BetaManagedAgentsAgentToolEvaluationAuto): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsAgentToolEvaluation] to a value of type [T].
         *
         * An instance of [BetaManagedAgentsAgentToolEvaluation] can contain an unknown variant if
         * it was deserialized from data that doesn't match any known variant. For example, if the
         * SDK is on an older version than the API, then the API may respond with new variants that
         * the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsAgentToolEvaluation: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsAgentToolEvaluation>(
            BetaManagedAgentsAgentToolEvaluation::class
        ) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaManagedAgentsAgentToolEvaluation {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "always_allow" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentToolEvaluationAlwaysAllow>(),
                        )
                        ?.let {
                            BetaManagedAgentsAgentToolEvaluation(alwaysAllow = it, _json = json)
                        } ?: BetaManagedAgentsAgentToolEvaluation(_json = json)
                }
                "always_ask" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentToolEvaluationAlwaysAsk>(),
                        )
                        ?.let { BetaManagedAgentsAgentToolEvaluation(alwaysAsk = it, _json = json) }
                        ?: BetaManagedAgentsAgentToolEvaluation(_json = json)
                }
                "auto" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAgentToolEvaluationAuto>(),
                        )
                        ?.let { BetaManagedAgentsAgentToolEvaluation(auto = it, _json = json) }
                        ?: BetaManagedAgentsAgentToolEvaluation(_json = json)
                }
            }

            return BetaManagedAgentsAgentToolEvaluation(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsAgentToolEvaluation>(
            BetaManagedAgentsAgentToolEvaluation::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsAgentToolEvaluation,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.alwaysAllow != null -> generator.writeObject(value.alwaysAllow)
                value.alwaysAsk != null -> generator.writeObject(value.alwaysAsk)
                value.auto != null -> generator.writeObject(value.auto)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaManagedAgentsAgentToolEvaluation")
            }
        }
    }

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val ALWAYS_ALLOW = of("always_allow")

            @JvmField val ALWAYS_ASK = of("always_ask")

            @JvmField val AUTO = of("auto")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            ALWAYS_ALLOW,
            ALWAYS_ASK,
            AUTO,
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
            ALWAYS_ALLOW,
            ALWAYS_ASK,
            AUTO,
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
                ALWAYS_ALLOW -> Value.ALWAYS_ALLOW
                ALWAYS_ASK -> Value.ALWAYS_ASK
                AUTO -> Value.AUTO
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
                ALWAYS_ALLOW -> Known.ALWAYS_ALLOW
                ALWAYS_ASK -> Known.ALWAYS_ASK
                AUTO -> Known.AUTO
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
