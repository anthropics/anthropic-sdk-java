package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebFetchUrlSourcesTest {

    @Test
    fun create() {
        val betaWebFetchUrlSources =
            BetaWebFetchUrlSources.builder()
                .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                .userInput(BetaWebFetchUrlSourceAll.builder().build())
                .build()

        assertThat(betaWebFetchUrlSources.clientToolResults())
            .contains(
                BetaWebFetchUrlSources.ClientToolResults.ofAll(
                    BetaWebFetchUrlSourceAll.builder().build()
                )
            )
        assertThat(betaWebFetchUrlSources.serverToolResults())
            .contains(
                BetaWebFetchUrlSources.ServerToolResults.ofAll(
                    BetaWebFetchUrlSourceAll.builder().build()
                )
            )
        assertThat(betaWebFetchUrlSources.userInput())
            .contains(
                BetaWebFetchUrlSources.UserInput.ofAll(BetaWebFetchUrlSourceAll.builder().build())
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebFetchUrlSources =
            BetaWebFetchUrlSources.builder()
                .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                .userInput(BetaWebFetchUrlSourceAll.builder().build())
                .build()

        val roundtrippedBetaWebFetchUrlSources =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebFetchUrlSources),
                jacksonTypeRef<BetaWebFetchUrlSources>(),
            )

        assertThat(roundtrippedBetaWebFetchUrlSources).isEqualTo(betaWebFetchUrlSources)
    }
}
