package com.anthropic.services.blocking.beta.organization

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
        val rbacRoleService = client.beta().organization().rbacRoles()

        val betaRbacRole = rbacRoleService.retrieve("rbac_role_id")

        betaRbacRole.validate()
    }

    @Test
    fun list() {
        val rbacRoleService = client.beta().organization().rbacRoles()

        val page = rbacRoleService.list()

        page.response().validate()
    }
}
