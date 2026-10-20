package com.anthropic.models.userprofiles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileExternalUserDetailsParamsTest {

    @Test
    fun create() {
        val userProfileExternalUserDetailsParams =
            UserProfileExternalUserDetailsParams.builder()
                .accountStatus(UserProfileExternalUserAccountStatus.ACTIVE)
                .country("country")
                .emailHash("x")
                .entityType(UserProfileExternalUserEntityType.INDIVIDUAL)
                .nameHash("x")
                .onboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .referenceId("x")
                .build()

        assertThat(userProfileExternalUserDetailsParams.accountStatus())
            .contains(UserProfileExternalUserAccountStatus.ACTIVE)
        assertThat(userProfileExternalUserDetailsParams.country()).contains("country")
        assertThat(userProfileExternalUserDetailsParams.emailHash()).contains("x")
        assertThat(userProfileExternalUserDetailsParams.entityType())
            .contains(UserProfileExternalUserEntityType.INDIVIDUAL)
        assertThat(userProfileExternalUserDetailsParams.nameHash()).contains("x")
        assertThat(userProfileExternalUserDetailsParams.onboardedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(userProfileExternalUserDetailsParams.referenceId()).contains("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileExternalUserDetailsParams =
            UserProfileExternalUserDetailsParams.builder()
                .accountStatus(UserProfileExternalUserAccountStatus.ACTIVE)
                .country("country")
                .emailHash("x")
                .entityType(UserProfileExternalUserEntityType.INDIVIDUAL)
                .nameHash("x")
                .onboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .referenceId("x")
                .build()

        val roundtrippedUserProfileExternalUserDetailsParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileExternalUserDetailsParams),
                jacksonTypeRef<UserProfileExternalUserDetailsParams>(),
            )

        assertThat(roundtrippedUserProfileExternalUserDetailsParams)
            .isEqualTo(userProfileExternalUserDetailsParams)
    }
}
