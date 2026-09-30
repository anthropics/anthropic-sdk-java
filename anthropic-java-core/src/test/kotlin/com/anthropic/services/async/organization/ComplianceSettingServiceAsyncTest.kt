package com.anthropic.services.async.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.organization.compliancesettings.ComplianceSettingUpdateParams
import com.anthropic.models.organization.compliancesettings.ComplianceSettingsStateEnabledParam
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
        val complianceSettingServiceAsync = client.organization().complianceSettings()

        val organizationComplianceSettingsFuture = complianceSettingServiceAsync.retrieve()

        val organizationComplianceSettings = organizationComplianceSettingsFuture.get()
        organizationComplianceSettings.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val complianceSettingServiceAsync = client.organization().complianceSettings()

        val organizationComplianceSettingsFuture =
            complianceSettingServiceAsync.update(
                ComplianceSettingUpdateParams.builder()
                    .state(ComplianceSettingsStateEnabledParam.builder().build())
                    .build()
            )

        val organizationComplianceSettings = organizationComplianceSettingsFuture.get()
        organizationComplianceSettings.validate()
    }
}
