// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCloudConfigTest {

    @Test
    fun create() {
        val betaCloudConfig =
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

        assertThat(betaCloudConfig.networking())
            .isEqualTo(
                BetaCloudConfig.Networking.ofLimited(
                    BetaLimitedNetwork.builder()
                        .allowMcpServers(false)
                        .allowPackageManagers(true)
                        .addAllowedHost("api.example.com")
                        .build()
                )
            )
        assertThat(betaCloudConfig.packages())
            .isEqualTo(
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
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCloudConfig =
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

        val roundtrippedBetaCloudConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCloudConfig),
                jacksonTypeRef<BetaCloudConfig>(),
            )

        assertThat(roundtrippedBetaCloudConfig).isEqualTo(betaCloudConfig)
    }
}
