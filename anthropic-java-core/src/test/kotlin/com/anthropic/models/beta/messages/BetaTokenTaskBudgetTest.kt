// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTokenTaskBudgetTest {

    @Test
    fun create() {
        val betaTokenTaskBudget = BetaTokenTaskBudget.builder().total(1024L).remaining(0L).build()

        assertThat(betaTokenTaskBudget.total()).isEqualTo(1024L)
        assertThat(betaTokenTaskBudget.remaining()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTokenTaskBudget = BetaTokenTaskBudget.builder().total(1024L).remaining(0L).build()

        val roundtrippedBetaTokenTaskBudget =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTokenTaskBudget),
                jacksonTypeRef<BetaTokenTaskBudget>(),
            )

        assertThat(roundtrippedBetaTokenTaskBudget).isEqualTo(betaTokenTaskBudget)
    }
}
