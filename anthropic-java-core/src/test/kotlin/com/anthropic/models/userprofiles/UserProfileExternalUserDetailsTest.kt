package com.anthropic.models.userprofiles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileExternalUserDetailsTest {

    @Test
    fun create() {
        val userProfileExternalUserDetails =
            UserProfileExternalUserDetails.builder()
                .accountStatus(UserProfileExternalUserAccountStatus.ACTIVE)
                .country("country")
                .emailHash("email_hash")
                .entityType(UserProfileExternalUserEntityType.INDIVIDUAL)
                .nameHash("name_hash")
                .onboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .referenceId("reference_id")
                .build()

        assertThat(userProfileExternalUserDetails.accountStatus())
            .contains(UserProfileExternalUserAccountStatus.ACTIVE)
        assertThat(userProfileExternalUserDetails.country()).contains("country")
        assertThat(userProfileExternalUserDetails.emailHash()).contains("email_hash")
        assertThat(userProfileExternalUserDetails.entityType())
            .contains(UserProfileExternalUserEntityType.INDIVIDUAL)
        assertThat(userProfileExternalUserDetails.nameHash()).contains("name_hash")
        assertThat(userProfileExternalUserDetails.onboardedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(userProfileExternalUserDetails.referenceId()).contains("reference_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileExternalUserDetails =
            UserProfileExternalUserDetails.builder()
                .accountStatus(UserProfileExternalUserAccountStatus.ACTIVE)
                .country("country")
                .emailHash("email_hash")
                .entityType(UserProfileExternalUserEntityType.INDIVIDUAL)
                .nameHash("name_hash")
                .onboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .referenceId("reference_id")
                .build()

        val roundtrippedUserProfileExternalUserDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileExternalUserDetails),
                jacksonTypeRef<UserProfileExternalUserDetails>(),
            )

        assertThat(roundtrippedUserProfileExternalUserDetails)
            .isEqualTo(userProfileExternalUserDetails)
    }
}
