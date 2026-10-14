@file:JvmName("Content")

package com.anthropic.internal.telemetry

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import io.opentelemetry.api.trace.Span

/**
 * The most that the content attributes of one span hold together when the client sets no
 * `maxContentBytes` ([contentLimit]), in bytes of UTF-8 ([utf8Length]), when leaving out stop
 * sequences or a tool's input that alone are over it, removing message parts, the first one first,
 * cutting the end of the last `text` part, and removing a tool result's trailing content blocks can
 * bring them under it.
 */
@JvmSynthetic internal const val CONTENT_LIMIT = 750_000

/** The most bytes of UTF-8 that one char of a string takes, so `n` chars are within `3n` bytes. */
@JvmSynthetic internal const val MAX_UTF8_BYTES_PER_CHAR = 3

private const val ONE_BYTE_UTF8_END = 0x80
private const val TWO_BYTE_UTF8_END = 0x800

/** The length of [text] in UTF-8, in bytes, counted without encoding it. */
@JvmSynthetic
internal fun utf8Length(text: CharSequence): Int {
    var length = 0
    for (char in text) {
        length += utf8Length(char)
    }
    return length
}

/**
 * The bytes of [char] in UTF-8. A surrogate is half of the four bytes of its pair. Without its pair
 * it is still two, which is no less than the `?` that an exporter encodes in its place.
 */
private fun utf8Length(char: Char): Int =
    when {
        char.code < ONE_BYTE_UTF8_END -> 1
        char.code < TWO_BYTE_UTF8_END || char.isSurrogate() -> 2
        else -> MAX_UTF8_BYTES_PER_CHAR
    }

private val nodes = JsonNodeFactory.instance

/**
 * The part for a content block of the API. A thinking block is a reasoning part with the block's
 * thinking, which is empty for a hidden block, and without its signature. A tool-use block, also of
 * a server tool or an MCP tool, is a tool call. A result block is a tool call response: its
 * response is the [toolResultContent] of a `tool_result` block, and the [resultBlockContent] of a
 * server tool's or an MCP tool's result block. Any other block is a part with only its type after
 * the prefix `anthropic_`. Image and document data is left out because it is large and binary.
 * Redacted thinking is left out because it is encrypted.
 */
@JvmSynthetic
internal fun blockPart(block: JsonNode): ObjectNode {
    val part = nodes.objectNode()
    when (val type = block.path("type").asText()) {
        "text" -> {
            part.put("type", "text")
            part.put("content", block.path("text").asText())
        }
        "thinking" -> {
            part.put("type", "reasoning")
            part.put("content", block.path("thinking").asText())
        }
        "tool_use",
        "server_tool_use",
        "mcp_tool_use" -> {
            part.put("type", "tool_call")
            part.put("id", block.path("id").asText())
            part.put("name", block.path("name").asText())
            part.set<JsonNode>("arguments", block.get("input"))
            if (type == "mcp_tool_use") {
                part.put("anthropic_mcp_server_name", block.path("server_name").asText())
            }
            val toolsetName = block.path("toolset_name")
            if (type == "tool_use" && toolsetName.isTextual) {
                part.put("anthropic_toolset_name", toolsetName.asText())
            }
        }
        "tool_result" -> {
            part.put("type", "tool_call_response")
            part.put("id", block.path("tool_use_id").asText())
            part.set<JsonNode>("response", toolResultContent(block.get("content")))
        }
        else -> {
            if (!type.endsWith("_tool_result")) {
                return part.put("type", "anthropic_" + type.ifEmpty { "unknown" })
            }
            part.put("type", "tool_call_response")
            part.put("id", block.path("tool_use_id").asText())
            part.set<JsonNode>("response", resultBlockContent(block.get("content")))
        }
    }
    return part
}

