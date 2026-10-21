package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsBranchCheckoutTest {

    @Test
    fun create() {
        val betaManagedAgentsBranchCheckout = BetaManagedAgentsBranchCheckout.of("main")

        assertThat(betaManagedAgentsBranchCheckout.name()).isEqualTo("main")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsBranchCheckout = BetaManagedAgentsBranchCheckout.of("main")

        val roundtrippedBetaManagedAgentsBranchCheckout =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsBranchCheckout),
                jacksonTypeRef<BetaManagedAgentsBranchCheckout>(),
            )

        assertThat(roundtrippedBetaManagedAgentsBranchCheckout)
            .isEqualTo(betaManagedAgentsBranchCheckout)
    }
}
