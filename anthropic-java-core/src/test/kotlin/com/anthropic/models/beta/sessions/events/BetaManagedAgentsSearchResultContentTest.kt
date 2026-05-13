// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSearchResultContentTest {

    @Test
    fun create() {
        val betaManagedAgentsSearchResultContent =
            BetaManagedAgentsSearchResultContent.builder()
                .text("x")
                .type(BetaManagedAgentsSearchResultContent.Type.TEXT)
                .build()

        assertThat(betaManagedAgentsSearchResultContent.text()).isEqualTo("x")
        assertThat(betaManagedAgentsSearchResultContent.type())
            .isEqualTo(BetaManagedAgentsSearchResultContent.Type.TEXT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSearchResultContent =
            BetaManagedAgentsSearchResultContent.builder()
                .text("x")
                .type(BetaManagedAgentsSearchResultContent.Type.TEXT)
                .build()

        val roundtrippedBetaManagedAgentsSearchResultContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSearchResultContent),
                jacksonTypeRef<BetaManagedAgentsSearchResultContent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSearchResultContent)
            .isEqualTo(betaManagedAgentsSearchResultContent)
    }
}
