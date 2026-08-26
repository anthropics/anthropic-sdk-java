// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.UnwrapWebhookParams
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.errors.AnthropicWebhookException
import com.anthropic.models.beta.webhooks.UnwrapWebhookEvent
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.standardwebhooks.Webhook
import com.standardwebhooks.exceptions.WebhookVerificationException
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class WebhookServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    WebhookService {

    private val withRawResponse: WebhookService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): WebhookService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): WebhookService =
        WebhookServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun parseUnverified(body: String): UnwrapWebhookEvent =
        try {
            clientOptions.jsonMapper.readValue(body, jacksonTypeRef<UnwrapWebhookEvent>())
        } catch (e: Exception) {
            throw AnthropicInvalidDataException("Error parsing body", e)
        }

    override fun unwrap(unwrapParams: UnwrapWebhookParams): UnwrapWebhookEvent {
        val headers = unwrapParams.headers()
        try {
            val webhookSecret =
                checkRequired(
                    "webhookKey",
                    unwrapParams.secret().getOrNull() ?: clientOptions.webhookKey().getOrNull(),
                )
            checkRequired("webhookKey", webhookSecret.isNotEmpty())

            val headersMap = headers.names().associateWith { name -> headers.values(name) }.toMap()

            val webhook = Webhook(webhookSecret)
            webhook.verify(unwrapParams.body(), headersMap)
        } catch (e: WebhookVerificationException) {
            throw AnthropicWebhookException("Could not verify webhook event signature", e)
        }
        return try {
            clientOptions.jsonMapper.readValue(
                unwrapParams.body(),
                jacksonTypeRef<UnwrapWebhookEvent>(),
            )
        } catch (e: Exception) {
            throw AnthropicInvalidDataException("Error parsing body", e)
        }
    }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        WebhookService.WithRawResponse {

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): WebhookService.WithRawResponse =
            WebhookServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )
    }
}
