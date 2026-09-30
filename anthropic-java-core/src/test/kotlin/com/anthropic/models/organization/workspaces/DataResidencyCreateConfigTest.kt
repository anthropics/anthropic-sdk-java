package com.anthropic.models.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DataResidencyCreateConfigTest {

    @Test
    fun create() {
        val dataResidencyCreateConfig =
            DataResidencyCreateConfig.builder()
                .allowedInferenceGeosUnrestricted()
                .defaultInferenceGeo(DataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL)
                .workspaceGeo(DataResidencyCreateConfig.WorkspaceGeo.US)
                .build()

        assertThat(dataResidencyCreateConfig.allowedInferenceGeos())
            .contains(DataResidencyCreateConfig.AllowedInferenceGeos.ofUnrestricted())
        assertThat(dataResidencyCreateConfig.defaultInferenceGeo())
            .contains(DataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL)
        assertThat(dataResidencyCreateConfig.workspaceGeo())
            .contains(DataResidencyCreateConfig.WorkspaceGeo.US)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val dataResidencyCreateConfig =
            DataResidencyCreateConfig.builder()
                .allowedInferenceGeosUnrestricted()
                .defaultInferenceGeo(DataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL)
                .workspaceGeo(DataResidencyCreateConfig.WorkspaceGeo.US)
                .build()

        val roundtrippedDataResidencyCreateConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(dataResidencyCreateConfig),
                jacksonTypeRef<DataResidencyCreateConfig>(),
            )

        assertThat(roundtrippedDataResidencyCreateConfig).isEqualTo(dataResidencyCreateConfig)
    }
}
