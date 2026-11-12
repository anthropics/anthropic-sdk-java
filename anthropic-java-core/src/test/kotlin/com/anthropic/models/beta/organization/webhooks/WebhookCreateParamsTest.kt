package com.anthropic.models.beta.organization.webhooks

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebhookCreateParamsTest {

    @Test
    fun create() {
        WebhookCreateParams.builder()
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .addEnabledEvent(
                WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_OPENED
            )
            .url("x")
            .description("description")
            .name("name")
            .build()
    }

    @Test
    fun headers() {
        val params =
            WebhookCreateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .addEnabledEvent(
                    WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_OPENED
                )
                .url("x")
                .description("description")
                .name("name")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("computer-use-2024-10-22", "webhooks-2026-10-07").joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            WebhookCreateParams.builder()
                .addEnabledEvent(
                    WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_OPENED
                )
                .url("x")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            WebhookCreateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .addEnabledEvent(
                    WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_OPENED
                )
                .url("x")
                .description("description")
                .name("name")
                .build()

        val body = params._body()

        assertThat(body.enabledEvents())
            .containsExactly(
                WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_OPENED
            )
        assertThat(body.url()).isEqualTo("x")
        assertThat(body.description()).contains("description")
        assertThat(body.name()).contains("name")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            WebhookCreateParams.builder()
                .addEnabledEvent(
                    WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_OPENED
                )
                .url("x")
                .build()

        val body = params._body()

        assertThat(body.enabledEvents())
            .containsExactly(
                WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_OPENED
            )
        assertThat(body.url()).isEqualTo("x")
    }
}
