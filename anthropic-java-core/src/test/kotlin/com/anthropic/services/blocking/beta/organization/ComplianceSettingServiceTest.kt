// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.organization.compliancesettings.BetaComplianceSettingsStateEnabledParam
import com.anthropic.models.beta.organization.compliancesettings.ComplianceSettingUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ComplianceSettingServiceTest {

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val complianceSettingService = client.beta().organization().complianceSettings()

        val betaComplianceSettings = complianceSettingService.retrieve()

        betaComplianceSettings.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val complianceSettingService = client.beta().organization().complianceSettings()

        val betaComplianceSettings =
            complianceSettingService.update(
                ComplianceSettingUpdateParams.builder()
                    .state(BetaComplianceSettingsStateEnabledParam.builder().build())
                    .build()
            )

        betaComplianceSettings.validate()
    }
}
