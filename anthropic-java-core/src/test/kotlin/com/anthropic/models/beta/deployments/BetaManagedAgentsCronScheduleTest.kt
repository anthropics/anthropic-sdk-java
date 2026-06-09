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
                .expression("x")
                .timezone("x")
                .type(BetaManagedAgentsCronSchedule.Type.CRON)
                .lastRunAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addUpcomingRunsAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaManagedAgentsCronSchedule.expression()).isEqualTo("x")
        assertThat(betaManagedAgentsCronSchedule.timezone()).isEqualTo("x")
        assertThat(betaManagedAgentsCronSchedule.type())
            .isEqualTo(BetaManagedAgentsCronSchedule.Type.CRON)
        assertThat(betaManagedAgentsCronSchedule.lastRunAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsCronSchedule.upcomingRunsAt().getOrNull())
            .containsExactly(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsCronSchedule =
            BetaManagedAgentsCronSchedule.builder()
                .expression("x")
                .timezone("x")
                .type(BetaManagedAgentsCronSchedule.Type.CRON)
                .build()

        val betaManagedAgentsCronSchedule =
            baseBetaManagedAgentsCronSchedule
                .toBuilder()
                .addUpcomingRunsAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaManagedAgentsCronSchedule.upcomingRunsAt().getOrNull())
            .containsExactly(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCronSchedule =
            BetaManagedAgentsCronSchedule.builder()
                .expression("x")
                .timezone("x")
                .type(BetaManagedAgentsCronSchedule.Type.CRON)
                .lastRunAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addUpcomingRunsAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
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
