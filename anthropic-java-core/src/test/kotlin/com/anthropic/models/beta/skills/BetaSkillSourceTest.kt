// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSkillSourceTest {

    @Test
    fun create() {
        val betaSkillSource = BetaSkillSource.of(BetaSkillSource.Type.CUSTOM)

        assertThat(betaSkillSource.type()).isEqualTo(BetaSkillSource.Type.CUSTOM)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSkillSource = BetaSkillSource.of(BetaSkillSource.Type.CUSTOM)

        val roundtrippedBetaSkillSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSkillSource),
                jacksonTypeRef<BetaSkillSource>(),
            )

        assertThat(roundtrippedBetaSkillSource).isEqualTo(betaSkillSource)
    }
}
