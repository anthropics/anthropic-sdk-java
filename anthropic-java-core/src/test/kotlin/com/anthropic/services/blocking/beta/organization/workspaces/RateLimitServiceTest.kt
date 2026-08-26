// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization.workspaces

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class RateLimitServiceTest {

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val rateLimitService = client.beta().organization().workspaces().rateLimits()

        val page = rateLimitService.list("workspace_id")

        page.response().validate()
    }
}
