// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EnvironmentUpdateParamsTest {

    @Test
    fun create() {
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
    }

    @Test
    fun pathParams() {
        val params =
            EnvironmentUpdateParams.builder().environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW").build()

        assertThat(params._pathParam(0)).isEqualTo("env_011CZkZ9X2dpNyB7HsEFoRfW")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
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

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            EnvironmentUpdateParams.builder().environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
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

        val body = params._body()

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
                EnvironmentUpdateParams.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
        assertThat(body.name()).contains("x")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            EnvironmentUpdateParams.builder().environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW").build()

        val body = params._body()
    }
}
