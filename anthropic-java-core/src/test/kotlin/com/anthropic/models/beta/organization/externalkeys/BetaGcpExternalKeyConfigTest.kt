// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGcpExternalKeyConfigTest {

    @Test
    fun create() {
        val betaGcpExternalKeyConfig =
            BetaGcpExternalKeyConfig.of(
                "projects/my-proj/locations/us/keyRings/my-ring/cryptoKeys/my-key"
            )

        assertThat(betaGcpExternalKeyConfig.keyName())
            .isEqualTo("projects/my-proj/locations/us/keyRings/my-ring/cryptoKeys/my-key")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGcpExternalKeyConfig =
            BetaGcpExternalKeyConfig.of(
                "projects/my-proj/locations/us/keyRings/my-ring/cryptoKeys/my-key"
            )

        val roundtrippedBetaGcpExternalKeyConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGcpExternalKeyConfig),
                jacksonTypeRef<BetaGcpExternalKeyConfig>(),
            )

        assertThat(roundtrippedBetaGcpExternalKeyConfig).isEqualTo(betaGcpExternalKeyConfig)
    }
}
