package kotlinx.kmp.util.core

internal actual fun urlEncode(value: String): String =
    js("encodeURIComponent(value)").unsafeCast<String>()
