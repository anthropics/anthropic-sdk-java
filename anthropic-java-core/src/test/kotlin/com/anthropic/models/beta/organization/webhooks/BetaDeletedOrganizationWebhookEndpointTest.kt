package com.anthropic.models.beta.organization.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDeletedOrganizationWebhookEndpointTest {

    @Test
    fun create() {
        val betaDeletedOrganizationWebhookEndpoint = BetaDeletedOrganizationWebhookEndpoint.of("id")

        assertThat(betaDeletedOrganizationWebhookEndpoint.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDeletedOrganizationWebhookEndpoint = BetaDeletedOrganizationWebhookEndpoint.of("id")

        val roundtrippedBetaDeletedOrganizationWebhookEndpoint =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDeletedOrganizationWebhookEndpoint),
                jacksonTypeRef<BetaDeletedOrganizationWebhookEndpoint>(),
            )

        assertThat(roundtrippedBetaDeletedOrganizationWebhookEndpoint)
            .isEqualTo(betaDeletedOrganizationWebhookEndpoint)
    }
}
