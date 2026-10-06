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
 * Which tools' results contribute URLs that may be fetched. Accepts the string "all" or "none", or
 * an object whose type is "all", "none", "only" or "except". Responses use the object form.
 */
@JsonDeserialize(using = BetaManagedAgentsWebFetchUrlSourceToolFilterParams.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsWebFetchUrlSourceToolFilterParams.Serializer::class)
class BetaManagedAgentsWebFetchUrlSourceToolFilterParams
private constructor(
    private val shorthand: BetaManagedAgentsWebFetchUrlSourceShorthand? = null,
    private val betaManagedAgentsWebFetchUrlSourceToolFilter:
        BetaManagedAgentsWebFetchUrlSourceToolFilter? =
        null,
    private val _json: JsonValue? = null,
) {

    /**
     * String form of a url_sources value that has no field other than its type: "all" means
     * {"type": "all"} and "none" means {"type": "none"}.
     */
    fun shorthand(): Optional<BetaManagedAgentsWebFetchUrlSourceShorthand> =
        Optional.ofNullable(shorthand)

    /** Which tools' results contribute URLs that may be fetched. */
    fun betaManagedAgentsWebFetchUrlSourceToolFilter():
        Optional<BetaManagedAgentsWebFetchUrlSourceToolFilter> =
        Optional.ofNullable(betaManagedAgentsWebFetchUrlSourceToolFilter)

    fun isShorthand(): Boolean = shorthand != null

    fun isBetaManagedAgentsWebFetchUrlSourceToolFilter(): Boolean =
        betaManagedAgentsWebFetchUrlSourceToolFilter != null

    /**
     * String form of a url_sources value that has no field other than its type: "all" means
     * {"type": "all"} and "none" means {"type": "none"}.
     */
    fun asShorthand(): BetaManagedAgentsWebFetchUrlSourceShorthand =
        shorthand.getOrThrow("shorthand")

    /** Which tools' results contribute URLs that may be fetched. */
    fun asBetaManagedAgentsWebFetchUrlSourceToolFilter():
        BetaManagedAgentsWebFetchUrlSourceToolFilter =
        betaManagedAgentsWebFetchUrlSourceToolFilter.getOrThrow(
            "betaManagedAgentsWebFetchUrlSourceToolFilter"
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
     * Optional<String> result = betaManagedAgentsWebFetchUrlSourceToolFilterParams.accept(new BetaManagedAgentsWebFetchUrlSourceToolFilterParams.Visitor<Optional<String>>() {
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
            betaManagedAgentsWebFetchUrlSourceToolFilter != null ->
                visitor.visitBetaManagedAgentsWebFetchUrlSourceToolFilter(
                    betaManagedAgentsWebFetchUrlSourceToolFilter
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
    fun validate(): BetaManagedAgentsWebFetchUrlSourceToolFilterParams = apply {
        if (validated) {
            return@apply
        }

        when {
            shorthand != null -> shorthand.validate()
            betaManagedAgentsWebFetchUrlSourceToolFilter != null ->
                betaManagedAgentsWebFetchUrlSourceToolFilter.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaManagedAgentsWebFetchUrlSourceToolFilterParams: $_json"
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
            betaManagedAgentsWebFetchUrlSourceToolFilter != null ->
                betaManagedAgentsWebFetchUrlSourceToolFilter.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsWebFetchUrlSourceToolFilterParams &&
            shorthand == other.shorthand &&
            betaManagedAgentsWebFetchUrlSourceToolFilter ==
                other.betaManagedAgentsWebFetchUrlSourceToolFilter
    }

    override fun hashCode(): Int =
        Objects.hash(shorthand, betaManagedAgentsWebFetchUrlSourceToolFilter)

    override fun toString(): String =
        when {
            shorthand != null ->
                "BetaManagedAgentsWebFetchUrlSourceToolFilterParams{shorthand=$shorthand}"
            betaManagedAgentsWebFetchUrlSourceToolFilter != null ->
                "BetaManagedAgentsWebFetchUrlSourceToolFilterParams{betaManagedAgentsWebFetchUrlSourceToolFilter=$betaManagedAgentsWebFetchUrlSourceToolFilter}"
            _json != null -> "BetaManagedAgentsWebFetchUrlSourceToolFilterParams{_unknown=$_json}"
            else ->
                throw IllegalStateException(
                    "Invalid BetaManagedAgentsWebFetchUrlSourceToolFilterParams"
                )
        }

    companion object {

        /**
         * String form of a url_sources value that has no field other than its type: "all" means
         * {"type": "all"} and "none" means {"type": "none"}.
         */
        @JvmStatic
        fun ofShorthand(shorthand: BetaManagedAgentsWebFetchUrlSourceShorthand) =
            BetaManagedAgentsWebFetchUrlSourceToolFilterParams(shorthand = shorthand)

        /** Which tools' results contribute URLs that may be fetched. */
        @JvmStatic
        fun ofBetaManagedAgentsWebFetchUrlSourceToolFilter(
            betaManagedAgentsWebFetchUrlSourceToolFilter:
                BetaManagedAgentsWebFetchUrlSourceToolFilter
        ) =
            BetaManagedAgentsWebFetchUrlSourceToolFilterParams(
                betaManagedAgentsWebFetchUrlSourceToolFilter =
                    betaManagedAgentsWebFetchUrlSourceToolFilter
            )
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaManagedAgentsWebFetchUrlSourceToolFilterParams] to a value of type [T].
     */
    interface Visitor<out T> {

        /**
         * String form of a url_sources value that has no field other than its type: "all" means
         * {"type": "all"} and "none" means {"type": "none"}.
         */
        fun visitShorthand(shorthand: BetaManagedAgentsWebFetchUrlSourceShorthand): T

        /** Which tools' results contribute URLs that may be fetched. */
        fun visitBetaManagedAgentsWebFetchUrlSourceToolFilter(
            betaManagedAgentsWebFetchUrlSourceToolFilter:
                BetaManagedAgentsWebFetchUrlSourceToolFilter
        ): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsWebFetchUrlSourceToolFilterParams] to a
         * value of type [T].
         *
         * An instance of [BetaManagedAgentsWebFetchUrlSourceToolFilterParams] can contain an
         * unknown variant if it was deserialized from data that doesn't match any known variant.
         * For example, if the SDK is on an older version than the API, then the API may respond
         * with new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsWebFetchUrlSourceToolFilterParams: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsWebFetchUrlSourceToolFilterParams>(
            BetaManagedAgentsWebFetchUrlSourceToolFilterParams::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaManagedAgentsWebFetchUrlSourceToolFilterParams {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceShorthand>(),
                            )
                            ?.let {
                                BetaManagedAgentsWebFetchUrlSourceToolFilterParams(
                                    shorthand = it,
                                    _json = json,
                                )
                            },
                        tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourceToolFilter>(),
                            )
                            ?.let {
                                BetaManagedAgentsWebFetchUrlSourceToolFilterParams(
                                    betaManagedAgentsWebFetchUrlSourceToolFilter = it,
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
                0 -> BetaManagedAgentsWebFetchUrlSourceToolFilterParams(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsWebFetchUrlSourceToolFilterParams>(
            BetaManagedAgentsWebFetchUrlSourceToolFilterParams::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsWebFetchUrlSourceToolFilterParams,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.shorthand != null -> generator.writeObject(value.shorthand)
                value.betaManagedAgentsWebFetchUrlSourceToolFilter != null ->
                    generator.writeObject(value.betaManagedAgentsWebFetchUrlSourceToolFilter)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException(
                        "Invalid BetaManagedAgentsWebFetchUrlSourceToolFilterParams"
                    )
            }
        }
    }
}
