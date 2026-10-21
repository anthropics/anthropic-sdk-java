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
                .addContent(BetaManagedAgentsSystemContentBlock.of("Where is my order #1234?"))
                .build()

        assertThat(betaManagedAgentsDeploymentSystemMessageEvent.content())
            .containsExactly(BetaManagedAgentsSystemContentBlock.of("Where is my order #1234?"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentSystemMessageEvent =
            BetaManagedAgentsDeploymentSystemMessageEvent.builder()
                .addContent(BetaManagedAgentsSystemContentBlock.of("Where is my order #1234?"))
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
