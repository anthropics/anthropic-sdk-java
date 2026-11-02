package com.anthropic.services.blocking.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.organization.rbacgroups.RbacGroupCreateParams
import com.anthropic.models.organization.rbacgroups.RbacGroupUpdateParams
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
        val rbacGroupService = client.organization().rbacGroups()

        val rbacGroup =
            rbacGroupService.create(RbacGroupCreateParams.builder().name("Engineering").build())

        rbacGroup.validate()
    }

    @Test
    fun retrieve() {
        val rbacGroupService = client.organization().rbacGroups()

        val rbacGroup = rbacGroupService.retrieve("rbac_group_id")

        rbacGroup.validate()
    }

    @Test
    fun update() {
        val rbacGroupService = client.organization().rbacGroups()

        val rbacGroup =
            rbacGroupService.update(
                RbacGroupUpdateParams.builder()
                    .rbacGroupId("rbac_group_id")
                    .name("Engineering")
                    .build()
            )

        rbacGroup.validate()
    }

    @Test
    fun list() {
        val rbacGroupService = client.organization().rbacGroups()

        val page = rbacGroupService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val rbacGroupService = client.organization().rbacGroups()

        val deletedRbacGroup = rbacGroupService.delete("rbac_group_id")

        deletedRbacGroup.validate()
    }
}
