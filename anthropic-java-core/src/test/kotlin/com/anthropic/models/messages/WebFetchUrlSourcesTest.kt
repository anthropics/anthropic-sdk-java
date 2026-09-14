package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebFetchUrlSourcesTest {

    @Test
    fun create() {
        val webFetchUrlSources =
            WebFetchUrlSources.builder()
                .clientToolResults(WebFetchUrlSourceAll.builder().build())
                .serverToolResults(WebFetchUrlSourceAll.builder().build())
                .userInput(WebFetchUrlSourceAll.builder().build())
                .build()

        assertThat(webFetchUrlSources.clientToolResults())
            .contains(
                WebFetchUrlSources.ClientToolResults.ofAll(WebFetchUrlSourceAll.builder().build())
            )
        assertThat(webFetchUrlSources.serverToolResults())
            .contains(
                WebFetchUrlSources.ServerToolResults.ofAll(WebFetchUrlSourceAll.builder().build())
            )
        assertThat(webFetchUrlSources.userInput())
            .contains(WebFetchUrlSources.UserInput.ofAll(WebFetchUrlSourceAll.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webFetchUrlSources =
            WebFetchUrlSources.builder()
                .clientToolResults(WebFetchUrlSourceAll.builder().build())
                .serverToolResults(WebFetchUrlSourceAll.builder().build())
                .userInput(WebFetchUrlSourceAll.builder().build())
                .build()

        val roundtrippedWebFetchUrlSources =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webFetchUrlSources),
                jacksonTypeRef<WebFetchUrlSources>(),
            )

        assertThat(roundtrippedWebFetchUrlSources).isEqualTo(webFetchUrlSources)
    }
}
