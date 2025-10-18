// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaContainerParamsTest {

    @Test
    fun create() {
        val betaContainerParams =
            BetaContainerParams.builder()
                .id("id")
                .addSkill(
                    BetaSkillParams.builder()
                        .skillId("x")
                        .type(BetaSkillParams.Type.ANTHROPIC)
                        .version("x")
                        .build()
                )
                .build()

        assertThat(betaContainerParams.id()).contains("id")
        assertThat(betaContainerParams.skills().getOrNull())
            .containsExactly(
                BetaSkillParams.builder()
                    .skillId("x")
                    .type(BetaSkillParams.Type.ANTHROPIC)
                    .version("x")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaContainerParams =
            BetaContainerParams.builder()
                .id("id")
                .addSkill(
                    BetaSkillParams.builder()
                        .skillId("x")
                        .type(BetaSkillParams.Type.ANTHROPIC)
                        .version("x")
                        .build()
                )
                .build()

        val roundtrippedBetaContainerParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContainerParams),
                jacksonTypeRef<BetaContainerParams>(),
            )

        assertThat(roundtrippedBetaContainerParams).isEqualTo(betaContainerParams)
    }
}
