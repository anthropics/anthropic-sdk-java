package com.anthropic.models.userprofiles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileCyberRequirementsTest {

    @Test
    fun create() {
        val userProfileCyberRequirements =
            UserProfileCyberRequirements.builder().dataRetention(true).humanReview(true).build()

        assertThat(userProfileCyberRequirements.dataRetention()).isEqualTo(true)
        assertThat(userProfileCyberRequirements.humanReview()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileCyberRequirements =
            UserProfileCyberRequirements.builder().dataRetention(true).humanReview(true).build()

        val roundtrippedUserProfileCyberRequirements =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileCyberRequirements),
                jacksonTypeRef<UserProfileCyberRequirements>(),
            )

        assertThat(roundtrippedUserProfileCyberRequirements).isEqualTo(userProfileCyberRequirements)
    }
}
