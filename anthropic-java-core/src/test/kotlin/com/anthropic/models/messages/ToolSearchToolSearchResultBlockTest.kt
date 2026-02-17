// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolSearchToolSearchResultBlockTest {

    @Test
    fun create() {
        val toolSearchToolSearchResultBlock =
            ToolSearchToolSearchResultBlock.builder()
                .addToolReference(ToolReferenceBlock.builder().toolName("tool_name").build())
                .build()

        assertThat(toolSearchToolSearchResultBlock.toolReferences())
            .containsExactly(ToolReferenceBlock.builder().toolName("tool_name").build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolSearchToolSearchResultBlock =
            ToolSearchToolSearchResultBlock.builder()
                .addToolReference(ToolReferenceBlock.builder().toolName("tool_name").build())
                .build()

        val roundtrippedToolSearchToolSearchResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolSearchToolSearchResultBlock),
                jacksonTypeRef<ToolSearchToolSearchResultBlock>(),
            )

        assertThat(roundtrippedToolSearchToolSearchResultBlock)
            .isEqualTo(toolSearchToolSearchResultBlock)
    }
}
