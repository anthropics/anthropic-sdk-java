package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMemoryStoreArchivedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsMemoryStoreArchivedRunError =
            BetaManagedAgentsMemoryStoreArchivedRunError.of("message")

        assertThat(betaManagedAgentsMemoryStoreArchivedRunError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemoryStoreArchivedRunError =
            BetaManagedAgentsMemoryStoreArchivedRunError.of("message")

        val roundtrippedBetaManagedAgentsMemoryStoreArchivedRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMemoryStoreArchivedRunError),
                jacksonTypeRef<BetaManagedAgentsMemoryStoreArchivedRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMemoryStoreArchivedRunError)
            .isEqualTo(betaManagedAgentsMemoryStoreArchivedRunError)
    }
}
