// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.sessions.BetaManagedAgentsSystemContentBlock
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeploymentSystemMessageEventTest {

    @Test
    fun create() {
        val betaManagedAgentsDeploymentSystemMessageEvent =
            BetaManagedAgentsDeploymentSystemMessageEvent.builder()
                .addTextContent("Where is my order #1234?")
                .type(BetaManagedAgentsDeploymentSystemMessageEvent.Type.SYSTEM_MESSAGE)
                .build()

        assertThat(betaManagedAgentsDeploymentSystemMessageEvent.content())
            .containsExactly(
                BetaManagedAgentsSystemContentBlock.builder()
                    .text("Where is my order #1234?")
                    .type(BetaManagedAgentsSystemContentBlock.Type.TEXT)
                    .build()
            )
        assertThat(betaManagedAgentsDeploymentSystemMessageEvent.type())
            .isEqualTo(BetaManagedAgentsDeploymentSystemMessageEvent.Type.SYSTEM_MESSAGE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentSystemMessageEvent =
            BetaManagedAgentsDeploymentSystemMessageEvent.builder()
                .addTextContent("Where is my order #1234?")
                .type(BetaManagedAgentsDeploymentSystemMessageEvent.Type.SYSTEM_MESSAGE)
                .build()

        val roundtrippedBetaManagedAgentsDeploymentSystemMessageEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentSystemMessageEvent),
                jacksonTypeRef<BetaManagedAgentsDeploymentSystemMessageEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentSystemMessageEvent)
            .isEqualTo(betaManagedAgentsDeploymentSystemMessageEvent)
    }
}
