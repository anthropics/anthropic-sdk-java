package com.anthropic.services.async.beta.organization.workspaces

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.organization.workspaces.ratelimits.BetaWorkspaceRateLimitType
import com.anthropic.models.beta.organization.workspaces.ratelimits.BetaWorkspaceRateLimitUpdateValue
import com.anthropic.models.beta.organization.workspaces.ratelimits.RateLimitUpdateParams
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
        val rateLimitServiceAsync =
            client.beta().organization().workspaces().rateLimits().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaWorkspaceRateLimitFuture =
            rateLimitServiceAsync.update(
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

        val betaWorkspaceRateLimit = betaWorkspaceRateLimitFuture.get()
        betaWorkspaceRateLimit.validate()
    }

    @Test
    fun list() {
        val rateLimitServiceAsync =
            client.beta().organization().workspaces().rateLimits().withOptions {
                it.putQueryParam("beta", "true")
            }

        val pageFuture = rateLimitServiceAsync.list("workspace_id")

        val page = pageFuture.get()
        page.response().validate()
    }
}
