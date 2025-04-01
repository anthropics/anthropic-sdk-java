// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMetadataTest {

    @Test
    fun create() {
        val betaMetadata =
            BetaMetadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()

        assertThat(betaMetadata.userId()).contains("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMetadata =
            BetaMetadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()

        val roundtrippedBetaMetadata =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMetadata),
                jacksonTypeRef<BetaMetadata>(),
            )

        assertThat(roundtrippedBetaMetadata).isEqualTo(betaMetadata)
    }
}
