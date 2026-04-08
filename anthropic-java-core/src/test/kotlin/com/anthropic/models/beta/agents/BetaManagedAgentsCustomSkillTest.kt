// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsCustomSkillTest {

    @Test
    fun create() {
        val betaManagedAgentsCustomSkill =
            BetaManagedAgentsCustomSkill.builder()
                .skillId("skill_011CZkZFNu9hAbo3jZPRgTlx")
                .type(BetaManagedAgentsCustomSkill.Type.CUSTOM)
                .version("2")
                .build()

        assertThat(betaManagedAgentsCustomSkill.skillId())
            .isEqualTo("skill_011CZkZFNu9hAbo3jZPRgTlx")
        assertThat(betaManagedAgentsCustomSkill.type())
            .isEqualTo(BetaManagedAgentsCustomSkill.Type.CUSTOM)
        assertThat(betaManagedAgentsCustomSkill.version()).isEqualTo("2")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCustomSkill =
            BetaManagedAgentsCustomSkill.builder()
                .skillId("skill_011CZkZFNu9hAbo3jZPRgTlx")
                .type(BetaManagedAgentsCustomSkill.Type.CUSTOM)
                .version("2")
                .build()

        val roundtrippedBetaManagedAgentsCustomSkill =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsCustomSkill),
                jacksonTypeRef<BetaManagedAgentsCustomSkill>(),
            )

        assertThat(roundtrippedBetaManagedAgentsCustomSkill).isEqualTo(betaManagedAgentsCustomSkill)
    }
}
