package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CacheMissSystemChangedTest {

    @Test
    fun create() {
        val cacheMissSystemChanged = CacheMissSystemChanged.of(0L)

        assertThat(cacheMissSystemChanged.cacheMissedInputTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val cacheMissSystemChanged = CacheMissSystemChanged.of(0L)

        val roundtrippedCacheMissSystemChanged =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cacheMissSystemChanged),
                jacksonTypeRef<CacheMissSystemChanged>(),
            )

        assertThat(roundtrippedCacheMissSystemChanged).isEqualTo(cacheMissSystemChanged)
    }
}
