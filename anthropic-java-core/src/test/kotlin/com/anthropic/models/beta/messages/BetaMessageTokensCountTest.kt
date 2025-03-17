// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMessageTokensCountTest {

    @Test
    fun createBetaMessageTokensCount() {
        val betaMessageTokensCount = BetaMessageTokensCount.builder().inputTokens(2095L).build()
        assertThat(betaMessageTokensCount).isNotNull
        assertThat(betaMessageTokensCount.inputTokens()).isEqualTo(2095L)
    }
}
