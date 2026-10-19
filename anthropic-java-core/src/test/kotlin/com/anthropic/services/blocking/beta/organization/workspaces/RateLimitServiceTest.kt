package com.anthropic.services.blocking.beta.organization.workspaces

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.organization.workspaces.ratelimits.BetaWorkspaceRateLimitType
import com.anthropic.models.beta.organization.workspaces.ratelimits.BetaWorkspaceRateLimitUpdateValue
import com.anthropic.models.beta.organization.workspaces.ratelimits.RateLimitUpdateParams
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
        val rateLimitService = client.beta().organization().workspaces().rateLimits()

        val betaWorkspaceRateLimit =
            rateLimitService.update(
                RateLimitUpdateParams.builder()
                    .workspaceId("workspace_id")
                    .rateLimitId("rate_limit_id")
                    .addLimit(
                        BetaWorkspaceRateLimitUpdateValue.builder()
                            .type(BetaWorkspaceRateLimitType.FAST_ITPMCA)
                            .value(0L)
                            .build()
                    )
                    .build()
            )

        betaWorkspaceRateLimit.validate()
    }

    @Test
    fun list() {
        val rateLimitService = client.beta().organization().workspaces().rateLimits()

        val page = rateLimitService.list("workspace_id")

        page.response().validate()
    }
}
