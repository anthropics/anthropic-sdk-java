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
                .addToolReference(ToolReferenceBlock.of("tool_name"))
                .build()

        assertThat(toolSearchToolSearchResultBlock.toolReferences())
            .containsExactly(ToolReferenceBlock.of("tool_name"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolSearchToolSearchResultBlock =
            ToolSearchToolSearchResultBlock.builder()
                .addToolReference(ToolReferenceBlock.of("tool_name"))
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
