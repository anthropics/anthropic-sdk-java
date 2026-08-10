// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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

@JsonDeserialize(using = BetaRawContentBlockDelta.Deserializer::class)
@JsonSerialize(using = BetaRawContentBlockDelta.Serializer::class)
class BetaRawContentBlockDelta
private constructor(
    private val text: BetaTextDelta? = null,
    private val inputJson: BetaInputJsonDelta? = null,
    private val citations: BetaCitationsDelta? = null,
    private val thinking: BetaThinkingDelta? = null,
    private val signature: BetaSignatureDelta? = null,
    private val compaction: BetaCompactionContentBlockDelta? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitText(text: BetaTextDelta): Type = Type.TEXT_DELTA

                override fun visitInputJson(inputJson: BetaInputJsonDelta): Type =
                    Type.INPUT_JSON_DELTA

                override fun visitCitations(citations: BetaCitationsDelta): Type =
                    Type.CITATIONS_DELTA

                override fun visitThinking(thinking: BetaThinkingDelta): Type = Type.THINKING_DELTA

                override fun visitSignature(signature: BetaSignatureDelta): Type =
                    Type.SIGNATURE_DELTA

                override fun visitCompaction(compaction: BetaCompactionContentBlockDelta): Type =
                    Type.COMPACTION_DELTA

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun text(): Optional<BetaTextDelta> = Optional.ofNullable(text)

    fun inputJson(): Optional<BetaInputJsonDelta> = Optional.ofNullable(inputJson)

    fun citations(): Optional<BetaCitationsDelta> = Optional.ofNullable(citations)

    fun thinking(): Optional<BetaThinkingDelta> = Optional.ofNullable(thinking)

    fun signature(): Optional<BetaSignatureDelta> = Optional.ofNullable(signature)

    fun compaction(): Optional<BetaCompactionContentBlockDelta> = Optional.ofNullable(compaction)

    fun isText(): Boolean = text != null

    fun isInputJson(): Boolean = inputJson != null

    fun isCitations(): Boolean = citations != null

    fun isThinking(): Boolean = thinking != null

    fun isSignature(): Boolean = signature != null

    fun isCompaction(): Boolean = compaction != null

    fun asText(): BetaTextDelta = text.getOrThrow("text")

    fun asInputJson(): BetaInputJsonDelta = inputJson.getOrThrow("inputJson")

    fun asCitations(): BetaCitationsDelta = citations.getOrThrow("citations")

    fun asThinking(): BetaThinkingDelta = thinking.getOrThrow("thinking")

    fun asSignature(): BetaSignatureDelta = signature.getOrThrow("signature")

    fun asCompaction(): BetaCompactionContentBlockDelta = compaction.getOrThrow("compaction")

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
     * Optional<String> result = betaRawContentBlockDelta.accept(new BetaRawContentBlockDelta.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitText(BetaTextDelta text) {
     *         return Optional.of(text.toString());
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
            text != null -> visitor.visitText(text)
            inputJson != null -> visitor.visitInputJson(inputJson)
            citations != null -> visitor.visitCitations(citations)
            thinking != null -> visitor.visitThinking(thinking)
            signature != null -> visitor.visitSignature(signature)
            compaction != null -> visitor.visitCompaction(compaction)
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
    fun validate(): BetaRawContentBlockDelta = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitText(text: BetaTextDelta) {
                    text.validate()
                }

                override fun visitInputJson(inputJson: BetaInputJsonDelta) {
                    inputJson.validate()
                }

                override fun visitCitations(citations: BetaCitationsDelta) {
                    citations.validate()
                }

                override fun visitThinking(thinking: BetaThinkingDelta) {
                    thinking.validate()
                }

                override fun visitSignature(signature: BetaSignatureDelta) {
                    signature.validate()
                }

                override fun visitCompaction(compaction: BetaCompactionContentBlockDelta) {
                    compaction.validate()
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
                override fun visitText(text: BetaTextDelta) = text.validity()

                override fun visitInputJson(inputJson: BetaInputJsonDelta) = inputJson.validity()

                override fun visitCitations(citations: BetaCitationsDelta) = citations.validity()

                override fun visitThinking(thinking: BetaThinkingDelta) = thinking.validity()

                override fun visitSignature(signature: BetaSignatureDelta) = signature.validity()

                override fun visitCompaction(compaction: BetaCompactionContentBlockDelta) =
                    compaction.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaRawContentBlockDelta &&
            text == other.text &&
            inputJson == other.inputJson &&
            citations == other.citations &&
            thinking == other.thinking &&
            signature == other.signature &&
            compaction == other.compaction
    }

    override fun hashCode(): Int =
        Objects.hash(text, inputJson, citations, thinking, signature, compaction)

    override fun toString(): String =
        when {
            text != null -> "BetaRawContentBlockDelta{text=$text}"
            inputJson != null -> "BetaRawContentBlockDelta{inputJson=$inputJson}"
            citations != null -> "BetaRawContentBlockDelta{citations=$citations}"
            thinking != null -> "BetaRawContentBlockDelta{thinking=$thinking}"
            signature != null -> "BetaRawContentBlockDelta{signature=$signature}"
            compaction != null -> "BetaRawContentBlockDelta{compaction=$compaction}"
            _json != null -> "BetaRawContentBlockDelta{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaRawContentBlockDelta")
        }

    companion object {

        @JvmStatic fun ofText(text: BetaTextDelta) = BetaRawContentBlockDelta(text = text)

        /**
         * Returns an immutable instance of [BetaRawContentBlockDelta] whose [ofText] variant is
         * built from the given required [text].
         */
        @JvmStatic fun ofText(text: String) = ofText(BetaTextDelta.of(text))

        @JvmStatic
        fun ofInputJson(inputJson: BetaInputJsonDelta) =
            BetaRawContentBlockDelta(inputJson = inputJson)

        /**
         * Returns an immutable instance of [BetaRawContentBlockDelta] whose [ofInputJson] variant
         * is built from the given required [partialJson].
         */
        @JvmStatic
        fun ofInputJson(partialJson: String) = ofInputJson(BetaInputJsonDelta.of(partialJson))

        @JvmStatic
        fun ofCitations(citations: BetaCitationsDelta) =
            BetaRawContentBlockDelta(citations = citations)

        /**
         * Returns an immutable instance of [BetaRawContentBlockDelta] whose [ofCitations] variant
         * is built from the given required [citation].
         */
        @JvmStatic
        fun ofCitations(citation: BetaCitationsDelta.Citation) =
            ofCitations(BetaCitationsDelta.of(citation))

        @JvmStatic
        fun ofThinking(thinking: BetaThinkingDelta) = BetaRawContentBlockDelta(thinking = thinking)

        @JvmStatic
        fun ofSignature(signature: BetaSignatureDelta) =
            BetaRawContentBlockDelta(signature = signature)

        /**
         * Returns an immutable instance of [BetaRawContentBlockDelta] whose [ofSignature] variant
         * is built from the given required [signature].
         */
        @JvmStatic
        fun ofSignature(signature: String) = ofSignature(BetaSignatureDelta.of(signature))

        @JvmStatic
        fun ofCompaction(compaction: BetaCompactionContentBlockDelta) =
            BetaRawContentBlockDelta(compaction = compaction)
    }

    /**
     * An interface that defines how to map each variant of [BetaRawContentBlockDelta] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitText(text: BetaTextDelta): T

        fun visitInputJson(inputJson: BetaInputJsonDelta): T

        fun visitCitations(citations: BetaCitationsDelta): T

        fun visitThinking(thinking: BetaThinkingDelta): T

        fun visitSignature(signature: BetaSignatureDelta): T

        fun visitCompaction(compaction: BetaCompactionContentBlockDelta): T

        /**
         * Maps an unknown variant of [BetaRawContentBlockDelta] to a value of type [T].
         *
         * An instance of [BetaRawContentBlockDelta] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaRawContentBlockDelta: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaRawContentBlockDelta>(BetaRawContentBlockDelta::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaRawContentBlockDelta {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text_delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaTextDelta>())?.let {
                        BetaRawContentBlockDelta(text = it, _json = json)
                    } ?: BetaRawContentBlockDelta(_json = json)
                }
                "input_json_delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaInputJsonDelta>())?.let {
                        BetaRawContentBlockDelta(inputJson = it, _json = json)
                    } ?: BetaRawContentBlockDelta(_json = json)
                }
                "citations_delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCitationsDelta>())?.let {
                        BetaRawContentBlockDelta(citations = it, _json = json)
                    } ?: BetaRawContentBlockDelta(_json = json)
                }
                "thinking_delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaThinkingDelta>())?.let {
                        BetaRawContentBlockDelta(thinking = it, _json = json)
                    } ?: BetaRawContentBlockDelta(_json = json)
                }
                "signature_delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaSignatureDelta>())?.let {
                        BetaRawContentBlockDelta(signature = it, _json = json)
                    } ?: BetaRawContentBlockDelta(_json = json)
                }
                "compaction_delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCompactionContentBlockDelta>())
                        ?.let { BetaRawContentBlockDelta(compaction = it, _json = json) }
                        ?: BetaRawContentBlockDelta(_json = json)
                }
            }

            return BetaRawContentBlockDelta(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaRawContentBlockDelta>(BetaRawContentBlockDelta::class) {

        override fun serialize(
            value: BetaRawContentBlockDelta,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.text != null -> generator.writeObject(value.text)
                value.inputJson != null -> generator.writeObject(value.inputJson)
                value.citations != null -> generator.writeObject(value.citations)
                value.thinking != null -> generator.writeObject(value.thinking)
                value.signature != null -> generator.writeObject(value.signature)
                value.compaction != null -> generator.writeObject(value.compaction)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaRawContentBlockDelta")
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

            @JvmField val TEXT_DELTA = of("text_delta")

            @JvmField val INPUT_JSON_DELTA = of("input_json_delta")

            @JvmField val CITATIONS_DELTA = of("citations_delta")

            @JvmField val THINKING_DELTA = of("thinking_delta")

            @JvmField val SIGNATURE_DELTA = of("signature_delta")

            @JvmField val COMPACTION_DELTA = of("compaction_delta")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonValue): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            TEXT_DELTA,
            INPUT_JSON_DELTA,
            CITATIONS_DELTA,
            THINKING_DELTA,
            SIGNATURE_DELTA,
            COMPACTION_DELTA,
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
            TEXT_DELTA,
            INPUT_JSON_DELTA,
            CITATIONS_DELTA,
            THINKING_DELTA,
            SIGNATURE_DELTA,
            COMPACTION_DELTA,
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
                TEXT_DELTA -> Value.TEXT_DELTA
                INPUT_JSON_DELTA -> Value.INPUT_JSON_DELTA
                CITATIONS_DELTA -> Value.CITATIONS_DELTA
                THINKING_DELTA -> Value.THINKING_DELTA
                SIGNATURE_DELTA -> Value.SIGNATURE_DELTA
                COMPACTION_DELTA -> Value.COMPACTION_DELTA
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
                TEXT_DELTA -> Known.TEXT_DELTA
                INPUT_JSON_DELTA -> Known.INPUT_JSON_DELTA
                CITATIONS_DELTA -> Known.CITATIONS_DELTA
                THINKING_DELTA -> Known.THINKING_DELTA
                SIGNATURE_DELTA -> Known.SIGNATURE_DELTA
                COMPACTION_DELTA -> Known.COMPACTION_DELTA
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
