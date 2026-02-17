// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolSearchToolRegex20251119Test {

    @Test
    fun create() {
        val toolSearchToolRegex20251119 =
            ToolSearchToolRegex20251119.builder()
                .type(ToolSearchToolRegex20251119.Type.TOOL_SEARCH_TOOL_REGEX_20251119)
                .addAllowedCaller(ToolSearchToolRegex20251119.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        assertThat(toolSearchToolRegex20251119.type())
            .isEqualTo(ToolSearchToolRegex20251119.Type.TOOL_SEARCH_TOOL_REGEX_20251119)
        assertThat(toolSearchToolRegex20251119.allowedCallers().getOrNull())
            .containsExactly(ToolSearchToolRegex20251119.AllowedCaller.DIRECT)
        assertThat(toolSearchToolRegex20251119.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(toolSearchToolRegex20251119.deferLoading()).contains(true)
        assertThat(toolSearchToolRegex20251119.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolSearchToolRegex20251119 =
            ToolSearchToolRegex20251119.builder()
                .type(ToolSearchToolRegex20251119.Type.TOOL_SEARCH_TOOL_REGEX_20251119)
                .addAllowedCaller(ToolSearchToolRegex20251119.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val roundtrippedToolSearchToolRegex20251119 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolSearchToolRegex20251119),
                jacksonTypeRef<ToolSearchToolRegex20251119>(),
            )

        assertThat(roundtrippedToolSearchToolRegex20251119).isEqualTo(toolSearchToolRegex20251119)
    }
}
