package com.anthropic.models.beta.agents

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
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

/**
 * Whether URLs in the text of user messages may be fetched. Accepts the string "all" or "none", or
 * the object {"type": "all"} or {"type": "none"}. Responses use the object form.
 */
@JsonDeserialize(using = BetaManagedAgentsWebFetchUrlSourceUserInputParams.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsWebFetchUrlSourceUserInputParams.Serializer::class)
class BetaManagedAgentsWebFetchUrlSourceUserInputParams
private constructor(
    private val shorthand: BetaManagedAgentsWebFetchUrlSourceShorthand? = null,
    private val betaManagedAgentsWebFetchUrlSourceUserInput:
        BetaManagedAgentsWebFetchUrlSourceUserInput? =
        null,
    private val _json: JsonValue? = null,
) {

    /**
     * String form of a url_sources value that has no field other than its type: "all" means
     * {"type": "all"} and "none" means {"type": "none"}.
     */
    fun shorthand(): Optional<BetaManagedAgentsWebFetchUrlSourceShorthand> =
        Optional.ofNullable(shorthand)

    /** Whether URLs in the text of user messages may be fetched. */
    fun betaManagedAgentsWebFetchUrlSourceUserInput():
        Optional<BetaManagedAgentsWebFetchUrlSourceUserInput> =
        Optional.ofNullable(betaManagedAgentsWebFetchUrlSourceUserInput)

    fun isShorthand(): Boolean = shorthand != null

    fun isBetaManagedAgentsWebFetchUrlSourceUserInput(): Boolean =
        betaManagedAgentsWebFetchUrlSourceUserInput != null

    /**
     * String form of a url_sources value that has no field other than its type: "all" means
     * {"type": "all"} and "none" means {"type": "none"}.
     */
    fun asShorthand(): BetaManagedAgentsWebFetchUrlSourceShorthand =
        shorthand.getOrThrow("shorthand")

    /** Whether URLs in the text of user messages may be fetched. */
    fun asBetaManagedAgentsWebFetchUrlSourceUserInput():
        BetaManagedAgentsWebFetchUrlSourceUserInput =
        betaManagedAgentsWebFetchUrlSourceUserInput.getOrThrow(
            "betaManagedAgentsWebFetchUrlSourceUserInput"
        )

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
     * Optional<String> result = betaManagedAgentsWebFetchUrlSourceUserInputParams.accept(new BetaManagedAgentsWebFetchUrlSourceUserInputParams.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitShorthand(BetaManagedAgentsWebFetchUrlSourceShorthand shorthand) {
     *         return Optional.of(shorthand.toString());
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
            shorthand != null -> visitor.visitShorthand(shorthand)
            betaManagedAgentsWebFetchUrlSourceUserInput != null ->
                visitor.visitBetaManagedAgentsWebFetchUrlSourceUserInput(
                    betaManagedAgentsWebFetchUrlSourceUserInput
                )
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
    fun validate(): BetaManagedAgentsWebFetchUrlSourceUserInputParams = apply {
        if (validated) {
            return@apply
        }

        when {
            shorthand != null -> shorthand.validate()
            betaManagedAgentsWebFetchUrlSourceUserInput != null ->
                betaManagedAgentsWebFetchUrlSourceUserInput.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaManagedAgentsWebFetchUrlSourceUserInputParams: $_json"
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
            shorthand != null -> shorthand.validity()
            betaManagedAgentsWebFetchUrlSourceUserInput != null ->
                betaManagedAgentsWebFetchUrlSourceUserInput.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsWebFetchUrlSourceUserInputParams &&
            shorthand == other.shorthand &&
            betaManagedAgentsWebFetchUrlSourceUserInput ==
                other.betaManagedAgentsWebFetchUrlSourceUserInput
    }

    override fun hashCode(): Int =
        Objects.hash(shorthand, betaManagedAgentsWebFetchUrlSourceUserInput)

    override fun toString(): String =
        when {
            shorthand != null ->
                "BetaManagedAgentsWebFetchUrlSourceUserInputParams{shorthand=$shorthand}"
            betaManagedAgentsWebFetchUrlSourceUserInput != null ->
                "BetaManagedAgentsWebFetchUrlSourceUserInputParams{betaManagedAgentsWebFetchUrlSourceUserInput=$betaManagedAgentsWebFetchUrlSourceUserInput}"
            _json != null -> "BetaManagedAgentsWebFetchUrlSourceUserInputParams{_unknown=$_json}"
            else ->
                throw IllegalStateException(
                    "Invalid BetaManagedAgentsWebFetchUrlSourceUserInputParams"
                )
        }

    companion object {

        /**
         * String form of a url_sources value that has no field other than its type: "all" means
         * {"type": "all"} and "none" means {"type": "none"}.
         */
        @JvmStatic
        fun ofShorthand(shorthand: BetaManagedAgentsWebFetchUrlSourceShorthand) =
            BetaManagedAgentsWebFetchUrlSourceUserInputParams(shorthand = shorthand)

        /** Whether URLs in the text of user messages may be fetched. */
        @JvmStatic
        fun ofBetaManagedAgentsWebFetchUrlSourceUserInput(
            betaManagedAgentsWebFetchUrlSourceUserInput: BetaManagedAgentsWebFetchUrlSourceUserInput
        ) =
            BetaManagedAgentsWebFetchUrlSourceUserInputParams(
                betaManagedAgentsWebFetchUrlSourceUserInput =
                    betaManagedAgentsWebFetchUrlSourceUserInput
            )
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaManagedAgentsWebFetchUrlSourceUserInputParams] to a value of type [T].
     */
    interface Visitor<out T> {

        /**
         * String form of a url_sources value that has no field other than its type: "all" means
         * {"type": "all"} and "none" means {"type": "none"}.
         */
        fun visitShorthand(shorthand: BetaManagedAgentsWebFetchUrlSourceShorthand): T

        /** Whether URLs in the text of user messages may be fetched. */
        fun visitBetaManagedAgentsWebFetchUrlSourceUserInput(
            betaManagedAgentsWebFetchUrlSourceUserInput: BetaManagedAgentsWebFetchUrlSourceUserInput
        ): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsWebFetchUrlSourceUserInputParams] to a value
         * of type [T].
         *
         * An instance of [BetaManagedAgentsWebFetchUrlSourceUserInputParams] can contain an unknown
         * variant if it was deserialized from data that doesn't match any known variant. For
         * example, if the SDK is on an older version than the API, then the API may respond with
         * new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsWebFetchUrlSourceUserInputParams: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsWebFetchUrlSourceUserInputParams>(
            BetaManagedAgentsWebFetchUrlSourceUserInputParams::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaManagedAgentsWebFetchUrlSourceUserInputParams {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceShorthand>(),
                            )
                            ?.let {
                                BetaManagedAgentsWebFetchUrlSourceUserInputParams(
                                    shorthand = it,
                                    _json = json,
                                )
                            },
                        tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceUserInput>(),
                            )
                            ?.let {
                                BetaManagedAgentsWebFetchUrlSourceUserInputParams(
                                    betaManagedAgentsWebFetchUrlSourceUserInput = it,
                                    _json = json,
                                )
                            },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> BetaManagedAgentsWebFetchUrlSourceUserInputParams(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsWebFetchUrlSourceUserInputParams>(
            BetaManagedAgentsWebFetchUrlSourceUserInputParams::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsWebFetchUrlSourceUserInputParams,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.shorthand != null -> generator.writeObject(value.shorthand)
                value.betaManagedAgentsWebFetchUrlSourceUserInput != null ->
                    generator.writeObject(value.betaManagedAgentsWebFetchUrlSourceUserInput)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException(
                        "Invalid BetaManagedAgentsWebFetchUrlSourceUserInputParams"
                    )
            }
        }
    }
}
