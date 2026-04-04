package java.io

open class IOException : Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}

abstract class InputStream {
    abstract fun read(): Int
    open fun read(b: ByteArray): Int = read(b, 0, b.size)
    open fun read(b: ByteArray, off: Int, len: Int): Int = -1
    open fun readBytes(): ByteArray = ByteArray(0)
    open fun close() {}
    inline fun <T> use(block: (InputStream) -> T): T { try { return block(this) } finally { close() } }
}

abstract class OutputStream {
    abstract fun write(b: Int)
    open fun write(bytes: ByteArray) {}
    open fun write(bytes: ByteArray, off: Int, len: Int) {}
    open fun close() {}
    open fun flush() {}
}
