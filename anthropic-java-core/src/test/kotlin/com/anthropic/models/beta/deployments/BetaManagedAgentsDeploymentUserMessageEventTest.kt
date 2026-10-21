package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeploymentUserMessageEventTest {

    @Test
    fun create() {
        val betaManagedAgentsDeploymentUserMessageEvent =
            BetaManagedAgentsDeploymentUserMessageEvent.builder()
                .addTextContent("Where is my order #1234?")
                .build()

        assertThat(betaManagedAgentsDeploymentUserMessageEvent.content())
            .containsExactly(
                BetaManagedAgentsDeploymentUserMessageEvent.Content.ofText(
                    "Where is my order #1234?"
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentUserMessageEvent =
            BetaManagedAgentsDeploymentUserMessageEvent.builder()
                .addTextContent("Where is my order #1234?")
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
