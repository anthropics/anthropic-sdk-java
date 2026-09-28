package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CacheMissToolsChangedTest {

    @Test
    fun create() {
        val cacheMissToolsChanged = CacheMissToolsChanged.of(0L)

        assertThat(cacheMissToolsChanged.cacheMissedInputTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val cacheMissToolsChanged = CacheMissToolsChanged.of(0L)

        val roundtrippedCacheMissToolsChanged =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cacheMissToolsChanged),
                jacksonTypeRef<CacheMissToolsChanged>(),
            )

        assertThat(roundtrippedCacheMissToolsChanged).isEqualTo(cacheMissToolsChanged)
    }
}
