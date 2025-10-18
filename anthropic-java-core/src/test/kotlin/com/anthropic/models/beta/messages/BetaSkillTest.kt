// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSkillTest {

    @Test
    fun create() {
        val betaSkill =
            BetaSkill.builder().skillId("x").type(BetaSkill.Type.ANTHROPIC).version("x").build()

        assertThat(betaSkill.skillId()).isEqualTo("x")
        assertThat(betaSkill.type()).isEqualTo(BetaSkill.Type.ANTHROPIC)
        assertThat(betaSkill.version()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSkill =
            BetaSkill.builder().skillId("x").type(BetaSkill.Type.ANTHROPIC).version("x").build()

        val roundtrippedBetaSkill =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSkill),
                jacksonTypeRef<BetaSkill>(),
            )

        assertThat(roundtrippedBetaSkill).isEqualTo(betaSkill)
    }
}
