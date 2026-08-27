// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDeletedSkillTest {

    @Test
    fun create() {
        val betaDeletedSkill = BetaDeletedSkill.of("skill_01JAbcdefghijklmnopqrstuvw")

        assertThat(betaDeletedSkill.id()).isEqualTo("skill_01JAbcdefghijklmnopqrstuvw")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDeletedSkill = BetaDeletedSkill.of("skill_01JAbcdefghijklmnopqrstuvw")

        val roundtrippedBetaDeletedSkill =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDeletedSkill),
                jacksonTypeRef<BetaDeletedSkill>(),
            )

        assertThat(roundtrippedBetaDeletedSkill).isEqualTo(betaDeletedSkill)
    }
}
