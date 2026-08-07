// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCacheMissMessagesChangedTest {

    @Test
    fun create() {
        val betaCacheMissMessagesChanged = BetaCacheMissMessagesChanged.of(0L)

        assertThat(betaCacheMissMessagesChanged.cacheMissedInputTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCacheMissMessagesChanged = BetaCacheMissMessagesChanged.of(0L)

        val roundtrippedBetaCacheMissMessagesChanged =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCacheMissMessagesChanged),
                jacksonTypeRef<BetaCacheMissMessagesChanged>(),
            )

        assertThat(roundtrippedBetaCacheMissMessagesChanged).isEqualTo(betaCacheMissMessagesChanged)
    }
}
