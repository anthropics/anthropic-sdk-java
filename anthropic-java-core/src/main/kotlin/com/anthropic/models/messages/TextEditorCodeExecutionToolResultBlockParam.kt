// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

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

class TextEditorCodeExecutionToolResultBlockParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val content: JsonField<Content>,
    private val toolUseId: JsonField<String>,
    private val type: JsonValue,
    private val cacheControl: JsonField<CacheControlEphemeral>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content") @ExcludeMissing content: JsonField<Content> = JsonMissing.of(),
        @JsonProperty("tool_use_id")
        @ExcludeMissing
        toolUseId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
    ) : this(content, toolUseId, type, cacheControl, mutableMapOf())

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
     * JsonValue.from("text_editor_code_execution_tool_result")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Create a cache control breakpoint at this content block.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<CacheControlEphemeral> = cacheControl.getOptional("cache_control")

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

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<CacheControlEphemeral> = cacheControl

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
         * [TextEditorCodeExecutionToolResultBlockParam].
         *
         * The following fields are required:
         * ```java
         * .content()
         * .toolUseId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [TextEditorCodeExecutionToolResultBlockParam]. */
    class Builder internal constructor() {

        private var content: JsonField<Content>? = null
        private var toolUseId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("text_editor_code_execution_tool_result")
        private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            textEditorCodeExecutionToolResultBlockParam: TextEditorCodeExecutionToolResultBlockParam
        ) = apply {
            content = textEditorCodeExecutionToolResultBlockParam.content
            toolUseId = textEditorCodeExecutionToolResultBlockParam.toolUseId
            type = textEditorCodeExecutionToolResultBlockParam.type
            cacheControl = textEditorCodeExecutionToolResultBlockParam.cacheControl
            additionalProperties =
                textEditorCodeExecutionToolResultBlockParam.additionalProperties.toMutableMap()
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
         * `Content.ofTextEditorCodeExecutionToolResultErrorParam(textEditorCodeExecutionToolResultErrorParam)`.
         */
        fun content(
            textEditorCodeExecutionToolResultErrorParam: TextEditorCodeExecutionToolResultErrorParam
        ) =
            content(
                Content.ofTextEditorCodeExecutionToolResultErrorParam(
                    textEditorCodeExecutionToolResultErrorParam
                )
            )

        /**
         * Alias for calling [content] with `textEditorCodeExecutionToolResultErrorParam.toParam()`.
         */
        fun content(
            textEditorCodeExecutionToolResultErrorParam: TextEditorCodeExecutionToolResultError
        ) = content(textEditorCodeExecutionToolResultErrorParam.toParam())

        /**
         * Alias for calling [content] with
         * `Content.ofTextEditorCodeExecutionViewResultBlockParam(textEditorCodeExecutionViewResultBlockParam)`.
         */
        fun content(
            textEditorCodeExecutionViewResultBlockParam: TextEditorCodeExecutionViewResultBlockParam
        ) =
            content(
                Content.ofTextEditorCodeExecutionViewResultBlockParam(
                    textEditorCodeExecutionViewResultBlockParam
                )
            )

        /**
         * Alias for calling [content] with `textEditorCodeExecutionViewResultBlockParam.toParam()`.
         */
        fun content(
            textEditorCodeExecutionViewResultBlockParam: TextEditorCodeExecutionViewResultBlock
        ) = content(textEditorCodeExecutionViewResultBlockParam.toParam())

        /**
         * Alias for calling [content] with
         * `Content.ofTextEditorCodeExecutionCreateResultBlockParam(textEditorCodeExecutionCreateResultBlockParam)`.
         */
        fun content(
            textEditorCodeExecutionCreateResultBlockParam:
                TextEditorCodeExecutionCreateResultBlockParam
        ) =
            content(
                Content.ofTextEditorCodeExecutionCreateResultBlockParam(
                    textEditorCodeExecutionCreateResultBlockParam
                )
            )

        /**
         * Alias for calling [content] with
         * `textEditorCodeExecutionCreateResultBlockParam.toParam()`.
         */
        fun content(
            textEditorCodeExecutionCreateResultBlockParam: TextEditorCodeExecutionCreateResultBlock
        ) = content(textEditorCodeExecutionCreateResultBlockParam.toParam())

        /**
         * Alias for calling [content] with
         * `Content.ofTextEditorCodeExecutionStrReplaceResultBlockParam(textEditorCodeExecutionStrReplaceResultBlockParam)`.
         */
        fun content(
            textEditorCodeExecutionStrReplaceResultBlockParam:
                TextEditorCodeExecutionStrReplaceResultBlockParam
        ) =
            content(
                Content.ofTextEditorCodeExecutionStrReplaceResultBlockParam(
                    textEditorCodeExecutionStrReplaceResultBlockParam
                )
            )

        /**
         * Alias for calling [content] with
         * `textEditorCodeExecutionStrReplaceResultBlockParam.toParam()`.
         */
        fun content(
            textEditorCodeExecutionStrReplaceResultBlockParam:
                TextEditorCodeExecutionStrReplaceResultBlock
        ) = content(textEditorCodeExecutionStrReplaceResultBlockParam.toParam())

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
         * JsonValue.from("text_editor_code_execution_tool_result")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Create a cache control breakpoint at this content block. */
        fun cacheControl(cacheControl: CacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<CacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed [CacheControlEphemeral]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun cacheControl(cacheControl: JsonField<CacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

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
         * Returns an immutable instance of [TextEditorCodeExecutionToolResultBlockParam].
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
        fun build(): TextEditorCodeExecutionToolResultBlockParam =
            TextEditorCodeExecutionToolResultBlockParam(
                checkRequired("content", content),
                checkRequired("toolUseId", toolUseId),
                type,
                cacheControl,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): TextEditorCodeExecutionToolResultBlockParam = apply {
        if (validated) {
            return@apply
        }

        content().validate()
        toolUseId()
        _type().let {
            if (it != JsonValue.from("text_editor_code_execution_tool_result")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
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
            type.let {
                if (it == JsonValue.from("text_editor_code_execution_tool_result")) 1 else 0
            } +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0)

    @JsonDeserialize(using = Content.Deserializer::class)
    @JsonSerialize(using = Content.Serializer::class)
    class Content
    private constructor(
        private val textEditorCodeExecutionToolResultErrorParam:
            TextEditorCodeExecutionToolResultErrorParam? =
            null,
        private val textEditorCodeExecutionViewResultBlockParam:
            TextEditorCodeExecutionViewResultBlockParam? =
            null,
        private val textEditorCodeExecutionCreateResultBlockParam:
            TextEditorCodeExecutionCreateResultBlockParam? =
            null,
        private val textEditorCodeExecutionStrReplaceResultBlockParam:
            TextEditorCodeExecutionStrReplaceResultBlockParam? =
            null,
        private val _json: JsonValue? = null,
    ) {

        fun textEditorCodeExecutionToolResultErrorParam():
            Optional<TextEditorCodeExecutionToolResultErrorParam> =
            Optional.ofNullable(textEditorCodeExecutionToolResultErrorParam)

        fun textEditorCodeExecutionViewResultBlockParam():
            Optional<TextEditorCodeExecutionViewResultBlockParam> =
            Optional.ofNullable(textEditorCodeExecutionViewResultBlockParam)

        fun textEditorCodeExecutionCreateResultBlockParam():
            Optional<TextEditorCodeExecutionCreateResultBlockParam> =
            Optional.ofNullable(textEditorCodeExecutionCreateResultBlockParam)

        fun textEditorCodeExecutionStrReplaceResultBlockParam():
            Optional<TextEditorCodeExecutionStrReplaceResultBlockParam> =
            Optional.ofNullable(textEditorCodeExecutionStrReplaceResultBlockParam)

        fun isTextEditorCodeExecutionToolResultErrorParam(): Boolean =
            textEditorCodeExecutionToolResultErrorParam != null

        fun isTextEditorCodeExecutionViewResultBlockParam(): Boolean =
            textEditorCodeExecutionViewResultBlockParam != null

        fun isTextEditorCodeExecutionCreateResultBlockParam(): Boolean =
            textEditorCodeExecutionCreateResultBlockParam != null

        fun isTextEditorCodeExecutionStrReplaceResultBlockParam(): Boolean =
            textEditorCodeExecutionStrReplaceResultBlockParam != null

        fun asTextEditorCodeExecutionToolResultErrorParam():
            TextEditorCodeExecutionToolResultErrorParam =
            textEditorCodeExecutionToolResultErrorParam.getOrThrow(
                "textEditorCodeExecutionToolResultErrorParam"
            )

        fun asTextEditorCodeExecutionViewResultBlockParam():
            TextEditorCodeExecutionViewResultBlockParam =
            textEditorCodeExecutionViewResultBlockParam.getOrThrow(
                "textEditorCodeExecutionViewResultBlockParam"
            )

        fun asTextEditorCodeExecutionCreateResultBlockParam():
            TextEditorCodeExecutionCreateResultBlockParam =
            textEditorCodeExecutionCreateResultBlockParam.getOrThrow(
                "textEditorCodeExecutionCreateResultBlockParam"
            )

        fun asTextEditorCodeExecutionStrReplaceResultBlockParam():
            TextEditorCodeExecutionStrReplaceResultBlockParam =
            textEditorCodeExecutionStrReplaceResultBlockParam.getOrThrow(
                "textEditorCodeExecutionStrReplaceResultBlockParam"
            )

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                textEditorCodeExecutionToolResultErrorParam != null ->
                    visitor.visitTextEditorCodeExecutionToolResultErrorParam(
                        textEditorCodeExecutionToolResultErrorParam
                    )
                textEditorCodeExecutionViewResultBlockParam != null ->
                    visitor.visitTextEditorCodeExecutionViewResultBlockParam(
                        textEditorCodeExecutionViewResultBlockParam
                    )
                textEditorCodeExecutionCreateResultBlockParam != null ->
                    visitor.visitTextEditorCodeExecutionCreateResultBlockParam(
                        textEditorCodeExecutionCreateResultBlockParam
                    )
                textEditorCodeExecutionStrReplaceResultBlockParam != null ->
                    visitor.visitTextEditorCodeExecutionStrReplaceResultBlockParam(
                        textEditorCodeExecutionStrReplaceResultBlockParam
                    )
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Content = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitTextEditorCodeExecutionToolResultErrorParam(
                        textEditorCodeExecutionToolResultErrorParam:
                            TextEditorCodeExecutionToolResultErrorParam
                    ) {
                        textEditorCodeExecutionToolResultErrorParam.validate()
                    }

                    override fun visitTextEditorCodeExecutionViewResultBlockParam(
                        textEditorCodeExecutionViewResultBlockParam:
                            TextEditorCodeExecutionViewResultBlockParam
                    ) {
                        textEditorCodeExecutionViewResultBlockParam.validate()
                    }

                    override fun visitTextEditorCodeExecutionCreateResultBlockParam(
                        textEditorCodeExecutionCreateResultBlockParam:
                            TextEditorCodeExecutionCreateResultBlockParam
                    ) {
                        textEditorCodeExecutionCreateResultBlockParam.validate()
                    }

                    override fun visitTextEditorCodeExecutionStrReplaceResultBlockParam(
                        textEditorCodeExecutionStrReplaceResultBlockParam:
                            TextEditorCodeExecutionStrReplaceResultBlockParam
                    ) {
                        textEditorCodeExecutionStrReplaceResultBlockParam.validate()
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
                    override fun visitTextEditorCodeExecutionToolResultErrorParam(
                        textEditorCodeExecutionToolResultErrorParam:
                            TextEditorCodeExecutionToolResultErrorParam
                    ) = textEditorCodeExecutionToolResultErrorParam.validity()

                    override fun visitTextEditorCodeExecutionViewResultBlockParam(
                        textEditorCodeExecutionViewResultBlockParam:
                            TextEditorCodeExecutionViewResultBlockParam
                    ) = textEditorCodeExecutionViewResultBlockParam.validity()

                    override fun visitTextEditorCodeExecutionCreateResultBlockParam(
                        textEditorCodeExecutionCreateResultBlockParam:
                            TextEditorCodeExecutionCreateResultBlockParam
                    ) = textEditorCodeExecutionCreateResultBlockParam.validity()

                    override fun visitTextEditorCodeExecutionStrReplaceResultBlockParam(
                        textEditorCodeExecutionStrReplaceResultBlockParam:
                            TextEditorCodeExecutionStrReplaceResultBlockParam
                    ) = textEditorCodeExecutionStrReplaceResultBlockParam.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Content &&
                textEditorCodeExecutionToolResultErrorParam ==
                    other.textEditorCodeExecutionToolResultErrorParam &&
                textEditorCodeExecutionViewResultBlockParam ==
                    other.textEditorCodeExecutionViewResultBlockParam &&
                textEditorCodeExecutionCreateResultBlockParam ==
                    other.textEditorCodeExecutionCreateResultBlockParam &&
                textEditorCodeExecutionStrReplaceResultBlockParam ==
                    other.textEditorCodeExecutionStrReplaceResultBlockParam
        }

        override fun hashCode(): Int =
            Objects.hash(
                textEditorCodeExecutionToolResultErrorParam,
                textEditorCodeExecutionViewResultBlockParam,
                textEditorCodeExecutionCreateResultBlockParam,
                textEditorCodeExecutionStrReplaceResultBlockParam,
            )

        override fun toString(): String =
            when {
                textEditorCodeExecutionToolResultErrorParam != null ->
                    "Content{textEditorCodeExecutionToolResultErrorParam=$textEditorCodeExecutionToolResultErrorParam}"
                textEditorCodeExecutionViewResultBlockParam != null ->
                    "Content{textEditorCodeExecutionViewResultBlockParam=$textEditorCodeExecutionViewResultBlockParam}"
                textEditorCodeExecutionCreateResultBlockParam != null ->
                    "Content{textEditorCodeExecutionCreateResultBlockParam=$textEditorCodeExecutionCreateResultBlockParam}"
                textEditorCodeExecutionStrReplaceResultBlockParam != null ->
                    "Content{textEditorCodeExecutionStrReplaceResultBlockParam=$textEditorCodeExecutionStrReplaceResultBlockParam}"
                _json != null -> "Content{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Content")
            }

        companion object {

            @JvmStatic
            fun ofTextEditorCodeExecutionToolResultErrorParam(
                textEditorCodeExecutionToolResultErrorParam:
                    TextEditorCodeExecutionToolResultErrorParam
            ) =
                Content(
                    textEditorCodeExecutionToolResultErrorParam =
                        textEditorCodeExecutionToolResultErrorParam
                )

            @JvmStatic
            fun ofTextEditorCodeExecutionViewResultBlockParam(
                textEditorCodeExecutionViewResultBlockParam:
                    TextEditorCodeExecutionViewResultBlockParam
            ) =
                Content(
                    textEditorCodeExecutionViewResultBlockParam =
                        textEditorCodeExecutionViewResultBlockParam
                )

            @JvmStatic
            fun ofTextEditorCodeExecutionCreateResultBlockParam(
                textEditorCodeExecutionCreateResultBlockParam:
                    TextEditorCodeExecutionCreateResultBlockParam
            ) =
                Content(
                    textEditorCodeExecutionCreateResultBlockParam =
                        textEditorCodeExecutionCreateResultBlockParam
                )

            @JvmStatic
            fun ofTextEditorCodeExecutionStrReplaceResultBlockParam(
                textEditorCodeExecutionStrReplaceResultBlockParam:
                    TextEditorCodeExecutionStrReplaceResultBlockParam
            ) =
                Content(
                    textEditorCodeExecutionStrReplaceResultBlockParam =
                        textEditorCodeExecutionStrReplaceResultBlockParam
                )
        }

        /**
         * An interface that defines how to map each variant of [Content] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitTextEditorCodeExecutionToolResultErrorParam(
                textEditorCodeExecutionToolResultErrorParam:
                    TextEditorCodeExecutionToolResultErrorParam
            ): T

            fun visitTextEditorCodeExecutionViewResultBlockParam(
                textEditorCodeExecutionViewResultBlockParam:
                    TextEditorCodeExecutionViewResultBlockParam
            ): T

            fun visitTextEditorCodeExecutionCreateResultBlockParam(
                textEditorCodeExecutionCreateResultBlockParam:
                    TextEditorCodeExecutionCreateResultBlockParam
            ): T

            fun visitTextEditorCodeExecutionStrReplaceResultBlockParam(
                textEditorCodeExecutionStrReplaceResultBlockParam:
                    TextEditorCodeExecutionStrReplaceResultBlockParam
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
                                    jacksonTypeRef<TextEditorCodeExecutionToolResultErrorParam>(),
                                )
                                ?.let {
                                    Content(
                                        textEditorCodeExecutionToolResultErrorParam = it,
                                        _json = json,
                                    )
                                },
                            tryDeserialize(
                                    node,
                                    jacksonTypeRef<TextEditorCodeExecutionViewResultBlockParam>(),
                                )
                                ?.let {
                                    Content(
                                        textEditorCodeExecutionViewResultBlockParam = it,
                                        _json = json,
                                    )
                                },
                            tryDeserialize(
                                    node,
                                    jacksonTypeRef<TextEditorCodeExecutionCreateResultBlockParam>(),
                                )
                                ?.let {
                                    Content(
                                        textEditorCodeExecutionCreateResultBlockParam = it,
                                        _json = json,
                                    )
                                },
                            tryDeserialize(
                                    node,
                                    jacksonTypeRef<
                                        TextEditorCodeExecutionStrReplaceResultBlockParam
                                    >(),
                                )
                                ?.let {
                                    Content(
                                        textEditorCodeExecutionStrReplaceResultBlockParam = it,
                                        _json = json,
                                    )
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
                    value.textEditorCodeExecutionToolResultErrorParam != null ->
                        generator.writeObject(value.textEditorCodeExecutionToolResultErrorParam)
                    value.textEditorCodeExecutionViewResultBlockParam != null ->
                        generator.writeObject(value.textEditorCodeExecutionViewResultBlockParam)
                    value.textEditorCodeExecutionCreateResultBlockParam != null ->
                        generator.writeObject(value.textEditorCodeExecutionCreateResultBlockParam)
                    value.textEditorCodeExecutionStrReplaceResultBlockParam != null ->
                        generator.writeObject(
                            value.textEditorCodeExecutionStrReplaceResultBlockParam
                        )
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

        return other is TextEditorCodeExecutionToolResultBlockParam &&
            content == other.content &&
            toolUseId == other.toolUseId &&
            type == other.type &&
            cacheControl == other.cacheControl &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(content, toolUseId, type, cacheControl, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "TextEditorCodeExecutionToolResultBlockParam{content=$content, toolUseId=$toolUseId, type=$type, cacheControl=$cacheControl, additionalProperties=$additionalProperties}"
}
