// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EnvironmentCreateParamsTest {

    @Test
    fun create() {
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
    }

    @Test
    fun headers() {
        val params =
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

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = EnvironmentCreateParams.builder().name("python-data-analysis").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
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

        val body = params._body()

        assertThat(body.name()).isEqualTo("python-data-analysis")
        assertThat(body.config())
            .contains(
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
        assertThat(body.description()).contains("Python environment with data-analysis packages.")
        assertThat(body.metadata())
            .contains(
                EnvironmentCreateParams.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = EnvironmentCreateParams.builder().name("python-data-analysis").build()

        val body = params._body()

        assertThat(body.name()).isEqualTo("python-data-analysis")
    }
}
