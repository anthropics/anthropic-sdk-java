package com.anthropic.models.beta.sessions

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagent20261001Params
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentCoordinatorParams
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

/** Multiagent orchestration configuration. */
@JsonDeserialize(using = BetaManagedAgentsMultiagentParams.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsMultiagentParams.Serializer::class)
class BetaManagedAgentsMultiagentParams
private constructor(
    private val coordinator: BetaManagedAgentsMultiagentCoordinatorParams? = null,
    private val multiagent20261001: BetaManagedAgentsMultiagent20261001Params? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            coordinator != null -> Type.COORDINATOR
            multiagent20261001 != null -> Type.MULTIAGENT_20261001
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /**
     * A coordinator topology: the session's primary thread orchestrates work by spawning session
     * threads, each running an agent drawn from the `agents` roster.
     */
    fun coordinator(): Optional<BetaManagedAgentsMultiagentCoordinatorParams> =
        Optional.ofNullable(coordinator)

    /**
     * Multiagent configuration with three members, each enabled or disabled on its own. On an
     * update, if the agent's stored `multiagent` also has type `multiagent_20261001`, this
     * configuration is merged into the stored one, level by level, instead of replacing it. A key
     * that the update omits keeps its stored value. A key sent as null takes its default, on create
     * as well, so `"workflows": null` enables workflows. An object sent with a `type` other than
     * the stored one replaces the stored object, and the keys that it omits take their defaults. A
     * `predefined_agents` list that is sent replaces the stored list. Every object that is sent
     * needs its `type`, and an enabled `advisor` needs its `model`. Other validation applies to the
     * merged result.
     */
    fun multiagent20261001(): Optional<BetaManagedAgentsMultiagent20261001Params> =
        Optional.ofNullable(multiagent20261001)

    fun isCoordinator(): Boolean = coordinator != null

    fun isMultiagent20261001(): Boolean = multiagent20261001 != null

    /**
     * A coordinator topology: the session's primary thread orchestrates work by spawning session
     * threads, each running an agent drawn from the `agents` roster.
     */
    fun asCoordinator(): BetaManagedAgentsMultiagentCoordinatorParams =
        coordinator.getOrThrow("coordinator")

    /**
     * Multiagent configuration with three members, each enabled or disabled on its own. On an
     * update, if the agent's stored `multiagent` also has type `multiagent_20261001`, this
     * configuration is merged into the stored one, level by level, instead of replacing it. A key
     * that the update omits keeps its stored value. A key sent as null takes its default, on create
     * as well, so `"workflows": null` enables workflows. An object sent with a `type` other than
     * the stored one replaces the stored object, and the keys that it omits take their defaults. A
     * `predefined_agents` list that is sent replaces the stored list. Every object that is sent
     * needs its `type`, and an enabled `advisor` needs its `model`. Other validation applies to the
     * merged result.
     */
    fun asMultiagent20261001(): BetaManagedAgentsMultiagent20261001Params =
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
     * Optional<String> result = betaManagedAgentsMultiagentParams.accept(new BetaManagedAgentsMultiagentParams.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitCoordinator(BetaManagedAgentsMultiagentCoordinatorParams coordinator) {
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
    fun validate(): BetaManagedAgentsMultiagentParams = apply {
        if (validated) {
            return@apply
        }

        when {
            coordinator != null -> coordinator.validate()
            multiagent20261001 != null -> multiagent20261001.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaManagedAgentsMultiagentParams: $_json"
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
            coordinator != null -> coordinator.validity()
            multiagent20261001 != null -> multiagent20261001.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMultiagentParams &&
            coordinator == other.coordinator &&
            multiagent20261001 == other.multiagent20261001
    }

    override fun hashCode(): Int = Objects.hash(coordinator, multiagent20261001)

    override fun toString(): String =
        when {
            coordinator != null -> "BetaManagedAgentsMultiagentParams{coordinator=$coordinator}"
            multiagent20261001 != null ->
                "BetaManagedAgentsMultiagentParams{multiagent20261001=$multiagent20261001}"
            _json != null -> "BetaManagedAgentsMultiagentParams{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsMultiagentParams")
        }

    companion object {

        /**
         * A coordinator topology: the session's primary thread orchestrates work by spawning
         * session threads, each running an agent drawn from the `agents` roster.
         */
        @JvmStatic
        fun ofCoordinator(coordinator: BetaManagedAgentsMultiagentCoordinatorParams) =
            BetaManagedAgentsMultiagentParams(coordinator = coordinator)

        /**
         * Returns an immutable instance of [BetaManagedAgentsMultiagentParams] whose
         * [ofCoordinator] variant is built from the given required [agents].
         */
        @JvmStatic
        fun ofCoordinator(agents: List<BetaManagedAgentsMultiagentRosterEntryParams>) =
            ofCoordinator(
                BetaManagedAgentsMultiagentCoordinatorParams.builder()
                    .type(BetaManagedAgentsMultiagentCoordinatorParams.Type.COORDINATOR)
                    .agents(agents)
                    .build()
            )

        /**
         * Multiagent configuration with three members, each enabled or disabled on its own. On an
         * update, if the agent's stored `multiagent` also has type `multiagent_20261001`, this
         * configuration is merged into the stored one, level by level, instead of replacing it. A
         * key that the update omits keeps its stored value. A key sent as null takes its default,
         * on create as well, so `"workflows": null` enables workflows. An object sent with a `type`
         * other than the stored one replaces the stored object, and the keys that it omits take
         * their defaults. A `predefined_agents` list that is sent replaces the stored list. Every
         * object that is sent needs its `type`, and an enabled `advisor` needs its `model`. Other
         * validation applies to the merged result.
         */
        @JvmStatic
        fun ofMultiagent20261001(multiagent20261001: BetaManagedAgentsMultiagent20261001Params) =
            BetaManagedAgentsMultiagentParams(multiagent20261001 = multiagent20261001)
    }

    /**
     * An interface that defines how to map each variant of [BetaManagedAgentsMultiagentParams] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        /**
         * A coordinator topology: the session's primary thread orchestrates work by spawning
         * session threads, each running an agent drawn from the `agents` roster.
         */
        fun visitCoordinator(coordinator: BetaManagedAgentsMultiagentCoordinatorParams): T

        /**
         * Multiagent configuration with three members, each enabled or disabled on its own. On an
         * update, if the agent's stored `multiagent` also has type `multiagent_20261001`, this
         * configuration is merged into the stored one, level by level, instead of replacing it. A
         * key that the update omits keeps its stored value. A key sent as null takes its default,
         * on create as well, so `"workflows": null` enables workflows. An object sent with a `type`
         * other than the stored one replaces the stored object, and the keys that it omits take
         * their defaults. A `predefined_agents` list that is sent replaces the stored list. Every
         * object that is sent needs its `type`, and an enabled `advisor` needs its `model`. Other
         * validation applies to the merged result.
         */
        fun visitMultiagent20261001(
            multiagent20261001: BetaManagedAgentsMultiagent20261001Params
        ): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsMultiagentParams] to a value of type [T].
         *
         * An instance of [BetaManagedAgentsMultiagentParams] can contain an unknown variant if it
         * was deserialized from data that doesn't match any known variant. For example, if the SDK
         * is on an older version than the API, then the API may respond with new variants that the
         * SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaManagedAgentsMultiagentParams: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsMultiagentParams>(
            BetaManagedAgentsMultiagentParams::class
        ) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaManagedAgentsMultiagentParams {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "coordinator" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsMultiagentCoordinatorParams>(),
                        )
                        ?.let { BetaManagedAgentsMultiagentParams(coordinator = it, _json = json) }
                        ?: BetaManagedAgentsMultiagentParams(_json = json)
                }
                "multiagent_20261001" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsMultiagent20261001Params>(),
                        )
                        ?.let {
                            BetaManagedAgentsMultiagentParams(multiagent20261001 = it, _json = json)
                        } ?: BetaManagedAgentsMultiagentParams(_json = json)
                }
            }

            return BetaManagedAgentsMultiagentParams(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsMultiagentParams>(
            BetaManagedAgentsMultiagentParams::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsMultiagentParams,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.coordinator != null -> generator.writeObject(value.coordinator)
                value.multiagent20261001 != null -> generator.writeObject(value.multiagent20261001)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaManagedAgentsMultiagentParams")
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
