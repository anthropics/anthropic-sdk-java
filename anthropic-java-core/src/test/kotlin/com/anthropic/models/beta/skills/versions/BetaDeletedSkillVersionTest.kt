// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills.versions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDeletedSkillVersionTest {

    @Test
    fun create() {
        val betaDeletedSkillVersion = BetaDeletedSkillVersion.of("id")

        assertThat(betaDeletedSkillVersion.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDeletedSkillVersion = BetaDeletedSkillVersion.of("id")

        val roundtrippedBetaDeletedSkillVersion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDeletedSkillVersion),
                jacksonTypeRef<BetaDeletedSkillVersion>(),
            )

        assertThat(roundtrippedBetaDeletedSkillVersion).isEqualTo(betaDeletedSkillVersion)
    }
}
