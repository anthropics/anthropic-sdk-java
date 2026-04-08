// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPackagesTest {

    @Test
    fun create() {
        val betaPackages =
            BetaPackages.builder()
                .addApt("string")
                .addCargo("string")
                .addGem("string")
                .addGo("string")
                .addNpm("string")
                .addPip("string")
                .type(BetaPackages.Type.PACKAGES)
                .build()

        assertThat(betaPackages.apt()).containsExactly("string")
        assertThat(betaPackages.cargo()).containsExactly("string")
        assertThat(betaPackages.gem()).containsExactly("string")
        assertThat(betaPackages.go()).containsExactly("string")
        assertThat(betaPackages.npm()).containsExactly("string")
        assertThat(betaPackages.pip()).containsExactly("string")
        assertThat(betaPackages.type()).contains(BetaPackages.Type.PACKAGES)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPackages =
            BetaPackages.builder()
                .addApt("string")
                .addCargo("string")
                .addGem("string")
                .addGo("string")
                .addNpm("string")
                .addPip("string")
                .type(BetaPackages.Type.PACKAGES)
                .build()

        val roundtrippedBetaPackages =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPackages),
                jacksonTypeRef<BetaPackages>(),
            )

        assertThat(roundtrippedBetaPackages).isEqualTo(betaPackages)
    }
}
