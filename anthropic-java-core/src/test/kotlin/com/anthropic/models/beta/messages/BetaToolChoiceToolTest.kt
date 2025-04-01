// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolChoiceToolTest {

    @Test
    fun create() {
        val betaToolChoiceTool =
            BetaToolChoiceTool.builder().name("name").disableParallelToolUse(true).build()

        assertThat(betaToolChoiceTool.name()).isEqualTo("name")
        assertThat(betaToolChoiceTool.disableParallelToolUse()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolChoiceTool =
            BetaToolChoiceTool.builder().name("name").disableParallelToolUse(true).build()

        val roundtrippedBetaToolChoiceTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolChoiceTool),
                jacksonTypeRef<BetaToolChoiceTool>(),
            )

        assertThat(roundtrippedBetaToolChoiceTool).isEqualTo(betaToolChoiceTool)
    }
}
