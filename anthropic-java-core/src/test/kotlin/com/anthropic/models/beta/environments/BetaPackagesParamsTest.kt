// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPackagesParamsTest {

    @Test
    fun create() {
        val betaPackagesParams =
            BetaPackagesParams.builder()
                .addApt("string")
                .addCargo("string")
                .addGem("string")
                .addGo("string")
                .addNpm("string")
                .addPip("string")
                .type(BetaPackagesParams.Type.PACKAGES)
                .build()

        assertThat(betaPackagesParams.apt().getOrNull()).containsExactly("string")
        assertThat(betaPackagesParams.cargo().getOrNull()).containsExactly("string")
        assertThat(betaPackagesParams.gem().getOrNull()).containsExactly("string")
        assertThat(betaPackagesParams.go().getOrNull()).containsExactly("string")
        assertThat(betaPackagesParams.npm().getOrNull()).containsExactly("string")
        assertThat(betaPackagesParams.pip().getOrNull()).containsExactly("string")
        assertThat(betaPackagesParams.type()).contains(BetaPackagesParams.Type.PACKAGES)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPackagesParams =
            BetaPackagesParams.builder()
                .addApt("string")
                .addCargo("string")
                .addGem("string")
                .addGo("string")
                .addNpm("string")
                .addPip("string")
                .type(BetaPackagesParams.Type.PACKAGES)
                .build()

        val roundtrippedBetaPackagesParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPackagesParams),
                jacksonTypeRef<BetaPackagesParams>(),
            )

        assertThat(roundtrippedBetaPackagesParams).isEqualTo(betaPackagesParams)
    }
}
