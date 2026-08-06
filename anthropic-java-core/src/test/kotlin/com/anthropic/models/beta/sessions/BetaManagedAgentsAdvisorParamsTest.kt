// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAdvisorParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsAdvisorParams =
            BetaManagedAgentsAdvisorParams.builder()
                .model("claude-fable-5")
                .type(BetaManagedAgentsAdvisorParams.Type.ADVISOR)
                .build()

        assertThat(betaManagedAgentsAdvisorParams.model()).isEqualTo("claude-fable-5")
        assertThat(betaManagedAgentsAdvisorParams.type())
            .isEqualTo(BetaManagedAgentsAdvisorParams.Type.ADVISOR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAdvisorParams =
            BetaManagedAgentsAdvisorParams.builder()
                .model("claude-fable-5")
                .type(BetaManagedAgentsAdvisorParams.Type.ADVISOR)
                .build()

        val roundtrippedBetaManagedAgentsAdvisorParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAdvisorParams),
                jacksonTypeRef<BetaManagedAgentsAdvisorParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAdvisorParams)
            .isEqualTo(betaManagedAgentsAdvisorParams)
    }
}
