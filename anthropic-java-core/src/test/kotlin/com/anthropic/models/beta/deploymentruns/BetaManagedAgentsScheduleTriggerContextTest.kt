// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsScheduleTriggerContextTest {

    @Test
    fun create() {
        val betaManagedAgentsScheduleTriggerContext =
            BetaManagedAgentsScheduleTriggerContext.builder()
                .scheduledAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsScheduleTriggerContext.Type.SCHEDULE)
                .build()

        assertThat(betaManagedAgentsScheduleTriggerContext.scheduledAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsScheduleTriggerContext.type())
            .isEqualTo(BetaManagedAgentsScheduleTriggerContext.Type.SCHEDULE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsScheduleTriggerContext =
            BetaManagedAgentsScheduleTriggerContext.builder()
                .scheduledAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .type(BetaManagedAgentsScheduleTriggerContext.Type.SCHEDULE)
                .build()

        val roundtrippedBetaManagedAgentsScheduleTriggerContext =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsScheduleTriggerContext),
                jacksonTypeRef<BetaManagedAgentsScheduleTriggerContext>(),
            )

        assertThat(roundtrippedBetaManagedAgentsScheduleTriggerContext)
            .isEqualTo(betaManagedAgentsScheduleTriggerContext)
    }
}
