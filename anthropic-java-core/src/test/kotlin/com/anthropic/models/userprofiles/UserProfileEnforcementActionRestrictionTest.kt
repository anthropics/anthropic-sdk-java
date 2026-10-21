package com.anthropic.models.userprofiles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileEnforcementActionRestrictionTest {

    @Test
    fun create() {
        val userProfileEnforcementActionRestriction =
            UserProfileEnforcementActionRestriction.builder()
                .id("enac_011CZkZCu8hGbp5mYRQgUmz9")
                .action(UserProfileEnforcementActionNextStep.VERIFY)
                .expiresAt(null)
                .openedAt(OffsetDateTime.parse("2026-10-02T19:30:00Z"))
                .reason(UserProfileEnforcementActionReason.FRAUD_OR_ABUSE)
                .build()

        assertThat(userProfileEnforcementActionRestriction.id())
            .isEqualTo("enac_011CZkZCu8hGbp5mYRQgUmz9")
        assertThat(userProfileEnforcementActionRestriction.action())
            .contains(UserProfileEnforcementActionNextStep.VERIFY)
        assertThat(userProfileEnforcementActionRestriction.expiresAt()).isEmpty
        assertThat(userProfileEnforcementActionRestriction.openedAt())
            .isEqualTo(OffsetDateTime.parse("2026-10-02T19:30:00Z"))
        assertThat(userProfileEnforcementActionRestriction.reason())
            .isEqualTo(UserProfileEnforcementActionReason.FRAUD_OR_ABUSE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileEnforcementActionRestriction =
            UserProfileEnforcementActionRestriction.builder()
                .id("enac_011CZkZCu8hGbp5mYRQgUmz9")
                .action(UserProfileEnforcementActionNextStep.VERIFY)
                .expiresAt(null)
                .openedAt(OffsetDateTime.parse("2026-10-02T19:30:00Z"))
                .reason(UserProfileEnforcementActionReason.FRAUD_OR_ABUSE)
                .build()

        val roundtrippedUserProfileEnforcementActionRestriction =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileEnforcementActionRestriction),
                jacksonTypeRef<UserProfileEnforcementActionRestriction>(),
            )

        assertThat(roundtrippedUserProfileEnforcementActionRestriction)
            .isEqualTo(userProfileEnforcementActionRestriction)
    }
}
