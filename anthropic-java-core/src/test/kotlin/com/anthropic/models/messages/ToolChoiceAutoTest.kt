// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolChoiceAutoTest {

    @Test
    fun create() {
        val toolChoiceAuto = ToolChoiceAuto.builder().disableParallelToolUse(true).build()

        assertThat(toolChoiceAuto.disableParallelToolUse()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolChoiceAuto = ToolChoiceAuto.builder().disableParallelToolUse(true).build()

        val roundtrippedToolChoiceAuto =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolChoiceAuto),
                jacksonTypeRef<ToolChoiceAuto>(),
            )

        assertThat(roundtrippedToolChoiceAuto).isEqualTo(toolChoiceAuto)
    }
}
