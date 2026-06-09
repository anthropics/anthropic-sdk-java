// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
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

/** Describes what triggered a deployment run, with trigger-specific metadata. */
@JsonDeserialize(using = BetaManagedAgentsTriggerContext.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsTriggerContext.Serializer::class)
class BetaManagedAgentsTriggerContext
private constructor(
    private val schedule: BetaManagedAgentsScheduleTriggerContext? = null,
    private val manual: BetaManagedAgentsManualTriggerContext? = null,
    private val _json: JsonValue? = null,
) {

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

        /** The run was started manually by creating a session directly against the deployment. */
        @JvmStatic
        fun ofManual(manual: BetaManagedAgentsManualTriggerContext) =
            BetaManagedAgentsTriggerContext(manual = manual)
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
}
