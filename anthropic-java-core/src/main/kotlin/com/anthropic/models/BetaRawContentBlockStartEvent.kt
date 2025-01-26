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
import kotlin.jvm.optionals.getOrNull

@NoAutoDetect
class BetaRawContentBlockStartEvent
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

    fun validate(): BetaRawContentBlockStartEvent = apply {
        if (validated) {
            return@apply
        }

        contentBlock().validate()
        index()
        type()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder internal constructor() {

        private var contentBlock: JsonField<ContentBlock>? = null
        private var index: JsonField<Long>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaRawContentBlockStartEvent: BetaRawContentBlockStartEvent) = apply {
            contentBlock = betaRawContentBlockStartEvent.contentBlock
            index = betaRawContentBlockStartEvent.index
            type = betaRawContentBlockStartEvent.type
            additionalProperties = betaRawContentBlockStartEvent.additionalProperties.toMutableMap()
        }

        fun contentBlock(contentBlock: ContentBlock) = contentBlock(JsonField.of(contentBlock))

        fun contentBlock(contentBlock: JsonField<ContentBlock>) = apply {
            this.contentBlock = contentBlock
        }

        fun contentBlock(betaText: BetaTextBlock) = contentBlock(ContentBlock.ofBetaText(betaText))

        fun contentBlock(betaToolUse: BetaToolUseBlock) =
            contentBlock(ContentBlock.ofBetaToolUse(betaToolUse))

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

        fun build(): BetaRawContentBlockStartEvent =
            BetaRawContentBlockStartEvent(
                checkRequired("contentBlock", contentBlock),
                checkRequired("index", index),
                checkRequired("type", type),
                additionalProperties.toImmutable(),
            )
    }

    @JsonDeserialize(using = ContentBlock.Deserializer::class)
    @JsonSerialize(using = ContentBlock.Serializer::class)
    class ContentBlock
    private constructor(
        private val betaText: BetaTextBlock? = null,
        private val betaToolUse: BetaToolUseBlock? = null,
        private val _json: JsonValue? = null,
    ) {

        fun betaText(): Optional<BetaTextBlock> = Optional.ofNullable(betaText)

        fun betaToolUse(): Optional<BetaToolUseBlock> = Optional.ofNullable(betaToolUse)

        fun isBetaText(): Boolean = betaText != null

        fun isBetaToolUse(): Boolean = betaToolUse != null

        fun asBetaText(): BetaTextBlock = betaText.getOrThrow("betaText")

        fun asBetaToolUse(): BetaToolUseBlock = betaToolUse.getOrThrow("betaToolUse")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                betaText != null -> visitor.visitBetaText(betaText)
                betaToolUse != null -> visitor.visitBetaToolUse(betaToolUse)
                else -> visitor.unknown(_json)
            }
        }

        private var validated: Boolean = false

        fun validate(): ContentBlock = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBetaText(betaText: BetaTextBlock) {
                        betaText.validate()
                    }

                    override fun visitBetaToolUse(betaToolUse: BetaToolUseBlock) {
                        betaToolUse.validate()
                    }
                }
            )
            validated = true
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is ContentBlock && betaText == other.betaText && betaToolUse == other.betaToolUse /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaText, betaToolUse) /* spotless:on */

        override fun toString(): String =
            when {
                betaText != null -> "ContentBlock{betaText=$betaText}"
                betaToolUse != null -> "ContentBlock{betaToolUse=$betaToolUse}"
                _json != null -> "ContentBlock{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid ContentBlock")
            }

        companion object {

            @JvmStatic fun ofBetaText(betaText: BetaTextBlock) = ContentBlock(betaText = betaText)

            @JvmStatic
            fun ofBetaToolUse(betaToolUse: BetaToolUseBlock) =
                ContentBlock(betaToolUse = betaToolUse)
        }

        interface Visitor<out T> {

            fun visitBetaText(betaText: BetaTextBlock): T

            fun visitBetaToolUse(betaToolUse: BetaToolUseBlock): T

            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown ContentBlock: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<ContentBlock>(ContentBlock::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): ContentBlock {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "text" -> {
                        tryDeserialize(node, jacksonTypeRef<BetaTextBlock>()) { it.validate() }
                            ?.let {
                                return ContentBlock(betaText = it, _json = json)
                            }
                    }
                    "tool_use" -> {
                        tryDeserialize(node, jacksonTypeRef<BetaToolUseBlock>()) { it.validate() }
                            ?.let {
                                return ContentBlock(betaToolUse = it, _json = json)
                            }
                    }
                }

                return ContentBlock(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<ContentBlock>(ContentBlock::class) {

            override fun serialize(
                value: ContentBlock,
                generator: JsonGenerator,
                provider: SerializerProvider
            ) {
                when {
                    value.betaText != null -> generator.writeObject(value.betaText)
                    value.betaToolUse != null -> generator.writeObject(value.betaToolUse)
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

        return /* spotless:off */ other is BetaRawContentBlockStartEvent && contentBlock == other.contentBlock && index == other.index && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(contentBlock, index, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRawContentBlockStartEvent{contentBlock=$contentBlock, index=$index, type=$type, additionalProperties=$additionalProperties}"
}
