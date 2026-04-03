package com.anthropic.core

/** URL-encode a string for use in URL paths and query parameters. */
internal expect fun urlEncode(value: String): String
