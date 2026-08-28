// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.compliancesettings

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceSettingsStateEnabledParamTest {

    @Test
    fun create() {
        val betaComplianceSettingsStateEnabledParam =
            BetaComplianceSettingsStateEnabledParam.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceSettingsStateEnabledParam =
            BetaComplianceSettingsStateEnabledParam.builder().build()

        val roundtrippedBetaComplianceSettingsStateEnabledParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceSettingsStateEnabledParam),
                jacksonTypeRef<BetaComplianceSettingsStateEnabledParam>(),
            )

        assertThat(roundtrippedBetaComplianceSettingsStateEnabledParam)
            .isEqualTo(betaComplianceSettingsStateEnabledParam)
    }
}
