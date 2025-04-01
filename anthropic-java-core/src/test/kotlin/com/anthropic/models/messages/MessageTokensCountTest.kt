// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageTokensCountTest {

    @Test
    fun create() {
        val messageTokensCount = MessageTokensCount.builder().inputTokens(2095L).build()

        assertThat(messageTokensCount.inputTokens()).isEqualTo(2095L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val messageTokensCount = MessageTokensCount.builder().inputTokens(2095L).build()

        val roundtrippedMessageTokensCount =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageTokensCount),
                jacksonTypeRef<MessageTokensCount>(),
            )

        assertThat(roundtrippedMessageTokensCount).isEqualTo(messageTokensCount)
    }
}
