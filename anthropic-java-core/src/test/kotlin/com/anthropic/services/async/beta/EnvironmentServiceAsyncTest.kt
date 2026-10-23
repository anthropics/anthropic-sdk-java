package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.environments.BetaCloudConfigParams
import com.anthropic.models.beta.environments.BetaLimitedNetworkParams
import com.anthropic.models.beta.environments.BetaPackagesParams
import com.anthropic.models.beta.environments.EnvironmentArchiveParams
import com.anthropic.models.beta.environments.EnvironmentCreateParams
import com.anthropic.models.beta.environments.EnvironmentDeleteParams
import com.anthropic.models.beta.environments.EnvironmentRetrieveParams
import com.anthropic.models.beta.environments.EnvironmentUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class EnvironmentServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val environmentServiceAsync =
            client.beta().environments().withOptions { it.putQueryParam("beta", "true") }

        val betaEnvironmentFuture =
            environmentServiceAsync.create(
                EnvironmentCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .name("python-data-analysis")
                    .config(
                        BetaCloudConfigParams.builder()
                            .networking(
                                BetaLimitedNetworkParams.builder()
                                    .allowMcpServers(true)
                                    .allowPackageManagers(true)
                                    .addAllowedHost("api.example.com")
                                    .build()
                            )
                            .packages(
                                BetaPackagesParams.builder()
                                    .addApt("string")
                                    .addCargo("string")
                                    .addGem("string")
                                    .addGo("string")
                                    .addNpm("string")
                                    .addPip("pandas")
                                    .addPip("numpy")
                                    .type(BetaPackagesParams.Type.PACKAGES)
                                    .build()
                            )
                            .build()
                    )
                    .description("Python environment with data-analysis packages.")
                    .metadata(
                        EnvironmentCreateParams.Metadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .scope(EnvironmentCreateParams.Scope.ORGANIZATION)
                    .build()
            )

        val betaEnvironment = betaEnvironmentFuture.get()
        betaEnvironment.validate()
    }

    @Test
    fun retrieve() {
        val environmentServiceAsync =
            client.beta().environments().withOptions { it.putQueryParam("beta", "true") }

        val betaEnvironmentFuture =
            environmentServiceAsync.retrieve(
                EnvironmentRetrieveParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaEnvironment = betaEnvironmentFuture.get()
        betaEnvironment.validate()
    }

    @Test
    fun update() {
        val environmentServiceAsync =
            client.beta().environments().withOptions { it.putQueryParam("beta", "true") }

        val betaEnvironmentFuture =
            environmentServiceAsync.update(
                EnvironmentUpdateParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .config(
                        BetaCloudConfigParams.builder()
                            .networking(
                                BetaLimitedNetworkParams.builder()
                                    .allowMcpServers(true)
                                    .allowPackageManagers(true)
                                    .addAllowedHost("api.example.com")
                                    .build()
                            )
                            .packages(
                                BetaPackagesParams.builder()
                                    .addApt("string")
                                    .addCargo("string")
                                    .addGem("string")
                                    .addGo("string")
                                    .addNpm("string")
                                    .addPip("pandas")
                                    .addPip("numpy")
                                    .type(BetaPackagesParams.Type.PACKAGES)
                                    .build()
                            )
                            .build()
                    )
                    .description("Python environment with data-analysis packages.")
                    .metadata(
                        EnvironmentUpdateParams.Metadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .name("x")
                    .scope(EnvironmentUpdateParams.Scope.ORGANIZATION)
                    .build()
            )

        val betaEnvironment = betaEnvironmentFuture.get()
        betaEnvironment.validate()
    }

    @Test
    fun list() {
        val environmentServiceAsync =
            client.beta().environments().withOptions { it.putQueryParam("beta", "true") }

        val pageFuture = environmentServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val environmentServiceAsync =
            client.beta().environments().withOptions { it.putQueryParam("beta", "true") }

        val betaEnvironmentDeleteResponseFuture =
            environmentServiceAsync.delete(
                EnvironmentDeleteParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaEnvironmentDeleteResponse = betaEnvironmentDeleteResponseFuture.get()
        betaEnvironmentDeleteResponse.validate()
    }

    @Test
    fun archive() {
        val environmentServiceAsync =
            client.beta().environments().withOptions { it.putQueryParam("beta", "true") }

        val betaEnvironmentFuture =
            environmentServiceAsync.archive(
                EnvironmentArchiveParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaEnvironment = betaEnvironmentFuture.get()
        betaEnvironment.validate()
    }
}
