// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsCommitCheckoutTest {

    @Test
    fun create() {
        val betaManagedAgentsCommitCheckout =
            BetaManagedAgentsCommitCheckout.builder()
                .sha("xxxxxxx")
                .type(BetaManagedAgentsCommitCheckout.Type.COMMIT)
                .build()

        assertThat(betaManagedAgentsCommitCheckout.sha()).isEqualTo("xxxxxxx")
        assertThat(betaManagedAgentsCommitCheckout.type())
            .isEqualTo(BetaManagedAgentsCommitCheckout.Type.COMMIT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCommitCheckout =
            BetaManagedAgentsCommitCheckout.builder()
                .sha("xxxxxxx")
                .type(BetaManagedAgentsCommitCheckout.Type.COMMIT)
                .build()

        val roundtrippedBetaManagedAgentsCommitCheckout =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsCommitCheckout),
                jacksonTypeRef<BetaManagedAgentsCommitCheckout>(),
            )

        assertThat(roundtrippedBetaManagedAgentsCommitCheckout)
            .isEqualTo(betaManagedAgentsCommitCheckout)
    }
}
