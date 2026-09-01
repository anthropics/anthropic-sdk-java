// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.compliancesettings

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceSettingsStateDisabledParamTest {

    @Test
    fun create() {
        val betaComplianceSettingsStateDisabledParam =
            BetaComplianceSettingsStateDisabledParam.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceSettingsStateDisabledParam =
            BetaComplianceSettingsStateDisabledParam.builder().build()

        val roundtrippedBetaComplianceSettingsStateDisabledParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceSettingsStateDisabledParam),
                jacksonTypeRef<BetaComplianceSettingsStateDisabledParam>(),
            )

        assertThat(roundtrippedBetaComplianceSettingsStateDisabledParam)
            .isEqualTo(betaComplianceSettingsStateDisabledParam)
    }
}
