// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentArchivedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentArchivedRunError =
            BetaManagedAgentsAgentArchivedRunError.builder()
                .message("message")
                .type(BetaManagedAgentsAgentArchivedRunError.Type.AGENT_ARCHIVED_ERROR)
                .build()

        assertThat(betaManagedAgentsAgentArchivedRunError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsAgentArchivedRunError.type())
            .isEqualTo(BetaManagedAgentsAgentArchivedRunError.Type.AGENT_ARCHIVED_ERROR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentArchivedRunError =
            BetaManagedAgentsAgentArchivedRunError.builder()
                .message("message")
                .type(BetaManagedAgentsAgentArchivedRunError.Type.AGENT_ARCHIVED_ERROR)
                .build()

        val roundtrippedBetaManagedAgentsAgentArchivedRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentArchivedRunError),
                jacksonTypeRef<BetaManagedAgentsAgentArchivedRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentArchivedRunError)
            .isEqualTo(betaManagedAgentsAgentArchivedRunError)
    }
}
