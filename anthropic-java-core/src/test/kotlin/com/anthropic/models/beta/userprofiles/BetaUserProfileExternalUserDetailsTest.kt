package com.anthropic.models.beta.userprofiles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUserProfileExternalUserDetailsTest {

    @Test
    fun create() {
        val betaUserProfileExternalUserDetails =
            BetaUserProfileExternalUserDetails.builder()
                .accountStatus(BetaUserProfileExternalUserDetails.AccountStatus.ACTIVE)
                .country("country")
                .emailHash("email_hash")
                .entityType(BetaUserProfileExternalUserDetails.EntityType.INDIVIDUAL)
                .nameHash("name_hash")
                .onboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .referenceId("reference_id")
                .build()

        assertThat(betaUserProfileExternalUserDetails.accountStatus())
            .contains(BetaUserProfileExternalUserDetails.AccountStatus.ACTIVE)
        assertThat(betaUserProfileExternalUserDetails.country()).contains("country")
        assertThat(betaUserProfileExternalUserDetails.emailHash()).contains("email_hash")
        assertThat(betaUserProfileExternalUserDetails.entityType())
            .contains(BetaUserProfileExternalUserDetails.EntityType.INDIVIDUAL)
        assertThat(betaUserProfileExternalUserDetails.nameHash()).contains("name_hash")
        assertThat(betaUserProfileExternalUserDetails.onboardedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaUserProfileExternalUserDetails.referenceId()).contains("reference_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaUserProfileExternalUserDetails =
            BetaUserProfileExternalUserDetails.builder()
                .accountStatus(BetaUserProfileExternalUserDetails.AccountStatus.ACTIVE)
                .country("country")
                .emailHash("email_hash")
                .entityType(BetaUserProfileExternalUserDetails.EntityType.INDIVIDUAL)
                .nameHash("name_hash")
                .onboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .referenceId("reference_id")
                .build()

        val roundtrippedBetaUserProfileExternalUserDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUserProfileExternalUserDetails),
                jacksonTypeRef<BetaUserProfileExternalUserDetails>(),
            )

        assertThat(roundtrippedBetaUserProfileExternalUserDetails)
            .isEqualTo(betaUserProfileExternalUserDetails)
    }
}
