package com.anthropic.services.blocking.beta.organization.claudecode.managedsettings

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.claudecode.managedsettings.configuration.BetaClaudeCodeManagedSettingsOrganizationModeParam
import com.anthropic.models.beta.organization.claudecode.managedsettings.configuration.ConfigurationRetrieveParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.configuration.ConfigurationUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ConfigurationServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val configurationService =
            client
                .beta()
                .organization()
                .claudeCode()
                .managedSettings()
                .configuration()
                .withOptions { it.putQueryParam("beta", "true") }

        val betaClaudeCodeManagedSettingsConfiguration =
            configurationService.retrieve(
                ConfigurationRetrieveParams.builder()
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        betaClaudeCodeManagedSettingsConfiguration.validate()
    }

    @Test
    fun update() {
        val configurationService =
            client
                .beta()
                .organization()
                .claudeCode()
                .managedSettings()
                .configuration()
                .withOptions { it.putQueryParam("beta", "true") }

        val betaClaudeCodeManagedSettingsConfiguration =
            configurationService.update(
                ConfigurationUpdateParams.builder()
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .mode(BetaClaudeCodeManagedSettingsOrganizationModeParam.builder().build())
                    .addRbacGroupOrder("string")
                    .rbacGroupOrderVersion("rbac_group_order_version")
                    .build()
            )

        betaClaudeCodeManagedSettingsConfiguration.validate()
    }
}
