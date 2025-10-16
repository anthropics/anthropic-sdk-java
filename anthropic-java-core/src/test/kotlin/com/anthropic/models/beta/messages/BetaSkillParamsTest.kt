// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSkillParamsTest {

    @Test
    fun create() {
        val betaSkillParams =
            BetaSkillParams.builder()
                .skillId("x")
                .type(BetaSkillParams.Type.ANTHROPIC)
                .version("x")
                .build()

        assertThat(betaSkillParams.skillId()).isEqualTo("x")
        assertThat(betaSkillParams.type()).isEqualTo(BetaSkillParams.Type.ANTHROPIC)
        assertThat(betaSkillParams.version()).contains("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSkillParams =
            BetaSkillParams.builder()
                .skillId("x")
                .type(BetaSkillParams.Type.ANTHROPIC)
                .version("x")
                .build()

        val roundtrippedBetaSkillParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSkillParams),
                jacksonTypeRef<BetaSkillParams>(),
            )

        assertThat(roundtrippedBetaSkillParams).isEqualTo(betaSkillParams)
    }
}
