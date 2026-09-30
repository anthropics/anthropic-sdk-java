package com.anthropic.models.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DataResidencyUpdateConfigTest {

    @Test
    fun create() {
        val dataResidencyUpdateConfig =
            DataResidencyUpdateConfig.builder()
                .allowedInferenceGeosUnrestricted()
                .defaultInferenceGeo(DataResidencyUpdateConfig.DefaultInferenceGeo.GLOBAL)
                .build()

        assertThat(dataResidencyUpdateConfig.allowedInferenceGeos())
            .contains(DataResidencyUpdateConfig.AllowedInferenceGeos.ofUnrestricted())
        assertThat(dataResidencyUpdateConfig.defaultInferenceGeo())
            .contains(DataResidencyUpdateConfig.DefaultInferenceGeo.GLOBAL)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val dataResidencyUpdateConfig =
            DataResidencyUpdateConfig.builder()
                .allowedInferenceGeosUnrestricted()
                .defaultInferenceGeo(DataResidencyUpdateConfig.DefaultInferenceGeo.GLOBAL)
                .build()

        val roundtrippedDataResidencyUpdateConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(dataResidencyUpdateConfig),
                jacksonTypeRef<DataResidencyUpdateConfig>(),
            )

        assertThat(roundtrippedDataResidencyUpdateConfig).isEqualTo(dataResidencyUpdateConfig)
    }
}
