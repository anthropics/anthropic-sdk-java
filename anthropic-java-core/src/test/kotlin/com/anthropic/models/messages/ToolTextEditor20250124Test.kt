// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolTextEditor20250124Test {

    @Test
    fun create() {
        val toolTextEditor20250124 =
            ToolTextEditor20250124.builder()
                .addAllowedCaller(ToolTextEditor20250124.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .addInputExample(
                    ToolTextEditor20250124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        assertThat(toolTextEditor20250124.allowedCallers().getOrNull())
            .containsExactly(ToolTextEditor20250124.AllowedCaller.DIRECT)
        assertThat(toolTextEditor20250124.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(toolTextEditor20250124.deferLoading()).contains(true)
        assertThat(toolTextEditor20250124.inputExamples().getOrNull())
            .containsExactly(
                ToolTextEditor20250124.InputExample.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(toolTextEditor20250124.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolTextEditor20250124 =
            ToolTextEditor20250124.builder()
                .addAllowedCaller(ToolTextEditor20250124.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .addInputExample(
                    ToolTextEditor20250124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val roundtrippedToolTextEditor20250124 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolTextEditor20250124),
                jacksonTypeRef<ToolTextEditor20250124>(),
            )

        assertThat(roundtrippedToolTextEditor20250124).isEqualTo(toolTextEditor20250124)
    }
}
