// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.compliancesettings

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComplianceSettingUpdateParamsTest {

    @Test
    fun create() {
        ComplianceSettingUpdateParams.builder()
            .state(BetaComplianceSettingsStateEnabledParam.builder().build())
            .build()
    }

    @Test
    fun body() {
        val params =
            ComplianceSettingUpdateParams.builder()
                .state(BetaComplianceSettingsStateEnabledParam.builder().build())
                .build()

        val body = params._body()

        assertThat(body.state())
            .isEqualTo(
                BetaComplianceSettingsStateParam.ofEnabled(
                    BetaComplianceSettingsStateEnabledParam.builder().build()
                )
            )
    }
}
