package com.anthropic.models.beta.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaServerToolsCapabilityTest {

    @Test
    fun create() {
        val betaServerToolsCapability =
            BetaServerToolsCapability.builder()
                .codeExecution(BetaCapabilitySupport.of(true))
                .supported(true)
                .webSearch(BetaCapabilitySupport.of(true))
                .build()

        assertThat(betaServerToolsCapability.codeExecution())
            .isEqualTo(BetaCapabilitySupport.of(true))
        assertThat(betaServerToolsCapability.supported()).isEqualTo(true)
        assertThat(betaServerToolsCapability.webSearch()).isEqualTo(BetaCapabilitySupport.of(true))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaServerToolsCapability =
            BetaServerToolsCapability.builder()
                .codeExecution(BetaCapabilitySupport.of(true))
                .supported(true)
                .webSearch(BetaCapabilitySupport.of(true))
                .build()

        val roundtrippedBetaServerToolsCapability =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaServerToolsCapability),
                jacksonTypeRef<BetaServerToolsCapability>(),
            )

        assertThat(roundtrippedBetaServerToolsCapability).isEqualTo(betaServerToolsCapability)
    }
}
