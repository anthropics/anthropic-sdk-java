package kotlinx.kmp.util.core

import java.net.URLEncoder

actual fun urlEncode(value: String): String = URLEncoder.encode(value, "UTF-8")

actual fun <T> runBlockingCompat(block: suspend () -> T): T = kotlinx.coroutines.runBlocking { block() }

