// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.userprofiles

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileListPageResponseTest {

    @Test
    fun create() {
        val userProfileListPageResponse =
            UserProfileListPageResponse.builder()
                .addData(
                    BetaUserProfile.builder()
                        .id("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .metadata(BetaUserProfile.Metadata.builder().build())
                        .trustGrants(
                            BetaUserProfile.TrustGrants.builder()
                                .putAdditionalProperty(
                                    "cyber",
                                    JsonValue.from(mapOf("status" to "active")),
                                )
                                .build()
                        )
                        .type(BetaUserProfile.Type.USER_PROFILE)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .accessType(BetaUserProfile.AccessType.APPLICATION)
                        .externalId("user_12345")
                        .externalUserOnboardedAt(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
                        .name("Example User")
                        .build()
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        assertThat(userProfileListPageResponse.data())
            .containsExactly(
                BetaUserProfile.builder()
                    .id("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .metadata(BetaUserProfile.Metadata.builder().build())
                    .trustGrants(
                        BetaUserProfile.TrustGrants.builder()
                            .putAdditionalProperty(
                                "cyber",
                                JsonValue.from(mapOf("status" to "active")),
                            )
                            .build()
                    )
                    .type(BetaUserProfile.Type.USER_PROFILE)
                    .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .accessType(BetaUserProfile.AccessType.APPLICATION)
                    .externalId("user_12345")
                    .externalUserOnboardedAt(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
                    .name("Example User")
                    .build()
            )
        assertThat(userProfileListPageResponse.nextPage())
            .contains("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileListPageResponse =
            UserProfileListPageResponse.builder()
                .addData(
                    BetaUserProfile.builder()
                        .id("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .metadata(BetaUserProfile.Metadata.builder().build())
                        .trustGrants(
                            BetaUserProfile.TrustGrants.builder()
                                .putAdditionalProperty(
                                    "cyber",
                                    JsonValue.from(mapOf("status" to "active")),
                                )
                                .build()
                        )
                        .type(BetaUserProfile.Type.USER_PROFILE)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .accessType(BetaUserProfile.AccessType.APPLICATION)
                        .externalId("user_12345")
                        .externalUserOnboardedAt(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
                        .name("Example User")
                        .build()
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        val roundtrippedUserProfileListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileListPageResponse),
                jacksonTypeRef<UserProfileListPageResponse>(),
            )

        assertThat(roundtrippedUserProfileListPageResponse).isEqualTo(userProfileListPageResponse)
    }
}
