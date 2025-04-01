// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CacheControlEphemeralTest {

    @Test
    fun create() {
        val cacheControlEphemeral = CacheControlEphemeral.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val cacheControlEphemeral = CacheControlEphemeral.builder().build()

        val roundtrippedCacheControlEphemeral =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cacheControlEphemeral),
                jacksonTypeRef<CacheControlEphemeral>(),
            )

        assertThat(roundtrippedCacheControlEphemeral).isEqualTo(cacheControlEphemeral)
    }
}
