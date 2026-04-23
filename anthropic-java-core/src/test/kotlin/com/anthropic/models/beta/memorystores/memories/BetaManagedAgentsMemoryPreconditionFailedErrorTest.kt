// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMemoryPreconditionFailedErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsMemoryPreconditionFailedError =
            BetaManagedAgentsMemoryPreconditionFailedError.builder()
                .type(
                    BetaManagedAgentsMemoryPreconditionFailedError.Type
                        .MEMORY_PRECONDITION_FAILED_ERROR
                )
                .message("message")
                .build()

        assertThat(betaManagedAgentsMemoryPreconditionFailedError.type())
            .isEqualTo(
                BetaManagedAgentsMemoryPreconditionFailedError.Type.MEMORY_PRECONDITION_FAILED_ERROR
            )
        assertThat(betaManagedAgentsMemoryPreconditionFailedError.message()).contains("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemoryPreconditionFailedError =
            BetaManagedAgentsMemoryPreconditionFailedError.builder()
                .type(
                    BetaManagedAgentsMemoryPreconditionFailedError.Type
                        .MEMORY_PRECONDITION_FAILED_ERROR
                )
                .message("message")
                .build()

        val roundtrippedBetaManagedAgentsMemoryPreconditionFailedError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMemoryPreconditionFailedError),
                jacksonTypeRef<BetaManagedAgentsMemoryPreconditionFailedError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMemoryPreconditionFailedError)
            .isEqualTo(betaManagedAgentsMemoryPreconditionFailedError)
    }
}
