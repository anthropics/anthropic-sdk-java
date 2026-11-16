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
            .addEnabledEvent(
                WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_RESOLVED
            )
            .url("https://example.com/anthropic-webhooks")
            .description("description")
            .name("Enforcement actions")
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
                .addEnabledEvent(
                    WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_RESOLVED
                )
                .url("https://example.com/anthropic-webhooks")
                .description("description")
                .name("Enforcement actions")
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
                .addEnabledEvent(
                    WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_RESOLVED
                )
                .url("https://example.com/anthropic-webhooks")
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
                .addEnabledEvent(
                    WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_RESOLVED
                )
                .url("https://example.com/anthropic-webhooks")
                .description("description")
                .name("Enforcement actions")
                .build()

        val body = params._body()

        assertThat(body.enabledEvents())
            .containsExactly(
                WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_OPENED,
                WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_RESOLVED,
            )
        assertThat(body.url()).isEqualTo("https://example.com/anthropic-webhooks")
        assertThat(body.description()).contains("description")
        assertThat(body.name()).contains("Enforcement actions")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            WebhookCreateParams.builder()
                .addEnabledEvent(
                    WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_OPENED
                )
                .addEnabledEvent(
                    WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_RESOLVED
                )
                .url("https://example.com/anthropic-webhooks")
                .build()

        val body = params._body()

        assertThat(body.enabledEvents())
            .containsExactly(
                WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_OPENED,
                WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_RESOLVED,
            )
        assertThat(body.url()).isEqualTo("https://example.com/anthropic-webhooks")
    }
}
