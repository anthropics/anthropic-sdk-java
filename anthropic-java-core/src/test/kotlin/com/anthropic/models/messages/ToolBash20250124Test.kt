// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolBash20250124Test {

    @Test
    fun create() {
        val toolBash20250124 =
            ToolBash20250124.builder()
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        assertThat(toolBash20250124.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolBash20250124 =
            ToolBash20250124.builder()
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val roundtrippedToolBash20250124 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolBash20250124),
                jacksonTypeRef<ToolBash20250124>(),
            )

        assertThat(roundtrippedToolBash20250124).isEqualTo(toolBash20250124)
    }
}
