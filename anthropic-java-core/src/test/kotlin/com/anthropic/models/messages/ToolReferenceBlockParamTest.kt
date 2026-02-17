// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolReferenceBlockParamTest {

    @Test
    fun create() {
        val toolReferenceBlockParam =
            ToolReferenceBlockParam.builder()
                .toolName("tool_name")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        assertThat(toolReferenceBlockParam.toolName()).isEqualTo("tool_name")
        assertThat(toolReferenceBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolReferenceBlockParam =
            ToolReferenceBlockParam.builder()
                .toolName("tool_name")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val roundtrippedToolReferenceBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolReferenceBlockParam),
                jacksonTypeRef<ToolReferenceBlockParam>(),
            )

        assertThat(roundtrippedToolReferenceBlockParam).isEqualTo(toolReferenceBlockParam)
    }
}
