// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
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
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaBashCodeExecutionToolResultBlock
private constructor(
    private val content: JsonField<Content>,
    private val toolUseId: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content") @ExcludeMissing content: JsonField<Content> = JsonMissing.of(),
        @JsonProperty("tool_use_id")
        @ExcludeMissing
        toolUseId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(content, toolUseId, type, mutableMapOf())

    fun toParam(): BetaBashCodeExecutionToolResultBlockParam =
        BetaBashCodeExecutionToolResultBlockParam.builder()
            .content(
                _content().map {
                    it.accept(
                        object :
                            BetaBashCodeExecutionToolResultBlock.Content.Visitor<
                                BetaBashCodeExecutionToolResultBlockParam.Content
                            > {
                            override fun visitBetaBashCodeExecutionToolResultError(
                                betaBashCodeExecutionToolResultError:
                                    BetaBashCodeExecutionToolResultError
                            ): BetaBashCodeExecutionToolResultBlockParam.Content =
                                BetaBashCodeExecutionToolResultBlockParam.Content
                                    .ofBetaBashCodeExecutionToolResultErrorParam(
                                        betaBashCodeExecutionToolResultError.toParam()
                                    )

                            override fun visitBetaBashCodeExecutionResultBlock(
                                betaBashCodeExecutionResultBlock: BetaBashCodeExecutionResultBlock
                            ): BetaBashCodeExecutionToolResultBlockParam.Content =
                                BetaBashCodeExecutionToolResultBlockParam.Content
                                    .ofBetaBashCodeExecutionResultBlockParam(
                                        betaBashCodeExecutionResultBlock.toParam()
                                    )
                        }
                    )
                }
            )
            .toolUseId(_toolUseId())
            .build()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun content(): Content = content.getRequired("content")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun toolUseId(): String = toolUseId.getRequired("tool_use_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("bash_code_execution_tool_result")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content") @ExcludeMissing fun _content(): JsonField<Content> = content

    /**
     * Returns the raw JSON value of [toolUseId].
     *
     * Unlike [toolUseId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tool_use_id") @ExcludeMissing fun _toolUseId(): JsonField<String> = toolUseId

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [BetaBashCodeExecutionToolResultBlock].
         *
         * The following fields are required:
         * ```java
         * .content()
         * .toolUseId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaBashCodeExecutionToolResultBlock]. */
    class Builder internal constructor() {

        private var content: JsonField<Content>? = null
        private var toolUseId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("bash_code_execution_tool_result")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaBashCodeExecutionToolResultBlock: BetaBashCodeExecutionToolResultBlock
        ) = apply {
            content = betaBashCodeExecutionToolResultBlock.content
            toolUseId = betaBashCodeExecutionToolResultBlock.toolUseId
            type = betaBashCodeExecutionToolResultBlock.type
            additionalProperties =
                betaBashCodeExecutionToolResultBlock.additionalProperties.toMutableMap()
        }

        fun content(content: Content) = content(JsonField.of(content))

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed [Content] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun content(content: JsonField<Content>) = apply { this.content = content }

        /**
         * Alias for calling [content] with
         * `Content.ofBetaBashCodeExecutionToolResultError(betaBashCodeExecutionToolResultError)`.
         */
        fun content(betaBashCodeExecutionToolResultError: BetaBashCodeExecutionToolResultError) =
            content(
                Content.ofBetaBashCodeExecutionToolResultError(betaBashCodeExecutionToolResultError)
            )

        /**
         * Alias for calling [content] with
         * `Content.ofBetaBashCodeExecutionResultBlock(betaBashCodeExecutionResultBlock)`.
         */
        fun content(betaBashCodeExecutionResultBlock: BetaBashCodeExecutionResultBlock) =
            content(Content.ofBetaBashCodeExecutionResultBlock(betaBashCodeExecutionResultBlock))

        fun toolUseId(toolUseId: String) = toolUseId(JsonField.of(toolUseId))

        /**
         * Sets [Builder.toolUseId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.toolUseId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun toolUseId(toolUseId: JsonField<String>) = apply { this.toolUseId = toolUseId }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("bash_code_execution_tool_result")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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

        /**
         * Returns an immutable instance of [BetaBashCodeExecutionToolResultBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .content()
         * .toolUseId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaBashCodeExecutionToolResultBlock =
            BetaBashCodeExecutionToolResultBlock(
                checkRequired("content", content),
                checkRequired("toolUseId", toolUseId),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaBashCodeExecutionToolResultBlock = apply {
        if (validated) {
            return@apply
        }

        content().validate()
        toolUseId()
        _type().let {
            if (it != JsonValue.from("bash_code_execution_tool_result")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
        (content.asKnown().getOrNull()?.validity() ?: 0) +
            (if (toolUseId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("bash_code_execution_tool_result")) 1 else 0 }

    @JsonDeserialize(using = Content.Deserializer::class)
    @JsonSerialize(using = Content.Serializer::class)
    class Content
    private constructor(
        private val betaBashCodeExecutionToolResultError: BetaBashCodeExecutionToolResultError? =
            null,
        private val betaBashCodeExecutionResultBlock: BetaBashCodeExecutionResultBlock? = null,
        private val _json: JsonValue? = null,
    ) {

        fun betaBashCodeExecutionToolResultError(): Optional<BetaBashCodeExecutionToolResultError> =
            Optional.ofNullable(betaBashCodeExecutionToolResultError)

        fun betaBashCodeExecutionResultBlock(): Optional<BetaBashCodeExecutionResultBlock> =
            Optional.ofNullable(betaBashCodeExecutionResultBlock)

        fun isBetaBashCodeExecutionToolResultError(): Boolean =
            betaBashCodeExecutionToolResultError != null

        fun isBetaBashCodeExecutionResultBlock(): Boolean = betaBashCodeExecutionResultBlock != null

        fun asBetaBashCodeExecutionToolResultError(): BetaBashCodeExecutionToolResultError =
            betaBashCodeExecutionToolResultError.getOrThrow("betaBashCodeExecutionToolResultError")

        fun asBetaBashCodeExecutionResultBlock(): BetaBashCodeExecutionResultBlock =
            betaBashCodeExecutionResultBlock.getOrThrow("betaBashCodeExecutionResultBlock")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                betaBashCodeExecutionToolResultError != null ->
                    visitor.visitBetaBashCodeExecutionToolResultError(
                        betaBashCodeExecutionToolResultError
                    )
                betaBashCodeExecutionResultBlock != null ->
                    visitor.visitBetaBashCodeExecutionResultBlock(betaBashCodeExecutionResultBlock)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Content = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBetaBashCodeExecutionToolResultError(
                        betaBashCodeExecutionToolResultError: BetaBashCodeExecutionToolResultError
                    ) {
                        betaBashCodeExecutionToolResultError.validate()
                    }

                    override fun visitBetaBashCodeExecutionResultBlock(
                        betaBashCodeExecutionResultBlock: BetaBashCodeExecutionResultBlock
                    ) {
                        betaBashCodeExecutionResultBlock.validate()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitBetaBashCodeExecutionToolResultError(
                        betaBashCodeExecutionToolResultError: BetaBashCodeExecutionToolResultError
                    ) = betaBashCodeExecutionToolResultError.validity()

                    override fun visitBetaBashCodeExecutionResultBlock(
                        betaBashCodeExecutionResultBlock: BetaBashCodeExecutionResultBlock
                    ) = betaBashCodeExecutionResultBlock.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Content &&
                betaBashCodeExecutionToolResultError ==
                    other.betaBashCodeExecutionToolResultError &&
                betaBashCodeExecutionResultBlock == other.betaBashCodeExecutionResultBlock
        }

        override fun hashCode(): Int =
            Objects.hash(betaBashCodeExecutionToolResultError, betaBashCodeExecutionResultBlock)

        override fun toString(): String =
            when {
                betaBashCodeExecutionToolResultError != null ->
                    "Content{betaBashCodeExecutionToolResultError=$betaBashCodeExecutionToolResultError}"
                betaBashCodeExecutionResultBlock != null ->
                    "Content{betaBashCodeExecutionResultBlock=$betaBashCodeExecutionResultBlock}"
                _json != null -> "Content{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Content")
            }

        companion object {

            @JvmStatic
            fun ofBetaBashCodeExecutionToolResultError(
                betaBashCodeExecutionToolResultError: BetaBashCodeExecutionToolResultError
            ) = Content(betaBashCodeExecutionToolResultError = betaBashCodeExecutionToolResultError)

            @JvmStatic
            fun ofBetaBashCodeExecutionResultBlock(
                betaBashCodeExecutionResultBlock: BetaBashCodeExecutionResultBlock
            ) = Content(betaBashCodeExecutionResultBlock = betaBashCodeExecutionResultBlock)
        }

        /**
         * An interface that defines how to map each variant of [Content] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitBetaBashCodeExecutionToolResultError(
                betaBashCodeExecutionToolResultError: BetaBashCodeExecutionToolResultError
            ): T

            fun visitBetaBashCodeExecutionResultBlock(
                betaBashCodeExecutionResultBlock: BetaBashCodeExecutionResultBlock
            ): T

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

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(
                                    node,
                                    jacksonTypeRef<BetaBashCodeExecutionToolResultError>(),
                                )
                                ?.let {
                                    Content(betaBashCodeExecutionToolResultError = it, _json = json)
                                },
                            tryDeserialize(node, jacksonTypeRef<BetaBashCodeExecutionResultBlock>())
                                ?.let {
                                    Content(betaBashCodeExecutionResultBlock = it, _json = json)
                                },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from boolean).
                    0 -> Content(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<Content>(Content::class) {

            override fun serialize(
                value: Content,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.betaBashCodeExecutionToolResultError != null ->
                        generator.writeObject(value.betaBashCodeExecutionToolResultError)
                    value.betaBashCodeExecutionResultBlock != null ->
                        generator.writeObject(value.betaBashCodeExecutionResultBlock)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Content")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaBashCodeExecutionToolResultBlock &&
            content == other.content &&
            toolUseId == other.toolUseId &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(content, toolUseId, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaBashCodeExecutionToolResultBlock{content=$content, toolUseId=$toolUseId, type=$type, additionalProperties=$additionalProperties}"
}
