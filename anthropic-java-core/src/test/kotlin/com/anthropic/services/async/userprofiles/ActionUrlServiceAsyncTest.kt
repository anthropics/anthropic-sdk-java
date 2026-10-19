package com.anthropic.services.async.userprofiles

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.userprofiles.actionurls.ActionUrlCreateParams
import com.anthropic.models.userprofiles.actionurls.UserProfileEnrollEndUserAction
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ActionUrlServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val actionUrlServiceAsync = client.userProfiles().actionUrls()

        val userProfileActionUrlFuture =
            actionUrlServiceAsync.create(
                ActionUrlCreateParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .action(UserProfileEnrollEndUserAction.builder().build())
                    .build()
            )

        val userProfileActionUrl = userProfileActionUrlFuture.get()
        userProfileActionUrl.validate()
    }
}
