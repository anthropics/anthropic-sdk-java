package com.anthropic.models.beta.organization.webhooks

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebhookUpdateParamsTest {

    @Test
    fun create() {
        WebhookUpdateParams.builder()
            .webhookEndpointId("webhook_endpoint_id")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .description("description")
            .addEnabledEvent(
                WebhookUpdateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_OPENED
            )
            .name("name")
            .state(WebhookUpdateParams.State.DISABLED)
            .url("x")
            .build()
    }

    @Test
    fun pathParams() {
        val params = WebhookUpdateParams.builder().webhookEndpointId("webhook_endpoint_id").build()

        assertThat(params._pathParam(0)).isEqualTo("webhook_endpoint_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            WebhookUpdateParams.builder()
                .webhookEndpointId("webhook_endpoint_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .description("description")
                .addEnabledEvent(
                    WebhookUpdateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_OPENED
                )
                .name("name")
                .state(WebhookUpdateParams.State.DISABLED)
                .url("x")
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
        val params = WebhookUpdateParams.builder().webhookEndpointId("webhook_endpoint_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            WebhookUpdateParams.builder()
                .webhookEndpointId("webhook_endpoint_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .description("description")
                .addEnabledEvent(
                    WebhookUpdateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_OPENED
                )
                .name("name")
                .state(WebhookUpdateParams.State.DISABLED)
                .url("x")
                .build()

        val body = params._body()

        assertThat(body.description()).contains("description")
        assertThat(body.enabledEvents().getOrNull())
            .containsExactly(
                WebhookUpdateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_OPENED
            )
        assertThat(body.name()).contains("name")
        assertThat(body.state()).contains(WebhookUpdateParams.State.DISABLED)
        assertThat(body.url()).contains("x")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = WebhookUpdateParams.builder().webhookEndpointId("webhook_endpoint_id").build()

        val body = params._body()
    }
}
