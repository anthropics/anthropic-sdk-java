package com.anthropic.models.organization.compliancesettings

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrganizationComplianceSettingsTest {

    @Test
    fun create() {
        val organizationComplianceSettings =
            OrganizationComplianceSettings.builder()
                .state(ComplianceSettingsStateEnabled.builder().build())
                .build()

        assertThat(organizationComplianceSettings.state())
            .isEqualTo(
                ComplianceSettingsState.ofEnabled(ComplianceSettingsStateEnabled.builder().build())
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val organizationComplianceSettings =
            OrganizationComplianceSettings.builder()
                .state(ComplianceSettingsStateEnabled.builder().build())
                .build()

        val roundtrippedOrganizationComplianceSettings =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(organizationComplianceSettings),
                jacksonTypeRef<OrganizationComplianceSettings>(),
            )

        assertThat(roundtrippedOrganizationComplianceSettings)
            .isEqualTo(organizationComplianceSettings)
    }
}
