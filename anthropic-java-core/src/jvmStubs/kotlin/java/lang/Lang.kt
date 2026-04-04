package java.lang

interface AutoCloseable {
    fun close()
}

fun interface Runnable {
    fun run()
}

class Thread(private val target: Runnable? = null) {
    var name: String = ""
    var isDaemon: Boolean = false
    fun start() { target?.run() }
    companion object {
        fun sleep(millis: Long) {}
        fun currentThread(): Thread = Thread()
    }
}

object System {
    fun getProperty(key: String): String? = null
    fun getProperty(key: String, defaultValue: String): String = defaultValue
    fun getenv(key: String): String? = null
    fun nanoTime(): Long = (kotlin.js.Date.now() * 1_000_000).toLong()
    fun currentTimeMillis(): Long = kotlin.js.Date.now().toLong()
}
