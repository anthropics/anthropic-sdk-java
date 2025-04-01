// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolChoiceAnyTest {

    @Test
    fun create() {
        val betaToolChoiceAny = BetaToolChoiceAny.builder().disableParallelToolUse(true).build()

        assertThat(betaToolChoiceAny.disableParallelToolUse()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolChoiceAny = BetaToolChoiceAny.builder().disableParallelToolUse(true).build()

        val roundtrippedBetaToolChoiceAny =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolChoiceAny),
                jacksonTypeRef<BetaToolChoiceAny>(),
            )

        assertThat(roundtrippedBetaToolChoiceAny).isEqualTo(betaToolChoiceAny)
    }
}
