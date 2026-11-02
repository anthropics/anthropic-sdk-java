package com.anthropic.services.async.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class RbacRoleServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val rbacRoleServiceAsync = client.organization().rbacRoles()

        val rbacRoleFuture = rbacRoleServiceAsync.retrieve("rbac_role_id")

        val rbacRole = rbacRoleFuture.get()
        rbacRole.validate()
    }

    @Test
    fun list() {
        val rbacRoleServiceAsync = client.organization().rbacRoles()

        val pageFuture = rbacRoleServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }
}
