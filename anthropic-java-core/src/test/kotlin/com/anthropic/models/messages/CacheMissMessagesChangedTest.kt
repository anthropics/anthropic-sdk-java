package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CacheMissMessagesChangedTest {

    @Test
    fun create() {
        val cacheMissMessagesChanged = CacheMissMessagesChanged.of(0L)

        assertThat(cacheMissMessagesChanged.cacheMissedInputTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val cacheMissMessagesChanged = CacheMissMessagesChanged.of(0L)

        val roundtrippedCacheMissMessagesChanged =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cacheMissMessagesChanged),
                jacksonTypeRef<CacheMissMessagesChanged>(),
            )

        assertThat(roundtrippedCacheMissMessagesChanged).isEqualTo(cacheMissMessagesChanged)
    }
}
