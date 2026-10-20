package com.anthropic.models.userprofiles

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileEfsProgramTest {

    @Test
    fun create() {
        val userProfileEfsProgram =
            UserProfileEfsProgram.builder()
                .features(
                    UserProfileEfsProgram.Features.builder()
                        .putAdditionalProperty(
                            "data_retention_exemption",
                            JsonValue.from(mapOf("status" to "inactive")),
                        )
                        .putAdditionalProperty("cmek", JsonValue.from(mapOf("status" to "active")))
                        .putAdditionalProperty(
                            "storage",
                            JsonValue.from(mapOf("status" to "inactive")),
                        )
                        .build()
                )
                .status(UserProfileProgramStatus.ACTIVE)
                .build()

        assertThat(userProfileEfsProgram.features())
            .isEqualTo(
                UserProfileEfsProgram.Features.builder()
                    .putAdditionalProperty(
                        "data_retention_exemption",
                        JsonValue.from(mapOf("status" to "inactive")),
                    )
                    .putAdditionalProperty("cmek", JsonValue.from(mapOf("status" to "active")))
                    .putAdditionalProperty("storage", JsonValue.from(mapOf("status" to "inactive")))
                    .build()
            )
        assertThat(userProfileEfsProgram.status()).isEqualTo(UserProfileProgramStatus.ACTIVE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileEfsProgram =
            UserProfileEfsProgram.builder()
                .features(
                    UserProfileEfsProgram.Features.builder()
                        .putAdditionalProperty(
                            "data_retention_exemption",
                            JsonValue.from(mapOf("status" to "inactive")),
                        )
                        .putAdditionalProperty("cmek", JsonValue.from(mapOf("status" to "active")))
                        .putAdditionalProperty(
                            "storage",
                            JsonValue.from(mapOf("status" to "inactive")),
                        )
                        .build()
                )
                .status(UserProfileProgramStatus.ACTIVE)
                .build()

        val roundtrippedUserProfileEfsProgram =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileEfsProgram),
                jacksonTypeRef<UserProfileEfsProgram>(),
            )

        assertThat(roundtrippedUserProfileEfsProgram).isEqualTo(userProfileEfsProgram)
    }
}
