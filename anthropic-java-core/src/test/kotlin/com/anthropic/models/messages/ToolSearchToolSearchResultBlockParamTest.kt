// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolSearchToolSearchResultBlockParamTest {

    @Test
    fun create() {
        val toolSearchToolSearchResultBlockParam =
            ToolSearchToolSearchResultBlockParam.builder()
                .addToolReference(
                    ToolReferenceBlockParam.builder()
                        .toolName("tool_name")
                        .cacheControl(
                            CacheControlEphemeral.builder()
                                .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .build()
                )
                .build()

        assertThat(toolSearchToolSearchResultBlockParam.toolReferences())
            .containsExactly(
                ToolReferenceBlockParam.builder()
                    .toolName("tool_name")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolSearchToolSearchResultBlockParam =
            ToolSearchToolSearchResultBlockParam.builder()
                .addToolReference(
                    ToolReferenceBlockParam.builder()
                        .toolName("tool_name")
                        .cacheControl(
                            CacheControlEphemeral.builder()
                                .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .build()
                )
                .build()

        val roundtrippedToolSearchToolSearchResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolSearchToolSearchResultBlockParam),
                jacksonTypeRef<ToolSearchToolSearchResultBlockParam>(),
            )

        assertThat(roundtrippedToolSearchToolSearchResultBlockParam)
            .isEqualTo(toolSearchToolSearchResultBlockParam)
    }
}
