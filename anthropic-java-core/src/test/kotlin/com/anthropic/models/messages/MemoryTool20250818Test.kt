// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemoryTool20250818Test {

    @Test
    fun create() {
        val memoryTool20250818 =
            MemoryTool20250818.builder()
                .addAllowedCaller(MemoryTool20250818.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .addInputExample(
                    MemoryTool20250818.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        assertThat(memoryTool20250818.allowedCallers().getOrNull())
            .containsExactly(MemoryTool20250818.AllowedCaller.DIRECT)
        assertThat(memoryTool20250818.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(memoryTool20250818.deferLoading()).contains(true)
        assertThat(memoryTool20250818.inputExamples().getOrNull())
            .containsExactly(
                MemoryTool20250818.InputExample.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(memoryTool20250818.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val memoryTool20250818 =
            MemoryTool20250818.builder()
                .addAllowedCaller(MemoryTool20250818.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .addInputExample(
                    MemoryTool20250818.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val roundtrippedMemoryTool20250818 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(memoryTool20250818),
                jacksonTypeRef<MemoryTool20250818>(),
            )

        assertThat(roundtrippedMemoryTool20250818).isEqualTo(memoryTool20250818)
    }
}
