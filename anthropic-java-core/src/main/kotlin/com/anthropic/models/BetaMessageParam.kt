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
class BetaMessageParam
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

    fun validate(): BetaMessageParam = apply {
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

    /** A builder for [BetaMessageParam]. */
    class Builder internal constructor() {

        private var content: JsonField<Content>? = null
        private var role: JsonField<Role>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaMessageParam: BetaMessageParam) = apply {
            content = betaMessageParam.content
            role = betaMessageParam.role
            additionalProperties = betaMessageParam.additionalProperties.toMutableMap()
        }

        fun content(content: Content) = content(JsonField.of(content))

        fun content(content: JsonField<Content>) = apply { this.content = content }

        fun content(string: String) = content(Content.ofString(string))

        fun contentOfBetaContentBlockParams(betaContentBlockParams: List<BetaContentBlockParam>) =
            content(Content.ofBetaContentBlockParams(betaContentBlockParams))

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

        fun build(): BetaMessageParam =
            BetaMessageParam(
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
        private val betaContentBlockParams: List<BetaContentBlockParam>? = null,
        private val _json: JsonValue? = null,
    ) {

        fun string(): Optional<String> = Optional.ofNullable(string)

        fun betaContentBlockParams(): Optional<List<BetaContentBlockParam>> =
            Optional.ofNullable(betaContentBlockParams)

        fun isString(): Boolean = string != null

        fun isBetaContentBlockParams(): Boolean = betaContentBlockParams != null

        fun asString(): String = string.getOrThrow("string")

        fun asBetaContentBlockParams(): List<BetaContentBlockParam> =
            betaContentBlockParams.getOrThrow("betaContentBlockParams")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                string != null -> visitor.visitString(string)
                betaContentBlockParams != null ->
                    visitor.visitBetaContentBlockParams(betaContentBlockParams)
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

                    override fun visitBetaContentBlockParams(
                        betaContentBlockParams: List<BetaContentBlockParam>
                    ) {
                        betaContentBlockParams.forEach { it.validate() }
                    }
                }
            )
            validated = true
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Content && string == other.string && betaContentBlockParams == other.betaContentBlockParams /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(string, betaContentBlockParams) /* spotless:on */

        override fun toString(): String =
            when {
                string != null -> "Content{string=$string}"
                betaContentBlockParams != null ->
                    "Content{betaContentBlockParams=$betaContentBlockParams}"
                _json != null -> "Content{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Content")
            }

        companion object {

            @JvmStatic fun ofString(string: String) = Content(string = string)

            @JvmStatic
            fun ofBetaContentBlockParams(betaContentBlockParams: List<BetaContentBlockParam>) =
                Content(betaContentBlockParams = betaContentBlockParams)
        }

        /**
         * An interface that defines how to map each variant of [Content] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitString(string: String): T

            fun visitBetaContentBlockParams(betaContentBlockParams: List<BetaContentBlockParam>): T

            /**
             * Maps an unknown variant of [Content] to a value of type [T].
             *
             * An instance of [Content] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Content: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Content>(Content::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Content {
                val json = JsonValue.fromJsonNode(node)

                tryDeserialize(node, jacksonTypeRef<String>())?.let {
                    return Content(string = it, _json = json)
                }
                tryDeserialize(node, jacksonTypeRef<List<BetaContentBlockParam>>()) {
                        it.forEach { it.validate() }
                    }
                    ?.let {
                        return Content(betaContentBlockParams = it, _json = json)
                    }

                return Content(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Content>(Content::class) {

            override fun serialize(
                value: Content,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.string != null -> generator.writeObject(value.string)
                    value.betaContentBlockParams != null ->
                        generator.writeObject(value.betaContentBlockParams)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Content")
                }
            }
        }
    }

    class Role @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val USER = of("user")

            @JvmField val ASSISTANT = of("assistant")

            @JvmStatic fun of(value: String) = Role(JsonField.of(value))
        }

        /** An enum containing [Role]'s known values. */
        enum class Known {
            USER,
            ASSISTANT,
        }

        /**
         * An enum containing [Role]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Role] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            USER,
            ASSISTANT,
            /** An enum member indicating that [Role] was instantiated with an unknown value. */
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
                USER -> Value.USER
                ASSISTANT -> Value.ASSISTANT
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
                USER -> Known.USER
                ASSISTANT -> Known.ASSISTANT
                else -> throw AnthropicInvalidDataException("Unknown Role: $value")
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

        return /* spotless:off */ other is BetaMessageParam && content == other.content && role == other.role && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(content, role, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMessageParam{content=$content, role=$role, additionalProperties=$additionalProperties}"
}
