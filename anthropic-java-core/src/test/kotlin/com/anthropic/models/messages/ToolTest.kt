// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolTest {

    @Test
    fun create() {
        val tool =
            Tool.builder()
                .inputSchema(
                    Tool.InputSchema.builder()
                        .properties(
                            Tool.InputSchema.Properties.builder()
                                .putAdditionalProperty("location", JsonValue.from("bar"))
                                .putAdditionalProperty("unit", JsonValue.from("bar"))
                                .build()
                        )
                        .addRequired("location")
                        .build()
                )
                .name("name")
                .addAllowedCaller(Tool.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .description("Get the current weather in a given location")
                .eagerInputStreaming(true)
                .addInputExample(
                    Tool.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .type(Tool.Type.CUSTOM)
                .build()

        assertThat(tool.inputSchema())
            .isEqualTo(
                Tool.InputSchema.builder()
                    .properties(
                        Tool.InputSchema.Properties.builder()
                            .putAdditionalProperty("location", JsonValue.from("bar"))
                            .putAdditionalProperty("unit", JsonValue.from("bar"))
                            .build()
                    )
                    .addRequired("location")
                    .build()
            )
        assertThat(tool.name()).isEqualTo("name")
        assertThat(tool.allowedCallers().getOrNull()).containsExactly(Tool.AllowedCaller.DIRECT)
        assertThat(tool.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(tool.deferLoading()).contains(true)
        assertThat(tool.description()).contains("Get the current weather in a given location")
        assertThat(tool.eagerInputStreaming()).contains(true)
        assertThat(tool.inputExamples().getOrNull())
            .containsExactly(
                Tool.InputExample.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(tool.strict()).contains(true)
        assertThat(tool.type()).contains(Tool.Type.CUSTOM)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val tool =
            Tool.builder()
                .inputSchema(
                    Tool.InputSchema.builder()
                        .properties(
                            Tool.InputSchema.Properties.builder()
                                .putAdditionalProperty("location", JsonValue.from("bar"))
                                .putAdditionalProperty("unit", JsonValue.from("bar"))
                                .build()
                        )
                        .addRequired("location")
                        .build()
                )
                .name("name")
                .addAllowedCaller(Tool.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .description("Get the current weather in a given location")
                .eagerInputStreaming(true)
                .addInputExample(
                    Tool.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .type(Tool.Type.CUSTOM)
                .build()

        val roundtrippedTool =
            jsonMapper.readValue(jsonMapper.writeValueAsString(tool), jacksonTypeRef<Tool>())

        assertThat(roundtrippedTool).isEqualTo(tool)
    }
}
