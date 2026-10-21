package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWorkspaceArchivedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsWorkspaceArchivedRunError =
            BetaManagedAgentsWorkspaceArchivedRunError.of("message")

        assertThat(betaManagedAgentsWorkspaceArchivedRunError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkspaceArchivedRunError =
            BetaManagedAgentsWorkspaceArchivedRunError.of("message")

        val roundtrippedBetaManagedAgentsWorkspaceArchivedRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkspaceArchivedRunError),
                jacksonTypeRef<BetaManagedAgentsWorkspaceArchivedRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkspaceArchivedRunError)
            .isEqualTo(betaManagedAgentsWorkspaceArchivedRunError)
    }
}
