package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSkillNotFoundRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsSkillNotFoundRunError =
            BetaManagedAgentsSkillNotFoundRunError.of("message")

        assertThat(betaManagedAgentsSkillNotFoundRunError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSkillNotFoundRunError =
            BetaManagedAgentsSkillNotFoundRunError.of("message")

        val roundtrippedBetaManagedAgentsSkillNotFoundRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSkillNotFoundRunError),
                jacksonTypeRef<BetaManagedAgentsSkillNotFoundRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSkillNotFoundRunError)
            .isEqualTo(betaManagedAgentsSkillNotFoundRunError)
    }
}
