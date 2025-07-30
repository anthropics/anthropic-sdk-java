// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolTextEditor20250429Test {

    @Test
    fun create() {
        val toolTextEditor20250429 =
            ToolTextEditor20250429.builder()
                .cacheControl(CacheControlEphemeral.builder().build())
                .build()

        assertThat(toolTextEditor20250429.cacheControl())
            .contains(CacheControlEphemeral.builder().build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolTextEditor20250429 =
            ToolTextEditor20250429.builder()
                .cacheControl(CacheControlEphemeral.builder().build())
                .build()

        val roundtrippedToolTextEditor20250429 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolTextEditor20250429),
                jacksonTypeRef<ToolTextEditor20250429>(),
            )

        assertThat(roundtrippedToolTextEditor20250429).isEqualTo(toolTextEditor20250429)
    }
}
