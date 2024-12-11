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

@JsonDeserialize(builder = PromptCachingBetaMessageParam.Builder::class)
@NoAutoDetect
class PromptCachingBetaMessageParam
private constructor(
    private val role: JsonField<Role>,
    private val content: JsonField<Content>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    private var validated: Boolean = false

    fun role(): Role = role.getRequired("role")

    fun content(): Content = content.getRequired("content")

    @JsonProperty("role") @ExcludeMissing fun _role() = role

    @JsonProperty("content") @ExcludeMissing fun _content() = content

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): PromptCachingBetaMessageParam = apply {
        if (!validated) {
            role()
            content()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var role: JsonField<Role> = JsonMissing.of()
        private var content: JsonField<Content> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(promptCachingBetaMessageParam: PromptCachingBetaMessageParam) = apply {
            this.role = promptCachingBetaMessageParam.role
            this.content = promptCachingBetaMessageParam.content
            additionalProperties(promptCachingBetaMessageParam.additionalProperties)
        }

        fun role(role: Role) = role(JsonField.of(role))

        @JsonProperty("role")
        @ExcludeMissing
        fun role(role: JsonField<Role>) = apply { this.role = role }

        fun content(content: Content) = content(JsonField.of(content))

        @JsonProperty("content")
        @ExcludeMissing
        fun content(content: JsonField<Content>) = apply { this.content = content }

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

        fun build(): PromptCachingBetaMessageParam =
            PromptCachingBetaMessageParam(
                role,
                content,
                additionalProperties.toImmutable(),
            )
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
            private val promptCachingBetaTextBlockParam: PromptCachingBetaTextBlockParam? = null,
            private val promptCachingBetaImageBlockParam: PromptCachingBetaImageBlockParam? = null,
            private val promptCachingBetaToolUseBlockParam: PromptCachingBetaToolUseBlockParam? =
                null,
            private val promptCachingBetaToolResultBlockParam:
                PromptCachingBetaToolResultBlockParam? =
                null,
            private val _json: JsonValue? = null,
        ) {

            private var validated: Boolean = false

            fun promptCachingBetaTextBlockParam(): Optional<PromptCachingBetaTextBlockParam> =
                Optional.ofNullable(promptCachingBetaTextBlockParam)

            fun promptCachingBetaImageBlockParam(): Optional<PromptCachingBetaImageBlockParam> =
                Optional.ofNullable(promptCachingBetaImageBlockParam)

            fun promptCachingBetaToolUseBlockParam(): Optional<PromptCachingBetaToolUseBlockParam> =
                Optional.ofNullable(promptCachingBetaToolUseBlockParam)

            fun promptCachingBetaToolResultBlockParam():
                Optional<PromptCachingBetaToolResultBlockParam> =
                Optional.ofNullable(promptCachingBetaToolResultBlockParam)

            fun isPromptCachingBetaTextBlockParam(): Boolean =
                promptCachingBetaTextBlockParam != null

            fun isPromptCachingBetaImageBlockParam(): Boolean =
                promptCachingBetaImageBlockParam != null

            fun isPromptCachingBetaToolUseBlockParam(): Boolean =
                promptCachingBetaToolUseBlockParam != null

            fun isPromptCachingBetaToolResultBlockParam(): Boolean =
                promptCachingBetaToolResultBlockParam != null

            fun asPromptCachingBetaTextBlockParam(): PromptCachingBetaTextBlockParam =
                promptCachingBetaTextBlockParam.getOrThrow("promptCachingBetaTextBlockParam")

            fun asPromptCachingBetaImageBlockParam(): PromptCachingBetaImageBlockParam =
                promptCachingBetaImageBlockParam.getOrThrow("promptCachingBetaImageBlockParam")

            fun asPromptCachingBetaToolUseBlockParam(): PromptCachingBetaToolUseBlockParam =
                promptCachingBetaToolUseBlockParam.getOrThrow("promptCachingBetaToolUseBlockParam")

            fun asPromptCachingBetaToolResultBlockParam(): PromptCachingBetaToolResultBlockParam =
                promptCachingBetaToolResultBlockParam.getOrThrow(
                    "promptCachingBetaToolResultBlockParam"
                )

            fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

            fun <T> accept(visitor: Visitor<T>): T {
                return when {
                    promptCachingBetaTextBlockParam != null ->
                        visitor.visitPromptCachingBetaTextBlockParam(
                            promptCachingBetaTextBlockParam
                        )
                    promptCachingBetaImageBlockParam != null ->
                        visitor.visitPromptCachingBetaImageBlockParam(
                            promptCachingBetaImageBlockParam
                        )
                    promptCachingBetaToolUseBlockParam != null ->
                        visitor.visitPromptCachingBetaToolUseBlockParam(
                            promptCachingBetaToolUseBlockParam
                        )
                    promptCachingBetaToolResultBlockParam != null ->
                        visitor.visitPromptCachingBetaToolResultBlockParam(
                            promptCachingBetaToolResultBlockParam
                        )
                    else -> visitor.unknown(_json)
                }
            }

            fun validate(): Block = apply {
                if (!validated) {
                    if (
                        promptCachingBetaTextBlockParam == null &&
                            promptCachingBetaImageBlockParam == null &&
                            promptCachingBetaToolUseBlockParam == null &&
                            promptCachingBetaToolResultBlockParam == null
                    ) {
                        throw AnthropicInvalidDataException("Unknown Block: $_json")
                    }
                    promptCachingBetaTextBlockParam?.validate()
                    promptCachingBetaImageBlockParam?.validate()
                    promptCachingBetaToolUseBlockParam?.validate()
                    promptCachingBetaToolResultBlockParam?.validate()
                    validated = true
                }
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return /* spotless:off */ other is Block && promptCachingBetaTextBlockParam == other.promptCachingBetaTextBlockParam && promptCachingBetaImageBlockParam == other.promptCachingBetaImageBlockParam && promptCachingBetaToolUseBlockParam == other.promptCachingBetaToolUseBlockParam && promptCachingBetaToolResultBlockParam == other.promptCachingBetaToolResultBlockParam /* spotless:on */
            }

            override fun hashCode(): Int = /* spotless:off */ Objects.hash(promptCachingBetaTextBlockParam, promptCachingBetaImageBlockParam, promptCachingBetaToolUseBlockParam, promptCachingBetaToolResultBlockParam) /* spotless:on */

            override fun toString(): String =
                when {
                    promptCachingBetaTextBlockParam != null ->
                        "Block{promptCachingBetaTextBlockParam=$promptCachingBetaTextBlockParam}"
                    promptCachingBetaImageBlockParam != null ->
                        "Block{promptCachingBetaImageBlockParam=$promptCachingBetaImageBlockParam}"
                    promptCachingBetaToolUseBlockParam != null ->
                        "Block{promptCachingBetaToolUseBlockParam=$promptCachingBetaToolUseBlockParam}"
                    promptCachingBetaToolResultBlockParam != null ->
                        "Block{promptCachingBetaToolResultBlockParam=$promptCachingBetaToolResultBlockParam}"
                    _json != null -> "Block{_unknown=$_json}"
                    else -> throw IllegalStateException("Invalid Block")
                }

            companion object {

                @JvmStatic
                fun ofPromptCachingBetaTextBlockParam(
                    promptCachingBetaTextBlockParam: PromptCachingBetaTextBlockParam
                ) = Block(promptCachingBetaTextBlockParam = promptCachingBetaTextBlockParam)

                @JvmStatic
                fun ofPromptCachingBetaImageBlockParam(
                    promptCachingBetaImageBlockParam: PromptCachingBetaImageBlockParam
                ) = Block(promptCachingBetaImageBlockParam = promptCachingBetaImageBlockParam)

                @JvmStatic
                fun ofPromptCachingBetaToolUseBlockParam(
                    promptCachingBetaToolUseBlockParam: PromptCachingBetaToolUseBlockParam
                ) = Block(promptCachingBetaToolUseBlockParam = promptCachingBetaToolUseBlockParam)

                @JvmStatic
                fun ofPromptCachingBetaToolResultBlockParam(
                    promptCachingBetaToolResultBlockParam: PromptCachingBetaToolResultBlockParam
                ) =
                    Block(
                        promptCachingBetaToolResultBlockParam =
                            promptCachingBetaToolResultBlockParam
                    )
            }

            interface Visitor<out T> {

                fun visitPromptCachingBetaTextBlockParam(
                    promptCachingBetaTextBlockParam: PromptCachingBetaTextBlockParam
                ): T

                fun visitPromptCachingBetaImageBlockParam(
                    promptCachingBetaImageBlockParam: PromptCachingBetaImageBlockParam
                ): T

                fun visitPromptCachingBetaToolUseBlockParam(
                    promptCachingBetaToolUseBlockParam: PromptCachingBetaToolUseBlockParam
                ): T

                fun visitPromptCachingBetaToolResultBlockParam(
                    promptCachingBetaToolResultBlockParam: PromptCachingBetaToolResultBlockParam
                ): T

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
                            tryDeserialize(
                                    node,
                                    jacksonTypeRef<PromptCachingBetaTextBlockParam>()
                                ) {
                                    it.validate()
                                }
                                ?.let {
                                    return Block(promptCachingBetaTextBlockParam = it, _json = json)
                                }
                        }
                        "image" -> {
                            tryDeserialize(
                                    node,
                                    jacksonTypeRef<PromptCachingBetaImageBlockParam>()
                                ) {
                                    it.validate()
                                }
                                ?.let {
                                    return Block(
                                        promptCachingBetaImageBlockParam = it,
                                        _json = json
                                    )
                                }
                        }
                        "tool_use" -> {
                            tryDeserialize(
                                    node,
                                    jacksonTypeRef<PromptCachingBetaToolUseBlockParam>()
                                ) {
                                    it.validate()
                                }
                                ?.let {
                                    return Block(
                                        promptCachingBetaToolUseBlockParam = it,
                                        _json = json
                                    )
                                }
                        }
                        "tool_result" -> {
                            tryDeserialize(
                                    node,
                                    jacksonTypeRef<PromptCachingBetaToolResultBlockParam>()
                                ) {
                                    it.validate()
                                }
                                ?.let {
                                    return Block(
                                        promptCachingBetaToolResultBlockParam = it,
                                        _json = json
                                    )
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
                        value.promptCachingBetaTextBlockParam != null ->
                            generator.writeObject(value.promptCachingBetaTextBlockParam)
                        value.promptCachingBetaImageBlockParam != null ->
                            generator.writeObject(value.promptCachingBetaImageBlockParam)
                        value.promptCachingBetaToolUseBlockParam != null ->
                            generator.writeObject(value.promptCachingBetaToolUseBlockParam)
                        value.promptCachingBetaToolResultBlockParam != null ->
                            generator.writeObject(value.promptCachingBetaToolResultBlockParam)
                        value._json != null -> generator.writeObject(value._json)
                        else -> throw IllegalStateException("Invalid Block")
                    }
                }
            }
        }
    }

    class Role
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Role && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()

        companion object {

            @JvmField val USER = of("user")

            @JvmField val ASSISTANT = of("assistant")

            @JvmStatic fun of(value: String) = Role(JsonField.of(value))
        }

        enum class Known {
            USER,
            ASSISTANT,
        }

        enum class Value {
            USER,
            ASSISTANT,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                USER -> Value.USER
                ASSISTANT -> Value.ASSISTANT
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                USER -> Known.USER
                ASSISTANT -> Known.ASSISTANT
                else -> throw AnthropicInvalidDataException("Unknown Role: $value")
            }

        fun asString(): String = _value().asStringOrThrow()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is PromptCachingBetaMessageParam && role == other.role && content == other.content && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(role, content, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "PromptCachingBetaMessageParam{role=$role, content=$content, additionalProperties=$additionalProperties}"
}
