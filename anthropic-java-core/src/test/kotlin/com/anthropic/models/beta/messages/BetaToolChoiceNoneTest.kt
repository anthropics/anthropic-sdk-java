// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolChoiceNoneTest {

    @Test
    fun create() {
        val betaToolChoiceNone = BetaToolChoiceNone.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolChoiceNone = BetaToolChoiceNone.builder().build()

        val roundtrippedBetaToolChoiceNone =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolChoiceNone),
                jacksonTypeRef<BetaToolChoiceNone>(),
            )

        assertThat(roundtrippedBetaToolChoiceNone).isEqualTo(betaToolChoiceNone)
    }
}
