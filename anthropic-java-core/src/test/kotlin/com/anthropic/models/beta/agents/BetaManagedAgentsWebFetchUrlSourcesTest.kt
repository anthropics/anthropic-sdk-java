package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWebFetchUrlSourcesTest {

    @Test
    fun create() {
        val betaManagedAgentsWebFetchUrlSources =
            BetaManagedAgentsWebFetchUrlSources.builder()
                .clientToolResults(BetaManagedAgentsWebFetchUrlSourceAll.builder().build())
                .serverToolResults(BetaManagedAgentsWebFetchUrlSourceAll.builder().build())
                .userInput(BetaManagedAgentsWebFetchUrlSourceAll.builder().build())
                .build()

        assertThat(betaManagedAgentsWebFetchUrlSources.clientToolResults())
            .contains(
                BetaManagedAgentsWebFetchUrlSourceToolFilter.ofAll(
                    BetaManagedAgentsWebFetchUrlSourceAll.builder().build()
                )
            )
        assertThat(betaManagedAgentsWebFetchUrlSources.serverToolResults())
            .contains(
                BetaManagedAgentsWebFetchUrlSourceToolFilter.ofAll(
                    BetaManagedAgentsWebFetchUrlSourceAll.builder().build()
                )
            )
        assertThat(betaManagedAgentsWebFetchUrlSources.userInput())
            .contains(
                BetaManagedAgentsWebFetchUrlSourceUserInput.ofAll(
                    BetaManagedAgentsWebFetchUrlSourceAll.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWebFetchUrlSources =
            BetaManagedAgentsWebFetchUrlSources.builder()
                .clientToolResults(BetaManagedAgentsWebFetchUrlSourceAll.builder().build())
                .serverToolResults(BetaManagedAgentsWebFetchUrlSourceAll.builder().build())
                .userInput(BetaManagedAgentsWebFetchUrlSourceAll.builder().build())
                .build()

        val roundtrippedBetaManagedAgentsWebFetchUrlSources =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWebFetchUrlSources),
                jacksonTypeRef<BetaManagedAgentsWebFetchUrlSources>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWebFetchUrlSources)
            .isEqualTo(betaManagedAgentsWebFetchUrlSources)
    }
}
