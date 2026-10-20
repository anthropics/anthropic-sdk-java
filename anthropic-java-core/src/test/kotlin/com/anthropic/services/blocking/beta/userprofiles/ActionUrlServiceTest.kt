package com.anthropic.services.blocking.beta.userprofiles

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.userprofiles.actionurls.ActionUrlCreateParams
import com.anthropic.models.beta.userprofiles.actionurls.BetaUserProfileEnrollEndUserAction
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ActionUrlServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val actionUrlService = client.beta().userProfiles().actionUrls()

        val betaUserProfileActionUrl =
            actionUrlService.create(
                ActionUrlCreateParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .action(BetaUserProfileEnrollEndUserAction.builder().build())
                    .build()
            )

        betaUserProfileActionUrl.validate()
    }
}
