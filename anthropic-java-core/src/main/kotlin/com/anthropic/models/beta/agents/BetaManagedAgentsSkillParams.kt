// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

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

/** Skill to load in the session container. */
@JsonDeserialize(using = BetaManagedAgentsSkillParams.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsSkillParams.Serializer::class)
class BetaManagedAgentsSkillParams
private constructor(
    private val anthropic: BetaManagedAgentsAnthropicSkillParams? = null,
    private val custom: BetaManagedAgentsCustomSkillParams? = null,
    private val _json: JsonValue? = null,
) {

    /** An Anthropic-managed skill. */
    fun anthropic(): Optional<BetaManagedAgentsAnthropicSkillParams> =
        Optional.ofNullable(anthropic)

    /** A user-created custom skill. */
    fun custom(): Optional<BetaManagedAgentsCustomSkillParams> = Optional.ofNullable(custom)

    fun isAnthropic(): Boolean = anthropic != null

    fun isCustom(): Boolean = custom != null

    /** An Anthropic-managed skill. */
    fun asAnthropic(): BetaManagedAgentsAnthropicSkillParams = anthropic.getOrThrow("anthropic")

    /** A user-created custom skill. */
    fun asCustom(): BetaManagedAgentsCustomSkillParams = custom.getOrThrow("custom")

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
     * Optional<String> result = betaManagedAgentsSkillParams.accept(new BetaManagedAgentsSkillParams.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitAnthropic(BetaManagedAgentsAnthropicSkillParams anthropic) {
     *         return Optional.of(anthropic.toString());
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
            anthropic != null -> visitor.visitAnthropic(anthropic)
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
    fun validate(): BetaManagedAgentsSkillParams = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitAnthropic(anthropic: BetaManagedAgentsAnthropicSkillParams) {
                    anthropic.validate()
                }

                override fun visitCustom(custom: BetaManagedAgentsCustomSkillParams) {
                    custom.validate()
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
                override fun visitAnthropic(anthropic: BetaManagedAgentsAnthropicSkillParams) =
                    anthropic.validity()

                override fun visitCustom(custom: BetaManagedAgentsCustomSkillParams) =
                    custom.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsSkillParams &&
            anthropic == other.anthropic &&
            custom == other.custom
    }

    override fun hashCode(): Int = Objects.hash(anthropic, custom)

    override fun toString(): String =
        when {
            anthropic != null -> "BetaManagedAgentsSkillParams{anthropic=$anthropic}"
            custom != null -> "BetaManagedAgentsSkillParams{custom=$custom}"
            _json != null -> "BetaManagedAgentsSkillParams{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsSkillParams")
        }

    companion object {

        /** An Anthropic-managed skill. */
        @JvmStatic
        fun ofAnthropic(anthropic: BetaManagedAgentsAnthropicSkillParams) =
            BetaManagedAgentsSkillParams(anthropic = anthropic)

        /** A user-created custom skill. */
        @JvmStatic
        fun ofCustom(custom: BetaManagedAgentsCustomSkillParams) =
            BetaManagedAgentsSkillParams(custom = custom)
    }

    /**
     * An interface that defines how to map each variant of [BetaManagedAgentsSkillParams] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        /** An Anthropic-managed skill. */
        fun visitAnthropic(anthropic: BetaManagedAgentsAnthropicSkillParams): T

        /** A user-created custom skill. */
        fun visitCustom(custom: BetaManagedAgentsCustomSkillParams): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsSkillParams] to a value of type [T].
         *
         * An instance of [BetaManagedAgentsSkillParams] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaManagedAgentsSkillParams: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsSkillParams>(BetaManagedAgentsSkillParams::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaManagedAgentsSkillParams {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "anthropic" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAnthropicSkillParams>(),
                        )
                        ?.let { BetaManagedAgentsSkillParams(anthropic = it, _json = json) }
                        ?: BetaManagedAgentsSkillParams(_json = json)
                }
                "custom" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsCustomSkillParams>(),
                        )
                        ?.let { BetaManagedAgentsSkillParams(custom = it, _json = json) }
                        ?: BetaManagedAgentsSkillParams(_json = json)
                }
            }

            return BetaManagedAgentsSkillParams(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsSkillParams>(BetaManagedAgentsSkillParams::class) {

        override fun serialize(
            value: BetaManagedAgentsSkillParams,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.anthropic != null -> generator.writeObject(value.anthropic)
                value.custom != null -> generator.writeObject(value.custom)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaManagedAgentsSkillParams")
            }
        }
    }
}
