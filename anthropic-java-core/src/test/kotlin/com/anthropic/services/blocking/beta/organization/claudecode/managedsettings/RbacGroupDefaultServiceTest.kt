package com.anthropic.services.blocking.beta.organization.claudecode.managedsettings

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroupdefault.RbacGroupDefaultRetrieveParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroupdefault.RbacGroupDefaultUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class RbacGroupDefaultServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val rbacGroupDefaultService =
            client
                .beta()
                .organization()
                .claudeCode()
                .managedSettings()
                .rbacGroupDefault()
                .withOptions { it.putQueryParam("beta", "true") }

        val betaClaudeCodeRbacGroupDefaultManagedSettings =
            rbacGroupDefaultService.retrieve(
                RbacGroupDefaultRetrieveParams.builder()
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        betaClaudeCodeRbacGroupDefaultManagedSettings.validate()
    }

    @Test
    fun update() {
        val rbacGroupDefaultService =
            client
                .beta()
                .organization()
                .claudeCode()
                .managedSettings()
                .rbacGroupDefault()
                .withOptions { it.putQueryParam("beta", "true") }

        val betaClaudeCodeRbacGroupDefaultManagedSettings =
            rbacGroupDefaultService.update(
                RbacGroupDefaultUpdateParams.builder()
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .settings(
                        RbacGroupDefaultUpdateParams.Settings.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .build()
            )

        betaClaudeCodeRbacGroupDefaultManagedSettings.validate()
    }
}
