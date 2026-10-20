package com.anthropic.models.beta.userprofiles.actionurls

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ActionUrlCreateParamsTest {

    @Test
    fun create() {
        ActionUrlCreateParams.builder()
            .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .action(BetaUserProfileEnrollEndUserAction.builder().build())
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            ActionUrlCreateParams.builder()
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .action(BetaUserProfileEnrollEndUserAction.builder().build())
                .build()

        assertThat(params._pathParam(0)).isEqualTo("uprof_011CZkZCu8hGbp5mYRQgUmz9")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            ActionUrlCreateParams.builder()
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .action(BetaUserProfileEnrollEndUserAction.builder().build())
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("message-batches-2024-09-24", "user-profiles-2026-08-18")
                            .joinToString(","),
                    )
                    .put("anthropic-workspace-id", "wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            ActionUrlCreateParams.builder()
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .action(BetaUserProfileEnrollEndUserAction.builder().build())
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            ActionUrlCreateParams.builder()
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .action(BetaUserProfileEnrollEndUserAction.builder().build())
                .build()

        val body = params._body()

        assertThat(body.action()).isEqualTo(BetaUserProfileEnrollEndUserAction.builder().build())
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            ActionUrlCreateParams.builder()
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .action(BetaUserProfileEnrollEndUserAction.builder().build())
                .build()

        val body = params._body()

        assertThat(body.action()).isEqualTo(BetaUserProfileEnrollEndUserAction.builder().build())
    }
}
