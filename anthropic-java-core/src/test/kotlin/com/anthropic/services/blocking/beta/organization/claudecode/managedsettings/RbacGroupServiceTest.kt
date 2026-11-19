package com.anthropic.services.blocking.beta.organization.claudecode.managedsettings

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupDeleteParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupRetrieveParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupSetParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class RbacGroupServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val rbacGroupService =
            client.beta().organization().claudeCode().managedSettings().rbacGroups().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeCodeRbacGroupManagedSettings =
            rbacGroupService.retrieve(
                RbacGroupRetrieveParams.builder()
                    .rbacGroupId("rbac_group_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        betaClaudeCodeRbacGroupManagedSettings.validate()
    }

    @Test
    fun list() {
        val rbacGroupService =
            client.beta().organization().claudeCode().managedSettings().rbacGroups().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page = rbacGroupService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val rbacGroupService =
            client.beta().organization().claudeCode().managedSettings().rbacGroups().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaDeletedClaudeCodeRbacGroupManagedSettings =
            rbacGroupService.delete(
                RbacGroupDeleteParams.builder()
                    .rbacGroupId("rbac_group_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        betaDeletedClaudeCodeRbacGroupManagedSettings.validate()
    }

    @Test
    fun set() {
        val rbacGroupService =
            client.beta().organization().claudeCode().managedSettings().rbacGroups().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeCodeRbacGroupManagedSettings =
            rbacGroupService.set(
                RbacGroupSetParams.builder()
                    .rbacGroupId("rbac_group_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .settings(
                        RbacGroupSetParams.Settings.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .build()
            )

        betaClaudeCodeRbacGroupManagedSettings.validate()
    }
}
