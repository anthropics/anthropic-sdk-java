// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSkillTest {

    @Test
    fun create() {
        val betaSkill =
            BetaSkill.builder()
                .skillId("pdf")
                .type(BetaSkill.Type.ANTHROPIC)
                .version("latest")
                .build()

        assertThat(betaSkill.skillId()).isEqualTo("pdf")
        assertThat(betaSkill.type()).isEqualTo(BetaSkill.Type.ANTHROPIC)
        assertThat(betaSkill.version()).isEqualTo("latest")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSkill =
            BetaSkill.builder()
                .skillId("pdf")
                .type(BetaSkill.Type.ANTHROPIC)
                .version("latest")
                .build()

        val roundtrippedBetaSkill =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSkill),
                jacksonTypeRef<BetaSkill>(),
            )

        assertThat(roundtrippedBetaSkill).isEqualTo(betaSkill)
    }
}
