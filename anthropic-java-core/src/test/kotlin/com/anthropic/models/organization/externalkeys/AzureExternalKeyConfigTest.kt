package com.anthropic.models.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AzureExternalKeyConfigTest {

    @Test
    fun create() {
        val azureExternalKeyConfig =
            AzureExternalKeyConfig.builder()
                .keyName("key_name")
                .tenantId("tenant_id")
                .vaultUri("https://my-vault.vault.azure.net/")
                .clientId("client_id")
                .build()

        assertThat(azureExternalKeyConfig.keyName()).isEqualTo("key_name")
        assertThat(azureExternalKeyConfig.tenantId()).isEqualTo("tenant_id")
        assertThat(azureExternalKeyConfig.vaultUri()).isEqualTo("https://my-vault.vault.azure.net/")
        assertThat(azureExternalKeyConfig.clientId()).contains("client_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val azureExternalKeyConfig =
            AzureExternalKeyConfig.builder()
                .keyName("key_name")
                .tenantId("tenant_id")
                .vaultUri("https://my-vault.vault.azure.net/")
                .clientId("client_id")
                .build()

        val roundtrippedAzureExternalKeyConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(azureExternalKeyConfig),
                jacksonTypeRef<AzureExternalKeyConfig>(),
            )

        assertThat(roundtrippedAzureExternalKeyConfig).isEqualTo(azureExternalKeyConfig)
    }
}
