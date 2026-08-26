// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDataResidencyCreateConfigTest {

    @Test
    fun create() {
        val betaDataResidencyCreateConfig =
            BetaDataResidencyCreateConfig.builder()
                .allowedInferenceGeosUnrestricted()
                .defaultInferenceGeo(BetaDataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL)
                .workspaceGeo(BetaDataResidencyCreateConfig.WorkspaceGeo.US)
                .build()

        assertThat(betaDataResidencyCreateConfig.allowedInferenceGeos())
            .contains(BetaDataResidencyCreateConfig.AllowedInferenceGeos.ofUnrestricted())
        assertThat(betaDataResidencyCreateConfig.defaultInferenceGeo())
            .contains(BetaDataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL)
        assertThat(betaDataResidencyCreateConfig.workspaceGeo())
            .contains(BetaDataResidencyCreateConfig.WorkspaceGeo.US)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDataResidencyCreateConfig =
            BetaDataResidencyCreateConfig.builder()
                .allowedInferenceGeosUnrestricted()
                .defaultInferenceGeo(BetaDataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL)
                .workspaceGeo(BetaDataResidencyCreateConfig.WorkspaceGeo.US)
                .build()

        val roundtrippedBetaDataResidencyCreateConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDataResidencyCreateConfig),
                jacksonTypeRef<BetaDataResidencyCreateConfig>(),
            )

        assertThat(roundtrippedBetaDataResidencyCreateConfig)
            .isEqualTo(betaDataResidencyCreateConfig)
    }
}
