package com.anthropic.core

internal actual fun urlEncode(value: String): String =
    js("encodeURIComponent(value)").unsafeCast<String>()
