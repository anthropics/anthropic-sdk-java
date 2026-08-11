// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

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
import java.time.OffsetDateTime
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Describes what triggered a deployment run, with trigger-specific metadata. */
@JsonDeserialize(using = BetaManagedAgentsTriggerContext.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsTriggerContext.Serializer::class)
class BetaManagedAgentsTriggerContext
private constructor(
    private val schedule: BetaManagedAgentsScheduleTriggerContext? = null,
    private val manual: BetaManagedAgentsManualTriggerContext? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitSchedule(
                    schedule: BetaManagedAgentsScheduleTriggerContext
                ): Type = Type.SCHEDULE

                override fun visitManual(manual: BetaManagedAgentsManualTriggerContext): Type =
                    Type.MANUAL

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    /** The run was fired by the deployment's cron schedule. */
    fun schedule(): Optional<BetaManagedAgentsScheduleTriggerContext> =
        Optional.ofNullable(schedule)

    /** The run was started manually by creating a session directly against the deployment. */
    fun manual(): Optional<BetaManagedAgentsManualTriggerContext> = Optional.ofNullable(manual)

    fun isSchedule(): Boolean = schedule != null

    fun isManual(): Boolean = manual != null

    /** The run was fired by the deployment's cron schedule. */
    fun asSchedule(): BetaManagedAgentsScheduleTriggerContext = schedule.getOrThrow("schedule")

    /** The run was started manually by creating a session directly against the deployment. */
    fun asManual(): BetaManagedAgentsManualTriggerContext = manual.getOrThrow("manual")

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
     * Optional<String> result = betaManagedAgentsTriggerContext.accept(new BetaManagedAgentsTriggerContext.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitSchedule(BetaManagedAgentsScheduleTriggerContext schedule) {
     *         return Optional.of(schedule.toString());
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
            schedule != null -> visitor.visitSchedule(schedule)
            manual != null -> visitor.visitManual(manual)
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
    fun validate(): BetaManagedAgentsTriggerContext = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitSchedule(schedule: BetaManagedAgentsScheduleTriggerContext) {
                    schedule.validate()
                }

                override fun visitManual(manual: BetaManagedAgentsManualTriggerContext) {
                    manual.validate()
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
                override fun visitSchedule(schedule: BetaManagedAgentsScheduleTriggerContext) =
                    schedule.validity()

                override fun visitManual(manual: BetaManagedAgentsManualTriggerContext) =
                    manual.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsTriggerContext &&
            schedule == other.schedule &&
            manual == other.manual
    }

    override fun hashCode(): Int = Objects.hash(schedule, manual)

    override fun toString(): String =
        when {
            schedule != null -> "BetaManagedAgentsTriggerContext{schedule=$schedule}"
            manual != null -> "BetaManagedAgentsTriggerContext{manual=$manual}"
            _json != null -> "BetaManagedAgentsTriggerContext{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsTriggerContext")
        }

    companion object {

        /** The run was fired by the deployment's cron schedule. */
        @JvmStatic
        fun ofSchedule(schedule: BetaManagedAgentsScheduleTriggerContext) =
            BetaManagedAgentsTriggerContext(schedule = schedule)

        /**
         * Returns an immutable instance of [BetaManagedAgentsTriggerContext] whose [ofSchedule]
         * variant is built from the given required [scheduledAt].
         */
        @JvmStatic
        fun ofSchedule(scheduledAt: OffsetDateTime) =
            ofSchedule(
                BetaManagedAgentsScheduleTriggerContext.builder()
                    .type(BetaManagedAgentsScheduleTriggerContext.Type.SCHEDULE)
                    .scheduledAt(scheduledAt)
                    .build()
            )

        /** The run was started manually by creating a session directly against the deployment. */
        @JvmStatic
        fun ofManual(manual: BetaManagedAgentsManualTriggerContext) =
            BetaManagedAgentsTriggerContext(manual = manual)

        /**
         * Returns an immutable instance of [BetaManagedAgentsTriggerContext] whose [ofManual]
         * variant is built from the given required [type].
         */
        @JvmStatic
        fun ofManual(type: BetaManagedAgentsManualTriggerContext.Type) =
            ofManual(BetaManagedAgentsManualTriggerContext.of(type))
    }

    /**
     * An interface that defines how to map each variant of [BetaManagedAgentsTriggerContext] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        /** The run was fired by the deployment's cron schedule. */
        fun visitSchedule(schedule: BetaManagedAgentsScheduleTriggerContext): T

        /** The run was started manually by creating a session directly against the deployment. */
        fun visitManual(manual: BetaManagedAgentsManualTriggerContext): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsTriggerContext] to a value of type [T].
         *
         * An instance of [BetaManagedAgentsTriggerContext] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaManagedAgentsTriggerContext: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsTriggerContext>(BetaManagedAgentsTriggerContext::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaManagedAgentsTriggerContext {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "schedule" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsScheduleTriggerContext>(),
                        )
                        ?.let { BetaManagedAgentsTriggerContext(schedule = it, _json = json) }
                        ?: BetaManagedAgentsTriggerContext(_json = json)
                }
                "manual" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsManualTriggerContext>(),
                        )
                        ?.let { BetaManagedAgentsTriggerContext(manual = it, _json = json) }
                        ?: BetaManagedAgentsTriggerContext(_json = json)
                }
            }

            return BetaManagedAgentsTriggerContext(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsTriggerContext>(BetaManagedAgentsTriggerContext::class) {

        override fun serialize(
            value: BetaManagedAgentsTriggerContext,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.schedule != null -> generator.writeObject(value.schedule)
                value.manual != null -> generator.writeObject(value.manual)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaManagedAgentsTriggerContext")
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

            @JvmField val SCHEDULE = of("schedule")

            @JvmField val MANUAL = of("manual")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            SCHEDULE,
            MANUAL,
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
            SCHEDULE,
            MANUAL,
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
                SCHEDULE -> Value.SCHEDULE
                MANUAL -> Value.MANUAL
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
                SCHEDULE -> Known.SCHEDULE
                MANUAL -> Known.MANUAL
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
