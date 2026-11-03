package com.anthropic.models.organization

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileNotFoundErrorDetailsTest {

    @Test
    fun create() {
        val userProfileNotFoundErrorDetails = UserProfileNotFoundErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileNotFoundErrorDetails = UserProfileNotFoundErrorDetails.builder().build()

        val roundtrippedUserProfileNotFoundErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileNotFoundErrorDetails),
                jacksonTypeRef<UserProfileNotFoundErrorDetails>(),
            )

        assertThat(roundtrippedUserProfileNotFoundErrorDetails)
            .isEqualTo(userProfileNotFoundErrorDetails)
    }
}
