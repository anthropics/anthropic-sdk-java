package com.anthropic.models.userprofiles

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileProgramsTest {

    @Test
    fun create() {
        val userProfilePrograms =
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
                                    JsonValue.from(mapOf("status" to "active")),
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

        assertThat(userProfilePrograms.cyber())
            .contains(
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
                            .putAdditionalProperty("b", JsonValue.from(mapOf("status" to "active")))
                            .putAdditionalProperty(
                                "c",
                                JsonValue.from(mapOf("status" to "inactive")),
                            )
                            .build()
                    )
                    .build()
            )
        assertThat(userProfilePrograms.efs())
            .contains(
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
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfilePrograms =
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
                                    JsonValue.from(mapOf("status" to "active")),
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

        val roundtrippedUserProfilePrograms =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfilePrograms),
                jacksonTypeRef<UserProfilePrograms>(),
            )

        assertThat(roundtrippedUserProfilePrograms).isEqualTo(userProfilePrograms)
    }
}
