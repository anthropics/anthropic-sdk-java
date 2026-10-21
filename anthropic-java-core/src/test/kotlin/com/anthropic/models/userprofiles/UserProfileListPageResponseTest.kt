package com.anthropic.models.userprofiles

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
                    UserProfile.builder()
                        .id("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                        .accessType(UserProfileAccessType.APPLICATION)
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .addEnforcementAction(
                            UserProfileEnforcementActionRestriction.builder()
                                .id("enac_011CZkZCu8hGbp5mYRQgUmz9")
                                .action(UserProfileEnforcementActionNextStep.VERIFY)
                                .expiresAt(null)
                                .openedAt(OffsetDateTime.parse("2026-10-02T19:30:00Z"))
                                .reason(UserProfileEnforcementActionReason.FRAUD_OR_ABUSE)
                                .build()
                        )
                        .enforcementStatus(UserProfileEnforcementStatus.UNRESTRICTED)
                        .externalUserDetails(
                            UserProfileExternalUserDetails.builder()
                                .accountStatus(UserProfileExternalUserAccountStatus.ACTIVE)
                                .country("US")
                                .emailHash(null)
                                .entityType(UserProfileExternalUserEntityType.BUSINESS)
                                .nameHash(null)
                                .onboardedAt(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
                                .referenceId("user_12345")
                                .build()
                        )
                        .metadata(UserProfile.Metadata.builder().build())
                        .name("Example User")
                        .programs(
                            UserProfilePrograms.builder()
                                .cyber(
                                    UserProfileCyberProgram.builder()
                                        .requirements(
                                            UserProfileCyberRequirements.builder()
                                                .dataRetention(true)
                                                .humanReview(true)
                                                .build()
                                        )
                                        .status(UserProfileProgramStatus.ACTIVE)
                                        .tiers(UserProfileCyberProgram.Tiers.builder().build())
                                        .build()
                                )
                                .efs(
                                    UserProfileEfsProgram.builder()
                                        .features(
                                            UserProfileEfsProgram.Features.builder()
                                                .putAdditionalProperty(
                                                    "data_retention_exemption",
                                                    JsonValue.from(mapOf("status" to "inactive")),
                                                )
                                                .putAdditionalProperty(
                                                    "cmek",
                                                    JsonValue.from(mapOf("status" to "active")),
                                                )
                                                .putAdditionalProperty(
                                                    "storage",
                                                    JsonValue.from(mapOf("status" to "inactive")),
                                                )
                                                .build()
                                        )
                                        .status(UserProfileProgramStatus.ACTIVE)
                                        .build()
                                )
                                .build()
                        )
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .build()
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        assertThat(userProfileListPageResponse.data())
            .containsExactly(
                UserProfile.builder()
                    .id("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .accessType(UserProfileAccessType.APPLICATION)
                    .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .addEnforcementAction(
                        UserProfileEnforcementActionRestriction.builder()
                            .id("enac_011CZkZCu8hGbp5mYRQgUmz9")
                            .action(UserProfileEnforcementActionNextStep.VERIFY)
                            .expiresAt(null)
                            .openedAt(OffsetDateTime.parse("2026-10-02T19:30:00Z"))
                            .reason(UserProfileEnforcementActionReason.FRAUD_OR_ABUSE)
                            .build()
                    )
                    .enforcementStatus(UserProfileEnforcementStatus.UNRESTRICTED)
                    .externalUserDetails(
                        UserProfileExternalUserDetails.builder()
                            .accountStatus(UserProfileExternalUserAccountStatus.ACTIVE)
                            .country("US")
                            .emailHash(null)
                            .entityType(UserProfileExternalUserEntityType.BUSINESS)
                            .nameHash(null)
                            .onboardedAt(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
                            .referenceId("user_12345")
                            .build()
                    )
                    .metadata(UserProfile.Metadata.builder().build())
                    .name("Example User")
                    .programs(
                        UserProfilePrograms.builder()
                            .cyber(
                                UserProfileCyberProgram.builder()
                                    .requirements(
                                        UserProfileCyberRequirements.builder()
                                            .dataRetention(true)
                                            .humanReview(true)
                                            .build()
                                    )
                                    .status(UserProfileProgramStatus.ACTIVE)
                                    .tiers(UserProfileCyberProgram.Tiers.builder().build())
                                    .build()
                            )
                            .efs(
                                UserProfileEfsProgram.builder()
                                    .features(
                                        UserProfileEfsProgram.Features.builder()
                                            .putAdditionalProperty(
                                                "data_retention_exemption",
                                                JsonValue.from(mapOf("status" to "inactive")),
                                            )
                                            .putAdditionalProperty(
                                                "cmek",
                                                JsonValue.from(mapOf("status" to "active")),
                                            )
                                            .putAdditionalProperty(
                                                "storage",
                                                JsonValue.from(mapOf("status" to "inactive")),
                                            )
                                            .build()
                                    )
                                    .status(UserProfileProgramStatus.ACTIVE)
                                    .build()
                            )
                            .build()
                    )
                    .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
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
                    UserProfile.builder()
                        .id("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                        .accessType(UserProfileAccessType.APPLICATION)
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .addEnforcementAction(
                            UserProfileEnforcementActionRestriction.builder()
                                .id("enac_011CZkZCu8hGbp5mYRQgUmz9")
                                .action(UserProfileEnforcementActionNextStep.VERIFY)
                                .expiresAt(null)
                                .openedAt(OffsetDateTime.parse("2026-10-02T19:30:00Z"))
                                .reason(UserProfileEnforcementActionReason.FRAUD_OR_ABUSE)
                                .build()
                        )
                        .enforcementStatus(UserProfileEnforcementStatus.UNRESTRICTED)
                        .externalUserDetails(
                            UserProfileExternalUserDetails.builder()
                                .accountStatus(UserProfileExternalUserAccountStatus.ACTIVE)
                                .country("US")
                                .emailHash(null)
                                .entityType(UserProfileExternalUserEntityType.BUSINESS)
                                .nameHash(null)
                                .onboardedAt(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
                                .referenceId("user_12345")
                                .build()
                        )
                        .metadata(UserProfile.Metadata.builder().build())
                        .name("Example User")
                        .programs(
                            UserProfilePrograms.builder()
                                .cyber(
                                    UserProfileCyberProgram.builder()
                                        .requirements(
                                            UserProfileCyberRequirements.builder()
                                                .dataRetention(true)
                                                .humanReview(true)
                                                .build()
                                        )
                                        .status(UserProfileProgramStatus.ACTIVE)
                                        .tiers(UserProfileCyberProgram.Tiers.builder().build())
                                        .build()
                                )
                                .efs(
                                    UserProfileEfsProgram.builder()
                                        .features(
                                            UserProfileEfsProgram.Features.builder()
                                                .putAdditionalProperty(
                                                    "data_retention_exemption",
                                                    JsonValue.from(mapOf("status" to "inactive")),
                                                )
                                                .putAdditionalProperty(
                                                    "cmek",
                                                    JsonValue.from(mapOf("status" to "active")),
                                                )
                                                .putAdditionalProperty(
                                                    "storage",
                                                    JsonValue.from(mapOf("status" to "inactive")),
                                                )
                                                .build()
                                        )
                                        .status(UserProfileProgramStatus.ACTIVE)
                                        .build()
                                )
                                .build()
                        )
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
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
