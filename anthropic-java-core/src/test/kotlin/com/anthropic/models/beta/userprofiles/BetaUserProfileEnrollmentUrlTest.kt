// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.userprofiles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUserProfileEnrollmentUrlTest {

    @Test
    fun create() {
        val betaUserProfileEnrollmentUrl =
            BetaUserProfileEnrollmentUrl.builder()
                .expiresAt(OffsetDateTime.parse("2026-03-15T10:15:00Z"))
                .type(BetaUserProfileEnrollmentUrl.Type.ENROLLMENT_URL)
                .url("https://platform.claude.com/user-profiles/enrollment/M3J0bGJxZ2ppMnptbnB1")
                .build()

        assertThat(betaUserProfileEnrollmentUrl.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:15:00Z"))
        assertThat(betaUserProfileEnrollmentUrl.type())
            .isEqualTo(BetaUserProfileEnrollmentUrl.Type.ENROLLMENT_URL)
        assertThat(betaUserProfileEnrollmentUrl.url())
            .isEqualTo("https://platform.claude.com/user-profiles/enrollment/M3J0bGJxZ2ppMnptbnB1")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaUserProfileEnrollmentUrl =
            BetaUserProfileEnrollmentUrl.builder()
                .expiresAt(OffsetDateTime.parse("2026-03-15T10:15:00Z"))
                .type(BetaUserProfileEnrollmentUrl.Type.ENROLLMENT_URL)
                .url("https://platform.claude.com/user-profiles/enrollment/M3J0bGJxZ2ppMnptbnB1")
                .build()

        val roundtrippedBetaUserProfileEnrollmentUrl =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUserProfileEnrollmentUrl),
                jacksonTypeRef<BetaUserProfileEnrollmentUrl>(),
            )

        assertThat(roundtrippedBetaUserProfileEnrollmentUrl).isEqualTo(betaUserProfileEnrollmentUrl)
    }
}
