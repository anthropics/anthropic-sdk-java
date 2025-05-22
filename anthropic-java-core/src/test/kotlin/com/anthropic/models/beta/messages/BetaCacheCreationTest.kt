// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCacheCreationTest {

    @Test
    fun create() {
        val betaCacheCreation =
            BetaCacheCreation.builder()
                .ephemeral1hInputTokens(0L)
                .ephemeral5mInputTokens(0L)
                .build()

        assertThat(betaCacheCreation.ephemeral1hInputTokens()).isEqualTo(0L)
        assertThat(betaCacheCreation.ephemeral5mInputTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCacheCreation =
            BetaCacheCreation.builder()
                .ephemeral1hInputTokens(0L)
                .ephemeral5mInputTokens(0L)
                .build()

        val roundtrippedBetaCacheCreation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCacheCreation),
                jacksonTypeRef<BetaCacheCreation>(),
            )

        assertThat(roundtrippedBetaCacheCreation).isEqualTo(betaCacheCreation)
    }
}