/** The parts of [content], a string or a list of content blocks. A string is one text part. */
@JvmSynthetic
internal fun contentParts(content: JsonNode?): ArrayNode {
    val parts = nodes.arrayNode()
    when {
        content == null -> {}
        content.isTextual ->
            parts.add(nodes.objectNode().put("type", "text").put("content", content.asText()))
        content.isArray -> content.forEach { parts.add(blockPart(it)) }
    }
    return parts
}

/**
 * The content blocks of [content], the `content` of a tool result as it is sent to the API: a
 * string or a list of content blocks. A string is one `text` block. No content is an empty list. A
 * `text` block and a `search_result` block keep all their fields except those whose name starts
 * with `encrypted_` ([withoutOpaqueData]). Any other block has its type only. Image and document
 * data is left out because it is large and binary.
 */
@JvmSynthetic
internal fun toolResultContent(content: JsonNode?): ArrayNode {
    val blocks = nodes.arrayNode()
    when {
        content == null -> {}
        content.isTextual ->
            blocks.add(nodes.objectNode().put("type", "text").put("text", content.asText()))
        content.isArray ->
            content.forEach { block ->
                val type = block.path("type").asText()
                blocks.add(
                    if (type == "text" || type == "search_result") withoutOpaqueData(block)
                    else nodes.objectNode().put("type", type.ifEmpty { "unknown" })
                )
            }
    }
    return blocks
}

/**
 * The response for [content], the `content` of the result block of a server tool or an MCP tool. A
 * string is one `text` block. No content is an empty list. In a list, a `text` block, a
 * `search_result` block and a `web_search_result` are kept. Any other element has its type only. A
 * content that is one object, such as a `code_execution_result`, is kept. What is kept is
 * [withoutOpaqueData].
 */
@JvmSynthetic
internal fun resultBlockContent(content: JsonNode?): JsonNode {
    if (content == null || content.isNull) {
        return nodes.arrayNode()
    }
    if (content.isTextual) {
        return nodes
            .arrayNode()
            .add(nodes.objectNode().put("type", "text").put("text", content.asText()))
    }
    if (content.isObject) {
        return withoutOpaqueData(content)
    }
    val blocks = nodes.arrayNode()
    content.forEach { element ->
        val type = element.path("type").asText()
        blocks.add(
            if (type == "text" || type == "search_result" || type == "web_search_result")
                withoutOpaqueData(element)
            else nodes.objectNode().put("type", type.ifEmpty { "unknown" })
        )
    }
    return blocks
}

/**
 * [node] as the API returns it, at any depth without the fields whose name starts with
 * `encrypted_`, which are opaque. Image and document data is left out because it is large and
 * binary. An `image` or `document` block has its type only. The view of a file that is an image or
 * a PDF has no `content`.
 */
private fun withoutOpaqueData(node: JsonNode): JsonNode {
    if (node.isArray) {
        return nodes.arrayNode().addAll(node.map(::withoutOpaqueData))
    }
    if (!node.isObject) {
        return node
    }
    val type = node.path("type").asText()
    if (type == "image" || type == "document") {
        return nodes.objectNode().put("type", type)
    }
    val viewsBinaryFile =
        type == "text_editor_code_execution_view_result" &&
            (node.path("file_type").asText() == "image" || node.path("file_type").asText() == "pdf")
    val kept = nodes.objectNode()
    node.fields().forEach { (name, value) ->
        if (name.startsWith("encrypted_") || (viewsBinaryFile && name == "content")) {
            return@forEach
        }
        kept.set<JsonNode>(name, withoutOpaqueData(value))
    }
    return kept
}

/** A message part, and its JSON, which is kept once it has been asked for. */
internal class Part(val node: JsonNode) {

    private var json: String? = null

    fun json(mapper: JsonMapper): String =
        json ?: mapper.writeValueAsString(node).also { json = it }

    /** Lets go of the JSON of a part that does not fit: it can be large, and no span has it. */
    fun forgetJson() {
        json = null
    }
}

/**
 * The [parts] of the system instructions or of one message, between the JSON that is around them:
 * [prefix] and [suffix], which stay when every part goes.
 */
