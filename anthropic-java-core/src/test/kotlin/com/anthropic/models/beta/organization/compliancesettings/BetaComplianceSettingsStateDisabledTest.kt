// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.compliancesettings

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceSettingsStateDisabledTest {

    @Test
    fun create() {
        val betaComplianceSettingsStateDisabled =
            BetaComplianceSettingsStateDisabled.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceSettingsStateDisabled =
            BetaComplianceSettingsStateDisabled.builder().build()

        val roundtrippedBetaComplianceSettingsStateDisabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceSettingsStateDisabled),
                jacksonTypeRef<BetaComplianceSettingsStateDisabled>(),
            )

        assertThat(roundtrippedBetaComplianceSettingsStateDisabled)
            .isEqualTo(betaComplianceSettingsStateDisabled)
    }
}
