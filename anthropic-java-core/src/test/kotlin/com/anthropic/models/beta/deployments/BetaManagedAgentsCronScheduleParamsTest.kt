// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsCronScheduleParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsCronScheduleParams =
            BetaManagedAgentsCronScheduleParams.builder()
                .expression("0 9 * * 1-5")
                .timezone("America/Los_Angeles")
                .type(BetaManagedAgentsCronScheduleParams.Type.CRON)
                .build()

        assertThat(betaManagedAgentsCronScheduleParams.expression()).isEqualTo("0 9 * * 1-5")
        assertThat(betaManagedAgentsCronScheduleParams.timezone()).isEqualTo("America/Los_Angeles")
        assertThat(betaManagedAgentsCronScheduleParams.type())
            .isEqualTo(BetaManagedAgentsCronScheduleParams.Type.CRON)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCronScheduleParams =
            BetaManagedAgentsCronScheduleParams.builder()
                .expression("0 9 * * 1-5")
                .timezone("America/Los_Angeles")
                .type(BetaManagedAgentsCronScheduleParams.Type.CRON)
                .build()

        val roundtrippedBetaManagedAgentsCronScheduleParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsCronScheduleParams),
                jacksonTypeRef<BetaManagedAgentsCronScheduleParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsCronScheduleParams)
            .isEqualTo(betaManagedAgentsCronScheduleParams)
    }
}
