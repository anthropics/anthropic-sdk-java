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
                .enforcementActions(listOf())
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
                                .requirements(null)
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
                        .efs(null)
                        .build()
                )
                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .build()

        assertThat(userProfile.id()).isEqualTo("uprof_011CZkZCu8hGbp5mYRQgUmz9")
        assertThat(userProfile.accessType()).isEqualTo(UserProfileAccessType.APPLICATION)
        assertThat(userProfile.createdAt()).isEqualTo(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(userProfile.enforcementActions()).containsExactly()
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
                            .requirements(null)
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
                    .efs(null)
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
                .enforcementActions(listOf())
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
                                .requirements(null)
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
                        .efs(null)
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
