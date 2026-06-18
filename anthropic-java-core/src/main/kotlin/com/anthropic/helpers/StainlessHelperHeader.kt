package com.anthropic.helpers

import com.anthropic.core.http.Headers

/**
 * Telemetry header naming the SDK helper(s) a request came from. Always this lowercase form;
 * [Headers] is case-insensitive but a single canonical casing keeps every call site greppable.
 */
@JvmSynthetic internal const val STAINLESS_HELPER_HEADER = "x-stainless-helper"

/**
 * The closed set of helper telemetry tags, shared verbatim across SDKs. A typo at any call site is
 * a compile error rather than silently mistagged telemetry. Existing values keep their original
 * spellings — telemetry consumers match on them, so renames lose history. New tags are hyphenated
 * lowercase.
 */
internal enum class StainlessHelperHeaderValue(@JvmSynthetic internal val value: String) {
    FALLBACK_REFUSAL_MIDDLEWARE("fallback-refusal-middleware");

    override fun toString(): String = value
}

/**
 * Returns the [STAINLESS_HELPER_HEADER] value to set after appending [value] to whatever is already
 * present in [headers] — existing tags keep their position, the new tag goes at the end, duplicates
 * are dropped, joined as one comma-separated string. The backend logs the header as one opaque
 * string, so a second header line or a clobbered value loses data; callers set the result via
 * `replaceHeaders(STAINLESS_HELPER_HEADER, …)`.
 */
@JvmSynthetic
internal fun mergedStainlessHelperValue(
    headers: Headers,
    value: StainlessHelperHeaderValue,
): String {
    val tokens = LinkedHashSet<String>()
    headers.values(STAINLESS_HELPER_HEADER).forEach { existing ->
        existing.split(",").map(String::trim).filter(String::isNotEmpty).forEach(tokens::add)
    }
    tokens.add(value.value)
    return tokens.joinToString(", ")
}
