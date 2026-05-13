// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSearchResultCitationsTest {

    @Test
    fun create() {
        val betaManagedAgentsSearchResultCitations =
            BetaManagedAgentsSearchResultCitations.builder().enabled(true).build()

        assertThat(betaManagedAgentsSearchResultCitations.enabled()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSearchResultCitations =
            BetaManagedAgentsSearchResultCitations.builder().enabled(true).build()

        val roundtrippedBetaManagedAgentsSearchResultCitations =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSearchResultCitations),
                jacksonTypeRef<BetaManagedAgentsSearchResultCitations>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSearchResultCitations)
            .isEqualTo(betaManagedAgentsSearchResultCitations)
    }
}
