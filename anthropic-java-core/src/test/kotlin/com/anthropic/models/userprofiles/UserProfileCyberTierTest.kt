package com.anthropic.models.userprofiles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileCyberTierTest {

    @Test
    fun create() {
        val userProfileCyberTier = UserProfileCyberTier.of(UserProfileCyberTier.Status.ACTIVE)

        assertThat(userProfileCyberTier.status()).isEqualTo(UserProfileCyberTier.Status.ACTIVE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileCyberTier = UserProfileCyberTier.of(UserProfileCyberTier.Status.ACTIVE)

        val roundtrippedUserProfileCyberTier =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileCyberTier),
                jacksonTypeRef<UserProfileCyberTier>(),
            )

        assertThat(roundtrippedUserProfileCyberTier).isEqualTo(userProfileCyberTier)
    }
}
