package com.anthropic.models.beta.organization.claudecode.managedsettings.configuration

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ConfigurationUpdateParamsTest {

    @Test
    fun create() {
        ConfigurationUpdateParams.builder()
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .mode(BetaClaudeCodeManagedSettingsOrganizationModeParam.builder().build())
            .addRbacGroupOrder("string")
            .rbacGroupOrderVersion("rbac_group_order_version")
            .build()
    }

    @Test
    fun headers() {
        val params =
            ConfigurationUpdateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .mode(BetaClaudeCodeManagedSettingsOrganizationModeParam.builder().build())
                .addRbacGroupOrder("string")
                .rbacGroupOrderVersion("rbac_group_order_version")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf(
                                "computer-use-2024-10-22",
                                "ce-claude-code-managed-settings-2026-10-06",
                            )
                            .joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = ConfigurationUpdateParams.builder().build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            ConfigurationUpdateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .mode(BetaClaudeCodeManagedSettingsOrganizationModeParam.builder().build())
                .addRbacGroupOrder("string")
                .rbacGroupOrderVersion("rbac_group_order_version")
                .build()

        val body = params._body()

        assertThat(body.mode())
            .contains(
                BetaClaudeCodeManagedSettingsModeParam.ofOrganization(
                    BetaClaudeCodeManagedSettingsOrganizationModeParam.builder().build()
                )
            )
        assertThat(body.rbacGroupOrder().getOrNull()).containsExactly("string")
        assertThat(body.rbacGroupOrderVersion()).contains("rbac_group_order_version")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = ConfigurationUpdateParams.builder().build()

        val body = params._body()
    }
}
