package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
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

class BetaSystemMessageParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val role: JsonValue,
    private val clearAt: JsonField<ClearAt>,
    private val content: JsonField<Content>,
    private val outputConfig: JsonField<BetaSystemMessageOutputConfig>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("role") @ExcludeMissing role: JsonValue = JsonMissing.of(),
        @JsonProperty("clear_at") @ExcludeMissing clearAt: JsonField<ClearAt> = JsonMissing.of(),
        @JsonProperty("content") @ExcludeMissing content: JsonField<Content> = JsonMissing.of(),
        @JsonProperty("output_config")
        @ExcludeMissing
        outputConfig: JsonField<BetaSystemMessageOutputConfig> = JsonMissing.of(),
    ) : this(role, clearAt, content, outputConfig, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("system")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("role") @ExcludeMissing fun _role(): JsonValue = role

    /**
     * How long this system message's text stays in front of the model. `"never"` (the default)
     * renders it on every request that includes it. `"next_user_message"` renders it only for the
     * user turn it follows: once a later `role: "user"` message exists in `messages` the message
     * stays in the array (send it unchanged) but is no longer shown to the model. Only permitted on
     * `role: "system"` messages.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun clearAt(): Optional<ClearAt> = clearAt.getOptional("clear_at")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun content(): Optional<Content> = content.getOptional("content")

    /**
     * Per-message output configuration on a role:"system" input message.
     *
     * Fields here apply per-turn; ``format`` remains top-level only. An empty ``{}`` is accepted on
     * a message that carries content; a message with neither content nor output_config fields is
     * rejected.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun outputConfig(): Optional<BetaSystemMessageOutputConfig> =
        outputConfig.getOptional("output_config")

    /**
     * Returns the raw JSON value of [clearAt].
     *
     * Unlike [clearAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("clear_at") @ExcludeMissing fun _clearAt(): JsonField<ClearAt> = clearAt

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content") @ExcludeMissing fun _content(): JsonField<Content> = content

    /**
     * Returns the raw JSON value of [outputConfig].
     *
     * Unlike [outputConfig], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("output_config")
    @ExcludeMissing
    fun _outputConfig(): JsonField<BetaSystemMessageOutputConfig> = outputConfig

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

        /** Returns a mutable builder for constructing an instance of [BetaSystemMessageParam]. */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaSystemMessageParam] with the required [content] set
         * to the given value.
         */
        @JvmStatic fun of(content: Content) = builder().content(content).build()

        /** Alias for calling [of] with `Content.ofString(string)`. */
        @JvmStatic fun of(string: String) = of(Content.ofString(string))

        /** Alias for calling [of] with `Content.ofBlocks(blocks)`. */
        @JvmStatic
        fun ofBlocks(blocks: List<Content.SystemMessageContentBlock>) = of(Content.ofBlocks(blocks))
    }

    /** A builder for [BetaSystemMessageParam]. */
    class Builder internal constructor() {

        private var role: JsonValue = JsonValue.from("system")
        private var clearAt: JsonField<ClearAt> = JsonMissing.of()
        private var content: JsonField<Content> = JsonMissing.of()
        private var outputConfig: JsonField<BetaSystemMessageOutputConfig> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaSystemMessageParam: BetaSystemMessageParam) = apply {
            role = betaSystemMessageParam.role
            clearAt = betaSystemMessageParam.clearAt
            content = betaSystemMessageParam.content
            outputConfig = betaSystemMessageParam.outputConfig
            additionalProperties = betaSystemMessageParam.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("system")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun role(role: JsonValue) = apply { this.role = role }

        /**
         * How long this system message's text stays in front of the model. `"never"` (the default)
         * renders it on every request that includes it. `"next_user_message"` renders it only for
         * the user turn it follows: once a later `role: "user"` message exists in `messages` the
         * message stays in the array (send it unchanged) but is no longer shown to the model. Only
         * permitted on `role: "system"` messages.
         */
        fun clearAt(clearAt: ClearAt?) = clearAt(JsonField.ofNullable(clearAt))

        /** Alias for calling [Builder.clearAt] with `clearAt.orElse(null)`. */
        fun clearAt(clearAt: Optional<ClearAt>) = clearAt(clearAt.getOrNull())

        /**
         * Sets [Builder.clearAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.clearAt] with a well-typed [ClearAt] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun clearAt(clearAt: JsonField<ClearAt>) = apply { this.clearAt = clearAt }

        fun content(content: Content) = content(JsonField.of(content))

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed [Content] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun content(content: JsonField<Content>) = apply { this.content = content }

        /** Alias for calling [content] with `Content.ofString(string)`. */
        fun content(string: String) = content(Content.ofString(string))

        /** Alias for calling [content] with `Content.ofBlocks(blocks)`. */
        fun contentOfBlocks(blocks: List<Content.SystemMessageContentBlock>) =
            content(Content.ofBlocks(blocks))

        /**
         * Per-message output configuration on a role:"system" input message.
         *
         * Fields here apply per-turn; ``format`` remains top-level only. An empty ``{}`` is
         * accepted on a message that carries content; a message with neither content nor
         * output_config fields is rejected.
         */
        fun outputConfig(outputConfig: BetaSystemMessageOutputConfig?) =
            outputConfig(JsonField.ofNullable(outputConfig))

        /** Alias for calling [Builder.outputConfig] with `outputConfig.orElse(null)`. */
        fun outputConfig(outputConfig: Optional<BetaSystemMessageOutputConfig>) =
            outputConfig(outputConfig.getOrNull())

        /**
         * Sets [Builder.outputConfig] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outputConfig] with a well-typed
         * [BetaSystemMessageOutputConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun outputConfig(outputConfig: JsonField<BetaSystemMessageOutputConfig>) = apply {
            this.outputConfig = outputConfig
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
         * Returns an immutable instance of [BetaSystemMessageParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaSystemMessageParam =
            BetaSystemMessageParam(
                role,
                clearAt,
                content,
                outputConfig,
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
    fun validate(): BetaSystemMessageParam = apply {
        if (validated) {
            return@apply
        }

        _role().let {
            if (it != JsonValue.from("system")) {
                throw AnthropicInvalidDataException("'role' is invalid, received $it")
            }
        }
        clearAt().ifPresent { it.validate() }
        content().ifPresent { it.validate() }
        outputConfig().ifPresent { it.validate() }
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
        role.let { if (it == JsonValue.from("system")) 1 else 0 } +
            (clearAt.asKnown().getOrNull()?.validity() ?: 0) +
            (content.asKnown().getOrNull()?.validity() ?: 0) +
            (outputConfig.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * How long this system message's text stays in front of the model. `"never"` (the default)
     * renders it on every request that includes it. `"next_user_message"` renders it only for the
     * user turn it follows: once a later `role: "user"` message exists in `messages` the message
     * stays in the array (send it unchanged) but is no longer shown to the model. Only permitted on
     * `role: "system"` messages.
     */
    class ClearAt private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val NEXT_USER_MESSAGE = ClearAt(JsonField.of("next_user_message"))

            @JvmField val NEVER = ClearAt(JsonField.of("never"))

            @JvmStatic
            fun of(value: String): ClearAt =
                // Intern known values so `==` works
                when (value) {
                    "next_user_message" -> NEXT_USER_MESSAGE
                    "never" -> NEVER
                    else -> ClearAt(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): ClearAt =
                value.asString().getOrNull()?.let { of(it) } ?: ClearAt(value)
        }

        /** An enum containing [ClearAt]'s known values. */
        enum class Known {
            NEXT_USER_MESSAGE,
            NEVER,
        }

        /**
         * An enum containing [ClearAt]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [ClearAt] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            NEXT_USER_MESSAGE,
            NEVER,
            /** An enum member indicating that [ClearAt] was instantiated with an unknown value. */
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
                NEXT_USER_MESSAGE -> Value.NEXT_USER_MESSAGE
                NEVER -> Value.NEVER
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
                NEXT_USER_MESSAGE -> Known.NEXT_USER_MESSAGE
                NEVER -> Known.NEVER
                else -> throw AnthropicInvalidDataException("Unknown ClearAt: $value")
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
        fun validate(): ClearAt = apply {
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

            return other is ClearAt && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    @JsonDeserialize(using = Content.Deserializer::class)
    @JsonSerialize(using = Content.Serializer::class)
    class Content
    private constructor(
        private val string: String? = null,
        private val blocks: List<SystemMessageContentBlock>? = null,
        private val _json: JsonValue? = null,
    ) {

        fun string(): Optional<String> = Optional.ofNullable(string)

        fun blocks(): Optional<List<SystemMessageContentBlock>> = Optional.ofNullable(blocks)

        fun isString(): Boolean = string != null

        fun isBlocks(): Boolean = blocks != null

        fun asString(): String = string.getOrThrow("string")

        fun asBlocks(): List<SystemMessageContentBlock> = blocks.getOrThrow("blocks")

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
         *     public Optional<String> visitString(String string) {
         *         return Optional.of(string.toString());
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
                string != null -> visitor.visitString(string)
                blocks != null -> visitor.visitBlocks(blocks)
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

            when {
                string != null -> {}
                blocks != null -> blocks.forEach { it.validate() }
                else -> throw AnthropicInvalidDataException("Unknown Content: $_json")
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            when {
                string != null -> 1
                blocks != null -> blocks.sumOf { it.validity().toInt() }
                else -> 0
            }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Content && string == other.string && blocks == other.blocks
        }

        override fun hashCode(): Int = Objects.hash(string, blocks)

        override fun toString(): String =
            when {
                string != null -> "Content{string=$string}"
                blocks != null -> "Content{blocks=$blocks}"
                _json != null -> "Content{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Content")
            }

        companion object {

            @JvmStatic fun ofString(string: String) = Content(string = string)

            @JvmStatic
            fun ofBlocks(blocks: List<SystemMessageContentBlock>) =
                Content(blocks = blocks.toImmutable())
        }

        /**
         * An interface that defines how to map each variant of [Content] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitString(string: String): T

            fun visitBlocks(blocks: List<SystemMessageContentBlock>): T

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
                            tryDeserialize(node, jacksonTypeRef<String>())?.let {
                                Content(string = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<List<SystemMessageContentBlock>>())
                                ?.let { Content(blocks = it, _json = json) },
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
                    value.string != null -> generator.writeObject(value.string)
                    value.blocks != null -> generator.writeObject(value.blocks)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Content")
                }
            }
        }

        @JsonDeserialize(using = SystemMessageContentBlock.Deserializer::class)
        @JsonSerialize(using = SystemMessageContentBlock.Serializer::class)
        class SystemMessageContentBlock
        private constructor(
            private val text: BetaTextBlockParam? = null,
            private val toolAddition: BetaRequestToolAdditionBlock? = null,
            private val toolRemoval: BetaRequestToolRemovalBlock? = null,
            private val _json: JsonValue? = null,
        ) {

            fun type(): Type =
                when {
                    text != null -> Type.TEXT
                    toolAddition != null -> Type.TOOL_ADDITION
                    toolRemoval != null -> Type.TOOL_REMOVAL
                    else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }

            fun cacheControl(): Optional<BetaCacheControlEphemeral> =
                when {
                    text != null -> text.cacheControl()
                    toolAddition != null -> toolAddition.cacheControl()
                    toolRemoval != null -> toolRemoval.cacheControl()
                    else -> _json.getProperty<BetaCacheControlEphemeral>("cache_control").asKnown()
                }

            fun text(): Optional<BetaTextBlockParam> = Optional.ofNullable(text)

            /**
             * Mid-conversation directive to make a tool available.
             *
             * ``tool`` is a reference to a tool (or MCP toolset) declared in the request's
             * ``tools``. Under the ``inline-tools-2026-09-15`` beta it may instead be a reference
             * to a tool defined earlier in ``messages``, or a ``tool_definition`` object that
             * carries an inline tool definition in ``definition`` (the same object a ``tools``
             * entry holds). An ``mcp_toolset`` definition also requires the
             * ``mcp-client-2026-09-15`` beta. The tool is offered to the model from this point in
             * the conversation onward.
             */
            fun toolAddition(): Optional<BetaRequestToolAdditionBlock> =
                Optional.ofNullable(toolAddition)

            /**
             * Mid-conversation directive to withdraw a tool.
             *
             * ``tool`` references a tool (or MCP toolset) by name: one declared in the request's
             * ``tools`` or defined earlier in ``messages``. It is no longer offered to the model
             * from this point in the conversation onward.
             */
            fun toolRemoval(): Optional<BetaRequestToolRemovalBlock> =
                Optional.ofNullable(toolRemoval)

            fun isText(): Boolean = text != null

            fun isToolAddition(): Boolean = toolAddition != null

            fun isToolRemoval(): Boolean = toolRemoval != null

            fun asText(): BetaTextBlockParam = text.getOrThrow("text")

            /**
             * Mid-conversation directive to make a tool available.
             *
             * ``tool`` is a reference to a tool (or MCP toolset) declared in the request's
             * ``tools``. Under the ``inline-tools-2026-09-15`` beta it may instead be a reference
             * to a tool defined earlier in ``messages``, or a ``tool_definition`` object that
             * carries an inline tool definition in ``definition`` (the same object a ``tools``
             * entry holds). An ``mcp_toolset`` definition also requires the
             * ``mcp-client-2026-09-15`` beta. The tool is offered to the model from this point in
             * the conversation onward.
             */
            fun asToolAddition(): BetaRequestToolAdditionBlock =
                toolAddition.getOrThrow("toolAddition")

            /**
             * Mid-conversation directive to withdraw a tool.
             *
             * ``tool`` references a tool (or MCP toolset) by name: one declared in the request's
             * ``tools`` or defined earlier in ``messages``. It is no longer offered to the model
             * from this point in the conversation onward.
             */
            fun asToolRemoval(): BetaRequestToolRemovalBlock = toolRemoval.getOrThrow("toolRemoval")

            fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

            /**
             * Maps this instance's current variant to a value of type [T] using the given
             * [visitor].
             *
             * Note that this method is _not_ forwards compatible with new variants from the API,
             * unless [visitor] overrides [Visitor.unknown]. To handle variants not known to this
             * version of the SDK gracefully, consider overriding [Visitor.unknown]:
             * ```java
             * import com.anthropic.core.JsonValue;
             * import java.util.Optional;
             *
             * Optional<String> result = systemMessageContentBlock.accept(new SystemMessageContentBlock.Visitor<Optional<String>>() {
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
             * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in
             *   [visitor] and the current variant is unknown.
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
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
             */
            fun validate(): SystemMessageContentBlock = apply {
                if (validated) {
                    return@apply
                }

                when {
                    text != null -> text.validate()
                    toolAddition != null -> toolAddition.validate()
                    toolRemoval != null -> toolRemoval.validate()
                    else ->
                        throw AnthropicInvalidDataException(
                            "Unknown SystemMessageContentBlock: $_json"
                        )
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
             * Returns a score indicating how many valid values are contained in this object
             * recursively.
             *
             * Used for best match union deserialization.
             */
            @JvmSynthetic
            internal fun validity(): Int =
                when {
                    text != null -> text.validity()
                    toolAddition != null -> toolAddition.validity()
                    toolRemoval != null -> toolRemoval.validity()
                    else -> 0
                }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is SystemMessageContentBlock &&
                    text == other.text &&
                    toolAddition == other.toolAddition &&
                    toolRemoval == other.toolRemoval
            }

            override fun hashCode(): Int = Objects.hash(text, toolAddition, toolRemoval)

            override fun toString(): String =
                when {
                    text != null -> "SystemMessageContentBlock{text=$text}"
                    toolAddition != null -> "SystemMessageContentBlock{toolAddition=$toolAddition}"
                    toolRemoval != null -> "SystemMessageContentBlock{toolRemoval=$toolRemoval}"
                    _json != null -> "SystemMessageContentBlock{_unknown=$_json}"
                    else -> throw IllegalStateException("Invalid SystemMessageContentBlock")
                }

            companion object {

                @JvmStatic
                fun ofText(text: BetaTextBlockParam) = SystemMessageContentBlock(text = text)

                /**
                 * Returns an immutable instance of [SystemMessageContentBlock] whose [ofText]
                 * variant is built from the given required [text].
                 */
                @JvmStatic fun ofText(text: String) = ofText(BetaTextBlockParam.of(text))

                /**
                 * Mid-conversation directive to make a tool available.
                 *
                 * ``tool`` is a reference to a tool (or MCP toolset) declared in the request's
                 * ``tools``. Under the ``inline-tools-2026-09-15`` beta it may instead be a
                 * reference to a tool defined earlier in ``messages``, or a ``tool_definition``
                 * object that carries an inline tool definition in ``definition`` (the same object
                 * a ``tools`` entry holds). An ``mcp_toolset`` definition also requires the
                 * ``mcp-client-2026-09-15`` beta. The tool is offered to the model from this point
                 * in the conversation onward.
                 */
                @JvmStatic
                fun ofToolAddition(toolAddition: BetaRequestToolAdditionBlock) =
                    SystemMessageContentBlock(toolAddition = toolAddition)

                /**
                 * Returns an immutable instance of [SystemMessageContentBlock] whose
                 * [ofToolAddition] variant is built from the given required [tool].
                 */
                @JvmStatic
                fun ofToolAddition(tool: BetaRequestToolAdditionBlock.Tool) =
                    ofToolAddition(BetaRequestToolAdditionBlock.of(tool))

                /**
                 * Alias for calling [ofToolAddition] with
                 * `BetaRequestToolAdditionBlock.Tool.ofReference(reference)`.
                 */
                @JvmStatic
                fun ofToolAddition(reference: BetaToolChangeToolReference) =
                    ofToolAddition(BetaRequestToolAdditionBlock.Tool.ofReference(reference))

                /** Alias for calling [ofToolAddition] with `reference.toParam()`. */
                @JvmStatic
                fun ofToolAddition(reference: BetaResponseToolChangeToolReference) =
                    ofToolAddition(reference.toParam())

                /**
                 * Alias for calling [ofToolAddition] with
                 * `BetaRequestToolAdditionBlock.Tool.ofMcpToolReference(mcpToolReference)`.
                 */
                @JvmStatic
                fun ofToolAddition(mcpToolReference: BetaToolChangeMcpToolReference) =
                    ofToolAddition(
                        BetaRequestToolAdditionBlock.Tool.ofMcpToolReference(mcpToolReference)
                    )

                /** Alias for calling [ofToolAddition] with `mcpToolReference.toParam()`. */
                @JvmStatic
                fun ofToolAddition(mcpToolReference: BetaResponseToolChangeMcpToolReference) =
                    ofToolAddition(mcpToolReference.toParam())

                /**
                 * Alias for calling [ofToolAddition] with
                 * `BetaRequestToolAdditionBlock.Tool.ofMcpToolsetReference(mcpToolsetReference)`.
                 */
                @JvmStatic
                fun ofToolAddition(mcpToolsetReference: BetaToolChangeMcpToolsetReference) =
                    ofToolAddition(
                        BetaRequestToolAdditionBlock.Tool.ofMcpToolsetReference(mcpToolsetReference)
                    )

                /** Alias for calling [ofToolAddition] with `mcpToolsetReference.toParam()`. */
                @JvmStatic
                fun ofToolAddition(mcpToolsetReference: BetaResponseToolChangeMcpToolsetReference) =
                    ofToolAddition(mcpToolsetReference.toParam())

                /**
                 * Alias for calling [ofToolAddition] with
                 * `BetaRequestToolAdditionBlock.Tool.ofDefinition(definition)`.
                 */
                @JvmStatic
                fun ofToolAddition(definition: BetaToolChangeToolDefinitionParam) =
                    ofToolAddition(BetaRequestToolAdditionBlock.Tool.ofDefinition(definition))

                /** Alias for calling [ofToolAddition] with `definition.toParam()`. */
                @JvmStatic
                fun ofToolAddition(definition: BetaToolChangeToolDefinition) =
                    ofToolAddition(definition.toParam())

                /**
                 * Mid-conversation directive to withdraw a tool.
                 *
                 * ``tool`` references a tool (or MCP toolset) by name: one declared in the
                 * request's ``tools`` or defined earlier in ``messages``. It is no longer offered
                 * to the model from this point in the conversation onward.
                 */
                @JvmStatic
                fun ofToolRemoval(toolRemoval: BetaRequestToolRemovalBlock) =
                    SystemMessageContentBlock(toolRemoval = toolRemoval)

                /**
                 * Returns an immutable instance of [SystemMessageContentBlock] whose
                 * [ofToolRemoval] variant is built from the given required [tool].
                 */
                @JvmStatic
                fun ofToolRemoval(tool: BetaRequestToolRemovalBlock.Tool) =
                    ofToolRemoval(BetaRequestToolRemovalBlock.of(tool))

                /**
                 * Alias for calling [ofToolRemoval] with
                 * `BetaRequestToolRemovalBlock.Tool.ofReference(reference)`.
                 */
                @JvmStatic
                fun ofToolRemoval(reference: BetaToolChangeToolReference) =
                    ofToolRemoval(BetaRequestToolRemovalBlock.Tool.ofReference(reference))

                /** Alias for calling [ofToolRemoval] with `reference.toParam()`. */
                @JvmStatic
                fun ofToolRemoval(reference: BetaResponseToolChangeToolReference) =
                    ofToolRemoval(reference.toParam())

                /**
                 * Alias for calling [ofToolRemoval] with
                 * `BetaRequestToolRemovalBlock.Tool.ofMcpToolReference(mcpToolReference)`.
                 */
                @JvmStatic
                fun ofToolRemoval(mcpToolReference: BetaToolChangeMcpToolReference) =
                    ofToolRemoval(
                        BetaRequestToolRemovalBlock.Tool.ofMcpToolReference(mcpToolReference)
                    )

                /** Alias for calling [ofToolRemoval] with `mcpToolReference.toParam()`. */
                @JvmStatic
                fun ofToolRemoval(mcpToolReference: BetaResponseToolChangeMcpToolReference) =
                    ofToolRemoval(mcpToolReference.toParam())

                /**
                 * Alias for calling [ofToolRemoval] with
                 * `BetaRequestToolRemovalBlock.Tool.ofMcpToolsetReference(mcpToolsetReference)`.
                 */
                @JvmStatic
                fun ofToolRemoval(mcpToolsetReference: BetaToolChangeMcpToolsetReference) =
                    ofToolRemoval(
                        BetaRequestToolRemovalBlock.Tool.ofMcpToolsetReference(mcpToolsetReference)
                    )

                /** Alias for calling [ofToolRemoval] with `mcpToolsetReference.toParam()`. */
                @JvmStatic
                fun ofToolRemoval(mcpToolsetReference: BetaResponseToolChangeMcpToolsetReference) =
                    ofToolRemoval(mcpToolsetReference.toParam())
            }

            /**
             * An interface that defines how to map each variant of [SystemMessageContentBlock] to a
             * value of type [T].
             */
            interface Visitor<out T> {

                fun visitText(text: BetaTextBlockParam): T

                /**
                 * Mid-conversation directive to make a tool available.
                 *
                 * ``tool`` is a reference to a tool (or MCP toolset) declared in the request's
                 * ``tools``. Under the ``inline-tools-2026-09-15`` beta it may instead be a
                 * reference to a tool defined earlier in ``messages``, or a ``tool_definition``
                 * object that carries an inline tool definition in ``definition`` (the same object
                 * a ``tools`` entry holds). An ``mcp_toolset`` definition also requires the
                 * ``mcp-client-2026-09-15`` beta. The tool is offered to the model from this point
                 * in the conversation onward.
                 */
                fun visitToolAddition(toolAddition: BetaRequestToolAdditionBlock): T

                /**
                 * Mid-conversation directive to withdraw a tool.
                 *
                 * ``tool`` references a tool (or MCP toolset) by name: one declared in the
                 * request's ``tools`` or defined earlier in ``messages``. It is no longer offered
                 * to the model from this point in the conversation onward.
                 */
                fun visitToolRemoval(toolRemoval: BetaRequestToolRemovalBlock): T

                /**
                 * Maps an unknown variant of [SystemMessageContentBlock] to a value of type [T].
                 *
                 * An instance of [SystemMessageContentBlock] can contain an unknown variant if it
                 * was deserialized from data that doesn't match any known variant. For example, if
                 * the SDK is on an older version than the API, then the API may respond with new
                 * variants that the SDK is unaware of.
                 *
                 * @throws AnthropicInvalidDataException in the default implementation.
                 */
                fun unknown(json: JsonValue?): T {
                    throw AnthropicInvalidDataException("Unknown SystemMessageContentBlock: $json")
                }
            }

            internal class Deserializer :
                BaseDeserializer<SystemMessageContentBlock>(SystemMessageContentBlock::class) {

                override fun ObjectCodec.deserialize(node: JsonNode): SystemMessageContentBlock {
                    val json = JsonValue.fromJsonNode(node)
                    val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                    when (type) {
                        "text" -> {
                            return tryDeserialize(node, jacksonTypeRef<BetaTextBlockParam>())?.let {
                                SystemMessageContentBlock(text = it, _json = json)
                            } ?: SystemMessageContentBlock(_json = json)
                        }
                        "tool_addition" -> {
                            return tryDeserialize(
                                    node,
                                    jacksonTypeRef<BetaRequestToolAdditionBlock>(),
                                )
                                ?.let { SystemMessageContentBlock(toolAddition = it, _json = json) }
                                ?: SystemMessageContentBlock(_json = json)
                        }
                        "tool_removal" -> {
                            return tryDeserialize(
                                    node,
                                    jacksonTypeRef<BetaRequestToolRemovalBlock>(),
                                )
                                ?.let { SystemMessageContentBlock(toolRemoval = it, _json = json) }
                                ?: SystemMessageContentBlock(_json = json)
                        }
                    }

                    return SystemMessageContentBlock(_json = json)
                }
            }

            internal class Serializer :
                BaseSerializer<SystemMessageContentBlock>(SystemMessageContentBlock::class) {

                override fun serialize(
                    value: SystemMessageContentBlock,
                    generator: JsonGenerator,
                    provider: SerializerProvider,
                ) {
                    when {
                        value.text != null -> generator.writeObject(value.text)
                        value.toolAddition != null -> generator.writeObject(value.toolAddition)
                        value.toolRemoval != null -> generator.writeObject(value.toolRemoval)
                        value._json != null -> generator.writeObject(value._json)
                        else -> throw IllegalStateException("Invalid SystemMessageContentBlock")
                    }
                }
            }

            class Type private constructor(private val value: JsonField<String>) : Enum {

                /**
                 * Returns this class instance's raw value.
                 *
                 * This is usually only useful if this instance was deserialized from data that
                 * doesn't match any known member, and you want to know that value. For example, if
                 * the SDK is on an older version than the API, then the API may respond with new
                 * members that the SDK is unaware of.
                 */
                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    @JvmField val TEXT = Type(JsonField.of("text"))

                    @JvmField val TOOL_ADDITION = Type(JsonField.of("tool_addition"))

                    @JvmField val TOOL_REMOVAL = Type(JsonField.of("tool_removal"))

                    @JvmStatic
                    fun of(value: String): Type =
                        // Intern known values so `==` works
                        when (value) {
                            "text" -> TEXT
                            "tool_addition" -> TOOL_ADDITION
                            "tool_removal" -> TOOL_REMOVAL
                            else -> Type(JsonField.of(value))
                        }

                    @JsonCreator
                    @JvmStatic
                    fun of(value: JsonField<String>): Type =
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
                 * - It was deserialized from data that doesn't match any known member. For example,
                 *   if the SDK is on an older version than the API, then the API may respond with
                 *   new members that the SDK is unaware of.
                 * - It was constructed with an arbitrary value using the [of] method.
                 */
                enum class Value {
                    TEXT,
                    TOOL_ADDITION,
                    TOOL_REMOVAL,
                    /**
                     * An enum member indicating that [Type] was instantiated with an unknown value.
                     */
                    _UNKNOWN,
                }

                /**
                 * Returns an enum member corresponding to this class instance's value, or
                 * [Value._UNKNOWN] if the class was instantiated with an unknown value.
                 *
                 * Use the [known] method instead if you're certain the value is always known or if
                 * you want to throw for the unknown case.
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
                 * @throws AnthropicInvalidDataException if this class instance's value is a not a
                 *   known member.
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
                 * @throws AnthropicInvalidDataException if this class instance's value does not
                 *   have the expected primitive type.
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
                 * @throws AnthropicInvalidDataException if any value type in this object doesn't
                 *   match its expected type.
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
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaSystemMessageParam &&
            role == other.role &&
            clearAt == other.clearAt &&
            content == other.content &&
            outputConfig == other.outputConfig &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(role, clearAt, content, outputConfig, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaSystemMessageParam{role=$role, clearAt=$clearAt, content=$content, outputConfig=$outputConfig, additionalProperties=$additionalProperties}"
}
