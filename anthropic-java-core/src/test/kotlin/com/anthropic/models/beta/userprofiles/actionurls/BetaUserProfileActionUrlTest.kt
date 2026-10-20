package com.anthropic.models.beta.userprofiles.actionurls

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUserProfileActionUrlTest {

    @Test
    fun create() {
        val betaUserProfileActionUrl =
            BetaUserProfileActionUrl.builder()
                .expiresAt(OffsetDateTime.parse("2026-03-15T10:15:00Z"))
                .url("https://platform.claude.com/user-profiles/enrollment/M3J0bGJxZ2ppMnptbnB1")
                .build()

        assertThat(betaUserProfileActionUrl.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:15:00Z"))
        assertThat(betaUserProfileActionUrl.url())
            .isEqualTo("https://platform.claude.com/user-profiles/enrollment/M3J0bGJxZ2ppMnptbnB1")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaUserProfileActionUrl =
            BetaUserProfileActionUrl.builder()
                .expiresAt(OffsetDateTime.parse("2026-03-15T10:15:00Z"))
                .url("https://platform.claude.com/user-profiles/enrollment/M3J0bGJxZ2ppMnptbnB1")
                .build()

        val roundtrippedBetaUserProfileActionUrl =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUserProfileActionUrl),
                jacksonTypeRef<BetaUserProfileActionUrl>(),
            )

        assertThat(roundtrippedBetaUserProfileActionUrl).isEqualTo(betaUserProfileActionUrl)
    }
}
