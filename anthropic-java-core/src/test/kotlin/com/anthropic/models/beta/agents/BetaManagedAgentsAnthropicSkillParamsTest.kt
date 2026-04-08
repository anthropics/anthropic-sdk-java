// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAnthropicSkillParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsAnthropicSkillParams =
            BetaManagedAgentsAnthropicSkillParams.builder()
                .skillId("xlsx")
                .type(BetaManagedAgentsAnthropicSkillParams.Type.ANTHROPIC)
                .version("1")
                .build()

        assertThat(betaManagedAgentsAnthropicSkillParams.skillId()).isEqualTo("xlsx")
        assertThat(betaManagedAgentsAnthropicSkillParams.type())
            .isEqualTo(BetaManagedAgentsAnthropicSkillParams.Type.ANTHROPIC)
        assertThat(betaManagedAgentsAnthropicSkillParams.version()).contains("1")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAnthropicSkillParams =
            BetaManagedAgentsAnthropicSkillParams.builder()
                .skillId("xlsx")
                .type(BetaManagedAgentsAnthropicSkillParams.Type.ANTHROPIC)
                .version("1")
                .build()

        val roundtrippedBetaManagedAgentsAnthropicSkillParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAnthropicSkillParams),
                jacksonTypeRef<BetaManagedAgentsAnthropicSkillParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAnthropicSkillParams)
            .isEqualTo(betaManagedAgentsAnthropicSkillParams)
    }
}
