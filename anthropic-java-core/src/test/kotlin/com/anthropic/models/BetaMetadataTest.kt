// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMetadataTest {

    @Test
    fun createBetaMetadata() {
        val betaMetadata =
            BetaMetadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
        assertThat(betaMetadata).isNotNull
        assertThat(betaMetadata.userId()).contains("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
    }
}
