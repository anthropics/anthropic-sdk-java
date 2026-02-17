// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class MessageCountTokensToolTest {

    @Test
    fun ofTool() {
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

        val messageCountTokensTool = MessageCountTokensTool.ofTool(tool)

        assertThat(messageCountTokensTool.tool()).contains(tool)
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250522()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250825()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20260120()).isEmpty
        assertThat(messageCountTokensTool.memoryTool20250818()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250429()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250728()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20250305()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20250910()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolBm25_20251119()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofToolRoundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofTool(
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
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
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
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    @Test
    fun ofToolBash20250124() {
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

        val messageCountTokensTool = MessageCountTokensTool.ofToolBash20250124(toolBash20250124)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).contains(toolBash20250124)
        assertThat(messageCountTokensTool.codeExecutionTool20250522()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250825()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20260120()).isEmpty
        assertThat(messageCountTokensTool.memoryTool20250818()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250429()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250728()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20250305()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20250910()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolBm25_20251119()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofToolBash20250124Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofToolBash20250124(
                ToolBash20250124.builder()
                    .addAllowedCaller(ToolBash20250124.AllowedCaller.DIRECT)
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        ToolBash20250124.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    @Test
    fun ofCodeExecutionTool20250522() {
        val codeExecutionTool20250522 =
            CodeExecutionTool20250522.builder()
                .addAllowedCaller(CodeExecutionTool20250522.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofCodeExecutionTool20250522(codeExecutionTool20250522)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250522())
            .contains(codeExecutionTool20250522)
        assertThat(messageCountTokensTool.codeExecutionTool20250825()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20260120()).isEmpty
        assertThat(messageCountTokensTool.memoryTool20250818()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250429()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250728()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20250305()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20250910()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolBm25_20251119()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofCodeExecutionTool20250522Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofCodeExecutionTool20250522(
                CodeExecutionTool20250522.builder()
                    .addAllowedCaller(CodeExecutionTool20250522.AllowedCaller.DIRECT)
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    @Test
    fun ofCodeExecutionTool20250825() {
        val codeExecutionTool20250825 =
            CodeExecutionTool20250825.builder()
                .addAllowedCaller(CodeExecutionTool20250825.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofCodeExecutionTool20250825(codeExecutionTool20250825)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250522()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250825())
            .contains(codeExecutionTool20250825)
        assertThat(messageCountTokensTool.codeExecutionTool20260120()).isEmpty
        assertThat(messageCountTokensTool.memoryTool20250818()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250429()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250728()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20250305()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20250910()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolBm25_20251119()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofCodeExecutionTool20250825Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofCodeExecutionTool20250825(
                CodeExecutionTool20250825.builder()
                    .addAllowedCaller(CodeExecutionTool20250825.AllowedCaller.DIRECT)
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    @Test
    fun ofCodeExecutionTool20260120() {
        val codeExecutionTool20260120 =
            MessageCountTokensTool.CodeExecutionTool20260120.builder()
                .addAllowedCaller(
                    MessageCountTokensTool.CodeExecutionTool20260120.AllowedCaller.DIRECT
                )
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofCodeExecutionTool20260120(codeExecutionTool20260120)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250522()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250825()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20260120())
            .contains(codeExecutionTool20260120)
        assertThat(messageCountTokensTool.memoryTool20250818()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250429()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250728()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20250305()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20250910()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolBm25_20251119()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofCodeExecutionTool20260120Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofCodeExecutionTool20260120(
                MessageCountTokensTool.CodeExecutionTool20260120.builder()
                    .addAllowedCaller(
                        MessageCountTokensTool.CodeExecutionTool20260120.AllowedCaller.DIRECT
                    )
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    @Test
    fun ofMemoryTool20250818() {
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

        val messageCountTokensTool = MessageCountTokensTool.ofMemoryTool20250818(memoryTool20250818)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250522()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250825()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20260120()).isEmpty
        assertThat(messageCountTokensTool.memoryTool20250818()).contains(memoryTool20250818)
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250429()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250728()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20250305()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20250910()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolBm25_20251119()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofMemoryTool20250818Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofMemoryTool20250818(
                MemoryTool20250818.builder()
                    .addAllowedCaller(MemoryTool20250818.AllowedCaller.DIRECT)
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        MemoryTool20250818.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    @Test
    fun ofToolTextEditor20250124() {
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

        val messageCountTokensTool =
            MessageCountTokensTool.ofToolTextEditor20250124(toolTextEditor20250124)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250522()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250825()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20260120()).isEmpty
        assertThat(messageCountTokensTool.memoryTool20250818()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).contains(toolTextEditor20250124)
        assertThat(messageCountTokensTool.toolTextEditor20250429()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250728()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20250305()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20250910()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolBm25_20251119()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofToolTextEditor20250124Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofToolTextEditor20250124(
                ToolTextEditor20250124.builder()
                    .addAllowedCaller(ToolTextEditor20250124.AllowedCaller.DIRECT)
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        ToolTextEditor20250124.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    @Test
    fun ofToolTextEditor20250429() {
        val toolTextEditor20250429 =
            ToolTextEditor20250429.builder()
                .addAllowedCaller(ToolTextEditor20250429.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .addInputExample(
                    ToolTextEditor20250429.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofToolTextEditor20250429(toolTextEditor20250429)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250522()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250825()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20260120()).isEmpty
        assertThat(messageCountTokensTool.memoryTool20250818()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250429()).contains(toolTextEditor20250429)
        assertThat(messageCountTokensTool.toolTextEditor20250728()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20250305()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20250910()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolBm25_20251119()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofToolTextEditor20250429Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofToolTextEditor20250429(
                ToolTextEditor20250429.builder()
                    .addAllowedCaller(ToolTextEditor20250429.AllowedCaller.DIRECT)
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        ToolTextEditor20250429.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    @Test
    fun ofToolTextEditor20250728() {
        val toolTextEditor20250728 =
            ToolTextEditor20250728.builder()
                .addAllowedCaller(ToolTextEditor20250728.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .addInputExample(
                    ToolTextEditor20250728.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .maxCharacters(1L)
                .strict(true)
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofToolTextEditor20250728(toolTextEditor20250728)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250522()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250825()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20260120()).isEmpty
        assertThat(messageCountTokensTool.memoryTool20250818()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250429()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250728()).contains(toolTextEditor20250728)
        assertThat(messageCountTokensTool.webSearchTool20250305()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20250910()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolBm25_20251119()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofToolTextEditor20250728Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofToolTextEditor20250728(
                ToolTextEditor20250728.builder()
                    .addAllowedCaller(ToolTextEditor20250728.AllowedCaller.DIRECT)
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        ToolTextEditor20250728.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .maxCharacters(1L)
                    .strict(true)
                    .build()
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    @Test
    fun ofWebSearchTool20250305() {
        val webSearchTool20250305 =
            WebSearchTool20250305.builder()
                .addAllowedCaller(WebSearchTool20250305.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .strict(true)
                .userLocation(
                    WebSearchTool20250305.UserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofWebSearchTool20250305(webSearchTool20250305)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250522()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250825()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20260120()).isEmpty
        assertThat(messageCountTokensTool.memoryTool20250818()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250429()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250728()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20250305()).contains(webSearchTool20250305)
        assertThat(messageCountTokensTool.webFetchTool20250910()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolBm25_20251119()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofWebSearchTool20250305Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofWebSearchTool20250305(
                WebSearchTool20250305.builder()
                    .addAllowedCaller(WebSearchTool20250305.AllowedCaller.DIRECT)
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .maxUses(1L)
                    .strict(true)
                    .userLocation(
                        WebSearchTool20250305.UserLocation.builder()
                            .city("New York")
                            .country("US")
                            .region("California")
                            .timezone("America/New_York")
                            .build()
                    )
                    .build()
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    @Test
    fun ofWebFetchTool20250910() {
        val webFetchTool20250910 =
            WebFetchTool20250910.builder()
                .addAllowedCaller(WebFetchTool20250910.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .citations(CitationsConfigParam.builder().enabled(true).build())
                .deferLoading(true)
                .maxContentTokens(1L)
                .maxUses(1L)
                .strict(true)
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofWebFetchTool20250910(webFetchTool20250910)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250522()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250825()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20260120()).isEmpty
        assertThat(messageCountTokensTool.memoryTool20250818()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250429()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250728()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20250305()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20250910()).contains(webFetchTool20250910)
        assertThat(messageCountTokensTool.webSearchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolBm25_20251119()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofWebFetchTool20250910Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofWebFetchTool20250910(
                WebFetchTool20250910.builder()
                    .addAllowedCaller(WebFetchTool20250910.AllowedCaller.DIRECT)
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .citations(CitationsConfigParam.builder().enabled(true).build())
                    .deferLoading(true)
                    .maxContentTokens(1L)
                    .maxUses(1L)
                    .strict(true)
                    .build()
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    @Test
    fun ofWebSearchTool20260209() {
        val webSearchTool20260209 =
            MessageCountTokensTool.WebSearchTool20260209.builder()
                .addAllowedCaller(MessageCountTokensTool.WebSearchTool20260209.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .strict(true)
                .userLocation(
                    MessageCountTokensTool.WebSearchTool20260209.UserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofWebSearchTool20260209(webSearchTool20260209)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250522()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250825()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20260120()).isEmpty
        assertThat(messageCountTokensTool.memoryTool20250818()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250429()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250728()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20250305()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20250910()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20260209()).contains(webSearchTool20260209)
        assertThat(messageCountTokensTool.webFetchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolBm25_20251119()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofWebSearchTool20260209Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofWebSearchTool20260209(
                MessageCountTokensTool.WebSearchTool20260209.builder()
                    .addAllowedCaller(
                        MessageCountTokensTool.WebSearchTool20260209.AllowedCaller.DIRECT
                    )
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .maxUses(1L)
                    .strict(true)
                    .userLocation(
                        MessageCountTokensTool.WebSearchTool20260209.UserLocation.builder()
                            .city("New York")
                            .country("US")
                            .region("California")
                            .timezone("America/New_York")
                            .build()
                    )
                    .build()
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    @Test
    fun ofWebFetchTool20260209() {
        val webFetchTool20260209 =
            MessageCountTokensTool.WebFetchTool20260209.builder()
                .addAllowedCaller(MessageCountTokensTool.WebFetchTool20260209.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .citations(CitationsConfigParam.builder().enabled(true).build())
                .deferLoading(true)
                .maxContentTokens(1L)
                .maxUses(1L)
                .strict(true)
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofWebFetchTool20260209(webFetchTool20260209)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250522()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250825()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20260120()).isEmpty
        assertThat(messageCountTokensTool.memoryTool20250818()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250429()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250728()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20250305()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20250910()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20260209()).contains(webFetchTool20260209)
        assertThat(messageCountTokensTool.toolSearchToolBm25_20251119()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofWebFetchTool20260209Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofWebFetchTool20260209(
                MessageCountTokensTool.WebFetchTool20260209.builder()
                    .addAllowedCaller(
                        MessageCountTokensTool.WebFetchTool20260209.AllowedCaller.DIRECT
                    )
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .citations(CitationsConfigParam.builder().enabled(true).build())
                    .deferLoading(true)
                    .maxContentTokens(1L)
                    .maxUses(1L)
                    .strict(true)
                    .build()
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    @Test
    fun ofToolSearchToolBm25_20251119() {
        val toolSearchToolBm25_20251119 =
            ToolSearchToolBm25_20251119.builder()
                .type(ToolSearchToolBm25_20251119.Type.TOOL_SEARCH_TOOL_BM25_20251119)
                .addAllowedCaller(ToolSearchToolBm25_20251119.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofToolSearchToolBm25_20251119(toolSearchToolBm25_20251119)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250522()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250825()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20260120()).isEmpty
        assertThat(messageCountTokensTool.memoryTool20250818()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250429()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250728()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20250305()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20250910()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolBm25_20251119())
            .contains(toolSearchToolBm25_20251119)
        assertThat(messageCountTokensTool.toolSearchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofToolSearchToolBm25_20251119Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofToolSearchToolBm25_20251119(
                ToolSearchToolBm25_20251119.builder()
                    .type(ToolSearchToolBm25_20251119.Type.TOOL_SEARCH_TOOL_BM25_20251119)
                    .addAllowedCaller(ToolSearchToolBm25_20251119.AllowedCaller.DIRECT)
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    @Test
    fun ofToolSearchToolRegex20251119() {
        val toolSearchToolRegex20251119 =
            ToolSearchToolRegex20251119.builder()
                .type(ToolSearchToolRegex20251119.Type.TOOL_SEARCH_TOOL_REGEX_20251119)
                .addAllowedCaller(ToolSearchToolRegex20251119.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofToolSearchToolRegex20251119(toolSearchToolRegex20251119)

        assertThat(messageCountTokensTool.tool()).isEmpty
        assertThat(messageCountTokensTool.toolBash20250124()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250522()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20250825()).isEmpty
        assertThat(messageCountTokensTool.codeExecutionTool20260120()).isEmpty
        assertThat(messageCountTokensTool.memoryTool20250818()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250124()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250429()).isEmpty
        assertThat(messageCountTokensTool.toolTextEditor20250728()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20250305()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20250910()).isEmpty
        assertThat(messageCountTokensTool.webSearchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.webFetchTool20260209()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolBm25_20251119()).isEmpty
        assertThat(messageCountTokensTool.toolSearchToolRegex20251119())
            .contains(toolSearchToolRegex20251119)
    }

    @Test
    fun ofToolSearchToolRegex20251119Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofToolSearchToolRegex20251119(
                ToolSearchToolRegex20251119.builder()
                    .type(ToolSearchToolRegex20251119.Type.TOOL_SEARCH_TOOL_REGEX_20251119)
                    .addAllowedCaller(ToolSearchToolRegex20251119.AllowedCaller.DIRECT)
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val messageCountTokensTool =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<MessageCountTokensTool>())

        val e = assertThrows<AnthropicInvalidDataException> { messageCountTokensTool.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
