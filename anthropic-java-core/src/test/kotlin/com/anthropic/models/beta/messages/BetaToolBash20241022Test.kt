// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolBash20241022Test {

    @Test
    fun create() {
        val betaToolBash20241022 =
            BetaToolBash20241022.builder()
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        assertThat(betaToolBash20241022.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolBash20241022 =
            BetaToolBash20241022.builder()
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        val roundtrippedBetaToolBash20241022 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolBash20241022),
                jacksonTypeRef<BetaToolBash20241022>(),
            )

        assertThat(roundtrippedBetaToolBash20241022).isEqualTo(betaToolBash20241022)
    }
}
