// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import kotlinx.kmp.util.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCapabilitySupportTest {

    @Test
    fun create() {
        val betaCapabilitySupport = BetaCapabilitySupport.builder().supported(true).build()

        assertThat(betaCapabilitySupport.supported()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCapabilitySupport = BetaCapabilitySupport.builder().supported(true).build()

        val roundtrippedBetaCapabilitySupport =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCapabilitySupport),
                jacksonTypeRef<BetaCapabilitySupport>(),
            )

        assertThat(roundtrippedBetaCapabilitySupport).isEqualTo(betaCapabilitySupport)
    }
}
