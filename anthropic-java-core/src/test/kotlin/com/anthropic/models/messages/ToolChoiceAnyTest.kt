// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolChoiceAnyTest {

    @Test
    fun create() {
        val toolChoiceAny = ToolChoiceAny.builder().disableParallelToolUse(true).build()

        assertThat(toolChoiceAny.disableParallelToolUse()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolChoiceAny = ToolChoiceAny.builder().disableParallelToolUse(true).build()

        val roundtrippedToolChoiceAny =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolChoiceAny),
                jacksonTypeRef<ToolChoiceAny>(),
            )

        assertThat(roundtrippedToolChoiceAny).isEqualTo(toolChoiceAny)
    }
}
