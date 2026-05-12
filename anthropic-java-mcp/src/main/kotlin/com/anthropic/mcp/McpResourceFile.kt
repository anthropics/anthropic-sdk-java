package com.anthropic.mcp

import com.anthropic.core.checkRequired
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Represents an MCP resource as a file suitable for use with the files upload API. */
class McpResourceFile
private constructor(
    @get:JvmName("filename") val filename: String?,
    @get:JvmName("content") val content: ByteArray,
    @get:JvmName("mimeType") val mimeType: String?,
) {

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    fun toBuilder() = Builder().from(this)

    class Builder internal constructor() {

        private var filename: String? = null
        private var content: ByteArray? = null
        private var mimeType: String? = null

        @JvmSynthetic
        internal fun from(file: McpResourceFile) = apply {
            filename = file.filename
            content = file.content
            mimeType = file.mimeType
        }

        /** Filename extracted from the resource URI, or null if not determinable. */
        fun filename(filename: String?) = apply { this.filename = filename }

        /** Alias for calling [Builder.filename] with `filename.orElse(null)`. */
        fun filename(filename: Optional<String>) = filename(filename.getOrNull())

        /** Raw file bytes. */
        fun content(content: ByteArray) = apply { this.content = content }

        /** MIME type from the resource, or null if not specified. */
        fun mimeType(mimeType: String?) = apply { this.mimeType = mimeType }

        /** Alias for calling [Builder.mimeType] with `mimeType.orElse(null)`. */
        fun mimeType(mimeType: Optional<String>) = mimeType(mimeType.getOrNull())

        fun build(): McpResourceFile =
            McpResourceFile(filename, checkRequired("content", content), mimeType)
    }
}
