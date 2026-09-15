package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCompactionConfigTest {

    @Test
    fun create() {
        val betaCompactionConfig =
            BetaCompactionConfig.builder().instructions("instructions").build()

        assertThat(betaCompactionConfig.instructions()).contains("instructions")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCompactionConfig =
            BetaCompactionConfig.builder().instructions("instructions").build()

        val roundtrippedBetaCompactionConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCompactionConfig),
                jacksonTypeRef<BetaCompactionConfig>(),
            )

        assertThat(roundtrippedBetaCompactionConfig).isEqualTo(betaCompactionConfig)
    }
}
