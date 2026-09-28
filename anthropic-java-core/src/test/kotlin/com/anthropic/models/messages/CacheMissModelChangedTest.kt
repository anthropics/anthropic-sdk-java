package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CacheMissModelChangedTest {

    @Test
    fun create() {
        val cacheMissModelChanged = CacheMissModelChanged.of(0L)

        assertThat(cacheMissModelChanged.cacheMissedInputTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val cacheMissModelChanged = CacheMissModelChanged.of(0L)

        val roundtrippedCacheMissModelChanged =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cacheMissModelChanged),
                jacksonTypeRef<CacheMissModelChanged>(),
            )

        assertThat(roundtrippedCacheMissModelChanged).isEqualTo(cacheMissModelChanged)
    }
}
