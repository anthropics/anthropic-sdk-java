// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsConflictErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsConflictError =
            BetaManagedAgentsConflictError.builder()
                .type(BetaManagedAgentsConflictError.Type.CONFLICT_ERROR)
                .message("message")
                .build()

        assertThat(betaManagedAgentsConflictError.type())
            .isEqualTo(BetaManagedAgentsConflictError.Type.CONFLICT_ERROR)
        assertThat(betaManagedAgentsConflictError.message()).contains("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsConflictError =
            BetaManagedAgentsConflictError.builder()
                .type(BetaManagedAgentsConflictError.Type.CONFLICT_ERROR)
                .message("message")
                .build()

        val roundtrippedBetaManagedAgentsConflictError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsConflictError),
                jacksonTypeRef<BetaManagedAgentsConflictError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsConflictError)
            .isEqualTo(betaManagedAgentsConflictError)
    }
}
