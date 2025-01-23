// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional

@NoAutoDetect
class BetaContentBlockSource
@JsonCreator
private constructor(
    @JsonProperty("content")
    @ExcludeMissing
    private val content: JsonField<Content> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonField<Type> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun content(): Content = content.getRequired("content")

    fun type(): Type = type.getRequired("type")

    @JsonProperty("content") @ExcludeMissing fun _content(): JsonField<Content> = content

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaContentBlockSource = apply {
        if (validated) {
            return@apply
        }

        content().validate()
        type()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var content: JsonField<Content>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaContentBlockSource: BetaContentBlockSource) = apply {
            content = betaContentBlockSource.content
            type = betaContentBlockSource.type
            additionalProperties = betaContentBlockSource.additionalProperties.toMutableMap()
        }

        fun content(content: Content) = content(JsonField.of(content))

        fun content(content: JsonField<Content>) = apply { this.content = content }

        fun content(string: String) = content(Content.ofString(string))

        fun contentOfBetaContentBlockSourceContent(
            betaContentBlockSourceContent: List<BetaContentBlockSourceContent>
        ) = content(Content.ofBetaContentBlockSourceContent(betaContentBlockSourceContent))

        fun type(type: Type) = type(JsonField.of(type))

        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        fun build(): BetaContentBlockSource =
            BetaContentBlockSource(
                checkRequired("content", content),
                checkRequired("type", type),
                additionalProperties.toImmutable(),
            )
    }

    @JsonDeserialize(using = Content.Deserializer::class)
    @JsonSerialize(using = Content.Serializer::class)
    class Content
    private constructor(
        private val string: String? = null,
        private val betaContentBlockSourceContent: List<BetaContentBlockSourceContent>? = null,
        private val _json: JsonValue? = null,
    ) {

        fun string(): Optional<String> = Optional.ofNullable(string)

        fun betaContentBlockSourceContent(): Optional<List<BetaContentBlockSourceContent>> =
            Optional.ofNullable(betaContentBlockSourceContent)

        fun isString(): Boolean = string != null

        fun isBetaContentBlockSourceContent(): Boolean = betaContentBlockSourceContent != null

        fun asString(): String = string.getOrThrow("string")

        fun asBetaContentBlockSourceContent(): List<BetaContentBlockSourceContent> =
            betaContentBlockSourceContent.getOrThrow("betaContentBlockSourceContent")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                string != null -> visitor.visitString(string)
                betaContentBlockSourceContent != null ->
                    visitor.visitBetaContentBlockSourceContent(betaContentBlockSourceContent)
                else -> visitor.unknown(_json)
            }
        }

        private var validated: Boolean = false

        fun validate(): Content = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitString(string: String) {}

                    override fun visitBetaContentBlockSourceContent(
                        betaContentBlockSourceContent: List<BetaContentBlockSourceContent>
                    ) {
                        betaContentBlockSourceContent.forEach { it.validate() }
                    }
                }
            )
            validated = true
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Content && string == other.string && betaContentBlockSourceContent == other.betaContentBlockSourceContent /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(string, betaContentBlockSourceContent) /* spotless:on */

        override fun toString(): String =
            when {
                string != null -> "Content{string=$string}"
                betaContentBlockSourceContent != null ->
                    "Content{betaContentBlockSourceContent=$betaContentBlockSourceContent}"
                _json != null -> "Content{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Content")
            }

        companion object {

            @JvmStatic fun ofString(string: String) = Content(string = string)

            @JvmStatic
            fun ofBetaContentBlockSourceContent(
                betaContentBlockSourceContent: List<BetaContentBlockSourceContent>
            ) = Content(betaContentBlockSourceContent = betaContentBlockSourceContent)
        }

        interface Visitor<out T> {

            fun visitString(string: String): T

            fun visitBetaContentBlockSourceContent(
                betaContentBlockSourceContent: List<BetaContentBlockSourceContent>
            ): T

            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Content: $json")
            }
        }

        class Deserializer : BaseDeserializer<Content>(Content::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Content {
                val json = JsonValue.fromJsonNode(node)

                tryDeserialize(node, jacksonTypeRef<String>())?.let {
                    return Content(string = it, _json = json)
                }
                tryDeserialize(node, jacksonTypeRef<List<BetaContentBlockSourceContent>>()) {
                        it.forEach { it.validate() }
                    }
                    ?.let {
                        return Content(betaContentBlockSourceContent = it, _json = json)
                    }

                return Content(_json = json)
            }
        }

        class Serializer : BaseSerializer<Content>(Content::class) {

            override fun serialize(
                value: Content,
                generator: JsonGenerator,
                provider: SerializerProvider
            ) {
                when {
                    value.string != null -> generator.writeObject(value.string)
                    value.betaContentBlockSourceContent != null ->
                        generator.writeObject(value.betaContentBlockSourceContent)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Content")
                }
            }
        }
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val CONTENT = of("content")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            CONTENT,
        }

        enum class Value {
            CONTENT,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                CONTENT -> Value.CONTENT
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                CONTENT -> Known.CONTENT
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        fun asString(): String = _value().asStringOrThrow()

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Type && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaContentBlockSource && content == other.content && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(content, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaContentBlockSource{content=$content, type=$type, additionalProperties=$additionalProperties}"
}
