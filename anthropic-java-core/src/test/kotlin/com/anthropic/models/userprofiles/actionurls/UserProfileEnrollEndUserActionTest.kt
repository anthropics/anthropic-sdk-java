package com.anthropic.models.userprofiles.actionurls

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileEnrollEndUserActionTest {

    @Test
    fun create() {
        val userProfileEnrollEndUserAction = UserProfileEnrollEndUserAction.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileEnrollEndUserAction = UserProfileEnrollEndUserAction.builder().build()

        val roundtrippedUserProfileEnrollEndUserAction =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileEnrollEndUserAction),
                jacksonTypeRef<UserProfileEnrollEndUserAction>(),
            )

        assertThat(roundtrippedUserProfileEnrollEndUserAction)
            .isEqualTo(userProfileEnrollEndUserAction)
    }
}
