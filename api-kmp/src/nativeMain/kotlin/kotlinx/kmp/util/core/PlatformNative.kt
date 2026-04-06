package kotlinx.kmp.util.core

import kotlinx.kmp.util.core.http.HttpClient
import kotlinx.kmp.util.core.json.KotlinxApiJsonBackend
import kotlinx.serialization.json.Json
import kotlinx.coroutines.runBlocking

// --- Properties ---

actual fun getOsArch(): String = "native"
actual fun getOsName(): String = "Native"
actual fun getOsVersion(): String = "unknown"
actual fun getPackageVersion(): String = "unknown"
actual fun getJavaVersion(): String = "N/A"

// --- Platform ---

actual fun urlEncode(value: String): String {
    // Simple percent-encoding for native
    val sb = StringBuilder()
    for (c in value) {
        when {
            c.isLetterOrDigit() || c in "-._~" -> sb.append(c)
            else -> {
                val bytes = c.toString().encodeToByteArray()
                for (b in bytes) sb.append("%${(b.toInt() and 0xFF).toString(16).uppercase().padStart(2, '0')}")
            }
        }
    }
    return sb.toString()
}

actual fun <T> runBlockingCompat(block: suspend () -> T): T = runBlocking { block() }

// --- PlatformTime ---

actual fun currentTimeMillis(): Long = kotlinx.datetime.Clock.System.now().toEpochMilliseconds()

actual fun parseRetryAfterToDelayNanos(headerValue: String, nowMillis: Long): Long? {
    headerValue.trim().toDoubleOrNull()?.let { return (it * 1_000_000_000).toLong() }
    return null // RFC 1123 date parsing not supported on native yet
}

// --- PhantomReachable ---

actual fun closeWhenPhantomReachable(observed: Any, close: () -> Unit) {
    // Native has no phantom references — resources must be closed explicitly.
}

// --- ClientOptions ---

actual fun createDefaultStreamExecutor(): Any? = null
actual fun createDefaultSleeper(): Sleeper = DefaultSleeper()
actual fun shutdownStreamExecutor(executor: Any?) {}
actual fun wrapHttpClient(client: HttpClient): HttpClient = client

// --- ObjectMappers ---

actual class JsonMapperType(val json: Json)

actual fun jsonMapper(): JsonMapperType = JsonMapperType(Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = false
    coerceInputValues = true
})

actual fun apiJsonBackend(): ApiJsonBackend = KotlinxApiJsonBackend()

actual fun checkJsonVersionCompatibility() {}
