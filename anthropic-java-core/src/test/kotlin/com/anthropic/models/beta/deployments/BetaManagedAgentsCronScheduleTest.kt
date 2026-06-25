// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsCronScheduleTest {

    @Test
    fun create() {
        val betaManagedAgentsCronSchedule =
            BetaManagedAgentsCronSchedule.builder()
                .expression("0 9 * * 1-5")
                .timezone("America/Los_Angeles")
                .type(BetaManagedAgentsCronSchedule.Type.CRON)
                .lastRunAt(OffsetDateTime.parse("2026-03-16T16:00:09Z"))
                .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-17T16:00:00Z"))
                .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-18T16:00:00Z"))
                .build()

        assertThat(betaManagedAgentsCronSchedule.expression()).isEqualTo("0 9 * * 1-5")
        assertThat(betaManagedAgentsCronSchedule.timezone()).isEqualTo("America/Los_Angeles")
        assertThat(betaManagedAgentsCronSchedule.type())
            .isEqualTo(BetaManagedAgentsCronSchedule.Type.CRON)
        assertThat(betaManagedAgentsCronSchedule.lastRunAt())
            .contains(OffsetDateTime.parse("2026-03-16T16:00:09Z"))
        assertThat(betaManagedAgentsCronSchedule.upcomingRunsAt().getOrNull())
            .containsExactly(
                OffsetDateTime.parse("2026-03-17T16:00:00Z"),
                OffsetDateTime.parse("2026-03-18T16:00:00Z"),
            )
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsCronSchedule =
            BetaManagedAgentsCronSchedule.builder()
                .expression("0 9 * * 1-5")
                .timezone("America/Los_Angeles")
                .type(BetaManagedAgentsCronSchedule.Type.CRON)
                .build()

        val betaManagedAgentsCronSchedule =
            baseBetaManagedAgentsCronSchedule
                .toBuilder()
                .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-17T16:00:00Z"))
                .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-18T16:00:00Z"))
                .build()

        assertThat(betaManagedAgentsCronSchedule.upcomingRunsAt().getOrNull())
            .containsExactly(
                OffsetDateTime.parse("2026-03-17T16:00:00Z"),
                OffsetDateTime.parse("2026-03-18T16:00:00Z"),
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCronSchedule =
            BetaManagedAgentsCronSchedule.builder()
                .expression("0 9 * * 1-5")
                .timezone("America/Los_Angeles")
                .type(BetaManagedAgentsCronSchedule.Type.CRON)
                .lastRunAt(OffsetDateTime.parse("2026-03-16T16:00:09Z"))
                .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-17T16:00:00Z"))
                .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-18T16:00:00Z"))
                .build()

        val roundtrippedBetaManagedAgentsCronSchedule =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsCronSchedule),
                jacksonTypeRef<BetaManagedAgentsCronSchedule>(),
            )

        assertThat(roundtrippedBetaManagedAgentsCronSchedule)
            .isEqualTo(betaManagedAgentsCronSchedule)
    }
}
