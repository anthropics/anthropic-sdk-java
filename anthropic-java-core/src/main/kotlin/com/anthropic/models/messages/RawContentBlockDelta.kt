// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

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

@JsonDeserialize(using = RawContentBlockDelta.Deserializer::class)
@JsonSerialize(using = RawContentBlockDelta.Serializer::class)
class RawContentBlockDelta
private constructor(
    private val text: TextDelta? = null,
    private val inputJson: InputJsonDelta? = null,
    private val citations: CitationsDelta? = null,
    private val thinking: ThinkingDelta? = null,
    private val signature: SignatureDelta? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitText(text: TextDelta): Type = Type.TEXT_DELTA

                override fun visitInputJson(inputJson: InputJsonDelta): Type = Type.INPUT_JSON_DELTA

                override fun visitCitations(citations: CitationsDelta): Type = Type.CITATIONS_DELTA

                override fun visitThinking(thinking: ThinkingDelta): Type = Type.THINKING_DELTA

                override fun visitSignature(signature: SignatureDelta): Type = Type.SIGNATURE_DELTA

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun text(): Optional<TextDelta> = Optional.ofNullable(text)

    fun inputJson(): Optional<InputJsonDelta> = Optional.ofNullable(inputJson)

    fun citations(): Optional<CitationsDelta> = Optional.ofNullable(citations)

    fun thinking(): Optional<ThinkingDelta> = Optional.ofNullable(thinking)

    fun signature(): Optional<SignatureDelta> = Optional.ofNullable(signature)

    fun isText(): Boolean = text != null

    fun isInputJson(): Boolean = inputJson != null

    fun isCitations(): Boolean = citations != null

    fun isThinking(): Boolean = thinking != null

    fun isSignature(): Boolean = signature != null

    fun asText(): TextDelta = text.getOrThrow("text")

    fun asInputJson(): InputJsonDelta = inputJson.getOrThrow("inputJson")

    fun asCitations(): CitationsDelta = citations.getOrThrow("citations")

    fun asThinking(): ThinkingDelta = thinking.getOrThrow("thinking")

    fun asSignature(): SignatureDelta = signature.getOrThrow("signature")

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
     * Optional<String> result = rawContentBlockDelta.accept(new RawContentBlockDelta.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitText(TextDelta text) {
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
    fun validate(): RawContentBlockDelta = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitText(text: TextDelta) {
                    text.validate()
                }

                override fun visitInputJson(inputJson: InputJsonDelta) {
                    inputJson.validate()
                }

                override fun visitCitations(citations: CitationsDelta) {
                    citations.validate()
                }

                override fun visitThinking(thinking: ThinkingDelta) {
                    thinking.validate()
                }

                override fun visitSignature(signature: SignatureDelta) {
                    signature.validate()
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
                override fun visitText(text: TextDelta) = text.validity()

                override fun visitInputJson(inputJson: InputJsonDelta) = inputJson.validity()

                override fun visitCitations(citations: CitationsDelta) = citations.validity()

                override fun visitThinking(thinking: ThinkingDelta) = thinking.validity()

                override fun visitSignature(signature: SignatureDelta) = signature.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is RawContentBlockDelta &&
            text == other.text &&
            inputJson == other.inputJson &&
            citations == other.citations &&
            thinking == other.thinking &&
            signature == other.signature
    }

    override fun hashCode(): Int = Objects.hash(text, inputJson, citations, thinking, signature)

    override fun toString(): String =
        when {
            text != null -> "RawContentBlockDelta{text=$text}"
            inputJson != null -> "RawContentBlockDelta{inputJson=$inputJson}"
            citations != null -> "RawContentBlockDelta{citations=$citations}"
            thinking != null -> "RawContentBlockDelta{thinking=$thinking}"
            signature != null -> "RawContentBlockDelta{signature=$signature}"
            _json != null -> "RawContentBlockDelta{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid RawContentBlockDelta")
        }

    companion object {

        @JvmStatic fun ofText(text: TextDelta) = RawContentBlockDelta(text = text)

        /**
         * Returns an immutable instance of [RawContentBlockDelta] whose [ofText] variant is built
         * from the given required [text].
         */
        @JvmStatic fun ofText(text: String) = ofText(TextDelta.of(text))

        @JvmStatic
        fun ofInputJson(inputJson: InputJsonDelta) = RawContentBlockDelta(inputJson = inputJson)

        /**
         * Returns an immutable instance of [RawContentBlockDelta] whose [ofInputJson] variant is
         * built from the given required [partialJson].
         */
        @JvmStatic
        fun ofInputJson(partialJson: String) = ofInputJson(InputJsonDelta.of(partialJson))

        @JvmStatic
        fun ofCitations(citations: CitationsDelta) = RawContentBlockDelta(citations = citations)

        /**
         * Returns an immutable instance of [RawContentBlockDelta] whose [ofCitations] variant is
         * built from the given required [citation].
         */
        @JvmStatic
        fun ofCitations(citation: CitationsDelta.Citation) =
            ofCitations(CitationsDelta.of(citation))

        @JvmStatic
        fun ofThinking(thinking: ThinkingDelta) = RawContentBlockDelta(thinking = thinking)

        /**
         * Returns an immutable instance of [RawContentBlockDelta] whose [ofThinking] variant is
         * built from the given required [thinking].
         */
        @JvmStatic fun ofThinking(thinking: String) = ofThinking(ThinkingDelta.of(thinking))

        @JvmStatic
        fun ofSignature(signature: SignatureDelta) = RawContentBlockDelta(signature = signature)

        /**
         * Returns an immutable instance of [RawContentBlockDelta] whose [ofSignature] variant is
         * built from the given required [signature].
         */
        @JvmStatic fun ofSignature(signature: String) = ofSignature(SignatureDelta.of(signature))
    }

    /**
     * An interface that defines how to map each variant of [RawContentBlockDelta] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitText(text: TextDelta): T

        fun visitInputJson(inputJson: InputJsonDelta): T

        fun visitCitations(citations: CitationsDelta): T

        fun visitThinking(thinking: ThinkingDelta): T

        fun visitSignature(signature: SignatureDelta): T

        /**
         * Maps an unknown variant of [RawContentBlockDelta] to a value of type [T].
         *
         * An instance of [RawContentBlockDelta] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown RawContentBlockDelta: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<RawContentBlockDelta>(RawContentBlockDelta::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): RawContentBlockDelta {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text_delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<TextDelta>())?.let {
                        RawContentBlockDelta(text = it, _json = json)
                    } ?: RawContentBlockDelta(_json = json)
                }
                "input_json_delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<InputJsonDelta>())?.let {
                        RawContentBlockDelta(inputJson = it, _json = json)
                    } ?: RawContentBlockDelta(_json = json)
                }
                "citations_delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<CitationsDelta>())?.let {
                        RawContentBlockDelta(citations = it, _json = json)
                    } ?: RawContentBlockDelta(_json = json)
                }
                "thinking_delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<ThinkingDelta>())?.let {
                        RawContentBlockDelta(thinking = it, _json = json)
                    } ?: RawContentBlockDelta(_json = json)
                }
                "signature_delta" -> {
                    return tryDeserialize(node, jacksonTypeRef<SignatureDelta>())?.let {
                        RawContentBlockDelta(signature = it, _json = json)
                    } ?: RawContentBlockDelta(_json = json)
                }
            }

            return RawContentBlockDelta(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<RawContentBlockDelta>(RawContentBlockDelta::class) {

        override fun serialize(
            value: RawContentBlockDelta,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.text != null -> generator.writeObject(value.text)
                value.inputJson != null -> generator.writeObject(value.inputJson)
                value.citations != null -> generator.writeObject(value.citations)
                value.thinking != null -> generator.writeObject(value.thinking)
                value.signature != null -> generator.writeObject(value.signature)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid RawContentBlockDelta")
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
