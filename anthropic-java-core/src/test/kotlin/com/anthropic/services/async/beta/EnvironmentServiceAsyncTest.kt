// File generated from our OpenAPI spec by Stainless.

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

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val environmentServiceAsync = client.beta().environments()

        val betaEnvironmentFuture =
            environmentServiceAsync.create(
                EnvironmentCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
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
                    .build()
            )

        val betaEnvironment = betaEnvironmentFuture.get()
        betaEnvironment.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val environmentServiceAsync = client.beta().environments()

        val betaEnvironmentFuture =
            environmentServiceAsync.retrieve(
                EnvironmentRetrieveParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaEnvironment = betaEnvironmentFuture.get()
        betaEnvironment.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val environmentServiceAsync = client.beta().environments()

        val betaEnvironmentFuture =
            environmentServiceAsync.update(
                EnvironmentUpdateParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
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
                    .build()
            )

        val betaEnvironment = betaEnvironmentFuture.get()
        betaEnvironment.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val environmentServiceAsync = client.beta().environments()

        val pageFuture = environmentServiceAsync.list()

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
        val environmentServiceAsync = client.beta().environments()

        val betaEnvironmentDeleteResponseFuture =
            environmentServiceAsync.delete(
                EnvironmentDeleteParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaEnvironmentDeleteResponse = betaEnvironmentDeleteResponseFuture.get()
        betaEnvironmentDeleteResponse.validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val environmentServiceAsync = client.beta().environments()

        val betaEnvironmentFuture =
            environmentServiceAsync.archive(
                EnvironmentArchiveParams.builder()
                    .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaEnvironment = betaEnvironmentFuture.get()
        betaEnvironment.validate()
    }
}
