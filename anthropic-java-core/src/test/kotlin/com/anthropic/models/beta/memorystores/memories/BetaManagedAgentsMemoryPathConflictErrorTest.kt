// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMemoryPathConflictErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsMemoryPathConflictError =
            BetaManagedAgentsMemoryPathConflictError.builder()
                .type(BetaManagedAgentsMemoryPathConflictError.Type.MEMORY_PATH_CONFLICT_ERROR)
                .conflictingMemoryId("conflicting_memory_id")
                .conflictingPath("conflicting_path")
                .message("message")
                .build()

        assertThat(betaManagedAgentsMemoryPathConflictError.type())
            .isEqualTo(BetaManagedAgentsMemoryPathConflictError.Type.MEMORY_PATH_CONFLICT_ERROR)
        assertThat(betaManagedAgentsMemoryPathConflictError.conflictingMemoryId())
            .contains("conflicting_memory_id")
        assertThat(betaManagedAgentsMemoryPathConflictError.conflictingPath())
            .contains("conflicting_path")
        assertThat(betaManagedAgentsMemoryPathConflictError.message()).contains("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemoryPathConflictError =
            BetaManagedAgentsMemoryPathConflictError.builder()
                .type(BetaManagedAgentsMemoryPathConflictError.Type.MEMORY_PATH_CONFLICT_ERROR)
                .conflictingMemoryId("conflicting_memory_id")
                .conflictingPath("conflicting_path")
                .message("message")
                .build()

        val roundtrippedBetaManagedAgentsMemoryPathConflictError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMemoryPathConflictError),
                jacksonTypeRef<BetaManagedAgentsMemoryPathConflictError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMemoryPathConflictError)
            .isEqualTo(betaManagedAgentsMemoryPathConflictError)
    }
}
