@file:OptIn(kotlin.uuid.ExperimentalUuidApi::class)
// File generated from our OpenAPI spec by Stainless.


package com.anthropic.core.http

import com.anthropic.core.MultipartField
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.node.JsonNodeType
import com.fasterxml.jackson.databind.node.ObjectNode
import okio.Buffer
import okio.BufferedSink
import okio.Source
import okio.buffer
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Creates a new [HttpRequestBody] containing the given value serialized to a JSON string. The
 * content type will be set to `application/json` and will use UTF-8 encoding.
 *
 * The use of this method is _not supported_ as part of the public API of the Anthropic SDK. This
 * method may change or be removed without any prior notice.
 *
 * @param value The data to form the request body. This may be any object; it is not limited to JSON
 *   objects or nodes.
 */
inline fun <reified T> json(jsonMapper: JsonMapper, value: T): HttpRequestBody =
    object : HttpRequestBody {
        private val bytes: ByteArray by lazy { jsonMapper.writeValueAsBytes(value) }

        override fun writeTo(sink: BufferedSink) { sink.write(bytes) }

        override fun contentType(): String = "application/json"

        override fun contentLength(): Long = bytes.size.toLong()

        override fun repeatable(): Boolean = true

        override fun close() {}
    }

/**
 * Creates a JSON [ObjectNode] representing the JSON data parsed from a [HttpRequestBody].
 *
 * The use of this method is _not supported_ as part of the public API of the Anthropic SDK. This
 * method may change or be removed without any prior notice.
 */
fun bodyToJson(jsonMapper: ObjectMapper, body: HttpRequestBody?): ObjectNode? {
    val buf = Buffer()
    body?.writeTo(buf)
    if (buf.size > 0) {
        return jsonMapper.readValue(buf.readByteArray(), ObjectNode::class.java)
    }
    return null
}

internal fun multipartFormData(
    jsonMapper: JsonMapper,
    fields: Map<String, MultipartField<*>>,
): HttpRequestBody =
    MultipartBody.Builder()
        .apply {
            fields.forEach { (name, field) ->
                val knownValue = field.value.asKnown()
                val parts =
                    if (knownValue is Source || knownValue is java.io.InputStream) {
                        // Read directly from the stream/source instead of reading it all
                        // into memory due to the `jsonMapper` serialization below.
                        @Suppress("DEPRECATION")
                        val source: Source = if (knownValue is Source) knownValue
                            else (knownValue as java.io.InputStream).let { stream ->
                                // Bridge InputStream to okio Source on JVM
                                object : Source {
                                    override fun read(sink: okio.Buffer, byteCount: Long): Long {
                                        val bytes = ByteArray(byteCount.toInt().coerceAtMost(8192))
                                        val n = stream.read(bytes)
                                        if (n == -1) return -1
                                        sink.write(bytes, 0, n)
                                        return n.toLong()
                                    }
                                    override fun timeout() = okio.Timeout.NONE
                                    override fun close() = stream.close()
                                }
                            }
                        sequenceOf(name to source)
                    } else {
                        // Serialize the inner value directly to avoid Jackson key serializer
                        // issues with KnownValue wrapper on older Jackson versions.
                        val nodeValue = if (knownValue != null) knownValue else field.value
                        val node = jsonMapper.valueToTree<JsonNode>(nodeValue)
                        serializePart(name, node)
                    }

                parts.forEach { (name, source) ->
                    val byteArray = source.buffer().readByteArray()
                    val partBody = object : HttpRequestBody {
                        override fun writeTo(sink: BufferedSink) { sink.write(byteArray) }
                        override fun contentType(): String = field.contentType
                        override fun contentLength(): Long = byteArray.size.toLong()
                        override fun repeatable(): Boolean = true
                        override fun close() {}
                    }

                    addPart(
                        MultipartBody.Part.create(
                            name,
                            field.filename(),
                            field.contentType,
                            partBody,
                        )
                    )
                }
            }
        }
        .build()

