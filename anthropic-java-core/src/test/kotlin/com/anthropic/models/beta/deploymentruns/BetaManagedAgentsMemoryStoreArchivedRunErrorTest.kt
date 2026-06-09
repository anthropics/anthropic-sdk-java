// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMemoryStoreArchivedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsMemoryStoreArchivedRunError =
            BetaManagedAgentsMemoryStoreArchivedRunError.builder()
                .message("message")
                .type(BetaManagedAgentsMemoryStoreArchivedRunError.Type.MEMORY_STORE_ARCHIVED_ERROR)
                .build()

        assertThat(betaManagedAgentsMemoryStoreArchivedRunError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsMemoryStoreArchivedRunError.type())
            .isEqualTo(
                BetaManagedAgentsMemoryStoreArchivedRunError.Type.MEMORY_STORE_ARCHIVED_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemoryStoreArchivedRunError =
            BetaManagedAgentsMemoryStoreArchivedRunError.builder()
                .message("message")
                .type(BetaManagedAgentsMemoryStoreArchivedRunError.Type.MEMORY_STORE_ARCHIVED_ERROR)
                .build()

        val roundtrippedBetaManagedAgentsMemoryStoreArchivedRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMemoryStoreArchivedRunError),
                jacksonTypeRef<BetaManagedAgentsMemoryStoreArchivedRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMemoryStoreArchivedRunError)
            .isEqualTo(betaManagedAgentsMemoryStoreArchivedRunError)
    }
}
