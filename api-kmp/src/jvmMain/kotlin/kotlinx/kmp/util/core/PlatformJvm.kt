package kotlinx.kmp.util.core

import java.net.URLEncoder

actual fun urlEncode(value: String): String = URLEncoder.encode(value, "UTF-8")