internal class PartList(val prefix: String, val suffix: String, val parts: List<Part>) {

    fun json(mapper: JsonMapper): String =
        parts.joinToString(",", prefix, suffix) { it.json(mapper) }
}

/** The content attributes as [fitContent] gives them. One that the span does not have is `null`. */
internal class FittedContent(
    val systemInstructions: String?,
    val inputMessages: String?,
    val outputMessages: String?,
    /**
     * Whether the span keeps the content that is never cut, `gen_ai.request.stop_sequences`. It is
     * `false` when that content alone is over the limit: the span then goes without it.
     */
    val keepsUnreduced: Boolean,
    /**
     * The bytes of the three together, and of the content that is never cut when it is kept: their
     * [utf8Length], or, when nothing was counted because [MAX_UTF8_BYTES_PER_CHAR] for each char
     * fits, that number, which is no less.
     */
    val size: Int,
    /**
     * Whether a part was removed, the end of the last `text` part was cut off, or the content that
     * is never cut was left out.
     */
    val truncated: Boolean,
)

/**
 * The content of a Messages create request as [setRequestContent] set it on the span, kept so that
 * [setResponseContent] fits the response's content beside it without converting the request again.
 * The parts are those of the request, none removed and none cut, so that fitting them again with
 * the response gives what fitting the request and the response together gives.
 */
internal class RequestContent(
    /** The most that the content attributes hold together: the client's [contentLimit]. */
    val limit: Int,
    /**
     * The [utf8Length] of the content that is never cut: each element of the request's stop
     * sequences, also when the span goes without them because they alone are over [limit]. Zero
     * when the request has none.
     */
    val unreducedSize: Int,
    /**
     * The parts of `gen_ai.system_instructions`, or `null` when the request has no system prompt.
     */
    val system: PartList?,
    /** The messages of `gen_ai.input.messages`, the oldest first. */
    val input: List<PartList>,
    /** What the span has of the request's content. */
    val fitted: FittedContent,
)

/**
 * Sets the content attributes of a Messages create request: its [system] prompt, its [messages],
 * and its [stopSequences], an array of strings and not JSON, which is absent when the request sets
 * none or an empty list. The span has them whatever becomes of the call, and [setResponseContent]
 * adds the response's content to them. Each part that is kept is serialized once, and the parts
 * before the first one that does not fit are not serialized.
 *
 * Together the content attributes stay within [limit] where removing can bring them there, as
 * [fitContent] does: stop sequences that alone are over [limit] are left out whole. When anything
 * was removed, the span has `anthropic.content.truncated`; it is `true` or absent, never `false`.
 */
@JvmSynthetic
internal fun setRequestContent(
    span: Span,
    mapper: JsonMapper,
    system: JsonNode?,
    messages: JsonNode?,
    stopSequences: List<String>? = null,
    limit: Int = CONTENT_LIMIT,
): RequestContent {
    val unreducedSize = stopSequences.orEmpty().sumOf { utf8Length(it) }
    val instructions =
        if (system == null || system.isNull) null
        else PartList("[", "]", contentParts(system).map { Part(it) })
    val input =
        (messages ?: nodes.arrayNode()).map {
            messageParts(mapper, it.path("role").asText(), contentParts(it.get("content")))
        }
    val fitted = fitContent(mapper, limit, unreducedSize, instructions, input, output = null)
    if (!stopSequences.isNullOrEmpty() && fitted.keepsUnreduced) {
        span.setAttribute(GEN_AI_REQUEST_STOP_SEQUENCES, stopSequences)
    }
    setContent(span, fitted)
    return RequestContent(limit, unreducedSize, instructions, input, fitted)
}

