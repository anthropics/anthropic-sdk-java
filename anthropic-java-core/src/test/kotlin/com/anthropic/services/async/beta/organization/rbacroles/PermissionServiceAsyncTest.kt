package com.anthropic.services.async.beta.organization.rbacroles

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class PermissionServiceAsyncTest {

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val permissionServiceAsync = client.beta().organization().rbacRoles().permissions()

        val pageFuture = permissionServiceAsync.list("rbac_role_id")

        val page = pageFuture.get()
        page.response().validate()
    }
}
