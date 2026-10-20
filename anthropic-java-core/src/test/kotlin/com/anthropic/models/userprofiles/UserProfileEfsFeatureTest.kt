package com.anthropic.models.userprofiles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileEfsFeatureTest {

    @Test
    fun create() {
        val userProfileEfsFeature = UserProfileEfsFeature.of(UserProfileEfsFeature.Status.ACTIVE)

        assertThat(userProfileEfsFeature.status()).isEqualTo(UserProfileEfsFeature.Status.ACTIVE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileEfsFeature = UserProfileEfsFeature.of(UserProfileEfsFeature.Status.ACTIVE)

        val roundtrippedUserProfileEfsFeature =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileEfsFeature),
                jacksonTypeRef<UserProfileEfsFeature>(),
            )

        assertThat(roundtrippedUserProfileEfsFeature).isEqualTo(userProfileEfsFeature)
    }
}
