package com.anthropic.services.async.beta.organization.claudecode.managedsettings

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupDeleteParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupRetrieveParams
import com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups.RbacGroupSetParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class RbacGroupServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val rbacGroupServiceAsync =
            client.beta().organization().claudeCode().managedSettings().rbacGroups().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeCodeRbacGroupManagedSettingsFuture =
            rbacGroupServiceAsync.retrieve(
                RbacGroupRetrieveParams.builder()
                    .rbacGroupId("rbac_group_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        val betaClaudeCodeRbacGroupManagedSettings =
            betaClaudeCodeRbacGroupManagedSettingsFuture.get()
        betaClaudeCodeRbacGroupManagedSettings.validate()
    }

    @Test
    fun list() {
        val rbacGroupServiceAsync =
            client.beta().organization().claudeCode().managedSettings().rbacGroups().withOptions {
                it.putQueryParam("beta", "true")
            }

        val pageFuture = rbacGroupServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val rbacGroupServiceAsync =
            client.beta().organization().claudeCode().managedSettings().rbacGroups().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaDeletedClaudeCodeRbacGroupManagedSettingsFuture =
            rbacGroupServiceAsync.delete(
                RbacGroupDeleteParams.builder()
                    .rbacGroupId("rbac_group_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        val betaDeletedClaudeCodeRbacGroupManagedSettings =
            betaDeletedClaudeCodeRbacGroupManagedSettingsFuture.get()
        betaDeletedClaudeCodeRbacGroupManagedSettings.validate()
    }

    @Test
    fun set() {
        val rbacGroupServiceAsync =
            client.beta().organization().claudeCode().managedSettings().rbacGroups().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeCodeRbacGroupManagedSettingsFuture =
            rbacGroupServiceAsync.set(
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

        val betaClaudeCodeRbacGroupManagedSettings =
            betaClaudeCodeRbacGroupManagedSettingsFuture.get()
        betaClaudeCodeRbacGroupManagedSettings.validate()
    }
}
