// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolChoiceToolTest {

    @Test
    fun create() {
        val toolChoiceTool =
            ToolChoiceTool.builder().name("name").disableParallelToolUse(true).build()

        assertThat(toolChoiceTool.name()).isEqualTo("name")
        assertThat(toolChoiceTool.disableParallelToolUse()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolChoiceTool =
            ToolChoiceTool.builder().name("name").disableParallelToolUse(true).build()

        val roundtrippedToolChoiceTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolChoiceTool),
                jacksonTypeRef<ToolChoiceTool>(),
            )

        assertThat(roundtrippedToolChoiceTool).isEqualTo(toolChoiceTool)
    }
}
