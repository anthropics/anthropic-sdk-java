package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWebFetchUrlSourcesParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsWebFetchUrlSourcesParams =
            BetaManagedAgentsWebFetchUrlSourcesParams.builder()
                .clientToolResults(BetaManagedAgentsWebFetchUrlSourceShorthand.ALL)
                .serverToolResults(BetaManagedAgentsWebFetchUrlSourceShorthand.ALL)
                .userInput(BetaManagedAgentsWebFetchUrlSourceShorthand.ALL)
                .build()

        assertThat(betaManagedAgentsWebFetchUrlSourcesParams.clientToolResults())
            .contains(
                BetaManagedAgentsWebFetchUrlSourceToolFilterParams.ofShorthand(
                    BetaManagedAgentsWebFetchUrlSourceShorthand.ALL
                )
            )
        assertThat(betaManagedAgentsWebFetchUrlSourcesParams.serverToolResults())
            .contains(
                BetaManagedAgentsWebFetchUrlSourceToolFilterParams.ofShorthand(
                    BetaManagedAgentsWebFetchUrlSourceShorthand.ALL
                )
            )
        assertThat(betaManagedAgentsWebFetchUrlSourcesParams.userInput())
            .contains(
                BetaManagedAgentsWebFetchUrlSourceUserInputParams.ofShorthand(
                    BetaManagedAgentsWebFetchUrlSourceShorthand.ALL
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchUrlSourcesParams =
            BetaManagedAgentsWebFetchUrlSourcesParams.builder()
                .clientToolResults(BetaManagedAgentsWebFetchUrlSourceShorthand.ALL)
                .serverToolResults(BetaManagedAgentsWebFetchUrlSourceShorthand.ALL)
                .userInput(BetaManagedAgentsWebFetchUrlSourceShorthand.ALL)
                .build()

        val roundtrippedBetaManagedAgentsWebFetchUrlSourcesParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchUrlSourcesParams),
                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSourcesParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchUrlSourcesParams)
            .isEqualTo(betaManagedAgentsWebFetchUrlSourcesParams)
    }
}
