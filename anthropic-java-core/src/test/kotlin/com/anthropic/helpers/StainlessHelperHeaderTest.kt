package com.anthropic.helpers

import com.anthropic.core.http.Headers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class StainlessHelperHeaderTest {

    @Test
    fun `appends to an empty headers`() {
        val merged =
            mergedStainlessHelperValue(
                Headers.builder().build(),
                StainlessHelperHeaderValue.FALLBACK_REFUSAL_MIDDLEWARE,
            )
        assertThat(merged).isEqualTo("fallback-refusal-middleware")
    }

    @Test
    fun `appends to an existing tag`() {
        val headers = Headers.builder().put(STAINLESS_HELPER_HEADER, "BetaToolRunner").build()
        val merged =
            mergedStainlessHelperValue(
                headers,
                StainlessHelperHeaderValue.FALLBACK_REFUSAL_MIDDLEWARE,
            )
        assertThat(merged).isEqualTo("BetaToolRunner, fallback-refusal-middleware")
    }

    @Test
    fun `dedups`() {
        val headers =
            Headers.builder().put(STAINLESS_HELPER_HEADER, "fallback-refusal-middleware").build()
        val merged =
            mergedStainlessHelperValue(
                headers,
                StainlessHelperHeaderValue.FALLBACK_REFUSAL_MIDDLEWARE,
            )
        assertThat(merged).isEqualTo("fallback-refusal-middleware")
    }

    @Test
    fun `collapses multiple lines and casings`() {
        val headers =
            Headers.builder()
                .put("X-Stainless-Helper", "a")
                .put(STAINLESS_HELPER_HEADER, "b, c")
                .build()
        val merged =
            mergedStainlessHelperValue(
                headers,
                StainlessHelperHeaderValue.FALLBACK_REFUSAL_MIDDLEWARE,
            )
        assertThat(merged).isEqualTo("a, b, c, fallback-refusal-middleware")
    }
}
