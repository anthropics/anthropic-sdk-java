package com.anthropic.models.organization.compliancesettings

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComplianceSettingsStateEnabledParamTest {

    @Test
    fun create() {
        val complianceSettingsStateEnabledParam =
            ComplianceSettingsStateEnabledParam.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val complianceSettingsStateEnabledParam =
            ComplianceSettingsStateEnabledParam.builder().build()

        val roundtrippedComplianceSettingsStateEnabledParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(complianceSettingsStateEnabledParam),
                jacksonTypeRef<ComplianceSettingsStateEnabledParam>(),
            )

        assertThat(roundtrippedComplianceSettingsStateEnabledParam)
            .isEqualTo(complianceSettingsStateEnabledParam)
    }
}
