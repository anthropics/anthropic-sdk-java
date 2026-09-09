package com.anthropic.models.beta.userprofiles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUserProfileExternalUserDetailsParamsTest {

    @Test
    fun create() {
        val betaUserProfileExternalUserDetailsParams =
            BetaUserProfileExternalUserDetailsParams.builder()
                .accountStatus(BetaUserProfileExternalUserDetailsParams.AccountStatus.ACTIVE)
                .country("country")
                .emailHash("x")
                .entityType(BetaUserProfileExternalUserDetailsParams.EntityType.INDIVIDUAL)
                .nameHash("x")
                .onboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .referenceId("x")
                .build()

        assertThat(betaUserProfileExternalUserDetailsParams.accountStatus())
            .contains(BetaUserProfileExternalUserDetailsParams.AccountStatus.ACTIVE)
        assertThat(betaUserProfileExternalUserDetailsParams.country()).contains("country")
        assertThat(betaUserProfileExternalUserDetailsParams.emailHash()).contains("x")
        assertThat(betaUserProfileExternalUserDetailsParams.entityType())
            .contains(BetaUserProfileExternalUserDetailsParams.EntityType.INDIVIDUAL)
        assertThat(betaUserProfileExternalUserDetailsParams.nameHash()).contains("x")
        assertThat(betaUserProfileExternalUserDetailsParams.onboardedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaUserProfileExternalUserDetailsParams.referenceId()).contains("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaUserProfileExternalUserDetailsParams =
            BetaUserProfileExternalUserDetailsParams.builder()
                .accountStatus(BetaUserProfileExternalUserDetailsParams.AccountStatus.ACTIVE)
                .country("country")
                .emailHash("x")
                .entityType(BetaUserProfileExternalUserDetailsParams.EntityType.INDIVIDUAL)
                .nameHash("x")
                .onboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .referenceId("x")
                .build()

        val roundtrippedBetaUserProfileExternalUserDetailsParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUserProfileExternalUserDetailsParams),
                jacksonTypeRef<BetaUserProfileExternalUserDetailsParams>(),
            )

        assertThat(roundtrippedBetaUserProfileExternalUserDetailsParams)
            .isEqualTo(betaUserProfileExternalUserDetailsParams)
    }
}
