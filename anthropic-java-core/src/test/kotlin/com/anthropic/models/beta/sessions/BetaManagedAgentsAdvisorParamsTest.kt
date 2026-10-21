package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAdvisorParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsAdvisorParams = BetaManagedAgentsAdvisorParams.of("claude-opus-5-5")

        assertThat(betaManagedAgentsAdvisorParams.model()).isEqualTo("claude-opus-5-5")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAdvisorParams = BetaManagedAgentsAdvisorParams.of("claude-opus-5-5")

        val roundtrippedBetaManagedAgentsAdvisorParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAdvisorParams),
                jacksonTypeRef<BetaManagedAgentsAdvisorParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAdvisorParams)
            .isEqualTo(betaManagedAgentsAdvisorParams)
    }
}
