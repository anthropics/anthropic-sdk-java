package com.anthropic.models.organization.compliancesettings

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComplianceSettingsStateDisabledParamTest {

    @Test
    fun create() {
        val complianceSettingsStateDisabledParam =
            ComplianceSettingsStateDisabledParam.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val complianceSettingsStateDisabledParam =
            ComplianceSettingsStateDisabledParam.builder().build()

        val roundtrippedComplianceSettingsStateDisabledParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(complianceSettingsStateDisabledParam),
                jacksonTypeRef<ComplianceSettingsStateDisabledParam>(),
            )

        assertThat(roundtrippedComplianceSettingsStateDisabledParam)
            .isEqualTo(complianceSettingsStateDisabledParam)
    }
}
