// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaContainerTest {

    @Test
    fun create() {
        val betaContainer =
            BetaContainer.builder()
                .id("id")
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addSkill(
                    BetaSkill.builder()
                        .skillId("x")
                        .type(BetaSkill.Type.ANTHROPIC)
                        .version("x")
                        .build()
                )
                .build()

        assertThat(betaContainer.id()).isEqualTo("id")
        assertThat(betaContainer.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaContainer.skills().getOrNull())
            .containsExactly(
                BetaSkill.builder().skillId("x").type(BetaSkill.Type.ANTHROPIC).version("x").build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaContainer =
            BetaContainer.builder()
                .id("id")
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addSkill(
                    BetaSkill.builder()
                        .skillId("x")
                        .type(BetaSkill.Type.ANTHROPIC)
                        .version("x")
                        .build()
                )
                .build()

        val roundtrippedBetaContainer =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContainer),
                jacksonTypeRef<BetaContainer>(),
            )

        assertThat(roundtrippedBetaContainer).isEqualTo(betaContainer)
    }
}
