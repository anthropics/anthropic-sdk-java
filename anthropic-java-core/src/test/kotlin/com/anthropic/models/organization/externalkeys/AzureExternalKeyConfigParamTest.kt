package com.anthropic.models.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AzureExternalKeyConfigParamTest {

    @Test
    fun create() {
        val azureExternalKeyConfigParam =
            AzureExternalKeyConfigParam.builder()
                .keyName("key_name")
                .tenantId("tenant_id")
                .vaultUri("https://my-vault.vault.azure.net/")
                .clientId("client_id")
                .build()

        assertThat(azureExternalKeyConfigParam.keyName()).isEqualTo("key_name")
        assertThat(azureExternalKeyConfigParam.tenantId()).isEqualTo("tenant_id")
        assertThat(azureExternalKeyConfigParam.vaultUri())
            .isEqualTo("https://my-vault.vault.azure.net/")
        assertThat(azureExternalKeyConfigParam.clientId()).contains("client_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val azureExternalKeyConfigParam =
            AzureExternalKeyConfigParam.builder()
                .keyName("key_name")
                .tenantId("tenant_id")
                .vaultUri("https://my-vault.vault.azure.net/")
                .clientId("client_id")
                .build()

        val roundtrippedAzureExternalKeyConfigParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(azureExternalKeyConfigParam),
                jacksonTypeRef<AzureExternalKeyConfigParam>(),
            )

        assertThat(roundtrippedAzureExternalKeyConfigParam).isEqualTo(azureExternalKeyConfigParam)
    }
}
