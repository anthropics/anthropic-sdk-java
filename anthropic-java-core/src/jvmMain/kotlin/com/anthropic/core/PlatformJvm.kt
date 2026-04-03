package com.anthropic.core

import java.net.URLEncoder

internal actual fun urlEncode(value: String): String = URLEncoder.encode(value, "UTF-8")

actual typealias PlatformInputStream = java.io.InputStream
actual typealias PlatformOutputStream = java.io.OutputStream
actual typealias PlatformCloseable = java.lang.AutoCloseable
