// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolBash20250124Test {

    @Test
    fun create() {
        val toolBash20250124 =
            ToolBash20250124.builder().cacheControl(CacheControlEphemeral.builder().build()).build()

        assertThat(toolBash20250124.cacheControl())
            .contains(CacheControlEphemeral.builder().build())
    }
}
