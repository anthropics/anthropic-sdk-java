package com.anthropic.services.blocking.beta.organization.rbacroles

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class PermissionServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val permissionService = client.beta().organization().rbacRoles().permissions()

        val page = permissionService.list("rbac_role_id")

        page.response().validate()
    }
}
