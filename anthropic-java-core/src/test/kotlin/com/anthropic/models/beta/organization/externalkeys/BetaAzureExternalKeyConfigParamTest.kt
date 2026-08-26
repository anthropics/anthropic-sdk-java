// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAzureExternalKeyConfigParamTest {

    @Test
    fun create() {
        val betaAzureExternalKeyConfigParam =
            BetaAzureExternalKeyConfigParam.builder()
                .keyName("key_name")
                .tenantId("tenant_id")
                .vaultUri("https://my-vault.vault.azure.net/")
                .clientId("client_id")
                .build()

        assertThat(betaAzureExternalKeyConfigParam.keyName()).isEqualTo("key_name")
        assertThat(betaAzureExternalKeyConfigParam.tenantId()).isEqualTo("tenant_id")
        assertThat(betaAzureExternalKeyConfigParam.vaultUri())
            .isEqualTo("https://my-vault.vault.azure.net/")
        assertThat(betaAzureExternalKeyConfigParam.clientId()).contains("client_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAzureExternalKeyConfigParam =
            BetaAzureExternalKeyConfigParam.builder()
                .keyName("key_name")
                .tenantId("tenant_id")
                .vaultUri("https://my-vault.vault.azure.net/")
                .clientId("client_id")
                .build()

        val roundtrippedBetaAzureExternalKeyConfigParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAzureExternalKeyConfigParam),
                jacksonTypeRef<BetaAzureExternalKeyConfigParam>(),
            )

        assertThat(roundtrippedBetaAzureExternalKeyConfigParam)
            .isEqualTo(betaAzureExternalKeyConfigParam)
    }
}
