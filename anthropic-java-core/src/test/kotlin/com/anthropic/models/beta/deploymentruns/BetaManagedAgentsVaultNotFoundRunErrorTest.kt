package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsVaultNotFoundRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsVaultNotFoundRunError =
            BetaManagedAgentsVaultNotFoundRunError.of("message")

        assertThat(betaManagedAgentsVaultNotFoundRunError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsVaultNotFoundRunError =
            BetaManagedAgentsVaultNotFoundRunError.of("message")

        val roundtrippedBetaManagedAgentsVaultNotFoundRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsVaultNotFoundRunError),
                jacksonTypeRef<BetaManagedAgentsVaultNotFoundRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsVaultNotFoundRunError)
            .isEqualTo(betaManagedAgentsVaultNotFoundRunError)
    }
}