/**
 * Adds [output], the [contentParts] of the response, or of as much of a streamed one as arrived, to
 * the content attributes that [setRequestContent] set, of which [request] is what it returned. Each
 * output part is serialized once. When the output fits beside the request's content, which is the
 * usual case, only `gen_ai.output.messages` is set, and no bytes are counted when
 * [MAX_UTF8_BYTES_PER_CHAR] for each char of it fits. Otherwise the request's parts and the
 * output's are fitted together, from the JSON that [request] kept, and every content attribute is
 * set again. A `text` part that was cut as the last part of the request goes whole once an output
 * part follows it.
 */
@JvmSynthetic
internal fun setResponseContent(
    span: Span,
    mapper: JsonMapper,
    request: RequestContent,
    output: ArrayNode,
) {
    val outputMessages = listOf(messageParts(mapper, "assistant", output))
    if (!request.fitted.truncated) {
        val whole = jsonList(mapper, outputMessages)
        val most = MAX_UTF8_BYTES_PER_CHAR.toLong() * whole.length
        if (request.fitted.size + most <= request.limit) {
            span.setAttribute(GEN_AI_OUTPUT_MESSAGES, whole)
            return
        }
    }
    setContent(
        span,
        fitContent(
            mapper,
            request.limit,
            request.unreducedSize,
            request.system,
            request.input,
            outputMessages,
        ),
    )
}

/** Sets the attributes that [content] has, and `anthropic.content.truncated` when it is cut. */
private fun setContent(span: Span, content: FittedContent) {
    content.systemInstructions?.let { span.setAttribute(GEN_AI_SYSTEM_INSTRUCTIONS, it) }
    content.inputMessages?.let { span.setAttribute(GEN_AI_INPUT_MESSAGES, it) }
    content.outputMessages?.let { span.setAttribute(GEN_AI_OUTPUT_MESSAGES, it) }
    if (content.truncated) {
        span.setAttribute(ANTHROPIC_CONTENT_TRUNCATED, true)
    }
}

/**
 * The content attributes within [limit], where removing can bring them there: [system], the parts
 * of `gen_ai.system_instructions`, [input], the messages of `gen_ai.input.messages`, and [output],
 * those of `gen_ai.output.messages`. An attribute that the span does not have is `null` and stays
 * so. Every size is in bytes of UTF-8 ([utf8Length]). [unreducedSize] is that of the content that
 * counts toward [limit] and is never cut: `gen_ai.request.stop_sequences`, which the caller sets.
 *
 * First, when [unreducedSize] alone is over [limit], the span goes without that content
 * ([FittedContent.keepsUnreduced]): it counts as zero, and the result is truncated. That depends on
 * the request only, so fitting the request with the response agrees with fitting it alone.
 *
 * When the attributes are then within [limit], nothing else changes, and no bytes are counted when
 * [MAX_UTF8_BYTES_PER_CHAR] for each char of them fits ([fitByLength]). Otherwise the parts of
 * [system], then those of each message of [input], then those of each message of [output] are one
 * list, from which whole parts go, the first one first, while the attributes are over [limit] and
 * the list has more than one part. A message that lost every part stays, with no parts, and so does
 * the emptied list of the system instructions. When one part is left and the attributes are still
 * over [limit], a `text` part stays with the start of its `content` that fits ([cutText]), which is
 * empty when none of it does, and any other part goes whole. What is then still over [limit] is the
 * emptied lists, the messages without parts and the last `text` part, which stay as they are.
 */
