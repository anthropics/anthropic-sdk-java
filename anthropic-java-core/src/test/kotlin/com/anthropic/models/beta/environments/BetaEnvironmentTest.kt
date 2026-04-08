// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaEnvironmentTest {

    @Test
    fun create() {
        val betaEnvironment =
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

        assertThat(betaEnvironment.id()).isEqualTo("env_011CZkZ9X2dpNyB7HsEFoRfW")
        assertThat(betaEnvironment.archivedAt()).isEmpty
        assertThat(betaEnvironment.config())
            .isEqualTo(
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
        assertThat(betaEnvironment.createdAt()).isEqualTo("2026-03-15T10:00:00Z")
        assertThat(betaEnvironment.description())
            .isEqualTo("Python environment with data-analysis packages.")
        assertThat(betaEnvironment.metadata()).isEqualTo(BetaEnvironment.Metadata.builder().build())
        assertThat(betaEnvironment.name()).isEqualTo("python-data-analysis")
        assertThat(betaEnvironment.updatedAt()).isEqualTo("2026-03-15T10:00:00Z")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaEnvironment =
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

        val roundtrippedBetaEnvironment =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaEnvironment),
                jacksonTypeRef<BetaEnvironment>(),
            )

        assertThat(roundtrippedBetaEnvironment).isEqualTo(betaEnvironment)
    }
}
