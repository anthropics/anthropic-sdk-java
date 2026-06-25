// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsScheduleTest {

    @Test
    fun create() {
        val betaManagedAgentsSchedule =
            BetaManagedAgentsSchedule.builder()
                .expression("0 9 * * 1-5")
                .timezone("America/Los_Angeles")
                .type(BetaManagedAgentsSchedule.Type.CRON)
                .lastRunAt(OffsetDateTime.parse("2026-03-16T16:00:09Z"))
                .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-17T16:00:00Z"))
                .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-18T16:00:00Z"))
                .build()

        assertThat(betaManagedAgentsSchedule.expression()).isEqualTo("0 9 * * 1-5")
        assertThat(betaManagedAgentsSchedule.timezone()).isEqualTo("America/Los_Angeles")
        assertThat(betaManagedAgentsSchedule.type()).isEqualTo(BetaManagedAgentsSchedule.Type.CRON)
        assertThat(betaManagedAgentsSchedule.lastRunAt())
            .contains(OffsetDateTime.parse("2026-03-16T16:00:09Z"))
        assertThat(betaManagedAgentsSchedule.upcomingRunsAt().getOrNull())
            .containsExactly(
                OffsetDateTime.parse("2026-03-17T16:00:00Z"),
                OffsetDateTime.parse("2026-03-18T16:00:00Z"),
            )
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsSchedule =
            BetaManagedAgentsSchedule.builder()
                .expression("0 9 * * 1-5")
                .timezone("America/Los_Angeles")
                .type(BetaManagedAgentsSchedule.Type.CRON)
                .build()

        val betaManagedAgentsSchedule =
            baseBetaManagedAgentsSchedule
                .toBuilder()
                .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-17T16:00:00Z"))
                .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-18T16:00:00Z"))
                .build()

        assertThat(betaManagedAgentsSchedule.upcomingRunsAt().getOrNull())
            .containsExactly(
                OffsetDateTime.parse("2026-03-17T16:00:00Z"),
                OffsetDateTime.parse("2026-03-18T16:00:00Z"),
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSchedule =
            BetaManagedAgentsSchedule.builder()
                .expression("0 9 * * 1-5")
                .timezone("America/Los_Angeles")
                .type(BetaManagedAgentsSchedule.Type.CRON)
                .lastRunAt(OffsetDateTime.parse("2026-03-16T16:00:09Z"))
                .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-17T16:00:00Z"))
                .addUpcomingRunsAt(OffsetDateTime.parse("2026-03-18T16:00:00Z"))
                .build()

        val roundtrippedBetaManagedAgentsSchedule =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSchedule),
                jacksonTypeRef<BetaManagedAgentsSchedule>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSchedule).isEqualTo(betaManagedAgentsSchedule)
    }
}
