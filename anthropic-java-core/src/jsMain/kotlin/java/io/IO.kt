// JS compile-only stubs for java.io
package java.io

abstract class InputStream {
    abstract fun read(): Int
    open fun read(b: ByteArray): Int = read(b, 0, b.size)
    open fun read(b: ByteArray, off: Int, len: Int): Int = TODO("JS stub")
    fun readBytes(): ByteArray = TODO("JS stub")
    open fun close() {}
    inline fun <T> use(block: (InputStream) -> T): T {
        try {
            return block(this)
        } finally {
            close()
        }
    }
}

abstract class OutputStream {
    abstract fun write(b: Int)
    open fun write(b: ByteArray) { write(b, 0, b.size) }
    open fun write(b: ByteArray, off: Int, len: Int) { for (i in off until off + len) write(b[i].toInt()) }
    open fun flush() {}
    open fun close() {}
}

open class IOException : Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}
