// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDataResidencyTest {

    @Test
    fun create() {
        val betaDataResidency =
            BetaDataResidency.builder()
                .allowedInferenceGeosUnrestricted()
                .defaultInferenceGeo("default_inference_geo")
                .workspaceGeo("workspace_geo")
                .build()

        assertThat(betaDataResidency.allowedInferenceGeos())
            .isEqualTo(BetaDataResidency.AllowedInferenceGeos.ofUnrestricted())
        assertThat(betaDataResidency.defaultInferenceGeo()).isEqualTo("default_inference_geo")
        assertThat(betaDataResidency.workspaceGeo()).isEqualTo("workspace_geo")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDataResidency =
            BetaDataResidency.builder()
                .allowedInferenceGeosUnrestricted()
                .defaultInferenceGeo("default_inference_geo")
                .workspaceGeo("workspace_geo")
                .build()

        val roundtrippedBetaDataResidency =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDataResidency),
                jacksonTypeRef<BetaDataResidency>(),
            )

        assertThat(roundtrippedBetaDataResidency).isEqualTo(betaDataResidency)
    }
}
