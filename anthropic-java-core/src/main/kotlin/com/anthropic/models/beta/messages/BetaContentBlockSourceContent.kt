// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
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

@JsonDeserialize(using = BetaContentBlockSourceContent.Deserializer::class)
@JsonSerialize(using = BetaContentBlockSourceContent.Serializer::class)
class BetaContentBlockSourceContent
private constructor(
    private val text: BetaTextBlockParam? = null,
    private val image: BetaImageBlockParam? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitText(text: BetaTextBlockParam): Type = Type.TEXT

                override fun visitImage(image: BetaImageBlockParam): Type = Type.IMAGE

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        accept(
            object : Visitor<Optional<BetaCacheControlEphemeral>> {
                override fun visitText(
                    text: BetaTextBlockParam
                ): Optional<BetaCacheControlEphemeral> = text.cacheControl()

                override fun visitImage(
                    image: BetaImageBlockParam
                ): Optional<BetaCacheControlEphemeral> = image.cacheControl()

                override fun unknown(json: JsonValue?): Optional<BetaCacheControlEphemeral> =
                    json.getProperty<BetaCacheControlEphemeral>("cache_control").asKnown()
            }
        )

    fun text(): Optional<BetaTextBlockParam> = Optional.ofNullable(text)

    fun image(): Optional<BetaImageBlockParam> = Optional.ofNullable(image)

    fun isText(): Boolean = text != null

    fun isImage(): Boolean = image != null

    fun asText(): BetaTextBlockParam = text.getOrThrow("text")

    fun asImage(): BetaImageBlockParam = image.getOrThrow("image")

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
     * Optional<String> result = betaContentBlockSourceContent.accept(new BetaContentBlockSourceContent.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitText(BetaTextBlockParam text) {
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
            image != null -> visitor.visitImage(image)
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
    fun validate(): BetaContentBlockSourceContent = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitText(text: BetaTextBlockParam) {
                    text.validate()
                }

                override fun visitImage(image: BetaImageBlockParam) {
                    image.validate()
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
                override fun visitText(text: BetaTextBlockParam) = text.validity()

                override fun visitImage(image: BetaImageBlockParam) = image.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaContentBlockSourceContent && text == other.text && image == other.image
    }

    override fun hashCode(): Int = Objects.hash(text, image)

    override fun toString(): String =
        when {
            text != null -> "BetaContentBlockSourceContent{text=$text}"
            image != null -> "BetaContentBlockSourceContent{image=$image}"
            _json != null -> "BetaContentBlockSourceContent{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaContentBlockSourceContent")
        }

    companion object {

        @JvmStatic fun ofText(text: BetaTextBlockParam) = BetaContentBlockSourceContent(text = text)

        /**
         * Returns an immutable instance of [BetaContentBlockSourceContent] whose [ofText] variant
         * is built from the given required [text].
         */
        @JvmStatic fun ofText(text: String) = ofText(BetaTextBlockParam.of(text))

        @JvmStatic
        fun ofImage(image: BetaImageBlockParam) = BetaContentBlockSourceContent(image = image)

        /**
         * Returns an immutable instance of [BetaContentBlockSourceContent] whose [ofImage] variant
         * is built from the given required [source].
         */
        @JvmStatic
        fun ofImage(source: BetaImageBlockParam.Source) = ofImage(BetaImageBlockParam.of(source))
    }

    /**
     * An interface that defines how to map each variant of [BetaContentBlockSourceContent] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        fun visitText(text: BetaTextBlockParam): T

        fun visitImage(image: BetaImageBlockParam): T

        /**
         * Maps an unknown variant of [BetaContentBlockSourceContent] to a value of type [T].
         *
         * An instance of [BetaContentBlockSourceContent] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaContentBlockSourceContent: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaContentBlockSourceContent>(BetaContentBlockSourceContent::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaContentBlockSourceContent {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "text" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaTextBlockParam>())?.let {
                        BetaContentBlockSourceContent(text = it, _json = json)
                    } ?: BetaContentBlockSourceContent(_json = json)
                }
                "image" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaImageBlockParam>())?.let {
                        BetaContentBlockSourceContent(image = it, _json = json)
                    } ?: BetaContentBlockSourceContent(_json = json)
                }
            }

            return BetaContentBlockSourceContent(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaContentBlockSourceContent>(BetaContentBlockSourceContent::class) {

        override fun serialize(
            value: BetaContentBlockSourceContent,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.text != null -> generator.writeObject(value.text)
                value.image != null -> generator.writeObject(value.image)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaContentBlockSourceContent")
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

            @JvmField val TEXT = of("text")

            @JvmField val IMAGE = of("image")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            TEXT,
            IMAGE,
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
            TEXT,
            IMAGE,
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
                TEXT -> Value.TEXT
                IMAGE -> Value.IMAGE
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
                TEXT -> Known.TEXT
                IMAGE -> Known.IMAGE
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
