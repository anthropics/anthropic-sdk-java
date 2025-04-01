// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolTextEditor20250124Test {

    @Test
    fun create() {
        val betaToolTextEditor20250124 =
            BetaToolTextEditor20250124.builder()
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        assertThat(betaToolTextEditor20250124.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolTextEditor20250124 =
            BetaToolTextEditor20250124.builder()
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        val roundtrippedBetaToolTextEditor20250124 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolTextEditor20250124),
                jacksonTypeRef<BetaToolTextEditor20250124>(),
            )

        assertThat(roundtrippedBetaToolTextEditor20250124).isEqualTo(betaToolTextEditor20250124)
    }
}
