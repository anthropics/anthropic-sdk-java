// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsTextBlock
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeploymentUserMessageEventTest {

    @Test
    fun create() {
        val betaManagedAgentsDeploymentUserMessageEvent =
            BetaManagedAgentsDeploymentUserMessageEvent.builder()
                .addTextContent("Where is my order #1234?")
                .type(BetaManagedAgentsDeploymentUserMessageEvent.Type.USER_MESSAGE)
                .build()

        assertThat(betaManagedAgentsDeploymentUserMessageEvent.content())
            .containsExactly(
                BetaManagedAgentsDeploymentUserMessageEvent.Content.ofText(
                    BetaManagedAgentsTextBlock.builder()
                        .text("Where is my order #1234?")
                        .type(BetaManagedAgentsTextBlock.Type.TEXT)
                        .build()
                )
            )
        assertThat(betaManagedAgentsDeploymentUserMessageEvent.type())
            .isEqualTo(BetaManagedAgentsDeploymentUserMessageEvent.Type.USER_MESSAGE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentUserMessageEvent =
            BetaManagedAgentsDeploymentUserMessageEvent.builder()
                .addTextContent("Where is my order #1234?")
                .type(BetaManagedAgentsDeploymentUserMessageEvent.Type.USER_MESSAGE)
                .build()

        val roundtrippedBetaManagedAgentsDeploymentUserMessageEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentUserMessageEvent),
                jacksonTypeRef<BetaManagedAgentsDeploymentUserMessageEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentUserMessageEvent)
            .isEqualTo(betaManagedAgentsDeploymentUserMessageEvent)
    }
}
