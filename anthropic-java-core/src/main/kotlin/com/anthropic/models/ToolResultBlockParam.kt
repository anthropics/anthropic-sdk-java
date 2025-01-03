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
import kotlin.jvm.optionals.getOrNull

@NoAutoDetect
class ToolResultBlockParam
@JsonCreator
private constructor(
    @JsonProperty("cache_control")
    @ExcludeMissing
    private val cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonField<Type> = JsonMissing.of(),
    @JsonProperty("tool_use_id")
    @ExcludeMissing
    private val toolUseId: JsonField<String> = JsonMissing.of(),
    @JsonProperty("is_error")
    @ExcludeMissing
    private val isError: JsonField<Boolean> = JsonMissing.of(),
    @JsonProperty("content")
    @ExcludeMissing
    private val content: JsonField<Content> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun cacheControl(): Optional<CacheControlEphemeral> =
        Optional.ofNullable(cacheControl.getNullable("cache_control"))

    fun type(): Type = type.getRequired("type")

    fun toolUseId(): String = toolUseId.getRequired("tool_use_id")

    fun isError(): Optional<Boolean> = Optional.ofNullable(isError.getNullable("is_error"))

    fun content(): Optional<Content> = Optional.ofNullable(content.getNullable("content"))

    @JsonProperty("cache_control") @ExcludeMissing fun _cacheControl() = cacheControl

    @JsonProperty("type") @ExcludeMissing fun _type() = type

    @JsonProperty("tool_use_id") @ExcludeMissing fun _toolUseId() = toolUseId

    @JsonProperty("is_error") @ExcludeMissing fun _isError() = isError

    @JsonProperty("content") @ExcludeMissing fun _content() = content

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): ToolResultBlockParam = apply {
        if (!validated) {
            cacheControl().map { it.validate() }
            type()
            toolUseId()
            isError()
            content()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
        private var type: JsonField<Type> = JsonMissing.of()
        private var toolUseId: JsonField<String> = JsonMissing.of()
        private var isError: JsonField<Boolean> = JsonMissing.of()
        private var content: JsonField<Content> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(toolResultBlockParam: ToolResultBlockParam) = apply {
            cacheControl = toolResultBlockParam.cacheControl
            type = toolResultBlockParam.type
            toolUseId = toolResultBlockParam.toolUseId
            isError = toolResultBlockParam.isError
            content = toolResultBlockParam.content
            additionalProperties = toolResultBlockParam.additionalProperties.toMutableMap()
        }

        fun cacheControl(cacheControl: CacheControlEphemeral) =
            cacheControl(JsonField.of(cacheControl))

        fun cacheControl(cacheControl: JsonField<CacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

        fun type(type: Type) = type(JsonField.of(type))

        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun toolUseId(toolUseId: String) = toolUseId(JsonField.of(toolUseId))

        fun toolUseId(toolUseId: JsonField<String>) = apply { this.toolUseId = toolUseId }

        fun isError(isError: Boolean) = isError(JsonField.of(isError))

        fun isError(isError: JsonField<Boolean>) = apply { this.isError = isError }

        fun content(content: Content) = content(JsonField.of(content))

        fun content(content: JsonField<Content>) = apply { this.content = content }

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

        fun build(): ToolResultBlockParam =
            ToolResultBlockParam(
                cacheControl,
                type,
                toolUseId,
                isError,
                content,
                additionalProperties.toImmutable(),
            )
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val TOOL_RESULT = of("tool_result")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            TOOL_RESULT,
        }

        enum class Value {
            TOOL_RESULT,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                TOOL_RESULT -> Value.TOOL_RESULT
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                TOOL_RESULT -> Known.TOOL_RESULT
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

    @JsonDeserialize(using = Content.Deserializer::class)
    @JsonSerialize(using = Content.Serializer::class)
    class Content
    private constructor(
        private val string: String? = null,
        private val blocks: List<Block>? = null,
        private val _json: JsonValue? = null,
    ) {

        private var validated: Boolean = false

        fun string(): Optional<String> = Optional.ofNullable(string)

        fun blocks(): Optional<List<Block>> = Optional.ofNullable(blocks)

        fun isString(): Boolean = string != null

        fun isBlocks(): Boolean = blocks != null

        fun asString(): String = string.getOrThrow("string")

        fun asBlocks(): List<Block> = blocks.getOrThrow("blocks")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                string != null -> visitor.visitString(string)
                blocks != null -> visitor.visitBlocks(blocks)
                else -> visitor.unknown(_json)
            }
        }

        fun validate(): Content = apply {
            if (!validated) {
                if (string == null && blocks == null) {
                    throw AnthropicInvalidDataException("Unknown Content: $_json")
                }
                validated = true
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Content && string == other.string && blocks == other.blocks /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(string, blocks) /* spotless:on */

        override fun toString(): String =
            when {
                string != null -> "Content{string=$string}"
                blocks != null -> "Content{blocks=$blocks}"
                _json != null -> "Content{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Content")
            }

        companion object {

            @JvmStatic fun ofString(string: String) = Content(string = string)

            @JvmStatic fun ofBlocks(blocks: List<Block>) = Content(blocks = blocks)
        }

        interface Visitor<out T> {

            fun visitString(string: String): T

            fun visitBlocks(blocks: List<Block>): T

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
                tryDeserialize(node, jacksonTypeRef<List<Block>>())?.let {
                    return Content(blocks = it, _json = json)
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
                    value.blocks != null -> generator.writeObject(value.blocks)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Content")
                }
            }
        }

        @JsonDeserialize(using = Block.Deserializer::class)
        @JsonSerialize(using = Block.Serializer::class)
        class Block
        private constructor(
            private val textBlockParam: TextBlockParam? = null,
            private val imageBlockParam: ImageBlockParam? = null,
            private val _json: JsonValue? = null,
        ) {

            private var validated: Boolean = false

            fun textBlockParam(): Optional<TextBlockParam> = Optional.ofNullable(textBlockParam)

            fun imageBlockParam(): Optional<ImageBlockParam> = Optional.ofNullable(imageBlockParam)

            fun isTextBlockParam(): Boolean = textBlockParam != null

            fun isImageBlockParam(): Boolean = imageBlockParam != null

            fun asTextBlockParam(): TextBlockParam = textBlockParam.getOrThrow("textBlockParam")

            fun asImageBlockParam(): ImageBlockParam = imageBlockParam.getOrThrow("imageBlockParam")

            fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

            fun <T> accept(visitor: Visitor<T>): T {
                return when {
                    textBlockParam != null -> visitor.visitTextBlockParam(textBlockParam)
                    imageBlockParam != null -> visitor.visitImageBlockParam(imageBlockParam)
                    else -> visitor.unknown(_json)
                }
            }

            fun validate(): Block = apply {
                if (!validated) {
                    if (textBlockParam == null && imageBlockParam == null) {
                        throw AnthropicInvalidDataException("Unknown Block: $_json")
                    }
                    textBlockParam?.validate()
                    imageBlockParam?.validate()
                    validated = true
                }
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return /* spotless:off */ other is Block && textBlockParam == other.textBlockParam && imageBlockParam == other.imageBlockParam /* spotless:on */
            }

            override fun hashCode(): Int = /* spotless:off */ Objects.hash(textBlockParam, imageBlockParam) /* spotless:on */

            override fun toString(): String =
                when {
                    textBlockParam != null -> "Block{textBlockParam=$textBlockParam}"
                    imageBlockParam != null -> "Block{imageBlockParam=$imageBlockParam}"
                    _json != null -> "Block{_unknown=$_json}"
                    else -> throw IllegalStateException("Invalid Block")
                }

            companion object {

                @JvmStatic
                fun ofTextBlockParam(textBlockParam: TextBlockParam) =
                    Block(textBlockParam = textBlockParam)

                @JvmStatic
                fun ofImageBlockParam(imageBlockParam: ImageBlockParam) =
                    Block(imageBlockParam = imageBlockParam)
            }

            interface Visitor<out T> {

                fun visitTextBlockParam(textBlockParam: TextBlockParam): T

                fun visitImageBlockParam(imageBlockParam: ImageBlockParam): T

                fun unknown(json: JsonValue?): T {
                    throw AnthropicInvalidDataException("Unknown Block: $json")
                }
            }

            class Deserializer : BaseDeserializer<Block>(Block::class) {

                override fun ObjectCodec.deserialize(node: JsonNode): Block {
                    val json = JsonValue.fromJsonNode(node)
                    val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                    when (type) {
                        "text" -> {
                            tryDeserialize(node, jacksonTypeRef<TextBlockParam>()) { it.validate() }
                                ?.let {
                                    return Block(textBlockParam = it, _json = json)
                                }
                        }
                        "image" -> {
                            tryDeserialize(node, jacksonTypeRef<ImageBlockParam>()) {
                                    it.validate()
                                }
                                ?.let {
                                    return Block(imageBlockParam = it, _json = json)
                                }
                        }
                    }

                    return Block(_json = json)
                }
            }

            class Serializer : BaseSerializer<Block>(Block::class) {

                override fun serialize(
                    value: Block,
                    generator: JsonGenerator,
                    provider: SerializerProvider
                ) {
                    when {
                        value.textBlockParam != null -> generator.writeObject(value.textBlockParam)
                        value.imageBlockParam != null ->
                            generator.writeObject(value.imageBlockParam)
                        value._json != null -> generator.writeObject(value._json)
                        else -> throw IllegalStateException("Invalid Block")
                    }
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is ToolResultBlockParam && cacheControl == other.cacheControl && type == other.type && toolUseId == other.toolUseId && isError == other.isError && content == other.content && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(cacheControl, type, toolUseId, isError, content, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ToolResultBlockParam{cacheControl=$cacheControl, type=$type, toolUseId=$toolUseId, isError=$isError, content=$content, additionalProperties=$additionalProperties}"
}
