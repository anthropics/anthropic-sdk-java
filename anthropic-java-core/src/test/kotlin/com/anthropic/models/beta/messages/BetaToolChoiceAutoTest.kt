// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolChoiceAutoTest {

    @Test
    fun create() {
        val betaToolChoiceAuto = BetaToolChoiceAuto.builder().disableParallelToolUse(true).build()

        assertThat(betaToolChoiceAuto.disableParallelToolUse()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolChoiceAuto = BetaToolChoiceAuto.builder().disableParallelToolUse(true).build()

        val roundtrippedBetaToolChoiceAuto =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolChoiceAuto),
                jacksonTypeRef<BetaToolChoiceAuto>(),
            )

        assertThat(roundtrippedBetaToolChoiceAuto).isEqualTo(betaToolChoiceAuto)
    }
}
