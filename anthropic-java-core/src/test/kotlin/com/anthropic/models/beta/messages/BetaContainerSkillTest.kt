// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaContainerSkillTest {

    @Test
    fun create() {
        val betaContainerSkill =
            BetaContainerSkill.builder()
                .skillId("pdf")
                .type(BetaContainerSkill.Type.ANTHROPIC)
                .version("latest")
                .build()

        assertThat(betaContainerSkill.skillId()).isEqualTo("pdf")
        assertThat(betaContainerSkill.type()).isEqualTo(BetaContainerSkill.Type.ANTHROPIC)
        assertThat(betaContainerSkill.version()).isEqualTo("latest")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaContainerSkill =
            BetaContainerSkill.builder()
                .skillId("pdf")
                .type(BetaContainerSkill.Type.ANTHROPIC)
                .version("latest")
                .build()

        val roundtrippedBetaContainerSkill =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContainerSkill),
                jacksonTypeRef<BetaContainerSkill>(),
            )

        assertThat(roundtrippedBetaContainerSkill).isEqualTo(betaContainerSkill)
    }
}
