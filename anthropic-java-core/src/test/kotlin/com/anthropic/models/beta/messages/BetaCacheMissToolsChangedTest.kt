// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCacheMissToolsChangedTest {

    @Test
    fun create() {
        val betaCacheMissToolsChanged =
            BetaCacheMissToolsChanged.builder().cacheMissedInputTokens(0L).build()

        assertThat(betaCacheMissToolsChanged.cacheMissedInputTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCacheMissToolsChanged =
            BetaCacheMissToolsChanged.builder().cacheMissedInputTokens(0L).build()

        val roundtrippedBetaCacheMissToolsChanged =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCacheMissToolsChanged),
                jacksonTypeRef<BetaCacheMissToolsChanged>(),
            )

        assertThat(roundtrippedBetaCacheMissToolsChanged).isEqualTo(betaCacheMissToolsChanged)
    }
}
