// File generated from our OpenAPI spec by Stainless.

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
                .citations(BetaManagedAgentsSearchResultCitations.builder().enabled(true).build())
                .addContent(
                    BetaManagedAgentsSearchResultContent.builder()
                        .text("x")
                        .type(BetaManagedAgentsSearchResultContent.Type.TEXT)
                        .build()
                )
                .source("x")
                .title("x")
                .type(BetaManagedAgentsSearchResultBlock.Type.SEARCH_RESULT)
                .build()

        assertThat(betaManagedAgentsSearchResultBlock.citations())
            .isEqualTo(BetaManagedAgentsSearchResultCitations.builder().enabled(true).build())
        assertThat(betaManagedAgentsSearchResultBlock.content())
            .containsExactly(
                BetaManagedAgentsSearchResultContent.builder()
                    .text("x")
                    .type(BetaManagedAgentsSearchResultContent.Type.TEXT)
                    .build()
            )
        assertThat(betaManagedAgentsSearchResultBlock.source()).isEqualTo("x")
        assertThat(betaManagedAgentsSearchResultBlock.title()).isEqualTo("x")
        assertThat(betaManagedAgentsSearchResultBlock.type())
            .isEqualTo(BetaManagedAgentsSearchResultBlock.Type.SEARCH_RESULT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSearchResultBlock =
            BetaManagedAgentsSearchResultBlock.builder()
                .citations(BetaManagedAgentsSearchResultCitations.builder().enabled(true).build())
                .addContent(
                    BetaManagedAgentsSearchResultContent.builder()
                        .text("x")
                        .type(BetaManagedAgentsSearchResultContent.Type.TEXT)
                        .build()
                )
                .source("x")
                .title("x")
                .type(BetaManagedAgentsSearchResultBlock.Type.SEARCH_RESULT)
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
