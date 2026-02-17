// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolBash20250124Test {

    @Test
    fun create() {
        val toolBash20250124 =
            ToolBash20250124.builder()
                .addAllowedCaller(ToolBash20250124.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .addInputExample(
                    ToolBash20250124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        assertThat(toolBash20250124.allowedCallers().getOrNull())
            .containsExactly(ToolBash20250124.AllowedCaller.DIRECT)
        assertThat(toolBash20250124.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(toolBash20250124.deferLoading()).contains(true)
        assertThat(toolBash20250124.inputExamples().getOrNull())
            .containsExactly(
                ToolBash20250124.InputExample.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(toolBash20250124.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolBash20250124 =
            ToolBash20250124.builder()
                .addAllowedCaller(ToolBash20250124.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .addInputExample(
                    ToolBash20250124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val roundtrippedToolBash20250124 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolBash20250124),
                jacksonTypeRef<ToolBash20250124>(),
            )

        assertThat(roundtrippedToolBash20250124).isEqualTo(toolBash20250124)
    }
}
