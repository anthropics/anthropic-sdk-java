package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentArchivedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentArchivedRunError =
            BetaManagedAgentsAgentArchivedRunError.of("message")

        assertThat(betaManagedAgentsAgentArchivedRunError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentArchivedRunError =
            BetaManagedAgentsAgentArchivedRunError.of("message")

        val roundtrippedBetaManagedAgentsAgentArchivedRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentArchivedRunError),
                jacksonTypeRef<BetaManagedAgentsAgentArchivedRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentArchivedRunError)
            .isEqualTo(betaManagedAgentsAgentArchivedRunError)
    }
}
