package com.anthropic.models.userprofiles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileNotBillableErrorDetailsTest {

    @Test
    fun create() {
        val userProfileNotBillableErrorDetails =
            UserProfileNotBillableErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileNotBillableErrorDetails =
            UserProfileNotBillableErrorDetails.builder().build()

        val roundtrippedUserProfileNotBillableErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileNotBillableErrorDetails),
                jacksonTypeRef<UserProfileNotBillableErrorDetails>(),
            )

        assertThat(roundtrippedUserProfileNotBillableErrorDetails)
            .isEqualTo(userProfileNotBillableErrorDetails)
    }
}
