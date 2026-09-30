package com.anthropic.models.organization.compliancesettings

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComplianceSettingsStateEnabledTest {

    @Test
    fun create() {
        val complianceSettingsStateEnabled = ComplianceSettingsStateEnabled.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val complianceSettingsStateEnabled = ComplianceSettingsStateEnabled.builder().build()

        val roundtrippedComplianceSettingsStateEnabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(complianceSettingsStateEnabled),
                jacksonTypeRef<ComplianceSettingsStateEnabled>(),
            )

        assertThat(roundtrippedComplianceSettingsStateEnabled)
            .isEqualTo(complianceSettingsStateEnabled)
    }
}
