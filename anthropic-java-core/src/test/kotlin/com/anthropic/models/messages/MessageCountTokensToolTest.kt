// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageCountTokensToolTest {

    @Test
    fun ofTool() {
        val tool =
            Tool.builder().inputSchema(Tool.InputSchema.builder().build()).name("name").build()

        val messageCountTokensTool = MessageCountTokensTool.ofTool(tool)

        assertThat(messageCountTokensTool.tool()).contains(tool)
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
    }

    @Test
    fun ofToolBash20250124() {
        val toolBash20250124 = ToolBash20250124.builder().build()

        val messageCountTokensTool = MessageCountTokensTool.ofToolBash20250124(toolBash20250124)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).contains(toolBash20250124)
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
    }

    @Test
    fun ofToolTextEditor20250124() {
        val toolTextEditor20250124 = ToolTextEditor20250124.builder().build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofToolTextEditor20250124(toolTextEditor20250124)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).contains(toolTextEditor20250124)
    }
}
