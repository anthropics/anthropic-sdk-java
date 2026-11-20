package com.anthropic.models.beta.organization.compliancesettings

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComplianceSettingUpdateParamsTest {

    @Test
    fun create() {
        ComplianceSettingUpdateParams.builder()
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .state(BetaComplianceSettingsStateEnabledParam.builder().build())
            .build()
    }

    @Test
    fun headers() {
        val params =
            ComplianceSettingUpdateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .state(BetaComplianceSettingsStateEnabledParam.builder().build())
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put("anthropic-beta", listOf("computer-use-2024-10-22").joinToString(","))
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            ComplianceSettingUpdateParams.builder()
                .state(BetaComplianceSettingsStateEnabledParam.builder().build())
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            ComplianceSettingUpdateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
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

    @Test
    fun bodyWithoutOptionalFields() {
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
