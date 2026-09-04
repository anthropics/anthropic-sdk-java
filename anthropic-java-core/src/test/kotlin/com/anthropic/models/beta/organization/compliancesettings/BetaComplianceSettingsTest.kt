// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.compliancesettings

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceSettingsTest {

    @Test
    fun create() {
        val betaComplianceSettings =
            BetaComplianceSettings.builder()
                .state(BetaComplianceSettingsStateEnabled.builder().build())
                .build()

        assertThat(betaComplianceSettings.state())
            .isEqualTo(
                BetaComplianceSettingsState.ofEnabled(
                    BetaComplianceSettingsStateEnabled.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceSettings =
            BetaComplianceSettings.builder()
                .state(BetaComplianceSettingsStateEnabled.builder().build())
                .build()

        val roundtrippedBetaComplianceSettings =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceSettings),
                jacksonTypeRef<BetaComplianceSettings>(),
            )

        assertThat(roundtrippedBetaComplianceSettings).isEqualTo(betaComplianceSettings)
    }
}
