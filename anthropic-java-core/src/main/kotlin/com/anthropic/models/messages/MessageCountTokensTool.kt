// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
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

@JsonDeserialize(using = MessageCountTokensTool.Deserializer::class)
@JsonSerialize(using = MessageCountTokensTool.Serializer::class)
class MessageCountTokensTool
private constructor(
    private val tool: Tool? = null,
    private val toolBash20250124: ToolBash20250124? = null,
    private val toolTextEditor20250124: ToolTextEditor20250124? = null,
    private val textEditor20250429: TextEditor20250429? = null,
    private val webSearchTool20250305: WebSearchTool20250305? = null,
    private val _json: JsonValue? = null,
) {

    fun tool(): Optional<Tool> = Optional.ofNullable(tool)

    fun toolBash20250124(): Optional<ToolBash20250124> = Optional.ofNullable(toolBash20250124)

    fun toolTextEditor20250124(): Optional<ToolTextEditor20250124> =
        Optional.ofNullable(toolTextEditor20250124)

    fun textEditor20250429(): Optional<TextEditor20250429> = Optional.ofNullable(textEditor20250429)

    fun webSearchTool20250305(): Optional<WebSearchTool20250305> =
        Optional.ofNullable(webSearchTool20250305)

    fun isTool(): Boolean = tool != null

    fun isToolBash20250124(): Boolean = toolBash20250124 != null

    fun isToolTextEditor20250124(): Boolean = toolTextEditor20250124 != null

    fun isTextEditor20250429(): Boolean = textEditor20250429 != null

    fun isWebSearchTool20250305(): Boolean = webSearchTool20250305 != null

    fun asTool(): Tool = tool.getOrThrow("tool")

    fun asToolBash20250124(): ToolBash20250124 = toolBash20250124.getOrThrow("toolBash20250124")

    fun asToolTextEditor20250124(): ToolTextEditor20250124 =
        toolTextEditor20250124.getOrThrow("toolTextEditor20250124")

    fun asTextEditor20250429(): TextEditor20250429 =
        textEditor20250429.getOrThrow("textEditor20250429")

    fun asWebSearchTool20250305(): WebSearchTool20250305 =
        webSearchTool20250305.getOrThrow("webSearchTool20250305")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            tool != null -> visitor.visitTool(tool)
            toolBash20250124 != null -> visitor.visitToolBash20250124(toolBash20250124)
            toolTextEditor20250124 != null ->
                visitor.visitToolTextEditor20250124(toolTextEditor20250124)
            textEditor20250429 != null -> visitor.visitTextEditor20250429(textEditor20250429)
            webSearchTool20250305 != null ->
                visitor.visitWebSearchTool20250305(webSearchTool20250305)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): MessageCountTokensTool = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitTool(tool: Tool) {
                    tool.validate()
                }

                override fun visitToolBash20250124(toolBash20250124: ToolBash20250124) {
                    toolBash20250124.validate()
                }

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: ToolTextEditor20250124
                ) {
                    toolTextEditor20250124.validate()
                }

                override fun visitTextEditor20250429(textEditor20250429: TextEditor20250429) {
                    textEditor20250429.validate()
                }

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: WebSearchTool20250305
                ) {
                    webSearchTool20250305.validate()
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        accept(
            object : Visitor<Int> {
                override fun visitTool(tool: Tool) = tool.validity()

                override fun visitToolBash20250124(toolBash20250124: ToolBash20250124) =
                    toolBash20250124.validity()

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: ToolTextEditor20250124
                ) = toolTextEditor20250124.validity()

                override fun visitTextEditor20250429(textEditor20250429: TextEditor20250429) =
                    textEditor20250429.validity()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: WebSearchTool20250305
                ) = webSearchTool20250305.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is MessageCountTokensTool && tool == other.tool && toolBash20250124 == other.toolBash20250124 && toolTextEditor20250124 == other.toolTextEditor20250124 && textEditor20250429 == other.textEditor20250429 && webSearchTool20250305 == other.webSearchTool20250305 /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(tool, toolBash20250124, toolTextEditor20250124, textEditor20250429, webSearchTool20250305) /* spotless:on */

    override fun toString(): String =
        when {
            tool != null -> "MessageCountTokensTool{tool=$tool}"
            toolBash20250124 != null -> "MessageCountTokensTool{toolBash20250124=$toolBash20250124}"
            toolTextEditor20250124 != null ->
                "MessageCountTokensTool{toolTextEditor20250124=$toolTextEditor20250124}"
            textEditor20250429 != null ->
                "MessageCountTokensTool{textEditor20250429=$textEditor20250429}"
            webSearchTool20250305 != null ->
                "MessageCountTokensTool{webSearchTool20250305=$webSearchTool20250305}"
            _json != null -> "MessageCountTokensTool{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid MessageCountTokensTool")
        }

    companion object {

        @JvmStatic fun ofTool(tool: Tool) = MessageCountTokensTool(tool = tool)

        @JvmStatic
        fun ofToolBash20250124(toolBash20250124: ToolBash20250124) =
            MessageCountTokensTool(toolBash20250124 = toolBash20250124)

        @JvmStatic
        fun ofToolTextEditor20250124(toolTextEditor20250124: ToolTextEditor20250124) =
            MessageCountTokensTool(toolTextEditor20250124 = toolTextEditor20250124)

        @JvmStatic
        fun ofTextEditor20250429(textEditor20250429: TextEditor20250429) =
            MessageCountTokensTool(textEditor20250429 = textEditor20250429)

        @JvmStatic
        fun ofWebSearchTool20250305(webSearchTool20250305: WebSearchTool20250305) =
            MessageCountTokensTool(webSearchTool20250305 = webSearchTool20250305)
    }

    /**
     * An interface that defines how to map each variant of [MessageCountTokensTool] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitTool(tool: Tool): T

        fun visitToolBash20250124(toolBash20250124: ToolBash20250124): T

        fun visitToolTextEditor20250124(toolTextEditor20250124: ToolTextEditor20250124): T

        fun visitTextEditor20250429(textEditor20250429: TextEditor20250429): T

        fun visitWebSearchTool20250305(webSearchTool20250305: WebSearchTool20250305): T

        /**
         * Maps an unknown variant of [MessageCountTokensTool] to a value of type [T].
         *
         * An instance of [MessageCountTokensTool] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown MessageCountTokensTool: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<MessageCountTokensTool>(MessageCountTokensTool::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): MessageCountTokensTool {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<Tool>())?.let {
                            MessageCountTokensTool(tool = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolBash20250124>())?.let {
                            MessageCountTokensTool(toolBash20250124 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolTextEditor20250124>())?.let {
                            MessageCountTokensTool(toolTextEditor20250124 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<TextEditor20250429>())?.let {
                            MessageCountTokensTool(textEditor20250429 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<WebSearchTool20250305>())?.let {
                            MessageCountTokensTool(webSearchTool20250305 = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> MessageCountTokensTool(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer :
        BaseSerializer<MessageCountTokensTool>(MessageCountTokensTool::class) {

        override fun serialize(
            value: MessageCountTokensTool,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.tool != null -> generator.writeObject(value.tool)
                value.toolBash20250124 != null -> generator.writeObject(value.toolBash20250124)
                value.toolTextEditor20250124 != null ->
                    generator.writeObject(value.toolTextEditor20250124)
                value.textEditor20250429 != null -> generator.writeObject(value.textEditor20250429)
                value.webSearchTool20250305 != null ->
                    generator.writeObject(value.webSearchTool20250305)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid MessageCountTokensTool")
            }
        }
    }

    class TextEditor20250429
    private constructor(
        private val name: JsonValue,
        private val type: JsonValue,
        private val cacheControl: JsonField<CacheControlEphemeral>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("name") @ExcludeMissing name: JsonValue = JsonMissing.of(),
            @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
            @JsonProperty("cache_control")
            @ExcludeMissing
            cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
        ) : this(name, type, cacheControl, mutableMapOf())

        /**
         * Name of the tool.
         *
         * This is how the tool will be called by the model and in `tool_use` blocks.
         *
         * Expected to always return the following:
         * ```java
         * JsonValue.from("str_replace_based_edit_tool")
         * ```
         *
         * However, this method can be useful for debugging and logging (e.g. if the server
         * responded with an unexpected value).
         */
        @JsonProperty("name") @ExcludeMissing fun _name(): JsonValue = name

        /**
         * Expected to always return the following:
         * ```java
         * JsonValue.from("text_editor_20250429")
         * ```
         *
         * However, this method can be useful for debugging and logging (e.g. if the server
         * responded with an unexpected value).
         */
        @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

        /**
         * Create a cache control breakpoint at this content block.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun cacheControl(): Optional<CacheControlEphemeral> =
            cacheControl.getOptional("cache_control")

        /**
         * Returns the raw JSON value of [cacheControl].
         *
         * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected
         * type.
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

            /** Returns a mutable builder for constructing an instance of [TextEditor20250429]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [TextEditor20250429]. */
        class Builder internal constructor() {

            private var name: JsonValue = JsonValue.from("str_replace_based_edit_tool")
            private var type: JsonValue = JsonValue.from("text_editor_20250429")
            private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(textEditor20250429: TextEditor20250429) = apply {
                name = textEditor20250429.name
                type = textEditor20250429.type
                cacheControl = textEditor20250429.cacheControl
                additionalProperties = textEditor20250429.additionalProperties.toMutableMap()
            }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * It is usually unnecessary to call this method because the field defaults to the
             * following:
             * ```java
             * JsonValue.from("str_replace_based_edit_tool")
             * ```
             *
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun name(name: JsonValue) = apply { this.name = name }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * It is usually unnecessary to call this method because the field defaults to the
             * following:
             * ```java
             * JsonValue.from("text_editor_20250429")
             * ```
             *
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
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
             * You should usually call [Builder.cacheControl] with a well-typed
             * [CacheControlEphemeral] value instead. This method is primarily for setting the field
             * to an undocumented or not yet supported value.
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
             * Returns an immutable instance of [TextEditor20250429].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): TextEditor20250429 =
                TextEditor20250429(name, type, cacheControl, additionalProperties.toMutableMap())
        }

        private var validated: Boolean = false

        fun validate(): TextEditor20250429 = apply {
            if (validated) {
                return@apply
            }

            _name().let {
                if (it != JsonValue.from("str_replace_based_edit_tool")) {
                    throw AnthropicInvalidDataException("'name' is invalid, received $it")
                }
            }
            _type().let {
                if (it != JsonValue.from("text_editor_20250429")) {
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            name.let { if (it == JsonValue.from("str_replace_based_edit_tool")) 1 else 0 } +
                type.let { if (it == JsonValue.from("text_editor_20250429")) 1 else 0 } +
                (cacheControl.asKnown().getOrNull()?.validity() ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is TextEditor20250429 && name == other.name && type == other.type && cacheControl == other.cacheControl && additionalProperties == other.additionalProperties /* spotless:on */
        }

        /* spotless:off */
        private val hashCode: Int by lazy { Objects.hash(name, type, cacheControl, additionalProperties) }
        /* spotless:on */

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "TextEditor20250429{name=$name, type=$type, cacheControl=$cacheControl, additionalProperties=$additionalProperties}"
    }
}
