// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EnvironmentListPageResponseTest {

    @Test
    fun create() {
        val environmentListPageResponse =
            EnvironmentListPageResponse.builder()
                .addData(
                    BetaEnvironment.builder()
                        .id("env_011CZkZ9X2dpNyB7HsEFoRfW")
                        .archivedAt(null)
                        .config(
                            BetaCloudConfig.builder()
                                .networking(
                                    BetaLimitedNetwork.builder()
                                        .allowMcpServers(false)
                                        .allowPackageManagers(true)
                                        .addAllowedHost("api.example.com")
                                        .build()
                                )
                                .packages(
                                    BetaPackages.builder()
                                        .addApt("string")
                                        .addCargo("string")
                                        .addGem("string")
                                        .addGo("string")
                                        .addNpm("string")
                                        .addPip("pandas")
                                        .addPip("numpy")
                                        .type(BetaPackages.Type.PACKAGES)
                                        .build()
                                )
                                .build()
                        )
                        .createdAt("2026-03-15T10:00:00Z")
                        .description("Python environment with data-analysis packages.")
                        .metadata(BetaEnvironment.Metadata.builder().build())
                        .name("python-data-analysis")
                        .updatedAt("2026-03-15T10:00:00Z")
                        .build()
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        assertThat(environmentListPageResponse.data())
            .containsExactly(
                BetaEnvironment.builder()
                    .id("env_011CZkZ9X2dpNyB7HsEFoRfW")
                    .archivedAt(null)
                    .config(
                        BetaCloudConfig.builder()
                            .networking(
                                BetaLimitedNetwork.builder()
                                    .allowMcpServers(false)
                                    .allowPackageManagers(true)
                                    .addAllowedHost("api.example.com")
                                    .build()
                            )
                            .packages(
                                BetaPackages.builder()
                                    .addApt("string")
                                    .addCargo("string")
                                    .addGem("string")
                                    .addGo("string")
                                    .addNpm("string")
                                    .addPip("pandas")
                                    .addPip("numpy")
                                    .type(BetaPackages.Type.PACKAGES)
                                    .build()
                            )
                            .build()
                    )
                    .createdAt("2026-03-15T10:00:00Z")
                    .description("Python environment with data-analysis packages.")
                    .metadata(BetaEnvironment.Metadata.builder().build())
                    .name("python-data-analysis")
                    .updatedAt("2026-03-15T10:00:00Z")
                    .build()
            )
        assertThat(environmentListPageResponse.nextPage())
            .contains("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val environmentListPageResponse =
            EnvironmentListPageResponse.builder()
                .addData(
                    BetaEnvironment.builder()
                        .id("env_011CZkZ9X2dpNyB7HsEFoRfW")
                        .archivedAt(null)
                        .config(
                            BetaCloudConfig.builder()
                                .networking(
                                    BetaLimitedNetwork.builder()
                                        .allowMcpServers(false)
                                        .allowPackageManagers(true)
                                        .addAllowedHost("api.example.com")
                                        .build()
                                )
                                .packages(
                                    BetaPackages.builder()
                                        .addApt("string")
                                        .addCargo("string")
                                        .addGem("string")
                                        .addGo("string")
                                        .addNpm("string")
                                        .addPip("pandas")
                                        .addPip("numpy")
                                        .type(BetaPackages.Type.PACKAGES)
                                        .build()
                                )
                                .build()
                        )
                        .createdAt("2026-03-15T10:00:00Z")
                        .description("Python environment with data-analysis packages.")
                        .metadata(BetaEnvironment.Metadata.builder().build())
                        .name("python-data-analysis")
                        .updatedAt("2026-03-15T10:00:00Z")
                        .build()
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        val roundtrippedEnvironmentListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(environmentListPageResponse),
                jacksonTypeRef<EnvironmentListPageResponse>(),
            )

        assertThat(roundtrippedEnvironmentListPageResponse).isEqualTo(environmentListPageResponse)
    }
}
