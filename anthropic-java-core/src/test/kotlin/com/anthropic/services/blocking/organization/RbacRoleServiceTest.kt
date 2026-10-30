package com.anthropic.services.blocking.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class RbacRoleServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val rbacRoleService = client.organization().rbacRoles()

        val rbacRole = rbacRoleService.retrieve("rbac_role_id")

        rbacRole.validate()
    }

    @Test
    fun list() {
        val rbacRoleService = client.organization().rbacRoles()

        val page = rbacRoleService.list()

        page.response().validate()
    }
}
