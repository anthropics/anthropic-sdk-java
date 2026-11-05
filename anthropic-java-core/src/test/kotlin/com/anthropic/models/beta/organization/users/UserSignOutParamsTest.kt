package com.anthropic.models.beta.organization.users

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserSignOutParamsTest {

    @Test
    fun create() {
        UserSignOutParams.builder()
            .userId("user_id")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params = UserSignOutParams.builder().userId("user_id").build()

        assertThat(params._pathParam(0)).isEqualTo("user_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            UserSignOutParams.builder()
                .userId("user_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("message-batches-2024-09-24", "admin-user-sign-out-2026-10-08")
                            .joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = UserSignOutParams.builder().userId("user_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
