@file:JvmName("HttpRequestBodies")

package com.anthropic.core.http

import com.anthropic.core.MultipartField
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.node.JsonNodeType
import com.fasterxml.jackson.databind.node.ObjectNode
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.nio.file.Files
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

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
@JvmSynthetic
inline fun <reified T> json(jsonMapper: JsonMapper, value: T): HttpRequestBody =
    object : HttpRequestBody {
        private val bytes: ByteArray by lazy { jsonMapper.writeValueAsBytes(value) }

        override fun writeTo(outputStream: OutputStream) = outputStream.write(bytes)

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
@JvmSynthetic
fun bodyToJson(jsonMapper: ObjectMapper, body: HttpRequestBody?): ObjectNode? {
    val jsonData = ByteArrayOutputStream()

    body?.writeTo(jsonData)
    if (jsonData.size() > 0) {
        return jsonMapper.readValue(jsonData.toByteArray(), ObjectNode::class.java)
    }
    return null
}

@JvmSynthetic
internal fun multipartFormData(
    jsonMapper: JsonMapper,
    fields: Map<String, MultipartField<*>>,
): HttpRequestBody =
    MultipartBody.Builder()
        .apply { fields.forEach { (name, field) -> addParts(jsonMapper, name, field) } }
        .build()

private fun MultipartBody.Builder.addParts(
    jsonMapper: JsonMapper,
    name: String,
    field: MultipartField<*>,
) {
    val knownValue = field.value.asKnown().getOrNull()

    if (knownValue is List<*>) {
        val elementFields = knownValue.filterIsInstance<MultipartField<*>>()
        if (elementFields.size == knownValue.size) {
            // Each element carries its own filename and content type, so it must be serialized as
            // its own part instead of inheriting the metadata of the enclosing field.
            elementFields.forEach { addParts(jsonMapper, "$name[]", it) }
            return
        }
    }

    val parts =
        if (knownValue is InputStream) {
            // Read directly from the `InputStream` instead of reading it all
            // into memory due to the `jsonMapper` serialization below.
            sequenceOf(name to knownValue)
        } else {
            val node = jsonMapper.valueToTree<JsonNode>(field.value)
            serializePart(name, node)
        }

    parts.forEach { (name, bytes) ->
        addPart(
            MultipartBody.Part.create(
                name,
                field.filename().getOrNull(),
                field.contentType,
                inputStreamBody(bytes, field.contentType),
            )
        )
    }
}

private fun serializePart(name: String, node: JsonNode): Sequence<Pair<String, InputStream>> =
    when (node.nodeType) {
        JsonNodeType.MISSING,
        JsonNodeType.NULL -> emptySequence()
        JsonNodeType.BINARY -> sequenceOf(name to node.binaryValue().inputStream())
        JsonNodeType.STRING -> sequenceOf(name to node.textValue().byteInputStream())
        JsonNodeType.BOOLEAN -> sequenceOf(name to node.booleanValue().toString().byteInputStream())
        JsonNodeType.NUMBER -> sequenceOf(name to node.numberValue().toString().byteInputStream())
        JsonNodeType.ARRAY ->
            node.elements().asSequence().flatMap { element -> serializePart("$name[]", element) }
        JsonNodeType.OBJECT ->
            node.fields().asSequence().flatMap { (key, value) ->
                serializePart("$name[$key]", value)
            }
        JsonNodeType.POJO,
        null -> throw AnthropicInvalidDataException("Unexpected JsonNode type: ${node.nodeType}")
    }

/**
 * Returns a part body that sends the content of [inputStream].
 *
 * The body is repeatable when the content can be read again without reading a stream into memory:
 * bytes already in memory, an unread [PathInputStream] over a regular file (opened again for each
 * write), or a [FileInputStream] whose position can be read (each write starts from the position it
 * had when the body was created). Any other stream is written once.
 */
private fun inputStreamBody(inputStream: InputStream, contentType: String): HttpRequestBody {
    if (inputStream is ByteArrayInputStream) {
        val bytes = inputStream.readBytes()
        return object : HttpRequestBody {
            override fun writeTo(outputStream: OutputStream) = outputStream.write(bytes)

            override fun contentType(): String = contentType

            override fun contentLength(): Long = bytes.size.toLong()

            override fun repeatable(): Boolean = true

            override fun close() {}
        }
    }

    if (
        inputStream is PathInputStream &&
            !inputStream.isRead &&
            Files.isRegularFile(inputStream.path)
    ) {
        val path = inputStream.path
        return object : HttpRequestBody {
            override fun writeTo(outputStream: OutputStream) {
                Files.newInputStream(path).use { it.copyTo(outputStream) }
            }

            override fun contentType(): String = contentType

            override fun contentLength(): Long = runCatching { Files.size(path) }.getOrDefault(-1L)

            override fun repeatable(): Boolean = true

            override fun close() = inputStream.close()
        }
    }

    if (inputStream is FileInputStream) {
        val channel = inputStream.channel
        // A pipe opened as a `FileInputStream` (stdin, for example) has no position: `position()`
        // throws.
        val startPosition =
            try {
                channel.position()
            } catch (e: IOException) {
                null
            }
        if (startPosition != null) {
            return object : HttpRequestBody {
                override fun writeTo(outputStream: OutputStream) {
                    channel.position(startPosition)
                    inputStream.copyTo(outputStream)
                }

                override fun contentType(): String = contentType

                override fun contentLength(): Long = maxOf(channel.size() - startPosition, 0)

                override fun repeatable(): Boolean = true

                override fun close() = inputStream.close()
            }
        }
    }

    return object : HttpRequestBody {
        override fun writeTo(outputStream: OutputStream) {
            inputStream.copyTo(outputStream)
        }

        override fun contentType(): String = contentType

        override fun contentLength(): Long = -1L

        override fun repeatable(): Boolean = false

        override fun close() = inputStream.close()
    }
}

private class MultipartBody
private constructor(private val boundary: String, private val parts: List<Part>) : HttpRequestBody {
    private val boundaryBytes: ByteArray = boundary.toByteArray()
    private val contentType = "multipart/form-data; boundary=$boundary"

    // This must remain in sync with `contentLength`.
    override fun writeTo(outputStream: OutputStream) {
        parts.forEach { part ->
            outputStream.write(DASHDASH)
            outputStream.write(boundaryBytes)
            outputStream.write(CRLF)

            outputStream.write(CONTENT_DISPOSITION)
            outputStream.write(part.contentDisposition.toByteArray())
            outputStream.write(CRLF)

            outputStream.write(CONTENT_TYPE)
            outputStream.write(part.contentType.toByteArray())
            outputStream.write(CRLF)

            outputStream.write(CRLF)
            part.body.writeTo(outputStream)
            outputStream.write(CRLF)
        }

        outputStream.write(DASHDASH)
        outputStream.write(boundaryBytes)
        outputStream.write(DASHDASH)
        outputStream.write(CRLF)
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
        private val boundary = UUID.randomUUID().toString()
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
            fun create(
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
