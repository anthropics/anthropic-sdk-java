package com.anthropic.models.beta.organization.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOrganizationWebhookEndpointSigningSecretTest {

    @Test
    fun create() {
        val betaOrganizationWebhookEndpointSigningSecret =
            BetaOrganizationWebhookEndpointSigningSecret.of("signing_secret")

        assertThat(betaOrganizationWebhookEndpointSigningSecret.signingSecret())
            .isEqualTo("signing_secret")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOrganizationWebhookEndpointSigningSecret =
            BetaOrganizationWebhookEndpointSigningSecret.of("signing_secret")

        val roundtrippedBetaOrganizationWebhookEndpointSigningSecret =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOrganizationWebhookEndpointSigningSecret),
                jacksonTypeRef<BetaOrganizationWebhookEndpointSigningSecret>(),
            )

        assertThat(roundtrippedBetaOrganizationWebhookEndpointSigningSecret)
            .isEqualTo(betaOrganizationWebhookEndpointSigningSecret)
    }
}
