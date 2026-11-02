package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsCommitCheckoutTest {

    @Test
    fun create() {
        val betaManagedAgentsCommitCheckout = BetaManagedAgentsCommitCheckout.of("92aD336")

        assertThat(betaManagedAgentsCommitCheckout.sha()).isEqualTo("92aD336")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCommitCheckout = BetaManagedAgentsCommitCheckout.of("92aD336")

        val roundtrippedBetaManagedAgentsCommitCheckout =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsCommitCheckout),
                jacksonTypeRef<BetaManagedAgentsCommitCheckout>(),
            )

        assertThat(roundtrippedBetaManagedAgentsCommitCheckout)
            .isEqualTo(betaManagedAgentsCommitCheckout)
    }
}
