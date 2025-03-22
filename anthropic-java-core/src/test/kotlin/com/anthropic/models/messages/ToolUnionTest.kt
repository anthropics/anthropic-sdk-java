// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolUnionTest {

    @Test
    fun ofTool() {
        val tool =
            Tool.builder().inputSchema(Tool.InputSchema.builder().build()).name("name").build()

        val toolUnion = ToolUnion.ofTool(tool)

        assertThat(toolUnion.tool()).contains(tool)
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.textEditor20250124()).isEmpty
    }

    @Test
    fun ofBash20250124() {
        val bash20250124 = ToolBash20250124.builder().build()

        val toolUnion = ToolUnion.ofBash20250124(bash20250124)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).contains(bash20250124)
        assertThat(toolUnion.textEditor20250124()).isEmpty
    }

    @Test
    fun ofTextEditor20250124() {
        val textEditor20250124 = ToolTextEditor20250124.builder().build()

        val toolUnion = ToolUnion.ofTextEditor20250124(textEditor20250124)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.textEditor20250124()).contains(textEditor20250124)
    }
}
