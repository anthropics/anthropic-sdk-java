// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEnvironmentArchivedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsEnvironmentArchivedRunError =
            BetaManagedAgentsEnvironmentArchivedRunError.builder()
                .message("message")
                .type(BetaManagedAgentsEnvironmentArchivedRunError.Type.ENVIRONMENT_ARCHIVED_ERROR)
                .build()

        assertThat(betaManagedAgentsEnvironmentArchivedRunError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsEnvironmentArchivedRunError.type())
            .isEqualTo(BetaManagedAgentsEnvironmentArchivedRunError.Type.ENVIRONMENT_ARCHIVED_ERROR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEnvironmentArchivedRunError =
            BetaManagedAgentsEnvironmentArchivedRunError.builder()
                .message("message")
                .type(BetaManagedAgentsEnvironmentArchivedRunError.Type.ENVIRONMENT_ARCHIVED_ERROR)
                .build()

        val roundtrippedBetaManagedAgentsEnvironmentArchivedRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEnvironmentArchivedRunError),
                jacksonTypeRef<BetaManagedAgentsEnvironmentArchivedRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEnvironmentArchivedRunError)
            .isEqualTo(betaManagedAgentsEnvironmentArchivedRunError)
    }
}
