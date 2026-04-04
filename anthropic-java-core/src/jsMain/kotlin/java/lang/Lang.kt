// JS compile-only stubs for java.lang types used in commonMain
package java.lang

interface AutoCloseable {
    fun close()
}

object System {
    fun getProperty(key: String): String? = null
    fun getProperty(key: String, defaultValue: String): String = defaultValue
    fun getenv(name: String): String? = null
    fun nanoTime(): Long = (js("Date.now()") as Number).toLong() * 1_000_000
    fun currentTimeMillis(): Long = (js("Date.now()") as Number).toLong()
}

class Thread(private val target: Runnable? = null) {
    var name: String = ""

    fun start() { target?.run() }

    companion object {
        fun sleep(millis: Long) { /* no-op on JS */ }
    }
}

typealias Void = Nothing?
typealias Object = Any

// For Class<T> usage like `::class.java`
typealias Class<T> = kotlin.reflect.KClass<T>
