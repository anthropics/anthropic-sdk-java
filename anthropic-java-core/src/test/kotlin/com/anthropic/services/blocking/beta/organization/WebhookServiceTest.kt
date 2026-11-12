package com.anthropic.services.blocking.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.webhooks.WebhookCreateParams
import com.anthropic.models.beta.organization.webhooks.WebhookDeleteParams
import com.anthropic.models.beta.organization.webhooks.WebhookRegenerateSigningSecretParams
import com.anthropic.models.beta.organization.webhooks.WebhookRetrieveParams
import com.anthropic.models.beta.organization.webhooks.WebhookUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class WebhookServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val webhookService =
            client.beta().organization().webhooks().withOptions { it.putQueryParam("beta", "true") }

        val betaNewOrganizationWebhookEndpoint =
            webhookService.create(
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
            )

        betaNewOrganizationWebhookEndpoint.validate()
    }

    @Test
    fun retrieve() {
        val webhookService =
            client.beta().organization().webhooks().withOptions { it.putQueryParam("beta", "true") }

        val betaOrganizationWebhookEndpoint =
            webhookService.retrieve(
                WebhookRetrieveParams.builder()
                    .webhookEndpointId("webhook_endpoint_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        betaOrganizationWebhookEndpoint.validate()
    }

    @Test
    fun update() {
        val webhookService =
            client.beta().organization().webhooks().withOptions { it.putQueryParam("beta", "true") }

        val betaOrganizationWebhookEndpoint =
            webhookService.update(
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
            )

        betaOrganizationWebhookEndpoint.validate()
    }

    @Test
    fun list() {
        val webhookService =
            client.beta().organization().webhooks().withOptions { it.putQueryParam("beta", "true") }

        val page = webhookService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val webhookService =
            client.beta().organization().webhooks().withOptions { it.putQueryParam("beta", "true") }

        val betaDeletedOrganizationWebhookEndpoint =
            webhookService.delete(
                WebhookDeleteParams.builder()
                    .webhookEndpointId("webhook_endpoint_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        betaDeletedOrganizationWebhookEndpoint.validate()
    }

    @Test
    fun regenerateSigningSecret() {
        val webhookService =
            client.beta().organization().webhooks().withOptions { it.putQueryParam("beta", "true") }

        val betaOrganizationWebhookEndpointSigningSecret =
            webhookService.regenerateSigningSecret(
                WebhookRegenerateSigningSecretParams.builder()
                    .webhookEndpointId("webhook_endpoint_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        betaOrganizationWebhookEndpointSigningSecret.validate()
    }
}
