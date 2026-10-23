package com.anthropic.services.async.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.webhooks.WebhookCreateParams
import com.anthropic.models.beta.organization.webhooks.WebhookDeleteParams
import com.anthropic.models.beta.organization.webhooks.WebhookRegenerateSigningSecretParams
import com.anthropic.models.beta.organization.webhooks.WebhookRetrieveParams
import com.anthropic.models.beta.organization.webhooks.WebhookUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class WebhookServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val webhookServiceAsync =
            client.beta().organization().webhooks().withOptions { it.putQueryParam("beta", "true") }

        val betaNewOrganizationWebhookEndpointFuture =
            webhookServiceAsync.create(
                WebhookCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .addEnabledEvent(
                        WebhookCreateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_OPENED
                    )
                    .url("x")
                    .description("description")
                    .name("name")
                    .build()
            )

        val betaNewOrganizationWebhookEndpoint = betaNewOrganizationWebhookEndpointFuture.get()
        betaNewOrganizationWebhookEndpoint.validate()
    }

    @Test
    fun retrieve() {
        val webhookServiceAsync =
            client.beta().organization().webhooks().withOptions { it.putQueryParam("beta", "true") }

        val betaOrganizationWebhookEndpointFuture =
            webhookServiceAsync.retrieve(
                WebhookRetrieveParams.builder()
                    .webhookEndpointId("webhook_endpoint_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaOrganizationWebhookEndpoint = betaOrganizationWebhookEndpointFuture.get()
        betaOrganizationWebhookEndpoint.validate()
    }

    @Test
    fun update() {
        val webhookServiceAsync =
            client.beta().organization().webhooks().withOptions { it.putQueryParam("beta", "true") }

        val betaOrganizationWebhookEndpointFuture =
            webhookServiceAsync.update(
                WebhookUpdateParams.builder()
                    .webhookEndpointId("webhook_endpoint_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .description("description")
                    .addEnabledEvent(
                        WebhookUpdateParams.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_OPENED
                    )
                    .name("name")
                    .state(WebhookUpdateParams.State.DISABLED)
                    .url("x")
                    .build()
            )

        val betaOrganizationWebhookEndpoint = betaOrganizationWebhookEndpointFuture.get()
        betaOrganizationWebhookEndpoint.validate()
    }

    @Test
    fun list() {
        val webhookServiceAsync =
            client.beta().organization().webhooks().withOptions { it.putQueryParam("beta", "true") }

        val pageFuture = webhookServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val webhookServiceAsync =
            client.beta().organization().webhooks().withOptions { it.putQueryParam("beta", "true") }

        val betaDeletedOrganizationWebhookEndpointFuture =
            webhookServiceAsync.delete(
                WebhookDeleteParams.builder()
                    .webhookEndpointId("webhook_endpoint_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaDeletedOrganizationWebhookEndpoint =
            betaDeletedOrganizationWebhookEndpointFuture.get()
        betaDeletedOrganizationWebhookEndpoint.validate()
    }

    @Test
    fun regenerateSigningSecret() {
        val webhookServiceAsync =
            client.beta().organization().webhooks().withOptions { it.putQueryParam("beta", "true") }

        val betaOrganizationWebhookEndpointSigningSecretFuture =
            webhookServiceAsync.regenerateSigningSecret(
                WebhookRegenerateSigningSecretParams.builder()
                    .webhookEndpointId("webhook_endpoint_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaOrganizationWebhookEndpointSigningSecret =
            betaOrganizationWebhookEndpointSigningSecretFuture.get()
        betaOrganizationWebhookEndpointSigningSecret.validate()
    }
}
