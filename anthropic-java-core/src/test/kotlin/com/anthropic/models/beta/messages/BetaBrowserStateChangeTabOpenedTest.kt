// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserStateChangeTabOpenedTest {

    @Test
    fun create() {
        val betaBrowserStateChangeTabOpened = BetaBrowserStateChangeTabOpened.of("tab_id")

        assertThat(betaBrowserStateChangeTabOpened.tabId()).isEqualTo("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserStateChangeTabOpened = BetaBrowserStateChangeTabOpened.of("tab_id")

        val roundtrippedBetaBrowserStateChangeTabOpened =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserStateChangeTabOpened),
                jacksonTypeRef<BetaBrowserStateChangeTabOpened>(),
            )

        assertThat(roundtrippedBetaBrowserStateChangeTabOpened)
            .isEqualTo(betaBrowserStateChangeTabOpened)
    }
}
