package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsScheduleParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsScheduleParams =
            BetaManagedAgentsScheduleParams.builder()
                .expression("0 9 * * 1-5")
                .timezone("America/Los_Angeles")
                .build()

        assertThat(betaManagedAgentsScheduleParams.expression()).isEqualTo("0 9 * * 1-5")
        assertThat(betaManagedAgentsScheduleParams.timezone()).isEqualTo("America/Los_Angeles")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsScheduleParams =
            BetaManagedAgentsScheduleParams.builder()
                .expression("0 9 * * 1-5")
                .timezone("America/Los_Angeles")
                .build()

        val roundtrippedBetaManagedAgentsScheduleParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsScheduleParams),
                jacksonTypeRef<BetaManagedAgentsScheduleParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsScheduleParams)
            .isEqualTo(betaManagedAgentsScheduleParams)
    }
}
