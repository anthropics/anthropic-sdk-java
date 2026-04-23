// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.userprofiles

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileCreateEnrollmentUrlParamsTest {

    @Test
    fun create() {
        UserProfileCreateEnrollmentUrlParams.builder()
            .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            UserProfileCreateEnrollmentUrlParams.builder()
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("uprof_011CZkZCu8hGbp5mYRQgUmz9")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            UserProfileCreateEnrollmentUrlParams.builder()
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            UserProfileCreateEnrollmentUrlParams.builder()
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
