// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAnthropicSkillTest {

    @Test
    fun create() {
        val betaManagedAgentsAnthropicSkill =
            BetaManagedAgentsAnthropicSkill.builder()
                .skillId("xlsx")
                .type(BetaManagedAgentsAnthropicSkill.Type.ANTHROPIC)
                .version("1")
                .build()

        assertThat(betaManagedAgentsAnthropicSkill.skillId()).isEqualTo("xlsx")
        assertThat(betaManagedAgentsAnthropicSkill.type())
            .isEqualTo(BetaManagedAgentsAnthropicSkill.Type.ANTHROPIC)
        assertThat(betaManagedAgentsAnthropicSkill.version()).isEqualTo("1")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAnthropicSkill =
            BetaManagedAgentsAnthropicSkill.builder()
                .skillId("xlsx")
                .type(BetaManagedAgentsAnthropicSkill.Type.ANTHROPIC)
                .version("1")
                .build()

        val roundtrippedBetaManagedAgentsAnthropicSkill =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAnthropicSkill),
                jacksonTypeRef<BetaManagedAgentsAnthropicSkill>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAnthropicSkill)
            .isEqualTo(betaManagedAgentsAnthropicSkill)
    }
}
