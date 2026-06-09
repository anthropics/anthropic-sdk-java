// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsVaultNotFoundRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsVaultNotFoundRunError =
            BetaManagedAgentsVaultNotFoundRunError.builder()
                .message("message")
                .type(BetaManagedAgentsVaultNotFoundRunError.Type.VAULT_NOT_FOUND_ERROR)
                .build()

        assertThat(betaManagedAgentsVaultNotFoundRunError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsVaultNotFoundRunError.type())
            .isEqualTo(BetaManagedAgentsVaultNotFoundRunError.Type.VAULT_NOT_FOUND_ERROR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsVaultNotFoundRunError =
            BetaManagedAgentsVaultNotFoundRunError.builder()
                .message("message")
                .type(BetaManagedAgentsVaultNotFoundRunError.Type.VAULT_NOT_FOUND_ERROR)
                .build()

        val roundtrippedBetaManagedAgentsVaultNotFoundRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsVaultNotFoundRunError),
                jacksonTypeRef<BetaManagedAgentsVaultNotFoundRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsVaultNotFoundRunError)
            .isEqualTo(betaManagedAgentsVaultNotFoundRunError)
    }
}
