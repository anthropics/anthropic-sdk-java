// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.UnwrapWebhookParams
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.errors.AnthropicWebhookException
import com.anthropic.models.beta.webhooks.UnwrapWebhookEvent
import java.util.function.Consumer

interface WebhookServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): WebhookServiceAsync

    /**
     * Unwraps a webhook event from its JSON representation.
     *
     * @throws AnthropicInvalidDataException if the body could not be parsed.
     */
    fun unwrap(body: String): UnwrapWebhookEvent

    /**
     * Unwraps a webhook event from its JSON representation.
     *
     * @throws AnthropicInvalidDataException if the body could not be parsed.
     * @throws AnthropicWebhookException if the webhook signature could not be verified
     */
    fun unwrap(unwrapParams: UnwrapWebhookParams): UnwrapWebhookEvent

    /**
     * A view of [WebhookServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): WebhookServiceAsync.WithRawResponse
    }
}
