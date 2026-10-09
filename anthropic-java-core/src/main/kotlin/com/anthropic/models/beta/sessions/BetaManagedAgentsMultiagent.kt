package com.anthropic.models.beta.sessions

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagent20261001
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentCoordinator
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

/** Resolved multiagent orchestration configuration as returned in API responses. */
@JsonDeserialize(using = BetaManagedAgentsMultiagent.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsMultiagent.Serializer::class)
class BetaManagedAgentsMultiagent
private constructor(
    private val coordinator: BetaManagedAgentsMultiagentCoordinator? = null,
    private val multiagent20261001: BetaManagedAgentsMultiagent20261001? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            coordinator != null -> Type.COORDINATOR
            multiagent20261001 != null -> Type.MULTIAGENT_20261001
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /** Resolved coordinator topology with a concrete agent roster. */
    fun coordinator(): Optional<BetaManagedAgentsMultiagentCoordinator> =
        Optional.ofNullable(coordinator)

    /**
     * Resolved multiagent configuration with three members, each enabled or disabled on its own.
     */
    fun multiagent20261001(): Optional<BetaManagedAgentsMultiagent20261001> =
        Optional.ofNullable(multiagent20261001)

    fun isCoordinator(): Boolean = coordinator != null

    fun isMultiagent20261001(): Boolean = multiagent20261001 != null

    /** Resolved coordinator topology with a concrete agent roster. */
    fun asCoordinator(): BetaManagedAgentsMultiagentCoordinator =
        coordinator.getOrThrow("coordinator")

    /**
     * Resolved multiagent configuration with three members, each enabled or disabled on its own.
     */
    fun asMultiagent20261001(): BetaManagedAgentsMultiagent20261001 =
        multiagent20261001.getOrThrow("multiagent20261001")

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
     * Optional<String> result = betaManagedAgentsMultiagent.accept(new BetaManagedAgentsMultiagent.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitCoordinator(BetaManagedAgentsMultiagentCoordinator coordinator) {
     *         return Optional.of(coordinator.toString());
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
            coordinator != null -> visitor.visitCoordinator(coordinator)
            multiagent20261001 != null -> visitor.visitMultiagent20261001(multiagent20261001)
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
    fun validate(): BetaManagedAgentsMultiagent = apply {
        if (validated) {
            return@apply
        }

        when {
            coordinator != null -> coordinator.validate()
            multiagent20261001 != null -> multiagent20261001.validate()
            else ->
                throw AnthropicInvalidDataException("Unknown BetaManagedAgentsMultiagent: $_json")
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
            coordinator != null -> coordinator.validity()
            multiagent20261001 != null -> multiagent20261001.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMultiagent &&
            coordinator == other.coordinator &&
            multiagent20261001 == other.multiagent20261001
    }

    override fun hashCode(): Int = Objects.hash(coordinator, multiagent20261001)

    override fun toString(): String =
        when {
            coordinator != null -> "BetaManagedAgentsMultiagent{coordinator=$coordinator}"
            multiagent20261001 != null ->
                "BetaManagedAgentsMultiagent{multiagent20261001=$multiagent20261001}"
            _json != null -> "BetaManagedAgentsMultiagent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsMultiagent")
        }

    companion object {

        /** Resolved coordinator topology with a concrete agent roster. */
        @JvmStatic
        fun ofCoordinator(coordinator: BetaManagedAgentsMultiagentCoordinator) =
            BetaManagedAgentsMultiagent(coordinator = coordinator)

        /**
         * Returns an immutable instance of [BetaManagedAgentsMultiagent] whose [ofCoordinator]
         * variant is built from the given required [agents].
         */
        @JvmStatic
        fun ofCoordinator(agents: List<BetaManagedAgentsMultiagentCoordinator.Agent>) =
            ofCoordinator(
                BetaManagedAgentsMultiagentCoordinator.builder()
                    .type(BetaManagedAgentsMultiagentCoordinator.Type.COORDINATOR)
                    .agents(agents)
                    .build()
            )

        /**
         * Resolved multiagent configuration with three members, each enabled or disabled on its
         * own.
         */
        @JvmStatic
        fun ofMultiagent20261001(multiagent20261001: BetaManagedAgentsMultiagent20261001) =
            BetaManagedAgentsMultiagent(multiagent20261001 = multiagent20261001)
    }

    /**
     * An interface that defines how to map each variant of [BetaManagedAgentsMultiagent] to a value
     * of type [T].
     */
    interface Visitor<out T> {

        /** Resolved coordinator topology with a concrete agent roster. */
        fun visitCoordinator(coordinator: BetaManagedAgentsMultiagentCoordinator): T

        /**
         * Resolved multiagent configuration with three members, each enabled or disabled on its
         * own.
         */
        fun visitMultiagent20261001(multiagent20261001: BetaManagedAgentsMultiagent20261001): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsMultiagent] to a value of type [T].
         *
         * An instance of [BetaManagedAgentsMultiagent] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaManagedAgentsMultiagent: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsMultiagent>(BetaManagedAgentsMultiagent::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaManagedAgentsMultiagent {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "coordinator" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsMultiagentCoordinator>(),
                        )
                        ?.let { BetaManagedAgentsMultiagent(coordinator = it, _json = json) }
                        ?: BetaManagedAgentsMultiagent(_json = json)
                }
                "multiagent_20261001" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsMultiagent20261001>(),
                        )
                        ?.let { BetaManagedAgentsMultiagent(multiagent20261001 = it, _json = json) }
                        ?: BetaManagedAgentsMultiagent(_json = json)
                }
            }

            return BetaManagedAgentsMultiagent(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsMultiagent>(BetaManagedAgentsMultiagent::class) {

        override fun serialize(
            value: BetaManagedAgentsMultiagent,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.coordinator != null -> generator.writeObject(value.coordinator)
                value.multiagent20261001 != null -> generator.writeObject(value.multiagent20261001)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaManagedAgentsMultiagent")
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

            @JvmField val COORDINATOR = Type(JsonField.of("coordinator"))

            @JvmField val MULTIAGENT_20261001 = Type(JsonField.of("multiagent_20261001"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "coordinator" -> COORDINATOR
                    "multiagent_20261001" -> MULTIAGENT_20261001
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            COORDINATOR,
            MULTIAGENT_20261001,
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
            COORDINATOR,
            MULTIAGENT_20261001,
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
                COORDINATOR -> Value.COORDINATOR
                MULTIAGENT_20261001 -> Value.MULTIAGENT_20261001
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
                COORDINATOR -> Known.COORDINATOR
                MULTIAGENT_20261001 -> Known.MULTIAGENT_20261001
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
