// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolChoiceNoneTest {

    @Test
    fun create() {
        val toolChoiceNone = ToolChoiceNone.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolChoiceNone = ToolChoiceNone.builder().build()

        val roundtrippedToolChoiceNone =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolChoiceNone),
                jacksonTypeRef<ToolChoiceNone>(),
            )

        assertThat(roundtrippedToolChoiceNone).isEqualTo(toolChoiceNone)
    }
}