@JvmSynthetic
internal fun fitContent(
    mapper: JsonMapper,
    limit: Int,
    unreducedSize: Int,
    system: PartList?,
    input: List<PartList>?,
    output: List<PartList>?,
): FittedContent {
    val lists = listOfNotNull(system) + input.orEmpty() + output.orEmpty()
    val count = lists.sumOf { it.parts.size }
    val keepsUnreduced = unreducedSize <= limit
    val keptUnreducedSize = if (keepsUnreduced) unreducedSize else 0
    if (
        keepsUnreduced &&
            fitByLength(mapper, lists, (limit - unreducedSize) / MAX_UTF8_BYTES_PER_CHAR)
    ) {
        val attributes =
            listOf(
                system?.json(mapper),
                input?.let { jsonList(mapper, it) },
                output?.let { jsonList(mapper, it) },
            )
        val most = unreducedSize + MAX_UTF8_BYTES_PER_CHAR * attributes.sumOf { it?.length ?: 0 }
        return FittedContent(attributes[0], attributes[1], attributes[2], true, most, false)
    }
    val room =
        limit -
            keptUnreducedSize -
            (if (system == null) 0 else utf8Length(system.prefix) + utf8Length(system.suffix)) -
            emptiedSize(input) -
            emptiedSize(output)
    // The parts that go are the first ones, so those that stay whole are the newest that fit.
    val whole = newestThatFit(mapper, lists, room)
    // When not even the last part fits, it is the one part that is left.
    val holdsLast = lists.indexOfLast { it.parts.isNotEmpty() }
    val last = if (whole == 0 && count > 0) lists[holdsLast].parts.last() else null
    val cut = last?.let { cutText(mapper, it.node, room) }?.let { Part(it) }
    var removed = count - whole - if (cut == null) 0 else 1
    val reduced = !keepsUnreduced || removed > 0 || (cut != null && cut.node != last.node)
    val kept =
        lists.mapIndexed { index, list ->
            val from = minOf(removed, list.parts.size)
            removed -= from
            val parts =
                if (cut != null && index == holdsLast) listOf(cut) else list.parts.drop(from)
            PartList(list.prefix, list.suffix, parts)
        }
    val firstInput = if (system == null) 0 else 1
    val systemInstructions = if (system == null) null else kept[0].json(mapper)
    val inputMessages =
        input?.let { jsonList(mapper, kept.subList(firstInput, firstInput + it.size)) }
    val outputMessages =
        output?.let { jsonList(mapper, kept.subList(kept.size - it.size, kept.size)) }
    val size =
        keptUnreducedSize +
            listOfNotNull(systemInstructions, inputMessages, outputMessages).sumOf {
                utf8Length(it)
            }
    return FittedContent(
        systemInstructions,
        inputMessages,
        outputMessages,
        keepsUnreduced,
        size,
        reduced,
    )
}

/**
 * Whether the JSON of [lists] is within [chars] chars, with what is around their parts, a comma
 * after each list and each part, and the brackets of two lists. Three bytes for each char is the
 * most that UTF-8 takes, so content that is within a third of the limit by its length is within the
 * limit, and its bytes need no counting. The JSON is asked from the last part back, and no further
 * than where [chars] run out.
 */
private fun fitByLength(mapper: JsonMapper, lists: List<PartList>, chars: Int): Boolean {
    var left = chars - 2 * "[]".length
    for (list in lists.asReversed()) {
        left -= list.prefix.length + list.suffix.length + 1
        for (part in list.parts.asReversed()) {
            left -= part.json(mapper).length + 1
            if (left < 0) {
                return false
            }
        }
    }
    return left >= 0
}

/**
 * How many parts of [lists], as one list, fit [room] from its end: the newest that fit. A part
 * takes its JSON and, when another part of its own list follows it, a comma. The JSON is asked from
 * the last part back, and no further than the first part that does not fit, whose JSON is not kept.
 */
private fun newestThatFit(mapper: JsonMapper, lists: List<PartList>, room: Int): Int {
    var left = room
    var fit = 0
    for (list in lists.asReversed()) {
        for (index in list.parts.indices.reversed()) {
            val part = list.parts[index]
            left -= utf8Length(part.json(mapper)) + if (index < list.parts.size - 1) 1 else 0
            if (left < 0) {
                part.forgetJson()
                return fit
            }
            fit++
        }
    }
    return fit
}

/**
 * The [utf8Length] of the JSON list of [messages] when none of them has a part: its brackets, what
 * is around each message's parts, and a comma between each two. Zero for an attribute that the span
 * does not have.
 */
