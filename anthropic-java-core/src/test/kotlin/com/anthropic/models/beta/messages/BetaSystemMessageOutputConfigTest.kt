// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSystemMessageOutputConfigTest {

    @Test
    fun create() {
        val betaSystemMessageOutputConfig =
            BetaSystemMessageOutputConfig.builder()
                .effort(BetaSystemMessageOutputConfig.Effort.LOW)
                .build()

        assertThat(betaSystemMessageOutputConfig.effort())
            .contains(BetaSystemMessageOutputConfig.Effort.LOW)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSystemMessageOutputConfig =
            BetaSystemMessageOutputConfig.builder()
                .effort(BetaSystemMessageOutputConfig.Effort.LOW)
                .build()

        val roundtrippedBetaSystemMessageOutputConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSystemMessageOutputConfig),
                jacksonTypeRef<BetaSystemMessageOutputConfig>(),
            )

        assertThat(roundtrippedBetaSystemMessageOutputConfig)
            .isEqualTo(betaSystemMessageOutputConfig)
    }
}
