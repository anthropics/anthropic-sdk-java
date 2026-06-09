// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSkillNotFoundRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsSkillNotFoundRunError =
            BetaManagedAgentsSkillNotFoundRunError.builder()
                .message("message")
                .type(BetaManagedAgentsSkillNotFoundRunError.Type.SKILL_NOT_FOUND_ERROR)
                .build()

        assertThat(betaManagedAgentsSkillNotFoundRunError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsSkillNotFoundRunError.type())
            .isEqualTo(BetaManagedAgentsSkillNotFoundRunError.Type.SKILL_NOT_FOUND_ERROR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSkillNotFoundRunError =
            BetaManagedAgentsSkillNotFoundRunError.builder()
                .message("message")
                .type(BetaManagedAgentsSkillNotFoundRunError.Type.SKILL_NOT_FOUND_ERROR)
                .build()

        val roundtrippedBetaManagedAgentsSkillNotFoundRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSkillNotFoundRunError),
                jacksonTypeRef<BetaManagedAgentsSkillNotFoundRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSkillNotFoundRunError)
            .isEqualTo(betaManagedAgentsSkillNotFoundRunError)
    }
}
