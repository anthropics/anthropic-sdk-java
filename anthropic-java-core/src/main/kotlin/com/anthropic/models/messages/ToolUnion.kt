// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
import com.anthropic.core.checkKnown
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

/** Code execution tool with REPL state persistence (daemon mode + gVisor checkpoint). */
@JsonDeserialize(using = ToolUnion.Deserializer::class)
@JsonSerialize(using = ToolUnion.Serializer::class)
class ToolUnion
private constructor(
    private val tool: Tool? = null,
    private val bash20250124: ToolBash20250124? = null,
    private val codeExecutionTool20250522: CodeExecutionTool20250522? = null,
    private val codeExecutionTool20250825: CodeExecutionTool20250825? = null,
    private val codeExecutionTool20260120: CodeExecutionTool20260120? = null,
    private val memoryTool20250818: MemoryTool20250818? = null,
    private val textEditor20250124: ToolTextEditor20250124? = null,
    private val textEditor20250429: ToolTextEditor20250429? = null,
    private val textEditor20250728: ToolTextEditor20250728? = null,
    private val webSearchTool20250305: WebSearchTool20250305? = null,
    private val webFetchTool20250910: WebFetchTool20250910? = null,
    private val webSearchTool20260209: WebSearchTool20260209? = null,
    private val webFetchTool20260209: WebFetchTool20260209? = null,
    private val searchToolBm25_20251119: ToolSearchToolBm25_20251119? = null,
    private val searchToolRegex20251119: ToolSearchToolRegex20251119? = null,
    private val _json: JsonValue? = null,
) {

    fun tool(): Optional<Tool> = Optional.ofNullable(tool)

    fun bash20250124(): Optional<ToolBash20250124> = Optional.ofNullable(bash20250124)

    fun codeExecutionTool20250522(): Optional<CodeExecutionTool20250522> =
        Optional.ofNullable(codeExecutionTool20250522)

    fun codeExecutionTool20250825(): Optional<CodeExecutionTool20250825> =
        Optional.ofNullable(codeExecutionTool20250825)

    /** Code execution tool with REPL state persistence (daemon mode + gVisor checkpoint). */
    fun codeExecutionTool20260120(): Optional<CodeExecutionTool20260120> =
        Optional.ofNullable(codeExecutionTool20260120)

    fun memoryTool20250818(): Optional<MemoryTool20250818> = Optional.ofNullable(memoryTool20250818)

    fun textEditor20250124(): Optional<ToolTextEditor20250124> =
        Optional.ofNullable(textEditor20250124)

    fun textEditor20250429(): Optional<ToolTextEditor20250429> =
        Optional.ofNullable(textEditor20250429)

    fun textEditor20250728(): Optional<ToolTextEditor20250728> =
        Optional.ofNullable(textEditor20250728)

    fun webSearchTool20250305(): Optional<WebSearchTool20250305> =
        Optional.ofNullable(webSearchTool20250305)

    fun webFetchTool20250910(): Optional<WebFetchTool20250910> =
        Optional.ofNullable(webFetchTool20250910)

    fun webSearchTool20260209(): Optional<WebSearchTool20260209> =
        Optional.ofNullable(webSearchTool20260209)

    fun webFetchTool20260209(): Optional<WebFetchTool20260209> =
        Optional.ofNullable(webFetchTool20260209)

    fun searchToolBm25_20251119(): Optional<ToolSearchToolBm25_20251119> =
        Optional.ofNullable(searchToolBm25_20251119)

    fun searchToolRegex20251119(): Optional<ToolSearchToolRegex20251119> =
        Optional.ofNullable(searchToolRegex20251119)

    fun isTool(): Boolean = tool != null

    fun isBash20250124(): Boolean = bash20250124 != null

    fun isCodeExecutionTool20250522(): Boolean = codeExecutionTool20250522 != null

    fun isCodeExecutionTool20250825(): Boolean = codeExecutionTool20250825 != null

    fun isCodeExecutionTool20260120(): Boolean = codeExecutionTool20260120 != null

    fun isMemoryTool20250818(): Boolean = memoryTool20250818 != null

    fun isTextEditor20250124(): Boolean = textEditor20250124 != null

    fun isTextEditor20250429(): Boolean = textEditor20250429 != null

    fun isTextEditor20250728(): Boolean = textEditor20250728 != null

    fun isWebSearchTool20250305(): Boolean = webSearchTool20250305 != null

    fun isWebFetchTool20250910(): Boolean = webFetchTool20250910 != null

    fun isWebSearchTool20260209(): Boolean = webSearchTool20260209 != null

    fun isWebFetchTool20260209(): Boolean = webFetchTool20260209 != null

    fun isSearchToolBm25_20251119(): Boolean = searchToolBm25_20251119 != null

    fun isSearchToolRegex20251119(): Boolean = searchToolRegex20251119 != null

    fun asTool(): Tool = tool.getOrThrow("tool")

    fun asBash20250124(): ToolBash20250124 = bash20250124.getOrThrow("bash20250124")

    fun asCodeExecutionTool20250522(): CodeExecutionTool20250522 =
        codeExecutionTool20250522.getOrThrow("codeExecutionTool20250522")

    fun asCodeExecutionTool20250825(): CodeExecutionTool20250825 =
        codeExecutionTool20250825.getOrThrow("codeExecutionTool20250825")

    /** Code execution tool with REPL state persistence (daemon mode + gVisor checkpoint). */
    fun asCodeExecutionTool20260120(): CodeExecutionTool20260120 =
        codeExecutionTool20260120.getOrThrow("codeExecutionTool20260120")

    fun asMemoryTool20250818(): MemoryTool20250818 =
        memoryTool20250818.getOrThrow("memoryTool20250818")

    fun asTextEditor20250124(): ToolTextEditor20250124 =
        textEditor20250124.getOrThrow("textEditor20250124")

    fun asTextEditor20250429(): ToolTextEditor20250429 =
        textEditor20250429.getOrThrow("textEditor20250429")

    fun asTextEditor20250728(): ToolTextEditor20250728 =
        textEditor20250728.getOrThrow("textEditor20250728")

    fun asWebSearchTool20250305(): WebSearchTool20250305 =
        webSearchTool20250305.getOrThrow("webSearchTool20250305")

    fun asWebFetchTool20250910(): WebFetchTool20250910 =
        webFetchTool20250910.getOrThrow("webFetchTool20250910")

    fun asWebSearchTool20260209(): WebSearchTool20260209 =
        webSearchTool20260209.getOrThrow("webSearchTool20260209")

    fun asWebFetchTool20260209(): WebFetchTool20260209 =
        webFetchTool20260209.getOrThrow("webFetchTool20260209")

    fun asSearchToolBm25_20251119(): ToolSearchToolBm25_20251119 =
        searchToolBm25_20251119.getOrThrow("searchToolBm25_20251119")

    fun asSearchToolRegex20251119(): ToolSearchToolRegex20251119 =
        searchToolRegex20251119.getOrThrow("searchToolRegex20251119")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            tool != null -> visitor.visitTool(tool)
            bash20250124 != null -> visitor.visitBash20250124(bash20250124)
            codeExecutionTool20250522 != null ->
                visitor.visitCodeExecutionTool20250522(codeExecutionTool20250522)
            codeExecutionTool20250825 != null ->
                visitor.visitCodeExecutionTool20250825(codeExecutionTool20250825)
            codeExecutionTool20260120 != null ->
                visitor.visitCodeExecutionTool20260120(codeExecutionTool20260120)
            memoryTool20250818 != null -> visitor.visitMemoryTool20250818(memoryTool20250818)
            textEditor20250124 != null -> visitor.visitTextEditor20250124(textEditor20250124)
            textEditor20250429 != null -> visitor.visitTextEditor20250429(textEditor20250429)
            textEditor20250728 != null -> visitor.visitTextEditor20250728(textEditor20250728)
            webSearchTool20250305 != null ->
                visitor.visitWebSearchTool20250305(webSearchTool20250305)
            webFetchTool20250910 != null -> visitor.visitWebFetchTool20250910(webFetchTool20250910)
            webSearchTool20260209 != null ->
                visitor.visitWebSearchTool20260209(webSearchTool20260209)
            webFetchTool20260209 != null -> visitor.visitWebFetchTool20260209(webFetchTool20260209)
            searchToolBm25_20251119 != null ->
                visitor.visitSearchToolBm25_20251119(searchToolBm25_20251119)
            searchToolRegex20251119 != null ->
                visitor.visitSearchToolRegex20251119(searchToolRegex20251119)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): ToolUnion = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitTool(tool: Tool) {
                    tool.validate()
                }

                override fun visitBash20250124(bash20250124: ToolBash20250124) {
                    bash20250124.validate()
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

                override fun visitTextEditor20250124(textEditor20250124: ToolTextEditor20250124) {
                    textEditor20250124.validate()
                }

                override fun visitTextEditor20250429(textEditor20250429: ToolTextEditor20250429) {
                    textEditor20250429.validate()
                }

                override fun visitTextEditor20250728(textEditor20250728: ToolTextEditor20250728) {
                    textEditor20250728.validate()
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

                override fun visitSearchToolBm25_20251119(
                    searchToolBm25_20251119: ToolSearchToolBm25_20251119
                ) {
                    searchToolBm25_20251119.validate()
                }

                override fun visitSearchToolRegex20251119(
                    searchToolRegex20251119: ToolSearchToolRegex20251119
                ) {
                    searchToolRegex20251119.validate()
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

                override fun visitBash20250124(bash20250124: ToolBash20250124) =
                    bash20250124.validity()

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

                override fun visitTextEditor20250124(textEditor20250124: ToolTextEditor20250124) =
                    textEditor20250124.validity()

                override fun visitTextEditor20250429(textEditor20250429: ToolTextEditor20250429) =
                    textEditor20250429.validity()

                override fun visitTextEditor20250728(textEditor20250728: ToolTextEditor20250728) =
                    textEditor20250728.validity()

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

                override fun visitSearchToolBm25_20251119(
                    searchToolBm25_20251119: ToolSearchToolBm25_20251119
                ) = searchToolBm25_20251119.validity()

                override fun visitSearchToolRegex20251119(
                    searchToolRegex20251119: ToolSearchToolRegex20251119
                ) = searchToolRegex20251119.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ToolUnion &&
            tool == other.tool &&
            bash20250124 == other.bash20250124 &&
            codeExecutionTool20250522 == other.codeExecutionTool20250522 &&
            codeExecutionTool20250825 == other.codeExecutionTool20250825 &&
            codeExecutionTool20260120 == other.codeExecutionTool20260120 &&
            memoryTool20250818 == other.memoryTool20250818 &&
            textEditor20250124 == other.textEditor20250124 &&
            textEditor20250429 == other.textEditor20250429 &&
            textEditor20250728 == other.textEditor20250728 &&
            webSearchTool20250305 == other.webSearchTool20250305 &&
            webFetchTool20250910 == other.webFetchTool20250910 &&
            webSearchTool20260209 == other.webSearchTool20260209 &&
            webFetchTool20260209 == other.webFetchTool20260209 &&
            searchToolBm25_20251119 == other.searchToolBm25_20251119 &&
            searchToolRegex20251119 == other.searchToolRegex20251119
    }

    override fun hashCode(): Int =
        Objects.hash(
            tool,
            bash20250124,
            codeExecutionTool20250522,
            codeExecutionTool20250825,
            codeExecutionTool20260120,
            memoryTool20250818,
            textEditor20250124,
            textEditor20250429,
            textEditor20250728,
            webSearchTool20250305,
            webFetchTool20250910,
            webSearchTool20260209,
            webFetchTool20260209,
            searchToolBm25_20251119,
            searchToolRegex20251119,
        )

    override fun toString(): String =
        when {
            tool != null -> "ToolUnion{tool=$tool}"
            bash20250124 != null -> "ToolUnion{bash20250124=$bash20250124}"
            codeExecutionTool20250522 != null ->
                "ToolUnion{codeExecutionTool20250522=$codeExecutionTool20250522}"
            codeExecutionTool20250825 != null ->
                "ToolUnion{codeExecutionTool20250825=$codeExecutionTool20250825}"
            codeExecutionTool20260120 != null ->
                "ToolUnion{codeExecutionTool20260120=$codeExecutionTool20260120}"
            memoryTool20250818 != null -> "ToolUnion{memoryTool20250818=$memoryTool20250818}"
            textEditor20250124 != null -> "ToolUnion{textEditor20250124=$textEditor20250124}"
            textEditor20250429 != null -> "ToolUnion{textEditor20250429=$textEditor20250429}"
            textEditor20250728 != null -> "ToolUnion{textEditor20250728=$textEditor20250728}"
            webSearchTool20250305 != null ->
                "ToolUnion{webSearchTool20250305=$webSearchTool20250305}"
            webFetchTool20250910 != null -> "ToolUnion{webFetchTool20250910=$webFetchTool20250910}"
            webSearchTool20260209 != null ->
                "ToolUnion{webSearchTool20260209=$webSearchTool20260209}"
            webFetchTool20260209 != null -> "ToolUnion{webFetchTool20260209=$webFetchTool20260209}"
            searchToolBm25_20251119 != null ->
                "ToolUnion{searchToolBm25_20251119=$searchToolBm25_20251119}"
            searchToolRegex20251119 != null ->
                "ToolUnion{searchToolRegex20251119=$searchToolRegex20251119}"
            _json != null -> "ToolUnion{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ToolUnion")
        }

    companion object {

        @JvmStatic fun ofTool(tool: Tool) = ToolUnion(tool = tool)

        @JvmStatic
        fun ofBash20250124(bash20250124: ToolBash20250124) = ToolUnion(bash20250124 = bash20250124)

        @JvmStatic
        fun ofCodeExecutionTool20250522(codeExecutionTool20250522: CodeExecutionTool20250522) =
            ToolUnion(codeExecutionTool20250522 = codeExecutionTool20250522)

        @JvmStatic
        fun ofCodeExecutionTool20250825(codeExecutionTool20250825: CodeExecutionTool20250825) =
            ToolUnion(codeExecutionTool20250825 = codeExecutionTool20250825)

        /** Code execution tool with REPL state persistence (daemon mode + gVisor checkpoint). */
        @JvmStatic
        fun ofCodeExecutionTool20260120(codeExecutionTool20260120: CodeExecutionTool20260120) =
            ToolUnion(codeExecutionTool20260120 = codeExecutionTool20260120)

        @JvmStatic
        fun ofMemoryTool20250818(memoryTool20250818: MemoryTool20250818) =
            ToolUnion(memoryTool20250818 = memoryTool20250818)

        @JvmStatic
        fun ofTextEditor20250124(textEditor20250124: ToolTextEditor20250124) =
            ToolUnion(textEditor20250124 = textEditor20250124)

        @JvmStatic
        fun ofTextEditor20250429(textEditor20250429: ToolTextEditor20250429) =
            ToolUnion(textEditor20250429 = textEditor20250429)

        @JvmStatic
        fun ofTextEditor20250728(textEditor20250728: ToolTextEditor20250728) =
            ToolUnion(textEditor20250728 = textEditor20250728)

        @JvmStatic
        fun ofWebSearchTool20250305(webSearchTool20250305: WebSearchTool20250305) =
            ToolUnion(webSearchTool20250305 = webSearchTool20250305)

        @JvmStatic
        fun ofWebFetchTool20250910(webFetchTool20250910: WebFetchTool20250910) =
            ToolUnion(webFetchTool20250910 = webFetchTool20250910)

        @JvmStatic
        fun ofWebSearchTool20260209(webSearchTool20260209: WebSearchTool20260209) =
            ToolUnion(webSearchTool20260209 = webSearchTool20260209)

        @JvmStatic
        fun ofWebFetchTool20260209(webFetchTool20260209: WebFetchTool20260209) =
            ToolUnion(webFetchTool20260209 = webFetchTool20260209)

        @JvmStatic
        fun ofSearchToolBm25_20251119(searchToolBm25_20251119: ToolSearchToolBm25_20251119) =
            ToolUnion(searchToolBm25_20251119 = searchToolBm25_20251119)

        @JvmStatic
        fun ofSearchToolRegex20251119(searchToolRegex20251119: ToolSearchToolRegex20251119) =
            ToolUnion(searchToolRegex20251119 = searchToolRegex20251119)
    }

    /** An interface that defines how to map each variant of [ToolUnion] to a value of type [T]. */
    interface Visitor<out T> {

        fun visitTool(tool: Tool): T

        fun visitBash20250124(bash20250124: ToolBash20250124): T

        fun visitCodeExecutionTool20250522(codeExecutionTool20250522: CodeExecutionTool20250522): T

        fun visitCodeExecutionTool20250825(codeExecutionTool20250825: CodeExecutionTool20250825): T

        /** Code execution tool with REPL state persistence (daemon mode + gVisor checkpoint). */
        fun visitCodeExecutionTool20260120(codeExecutionTool20260120: CodeExecutionTool20260120): T

        fun visitMemoryTool20250818(memoryTool20250818: MemoryTool20250818): T

        fun visitTextEditor20250124(textEditor20250124: ToolTextEditor20250124): T

        fun visitTextEditor20250429(textEditor20250429: ToolTextEditor20250429): T

        fun visitTextEditor20250728(textEditor20250728: ToolTextEditor20250728): T

        fun visitWebSearchTool20250305(webSearchTool20250305: WebSearchTool20250305): T

        fun visitWebFetchTool20250910(webFetchTool20250910: WebFetchTool20250910): T

        fun visitWebSearchTool20260209(webSearchTool20260209: WebSearchTool20260209): T

        fun visitWebFetchTool20260209(webFetchTool20260209: WebFetchTool20260209): T

        fun visitSearchToolBm25_20251119(searchToolBm25_20251119: ToolSearchToolBm25_20251119): T

        fun visitSearchToolRegex20251119(searchToolRegex20251119: ToolSearchToolRegex20251119): T

        /**
         * Maps an unknown variant of [ToolUnion] to a value of type [T].
         *
         * An instance of [ToolUnion] can contain an unknown variant if it was deserialized from
         * data that doesn't match any known variant. For example, if the SDK is on an older version
         * than the API, then the API may respond with new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown ToolUnion: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<ToolUnion>(ToolUnion::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): ToolUnion {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<Tool>())?.let {
                            ToolUnion(tool = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolBash20250124>())?.let {
                            ToolUnion(bash20250124 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<CodeExecutionTool20250522>())?.let {
                            ToolUnion(codeExecutionTool20250522 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<CodeExecutionTool20250825>())?.let {
                            ToolUnion(codeExecutionTool20250825 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<CodeExecutionTool20260120>())?.let {
                            ToolUnion(codeExecutionTool20260120 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<MemoryTool20250818>())?.let {
                            ToolUnion(memoryTool20250818 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolTextEditor20250124>())?.let {
                            ToolUnion(textEditor20250124 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolTextEditor20250429>())?.let {
                            ToolUnion(textEditor20250429 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolTextEditor20250728>())?.let {
                            ToolUnion(textEditor20250728 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<WebSearchTool20250305>())?.let {
                            ToolUnion(webSearchTool20250305 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<WebFetchTool20250910>())?.let {
                            ToolUnion(webFetchTool20250910 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<WebSearchTool20260209>())?.let {
                            ToolUnion(webSearchTool20260209 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<WebFetchTool20260209>())?.let {
                            ToolUnion(webFetchTool20260209 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolSearchToolBm25_20251119>())?.let {
                            ToolUnion(searchToolBm25_20251119 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolSearchToolRegex20251119>())?.let {
                            ToolUnion(searchToolRegex20251119 = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> ToolUnion(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer : BaseSerializer<ToolUnion>(ToolUnion::class) {

        override fun serialize(
            value: ToolUnion,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.tool != null -> generator.writeObject(value.tool)
                value.bash20250124 != null -> generator.writeObject(value.bash20250124)
                value.codeExecutionTool20250522 != null ->
                    generator.writeObject(value.codeExecutionTool20250522)
                value.codeExecutionTool20250825 != null ->
                    generator.writeObject(value.codeExecutionTool20250825)
                value.codeExecutionTool20260120 != null ->
                    generator.writeObject(value.codeExecutionTool20260120)
                value.memoryTool20250818 != null -> generator.writeObject(value.memoryTool20250818)
                value.textEditor20250124 != null -> generator.writeObject(value.textEditor20250124)
                value.textEditor20250429 != null -> generator.writeObject(value.textEditor20250429)
                value.textEditor20250728 != null -> generator.writeObject(value.textEditor20250728)
                value.webSearchTool20250305 != null ->
                    generator.writeObject(value.webSearchTool20250305)
                value.webFetchTool20250910 != null ->
                    generator.writeObject(value.webFetchTool20250910)
                value.webSearchTool20260209 != null ->
                    generator.writeObject(value.webSearchTool20260209)
                value.webFetchTool20260209 != null ->
                    generator.writeObject(value.webFetchTool20260209)
                value.searchToolBm25_20251119 != null ->
                    generator.writeObject(value.searchToolBm25_20251119)
                value.searchToolRegex20251119 != null ->
                    generator.writeObject(value.searchToolRegex20251119)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ToolUnion")
            }
        }
    }

    /** Code execution tool with REPL state persistence (daemon mode + gVisor checkpoint). */
    class CodeExecutionTool20260120
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val name: JsonValue,
        private val type: JsonValue,
        private val allowedCallers: JsonField<List<AllowedCaller>>,
        private val cacheControl: JsonField<CacheControlEphemeral>,
        private val deferLoading: JsonField<Boolean>,
        private val strict: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("name") @ExcludeMissing name: JsonValue = JsonMissing.of(),
            @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
            @JsonProperty("allowed_callers")
            @ExcludeMissing
            allowedCallers: JsonField<List<AllowedCaller>> = JsonMissing.of(),
            @JsonProperty("cache_control")
            @ExcludeMissing
            cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
            @JsonProperty("defer_loading")
            @ExcludeMissing
            deferLoading: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("strict") @ExcludeMissing strict: JsonField<Boolean> = JsonMissing.of(),
        ) : this(name, type, allowedCallers, cacheControl, deferLoading, strict, mutableMapOf())

        /**
         * Name of the tool.
         *
         * This is how the tool will be called by the model and in `tool_use` blocks.
         *
         * Expected to always return the following:
         * ```java
         * JsonValue.from("code_execution")
         * ```
         *
         * However, this method can be useful for debugging and logging (e.g. if the server
         * responded with an unexpected value).
         */
        @JsonProperty("name") @ExcludeMissing fun _name(): JsonValue = name

        /**
         * Expected to always return the following:
         * ```java
         * JsonValue.from("code_execution_20260120")
         * ```
         *
         * However, this method can be useful for debugging and logging (e.g. if the server
         * responded with an unexpected value).
         */
        @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

        /**
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun allowedCallers(): Optional<List<AllowedCaller>> =
            allowedCallers.getOptional("allowed_callers")

        /**
         * Create a cache control breakpoint at this content block.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun cacheControl(): Optional<CacheControlEphemeral> =
            cacheControl.getOptional("cache_control")

        /**
         * If true, tool will not be included in initial system prompt. Only loaded when returned
         * via tool_reference from tool search.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun deferLoading(): Optional<Boolean> = deferLoading.getOptional("defer_loading")

        /**
         * When true, guarantees schema validation on tool names and inputs
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun strict(): Optional<Boolean> = strict.getOptional("strict")

        /**
         * Returns the raw JSON value of [allowedCallers].
         *
         * Unlike [allowedCallers], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("allowed_callers")
        @ExcludeMissing
        fun _allowedCallers(): JsonField<List<AllowedCaller>> = allowedCallers

        /**
         * Returns the raw JSON value of [cacheControl].
         *
         * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("cache_control")
        @ExcludeMissing
        fun _cacheControl(): JsonField<CacheControlEphemeral> = cacheControl

        /**
         * Returns the raw JSON value of [deferLoading].
         *
         * Unlike [deferLoading], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("defer_loading")
        @ExcludeMissing
        fun _deferLoading(): JsonField<Boolean> = deferLoading

        /**
         * Returns the raw JSON value of [strict].
         *
         * Unlike [strict], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("strict") @ExcludeMissing fun _strict(): JsonField<Boolean> = strict

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
             * [CodeExecutionTool20260120].
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [CodeExecutionTool20260120]. */
        class Builder internal constructor() {

            private var name: JsonValue = JsonValue.from("code_execution")
            private var type: JsonValue = JsonValue.from("code_execution_20260120")
            private var allowedCallers: JsonField<MutableList<AllowedCaller>>? = null
            private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
            private var deferLoading: JsonField<Boolean> = JsonMissing.of()
            private var strict: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(codeExecutionTool20260120: CodeExecutionTool20260120) = apply {
                name = codeExecutionTool20260120.name
                type = codeExecutionTool20260120.type
                allowedCallers = codeExecutionTool20260120.allowedCallers.map { it.toMutableList() }
                cacheControl = codeExecutionTool20260120.cacheControl
                deferLoading = codeExecutionTool20260120.deferLoading
                strict = codeExecutionTool20260120.strict
                additionalProperties = codeExecutionTool20260120.additionalProperties.toMutableMap()
            }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * It is usually unnecessary to call this method because the field defaults to the
             * following:
             * ```java
             * JsonValue.from("code_execution")
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
             * JsonValue.from("code_execution_20260120")
             * ```
             *
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun type(type: JsonValue) = apply { this.type = type }

            fun allowedCallers(allowedCallers: List<AllowedCaller>) =
                allowedCallers(JsonField.of(allowedCallers))

            /**
             * Sets [Builder.allowedCallers] to an arbitrary JSON value.
             *
             * You should usually call [Builder.allowedCallers] with a well-typed
             * `List<AllowedCaller>` value instead. This method is primarily for setting the field
             * to an undocumented or not yet supported value.
             */
            fun allowedCallers(allowedCallers: JsonField<List<AllowedCaller>>) = apply {
                this.allowedCallers = allowedCallers.map { it.toMutableList() }
            }

            /**
             * Adds a single [AllowedCaller] to [allowedCallers].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addAllowedCaller(allowedCaller: AllowedCaller) = apply {
                allowedCallers =
                    (allowedCallers ?: JsonField.of(mutableListOf())).also {
                        checkKnown("allowedCallers", it).add(allowedCaller)
                    }
            }

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

            /**
             * If true, tool will not be included in initial system prompt. Only loaded when
             * returned via tool_reference from tool search.
             */
            fun deferLoading(deferLoading: Boolean) = deferLoading(JsonField.of(deferLoading))

            /**
             * Sets [Builder.deferLoading] to an arbitrary JSON value.
             *
             * You should usually call [Builder.deferLoading] with a well-typed [Boolean] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun deferLoading(deferLoading: JsonField<Boolean>) = apply {
                this.deferLoading = deferLoading
            }

            /** When true, guarantees schema validation on tool names and inputs */
            fun strict(strict: Boolean) = strict(JsonField.of(strict))

            /**
             * Sets [Builder.strict] to an arbitrary JSON value.
             *
             * You should usually call [Builder.strict] with a well-typed [Boolean] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun strict(strict: JsonField<Boolean>) = apply { this.strict = strict }

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
             * Returns an immutable instance of [CodeExecutionTool20260120].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): CodeExecutionTool20260120 =
                CodeExecutionTool20260120(
                    name,
                    type,
                    (allowedCallers ?: JsonMissing.of()).map { it.toImmutable() },
                    cacheControl,
                    deferLoading,
                    strict,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): CodeExecutionTool20260120 = apply {
            if (validated) {
                return@apply
            }

            _name().let {
                if (it != JsonValue.from("code_execution")) {
                    throw AnthropicInvalidDataException("'name' is invalid, received $it")
                }
            }
            _type().let {
                if (it != JsonValue.from("code_execution_20260120")) {
                    throw AnthropicInvalidDataException("'type' is invalid, received $it")
                }
            }
            allowedCallers().ifPresent { it.forEach { it.validate() } }
            cacheControl().ifPresent { it.validate() }
            deferLoading()
            strict()
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
            name.let { if (it == JsonValue.from("code_execution")) 1 else 0 } +
                type.let { if (it == JsonValue.from("code_execution_20260120")) 1 else 0 } +
                (allowedCallers.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                (cacheControl.asKnown().getOrNull()?.validity() ?: 0) +
                (if (deferLoading.asKnown().isPresent) 1 else 0) +
                (if (strict.asKnown().isPresent) 1 else 0)

        class AllowedCaller @JsonCreator private constructor(private val value: JsonField<String>) :
            Enum {

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

                @JvmField val DIRECT = of("direct")

                @JvmField val CODE_EXECUTION_20250825 = of("code_execution_20250825")

                @JvmStatic fun of(value: String) = AllowedCaller(JsonField.of(value))
            }

            /** An enum containing [AllowedCaller]'s known values. */
            enum class Known {
                DIRECT,
                CODE_EXECUTION_20250825,
            }

            /**
             * An enum containing [AllowedCaller]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [AllowedCaller] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                DIRECT,
                CODE_EXECUTION_20250825,
                /**
                 * An enum member indicating that [AllowedCaller] was instantiated with an unknown
                 * value.
                 */
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
                    DIRECT -> Value.DIRECT
                    CODE_EXECUTION_20250825 -> Value.CODE_EXECUTION_20250825
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
                    DIRECT -> Known.DIRECT
                    CODE_EXECUTION_20250825 -> Known.CODE_EXECUTION_20250825
                    else -> throw AnthropicInvalidDataException("Unknown AllowedCaller: $value")
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

            fun validate(): AllowedCaller = apply {
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

                return other is AllowedCaller && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is CodeExecutionTool20260120 &&
                name == other.name &&
                type == other.type &&
                allowedCallers == other.allowedCallers &&
                cacheControl == other.cacheControl &&
                deferLoading == other.deferLoading &&
                strict == other.strict &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                name,
                type,
                allowedCallers,
                cacheControl,
                deferLoading,
                strict,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "CodeExecutionTool20260120{name=$name, type=$type, allowedCallers=$allowedCallers, cacheControl=$cacheControl, deferLoading=$deferLoading, strict=$strict, additionalProperties=$additionalProperties}"
    }

    class WebSearchTool20260209
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val name: JsonValue,
        private val type: JsonValue,
        private val allowedCallers: JsonField<List<AllowedCaller>>,
        private val allowedDomains: JsonField<List<String>>,
        private val blockedDomains: JsonField<List<String>>,
        private val cacheControl: JsonField<CacheControlEphemeral>,
        private val deferLoading: JsonField<Boolean>,
        private val maxUses: JsonField<Long>,
        private val strict: JsonField<Boolean>,
        private val userLocation: JsonField<UserLocation>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("name") @ExcludeMissing name: JsonValue = JsonMissing.of(),
            @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
            @JsonProperty("allowed_callers")
            @ExcludeMissing
            allowedCallers: JsonField<List<AllowedCaller>> = JsonMissing.of(),
            @JsonProperty("allowed_domains")
            @ExcludeMissing
            allowedDomains: JsonField<List<String>> = JsonMissing.of(),
            @JsonProperty("blocked_domains")
            @ExcludeMissing
            blockedDomains: JsonField<List<String>> = JsonMissing.of(),
            @JsonProperty("cache_control")
            @ExcludeMissing
            cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
            @JsonProperty("defer_loading")
            @ExcludeMissing
            deferLoading: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("max_uses") @ExcludeMissing maxUses: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("strict") @ExcludeMissing strict: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("user_location")
            @ExcludeMissing
            userLocation: JsonField<UserLocation> = JsonMissing.of(),
        ) : this(
            name,
            type,
            allowedCallers,
            allowedDomains,
            blockedDomains,
            cacheControl,
            deferLoading,
            maxUses,
            strict,
            userLocation,
            mutableMapOf(),
        )

        /**
         * Name of the tool.
         *
         * This is how the tool will be called by the model and in `tool_use` blocks.
         *
         * Expected to always return the following:
         * ```java
         * JsonValue.from("web_search")
         * ```
         *
         * However, this method can be useful for debugging and logging (e.g. if the server
         * responded with an unexpected value).
         */
        @JsonProperty("name") @ExcludeMissing fun _name(): JsonValue = name

        /**
         * Expected to always return the following:
         * ```java
         * JsonValue.from("web_search_20260209")
         * ```
         *
         * However, this method can be useful for debugging and logging (e.g. if the server
         * responded with an unexpected value).
         */
        @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

        /**
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun allowedCallers(): Optional<List<AllowedCaller>> =
            allowedCallers.getOptional("allowed_callers")

        /**
         * If provided, only these domains will be included in results. Cannot be used alongside
         * `blocked_domains`.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun allowedDomains(): Optional<List<String>> = allowedDomains.getOptional("allowed_domains")

        /**
         * If provided, these domains will never appear in results. Cannot be used alongside
         * `allowed_domains`.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun blockedDomains(): Optional<List<String>> = blockedDomains.getOptional("blocked_domains")

        /**
         * Create a cache control breakpoint at this content block.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun cacheControl(): Optional<CacheControlEphemeral> =
            cacheControl.getOptional("cache_control")

        /**
         * If true, tool will not be included in initial system prompt. Only loaded when returned
         * via tool_reference from tool search.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun deferLoading(): Optional<Boolean> = deferLoading.getOptional("defer_loading")

        /**
         * Maximum number of times the tool can be used in the API request.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun maxUses(): Optional<Long> = maxUses.getOptional("max_uses")

        /**
         * When true, guarantees schema validation on tool names and inputs
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun strict(): Optional<Boolean> = strict.getOptional("strict")

        /**
         * Parameters for the user's location. Used to provide more relevant search results.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun userLocation(): Optional<UserLocation> = userLocation.getOptional("user_location")

        /**
         * Returns the raw JSON value of [allowedCallers].
         *
         * Unlike [allowedCallers], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("allowed_callers")
        @ExcludeMissing
        fun _allowedCallers(): JsonField<List<AllowedCaller>> = allowedCallers

        /**
         * Returns the raw JSON value of [allowedDomains].
         *
         * Unlike [allowedDomains], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("allowed_domains")
        @ExcludeMissing
        fun _allowedDomains(): JsonField<List<String>> = allowedDomains

        /**
         * Returns the raw JSON value of [blockedDomains].
         *
         * Unlike [blockedDomains], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("blocked_domains")
        @ExcludeMissing
        fun _blockedDomains(): JsonField<List<String>> = blockedDomains

        /**
         * Returns the raw JSON value of [cacheControl].
         *
         * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("cache_control")
        @ExcludeMissing
        fun _cacheControl(): JsonField<CacheControlEphemeral> = cacheControl

        /**
         * Returns the raw JSON value of [deferLoading].
         *
         * Unlike [deferLoading], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("defer_loading")
        @ExcludeMissing
        fun _deferLoading(): JsonField<Boolean> = deferLoading

        /**
         * Returns the raw JSON value of [maxUses].
         *
         * Unlike [maxUses], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("max_uses") @ExcludeMissing fun _maxUses(): JsonField<Long> = maxUses

        /**
         * Returns the raw JSON value of [strict].
         *
         * Unlike [strict], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("strict") @ExcludeMissing fun _strict(): JsonField<Boolean> = strict

        /**
         * Returns the raw JSON value of [userLocation].
         *
         * Unlike [userLocation], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("user_location")
        @ExcludeMissing
        fun _userLocation(): JsonField<UserLocation> = userLocation

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
             * Returns a mutable builder for constructing an instance of [WebSearchTool20260209].
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [WebSearchTool20260209]. */
        class Builder internal constructor() {

            private var name: JsonValue = JsonValue.from("web_search")
            private var type: JsonValue = JsonValue.from("web_search_20260209")
            private var allowedCallers: JsonField<MutableList<AllowedCaller>>? = null
            private var allowedDomains: JsonField<MutableList<String>>? = null
            private var blockedDomains: JsonField<MutableList<String>>? = null
            private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
            private var deferLoading: JsonField<Boolean> = JsonMissing.of()
            private var maxUses: JsonField<Long> = JsonMissing.of()
            private var strict: JsonField<Boolean> = JsonMissing.of()
            private var userLocation: JsonField<UserLocation> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(webSearchTool20260209: WebSearchTool20260209) = apply {
                name = webSearchTool20260209.name
                type = webSearchTool20260209.type
                allowedCallers = webSearchTool20260209.allowedCallers.map { it.toMutableList() }
                allowedDomains = webSearchTool20260209.allowedDomains.map { it.toMutableList() }
                blockedDomains = webSearchTool20260209.blockedDomains.map { it.toMutableList() }
                cacheControl = webSearchTool20260209.cacheControl
                deferLoading = webSearchTool20260209.deferLoading
                maxUses = webSearchTool20260209.maxUses
                strict = webSearchTool20260209.strict
                userLocation = webSearchTool20260209.userLocation
                additionalProperties = webSearchTool20260209.additionalProperties.toMutableMap()
            }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * It is usually unnecessary to call this method because the field defaults to the
             * following:
             * ```java
             * JsonValue.from("web_search")
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
             * JsonValue.from("web_search_20260209")
             * ```
             *
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun type(type: JsonValue) = apply { this.type = type }

            fun allowedCallers(allowedCallers: List<AllowedCaller>) =
                allowedCallers(JsonField.of(allowedCallers))

            /**
             * Sets [Builder.allowedCallers] to an arbitrary JSON value.
             *
             * You should usually call [Builder.allowedCallers] with a well-typed
             * `List<AllowedCaller>` value instead. This method is primarily for setting the field
             * to an undocumented or not yet supported value.
             */
            fun allowedCallers(allowedCallers: JsonField<List<AllowedCaller>>) = apply {
                this.allowedCallers = allowedCallers.map { it.toMutableList() }
            }

            /**
             * Adds a single [AllowedCaller] to [allowedCallers].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addAllowedCaller(allowedCaller: AllowedCaller) = apply {
                allowedCallers =
                    (allowedCallers ?: JsonField.of(mutableListOf())).also {
                        checkKnown("allowedCallers", it).add(allowedCaller)
                    }
            }

            /**
             * If provided, only these domains will be included in results. Cannot be used alongside
             * `blocked_domains`.
             */
            fun allowedDomains(allowedDomains: List<String>?) =
                allowedDomains(JsonField.ofNullable(allowedDomains))

            /** Alias for calling [Builder.allowedDomains] with `allowedDomains.orElse(null)`. */
            fun allowedDomains(allowedDomains: Optional<List<String>>) =
                allowedDomains(allowedDomains.getOrNull())

            /**
             * Sets [Builder.allowedDomains] to an arbitrary JSON value.
             *
             * You should usually call [Builder.allowedDomains] with a well-typed `List<String>`
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun allowedDomains(allowedDomains: JsonField<List<String>>) = apply {
                this.allowedDomains = allowedDomains.map { it.toMutableList() }
            }

            /**
             * Adds a single [String] to [allowedDomains].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addAllowedDomain(allowedDomain: String) = apply {
                allowedDomains =
                    (allowedDomains ?: JsonField.of(mutableListOf())).also {
                        checkKnown("allowedDomains", it).add(allowedDomain)
                    }
            }

            /**
             * If provided, these domains will never appear in results. Cannot be used alongside
             * `allowed_domains`.
             */
            fun blockedDomains(blockedDomains: List<String>?) =
                blockedDomains(JsonField.ofNullable(blockedDomains))

            /** Alias for calling [Builder.blockedDomains] with `blockedDomains.orElse(null)`. */
            fun blockedDomains(blockedDomains: Optional<List<String>>) =
                blockedDomains(blockedDomains.getOrNull())

            /**
             * Sets [Builder.blockedDomains] to an arbitrary JSON value.
             *
             * You should usually call [Builder.blockedDomains] with a well-typed `List<String>`
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun blockedDomains(blockedDomains: JsonField<List<String>>) = apply {
                this.blockedDomains = blockedDomains.map { it.toMutableList() }
            }

            /**
             * Adds a single [String] to [blockedDomains].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addBlockedDomain(blockedDomain: String) = apply {
                blockedDomains =
                    (blockedDomains ?: JsonField.of(mutableListOf())).also {
                        checkKnown("blockedDomains", it).add(blockedDomain)
                    }
            }

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

            /**
             * If true, tool will not be included in initial system prompt. Only loaded when
             * returned via tool_reference from tool search.
             */
            fun deferLoading(deferLoading: Boolean) = deferLoading(JsonField.of(deferLoading))

            /**
             * Sets [Builder.deferLoading] to an arbitrary JSON value.
             *
             * You should usually call [Builder.deferLoading] with a well-typed [Boolean] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun deferLoading(deferLoading: JsonField<Boolean>) = apply {
                this.deferLoading = deferLoading
            }

            /** Maximum number of times the tool can be used in the API request. */
            fun maxUses(maxUses: Long?) = maxUses(JsonField.ofNullable(maxUses))

            /**
             * Alias for [Builder.maxUses].
             *
             * This unboxed primitive overload exists for backwards compatibility.
             */
            fun maxUses(maxUses: Long) = maxUses(maxUses as Long?)

            /** Alias for calling [Builder.maxUses] with `maxUses.orElse(null)`. */
            fun maxUses(maxUses: Optional<Long>) = maxUses(maxUses.getOrNull())

            /**
             * Sets [Builder.maxUses] to an arbitrary JSON value.
             *
             * You should usually call [Builder.maxUses] with a well-typed [Long] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun maxUses(maxUses: JsonField<Long>) = apply { this.maxUses = maxUses }

            /** When true, guarantees schema validation on tool names and inputs */
            fun strict(strict: Boolean) = strict(JsonField.of(strict))

            /**
             * Sets [Builder.strict] to an arbitrary JSON value.
             *
             * You should usually call [Builder.strict] with a well-typed [Boolean] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun strict(strict: JsonField<Boolean>) = apply { this.strict = strict }

            /** Parameters for the user's location. Used to provide more relevant search results. */
            fun userLocation(userLocation: UserLocation?) =
                userLocation(JsonField.ofNullable(userLocation))

            /** Alias for calling [Builder.userLocation] with `userLocation.orElse(null)`. */
            fun userLocation(userLocation: Optional<UserLocation>) =
                userLocation(userLocation.getOrNull())

            /**
             * Sets [Builder.userLocation] to an arbitrary JSON value.
             *
             * You should usually call [Builder.userLocation] with a well-typed [UserLocation] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun userLocation(userLocation: JsonField<UserLocation>) = apply {
                this.userLocation = userLocation
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
             * Returns an immutable instance of [WebSearchTool20260209].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): WebSearchTool20260209 =
                WebSearchTool20260209(
                    name,
                    type,
                    (allowedCallers ?: JsonMissing.of()).map { it.toImmutable() },
                    (allowedDomains ?: JsonMissing.of()).map { it.toImmutable() },
                    (blockedDomains ?: JsonMissing.of()).map { it.toImmutable() },
                    cacheControl,
                    deferLoading,
                    maxUses,
                    strict,
                    userLocation,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): WebSearchTool20260209 = apply {
            if (validated) {
                return@apply
            }

            _name().let {
                if (it != JsonValue.from("web_search")) {
                    throw AnthropicInvalidDataException("'name' is invalid, received $it")
                }
            }
            _type().let {
                if (it != JsonValue.from("web_search_20260209")) {
                    throw AnthropicInvalidDataException("'type' is invalid, received $it")
                }
            }
            allowedCallers().ifPresent { it.forEach { it.validate() } }
            allowedDomains()
            blockedDomains()
            cacheControl().ifPresent { it.validate() }
            deferLoading()
            maxUses()
            strict()
            userLocation().ifPresent { it.validate() }
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
            name.let { if (it == JsonValue.from("web_search")) 1 else 0 } +
                type.let { if (it == JsonValue.from("web_search_20260209")) 1 else 0 } +
                (allowedCallers.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                (allowedDomains.asKnown().getOrNull()?.size ?: 0) +
                (blockedDomains.asKnown().getOrNull()?.size ?: 0) +
                (cacheControl.asKnown().getOrNull()?.validity() ?: 0) +
                (if (deferLoading.asKnown().isPresent) 1 else 0) +
                (if (maxUses.asKnown().isPresent) 1 else 0) +
                (if (strict.asKnown().isPresent) 1 else 0) +
                (userLocation.asKnown().getOrNull()?.validity() ?: 0)

        class AllowedCaller @JsonCreator private constructor(private val value: JsonField<String>) :
            Enum {

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

                @JvmField val DIRECT = of("direct")

                @JvmField val CODE_EXECUTION_20250825 = of("code_execution_20250825")

                @JvmStatic fun of(value: String) = AllowedCaller(JsonField.of(value))
            }

            /** An enum containing [AllowedCaller]'s known values. */
            enum class Known {
                DIRECT,
                CODE_EXECUTION_20250825,
            }

            /**
             * An enum containing [AllowedCaller]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [AllowedCaller] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                DIRECT,
                CODE_EXECUTION_20250825,
                /**
                 * An enum member indicating that [AllowedCaller] was instantiated with an unknown
                 * value.
                 */
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
                    DIRECT -> Value.DIRECT
                    CODE_EXECUTION_20250825 -> Value.CODE_EXECUTION_20250825
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
                    DIRECT -> Known.DIRECT
                    CODE_EXECUTION_20250825 -> Known.CODE_EXECUTION_20250825
                    else -> throw AnthropicInvalidDataException("Unknown AllowedCaller: $value")
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

            fun validate(): AllowedCaller = apply {
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

                return other is AllowedCaller && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        /** Parameters for the user's location. Used to provide more relevant search results. */
        class UserLocation
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val type: JsonValue,
            private val city: JsonField<String>,
            private val country: JsonField<String>,
            private val region: JsonField<String>,
            private val timezone: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
                @JsonProperty("city") @ExcludeMissing city: JsonField<String> = JsonMissing.of(),
                @JsonProperty("country")
                @ExcludeMissing
                country: JsonField<String> = JsonMissing.of(),
                @JsonProperty("region")
                @ExcludeMissing
                region: JsonField<String> = JsonMissing.of(),
                @JsonProperty("timezone")
                @ExcludeMissing
                timezone: JsonField<String> = JsonMissing.of(),
            ) : this(type, city, country, region, timezone, mutableMapOf())

            /**
             * Expected to always return the following:
             * ```java
             * JsonValue.from("approximate")
             * ```
             *
             * However, this method can be useful for debugging and logging (e.g. if the server
             * responded with an unexpected value).
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

            /**
             * The city of the user.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun city(): Optional<String> = city.getOptional("city")

            /**
             * The two letter [ISO country code](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2)
             * of the user.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun country(): Optional<String> = country.getOptional("country")

            /**
             * The region of the user.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun region(): Optional<String> = region.getOptional("region")

            /**
             * The [IANA timezone](https://nodatime.org/TimeZones) of the user.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun timezone(): Optional<String> = timezone.getOptional("timezone")

            /**
             * Returns the raw JSON value of [city].
             *
             * Unlike [city], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("city") @ExcludeMissing fun _city(): JsonField<String> = city

            /**
             * Returns the raw JSON value of [country].
             *
             * Unlike [country], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("country") @ExcludeMissing fun _country(): JsonField<String> = country

            /**
             * Returns the raw JSON value of [region].
             *
             * Unlike [region], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("region") @ExcludeMissing fun _region(): JsonField<String> = region

            /**
             * Returns the raw JSON value of [timezone].
             *
             * Unlike [timezone], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("timezone") @ExcludeMissing fun _timezone(): JsonField<String> = timezone

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

                /** Returns a mutable builder for constructing an instance of [UserLocation]. */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [UserLocation]. */
            class Builder internal constructor() {

                private var type: JsonValue = JsonValue.from("approximate")
                private var city: JsonField<String> = JsonMissing.of()
                private var country: JsonField<String> = JsonMissing.of()
                private var region: JsonField<String> = JsonMissing.of()
                private var timezone: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(userLocation: UserLocation) = apply {
                    type = userLocation.type
                    city = userLocation.city
                    country = userLocation.country
                    region = userLocation.region
                    timezone = userLocation.timezone
                    additionalProperties = userLocation.additionalProperties.toMutableMap()
                }

                /**
                 * Sets the field to an arbitrary JSON value.
                 *
                 * It is usually unnecessary to call this method because the field defaults to the
                 * following:
                 * ```java
                 * JsonValue.from("approximate")
                 * ```
                 *
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun type(type: JsonValue) = apply { this.type = type }

                /** The city of the user. */
                fun city(city: String?) = city(JsonField.ofNullable(city))

                /** Alias for calling [Builder.city] with `city.orElse(null)`. */
                fun city(city: Optional<String>) = city(city.getOrNull())

                /**
                 * Sets [Builder.city] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.city] with a well-typed [String] value instead.
                 * This method is primarily for setting the field to an undocumented or not yet
                 * supported value.
                 */
                fun city(city: JsonField<String>) = apply { this.city = city }

                /**
                 * The two letter
                 * [ISO country code](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) of the user.
                 */
                fun country(country: String?) = country(JsonField.ofNullable(country))

                /** Alias for calling [Builder.country] with `country.orElse(null)`. */
                fun country(country: Optional<String>) = country(country.getOrNull())

                /**
                 * Sets [Builder.country] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.country] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun country(country: JsonField<String>) = apply { this.country = country }

                /** The region of the user. */
                fun region(region: String?) = region(JsonField.ofNullable(region))

                /** Alias for calling [Builder.region] with `region.orElse(null)`. */
                fun region(region: Optional<String>) = region(region.getOrNull())

                /**
                 * Sets [Builder.region] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.region] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun region(region: JsonField<String>) = apply { this.region = region }

                /** The [IANA timezone](https://nodatime.org/TimeZones) of the user. */
                fun timezone(timezone: String?) = timezone(JsonField.ofNullable(timezone))

                /** Alias for calling [Builder.timezone] with `timezone.orElse(null)`. */
                fun timezone(timezone: Optional<String>) = timezone(timezone.getOrNull())

                /**
                 * Sets [Builder.timezone] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.timezone] with a well-typed [String] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun timezone(timezone: JsonField<String>) = apply { this.timezone = timezone }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [UserLocation].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): UserLocation =
                    UserLocation(
                        type,
                        city,
                        country,
                        region,
                        timezone,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            fun validate(): UserLocation = apply {
                if (validated) {
                    return@apply
                }

                _type().let {
                    if (it != JsonValue.from("approximate")) {
                        throw AnthropicInvalidDataException("'type' is invalid, received $it")
                    }
                }
                city()
                country()
                region()
                timezone()
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
                type.let { if (it == JsonValue.from("approximate")) 1 else 0 } +
                    (if (city.asKnown().isPresent) 1 else 0) +
                    (if (country.asKnown().isPresent) 1 else 0) +
                    (if (region.asKnown().isPresent) 1 else 0) +
                    (if (timezone.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is UserLocation &&
                    type == other.type &&
                    city == other.city &&
                    country == other.country &&
                    region == other.region &&
                    timezone == other.timezone &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(type, city, country, region, timezone, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "UserLocation{type=$type, city=$city, country=$country, region=$region, timezone=$timezone, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is WebSearchTool20260209 &&
                name == other.name &&
                type == other.type &&
                allowedCallers == other.allowedCallers &&
                allowedDomains == other.allowedDomains &&
                blockedDomains == other.blockedDomains &&
                cacheControl == other.cacheControl &&
                deferLoading == other.deferLoading &&
                maxUses == other.maxUses &&
                strict == other.strict &&
                userLocation == other.userLocation &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                name,
                type,
                allowedCallers,
                allowedDomains,
                blockedDomains,
                cacheControl,
                deferLoading,
                maxUses,
                strict,
                userLocation,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "WebSearchTool20260209{name=$name, type=$type, allowedCallers=$allowedCallers, allowedDomains=$allowedDomains, blockedDomains=$blockedDomains, cacheControl=$cacheControl, deferLoading=$deferLoading, maxUses=$maxUses, strict=$strict, userLocation=$userLocation, additionalProperties=$additionalProperties}"
    }

    class WebFetchTool20260209
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val name: JsonValue,
        private val type: JsonValue,
        private val allowedCallers: JsonField<List<AllowedCaller>>,
        private val allowedDomains: JsonField<List<String>>,
        private val blockedDomains: JsonField<List<String>>,
        private val cacheControl: JsonField<CacheControlEphemeral>,
        private val citations: JsonField<CitationsConfigParam>,
        private val deferLoading: JsonField<Boolean>,
        private val maxContentTokens: JsonField<Long>,
        private val maxUses: JsonField<Long>,
        private val strict: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("name") @ExcludeMissing name: JsonValue = JsonMissing.of(),
            @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
            @JsonProperty("allowed_callers")
            @ExcludeMissing
            allowedCallers: JsonField<List<AllowedCaller>> = JsonMissing.of(),
            @JsonProperty("allowed_domains")
            @ExcludeMissing
            allowedDomains: JsonField<List<String>> = JsonMissing.of(),
            @JsonProperty("blocked_domains")
            @ExcludeMissing
            blockedDomains: JsonField<List<String>> = JsonMissing.of(),
            @JsonProperty("cache_control")
            @ExcludeMissing
            cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
            @JsonProperty("citations")
            @ExcludeMissing
            citations: JsonField<CitationsConfigParam> = JsonMissing.of(),
            @JsonProperty("defer_loading")
            @ExcludeMissing
            deferLoading: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("max_content_tokens")
            @ExcludeMissing
            maxContentTokens: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("max_uses") @ExcludeMissing maxUses: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("strict") @ExcludeMissing strict: JsonField<Boolean> = JsonMissing.of(),
        ) : this(
            name,
            type,
            allowedCallers,
            allowedDomains,
            blockedDomains,
            cacheControl,
            citations,
            deferLoading,
            maxContentTokens,
            maxUses,
            strict,
            mutableMapOf(),
        )

        /**
         * Name of the tool.
         *
         * This is how the tool will be called by the model and in `tool_use` blocks.
         *
         * Expected to always return the following:
         * ```java
         * JsonValue.from("web_fetch")
         * ```
         *
         * However, this method can be useful for debugging and logging (e.g. if the server
         * responded with an unexpected value).
         */
        @JsonProperty("name") @ExcludeMissing fun _name(): JsonValue = name

        /**
         * Expected to always return the following:
         * ```java
         * JsonValue.from("web_fetch_20260209")
         * ```
         *
         * However, this method can be useful for debugging and logging (e.g. if the server
         * responded with an unexpected value).
         */
        @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

        /**
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun allowedCallers(): Optional<List<AllowedCaller>> =
            allowedCallers.getOptional("allowed_callers")

        /**
         * List of domains to allow fetching from
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun allowedDomains(): Optional<List<String>> = allowedDomains.getOptional("allowed_domains")

        /**
         * List of domains to block fetching from
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun blockedDomains(): Optional<List<String>> = blockedDomains.getOptional("blocked_domains")

        /**
         * Create a cache control breakpoint at this content block.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun cacheControl(): Optional<CacheControlEphemeral> =
            cacheControl.getOptional("cache_control")

        /**
         * Citations configuration for fetched documents. Citations are disabled by default.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun citations(): Optional<CitationsConfigParam> = citations.getOptional("citations")

        /**
         * If true, tool will not be included in initial system prompt. Only loaded when returned
         * via tool_reference from tool search.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun deferLoading(): Optional<Boolean> = deferLoading.getOptional("defer_loading")

        /**
         * Maximum number of tokens used by including web page text content in the context. The
         * limit is approximate and does not apply to binary content such as PDFs.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun maxContentTokens(): Optional<Long> = maxContentTokens.getOptional("max_content_tokens")

        /**
         * Maximum number of times the tool can be used in the API request.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun maxUses(): Optional<Long> = maxUses.getOptional("max_uses")

        /**
         * When true, guarantees schema validation on tool names and inputs
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun strict(): Optional<Boolean> = strict.getOptional("strict")

        /**
         * Returns the raw JSON value of [allowedCallers].
         *
         * Unlike [allowedCallers], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("allowed_callers")
        @ExcludeMissing
        fun _allowedCallers(): JsonField<List<AllowedCaller>> = allowedCallers

        /**
         * Returns the raw JSON value of [allowedDomains].
         *
         * Unlike [allowedDomains], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("allowed_domains")
        @ExcludeMissing
        fun _allowedDomains(): JsonField<List<String>> = allowedDomains

        /**
         * Returns the raw JSON value of [blockedDomains].
         *
         * Unlike [blockedDomains], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("blocked_domains")
        @ExcludeMissing
        fun _blockedDomains(): JsonField<List<String>> = blockedDomains

        /**
         * Returns the raw JSON value of [cacheControl].
         *
         * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("cache_control")
        @ExcludeMissing
        fun _cacheControl(): JsonField<CacheControlEphemeral> = cacheControl

        /**
         * Returns the raw JSON value of [citations].
         *
         * Unlike [citations], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("citations")
        @ExcludeMissing
        fun _citations(): JsonField<CitationsConfigParam> = citations

        /**
         * Returns the raw JSON value of [deferLoading].
         *
         * Unlike [deferLoading], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("defer_loading")
        @ExcludeMissing
        fun _deferLoading(): JsonField<Boolean> = deferLoading

        /**
         * Returns the raw JSON value of [maxContentTokens].
         *
         * Unlike [maxContentTokens], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("max_content_tokens")
        @ExcludeMissing
        fun _maxContentTokens(): JsonField<Long> = maxContentTokens

        /**
         * Returns the raw JSON value of [maxUses].
         *
         * Unlike [maxUses], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("max_uses") @ExcludeMissing fun _maxUses(): JsonField<Long> = maxUses

        /**
         * Returns the raw JSON value of [strict].
         *
         * Unlike [strict], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("strict") @ExcludeMissing fun _strict(): JsonField<Boolean> = strict

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

            /** Returns a mutable builder for constructing an instance of [WebFetchTool20260209]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [WebFetchTool20260209]. */
        class Builder internal constructor() {

            private var name: JsonValue = JsonValue.from("web_fetch")
            private var type: JsonValue = JsonValue.from("web_fetch_20260209")
            private var allowedCallers: JsonField<MutableList<AllowedCaller>>? = null
            private var allowedDomains: JsonField<MutableList<String>>? = null
            private var blockedDomains: JsonField<MutableList<String>>? = null
            private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
            private var citations: JsonField<CitationsConfigParam> = JsonMissing.of()
            private var deferLoading: JsonField<Boolean> = JsonMissing.of()
            private var maxContentTokens: JsonField<Long> = JsonMissing.of()
            private var maxUses: JsonField<Long> = JsonMissing.of()
            private var strict: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(webFetchTool20260209: WebFetchTool20260209) = apply {
                name = webFetchTool20260209.name
                type = webFetchTool20260209.type
                allowedCallers = webFetchTool20260209.allowedCallers.map { it.toMutableList() }
                allowedDomains = webFetchTool20260209.allowedDomains.map { it.toMutableList() }
                blockedDomains = webFetchTool20260209.blockedDomains.map { it.toMutableList() }
                cacheControl = webFetchTool20260209.cacheControl
                citations = webFetchTool20260209.citations
                deferLoading = webFetchTool20260209.deferLoading
                maxContentTokens = webFetchTool20260209.maxContentTokens
                maxUses = webFetchTool20260209.maxUses
                strict = webFetchTool20260209.strict
                additionalProperties = webFetchTool20260209.additionalProperties.toMutableMap()
            }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * It is usually unnecessary to call this method because the field defaults to the
             * following:
             * ```java
             * JsonValue.from("web_fetch")
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
             * JsonValue.from("web_fetch_20260209")
             * ```
             *
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun type(type: JsonValue) = apply { this.type = type }

            fun allowedCallers(allowedCallers: List<AllowedCaller>) =
                allowedCallers(JsonField.of(allowedCallers))

            /**
             * Sets [Builder.allowedCallers] to an arbitrary JSON value.
             *
             * You should usually call [Builder.allowedCallers] with a well-typed
             * `List<AllowedCaller>` value instead. This method is primarily for setting the field
             * to an undocumented or not yet supported value.
             */
            fun allowedCallers(allowedCallers: JsonField<List<AllowedCaller>>) = apply {
                this.allowedCallers = allowedCallers.map { it.toMutableList() }
            }

            /**
             * Adds a single [AllowedCaller] to [allowedCallers].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addAllowedCaller(allowedCaller: AllowedCaller) = apply {
                allowedCallers =
                    (allowedCallers ?: JsonField.of(mutableListOf())).also {
                        checkKnown("allowedCallers", it).add(allowedCaller)
                    }
            }

            /** List of domains to allow fetching from */
            fun allowedDomains(allowedDomains: List<String>?) =
                allowedDomains(JsonField.ofNullable(allowedDomains))

            /** Alias for calling [Builder.allowedDomains] with `allowedDomains.orElse(null)`. */
            fun allowedDomains(allowedDomains: Optional<List<String>>) =
                allowedDomains(allowedDomains.getOrNull())

            /**
             * Sets [Builder.allowedDomains] to an arbitrary JSON value.
             *
             * You should usually call [Builder.allowedDomains] with a well-typed `List<String>`
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun allowedDomains(allowedDomains: JsonField<List<String>>) = apply {
                this.allowedDomains = allowedDomains.map { it.toMutableList() }
            }

            /**
             * Adds a single [String] to [allowedDomains].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addAllowedDomain(allowedDomain: String) = apply {
                allowedDomains =
                    (allowedDomains ?: JsonField.of(mutableListOf())).also {
                        checkKnown("allowedDomains", it).add(allowedDomain)
                    }
            }

            /** List of domains to block fetching from */
            fun blockedDomains(blockedDomains: List<String>?) =
                blockedDomains(JsonField.ofNullable(blockedDomains))

            /** Alias for calling [Builder.blockedDomains] with `blockedDomains.orElse(null)`. */
            fun blockedDomains(blockedDomains: Optional<List<String>>) =
                blockedDomains(blockedDomains.getOrNull())

            /**
             * Sets [Builder.blockedDomains] to an arbitrary JSON value.
             *
             * You should usually call [Builder.blockedDomains] with a well-typed `List<String>`
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun blockedDomains(blockedDomains: JsonField<List<String>>) = apply {
                this.blockedDomains = blockedDomains.map { it.toMutableList() }
            }

            /**
             * Adds a single [String] to [blockedDomains].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addBlockedDomain(blockedDomain: String) = apply {
                blockedDomains =
                    (blockedDomains ?: JsonField.of(mutableListOf())).also {
                        checkKnown("blockedDomains", it).add(blockedDomain)
                    }
            }

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

            /** Citations configuration for fetched documents. Citations are disabled by default. */
            fun citations(citations: CitationsConfigParam?) =
                citations(JsonField.ofNullable(citations))

            /** Alias for calling [Builder.citations] with `citations.orElse(null)`. */
            fun citations(citations: Optional<CitationsConfigParam>) =
                citations(citations.getOrNull())

            /**
             * Sets [Builder.citations] to an arbitrary JSON value.
             *
             * You should usually call [Builder.citations] with a well-typed [CitationsConfigParam]
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun citations(citations: JsonField<CitationsConfigParam>) = apply {
                this.citations = citations
            }

            /**
             * If true, tool will not be included in initial system prompt. Only loaded when
             * returned via tool_reference from tool search.
             */
            fun deferLoading(deferLoading: Boolean) = deferLoading(JsonField.of(deferLoading))

            /**
             * Sets [Builder.deferLoading] to an arbitrary JSON value.
             *
             * You should usually call [Builder.deferLoading] with a well-typed [Boolean] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun deferLoading(deferLoading: JsonField<Boolean>) = apply {
                this.deferLoading = deferLoading
            }

            /**
             * Maximum number of tokens used by including web page text content in the context. The
             * limit is approximate and does not apply to binary content such as PDFs.
             */
            fun maxContentTokens(maxContentTokens: Long?) =
                maxContentTokens(JsonField.ofNullable(maxContentTokens))

            /**
             * Alias for [Builder.maxContentTokens].
             *
             * This unboxed primitive overload exists for backwards compatibility.
             */
            fun maxContentTokens(maxContentTokens: Long) =
                maxContentTokens(maxContentTokens as Long?)

            /**
             * Alias for calling [Builder.maxContentTokens] with `maxContentTokens.orElse(null)`.
             */
            fun maxContentTokens(maxContentTokens: Optional<Long>) =
                maxContentTokens(maxContentTokens.getOrNull())

            /**
             * Sets [Builder.maxContentTokens] to an arbitrary JSON value.
             *
             * You should usually call [Builder.maxContentTokens] with a well-typed [Long] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun maxContentTokens(maxContentTokens: JsonField<Long>) = apply {
                this.maxContentTokens = maxContentTokens
            }

            /** Maximum number of times the tool can be used in the API request. */
            fun maxUses(maxUses: Long?) = maxUses(JsonField.ofNullable(maxUses))

            /**
             * Alias for [Builder.maxUses].
             *
             * This unboxed primitive overload exists for backwards compatibility.
             */
            fun maxUses(maxUses: Long) = maxUses(maxUses as Long?)

            /** Alias for calling [Builder.maxUses] with `maxUses.orElse(null)`. */
            fun maxUses(maxUses: Optional<Long>) = maxUses(maxUses.getOrNull())

            /**
             * Sets [Builder.maxUses] to an arbitrary JSON value.
             *
             * You should usually call [Builder.maxUses] with a well-typed [Long] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun maxUses(maxUses: JsonField<Long>) = apply { this.maxUses = maxUses }

            /** When true, guarantees schema validation on tool names and inputs */
            fun strict(strict: Boolean) = strict(JsonField.of(strict))

            /**
             * Sets [Builder.strict] to an arbitrary JSON value.
             *
             * You should usually call [Builder.strict] with a well-typed [Boolean] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun strict(strict: JsonField<Boolean>) = apply { this.strict = strict }

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
             * Returns an immutable instance of [WebFetchTool20260209].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): WebFetchTool20260209 =
                WebFetchTool20260209(
                    name,
                    type,
                    (allowedCallers ?: JsonMissing.of()).map { it.toImmutable() },
                    (allowedDomains ?: JsonMissing.of()).map { it.toImmutable() },
                    (blockedDomains ?: JsonMissing.of()).map { it.toImmutable() },
                    cacheControl,
                    citations,
                    deferLoading,
                    maxContentTokens,
                    maxUses,
                    strict,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): WebFetchTool20260209 = apply {
            if (validated) {
                return@apply
            }

            _name().let {
                if (it != JsonValue.from("web_fetch")) {
                    throw AnthropicInvalidDataException("'name' is invalid, received $it")
                }
            }
            _type().let {
                if (it != JsonValue.from("web_fetch_20260209")) {
                    throw AnthropicInvalidDataException("'type' is invalid, received $it")
                }
            }
            allowedCallers().ifPresent { it.forEach { it.validate() } }
            allowedDomains()
            blockedDomains()
            cacheControl().ifPresent { it.validate() }
            citations().ifPresent { it.validate() }
            deferLoading()
            maxContentTokens()
            maxUses()
            strict()
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
            name.let { if (it == JsonValue.from("web_fetch")) 1 else 0 } +
                type.let { if (it == JsonValue.from("web_fetch_20260209")) 1 else 0 } +
                (allowedCallers.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                (allowedDomains.asKnown().getOrNull()?.size ?: 0) +
                (blockedDomains.asKnown().getOrNull()?.size ?: 0) +
                (cacheControl.asKnown().getOrNull()?.validity() ?: 0) +
                (citations.asKnown().getOrNull()?.validity() ?: 0) +
                (if (deferLoading.asKnown().isPresent) 1 else 0) +
                (if (maxContentTokens.asKnown().isPresent) 1 else 0) +
                (if (maxUses.asKnown().isPresent) 1 else 0) +
                (if (strict.asKnown().isPresent) 1 else 0)

        class AllowedCaller @JsonCreator private constructor(private val value: JsonField<String>) :
            Enum {

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

                @JvmField val DIRECT = of("direct")

                @JvmField val CODE_EXECUTION_20250825 = of("code_execution_20250825")

                @JvmStatic fun of(value: String) = AllowedCaller(JsonField.of(value))
            }

            /** An enum containing [AllowedCaller]'s known values. */
            enum class Known {
                DIRECT,
                CODE_EXECUTION_20250825,
            }

            /**
             * An enum containing [AllowedCaller]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [AllowedCaller] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                DIRECT,
                CODE_EXECUTION_20250825,
                /**
                 * An enum member indicating that [AllowedCaller] was instantiated with an unknown
                 * value.
                 */
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
                    DIRECT -> Value.DIRECT
                    CODE_EXECUTION_20250825 -> Value.CODE_EXECUTION_20250825
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
                    DIRECT -> Known.DIRECT
                    CODE_EXECUTION_20250825 -> Known.CODE_EXECUTION_20250825
                    else -> throw AnthropicInvalidDataException("Unknown AllowedCaller: $value")
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

            fun validate(): AllowedCaller = apply {
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

                return other is AllowedCaller && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is WebFetchTool20260209 &&
                name == other.name &&
                type == other.type &&
                allowedCallers == other.allowedCallers &&
                allowedDomains == other.allowedDomains &&
                blockedDomains == other.blockedDomains &&
                cacheControl == other.cacheControl &&
                citations == other.citations &&
                deferLoading == other.deferLoading &&
                maxContentTokens == other.maxContentTokens &&
                maxUses == other.maxUses &&
                strict == other.strict &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                name,
                type,
                allowedCallers,
                allowedDomains,
                blockedDomains,
                cacheControl,
                citations,
                deferLoading,
                maxContentTokens,
                maxUses,
                strict,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "WebFetchTool20260209{name=$name, type=$type, allowedCallers=$allowedCallers, allowedDomains=$allowedDomains, blockedDomains=$blockedDomains, cacheControl=$cacheControl, citations=$citations, deferLoading=$deferLoading, maxContentTokens=$maxContentTokens, maxUses=$maxUses, strict=$strict, additionalProperties=$additionalProperties}"
    }
}
