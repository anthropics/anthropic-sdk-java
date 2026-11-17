package com.anthropic.models.userprofiles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileRequiredErrorDetailsTest {

    @Test
    fun create() {
        val userProfileRequiredErrorDetails = UserProfileRequiredErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileRequiredErrorDetails = UserProfileRequiredErrorDetails.builder().build()

        val roundtrippedUserProfileRequiredErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileRequiredErrorDetails),
                jacksonTypeRef<UserProfileRequiredErrorDetails>(),
            )

        assertThat(roundtrippedUserProfileRequiredErrorDetails)
            .isEqualTo(userProfileRequiredErrorDetails)
    }
}
