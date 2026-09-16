package com.anthropic.core.http

import java.io.FilterInputStream
import java.nio.file.Files
import java.nio.file.Path

/**
 * An input stream over the file at [path] that also exposes the path and whether anything has read
 * from the stream yet, so that a caller can open the file again by path instead of consuming this
 * stream.
 */
internal class PathInputStream(val path: Path) : FilterInputStream(Files.newInputStream(path)) {

    /** Whether anything has read from, or skipped over, this stream. */
    var isRead: Boolean = false
        private set

    override fun read(): Int {
        isRead = true
        return super.read()
    }

    override fun read(b: ByteArray, off: Int, len: Int): Int {
        isRead = true
        return super.read(b, off, len)
    }

    override fun skip(n: Long): Long {
        isRead = true
        return super.skip(n)
    }
}
