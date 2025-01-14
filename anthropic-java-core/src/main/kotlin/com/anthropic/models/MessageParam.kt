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
class MessageParam
@JsonCreator
private constructor(
    @JsonProperty("content")
    @ExcludeMissing
    private val content: JsonField<Content> = JsonMissing.of(),
    @JsonProperty("role") @ExcludeMissing private val role: JsonField<Role> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun content(): Content = content.getRequired("content")

    fun role(): Role = role.getRequired("role")

    @JsonProperty("content") @ExcludeMissing fun _content(): JsonField<Content> = content

    @JsonProperty("role") @ExcludeMissing fun _role(): JsonField<Role> = role

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): MessageParam = apply {
        if (validated) {
            return@apply
        }

        content().validate()
        role()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var content: JsonField<Content>? = null
        private var role: JsonField<Role>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(messageParam: MessageParam) = apply {
            content = messageParam.content
            role = messageParam.role
            additionalProperties = messageParam.additionalProperties.toMutableMap()
        }

        fun content(content: Content) = content(JsonField.of(content))

        fun content(content: JsonField<Content>) = apply { this.content = content }

        fun content(string: String) = content(Content.ofString(string))

        fun contentOfContentBlockParams(contentBlockParams: List<ContentBlockParam>) =
            content(Content.ofContentBlockParams(contentBlockParams))

        fun role(role: Role) = role(JsonField.of(role))

        fun role(role: JsonField<Role>) = apply { this.role = role }

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

        fun build(): MessageParam =
            MessageParam(
                checkRequired("content", content),
                checkRequired("role", role),
                additionalProperties.toImmutable(),
            )
    }

    @JsonDeserialize(using = Content.Deserializer::class)
    @JsonSerialize(using = Content.Serializer::class)
    class Content
    private constructor(
        private val string: String? = null,
        private val contentBlockParams: List<ContentBlockParam>? = null,
        private val _json: JsonValue? = null,
    ) {

        fun string(): Optional<String> = Optional.ofNullable(string)

        fun contentBlockParams(): Optional<List<ContentBlockParam>> =
            Optional.ofNullable(contentBlockParams)

        fun isString(): Boolean = string != null

        fun isContentBlockParams(): Boolean = contentBlockParams != null

        fun asString(): String = string.getOrThrow("string")

        fun asContentBlockParams(): List<ContentBlockParam> =
            contentBlockParams.getOrThrow("contentBlockParams")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                string != null -> visitor.visitString(string)
                contentBlockParams != null -> visitor.visitContentBlockParams(contentBlockParams)
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

                    override fun visitContentBlockParams(
                        contentBlockParams: List<ContentBlockParam>
                    ) {
                        contentBlockParams.forEach { it.validate() }
                    }
                }
            )
            validated = true
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Content && string == other.string && contentBlockParams == other.contentBlockParams /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(string, contentBlockParams) /* spotless:on */

        override fun toString(): String =
            when {
                string != null -> "Content{string=$string}"
                contentBlockParams != null -> "Content{contentBlockParams=$contentBlockParams}"
                _json != null -> "Content{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Content")
            }

        companion object {

            @JvmStatic fun ofString(string: String) = Content(string = string)

            @JvmStatic
            fun ofContentBlockParams(contentBlockParams: List<ContentBlockParam>) =
                Content(contentBlockParams = contentBlockParams)
        }

        interface Visitor<out T> {

            fun visitString(string: String): T

            fun visitContentBlockParams(contentBlockParams: List<ContentBlockParam>): T

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
                tryDeserialize(node, jacksonTypeRef<List<ContentBlockParam>>()) {
                        it.forEach { it.validate() }
                    }
                    ?.let {
                        return Content(contentBlockParams = it, _json = json)
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
                    value.contentBlockParams != null ->
                        generator.writeObject(value.contentBlockParams)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Content")
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

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Role && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is MessageParam && content == other.content && role == other.role && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(content, role, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "MessageParam{content=$content, role=$role, additionalProperties=$additionalProperties}"
}
