// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
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
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * System instructions that appear mid-conversation.
 *
 * Use this block to provide or update system-level instructions at a specific point in the
 * conversation, rather than only via the top-level `system` parameter.
 */
class BetaMidConversationSystemBlockParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val content: JsonField<List<Content>>,
    private val type: JsonValue,
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content")
        @ExcludeMissing
        content: JsonField<List<Content>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
    ) : this(content, type, cacheControl, mutableMapOf())

    /**
     * System instruction text blocks.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun content(): List<Content> = content.getRequired("content")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("mid_conv_system")
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
    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        cacheControl.getOptional("cache_control")

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content") @ExcludeMissing fun _content(): JsonField<List<Content>> = content

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = cacheControl

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
         * [BetaMidConversationSystemBlockParam].
         *
         * The following fields are required:
         * ```java
         * .content()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaMidConversationSystemBlockParam] with the required
         * [content] set to the given value.
         */
        @JvmStatic fun of(content: List<Content>) = builder().content(content).build()
    }

    /** A builder for [BetaMidConversationSystemBlockParam]. */
    class Builder internal constructor() {

        private var content: JsonField<MutableList<Content>>? = null
        private var type: JsonValue = JsonValue.from("mid_conv_system")
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaMidConversationSystemBlockParam: BetaMidConversationSystemBlockParam
        ) = apply {
            content =
                betaMidConversationSystemBlockParam.content
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            type = betaMidConversationSystemBlockParam.type
            cacheControl = betaMidConversationSystemBlockParam.cacheControl
            additionalProperties =
                betaMidConversationSystemBlockParam.additionalProperties.toMutableMap()
        }

        /** System instruction text blocks. */
        fun content(content: List<Content>) = content(JsonField.of(content))

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed `List<Content>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun content(content: JsonField<List<Content>>) = apply {
            this.content = content.map { it.toMutableList() }
        }

        /**
         * Adds a single [Content] to [Builder.content].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addContent(content: Content) = apply {
            this.content =
                (this.content ?: JsonField.of(mutableListOf())).also {
                    checkKnown("content", it).add(content)
                }
        }

        /** Alias for calling [addContent] with `Content.ofText(text)`. */
        fun addContent(text: BetaTextBlockParam) = addContent(Content.ofText(text))

        /** Alias for calling [addContent] with `text.toParam()`. */
        fun addContent(text: BetaTextBlock) = addContent(text.toParam())

        /**
         * Alias for calling [addContent] with the following:
         * ```java
         * BetaTextBlockParam.builder()
         *     .text(text)
         *     .build()
         * ```
         */
        fun addTextContent(text: String) =
            addContent(BetaTextBlockParam.builder().text(text).build())

        /** Alias for calling [addContent] with `Content.ofToolAddition(toolAddition)`. */
        fun addContent(toolAddition: BetaRequestToolAdditionBlock) =
            addContent(Content.ofToolAddition(toolAddition))

        /**
         * Alias for calling [addContent] with the following:
         * ```java
         * BetaRequestToolAdditionBlock.builder()
         *     .tool(tool)
         *     .build()
         * ```
         */
        fun addToolAdditionContent(tool: BetaRequestToolAdditionBlock.Tool) =
            addContent(BetaRequestToolAdditionBlock.builder().tool(tool).build())

        /**
         * Alias for calling [addToolAdditionContent] with
         * `BetaRequestToolAdditionBlock.Tool.ofReference(reference)`.
         */
        fun addToolAdditionContent(reference: BetaToolChangeToolReference) =
            addToolAdditionContent(BetaRequestToolAdditionBlock.Tool.ofReference(reference))

        /**
         * Alias for calling [addToolAdditionContent] with the following:
         * ```java
         * BetaToolChangeToolReference.builder()
         *     .name(name)
         *     .build()
         * ```
         */
        fun addReferenceToolAdditionContent(name: String) =
            addToolAdditionContent(BetaToolChangeToolReference.builder().name(name).build())

        /**
         * Alias for calling [addToolAdditionContent] with
         * `BetaRequestToolAdditionBlock.Tool.ofMcpToolReference(mcpToolReference)`.
         */
        fun addToolAdditionContent(mcpToolReference: BetaToolChangeMcpToolReference) =
            addToolAdditionContent(
                BetaRequestToolAdditionBlock.Tool.ofMcpToolReference(mcpToolReference)
            )

        /**
         * Alias for calling [addToolAdditionContent] with
         * `BetaRequestToolAdditionBlock.Tool.ofMcpToolsetReference(mcpToolsetReference)`.
         */
        fun addToolAdditionContent(mcpToolsetReference: BetaToolChangeMcpToolsetReference) =
            addToolAdditionContent(
                BetaRequestToolAdditionBlock.Tool.ofMcpToolsetReference(mcpToolsetReference)
            )

        /**
         * Alias for calling [addToolAdditionContent] with the following:
         * ```java
         * BetaToolChangeMcpToolsetReference.builder()
         *     .serverName(serverName)
         *     .build()
         * ```
         */
        fun addMcpToolsetReferenceToolAdditionContent(serverName: String) =
            addToolAdditionContent(
                BetaToolChangeMcpToolsetReference.builder().serverName(serverName).build()
            )

        /** Alias for calling [addContent] with `Content.ofToolRemoval(toolRemoval)`. */
        fun addContent(toolRemoval: BetaRequestToolRemovalBlock) =
            addContent(Content.ofToolRemoval(toolRemoval))

        /**
         * Alias for calling [addContent] with the following:
         * ```java
         * BetaRequestToolRemovalBlock.builder()
         *     .tool(tool)
         *     .build()
         * ```
         */
        fun addToolRemovalContent(tool: BetaRequestToolRemovalBlock.Tool) =
            addContent(BetaRequestToolRemovalBlock.builder().tool(tool).build())

        /**
         * Alias for calling [addToolRemovalContent] with
         * `BetaRequestToolRemovalBlock.Tool.ofReference(reference)`.
         */
        fun addToolRemovalContent(reference: BetaToolChangeToolReference) =
            addToolRemovalContent(BetaRequestToolRemovalBlock.Tool.ofReference(reference))

        /**
         * Alias for calling [addToolRemovalContent] with the following:
         * ```java
         * BetaToolChangeToolReference.builder()
         *     .name(name)
         *     .build()
         * ```
         */
        fun addReferenceToolRemovalContent(name: String) =
            addToolRemovalContent(BetaToolChangeToolReference.builder().name(name).build())

        /**
         * Alias for calling [addToolRemovalContent] with
         * `BetaRequestToolRemovalBlock.Tool.ofMcpToolReference(mcpToolReference)`.
         */
        fun addToolRemovalContent(mcpToolReference: BetaToolChangeMcpToolReference) =
            addToolRemovalContent(
                BetaRequestToolRemovalBlock.Tool.ofMcpToolReference(mcpToolReference)
            )

        /**
         * Alias for calling [addToolRemovalContent] with
         * `BetaRequestToolRemovalBlock.Tool.ofMcpToolsetReference(mcpToolsetReference)`.
         */
        fun addToolRemovalContent(mcpToolsetReference: BetaToolChangeMcpToolsetReference) =
            addToolRemovalContent(
                BetaRequestToolRemovalBlock.Tool.ofMcpToolsetReference(mcpToolsetReference)
            )

        /**
         * Alias for calling [addToolRemovalContent] with the following:
         * ```java
         * BetaToolChangeMcpToolsetReference.builder()
         *     .serverName(serverName)
         *     .build()
         * ```
         */
        fun addMcpToolsetReferenceToolRemovalContent(serverName: String) =
            addToolRemovalContent(
                BetaToolChangeMcpToolsetReference.builder().serverName(serverName).build()
            )

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("mid_conv_system")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Create a cache control breakpoint at this content block. */
        fun cacheControl(cacheControl: BetaCacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<BetaCacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed
         * [BetaCacheControlEphemeral] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun cacheControl(cacheControl: JsonField<BetaCacheControlEphemeral>) = apply {
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
         * Returns an immutable instance of [BetaMidConversationSystemBlockParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .content()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaMidConversationSystemBlockParam =
            BetaMidConversationSystemBlockParam(
                checkRequired("content", content).map { it.toImmutable() },
                type,
                cacheControl,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaMidConversationSystemBlockParam = apply {
        if (validated) {
            return@apply
        }

        content().forEach { it.validate() }
        _type().let {
            if (it != JsonValue.from("mid_conv_system")) {
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
        (content.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            type.let { if (it == JsonValue.from("mid_conv_system")) 1 else 0 } +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * Mid-conversation directive to surface a declared tool.
     *
     * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it is
     * offered to the model from this point in the conversation onward.
     */
    @JsonDeserialize(using = Content.Deserializer::class)
    @JsonSerialize(using = Content.Serializer::class)
    class Content
    private constructor(
        private val text: BetaTextBlockParam? = null,
        private val toolAddition: BetaRequestToolAdditionBlock? = null,
        private val toolRemoval: BetaRequestToolRemovalBlock? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitText(text: BetaTextBlockParam): Type = Type.TEXT

                    override fun visitToolAddition(
                        toolAddition: BetaRequestToolAdditionBlock
                    ): Type = Type.TOOL_ADDITION

                    override fun visitToolRemoval(toolRemoval: BetaRequestToolRemovalBlock): Type =
                        Type.TOOL_REMOVAL

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun cacheControl(): Optional<BetaCacheControlEphemeral> =
            accept(
                object : Visitor<Optional<BetaCacheControlEphemeral>> {
                    override fun visitText(
                        text: BetaTextBlockParam
                    ): Optional<BetaCacheControlEphemeral> = text.cacheControl()

                    override fun visitToolAddition(
                        toolAddition: BetaRequestToolAdditionBlock
                    ): Optional<BetaCacheControlEphemeral> = toolAddition.cacheControl()

                    override fun visitToolRemoval(
                        toolRemoval: BetaRequestToolRemovalBlock
                    ): Optional<BetaCacheControlEphemeral> = toolRemoval.cacheControl()
                }
            )

        fun text(): Optional<BetaTextBlockParam> = Optional.ofNullable(text)

        /**
         * Mid-conversation directive to surface a declared tool.
         *
         * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it is
         * offered to the model from this point in the conversation onward.
         */
        fun toolAddition(): Optional<BetaRequestToolAdditionBlock> =
            Optional.ofNullable(toolAddition)

        /**
         * Mid-conversation directive to withdraw a tool.
         *
         * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it is
         * no longer offered to the model from this point in the conversation onward.
         */
        fun toolRemoval(): Optional<BetaRequestToolRemovalBlock> = Optional.ofNullable(toolRemoval)

        fun isText(): Boolean = text != null

        fun isToolAddition(): Boolean = toolAddition != null

        fun isToolRemoval(): Boolean = toolRemoval != null

        fun asText(): BetaTextBlockParam = text.getOrThrow("text")

        /**
         * Mid-conversation directive to surface a declared tool.
         *
         * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it is
         * offered to the model from this point in the conversation onward.
         */
        fun asToolAddition(): BetaRequestToolAdditionBlock = toolAddition.getOrThrow("toolAddition")

        /**
         * Mid-conversation directive to withdraw a tool.
         *
         * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it is
         * no longer offered to the model from this point in the conversation onward.
         */
        fun asToolRemoval(): BetaRequestToolRemovalBlock = toolRemoval.getOrThrow("toolRemoval")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = content.accept(new Content.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitText(BetaTextBlockParam text) {
         *         return Optional.of(text.toString());
         *     }
         *
         *     // ...
         *
         *     @Override
         *     public Optional<String> unknown(JsonValue json) {
         *         // Or inspect the `json`.
         *         return Optional.empty();
         *     }
         * });
         * ```
         *
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                text != null -> visitor.visitText(text)
                toolAddition != null -> visitor.visitToolAddition(toolAddition)
                toolRemoval != null -> visitor.visitToolRemoval(toolRemoval)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Content = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitText(text: BetaTextBlockParam) {
                        text.validate()
                    }

                    override fun visitToolAddition(toolAddition: BetaRequestToolAdditionBlock) {
                        toolAddition.validate()
                    }

                    override fun visitToolRemoval(toolRemoval: BetaRequestToolRemovalBlock) {
                        toolRemoval.validate()
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
                    override fun visitText(text: BetaTextBlockParam) = text.validity()

                    override fun visitToolAddition(toolAddition: BetaRequestToolAdditionBlock) =
                        toolAddition.validity()

                    override fun visitToolRemoval(toolRemoval: BetaRequestToolRemovalBlock) =
                        toolRemoval.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Content &&
                text == other.text &&
                toolAddition == other.toolAddition &&
                toolRemoval == other.toolRemoval
        }

        override fun hashCode(): Int = Objects.hash(text, toolAddition, toolRemoval)

        override fun toString(): String =
            when {
                text != null -> "Content{text=$text}"
                toolAddition != null -> "Content{toolAddition=$toolAddition}"
                toolRemoval != null -> "Content{toolRemoval=$toolRemoval}"
                _json != null -> "Content{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Content")
            }

        companion object {

            @JvmStatic fun ofText(text: BetaTextBlockParam) = Content(text = text)

            /**
             * Returns an immutable instance of [Content] whose [ofText] variant is built from the
             * given required [text].
             */
            @JvmStatic fun ofText(text: String) = ofText(BetaTextBlockParam.of(text))

            /**
             * Mid-conversation directive to surface a declared tool.
             *
             * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it
             * is offered to the model from this point in the conversation onward.
             */
            @JvmStatic
            fun ofToolAddition(toolAddition: BetaRequestToolAdditionBlock) =
                Content(toolAddition = toolAddition)

            /**
             * Returns an immutable instance of [Content] whose [ofToolAddition] variant is built
             * from the given required [tool].
             */
            @JvmStatic
            fun ofToolAddition(tool: BetaRequestToolAdditionBlock.Tool) =
                ofToolAddition(BetaRequestToolAdditionBlock.of(tool))

            /**
             * Mid-conversation directive to withdraw a tool.
             *
             * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it
             * is no longer offered to the model from this point in the conversation onward.
             */
            @JvmStatic
            fun ofToolRemoval(toolRemoval: BetaRequestToolRemovalBlock) =
                Content(toolRemoval = toolRemoval)

            /**
             * Returns an immutable instance of [Content] whose [ofToolRemoval] variant is built
             * from the given required [tool].
             */
            @JvmStatic
            fun ofToolRemoval(tool: BetaRequestToolRemovalBlock.Tool) =
                ofToolRemoval(BetaRequestToolRemovalBlock.of(tool))
        }

        /**
         * An interface that defines how to map each variant of [Content] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitText(text: BetaTextBlockParam): T

            /**
             * Mid-conversation directive to surface a declared tool.
             *
             * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it
             * is offered to the model from this point in the conversation onward.
             */
            fun visitToolAddition(toolAddition: BetaRequestToolAdditionBlock): T

            /**
             * Mid-conversation directive to withdraw a tool.
             *
             * ``tool`` references a tool (or MCP toolset) by name from the request's ``tools``; it
             * is no longer offered to the model from this point in the conversation onward.
             */
            fun visitToolRemoval(toolRemoval: BetaRequestToolRemovalBlock): T

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
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "text" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaTextBlockParam>())?.let {
                            Content(text = it, _json = json)
                        } ?: Content(_json = json)
                    }
                    "tool_addition" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaRequestToolAdditionBlock>())
                            ?.let { Content(toolAddition = it, _json = json) }
                            ?: Content(_json = json)
                    }
                    "tool_removal" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaRequestToolRemovalBlock>())
                            ?.let { Content(toolRemoval = it, _json = json) }
                            ?: Content(_json = json)
                    }
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
                    value.text != null -> generator.writeObject(value.text)
                    value.toolAddition != null -> generator.writeObject(value.toolAddition)
                    value.toolRemoval != null -> generator.writeObject(value.toolRemoval)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Content")
                }
            }
        }

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val TEXT = of("text")

                @JvmField val TOOL_ADDITION = of("tool_addition")

                @JvmField val TOOL_REMOVAL = of("tool_removal")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonValue): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                TEXT,
                TOOL_ADDITION,
                TOOL_REMOVAL,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                TEXT,
                TOOL_ADDITION,
                TOOL_REMOVAL,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    TEXT -> Value.TEXT
                    TOOL_ADDITION -> Value.TOOL_ADDITION
                    TOOL_REMOVAL -> Value.TOOL_REMOVAL
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    TEXT -> Known.TEXT
                    TOOL_ADDITION -> Known.TOOL_ADDITION
                    TOOL_REMOVAL -> Known.TOOL_REMOVAL
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
             */
            fun asString(): String =
                _value().asString().orElseThrow {
                    AnthropicInvalidDataException("Value is not a String")
                }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
             */
            fun validate(): Type = apply {
                if (validated) {
                    return@apply
                }

                known()
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
            @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Type && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaMidConversationSystemBlockParam &&
            content == other.content &&
            type == other.type &&
            cacheControl == other.cacheControl &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(content, type, cacheControl, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMidConversationSystemBlockParam{content=$content, type=$type, cacheControl=$cacheControl, additionalProperties=$additionalProperties}"
}
