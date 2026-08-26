// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDataResidencyUpdateConfigTest {

    @Test
    fun create() {
        val betaDataResidencyUpdateConfig =
            BetaDataResidencyUpdateConfig.builder()
                .allowedInferenceGeosUnrestricted()
                .defaultInferenceGeo(BetaDataResidencyUpdateConfig.DefaultInferenceGeo.GLOBAL)
                .build()

        assertThat(betaDataResidencyUpdateConfig.allowedInferenceGeos())
            .contains(BetaDataResidencyUpdateConfig.AllowedInferenceGeos.ofUnrestricted())
        assertThat(betaDataResidencyUpdateConfig.defaultInferenceGeo())
            .contains(BetaDataResidencyUpdateConfig.DefaultInferenceGeo.GLOBAL)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDataResidencyUpdateConfig =
            BetaDataResidencyUpdateConfig.builder()
                .allowedInferenceGeosUnrestricted()
                .defaultInferenceGeo(BetaDataResidencyUpdateConfig.DefaultInferenceGeo.GLOBAL)
                .build()

        val roundtrippedBetaDataResidencyUpdateConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDataResidencyUpdateConfig),
                jacksonTypeRef<BetaDataResidencyUpdateConfig>(),
            )

        assertThat(roundtrippedBetaDataResidencyUpdateConfig)
            .isEqualTo(betaDataResidencyUpdateConfig)
    }
}
