package com.anthropic.services.async.beta.userprofiles

import com.anthropic.CompletionExecutorAssertingInterceptor
import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.userprofiles.actionurls.ActionUrlCreateParams
import com.anthropic.models.beta.userprofiles.actionurls.BetaUserProfileEnrollEndUserAction
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ActionUrlServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .addInterceptor(CompletionExecutorAssertingInterceptor())
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val actionUrlServiceAsync =
            client.beta().userProfiles().actionUrls().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaUserProfileActionUrlFuture =
            actionUrlServiceAsync.create(
                ActionUrlCreateParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .action(BetaUserProfileEnrollEndUserAction.builder().build())
                    .build()
            )

        val betaUserProfileActionUrl = betaUserProfileActionUrlFuture.get()
        betaUserProfileActionUrl.validate()
    }
}
