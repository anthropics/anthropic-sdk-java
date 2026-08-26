// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.UnwrapWebhookParams
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.errors.AnthropicWebhookException
import com.anthropic.models.beta.webhooks.UnwrapWebhookEvent
import java.util.function.Consumer

interface WebhookService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): WebhookService

    /**
     * Parses a webhook payload into an event without verifying its signature. Prefer `unwrap()`
     * unless you have already verified the signature yourself.
     *
     * @throws AnthropicInvalidDataException if the body could not be parsed.
     */
    fun parseUnverified(body: String): UnwrapWebhookEvent

    /**
     * Verifies the webhook signature from the `webhook-id`, `webhook-timestamp` and
     * `webhook-signature` headers using your webhook signing key, then parses the payload into an
     * event. Fails if the signature is missing or invalid.
     *
     * @throws AnthropicWebhookException if the webhook signature could not be verified
     * @throws AnthropicInvalidDataException if the body could not be parsed.
     */
    fun unwrap(unwrapParams: UnwrapWebhookParams): UnwrapWebhookEvent

    /** A view of [WebhookService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): WebhookService.WithRawResponse
    }
}
