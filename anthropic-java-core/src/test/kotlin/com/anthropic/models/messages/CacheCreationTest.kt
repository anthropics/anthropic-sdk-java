// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CacheCreationTest {

    @Test
    fun create() {
        val cacheCreation =
            CacheCreation.builder().ephemeral1hInputTokens(0L).ephemeral5mInputTokens(0L).build()

        assertThat(cacheCreation.ephemeral1hInputTokens()).isEqualTo(0L)
        assertThat(cacheCreation.ephemeral5mInputTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val cacheCreation =
            CacheCreation.builder().ephemeral1hInputTokens(0L).ephemeral5mInputTokens(0L).build()

        val roundtrippedCacheCreation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cacheCreation),
                jacksonTypeRef<CacheCreation>(),
            )

        assertThat(roundtrippedCacheCreation).isEqualTo(cacheCreation)
    }
}
