// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEnvironmentNotFoundRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsEnvironmentNotFoundRunError =
            BetaManagedAgentsEnvironmentNotFoundRunError.builder()
                .message("message")
                .type(BetaManagedAgentsEnvironmentNotFoundRunError.Type.ENVIRONMENT_NOT_FOUND_ERROR)
                .build()

        assertThat(betaManagedAgentsEnvironmentNotFoundRunError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsEnvironmentNotFoundRunError.type())
            .isEqualTo(
                BetaManagedAgentsEnvironmentNotFoundRunError.Type.ENVIRONMENT_NOT_FOUND_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEnvironmentNotFoundRunError =
            BetaManagedAgentsEnvironmentNotFoundRunError.builder()
                .message("message")
                .type(BetaManagedAgentsEnvironmentNotFoundRunError.Type.ENVIRONMENT_NOT_FOUND_ERROR)
                .build()

        val roundtrippedBetaManagedAgentsEnvironmentNotFoundRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEnvironmentNotFoundRunError),
                jacksonTypeRef<BetaManagedAgentsEnvironmentNotFoundRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEnvironmentNotFoundRunError)
            .isEqualTo(betaManagedAgentsEnvironmentNotFoundRunError)
    }
}