private fun serializePart(name: String, node: JsonNode): Sequence<Pair<String, Source>> =
    when (node.nodeType) {
        JsonNodeType.MISSING,
        JsonNodeType.NULL -> emptySequence()
        JsonNodeType.BINARY -> sequenceOf(name to Buffer().write(node.binaryValue()))
        JsonNodeType.STRING -> sequenceOf(name to Buffer().writeUtf8(node.textValue()))
        JsonNodeType.BOOLEAN -> sequenceOf(name to Buffer().writeUtf8(node.booleanValue().toString()))
        JsonNodeType.NUMBER -> sequenceOf(name to Buffer().writeUtf8(node.numberValue().toString()))
        JsonNodeType.ARRAY ->
            sequenceOf(
                name to
                    Buffer().writeUtf8(
                        node
                            .elements()
                            .asSequence()
                            .mapNotNull { element ->
                                when (element.nodeType) {
                                    JsonNodeType.MISSING,
                                    JsonNodeType.NULL -> null
                                    JsonNodeType.STRING -> element.textValue()
                                    JsonNodeType.BOOLEAN -> element.booleanValue().toString()
                                    JsonNodeType.NUMBER -> element.numberValue().toString()
                                    null,
                                    JsonNodeType.BINARY,
                                    JsonNodeType.ARRAY,
                                    JsonNodeType.OBJECT,
                                    JsonNodeType.POJO ->
                                        throw AnthropicInvalidDataException(
                                            "Unexpected JsonNode type in array: ${element.nodeType}"
                                        )
                                }
                            }
                            .joinToString(",")
                    )
            )
        JsonNodeType.OBJECT ->
            node.fields().asSequence().flatMap { (key, value) ->
                serializePart("$name[$key]", value)
            }
        JsonNodeType.POJO,
        null -> throw AnthropicInvalidDataException("Unexpected JsonNode type: ${node.nodeType}")
    }

private class MultipartBody
private constructor(private val boundary: String, private val parts: List<Part>) : HttpRequestBody {
    private val boundaryBytes: ByteArray = boundary.toByteArray()
    private val contentType = "multipart/form-data; boundary=$boundary"

    // This must remain in sync with `contentLength`.
    override fun writeTo(sink: BufferedSink) {
        parts.forEach { part ->
            sink.write(DASHDASH)
            sink.write(boundaryBytes)
            sink.write(CRLF)

            sink.write(CONTENT_DISPOSITION)
            sink.write(part.contentDisposition.toByteArray())
            sink.write(CRLF)

            sink.write(CONTENT_TYPE)
            sink.write(part.contentType.toByteArray())
            sink.write(CRLF)

            sink.write(CRLF)
            part.body.writeTo(sink)
            sink.write(CRLF)
        }

        sink.write(DASHDASH)
        sink.write(boundaryBytes)
        sink.write(DASHDASH)
        sink.write(CRLF)
    }

    override fun contentType(): String = contentType

    // This must remain in sync with `writeTo`.
    override fun contentLength(): Long {
        var byteCount = 0L

        parts.forEach { part ->
            val contentLength = part.body.contentLength()
            if (contentLength == -1L) {
                return -1L
            }

            byteCount +=
                DASHDASH.size +
                    boundaryBytes.size +
                    CRLF.size +
                    CONTENT_DISPOSITION.size +
                    part.contentDisposition.toByteArray().size +
                    CRLF.size +
                    CONTENT_TYPE.size +
                    part.contentType.toByteArray().size +
                    CRLF.size +
                    CRLF.size +
                    contentLength +
                    CRLF.size
        }

        byteCount += DASHDASH.size + boundaryBytes.size + DASHDASH.size + CRLF.size
        return byteCount
    }

    override fun repeatable(): Boolean = parts.all { it.body.repeatable() }

    override fun close() {
        parts.forEach { it.body.close() }
    }

    class Builder {
    @OptIn(ExperimentalUuidApi::class)
        private val boundary = Uuid.random().toString()
        private val parts: MutableList<Part> = mutableListOf()

        fun addPart(part: Part) = apply { parts.add(part) }

        fun build() = MultipartBody(boundary, parts.toImmutable())
    }

    class Part
    private constructor(
        val contentDisposition: String,
        val contentType: String,
        val body: HttpRequestBody,
    ) {
        companion object {
            @JvmStatic fun create(
                name: String,
                filename: String?,
                contentType: String,
                body: HttpRequestBody,
            ): Part {
                val disposition = buildString {
                    append("form-data; name=")
                    appendQuotedString(name)
                    if (filename != null) {
                        append("; filename=")
                        appendQuotedString(filename)
                    }
                }
                return Part(disposition, contentType, body)
            }
        }
    }

    companion object {
        private val CRLF = byteArrayOf('\r'.code.toByte(), '\n'.code.toByte())
        private val DASHDASH = byteArrayOf('-'.code.toByte(), '-'.code.toByte())
        private val CONTENT_DISPOSITION = "Content-Disposition: ".toByteArray()
        private val CONTENT_TYPE = "Content-Type: ".toByteArray()

        private fun StringBuilder.appendQuotedString(key: String) {
            append('"')
            for (ch in key) {
                when (ch) {
                    '\n' -> append("%0A")
                    '\r' -> append("%0D")
                    '"' -> append("%22")
                    else -> append(ch)
                }
            }
            append('"')
        }
    }
}
