// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSkillParamsTest {

    @Test
    fun create() {
        val betaSkillParams =
            BetaSkillParams.builder()
                .skillId("pdf")
                .type(BetaSkillParams.Type.ANTHROPIC)
                .version("latest")
                .build()

        assertThat(betaSkillParams.skillId()).isEqualTo("pdf")
        assertThat(betaSkillParams.type()).isEqualTo(BetaSkillParams.Type.ANTHROPIC)
        assertThat(betaSkillParams.version()).contains("latest")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSkillParams =
            BetaSkillParams.builder()
                .skillId("pdf")
                .type(BetaSkillParams.Type.ANTHROPIC)
                .version("latest")
                .build()

        val roundtrippedBetaSkillParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSkillParams),
                jacksonTypeRef<BetaSkillParams>(),
            )

        assertThat(roundtrippedBetaSkillParams).isEqualTo(betaSkillParams)
    }
}
