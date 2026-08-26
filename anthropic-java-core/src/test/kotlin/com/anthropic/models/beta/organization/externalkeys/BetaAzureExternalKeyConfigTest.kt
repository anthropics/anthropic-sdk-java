// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAzureExternalKeyConfigTest {

    @Test
    fun create() {
        val betaAzureExternalKeyConfig =
            BetaAzureExternalKeyConfig.builder()
                .keyName("key_name")
                .tenantId("tenant_id")
                .vaultUri("https://my-vault.vault.azure.net/")
                .clientId("client_id")
                .build()

        assertThat(betaAzureExternalKeyConfig.keyName()).isEqualTo("key_name")
        assertThat(betaAzureExternalKeyConfig.tenantId()).isEqualTo("tenant_id")
        assertThat(betaAzureExternalKeyConfig.vaultUri())
            .isEqualTo("https://my-vault.vault.azure.net/")
        assertThat(betaAzureExternalKeyConfig.clientId()).contains("client_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAzureExternalKeyConfig =
            BetaAzureExternalKeyConfig.builder()
                .keyName("key_name")
                .tenantId("tenant_id")
                .vaultUri("https://my-vault.vault.azure.net/")
                .clientId("client_id")
                .build()

        val roundtrippedBetaAzureExternalKeyConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAzureExternalKeyConfig),
                jacksonTypeRef<BetaAzureExternalKeyConfig>(),
            )

        assertThat(roundtrippedBetaAzureExternalKeyConfig).isEqualTo(betaAzureExternalKeyConfig)
    }
}
