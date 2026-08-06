// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAdvisorTest {

    @Test
    fun create() {
        val betaManagedAgentsAdvisor =
            BetaManagedAgentsAdvisor.builder()
                .model("model")
                .type(BetaManagedAgentsAdvisor.Type.ADVISOR)
                .build()

        assertThat(betaManagedAgentsAdvisor.model()).isEqualTo("model")
        assertThat(betaManagedAgentsAdvisor.type()).isEqualTo(BetaManagedAgentsAdvisor.Type.ADVISOR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAdvisor =
            BetaManagedAgentsAdvisor.builder()
                .model("model")
                .type(BetaManagedAgentsAdvisor.Type.ADVISOR)
                .build()

        val roundtrippedBetaManagedAgentsAdvisor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAdvisor),
                jacksonTypeRef<BetaManagedAgentsAdvisor>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAdvisor).isEqualTo(betaManagedAgentsAdvisor)
    }
}
