// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.compliancesettings

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceSettingsStateEnabledTest {

    @Test
    fun create() {
        val betaComplianceSettingsStateEnabled =
            BetaComplianceSettingsStateEnabled.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceSettingsStateEnabled =
            BetaComplianceSettingsStateEnabled.builder().build()

        val roundtrippedBetaComplianceSettingsStateEnabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceSettingsStateEnabled),
                jacksonTypeRef<BetaComplianceSettingsStateEnabled>(),
            )

        assertThat(roundtrippedBetaComplianceSettingsStateEnabled)
            .isEqualTo(betaComplianceSettingsStateEnabled)
    }
}
