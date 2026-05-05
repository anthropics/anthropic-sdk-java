// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.UnwrapWebhookParams
import com.anthropic.core.http.Headers
import com.anthropic.errors.AnthropicWebhookException
import com.standardwebhooks.Webhook
import java.time.Instant
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class WebhookServiceTest {

    @Test
    fun unwrap() {
        val client = AnthropicOkHttpClient.builder().apiKey("my-anthropic-api-key").build()
        val webhookService = client.beta().webhooks()

        val payload =
            "{\"id\":\"wevt_011CZkZYZd9rLmz3ujAcsqEw\",\"created_at\":\"2026-03-15T10:00:00Z\",\"data\":{\"id\":\"sesn_011CZkZAtmR3yMPDzynEDxu7\",\"organization_id\":\"org_011CZkZZAe0sMna4vkBdtrfx\",\"type\":\"session.status_idled\",\"workspace_id\":\"wrkspc_011CZkZaBF1tNoB5wlCeusgy\"},\"type\":\"event\"}"
        val webhookSecret = "whsec_c2VjcmV0Cg=="
        val messageId = "1"
        val timestampSeconds = Instant.now().epochSecond
        val webhook = Webhook(webhookSecret)
        val signature = webhook.sign(messageId, timestampSeconds, payload)
        val headers =
            Headers.builder()
                .putAll(
                    mapOf(
                        "webhook-signature" to listOf(signature),
                        "webhook-id" to listOf(messageId),
                        "webhook-timestamp" to listOf(timestampSeconds.toString()),
                    )
                )
                .build()

        // Correct key should not throw
        webhookService.unwrap(
            UnwrapWebhookParams.builder()
                .body(payload)
                .headers(headers)
                .secret(webhookSecret)
                .build()
        )
        webhookService
            .withOptions { it.webhookKey(webhookSecret) }
            .unwrap(UnwrapWebhookParams.builder().body(payload).headers(headers).build())

        // Secret in method takes precedence to secret on client
        val wrongKey = "whsec_aaaaaaaaaa"
        webhookService
            .withOptions { it.webhookKey(wrongKey) }
            .unwrap(
                UnwrapWebhookParams.builder()
                    .body(payload)
                    .headers(headers)
                    .secret(webhookSecret)
                    .build()
            )

        // Wrong key should throw
        assertThrows<AnthropicWebhookException> {
            val wrongKey = "whsec_aaaaaaaaaa"
            webhookService.unwrap(
                UnwrapWebhookParams.builder()
                    .body(payload)
                    .headers(headers)
                    .secret(wrongKey)
                    .build()
            )
        }
        assertThrows<AnthropicWebhookException> {
            val wrongKey = "whsec_aaaaaaaaaa"
            webhookService
                .withOptions { it.webhookKey(wrongKey) }
                .unwrap(UnwrapWebhookParams.builder().body(payload).headers(headers).build())
        }

        assertThrows<AnthropicWebhookException> {
            val wrongKey = "whsec_aaaaaaaaaa"
            webhookService.unwrap(
                UnwrapWebhookParams.builder()
                    .body(payload)
                    .headers(headers)
                    .secret(wrongKey)
                    .build()
            )
        }
        assertThrows<AnthropicWebhookException> {
            val wrongKey = "whsec_aaaaaaaaaa"
            webhookService
                .withOptions { it.webhookKey(wrongKey) }
                .unwrap(UnwrapWebhookParams.builder().body(payload).headers(headers).build())
        }

        // Bad signature should throw
        assertThrows<AnthropicWebhookException> {
            val badSig = webhook.sign(messageId, timestampSeconds, "some other payload")
            val badHeaders =
                headers.toBuilder().replace("webhook-signature", listOf(badSig)).build()
            webhookService.unwrap(
                UnwrapWebhookParams.builder()
                    .body(payload)
                    .headers(badHeaders)
                    .secret(webhookSecret)
                    .build()
            )
        }
        assertThrows<AnthropicWebhookException> {
            val badSig = webhook.sign(messageId, timestampSeconds, "some other payload")
            val badHeaders =
                headers.toBuilder().replace("webhook-signature", listOf(badSig)).build()
            webhookService
                .withOptions { it.webhookKey(webhookSecret) }
                .unwrap(UnwrapWebhookParams.builder().body(payload).headers(badHeaders).build())
        }

        // Old timestamp should throw
        assertThrows<AnthropicWebhookException> {
            val oldHeaders = headers.toBuilder().replace("webhook-timestamp", listOf("5")).build()
            webhookService.unwrap(
                UnwrapWebhookParams.builder()
                    .body(payload)
                    .headers(oldHeaders)
                    .secret(webhookSecret)
                    .build()
            )
        }
        assertThrows<AnthropicWebhookException> {
            val oldHeaders = headers.toBuilder().replace("webhook-timestamp", listOf("5")).build()
            webhookService
                .withOptions { it.webhookKey(webhookSecret) }
                .unwrap(UnwrapWebhookParams.builder().body(payload).headers(oldHeaders).build())
        }

        // Wrong message ID should throw
        assertThrows<AnthropicWebhookException> {
            val wrongIdHeaders = headers.toBuilder().replace("webhook-id", listOf("wrong")).build()
            webhookService.unwrap(
                UnwrapWebhookParams.builder()
                    .body(payload)
                    .headers(wrongIdHeaders)
                    .secret(webhookSecret)
                    .build()
            )
        }
        assertThrows<AnthropicWebhookException> {
            val wrongIdHeaders = headers.toBuilder().replace("webhook-id", listOf("wrong")).build()
            webhookService
                .withOptions { it.webhookKey(webhookSecret) }
                .unwrap(UnwrapWebhookParams.builder().body(payload).headers(wrongIdHeaders).build())
        }
    }
}
