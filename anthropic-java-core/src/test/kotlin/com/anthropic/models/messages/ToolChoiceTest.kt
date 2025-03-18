// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolChoiceTest {

    @Test
    fun ofAuto() {
        val auto = ToolChoiceAuto.builder().build()

        val toolChoice = ToolChoice.ofAuto(auto)

        assertThat(toolChoice.auto()).contains(auto)
        assertThat(toolChoice.any()).isEmpty
        assertThat(toolChoice.tool()).isEmpty
        assertThat(toolChoice.none()).isEmpty
    }

    @Test
    fun ofAny() {
        val any = ToolChoiceAny.builder().build()

        val toolChoice = ToolChoice.ofAny(any)

        assertThat(toolChoice.auto()).isEmpty
        assertThat(toolChoice.any()).contains(any)
        assertThat(toolChoice.tool()).isEmpty
        assertThat(toolChoice.none()).isEmpty
    }

    @Test
    fun ofTool() {
        val tool = ToolChoiceTool.builder().name("name").build()

        val toolChoice = ToolChoice.ofTool(tool)

        assertThat(toolChoice.auto()).isEmpty
        assertThat(toolChoice.any()).isEmpty
        assertThat(toolChoice.tool()).contains(tool)
        assertThat(toolChoice.none()).isEmpty
    }

    @Test
    fun ofNone() {
        val none = ToolChoiceNone.builder().build()

        val toolChoice = ToolChoice.ofNone(none)

        assertThat(toolChoice.auto()).isEmpty
        assertThat(toolChoice.any()).isEmpty
        assertThat(toolChoice.tool()).isEmpty
        assertThat(toolChoice.none()).contains(none)
    }
}
