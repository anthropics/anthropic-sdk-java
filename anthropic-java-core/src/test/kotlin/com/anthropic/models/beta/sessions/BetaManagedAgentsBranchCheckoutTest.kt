// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsBranchCheckoutTest {

    @Test
    fun create() {
        val betaManagedAgentsBranchCheckout =
            BetaManagedAgentsBranchCheckout.builder()
                .name("main")
                .type(BetaManagedAgentsBranchCheckout.Type.BRANCH)
                .build()

        assertThat(betaManagedAgentsBranchCheckout.name()).isEqualTo("main")
        assertThat(betaManagedAgentsBranchCheckout.type())
            .isEqualTo(BetaManagedAgentsBranchCheckout.Type.BRANCH)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsBranchCheckout =
            BetaManagedAgentsBranchCheckout.builder()
                .name("main")
                .type(BetaManagedAgentsBranchCheckout.Type.BRANCH)
                .build()

        val roundtrippedBetaManagedAgentsBranchCheckout =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsBranchCheckout),
                jacksonTypeRef<BetaManagedAgentsBranchCheckout>(),
            )

        assertThat(roundtrippedBetaManagedAgentsBranchCheckout)
            .isEqualTo(betaManagedAgentsBranchCheckout)
    }
}