private fun emptiedSize(messages: List<PartList>?): Int =
    if (messages == null) 0
    else
        2 +
            messages.sumOf { utf8Length(it.prefix) + utf8Length(it.suffix) } +
            maxOf(0, messages.size - 1)

/** The JSON list of [messages]. */
private fun jsonList(mapper: JsonMapper, messages: List<PartList>): String =
    messages.joinToString(",", "[", "]") { it.json(mapper) }

/**
 * The [parts] of a message of the conventions' content format, between the JSON that is around
 * them: its [role], and the brackets of its `parts`.
 */
private fun messageParts(mapper: JsonMapper, role: String, parts: ArrayNode): PartList =
    PartList(
        """{"role":${mapper.writeValueAsString(role)},"parts":[""",
        "]}",
        parts.map { Part(it) },
    )

/** The length of the JSON of a char that is escaped as `\uXXXX`, and as a backslash and a char. */
private const val UNICODE_ESCAPE_LENGTH = 6
private const val SHORT_ESCAPE_LENGTH = 2

/**
 * [part], a `text` part, whose JSON is within [limit] bytes of UTF-8, with the end of its `content`
 * cut off, never between the two chars of a surrogate pair: it has the longest start of its
 * `content` that fits, which is empty when none of it does. `null` for any other part, such as a
 * `reasoning` part or a `tool_call` part, which is never cut.
 */
private fun cutText(mapper: JsonMapper, part: JsonNode, limit: Int): ObjectNode? {
    val content = part.get("content")
    if (part.path("type").asText() != "text" || content == null || !content.isTextual) {
        return null
    }
    val text = content.asText()
    val room = limit - utf8Length(mapper.writeValueAsString(withContent(part, "")))
    // A char takes at least one byte of the JSON, so the chars that fit are among the first `room`.
    // In their JSON each of them is itself, or an escape sequence, which starts with a backslash
    // and is ASCII.
    val head = text.take(maxOf(room, 0))
    val escaped = mapper.writeValueAsString(head)
    var length = 0
    var escapedLength = 0
    var size = 0
    while (length < head.length) {
        val escapeLength =
            when {
                escaped[1 + escapedLength] != '\\' -> 0
                escaped[2 + escapedLength] == 'u' -> UNICODE_ESCAPE_LENGTH
                else -> SHORT_ESCAPE_LENGTH
            }
        size += if (escapeLength > 0) escapeLength else utf8Length(head[length])
        if (size > room) {
            break
        }
        escapedLength += maxOf(escapeLength, 1)
        length++
    }
    if (
        length in 1 until text.length && Character.isSurrogatePair(text[length - 1], text[length])
    ) {
        length--
    }
    return withContent(part, text.substring(0, length))
}

/** [part] with [content] as its `content`, and its fields in the same order. */
private fun withContent(part: JsonNode, content: String): ObjectNode {
    val copy = nodes.objectNode()
    part.fields().forEach { (name, value) -> copy.set<JsonNode>(name, value) }
    return copy.put("content", content)
}

/** A serialized content attribute, and whether anything was removed from it to fit the limit. */
internal class Reduction(val json: String, val reduced: Boolean)

/**
 * The JSON of [jsons] between [prefix] and [suffix], with a comma between each two, within [limit]
 * bytes of UTF-8 ([utf8Length]), removing items from the end, the last one first, until none is
 * left.
 */
@JvmSynthetic
internal fun truncateTrailing(
    jsons: List<String>,
    limit: Int,
    prefix: String,
    suffix: String,
): Reduction {
    val sizes = jsons.map { utf8Length(it) }
    var size = utf8Length(prefix) + utf8Length(suffix) + sizes.sum() + maxOf(0, jsons.size - 1)
    var end = jsons.size
    while (end > 0 && size > limit) {
        end--
        // The first item has no comma before it.
        size -= sizes[end] + if (end > 0) 1 else 0
    }
    return Reduction(jsons.subList(0, end).joinToString(",", prefix, suffix), end < jsons.size)
}
