// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ToolTextEditor20250124Test {

    @Test
    fun createToolTextEditor20250124() {
        val toolTextEditor20250124 =
            ToolTextEditor20250124.builder()
                .cacheControl(CacheControlEphemeral.builder().build())
                .build()
        assertThat(toolTextEditor20250124).isNotNull
        assertThat(toolTextEditor20250124.cacheControl())
            .contains(CacheControlEphemeral.builder().build())
    }
}
