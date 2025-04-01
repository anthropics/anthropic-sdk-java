// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolBash20250124Test {

    @Test
    fun create() {
        val betaToolBash20250124 =
            BetaToolBash20250124.builder()
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        assertThat(betaToolBash20250124.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolBash20250124 =
            BetaToolBash20250124.builder()
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        val roundtrippedBetaToolBash20250124 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolBash20250124),
                jacksonTypeRef<BetaToolBash20250124>(),
            )

        assertThat(roundtrippedBetaToolBash20250124).isEqualTo(betaToolBash20250124)
    }
}
