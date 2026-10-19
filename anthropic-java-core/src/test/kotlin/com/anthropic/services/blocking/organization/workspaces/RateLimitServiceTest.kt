package com.anthropic.services.blocking.organization.workspaces

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.organization.workspaces.ratelimits.RateLimitUpdateParams
import com.anthropic.models.organization.workspaces.ratelimits.WorkspaceRateLimitType
import com.anthropic.models.organization.workspaces.ratelimits.WorkspaceRateLimitUpdateValue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class RateLimitServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun update() {
        val rateLimitService = client.organization().workspaces().rateLimits()

        val workspaceRateLimit =
            rateLimitService.update(
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

        workspaceRateLimit.validate()
    }

    @Test
    fun list() {
        val rateLimitService = client.organization().workspaces().rateLimits()

        val page = rateLimitService.list("workspace_id")

        page.response().validate()
    }
}
