// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolTextEditor20250728Test {

    @Test
    fun create() {
        val toolTextEditor20250728 =
            ToolTextEditor20250728.builder()
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .maxCharacters(1L)
                .strict(true)
                .build()

        assertThat(toolTextEditor20250728.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(toolTextEditor20250728.maxCharacters()).contains(1L)
        assertThat(toolTextEditor20250728.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolTextEditor20250728 =
            ToolTextEditor20250728.builder()
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .maxCharacters(1L)
                .strict(true)
                .build()

        val roundtrippedToolTextEditor20250728 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolTextEditor20250728),
                jacksonTypeRef<ToolTextEditor20250728>(),
            )

        assertThat(roundtrippedToolTextEditor20250728).isEqualTo(toolTextEditor20250728)
    }
}
