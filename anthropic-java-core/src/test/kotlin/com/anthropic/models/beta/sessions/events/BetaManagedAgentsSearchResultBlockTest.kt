package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSearchResultBlockTest {

    @Test
    fun create() {
        val betaManagedAgentsSearchResultBlock =
            BetaManagedAgentsSearchResultBlock.builder()
                .citations(BetaManagedAgentsSearchResultCitations.of(true))
                .addContent(BetaManagedAgentsSearchResultContent.of("x"))
                .source("x")
                .title("x")
                .build()

        assertThat(betaManagedAgentsSearchResultBlock.citations())
            .isEqualTo(BetaManagedAgentsSearchResultCitations.of(true))
        assertThat(betaManagedAgentsSearchResultBlock.content())
            .containsExactly(BetaManagedAgentsSearchResultContent.of("x"))
        assertThat(betaManagedAgentsSearchResultBlock.source()).isEqualTo("x")
        assertThat(betaManagedAgentsSearchResultBlock.title()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSearchResultBlock =
            BetaManagedAgentsSearchResultBlock.builder()
                .citations(BetaManagedAgentsSearchResultCitations.of(true))
                .addContent(BetaManagedAgentsSearchResultContent.of("x"))
                .source("x")
                .title("x")
                .build()

        val roundtrippedBetaManagedAgentsSearchResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSearchResultBlock),
                jacksonTypeRef<BetaManagedAgentsSearchResultBlock>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSearchResultBlock)
            .isEqualTo(betaManagedAgentsSearchResultBlock)
    }
}
