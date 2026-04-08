// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUnrestrictedNetworkTest {

    @Test
    fun create() {
        val betaUnrestrictedNetwork = BetaUnrestrictedNetwork.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaUnrestrictedNetwork = BetaUnrestrictedNetwork.builder().build()

        val roundtrippedBetaUnrestrictedNetwork =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUnrestrictedNetwork),
                jacksonTypeRef<BetaUnrestrictedNetwork>(),
            )

        assertThat(roundtrippedBetaUnrestrictedNetwork).isEqualTo(betaUnrestrictedNetwork)
    }
}
