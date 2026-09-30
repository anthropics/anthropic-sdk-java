package com.anthropic.models.organization.compliancesettings

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComplianceSettingUpdateParamsTest {

    @Test
    fun create() {
        ComplianceSettingUpdateParams.builder()
            .state(ComplianceSettingsStateEnabledParam.builder().build())
            .build()
    }

    @Test
    fun body() {
        val params =
            ComplianceSettingUpdateParams.builder()
                .state(ComplianceSettingsStateEnabledParam.builder().build())
                .build()

        val body = params._body()

        assertThat(body.state())
            .isEqualTo(
                ComplianceSettingsStateParam.ofEnabled(
                    ComplianceSettingsStateEnabledParam.builder().build()
                )
            )
    }
}
