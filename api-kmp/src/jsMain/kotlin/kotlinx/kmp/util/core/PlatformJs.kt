package kotlinx.kmp.util.core

import kotlinx.kmp.util.core.http.HttpClient
import kotlinx.kmp.util.core.json.KotlinxApiJsonBackend
import kotlinx.serialization.json.Json

// --- Properties ---

actual fun getOsArch(): String = "js"
actual fun getOsName(): String = "Browser"
actual fun getOsVersion(): String = "unknown"
actual fun getPackageVersion(): String = "unknown"
actual fun getJavaVersion(): String = "N/A"

// --- Platform ---

actual fun urlEncode(value: String): String =
    js("encodeURIComponent(value)").unsafeCast<String>()

actual fun <T> runBlockingCompat(block: suspend () -> T): T =
    throw UnsupportedOperationException("Blocking execution not supported on JS. Use suspend functions.")

// --- PlatformTime ---

actual fun currentTimeMillis(): Long = kotlin.js.Date.now().toLong()

actual fun parseRetryAfterToDelayNanos(headerValue: String, nowMillis: Long): Long? {
    // Try as seconds (numeric)
    headerValue.trim().toDoubleOrNull()?.let { seconds ->
        return (seconds * 1_000_000_000).toLong()
    }
    // Try as HTTP-date
    val parsed = kotlin.js.Date.parse(headerValue.trim())
    if (parsed.isNaN()) return null
    val delayMs = parsed.toLong() - nowMillis
    return if (delayMs > 0) delayMs * 1_000_000L else 0L
}

// --- PhantomReachable ---

actual fun closeWhenPhantomReachable(observed: Any, close: () -> Unit) {
    // JS has no phantom references — GC cleanup is not available.
    // Resources must be closed explicitly.
}

// --- ClientOptions platform functions ---

actual fun createDefaultStreamExecutor(): Any? = null
actual fun createDefaultSleeper(): Sleeper = DefaultSleeper()
actual fun shutdownStreamExecutor(executor: Any?) {} // no-op on JS
actual fun wrapHttpClient(client: HttpClient): HttpClient = client // no phantom-reachable on JS

// --- ObjectMappers ---

actual class JsonMapperType(val json: Json)

actual fun jsonMapper(): JsonMapperType = JsonMapperType(Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = false
    coerceInputValues = true
})

actual fun apiJsonBackend(): ApiJsonBackend = KotlinxApiJsonBackend()

actual fun checkJsonVersionCompatibility() {} // no-op on JS
