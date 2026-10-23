package com.anthropic.services.blocking.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupCreateParams
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupUpdateParams
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
    fun create() {
        val rbacGroupService =
            client.beta().organization().rbacGroups().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaRbacGroup =
            rbacGroupService.create(RbacGroupCreateParams.builder().name("Engineering").build())

        betaRbacGroup.validate()
    }

    @Test
    fun retrieve() {
        val rbacGroupService =
            client.beta().organization().rbacGroups().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaRbacGroup = rbacGroupService.retrieve("rbac_group_id")

        betaRbacGroup.validate()
    }

    @Test
    fun update() {
        val rbacGroupService =
            client.beta().organization().rbacGroups().withOptions {
                it.putQueryParam("beta", "true")
            }

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
        val rbacGroupService =
            client.beta().organization().rbacGroups().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page = rbacGroupService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val rbacGroupService =
            client.beta().organization().rbacGroups().withOptions {
                it.putQueryParam("beta", "true")
            }

        val rbacGroup = rbacGroupService.delete("rbac_group_id")

        rbacGroup.validate()
    }
}
