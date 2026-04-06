package kotlinx.kmp.util.core

/** URL-encode a string for use in URL paths and query parameters. */
expect fun urlEncode(value: String): String

/**
 * Blocking coroutine wrapper. JVM: delegates to kotlinx.coroutines.runBlocking.
 * JS: throws UnsupportedOperationException (use suspend functions directly on JS).
 */
expect fun <T> runBlockingCompat(block: suspend () -> T): T
