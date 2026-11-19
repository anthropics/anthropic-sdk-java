package com.anthropic.services.async.beta.organization.claudecode.managedsettings

import com.anthropic.CompletionExecutorAssertingInterceptor
import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.claudecode.managedsettings.configuration.BetaClaudeCodeManagedSettingsOrganizationModeParam
import com.anthropic.models.beta.organization.claudecode.managedsettings.configuration.ConfigurationRetrieveParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.configuration.ConfigurationUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ConfigurationServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .addInterceptor(CompletionExecutorAssertingInterceptor())
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val configurationServiceAsync =
            client
                .beta()
                .organization()
                .claudeCode()
                .managedSettings()
                .configuration()
                .withOptions { it.putQueryParam("beta", "true") }

        val betaClaudeCodeManagedSettingsConfigurationFuture =
            configurationServiceAsync.retrieve(
                ConfigurationRetrieveParams.builder()
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        val betaClaudeCodeManagedSettingsConfiguration =
            betaClaudeCodeManagedSettingsConfigurationFuture.get()
        betaClaudeCodeManagedSettingsConfiguration.validate()
    }

    @Test
    fun update() {
        val configurationServiceAsync =
            client
                .beta()
                .organization()
                .claudeCode()
                .managedSettings()
                .configuration()
                .withOptions { it.putQueryParam("beta", "true") }

        val betaClaudeCodeManagedSettingsConfigurationFuture =
            configurationServiceAsync.update(
                ConfigurationUpdateParams.builder()
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .mode(BetaClaudeCodeManagedSettingsOrganizationModeParam.builder().build())
                    .addRbacGroupOrder("string")
                    .rbacGroupOrderVersion("rbac_group_order_version")
                    .build()
            )

        val betaClaudeCodeManagedSettingsConfiguration =
            betaClaudeCodeManagedSettingsConfigurationFuture.get()
        betaClaudeCodeManagedSettingsConfiguration.validate()
    }
}
