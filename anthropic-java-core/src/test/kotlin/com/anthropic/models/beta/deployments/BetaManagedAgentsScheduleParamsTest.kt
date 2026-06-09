// File generated from our OpenAPI spec by Stainless.

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
                .expression("x")
                .timezone("x")
                .type(BetaManagedAgentsScheduleParams.Type.CRON)
                .build()

        assertThat(betaManagedAgentsScheduleParams.expression()).isEqualTo("x")
        assertThat(betaManagedAgentsScheduleParams.timezone()).isEqualTo("x")
        assertThat(betaManagedAgentsScheduleParams.type())
            .isEqualTo(BetaManagedAgentsScheduleParams.Type.CRON)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsScheduleParams =
            BetaManagedAgentsScheduleParams.builder()
                .expression("x")
                .timezone("x")
                .type(BetaManagedAgentsScheduleParams.Type.CRON)
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
