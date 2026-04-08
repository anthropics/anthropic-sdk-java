// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCloudConfigParamsTest {

    @Test
    fun create() {
        val betaCloudConfigParams =
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

        assertThat(betaCloudConfigParams.networking())
            .contains(
                BetaCloudConfigParams.Networking.ofLimited(
                    BetaLimitedNetworkParams.builder()
                        .allowMcpServers(true)
                        .allowPackageManagers(true)
                        .addAllowedHost("api.example.com")
                        .build()
                )
            )
        assertThat(betaCloudConfigParams.packages())
            .contains(
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
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCloudConfigParams =
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

        val roundtrippedBetaCloudConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCloudConfigParams),
                jacksonTypeRef<BetaCloudConfigParams>(),
            )

        assertThat(roundtrippedBetaCloudConfigParams).isEqualTo(betaCloudConfigParams)
    }
}
