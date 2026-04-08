// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.vaults.VaultArchiveParams
import com.anthropic.models.beta.vaults.VaultCreateParams
import com.anthropic.models.beta.vaults.VaultDeleteParams
import com.anthropic.models.beta.vaults.VaultRetrieveParams
import com.anthropic.models.beta.vaults.VaultUpdateParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class VaultServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val vaultService = client.beta().vaults()

        val betaManagedAgentsVault =
            vaultService.create(
                VaultCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .displayName("Example vault")
                    .metadata(
                        VaultCreateParams.Metadata.builder()
                            .putAdditionalProperty("environment", JsonValue.from("production"))
                            .build()
                    )
                    .build()
            )

        betaManagedAgentsVault.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val vaultService = client.beta().vaults()

        val betaManagedAgentsVault =
            vaultService.retrieve(
                VaultRetrieveParams.builder()
                    .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsVault.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val vaultService = client.beta().vaults()

        val betaManagedAgentsVault =
            vaultService.update(
                VaultUpdateParams.builder()
                    .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .displayName("Example vault")
                    .metadata(
                        VaultUpdateParams.Metadata.builder()
                            .putAdditionalProperty("environment", JsonValue.from("production"))
                            .build()
                    )
                    .build()
            )

        betaManagedAgentsVault.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val vaultService = client.beta().vaults()

        val page = vaultService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val vaultService = client.beta().vaults()

        val betaManagedAgentsDeletedVault =
            vaultService.delete(
                VaultDeleteParams.builder()
                    .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsDeletedVault.validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val vaultService = client.beta().vaults()

        val betaManagedAgentsVault =
            vaultService.archive(
                VaultArchiveParams.builder()
                    .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsVault.validate()
    }
}
