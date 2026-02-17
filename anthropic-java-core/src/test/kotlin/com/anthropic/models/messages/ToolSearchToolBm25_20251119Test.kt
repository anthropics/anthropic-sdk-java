// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolSearchToolBm25_20251119Test {

    @Test
    fun create() {
        val toolSearchToolBm25_20251119 =
            ToolSearchToolBm25_20251119.builder()
                .type(ToolSearchToolBm25_20251119.Type.TOOL_SEARCH_TOOL_BM25_20251119)
                .addAllowedCaller(ToolSearchToolBm25_20251119.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        assertThat(toolSearchToolBm25_20251119.type())
            .isEqualTo(ToolSearchToolBm25_20251119.Type.TOOL_SEARCH_TOOL_BM25_20251119)
        assertThat(toolSearchToolBm25_20251119.allowedCallers().getOrNull())
            .containsExactly(ToolSearchToolBm25_20251119.AllowedCaller.DIRECT)
        assertThat(toolSearchToolBm25_20251119.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(toolSearchToolBm25_20251119.deferLoading()).contains(true)
        assertThat(toolSearchToolBm25_20251119.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolSearchToolBm25_20251119 =
            ToolSearchToolBm25_20251119.builder()
                .type(ToolSearchToolBm25_20251119.Type.TOOL_SEARCH_TOOL_BM25_20251119)
                .addAllowedCaller(ToolSearchToolBm25_20251119.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val roundtrippedToolSearchToolBm25_20251119 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolSearchToolBm25_20251119),
                jacksonTypeRef<ToolSearchToolBm25_20251119>(),
            )

        assertThat(roundtrippedToolSearchToolBm25_20251119).isEqualTo(toolSearchToolBm25_20251119)
    }
}
