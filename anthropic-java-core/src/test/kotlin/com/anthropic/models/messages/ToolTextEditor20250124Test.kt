// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolTextEditor20250124Test {

    @Test
    fun create() {
        val toolTextEditor20250124 =
            ToolTextEditor20250124.builder()
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        assertThat(toolTextEditor20250124.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolTextEditor20250124 =
            ToolTextEditor20250124.builder()
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val roundtrippedToolTextEditor20250124 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolTextEditor20250124),
                jacksonTypeRef<ToolTextEditor20250124>(),
            )

        assertThat(roundtrippedToolTextEditor20250124).isEqualTo(toolTextEditor20250124)
    }
}
