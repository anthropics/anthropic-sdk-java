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
                .expression("x")
                .timezone("x")
                .type(BetaManagedAgentsSchedule.Type.CRON)
                .lastRunAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addUpcomingRunsAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaManagedAgentsSchedule.expression()).isEqualTo("x")
        assertThat(betaManagedAgentsSchedule.timezone()).isEqualTo("x")
        assertThat(betaManagedAgentsSchedule.type()).isEqualTo(BetaManagedAgentsSchedule.Type.CRON)
        assertThat(betaManagedAgentsSchedule.lastRunAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsSchedule.upcomingRunsAt().getOrNull())
            .containsExactly(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsSchedule =
            BetaManagedAgentsSchedule.builder()
                .expression("x")
                .timezone("x")
                .type(BetaManagedAgentsSchedule.Type.CRON)
                .build()

        val betaManagedAgentsSchedule =
            baseBetaManagedAgentsSchedule
                .toBuilder()
                .addUpcomingRunsAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaManagedAgentsSchedule.upcomingRunsAt().getOrNull())
            .containsExactly(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSchedule =
            BetaManagedAgentsSchedule.builder()
                .expression("x")
                .timezone("x")
                .type(BetaManagedAgentsSchedule.Type.CRON)
                .lastRunAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addUpcomingRunsAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaManagedAgentsSchedule =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSchedule),
                jacksonTypeRef<BetaManagedAgentsSchedule>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSchedule).isEqualTo(betaManagedAgentsSchedule)
    }
}
