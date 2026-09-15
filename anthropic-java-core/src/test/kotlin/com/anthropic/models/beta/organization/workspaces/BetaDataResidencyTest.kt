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
                .defaultInferenceGeo(BetaDataResidency.DefaultInferenceGeo.GLOBAL)
                .workspaceGeo(BetaDataResidency.WorkspaceGeo.US)
                .build()

        assertThat(betaDataResidency.allowedInferenceGeos())
            .isEqualTo(BetaDataResidency.AllowedInferenceGeos.ofUnrestricted())
        assertThat(betaDataResidency.defaultInferenceGeo())
            .isEqualTo(BetaDataResidency.DefaultInferenceGeo.GLOBAL)
        assertThat(betaDataResidency.workspaceGeo()).isEqualTo(BetaDataResidency.WorkspaceGeo.US)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDataResidency =
            BetaDataResidency.builder()
                .allowedInferenceGeosUnrestricted()
                .defaultInferenceGeo(BetaDataResidency.DefaultInferenceGeo.GLOBAL)
                .workspaceGeo(BetaDataResidency.WorkspaceGeo.US)
                .build()

        val roundtrippedBetaDataResidency =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDataResidency),
                jacksonTypeRef<BetaDataResidency>(),
            )

        assertThat(roundtrippedBetaDataResidency).isEqualTo(betaDataResidency)
    }
}
