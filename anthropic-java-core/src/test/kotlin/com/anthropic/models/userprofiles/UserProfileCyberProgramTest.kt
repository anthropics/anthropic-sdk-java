package com.anthropic.models.userprofiles

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileCyberProgramTest {

    @Test
    fun create() {
        val userProfileCyberProgram =
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
                        .putAdditionalProperty("a", JsonValue.from(mapOf("status" to "inactive")))
                        .putAdditionalProperty("b", JsonValue.from(mapOf("status" to "active")))
                        .putAdditionalProperty("c", JsonValue.from(mapOf("status" to "inactive")))
                        .build()
                )
                .build()

        assertThat(userProfileCyberProgram.requirements())
            .contains(
                UserProfileCyberRequirements.builder().dataRetention(true).humanReview(true).build()
            )
        assertThat(userProfileCyberProgram.status()).isEqualTo(UserProfileProgramStatus.ACTIVE)
        assertThat(userProfileCyberProgram.tiers())
            .isEqualTo(
                UserProfileCyberProgram.Tiers.builder()
                    .putAdditionalProperty("a", JsonValue.from(mapOf("status" to "inactive")))
                    .putAdditionalProperty("b", JsonValue.from(mapOf("status" to "active")))
                    .putAdditionalProperty("c", JsonValue.from(mapOf("status" to "inactive")))
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileCyberProgram =
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
                        .putAdditionalProperty("a", JsonValue.from(mapOf("status" to "inactive")))
                        .putAdditionalProperty("b", JsonValue.from(mapOf("status" to "active")))
                        .putAdditionalProperty("c", JsonValue.from(mapOf("status" to "inactive")))
                        .build()
                )
                .build()

        val roundtrippedUserProfileCyberProgram =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileCyberProgram),
                jacksonTypeRef<UserProfileCyberProgram>(),
            )

        assertThat(roundtrippedUserProfileCyberProgram).isEqualTo(userProfileCyberProgram)
    }
}
