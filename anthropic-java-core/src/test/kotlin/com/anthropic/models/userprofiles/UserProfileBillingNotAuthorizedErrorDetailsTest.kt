package com.anthropic.models.userprofiles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileBillingNotAuthorizedErrorDetailsTest {

    @Test
    fun create() {
        val userProfileBillingNotAuthorizedErrorDetails =
            UserProfileBillingNotAuthorizedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileBillingNotAuthorizedErrorDetails =
            UserProfileBillingNotAuthorizedErrorDetails.builder().build()

        val roundtrippedUserProfileBillingNotAuthorizedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileBillingNotAuthorizedErrorDetails),
                jacksonTypeRef<UserProfileBillingNotAuthorizedErrorDetails>(),
            )

        assertThat(roundtrippedUserProfileBillingNotAuthorizedErrorDetails)
            .isEqualTo(userProfileBillingNotAuthorizedErrorDetails)
    }
}
