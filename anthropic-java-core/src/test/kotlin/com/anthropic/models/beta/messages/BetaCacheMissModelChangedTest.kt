// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCacheMissModelChangedTest {

    @Test
    fun create() {
        val betaCacheMissModelChanged =
            BetaCacheMissModelChanged.builder().cacheMissedInputTokens(0L).build()

        assertThat(betaCacheMissModelChanged.cacheMissedInputTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCacheMissModelChanged =
            BetaCacheMissModelChanged.builder().cacheMissedInputTokens(0L).build()

        val roundtrippedBetaCacheMissModelChanged =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCacheMissModelChanged),
                jacksonTypeRef<BetaCacheMissModelChanged>(),
            )

        assertThat(roundtrippedBetaCacheMissModelChanged).isEqualTo(betaCacheMissModelChanged)
    }
}
