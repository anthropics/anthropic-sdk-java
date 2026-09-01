// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.organization.compliancesettings.BetaComplianceSettingsStateEnabledParam
import com.anthropic.models.beta.organization.compliancesettings.ComplianceSettingUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ComplianceSettingServiceAsyncTest {

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val complianceSettingServiceAsync = client.beta().organization().complianceSettings()

        val betaComplianceSettingsFuture = complianceSettingServiceAsync.retrieve()

        val betaComplianceSettings = betaComplianceSettingsFuture.get()
        betaComplianceSettings.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val complianceSettingServiceAsync = client.beta().organization().complianceSettings()

        val betaComplianceSettingsFuture =
            complianceSettingServiceAsync.update(
                ComplianceSettingUpdateParams.builder()
                    .state(BetaComplianceSettingsStateEnabledParam.builder().build())
                    .build()
            )

        val betaComplianceSettings = betaComplianceSettingsFuture.get()
        betaComplianceSettings.validate()
    }
}
