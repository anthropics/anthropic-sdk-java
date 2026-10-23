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
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class VaultServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val vaultService = client.beta().vaults().withOptions { it.putQueryParam("beta", "true") }

        val betaManagedAgentsVault =
            vaultService.create(
                VaultCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
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
        val vaultService = client.beta().vaults().withOptions { it.putQueryParam("beta", "true") }

        val betaManagedAgentsVault =
            vaultService.retrieve(
                VaultRetrieveParams.builder()
                    .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        betaManagedAgentsVault.validate()
    }

    @Test
    fun update() {
        val vaultService = client.beta().vaults().withOptions { it.putQueryParam("beta", "true") }

        val betaManagedAgentsVault =
            vaultService.update(
                VaultUpdateParams.builder()
                    .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
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

    @Test
    fun list() {
        val vaultService = client.beta().vaults().withOptions { it.putQueryParam("beta", "true") }

        val page = vaultService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val vaultService = client.beta().vaults().withOptions { it.putQueryParam("beta", "true") }

        val betaManagedAgentsDeletedVault =
            vaultService.delete(
                VaultDeleteParams.builder()
                    .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        betaManagedAgentsDeletedVault.validate()
    }

    @Test
    fun archive() {
        val vaultService = client.beta().vaults().withOptions { it.putQueryParam("beta", "true") }

        val betaManagedAgentsVault =
            vaultService.archive(
                VaultArchiveParams.builder()
                    .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        betaManagedAgentsVault.validate()
    }
}
