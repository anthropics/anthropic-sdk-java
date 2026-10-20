package com.anthropic.models.beta.organization.webhooks

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebhookListPageResponseTest {

    @Test
    fun create() {
        val webhookListPageResponse =
            WebhookListPageResponse.builder()
                .addData(
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
                )
                .hasMore(true)
                .firstId("first_id")
                .lastId("last_id")
                .build()

        assertThat(webhookListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(webhookListPageResponse.hasMore()).isEqualTo(true)
        assertThat(webhookListPageResponse.firstId()).contains("first_id")
        assertThat(webhookListPageResponse.lastId()).contains("last_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webhookListPageResponse =
            WebhookListPageResponse.builder()
                .addData(
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
                )
                .hasMore(true)
                .firstId("first_id")
                .lastId("last_id")
                .build()

        val roundtrippedWebhookListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webhookListPageResponse),
                jacksonTypeRef<WebhookListPageResponse>(),
            )

        assertThat(roundtrippedWebhookListPageResponse).isEqualTo(webhookListPageResponse)
    }
}
