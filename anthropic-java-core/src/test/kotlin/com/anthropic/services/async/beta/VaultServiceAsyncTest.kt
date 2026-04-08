// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
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
internal class VaultServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val vaultServiceAsync = client.beta().vaults()

        val betaManagedAgentsVaultFuture =
            vaultServiceAsync.create(
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

        val betaManagedAgentsVault = betaManagedAgentsVaultFuture.get()
        betaManagedAgentsVault.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val vaultServiceAsync = client.beta().vaults()

        val betaManagedAgentsVaultFuture =
            vaultServiceAsync.retrieve(
                VaultRetrieveParams.builder()
                    .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsVault = betaManagedAgentsVaultFuture.get()
        betaManagedAgentsVault.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val vaultServiceAsync = client.beta().vaults()

        val betaManagedAgentsVaultFuture =
            vaultServiceAsync.update(
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

        val betaManagedAgentsVault = betaManagedAgentsVaultFuture.get()
        betaManagedAgentsVault.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val vaultServiceAsync = client.beta().vaults()

        val pageFuture = vaultServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val vaultServiceAsync = client.beta().vaults()

        val betaManagedAgentsDeletedVaultFuture =
            vaultServiceAsync.delete(
                VaultDeleteParams.builder()
                    .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsDeletedVault = betaManagedAgentsDeletedVaultFuture.get()
        betaManagedAgentsDeletedVault.validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val vaultServiceAsync = client.beta().vaults()

        val betaManagedAgentsVaultFuture =
            vaultServiceAsync.archive(
                VaultArchiveParams.builder()
                    .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsVault = betaManagedAgentsVaultFuture.get()
        betaManagedAgentsVault.validate()
    }
}
