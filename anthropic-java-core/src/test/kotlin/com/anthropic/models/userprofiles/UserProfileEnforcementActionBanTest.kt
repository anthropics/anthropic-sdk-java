package com.anthropic.models.userprofiles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileEnforcementActionBanTest {

    @Test
    fun create() {
        val userProfileEnforcementActionBan =
            UserProfileEnforcementActionBan.builder()
                .id("enac_011CZkZCu8hGbp5mYRQgUmz9")
                .action(null)
                .expiresAt(null)
                .openedAt(OffsetDateTime.parse("2026-10-02T19:30:00Z"))
                .reason(UserProfileEnforcementActionReason.USAGE_POLICY_VIOLATION)
                .build()

        assertThat(userProfileEnforcementActionBan.id()).isEqualTo("enac_011CZkZCu8hGbp5mYRQgUmz9")
        assertThat(userProfileEnforcementActionBan.action()).isEmpty
        assertThat(userProfileEnforcementActionBan.expiresAt()).isEmpty
        assertThat(userProfileEnforcementActionBan.openedAt())
            .isEqualTo(OffsetDateTime.parse("2026-10-02T19:30:00Z"))
        assertThat(userProfileEnforcementActionBan.reason())
            .isEqualTo(UserProfileEnforcementActionReason.USAGE_POLICY_VIOLATION)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileEnforcementActionBan =
            UserProfileEnforcementActionBan.builder()
                .id("enac_011CZkZCu8hGbp5mYRQgUmz9")
                .action(null)
                .expiresAt(null)
                .openedAt(OffsetDateTime.parse("2026-10-02T19:30:00Z"))
                .reason(UserProfileEnforcementActionReason.USAGE_POLICY_VIOLATION)
                .build()

        val roundtrippedUserProfileEnforcementActionBan =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileEnforcementActionBan),
                jacksonTypeRef<UserProfileEnforcementActionBan>(),
            )

        assertThat(roundtrippedUserProfileEnforcementActionBan)
            .isEqualTo(userProfileEnforcementActionBan)
    }
}
