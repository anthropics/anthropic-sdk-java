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

@JsonDeserialize(builder = BetaRawContentBlockStartEvent.Builder::class)
@NoAutoDetect
class BetaRawContentBlockStartEvent
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

    fun validate(): BetaRawContentBlockStartEvent = apply {
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
        internal fun from(betaRawContentBlockStartEvent: BetaRawContentBlockStartEvent) = apply {
            this.type = betaRawContentBlockStartEvent.type
            this.index = betaRawContentBlockStartEvent.index
            this.contentBlock = betaRawContentBlockStartEvent.contentBlock
            additionalProperties(betaRawContentBlockStartEvent.additionalProperties)
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

        fun build(): BetaRawContentBlockStartEvent =
            BetaRawContentBlockStartEvent(
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
        private val betaTextBlock: BetaTextBlock? = null,
        private val betaToolUseBlock: BetaToolUseBlock? = null,
        private val _json: JsonValue? = null,
    ) {

        private var validated: Boolean = false

        fun betaTextBlock(): Optional<BetaTextBlock> = Optional.ofNullable(betaTextBlock)

        fun betaToolUseBlock(): Optional<BetaToolUseBlock> = Optional.ofNullable(betaToolUseBlock)

        fun isBetaTextBlock(): Boolean = betaTextBlock != null

        fun isBetaToolUseBlock(): Boolean = betaToolUseBlock != null

        fun asBetaTextBlock(): BetaTextBlock = betaTextBlock.getOrThrow("betaTextBlock")

        fun asBetaToolUseBlock(): BetaToolUseBlock = betaToolUseBlock.getOrThrow("betaToolUseBlock")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                betaTextBlock != null -> visitor.visitBetaTextBlock(betaTextBlock)
                betaToolUseBlock != null -> visitor.visitBetaToolUseBlock(betaToolUseBlock)
                else -> visitor.unknown(_json)
            }
        }

        fun validate(): ContentBlock = apply {
            if (!validated) {
                if (betaTextBlock == null && betaToolUseBlock == null) {
                    throw AnthropicInvalidDataException("Unknown ContentBlock: $_json")
                }
                betaTextBlock?.validate()
                betaToolUseBlock?.validate()
                validated = true
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is ContentBlock && betaTextBlock == other.betaTextBlock && betaToolUseBlock == other.betaToolUseBlock /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaTextBlock, betaToolUseBlock) /* spotless:on */

        override fun toString(): String =
            when {
                betaTextBlock != null -> "ContentBlock{betaTextBlock=$betaTextBlock}"
                betaToolUseBlock != null -> "ContentBlock{betaToolUseBlock=$betaToolUseBlock}"
                _json != null -> "ContentBlock{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid ContentBlock")
            }

        companion object {

            @JvmStatic
            fun ofBetaTextBlock(betaTextBlock: BetaTextBlock) =
                ContentBlock(betaTextBlock = betaTextBlock)

            @JvmStatic
            fun ofBetaToolUseBlock(betaToolUseBlock: BetaToolUseBlock) =
                ContentBlock(betaToolUseBlock = betaToolUseBlock)
        }

        interface Visitor<out T> {

            fun visitBetaTextBlock(betaTextBlock: BetaTextBlock): T

            fun visitBetaToolUseBlock(betaToolUseBlock: BetaToolUseBlock): T

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
                        tryDeserialize(node, jacksonTypeRef<BetaTextBlock>()) { it.validate() }
                            ?.let {
                                return ContentBlock(betaTextBlock = it, _json = json)
                            }
                    }
                    "tool_use" -> {
                        tryDeserialize(node, jacksonTypeRef<BetaToolUseBlock>()) { it.validate() }
                            ?.let {
                                return ContentBlock(betaToolUseBlock = it, _json = json)
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
                    value.betaTextBlock != null -> generator.writeObject(value.betaTextBlock)
                    value.betaToolUseBlock != null -> generator.writeObject(value.betaToolUseBlock)
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

        return /* spotless:off */ other is BetaRawContentBlockStartEvent && type == other.type && index == other.index && contentBlock == other.contentBlock && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(type, index, contentBlock, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRawContentBlockStartEvent{type=$type, index=$index, contentBlock=$contentBlock, additionalProperties=$additionalProperties}"
}
