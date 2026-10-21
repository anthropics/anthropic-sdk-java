package com.anthropic.models.userprofiles

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileTest {

    @Test
    fun create() {
        val userProfile =
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
                                .tiers(
                                    UserProfileCyberProgram.Tiers.builder()
                                        .putAdditionalProperty(
                                            "a",
                                            JsonValue.from(mapOf("status" to "inactive")),
                                        )
                                        .putAdditionalProperty(
                                            "b",
                                            JsonValue.from(mapOf("status" to "inactive")),
                                        )
                                        .putAdditionalProperty(
                                            "c",
                                            JsonValue.from(mapOf("status" to "inactive")),
                                        )
                                        .build()
                                )
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

        assertThat(userProfile.id()).isEqualTo("uprof_011CZkZCu8hGbp5mYRQgUmz9")
        assertThat(userProfile.accessType()).isEqualTo(UserProfileAccessType.APPLICATION)
        assertThat(userProfile.createdAt()).isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(userProfile.enforcementActions())
            .containsExactly(
                UserProfileEnforcementAction.ofRestriction(
                    UserProfileEnforcementActionRestriction.builder()
                        .id("enac_011CZkZCu8hGbp5mYRQgUmz9")
                        .action(UserProfileEnforcementActionNextStep.VERIFY)
                        .expiresAt(null)
                        .openedAt(OffsetDateTime.parse("2026-10-02T19:30:00Z"))
                        .reason(UserProfileEnforcementActionReason.FRAUD_OR_ABUSE)
                        .build()
                )
            )
        assertThat(userProfile.enforcementStatus())
            .isEqualTo(UserProfileEnforcementStatus.UNRESTRICTED)
        assertThat(userProfile.externalUserDetails())
            .isEqualTo(
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
        assertThat(userProfile.metadata()).isEqualTo(UserProfile.Metadata.builder().build())
        assertThat(userProfile.name()).contains("Example User")
        assertThat(userProfile.programs())
            .isEqualTo(
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
                            .tiers(
                                UserProfileCyberProgram.Tiers.builder()
                                    .putAdditionalProperty(
                                        "a",
                                        JsonValue.from(mapOf("status" to "inactive")),
                                    )
                                    .putAdditionalProperty(
                                        "b",
                                        JsonValue.from(mapOf("status" to "inactive")),
                                    )
                                    .putAdditionalProperty(
                                        "c",
                                        JsonValue.from(mapOf("status" to "inactive")),
                                    )
                                    .build()
                            )
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
        assertThat(userProfile.updatedAt()).isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfile =
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
                                .tiers(
                                    UserProfileCyberProgram.Tiers.builder()
                                        .putAdditionalProperty(
                                            "a",
                                            JsonValue.from(mapOf("status" to "inactive")),
                                        )
                                        .putAdditionalProperty(
                                            "b",
                                            JsonValue.from(mapOf("status" to "inactive")),
                                        )
                                        .putAdditionalProperty(
                                            "c",
                                            JsonValue.from(mapOf("status" to "inactive")),
                                        )
                                        .build()
                                )
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

        val roundtrippedUserProfile =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfile),
                jacksonTypeRef<UserProfile>(),
            )

        assertThat(roundtrippedUserProfile).isEqualTo(userProfile)
    }
}
