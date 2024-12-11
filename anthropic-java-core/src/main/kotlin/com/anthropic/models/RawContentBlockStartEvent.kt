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

@JsonDeserialize(builder = RawContentBlockStartEvent.Builder::class)
@NoAutoDetect
class RawContentBlockStartEvent
private constructor(
    private val type: JsonField<Type>,
    private val index: JsonField<Long>,
    private val contentBlock: JsonField<ContentBlock>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    private var validated: Boolean = false

    fun type(): Type = type.getRequired("type")

    fun index(): Long = index.getRequired("index")

    fun contentBlock(): ContentBlock = contentBlock.getRequired("content_block")

    @JsonProperty("type") @ExcludeMissing fun _type() = type

    @JsonProperty("index") @ExcludeMissing fun _index() = index

    @JsonProperty("content_block") @ExcludeMissing fun _contentBlock() = contentBlock

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): RawContentBlockStartEvent = apply {
        if (!validated) {
            type()
            index()
            contentBlock()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var type: JsonField<Type> = JsonMissing.of()
        private var index: JsonField<Long> = JsonMissing.of()
        private var contentBlock: JsonField<ContentBlock> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(rawContentBlockStartEvent: RawContentBlockStartEvent) = apply {
            this.type = rawContentBlockStartEvent.type
            this.index = rawContentBlockStartEvent.index
            this.contentBlock = rawContentBlockStartEvent.contentBlock
            additionalProperties(rawContentBlockStartEvent.additionalProperties)
        }

        fun type(type: Type) = type(JsonField.of(type))

        @JsonProperty("type")
        @ExcludeMissing
        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun index(index: Long) = index(JsonField.of(index))

        @JsonProperty("index")
        @ExcludeMissing
        fun index(index: JsonField<Long>) = apply { this.index = index }

        fun contentBlock(contentBlock: ContentBlock) = contentBlock(JsonField.of(contentBlock))

        @JsonProperty("content_block")
        @ExcludeMissing
        fun contentBlock(contentBlock: JsonField<ContentBlock>) = apply {
            this.contentBlock = contentBlock
        }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            this.additionalProperties.putAll(additionalProperties)
        }

        @JsonAnySetter
        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            this.additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun build(): RawContentBlockStartEvent =
            RawContentBlockStartEvent(
                type,
                index,
                contentBlock,
                additionalProperties.toImmutable(),
            )
    }

    @JsonDeserialize(using = ContentBlock.Deserializer::class)
    @JsonSerialize(using = ContentBlock.Serializer::class)
    class ContentBlock
    private constructor(
        private val textBlock: TextBlock? = null,
        private val toolUseBlock: ToolUseBlock? = null,
        private val _json: JsonValue? = null,
    ) {

        private var validated: Boolean = false

        fun textBlock(): Optional<TextBlock> = Optional.ofNullable(textBlock)

        fun toolUseBlock(): Optional<ToolUseBlock> = Optional.ofNullable(toolUseBlock)

        fun isTextBlock(): Boolean = textBlock != null

        fun isToolUseBlock(): Boolean = toolUseBlock != null

        fun asTextBlock(): TextBlock = textBlock.getOrThrow("textBlock")

        fun asToolUseBlock(): ToolUseBlock = toolUseBlock.getOrThrow("toolUseBlock")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                textBlock != null -> visitor.visitTextBlock(textBlock)
                toolUseBlock != null -> visitor.visitToolUseBlock(toolUseBlock)
                else -> visitor.unknown(_json)
            }
        }

        fun validate(): ContentBlock = apply {
            if (!validated) {
                if (textBlock == null && toolUseBlock == null) {
                    throw AnthropicInvalidDataException("Unknown ContentBlock: $_json")
                }
                textBlock?.validate()
                toolUseBlock?.validate()
                validated = true
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is ContentBlock && textBlock == other.textBlock && toolUseBlock == other.toolUseBlock /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(textBlock, toolUseBlock) /* spotless:on */

        override fun toString(): String =
            when {
                textBlock != null -> "ContentBlock{textBlock=$textBlock}"
                toolUseBlock != null -> "ContentBlock{toolUseBlock=$toolUseBlock}"
                _json != null -> "ContentBlock{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid ContentBlock")
            }

        companion object {

            @JvmStatic fun ofTextBlock(textBlock: TextBlock) = ContentBlock(textBlock = textBlock)

            @JvmStatic
            fun ofToolUseBlock(toolUseBlock: ToolUseBlock) =
                ContentBlock(toolUseBlock = toolUseBlock)
        }

        interface Visitor<out T> {

            fun visitTextBlock(textBlock: TextBlock): T

            fun visitToolUseBlock(toolUseBlock: ToolUseBlock): T

            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown ContentBlock: $json")
            }
        }

        class Deserializer : BaseDeserializer<ContentBlock>(ContentBlock::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): ContentBlock {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "text" -> {
                        tryDeserialize(node, jacksonTypeRef<TextBlock>()) { it.validate() }
                            ?.let {
                                return ContentBlock(textBlock = it, _json = json)
                            }
                    }
                    "tool_use" -> {
                        tryDeserialize(node, jacksonTypeRef<ToolUseBlock>()) { it.validate() }
                            ?.let {
                                return ContentBlock(toolUseBlock = it, _json = json)
                            }
                    }
                }

                return ContentBlock(_json = json)
            }
        }

        class Serializer : BaseSerializer<ContentBlock>(ContentBlock::class) {

            override fun serialize(
                value: ContentBlock,
                generator: JsonGenerator,
                provider: SerializerProvider
            ) {
                when {
                    value.textBlock != null -> generator.writeObject(value.textBlock)
                    value.toolUseBlock != null -> generator.writeObject(value.toolUseBlock)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid ContentBlock")
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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Type && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()

        companion object {

            @JvmField val CONTENT_BLOCK_START = of("content_block_start")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            CONTENT_BLOCK_START,
        }

        enum class Value {
            CONTENT_BLOCK_START,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                CONTENT_BLOCK_START -> Value.CONTENT_BLOCK_START
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                CONTENT_BLOCK_START -> Known.CONTENT_BLOCK_START
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        fun asString(): String = _value().asStringOrThrow()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is RawContentBlockStartEvent && type == other.type && index == other.index && contentBlock == other.contentBlock && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(type, index, contentBlock, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "RawContentBlockStartEvent{type=$type, index=$index, contentBlock=$contentBlock, additionalProperties=$additionalProperties}"
}
