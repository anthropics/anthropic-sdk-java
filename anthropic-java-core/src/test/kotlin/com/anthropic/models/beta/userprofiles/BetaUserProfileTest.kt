// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.userprofiles

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUserProfileTest {

    @Test
    fun create() {
        val betaUserProfile =
            BetaUserProfile.builder()
                .id("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .metadata(BetaUserProfile.Metadata.builder().build())
                .trustGrants(
                    BetaUserProfile.TrustGrants.builder()
                        .putAdditionalProperty("cyber", JsonValue.from(mapOf("status" to "active")))
                        .build()
                )
                .type(BetaUserProfile.Type.USER_PROFILE)
                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .accessType(BetaUserProfile.AccessType.APPLICATION)
                .externalId("user_12345")
                .externalUserOnboardedAt(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
                .name("Example User")
                .relationship(BetaUserProfile.Relationship.EXTERNAL)
                .build()

        assertThat(betaUserProfile.id()).isEqualTo("uprof_011CZkZCu8hGbp5mYRQgUmz9")
        assertThat(betaUserProfile.createdAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(betaUserProfile.metadata()).isEqualTo(BetaUserProfile.Metadata.builder().build())
        assertThat(betaUserProfile.trustGrants())
            .isEqualTo(
                BetaUserProfile.TrustGrants.builder()
                    .putAdditionalProperty("cyber", JsonValue.from(mapOf("status" to "active")))
                    .build()
            )
        assertThat(betaUserProfile.type()).isEqualTo(BetaUserProfile.Type.USER_PROFILE)
        assertThat(betaUserProfile.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(betaUserProfile.accessType()).contains(BetaUserProfile.AccessType.APPLICATION)
        assertThat(betaUserProfile.externalId()).contains("user_12345")
        assertThat(betaUserProfile.externalUserOnboardedAt())
            .contains(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
        assertThat(betaUserProfile.name()).contains("Example User")
        assertThat(betaUserProfile.relationship()).contains(BetaUserProfile.Relationship.EXTERNAL)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaUserProfile =
            BetaUserProfile.builder()
                .id("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .metadata(BetaUserProfile.Metadata.builder().build())
                .trustGrants(
                    BetaUserProfile.TrustGrants.builder()
                        .putAdditionalProperty("cyber", JsonValue.from(mapOf("status" to "active")))
                        .build()
                )
                .type(BetaUserProfile.Type.USER_PROFILE)
                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .accessType(BetaUserProfile.AccessType.APPLICATION)
                .externalId("user_12345")
                .externalUserOnboardedAt(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
                .name("Example User")
                .relationship(BetaUserProfile.Relationship.EXTERNAL)
                .build()

        val roundtrippedBetaUserProfile =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUserProfile),
                jacksonTypeRef<BetaUserProfile>(),
            )

        assertThat(roundtrippedBetaUserProfile).isEqualTo(betaUserProfile)
    }
}
