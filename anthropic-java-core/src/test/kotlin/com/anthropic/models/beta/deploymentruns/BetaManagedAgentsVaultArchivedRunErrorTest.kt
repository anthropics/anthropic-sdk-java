package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsVaultArchivedRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsVaultArchivedRunError =
            BetaManagedAgentsVaultArchivedRunError.of("message")

        assertThat(betaManagedAgentsVaultArchivedRunError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsVaultArchivedRunError =
            BetaManagedAgentsVaultArchivedRunError.of("message")

        val roundtrippedBetaManagedAgentsVaultArchivedRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsVaultArchivedRunError),
                jacksonTypeRef<BetaManagedAgentsVaultArchivedRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsVaultArchivedRunError)
            .isEqualTo(betaManagedAgentsVaultArchivedRunError)
    }
}
