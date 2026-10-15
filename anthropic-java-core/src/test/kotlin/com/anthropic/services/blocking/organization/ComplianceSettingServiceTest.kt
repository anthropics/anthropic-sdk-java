package com.anthropic.services.blocking.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.organization.compliancesettings.ComplianceSettingUpdateParams
import com.anthropic.models.organization.compliancesettings.ComplianceSettingsStateEnabledParam
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ComplianceSettingServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val complianceSettingService = client.organization().complianceSettings()

        val organizationComplianceSettings = complianceSettingService.retrieve()

        organizationComplianceSettings.validate()
    }

    @Test
    fun update() {
        val complianceSettingService = client.organization().complianceSettings()

        val organizationComplianceSettings =
            complianceSettingService.update(
                ComplianceSettingUpdateParams.builder()
                    .state(ComplianceSettingsStateEnabledParam.builder().build())
                    .build()
            )

        organizationComplianceSettings.validate()
    }
}
