package com.anthropic.models.beta.organization.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaNewOrganizationWebhookEndpointTest {

    @Test
    fun create() {
        val betaNewOrganizationWebhookEndpoint =
            BetaNewOrganizationWebhookEndpoint.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .description("description")
                .disabledReason("disabled_reason")
                .addEnabledEvent(
                    BetaNewOrganizationWebhookEndpoint.EnabledEvent
                        .USER_PROFILE_ENFORCEMENT_ACTION_OPENED
                )
                .name("name")
                .signingSecret("signing_secret")
                .state(BetaOrganizationWebhookEndpointState.DISABLED)
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .url("url")
                .build()

        assertThat(betaNewOrganizationWebhookEndpoint.id()).isEqualTo("id")
        assertThat(betaNewOrganizationWebhookEndpoint.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaNewOrganizationWebhookEndpoint.description()).isEqualTo("description")
        assertThat(betaNewOrganizationWebhookEndpoint.disabledReason()).isEqualTo("disabled_reason")
        assertThat(betaNewOrganizationWebhookEndpoint.enabledEvents())
            .containsExactly(
                BetaNewOrganizationWebhookEndpoint.EnabledEvent
                    .USER_PROFILE_ENFORCEMENT_ACTION_OPENED
            )
        assertThat(betaNewOrganizationWebhookEndpoint.name()).isEqualTo("name")
        assertThat(betaNewOrganizationWebhookEndpoint.signingSecret()).isEqualTo("signing_secret")
        assertThat(betaNewOrganizationWebhookEndpoint.state())
            .isEqualTo(BetaOrganizationWebhookEndpointState.DISABLED)
        assertThat(betaNewOrganizationWebhookEndpoint.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaNewOrganizationWebhookEndpoint.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaNewOrganizationWebhookEndpoint =
            BetaNewOrganizationWebhookEndpoint.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .description("description")
                .disabledReason("disabled_reason")
                .addEnabledEvent(
                    BetaNewOrganizationWebhookEndpoint.EnabledEvent
                        .USER_PROFILE_ENFORCEMENT_ACTION_OPENED
                )
                .name("name")
                .signingSecret("signing_secret")
                .state(BetaOrganizationWebhookEndpointState.DISABLED)
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .url("url")
                .build()

        val roundtrippedBetaNewOrganizationWebhookEndpoint =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaNewOrganizationWebhookEndpoint),
                jacksonTypeRef<BetaNewOrganizationWebhookEndpoint>(),
            )

        assertThat(roundtrippedBetaNewOrganizationWebhookEndpoint)
            .isEqualTo(betaNewOrganizationWebhookEndpoint)
    }
}
