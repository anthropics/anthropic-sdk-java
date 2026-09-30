package com.anthropic.services.blocking.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupCreateParams
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class RbacGroupServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val rbacGroupService = client.beta().organization().rbacGroups()

        val betaRbacGroup =
            rbacGroupService.create(RbacGroupCreateParams.builder().name("Engineering").build())

        betaRbacGroup.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val rbacGroupService = client.beta().organization().rbacGroups()

        val betaRbacGroup = rbacGroupService.retrieve("rbac_group_id")

        betaRbacGroup.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val rbacGroupService = client.beta().organization().rbacGroups()

        val betaRbacGroup =
            rbacGroupService.update(
                RbacGroupUpdateParams.builder()
                    .rbacGroupId("rbac_group_id")
                    .name("Engineering")
                    .build()
            )

        betaRbacGroup.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val rbacGroupService = client.beta().organization().rbacGroups()

        val page = rbacGroupService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val rbacGroupService = client.beta().organization().rbacGroups()

        val rbacGroup = rbacGroupService.delete("rbac_group_id")

        rbacGroup.validate()
    }
}
