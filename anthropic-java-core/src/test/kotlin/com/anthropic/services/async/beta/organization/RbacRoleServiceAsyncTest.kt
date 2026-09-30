package com.anthropic.services.async.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class RbacRoleServiceAsyncTest {

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val rbacRoleServiceAsync = client.beta().organization().rbacRoles()

        val betaRbacRoleFuture = rbacRoleServiceAsync.retrieve("rbac_role_id")

        val betaRbacRole = betaRbacRoleFuture.get()
        betaRbacRole.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val rbacRoleServiceAsync = client.beta().organization().rbacRoles()

        val pageFuture = rbacRoleServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }
}
