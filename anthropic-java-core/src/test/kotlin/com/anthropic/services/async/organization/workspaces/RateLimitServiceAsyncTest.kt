package com.anthropic.services.async.organization.workspaces

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.organization.workspaces.ratelimits.RateLimitUpdateParams
import com.anthropic.models.organization.workspaces.ratelimits.WorkspaceRateLimitType
import com.anthropic.models.organization.workspaces.ratelimits.WorkspaceRateLimitUpdateValue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class RateLimitServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun update() {
        val rateLimitServiceAsync = client.organization().workspaces().rateLimits()

        val workspaceRateLimitFuture =
            rateLimitServiceAsync.update(
                RateLimitUpdateParams.builder()
                    .workspaceId("workspace_id")
                    .rateLimitId("rate_limit_id")
                    .addLimit(
                        WorkspaceRateLimitUpdateValue.builder()
                            .type(WorkspaceRateLimitType.FAST_ITPMCA)
                            .value(0L)
                            .build()
                    )
                    .build()
            )

        val workspaceRateLimit = workspaceRateLimitFuture.get()
        workspaceRateLimit.validate()
    }

    @Test
    fun list() {
        val rateLimitServiceAsync = client.organization().workspaces().rateLimits()

        val pageFuture = rateLimitServiceAsync.list("workspace_id")

        val page = pageFuture.get()
        page.response().validate()
    }
}
