package com.anthropic.models.beta.userprofiles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDeletedUserProfileTest {

    @Test
    fun create() {
        val betaDeletedUserProfile = BetaDeletedUserProfile.of("uprof_011CZkZCu8hGbp5mYRQgUmz9")

        assertThat(betaDeletedUserProfile.id()).isEqualTo("uprof_011CZkZCu8hGbp5mYRQgUmz9")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDeletedUserProfile = BetaDeletedUserProfile.of("uprof_011CZkZCu8hGbp5mYRQgUmz9")

        val roundtrippedBetaDeletedUserProfile =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDeletedUserProfile),
                jacksonTypeRef<BetaDeletedUserProfile>(),
            )

        assertThat(roundtrippedBetaDeletedUserProfile).isEqualTo(betaDeletedUserProfile)
    }
}
