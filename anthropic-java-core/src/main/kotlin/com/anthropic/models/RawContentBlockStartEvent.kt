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
class RawContentBlockStartEvent
@JsonCreator
private constructor(
    @JsonProperty("content_block")
    @ExcludeMissing
    private val contentBlock: JsonField<ContentBlock> = JsonMissing.of(),
    @JsonProperty("index") @ExcludeMissing private val index: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonField<Type> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun contentBlock(): ContentBlock = contentBlock.getRequired("content_block")

    fun index(): Long = index.getRequired("index")

    fun type(): Type = type.getRequired("type")

    @JsonProperty("content_block")
    @ExcludeMissing
    fun _contentBlock(): JsonField<ContentBlock> = contentBlock

    @JsonProperty("index") @ExcludeMissing fun _index(): JsonField<Long> = index

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): RawContentBlockStartEvent = apply {
        if (!validated) {
            contentBlock()
            index()
            type()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var contentBlock: JsonField<ContentBlock>? = null
        private var index: JsonField<Long>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(rawContentBlockStartEvent: RawContentBlockStartEvent) = apply {
            contentBlock = rawContentBlockStartEvent.contentBlock
            index = rawContentBlockStartEvent.index
            type = rawContentBlockStartEvent.type
            additionalProperties = rawContentBlockStartEvent.additionalProperties.toMutableMap()
        }

        fun contentBlock(contentBlock: ContentBlock) = contentBlock(JsonField.of(contentBlock))

        fun contentBlock(contentBlock: JsonField<ContentBlock>) = apply {
            this.contentBlock = contentBlock
        }

        fun contentBlock(textBlock: TextBlock) = contentBlock(ContentBlock.ofTextBlock(textBlock))

        fun contentBlock(toolUseBlock: ToolUseBlock) =
            contentBlock(ContentBlock.ofToolUseBlock(toolUseBlock))

        fun index(index: Long) = index(JsonField.of(index))

        fun index(index: JsonField<Long>) = apply { this.index = index }

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

        fun build(): RawContentBlockStartEvent =
            RawContentBlockStartEvent(
                checkNotNull(contentBlock) { "`contentBlock` is required but was not set" },
                checkNotNull(index) { "`index` is required but was not set" },
                checkNotNull(type) { "`type` is required but was not set" },
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

        return /* spotless:off */ other is RawContentBlockStartEvent && contentBlock == other.contentBlock && index == other.index && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(contentBlock, index, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "RawContentBlockStartEvent{contentBlock=$contentBlock, index=$index, type=$type, additionalProperties=$additionalProperties}"
}
