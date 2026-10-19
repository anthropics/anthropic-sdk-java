package com.anthropic.models.userprofiles.actionurls

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileActionUrlTest {

    @Test
    fun create() {
        val userProfileActionUrl =
            UserProfileActionUrl.builder()
                .expiresAt(OffsetDateTime.parse("2026-03-15T10:15:00Z"))
                .url("https://platform.claude.com/user-profiles/enrollment/M3J0bGJxZ2ppMnptbnB1")
                .build()

        assertThat(userProfileActionUrl.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:15:00Z"))
        assertThat(userProfileActionUrl.url())
            .isEqualTo("https://platform.claude.com/user-profiles/enrollment/M3J0bGJxZ2ppMnptbnB1")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileActionUrl =
            UserProfileActionUrl.builder()
                .expiresAt(OffsetDateTime.parse("2026-03-15T10:15:00Z"))
                .url("https://platform.claude.com/user-profiles/enrollment/M3J0bGJxZ2ppMnptbnB1")
                .build()

        val roundtrippedUserProfileActionUrl =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileActionUrl),
                jacksonTypeRef<UserProfileActionUrl>(),
            )

        assertThat(roundtrippedUserProfileActionUrl).isEqualTo(userProfileActionUrl)
    }
}
