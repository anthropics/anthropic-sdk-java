package com.anthropic.models.beta.organization.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOrganizationWebhookEndpointTest {

    @Test
    fun create() {
        val betaOrganizationWebhookEndpoint =
            BetaOrganizationWebhookEndpoint.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .description("description")
                .disabledReason("disabled_reason")
                .addEnabledEvent(
                    BetaOrganizationWebhookEndpoint.EnabledEvent
                        .USER_PROFILE_ENFORCEMENT_ACTION_OPENED
                )
                .name("name")
                .state(BetaOrganizationWebhookEndpointState.DISABLED)
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .url("url")
                .build()

        assertThat(betaOrganizationWebhookEndpoint.id()).isEqualTo("id")
        assertThat(betaOrganizationWebhookEndpoint.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaOrganizationWebhookEndpoint.description()).isEqualTo("description")
        assertThat(betaOrganizationWebhookEndpoint.disabledReason()).isEqualTo("disabled_reason")
        assertThat(betaOrganizationWebhookEndpoint.enabledEvents())
            .containsExactly(
                BetaOrganizationWebhookEndpoint.EnabledEvent.USER_PROFILE_ENFORCEMENT_ACTION_OPENED
            )
        assertThat(betaOrganizationWebhookEndpoint.name()).isEqualTo("name")
        assertThat(betaOrganizationWebhookEndpoint.state())
            .isEqualTo(BetaOrganizationWebhookEndpointState.DISABLED)
        assertThat(betaOrganizationWebhookEndpoint.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaOrganizationWebhookEndpoint.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOrganizationWebhookEndpoint =
            BetaOrganizationWebhookEndpoint.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .description("description")
                .disabledReason("disabled_reason")
                .addEnabledEvent(
                    BetaOrganizationWebhookEndpoint.EnabledEvent
                        .USER_PROFILE_ENFORCEMENT_ACTION_OPENED
                )
                .name("name")
                .state(BetaOrganizationWebhookEndpointState.DISABLED)
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .url("url")
                .build()

        val roundtrippedBetaOrganizationWebhookEndpoint =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOrganizationWebhookEndpoint),
                jacksonTypeRef<BetaOrganizationWebhookEndpoint>(),
            )

        assertThat(roundtrippedBetaOrganizationWebhookEndpoint)
            .isEqualTo(betaOrganizationWebhookEndpoint)
    }
}
