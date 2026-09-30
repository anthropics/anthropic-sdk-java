package com.anthropic.models.organization.compliancesettings

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComplianceSettingsStateDisabledTest {

    @Test
    fun create() {
        val complianceSettingsStateDisabled = ComplianceSettingsStateDisabled.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val complianceSettingsStateDisabled = ComplianceSettingsStateDisabled.builder().build()

        val roundtrippedComplianceSettingsStateDisabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(complianceSettingsStateDisabled),
                jacksonTypeRef<ComplianceSettingsStateDisabled>(),
            )

        assertThat(roundtrippedComplianceSettingsStateDisabled)
            .isEqualTo(complianceSettingsStateDisabled)
    }
}
