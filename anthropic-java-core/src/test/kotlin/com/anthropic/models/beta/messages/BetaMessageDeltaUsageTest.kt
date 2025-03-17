// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMessageDeltaUsageTest {

    @Test
    fun createBetaMessageDeltaUsage() {
        val betaMessageDeltaUsage = BetaMessageDeltaUsage.builder().outputTokens(503L).build()
        assertThat(betaMessageDeltaUsage).isNotNull
        assertThat(betaMessageDeltaUsage.outputTokens()).isEqualTo(503L)
    }
}
