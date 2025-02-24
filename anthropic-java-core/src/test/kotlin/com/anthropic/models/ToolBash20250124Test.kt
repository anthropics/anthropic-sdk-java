// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ToolBash20250124Test {

    @Test
    fun createToolBash20250124() {
        val toolBash20250124 =
            ToolBash20250124.builder().cacheControl(CacheControlEphemeral.builder().build()).build()
        assertThat(toolBash20250124).isNotNull
        assertThat(toolBash20250124.cacheControl())
            .contains(CacheControlEphemeral.builder().build())
    }
}
