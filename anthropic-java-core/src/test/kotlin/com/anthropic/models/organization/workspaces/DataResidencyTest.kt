package com.anthropic.models.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DataResidencyTest {

    @Test
    fun create() {
        val dataResidency =
            DataResidency.builder()
                .allowedInferenceGeosUnrestricted()
                .defaultInferenceGeo(DataResidency.DefaultInferenceGeo.GLOBAL)
                .workspaceGeo(DataResidency.WorkspaceGeo.US)
                .build()

        assertThat(dataResidency.allowedInferenceGeos())
            .isEqualTo(DataResidency.AllowedInferenceGeos.ofUnrestricted())
        assertThat(dataResidency.defaultInferenceGeo())
            .isEqualTo(DataResidency.DefaultInferenceGeo.GLOBAL)
        assertThat(dataResidency.workspaceGeo()).isEqualTo(DataResidency.WorkspaceGeo.US)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val dataResidency =
            DataResidency.builder()
                .allowedInferenceGeosUnrestricted()
                .defaultInferenceGeo(DataResidency.DefaultInferenceGeo.GLOBAL)
                .workspaceGeo(DataResidency.WorkspaceGeo.US)
                .build()

        val roundtrippedDataResidency =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(dataResidency),
                jacksonTypeRef<DataResidency>(),
            )

        assertThat(roundtrippedDataResidency).isEqualTo(dataResidency)
    }
}
