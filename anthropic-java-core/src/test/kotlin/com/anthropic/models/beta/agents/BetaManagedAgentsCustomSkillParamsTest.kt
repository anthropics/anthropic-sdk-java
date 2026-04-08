// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsCustomSkillParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsCustomSkillParams =
            BetaManagedAgentsCustomSkillParams.builder()
                .skillId("skill_011CZkZFNu9hAbo3jZPRgTlx")
                .type(BetaManagedAgentsCustomSkillParams.Type.CUSTOM)
                .version("2")
                .build()

        assertThat(betaManagedAgentsCustomSkillParams.skillId())
            .isEqualTo("skill_011CZkZFNu9hAbo3jZPRgTlx")
        assertThat(betaManagedAgentsCustomSkillParams.type())
            .isEqualTo(BetaManagedAgentsCustomSkillParams.Type.CUSTOM)
        assertThat(betaManagedAgentsCustomSkillParams.version()).contains("2")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCustomSkillParams =
            BetaManagedAgentsCustomSkillParams.builder()
                .skillId("skill_011CZkZFNu9hAbo3jZPRgTlx")
                .type(BetaManagedAgentsCustomSkillParams.Type.CUSTOM)
                .version("2")
                .build()

        val roundtrippedBetaManagedAgentsCustomSkillParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsCustomSkillParams),
                jacksonTypeRef<BetaManagedAgentsCustomSkillParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsCustomSkillParams)
            .isEqualTo(betaManagedAgentsCustomSkillParams)
    }
}
