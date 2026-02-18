// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional

/** Code execution tool with REPL state persistence (daemon mode + gVisor checkpoint). */
@JsonDeserialize(using = MessageCountTokensTool.Deserializer::class)
@JsonSerialize(using = MessageCountTokensTool.Serializer::class)
class MessageCountTokensTool
private constructor(
    private val tool: Tool? = null,
    private val toolBash20250124: ToolBash20250124? = null,
    private val codeExecutionTool20250522: CodeExecutionTool20250522? = null,
    private val codeExecutionTool20250825: CodeExecutionTool20250825? = null,
    private val codeExecutionTool20260120: CodeExecutionTool20260120? = null,
    private val memoryTool20250818: MemoryTool20250818? = null,
    private val toolTextEditor20250124: ToolTextEditor20250124? = null,
    private val toolTextEditor20250429: ToolTextEditor20250429? = null,
    private val toolTextEditor20250728: ToolTextEditor20250728? = null,
    private val webSearchTool20250305: WebSearchTool20250305? = null,
    private val webFetchTool20250910: WebFetchTool20250910? = null,
    private val webSearchTool20260209: WebSearchTool20260209? = null,
    private val webFetchTool20260209: WebFetchTool20260209? = null,
    private val toolSearchToolBm25_20251119: ToolSearchToolBm25_20251119? = null,
    private val toolSearchToolRegex20251119: ToolSearchToolRegex20251119? = null,
    private val _json: JsonValue? = null,
) {

    fun tool(): Optional<Tool> = Optional.ofNullable(tool)

    fun toolBash20250124(): Optional<ToolBash20250124> = Optional.ofNullable(toolBash20250124)

    fun codeExecutionTool20250522(): Optional<CodeExecutionTool20250522> =
        Optional.ofNullable(codeExecutionTool20250522)

    fun codeExecutionTool20250825(): Optional<CodeExecutionTool20250825> =
        Optional.ofNullable(codeExecutionTool20250825)

    /** Code execution tool with REPL state persistence (daemon mode + gVisor checkpoint). */
    fun codeExecutionTool20260120(): Optional<CodeExecutionTool20260120> =
        Optional.ofNullable(codeExecutionTool20260120)

    fun memoryTool20250818(): Optional<MemoryTool20250818> = Optional.ofNullable(memoryTool20250818)

    fun toolTextEditor20250124(): Optional<ToolTextEditor20250124> =
        Optional.ofNullable(toolTextEditor20250124)

    fun toolTextEditor20250429(): Optional<ToolTextEditor20250429> =
        Optional.ofNullable(toolTextEditor20250429)

    fun toolTextEditor20250728(): Optional<ToolTextEditor20250728> =
        Optional.ofNullable(toolTextEditor20250728)

    fun webSearchTool20250305(): Optional<WebSearchTool20250305> =
        Optional.ofNullable(webSearchTool20250305)

    fun webFetchTool20250910(): Optional<WebFetchTool20250910> =
        Optional.ofNullable(webFetchTool20250910)

    fun webSearchTool20260209(): Optional<WebSearchTool20260209> =
        Optional.ofNullable(webSearchTool20260209)

    fun webFetchTool20260209(): Optional<WebFetchTool20260209> =
        Optional.ofNullable(webFetchTool20260209)

    fun toolSearchToolBm25_20251119(): Optional<ToolSearchToolBm25_20251119> =
        Optional.ofNullable(toolSearchToolBm25_20251119)

    fun toolSearchToolRegex20251119(): Optional<ToolSearchToolRegex20251119> =
        Optional.ofNullable(toolSearchToolRegex20251119)

    fun isTool(): Boolean = tool != null

    fun isToolBash20250124(): Boolean = toolBash20250124 != null

    fun isCodeExecutionTool20250522(): Boolean = codeExecutionTool20250522 != null

    fun isCodeExecutionTool20250825(): Boolean = codeExecutionTool20250825 != null

    fun isCodeExecutionTool20260120(): Boolean = codeExecutionTool20260120 != null

    fun isMemoryTool20250818(): Boolean = memoryTool20250818 != null

    fun isToolTextEditor20250124(): Boolean = toolTextEditor20250124 != null

    fun isToolTextEditor20250429(): Boolean = toolTextEditor20250429 != null

    fun isToolTextEditor20250728(): Boolean = toolTextEditor20250728 != null

    fun isWebSearchTool20250305(): Boolean = webSearchTool20250305 != null

    fun isWebFetchTool20250910(): Boolean = webFetchTool20250910 != null

    fun isWebSearchTool20260209(): Boolean = webSearchTool20260209 != null

    fun isWebFetchTool20260209(): Boolean = webFetchTool20260209 != null

    fun isToolSearchToolBm25_20251119(): Boolean = toolSearchToolBm25_20251119 != null

    fun isToolSearchToolRegex20251119(): Boolean = toolSearchToolRegex20251119 != null

    fun asTool(): Tool = tool.getOrThrow("tool")

    fun asToolBash20250124(): ToolBash20250124 = toolBash20250124.getOrThrow("toolBash20250124")

    fun asCodeExecutionTool20250522(): CodeExecutionTool20250522 =
        codeExecutionTool20250522.getOrThrow("codeExecutionTool20250522")

    fun asCodeExecutionTool20250825(): CodeExecutionTool20250825 =
        codeExecutionTool20250825.getOrThrow("codeExecutionTool20250825")

    /** Code execution tool with REPL state persistence (daemon mode + gVisor checkpoint). */
    fun asCodeExecutionTool20260120(): CodeExecutionTool20260120 =
        codeExecutionTool20260120.getOrThrow("codeExecutionTool20260120")

    fun asMemoryTool20250818(): MemoryTool20250818 =
        memoryTool20250818.getOrThrow("memoryTool20250818")

    fun asToolTextEditor20250124(): ToolTextEditor20250124 =
        toolTextEditor20250124.getOrThrow("toolTextEditor20250124")

    fun asToolTextEditor20250429(): ToolTextEditor20250429 =
        toolTextEditor20250429.getOrThrow("toolTextEditor20250429")

    fun asToolTextEditor20250728(): ToolTextEditor20250728 =
        toolTextEditor20250728.getOrThrow("toolTextEditor20250728")

    fun asWebSearchTool20250305(): WebSearchTool20250305 =
        webSearchTool20250305.getOrThrow("webSearchTool20250305")

    fun asWebFetchTool20250910(): WebFetchTool20250910 =
        webFetchTool20250910.getOrThrow("webFetchTool20250910")

    fun asWebSearchTool20260209(): WebSearchTool20260209 =
        webSearchTool20260209.getOrThrow("webSearchTool20260209")

    fun asWebFetchTool20260209(): WebFetchTool20260209 =
        webFetchTool20260209.getOrThrow("webFetchTool20260209")

    fun asToolSearchToolBm25_20251119(): ToolSearchToolBm25_20251119 =
        toolSearchToolBm25_20251119.getOrThrow("toolSearchToolBm25_20251119")

    fun asToolSearchToolRegex20251119(): ToolSearchToolRegex20251119 =
        toolSearchToolRegex20251119.getOrThrow("toolSearchToolRegex20251119")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            tool != null -> visitor.visitTool(tool)
            toolBash20250124 != null -> visitor.visitToolBash20250124(toolBash20250124)
            codeExecutionTool20250522 != null ->
                visitor.visitCodeExecutionTool20250522(codeExecutionTool20250522)
            codeExecutionTool20250825 != null ->
                visitor.visitCodeExecutionTool20250825(codeExecutionTool20250825)
            codeExecutionTool20260120 != null ->
                visitor.visitCodeExecutionTool20260120(codeExecutionTool20260120)
            memoryTool20250818 != null -> visitor.visitMemoryTool20250818(memoryTool20250818)
            toolTextEditor20250124 != null ->
                visitor.visitToolTextEditor20250124(toolTextEditor20250124)
            toolTextEditor20250429 != null ->
                visitor.visitToolTextEditor20250429(toolTextEditor20250429)
            toolTextEditor20250728 != null ->
                visitor.visitToolTextEditor20250728(toolTextEditor20250728)
            webSearchTool20250305 != null ->
                visitor.visitWebSearchTool20250305(webSearchTool20250305)
            webFetchTool20250910 != null -> visitor.visitWebFetchTool20250910(webFetchTool20250910)
            webSearchTool20260209 != null ->
                visitor.visitWebSearchTool20260209(webSearchTool20260209)
            webFetchTool20260209 != null -> visitor.visitWebFetchTool20260209(webFetchTool20260209)
            toolSearchToolBm25_20251119 != null ->
                visitor.visitToolSearchToolBm25_20251119(toolSearchToolBm25_20251119)
            toolSearchToolRegex20251119 != null ->
                visitor.visitToolSearchToolRegex20251119(toolSearchToolRegex20251119)
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

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: CodeExecutionTool20250522
                ) {
                    codeExecutionTool20250522.validate()
                }

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: CodeExecutionTool20250825
                ) {
                    codeExecutionTool20250825.validate()
                }

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: CodeExecutionTool20260120
                ) {
                    codeExecutionTool20260120.validate()
                }

                override fun visitMemoryTool20250818(memoryTool20250818: MemoryTool20250818) {
                    memoryTool20250818.validate()
                }

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: ToolTextEditor20250124
                ) {
                    toolTextEditor20250124.validate()
                }

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: ToolTextEditor20250429
                ) {
                    toolTextEditor20250429.validate()
                }

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: ToolTextEditor20250728
                ) {
                    toolTextEditor20250728.validate()
                }

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: WebSearchTool20250305
                ) {
                    webSearchTool20250305.validate()
                }

                override fun visitWebFetchTool20250910(webFetchTool20250910: WebFetchTool20250910) {
                    webFetchTool20250910.validate()
                }

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: WebSearchTool20260209
                ) {
                    webSearchTool20260209.validate()
                }

                override fun visitWebFetchTool20260209(webFetchTool20260209: WebFetchTool20260209) {
                    webFetchTool20260209.validate()
                }

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: ToolSearchToolBm25_20251119
                ) {
                    toolSearchToolBm25_20251119.validate()
                }

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: ToolSearchToolRegex20251119
                ) {
                    toolSearchToolRegex20251119.validate()
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

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: CodeExecutionTool20250522
                ) = codeExecutionTool20250522.validity()

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: CodeExecutionTool20250825
                ) = codeExecutionTool20250825.validity()

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: CodeExecutionTool20260120
                ) = codeExecutionTool20260120.validity()

                override fun visitMemoryTool20250818(memoryTool20250818: MemoryTool20250818) =
                    memoryTool20250818.validity()

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: ToolTextEditor20250124
                ) = toolTextEditor20250124.validity()

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: ToolTextEditor20250429
                ) = toolTextEditor20250429.validity()

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: ToolTextEditor20250728
                ) = toolTextEditor20250728.validity()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: WebSearchTool20250305
                ) = webSearchTool20250305.validity()

                override fun visitWebFetchTool20250910(webFetchTool20250910: WebFetchTool20250910) =
                    webFetchTool20250910.validity()

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: WebSearchTool20260209
                ) = webSearchTool20260209.validity()

                override fun visitWebFetchTool20260209(webFetchTool20260209: WebFetchTool20260209) =
                    webFetchTool20260209.validity()

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: ToolSearchToolBm25_20251119
                ) = toolSearchToolBm25_20251119.validity()

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: ToolSearchToolRegex20251119
                ) = toolSearchToolRegex20251119.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is MessageCountTokensTool &&
            tool == other.tool &&
            toolBash20250124 == other.toolBash20250124 &&
            codeExecutionTool20250522 == other.codeExecutionTool20250522 &&
            codeExecutionTool20250825 == other.codeExecutionTool20250825 &&
            codeExecutionTool20260120 == other.codeExecutionTool20260120 &&
            memoryTool20250818 == other.memoryTool20250818 &&
            toolTextEditor20250124 == other.toolTextEditor20250124 &&
            toolTextEditor20250429 == other.toolTextEditor20250429 &&
            toolTextEditor20250728 == other.toolTextEditor20250728 &&
            webSearchTool20250305 == other.webSearchTool20250305 &&
            webFetchTool20250910 == other.webFetchTool20250910 &&
            webSearchTool20260209 == other.webSearchTool20260209 &&
            webFetchTool20260209 == other.webFetchTool20260209 &&
            toolSearchToolBm25_20251119 == other.toolSearchToolBm25_20251119 &&
            toolSearchToolRegex20251119 == other.toolSearchToolRegex20251119
    }

    override fun hashCode(): Int =
        Objects.hash(
            tool,
            toolBash20250124,
            codeExecutionTool20250522,
            codeExecutionTool20250825,
            codeExecutionTool20260120,
            memoryTool20250818,
            toolTextEditor20250124,
            toolTextEditor20250429,
            toolTextEditor20250728,
            webSearchTool20250305,
            webFetchTool20250910,
            webSearchTool20260209,
            webFetchTool20260209,
            toolSearchToolBm25_20251119,
            toolSearchToolRegex20251119,
        )

    override fun toString(): String =
        when {
            tool != null -> "MessageCountTokensTool{tool=$tool}"
            toolBash20250124 != null -> "MessageCountTokensTool{toolBash20250124=$toolBash20250124}"
            codeExecutionTool20250522 != null ->
                "MessageCountTokensTool{codeExecutionTool20250522=$codeExecutionTool20250522}"
            codeExecutionTool20250825 != null ->
                "MessageCountTokensTool{codeExecutionTool20250825=$codeExecutionTool20250825}"
            codeExecutionTool20260120 != null ->
                "MessageCountTokensTool{codeExecutionTool20260120=$codeExecutionTool20260120}"
            memoryTool20250818 != null ->
                "MessageCountTokensTool{memoryTool20250818=$memoryTool20250818}"
            toolTextEditor20250124 != null ->
                "MessageCountTokensTool{toolTextEditor20250124=$toolTextEditor20250124}"
            toolTextEditor20250429 != null ->
                "MessageCountTokensTool{toolTextEditor20250429=$toolTextEditor20250429}"
            toolTextEditor20250728 != null ->
                "MessageCountTokensTool{toolTextEditor20250728=$toolTextEditor20250728}"
            webSearchTool20250305 != null ->
                "MessageCountTokensTool{webSearchTool20250305=$webSearchTool20250305}"
            webFetchTool20250910 != null ->
                "MessageCountTokensTool{webFetchTool20250910=$webFetchTool20250910}"
            webSearchTool20260209 != null ->
                "MessageCountTokensTool{webSearchTool20260209=$webSearchTool20260209}"
            webFetchTool20260209 != null ->
                "MessageCountTokensTool{webFetchTool20260209=$webFetchTool20260209}"
            toolSearchToolBm25_20251119 != null ->
                "MessageCountTokensTool{toolSearchToolBm25_20251119=$toolSearchToolBm25_20251119}"
            toolSearchToolRegex20251119 != null ->
                "MessageCountTokensTool{toolSearchToolRegex20251119=$toolSearchToolRegex20251119}"
            _json != null -> "MessageCountTokensTool{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid MessageCountTokensTool")
        }

    companion object {

        @JvmStatic fun ofTool(tool: Tool) = MessageCountTokensTool(tool = tool)

        @JvmStatic
        fun ofToolBash20250124(toolBash20250124: ToolBash20250124) =
            MessageCountTokensTool(toolBash20250124 = toolBash20250124)

        @JvmStatic
        fun ofCodeExecutionTool20250522(codeExecutionTool20250522: CodeExecutionTool20250522) =
            MessageCountTokensTool(codeExecutionTool20250522 = codeExecutionTool20250522)

        @JvmStatic
        fun ofCodeExecutionTool20250825(codeExecutionTool20250825: CodeExecutionTool20250825) =
            MessageCountTokensTool(codeExecutionTool20250825 = codeExecutionTool20250825)

        /** Code execution tool with REPL state persistence (daemon mode + gVisor checkpoint). */
        @JvmStatic
        fun ofCodeExecutionTool20260120(codeExecutionTool20260120: CodeExecutionTool20260120) =
            MessageCountTokensTool(codeExecutionTool20260120 = codeExecutionTool20260120)

        @JvmStatic
        fun ofMemoryTool20250818(memoryTool20250818: MemoryTool20250818) =
            MessageCountTokensTool(memoryTool20250818 = memoryTool20250818)

        @JvmStatic
        fun ofToolTextEditor20250124(toolTextEditor20250124: ToolTextEditor20250124) =
            MessageCountTokensTool(toolTextEditor20250124 = toolTextEditor20250124)

        @JvmStatic
        fun ofToolTextEditor20250429(toolTextEditor20250429: ToolTextEditor20250429) =
            MessageCountTokensTool(toolTextEditor20250429 = toolTextEditor20250429)

        @JvmStatic
        fun ofToolTextEditor20250728(toolTextEditor20250728: ToolTextEditor20250728) =
            MessageCountTokensTool(toolTextEditor20250728 = toolTextEditor20250728)

        @JvmStatic
        fun ofWebSearchTool20250305(webSearchTool20250305: WebSearchTool20250305) =
            MessageCountTokensTool(webSearchTool20250305 = webSearchTool20250305)

        @JvmStatic
        fun ofWebFetchTool20250910(webFetchTool20250910: WebFetchTool20250910) =
            MessageCountTokensTool(webFetchTool20250910 = webFetchTool20250910)

        @JvmStatic
        fun ofWebSearchTool20260209(webSearchTool20260209: WebSearchTool20260209) =
            MessageCountTokensTool(webSearchTool20260209 = webSearchTool20260209)

        @JvmStatic
        fun ofWebFetchTool20260209(webFetchTool20260209: WebFetchTool20260209) =
            MessageCountTokensTool(webFetchTool20260209 = webFetchTool20260209)

        @JvmStatic
        fun ofToolSearchToolBm25_20251119(
            toolSearchToolBm25_20251119: ToolSearchToolBm25_20251119
        ) = MessageCountTokensTool(toolSearchToolBm25_20251119 = toolSearchToolBm25_20251119)

        @JvmStatic
        fun ofToolSearchToolRegex20251119(
            toolSearchToolRegex20251119: ToolSearchToolRegex20251119
        ) = MessageCountTokensTool(toolSearchToolRegex20251119 = toolSearchToolRegex20251119)
    }

    /**
     * An interface that defines how to map each variant of [MessageCountTokensTool] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitTool(tool: Tool): T

        fun visitToolBash20250124(toolBash20250124: ToolBash20250124): T

        fun visitCodeExecutionTool20250522(codeExecutionTool20250522: CodeExecutionTool20250522): T

        fun visitCodeExecutionTool20250825(codeExecutionTool20250825: CodeExecutionTool20250825): T

        /** Code execution tool with REPL state persistence (daemon mode + gVisor checkpoint). */
        fun visitCodeExecutionTool20260120(codeExecutionTool20260120: CodeExecutionTool20260120): T

        fun visitMemoryTool20250818(memoryTool20250818: MemoryTool20250818): T

        fun visitToolTextEditor20250124(toolTextEditor20250124: ToolTextEditor20250124): T

        fun visitToolTextEditor20250429(toolTextEditor20250429: ToolTextEditor20250429): T

        fun visitToolTextEditor20250728(toolTextEditor20250728: ToolTextEditor20250728): T

        fun visitWebSearchTool20250305(webSearchTool20250305: WebSearchTool20250305): T

        fun visitWebFetchTool20250910(webFetchTool20250910: WebFetchTool20250910): T

        fun visitWebSearchTool20260209(webSearchTool20260209: WebSearchTool20260209): T

        fun visitWebFetchTool20260209(webFetchTool20260209: WebFetchTool20260209): T

        fun visitToolSearchToolBm25_20251119(
            toolSearchToolBm25_20251119: ToolSearchToolBm25_20251119
        ): T

        fun visitToolSearchToolRegex20251119(
            toolSearchToolRegex20251119: ToolSearchToolRegex20251119
        ): T

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
                        tryDeserialize(node, jacksonTypeRef<CodeExecutionTool20250522>())?.let {
                            MessageCountTokensTool(codeExecutionTool20250522 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<CodeExecutionTool20250825>())?.let {
                            MessageCountTokensTool(codeExecutionTool20250825 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<CodeExecutionTool20260120>())?.let {
                            MessageCountTokensTool(codeExecutionTool20260120 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<MemoryTool20250818>())?.let {
                            MessageCountTokensTool(memoryTool20250818 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolTextEditor20250124>())?.let {
                            MessageCountTokensTool(toolTextEditor20250124 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolTextEditor20250429>())?.let {
                            MessageCountTokensTool(toolTextEditor20250429 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolTextEditor20250728>())?.let {
                            MessageCountTokensTool(toolTextEditor20250728 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<WebSearchTool20250305>())?.let {
                            MessageCountTokensTool(webSearchTool20250305 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<WebFetchTool20250910>())?.let {
                            MessageCountTokensTool(webFetchTool20250910 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<WebSearchTool20260209>())?.let {
                            MessageCountTokensTool(webSearchTool20260209 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<WebFetchTool20260209>())?.let {
                            MessageCountTokensTool(webFetchTool20260209 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolSearchToolBm25_20251119>())?.let {
                            MessageCountTokensTool(toolSearchToolBm25_20251119 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolSearchToolRegex20251119>())?.let {
                            MessageCountTokensTool(toolSearchToolRegex20251119 = it, _json = json)
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
                value.codeExecutionTool20250522 != null ->
                    generator.writeObject(value.codeExecutionTool20250522)
                value.codeExecutionTool20250825 != null ->
                    generator.writeObject(value.codeExecutionTool20250825)
                value.codeExecutionTool20260120 != null ->
                    generator.writeObject(value.codeExecutionTool20260120)
                value.memoryTool20250818 != null -> generator.writeObject(value.memoryTool20250818)
                value.toolTextEditor20250124 != null ->
                    generator.writeObject(value.toolTextEditor20250124)
                value.toolTextEditor20250429 != null ->
                    generator.writeObject(value.toolTextEditor20250429)
                value.toolTextEditor20250728 != null ->
                    generator.writeObject(value.toolTextEditor20250728)
                value.webSearchTool20250305 != null ->
                    generator.writeObject(value.webSearchTool20250305)
                value.webFetchTool20250910 != null ->
                    generator.writeObject(value.webFetchTool20250910)
                value.webSearchTool20260209 != null ->
                    generator.writeObject(value.webSearchTool20260209)
                value.webFetchTool20260209 != null ->
                    generator.writeObject(value.webFetchTool20260209)
                value.toolSearchToolBm25_20251119 != null ->
                    generator.writeObject(value.toolSearchToolBm25_20251119)
                value.toolSearchToolRegex20251119 != null ->
                    generator.writeObject(value.toolSearchToolRegex20251119)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid MessageCountTokensTool")
            }
        }
    }
}
