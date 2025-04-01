// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolTextEditor20241022Test {

    @Test
    fun create() {
        val betaToolTextEditor20241022 =
            BetaToolTextEditor20241022.builder()
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        assertThat(betaToolTextEditor20241022.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolTextEditor20241022 =
            BetaToolTextEditor20241022.builder()
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        val roundtrippedBetaToolTextEditor20241022 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolTextEditor20241022),
                jacksonTypeRef<BetaToolTextEditor20241022>(),
            )

        assertThat(roundtrippedBetaToolTextEditor20241022).isEqualTo(betaToolTextEditor20241022)
    }
}
