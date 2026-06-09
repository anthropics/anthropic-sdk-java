// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsFileNotFoundRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsFileNotFoundRunError =
            BetaManagedAgentsFileNotFoundRunError.builder()
                .message("message")
                .type(BetaManagedAgentsFileNotFoundRunError.Type.FILE_NOT_FOUND_ERROR)
                .build()

        assertThat(betaManagedAgentsFileNotFoundRunError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsFileNotFoundRunError.type())
            .isEqualTo(BetaManagedAgentsFileNotFoundRunError.Type.FILE_NOT_FOUND_ERROR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsFileNotFoundRunError =
            BetaManagedAgentsFileNotFoundRunError.builder()
                .message("message")
                .type(BetaManagedAgentsFileNotFoundRunError.Type.FILE_NOT_FOUND_ERROR)
                .build()

        val roundtrippedBetaManagedAgentsFileNotFoundRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsFileNotFoundRunError),
                jacksonTypeRef<BetaManagedAgentsFileNotFoundRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsFileNotFoundRunError)
            .isEqualTo(betaManagedAgentsFileNotFoundRunError)
    }
}
