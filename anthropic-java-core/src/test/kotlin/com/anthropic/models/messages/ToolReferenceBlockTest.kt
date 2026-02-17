// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolReferenceBlockTest {

    @Test
    fun create() {
        val toolReferenceBlock = ToolReferenceBlock.builder().toolName("tool_name").build()

        assertThat(toolReferenceBlock.toolName()).isEqualTo("tool_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolReferenceBlock = ToolReferenceBlock.builder().toolName("tool_name").build()

        val roundtrippedToolReferenceBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolReferenceBlock),
                jacksonTypeRef<ToolReferenceBlock>(),
            )

        assertThat(roundtrippedToolReferenceBlock).isEqualTo(toolReferenceBlock)
    }
}
