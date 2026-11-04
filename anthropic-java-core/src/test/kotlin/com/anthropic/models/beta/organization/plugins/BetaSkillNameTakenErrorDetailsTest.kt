package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSkillNameTakenErrorDetailsTest {

    @Test
    fun create() {
        val betaSkillNameTakenErrorDetails = BetaSkillNameTakenErrorDetails.of("skill_name")

        assertThat(betaSkillNameTakenErrorDetails.skillName()).isEqualTo("skill_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSkillNameTakenErrorDetails = BetaSkillNameTakenErrorDetails.of("skill_name")

        val roundtrippedBetaSkillNameTakenErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSkillNameTakenErrorDetails),
                jacksonTypeRef<BetaSkillNameTakenErrorDetails>(),
            )

        assertThat(roundtrippedBetaSkillNameTakenErrorDetails)
            .isEqualTo(betaSkillNameTakenErrorDetails)
    }
}
