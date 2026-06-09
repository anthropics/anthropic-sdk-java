// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWorkspaceArchivedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsWorkspaceArchivedRunError =
            BetaManagedAgentsWorkspaceArchivedRunError.builder()
                .message("message")
                .type(BetaManagedAgentsWorkspaceArchivedRunError.Type.WORKSPACE_ARCHIVED_ERROR)
                .build()

        assertThat(betaManagedAgentsWorkspaceArchivedRunError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsWorkspaceArchivedRunError.type())
            .isEqualTo(BetaManagedAgentsWorkspaceArchivedRunError.Type.WORKSPACE_ARCHIVED_ERROR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWorkspaceArchivedRunError =
            BetaManagedAgentsWorkspaceArchivedRunError.builder()
                .message("message")
                .type(BetaManagedAgentsWorkspaceArchivedRunError.Type.WORKSPACE_ARCHIVED_ERROR)
                .build()

        val roundtrippedBetaManagedAgentsWorkspaceArchivedRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWorkspaceArchivedRunError),
                jacksonTypeRef<BetaManagedAgentsWorkspaceArchivedRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWorkspaceArchivedRunError)
            .isEqualTo(betaManagedAgentsWorkspaceArchivedRunError)
    }
}
