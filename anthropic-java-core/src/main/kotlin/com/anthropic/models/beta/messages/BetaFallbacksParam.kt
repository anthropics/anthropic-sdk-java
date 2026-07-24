// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
import com.anthropic.core.getOrThrow
import com.anthropic.core.toImmutable
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
 * Opt-in server-side retry on one or more substitute models when the requested model declines for
 * policy reasons. Tried in order: if the first entry also declines, the second is tried, and so on.
 * The string "default" requests the requested model's server-defined default fallback
 * configuration.
 */
@JsonDeserialize(using = BetaFallbacksParam.Deserializer::class)
@JsonSerialize(using = BetaFallbacksParam.Serializer::class)
class BetaFallbacksParam
private constructor(
    private val fallbackParams: List<BetaFallbackParam>? = null,
    private val default_: JsonValue? = null,
    private val _json: JsonValue? = null,
) {

    fun fallbackParams(): Optional<List<BetaFallbackParam>> = Optional.ofNullable(fallbackParams)

    fun default_(): Optional<JsonValue> = Optional.ofNullable(default_)

    fun isFallbackParams(): Boolean = fallbackParams != null

    fun isDefault(): Boolean = default_ != null

    fun asFallbackParams(): List<BetaFallbackParam> = fallbackParams.getOrThrow("fallbackParams")

    fun asDefault(): JsonValue = default_.getOrThrow("default_")

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
     * Optional<String> result = betaFallbacksParam.accept(new BetaFallbacksParam.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitFallbackParams(List<BetaFallbackParam> fallbackParams) {
     *         return Optional.of(fallbackParams.toString());
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
            fallbackParams != null -> visitor.visitFallbackParams(fallbackParams)
            default_ != null -> visitor.visitDefault(default_)
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
    fun validate(): BetaFallbacksParam = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitFallbackParams(fallbackParams: List<BetaFallbackParam>) {
                    fallbackParams.forEach { it.validate() }
                }

                override fun visitDefault(default_: JsonValue) {
                    default_.let {
                        if (it != JsonValue.from("default")) {
                            throw AnthropicInvalidDataException(
                                "'default_' is invalid, received $it"
                            )
                        }
                    }
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
                override fun visitFallbackParams(fallbackParams: List<BetaFallbackParam>) =
                    fallbackParams.sumOf { it.validity().toInt() }

                override fun visitDefault(default_: JsonValue) =
                    default_.let { if (it == JsonValue.from("default")) 1 else 0 }

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaFallbacksParam &&
            fallbackParams == other.fallbackParams &&
            default_ == other.default_
    }

    override fun hashCode(): Int = Objects.hash(fallbackParams, default_)

    override fun toString(): String =
        when {
            fallbackParams != null -> "BetaFallbacksParam{fallbackParams=$fallbackParams}"
            default_ != null -> "BetaFallbacksParam{default_=$default_}"
            _json != null -> "BetaFallbacksParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaFallbacksParam")
        }

    companion object {

        @JvmStatic
        fun ofFallbackParams(fallbackParams: List<BetaFallbackParam>) =
            BetaFallbacksParam(fallbackParams = fallbackParams.toImmutable())

        @JvmStatic fun ofDefault() = BetaFallbacksParam(default_ = JsonValue.from("default"))
    }

    /**
     * An interface that defines how to map each variant of [BetaFallbacksParam] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        fun visitFallbackParams(fallbackParams: List<BetaFallbackParam>): T

        fun visitDefault(default_: JsonValue): T

        /**
         * Maps an unknown variant of [BetaFallbacksParam] to a value of type [T].
         *
         * An instance of [BetaFallbacksParam] can contain an unknown variant if it was deserialized
         * from data that doesn't match any known variant. For example, if the SDK is on an older
         * version than the API, then the API may respond with new variants that the SDK is unaware
         * of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaFallbacksParam: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<BetaFallbacksParam>(BetaFallbacksParam::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaFallbacksParam {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<JsonValue>())
                            ?.let { BetaFallbacksParam(default_ = it, _json = json) }
                            ?.takeIf { it.isValid() },
                        tryDeserialize(node, jacksonTypeRef<List<BetaFallbackParam>>())?.let {
                            BetaFallbacksParam(fallbackParams = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> BetaFallbacksParam(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer : BaseSerializer<BetaFallbacksParam>(BetaFallbacksParam::class) {

        override fun serialize(
            value: BetaFallbacksParam,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.fallbackParams != null -> generator.writeObject(value.fallbackParams)
                value.default_ != null -> generator.writeObject(value.default_)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaFallbacksParam")
            }
        }
    }
}
