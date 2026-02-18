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

internal class ToolUnionTest {

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

        val toolUnion = ToolUnion.ofTool(tool)

        assertThat(toolUnion.tool()).contains(tool)
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(toolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(toolUnion.memoryTool20250818()).isEmpty
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
        assertThat(toolUnion.webFetchTool20250910()).isEmpty
        assertThat(toolUnion.webSearchTool20260209()).isEmpty
        assertThat(toolUnion.webFetchTool20260209()).isEmpty
        assertThat(toolUnion.searchToolBm25_20251119()).isEmpty
        assertThat(toolUnion.searchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofToolRoundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofTool(
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

        val roundtrippedToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUnion),
                jacksonTypeRef<ToolUnion>(),
            )

        assertThat(roundtrippedToolUnion).isEqualTo(toolUnion)
    }

    @Test
    fun ofBash20250124() {
        val bash20250124 =
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

        val toolUnion = ToolUnion.ofBash20250124(bash20250124)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).contains(bash20250124)
        assertThat(toolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(toolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(toolUnion.memoryTool20250818()).isEmpty
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
        assertThat(toolUnion.webFetchTool20250910()).isEmpty
        assertThat(toolUnion.webSearchTool20260209()).isEmpty
        assertThat(toolUnion.webFetchTool20260209()).isEmpty
        assertThat(toolUnion.searchToolBm25_20251119()).isEmpty
        assertThat(toolUnion.searchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofBash20250124Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofBash20250124(
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

        val roundtrippedToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUnion),
                jacksonTypeRef<ToolUnion>(),
            )

        assertThat(roundtrippedToolUnion).isEqualTo(toolUnion)
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

        val toolUnion = ToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250522()).contains(codeExecutionTool20250522)
        assertThat(toolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(toolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(toolUnion.memoryTool20250818()).isEmpty
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
        assertThat(toolUnion.webFetchTool20250910()).isEmpty
        assertThat(toolUnion.webSearchTool20260209()).isEmpty
        assertThat(toolUnion.webFetchTool20260209()).isEmpty
        assertThat(toolUnion.searchToolBm25_20251119()).isEmpty
        assertThat(toolUnion.searchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofCodeExecutionTool20250522Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofCodeExecutionTool20250522(
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

        val roundtrippedToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUnion),
                jacksonTypeRef<ToolUnion>(),
            )

        assertThat(roundtrippedToolUnion).isEqualTo(toolUnion)
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

        val toolUnion = ToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250825()).contains(codeExecutionTool20250825)
        assertThat(toolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(toolUnion.memoryTool20250818()).isEmpty
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
        assertThat(toolUnion.webFetchTool20250910()).isEmpty
        assertThat(toolUnion.webSearchTool20260209()).isEmpty
        assertThat(toolUnion.webFetchTool20260209()).isEmpty
        assertThat(toolUnion.searchToolBm25_20251119()).isEmpty
        assertThat(toolUnion.searchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofCodeExecutionTool20250825Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofCodeExecutionTool20250825(
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

        val roundtrippedToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUnion),
                jacksonTypeRef<ToolUnion>(),
            )

        assertThat(roundtrippedToolUnion).isEqualTo(toolUnion)
    }

    @Test
    fun ofCodeExecutionTool20260120() {
        val codeExecutionTool20260120 =
            CodeExecutionTool20260120.builder()
                .addAllowedCaller(CodeExecutionTool20260120.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val toolUnion = ToolUnion.ofCodeExecutionTool20260120(codeExecutionTool20260120)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(toolUnion.codeExecutionTool20260120()).contains(codeExecutionTool20260120)
        assertThat(toolUnion.memoryTool20250818()).isEmpty
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
        assertThat(toolUnion.webFetchTool20250910()).isEmpty
        assertThat(toolUnion.webSearchTool20260209()).isEmpty
        assertThat(toolUnion.webFetchTool20260209()).isEmpty
        assertThat(toolUnion.searchToolBm25_20251119()).isEmpty
        assertThat(toolUnion.searchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofCodeExecutionTool20260120Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofCodeExecutionTool20260120(
                CodeExecutionTool20260120.builder()
                    .addAllowedCaller(CodeExecutionTool20260120.AllowedCaller.DIRECT)
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUnion),
                jacksonTypeRef<ToolUnion>(),
            )

        assertThat(roundtrippedToolUnion).isEqualTo(toolUnion)
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

        val toolUnion = ToolUnion.ofMemoryTool20250818(memoryTool20250818)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(toolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(toolUnion.memoryTool20250818()).contains(memoryTool20250818)
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
        assertThat(toolUnion.webFetchTool20250910()).isEmpty
        assertThat(toolUnion.webSearchTool20260209()).isEmpty
        assertThat(toolUnion.webFetchTool20260209()).isEmpty
        assertThat(toolUnion.searchToolBm25_20251119()).isEmpty
        assertThat(toolUnion.searchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofMemoryTool20250818Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofMemoryTool20250818(
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

        val roundtrippedToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUnion),
                jacksonTypeRef<ToolUnion>(),
            )

        assertThat(roundtrippedToolUnion).isEqualTo(toolUnion)
    }

    @Test
    fun ofTextEditor20250124() {
        val textEditor20250124 =
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

        val toolUnion = ToolUnion.ofTextEditor20250124(textEditor20250124)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(toolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(toolUnion.memoryTool20250818()).isEmpty
        assertThat(toolUnion.textEditor20250124()).contains(textEditor20250124)
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
        assertThat(toolUnion.webFetchTool20250910()).isEmpty
        assertThat(toolUnion.webSearchTool20260209()).isEmpty
        assertThat(toolUnion.webFetchTool20260209()).isEmpty
        assertThat(toolUnion.searchToolBm25_20251119()).isEmpty
        assertThat(toolUnion.searchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofTextEditor20250124Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofTextEditor20250124(
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

        val roundtrippedToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUnion),
                jacksonTypeRef<ToolUnion>(),
            )

        assertThat(roundtrippedToolUnion).isEqualTo(toolUnion)
    }

    @Test
    fun ofTextEditor20250429() {
        val textEditor20250429 =
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

        val toolUnion = ToolUnion.ofTextEditor20250429(textEditor20250429)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(toolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(toolUnion.memoryTool20250818()).isEmpty
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).contains(textEditor20250429)
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
        assertThat(toolUnion.webFetchTool20250910()).isEmpty
        assertThat(toolUnion.webSearchTool20260209()).isEmpty
        assertThat(toolUnion.webFetchTool20260209()).isEmpty
        assertThat(toolUnion.searchToolBm25_20251119()).isEmpty
        assertThat(toolUnion.searchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofTextEditor20250429Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofTextEditor20250429(
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

        val roundtrippedToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUnion),
                jacksonTypeRef<ToolUnion>(),
            )

        assertThat(roundtrippedToolUnion).isEqualTo(toolUnion)
    }

    @Test
    fun ofTextEditor20250728() {
        val textEditor20250728 =
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

        val toolUnion = ToolUnion.ofTextEditor20250728(textEditor20250728)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(toolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(toolUnion.memoryTool20250818()).isEmpty
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).contains(textEditor20250728)
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
        assertThat(toolUnion.webFetchTool20250910()).isEmpty
        assertThat(toolUnion.webSearchTool20260209()).isEmpty
        assertThat(toolUnion.webFetchTool20260209()).isEmpty
        assertThat(toolUnion.searchToolBm25_20251119()).isEmpty
        assertThat(toolUnion.searchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofTextEditor20250728Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofTextEditor20250728(
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

        val roundtrippedToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUnion),
                jacksonTypeRef<ToolUnion>(),
            )

        assertThat(roundtrippedToolUnion).isEqualTo(toolUnion)
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

        val toolUnion = ToolUnion.ofWebSearchTool20250305(webSearchTool20250305)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(toolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(toolUnion.memoryTool20250818()).isEmpty
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).contains(webSearchTool20250305)
        assertThat(toolUnion.webFetchTool20250910()).isEmpty
        assertThat(toolUnion.webSearchTool20260209()).isEmpty
        assertThat(toolUnion.webFetchTool20260209()).isEmpty
        assertThat(toolUnion.searchToolBm25_20251119()).isEmpty
        assertThat(toolUnion.searchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofWebSearchTool20250305Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofWebSearchTool20250305(
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

        val roundtrippedToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUnion),
                jacksonTypeRef<ToolUnion>(),
            )

        assertThat(roundtrippedToolUnion).isEqualTo(toolUnion)
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

        val toolUnion = ToolUnion.ofWebFetchTool20250910(webFetchTool20250910)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(toolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(toolUnion.memoryTool20250818()).isEmpty
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
        assertThat(toolUnion.webFetchTool20250910()).contains(webFetchTool20250910)
        assertThat(toolUnion.webSearchTool20260209()).isEmpty
        assertThat(toolUnion.webFetchTool20260209()).isEmpty
        assertThat(toolUnion.searchToolBm25_20251119()).isEmpty
        assertThat(toolUnion.searchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofWebFetchTool20250910Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofWebFetchTool20250910(
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

        val roundtrippedToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUnion),
                jacksonTypeRef<ToolUnion>(),
            )

        assertThat(roundtrippedToolUnion).isEqualTo(toolUnion)
    }

    @Test
    fun ofWebSearchTool20260209() {
        val webSearchTool20260209 =
            WebSearchTool20260209.builder()
                .addAllowedCaller(WebSearchTool20260209.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .strict(true)
                .userLocation(
                    WebSearchTool20260209.UserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val toolUnion = ToolUnion.ofWebSearchTool20260209(webSearchTool20260209)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(toolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(toolUnion.memoryTool20250818()).isEmpty
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
        assertThat(toolUnion.webFetchTool20250910()).isEmpty
        assertThat(toolUnion.webSearchTool20260209()).contains(webSearchTool20260209)
        assertThat(toolUnion.webFetchTool20260209()).isEmpty
        assertThat(toolUnion.searchToolBm25_20251119()).isEmpty
        assertThat(toolUnion.searchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofWebSearchTool20260209Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofWebSearchTool20260209(
                WebSearchTool20260209.builder()
                    .addAllowedCaller(WebSearchTool20260209.AllowedCaller.DIRECT)
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
                        WebSearchTool20260209.UserLocation.builder()
                            .city("New York")
                            .country("US")
                            .region("California")
                            .timezone("America/New_York")
                            .build()
                    )
                    .build()
            )

        val roundtrippedToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUnion),
                jacksonTypeRef<ToolUnion>(),
            )

        assertThat(roundtrippedToolUnion).isEqualTo(toolUnion)
    }

    @Test
    fun ofWebFetchTool20260209() {
        val webFetchTool20260209 =
            WebFetchTool20260209.builder()
                .addAllowedCaller(WebFetchTool20260209.AllowedCaller.DIRECT)
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

        val toolUnion = ToolUnion.ofWebFetchTool20260209(webFetchTool20260209)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(toolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(toolUnion.memoryTool20250818()).isEmpty
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
        assertThat(toolUnion.webFetchTool20250910()).isEmpty
        assertThat(toolUnion.webSearchTool20260209()).isEmpty
        assertThat(toolUnion.webFetchTool20260209()).contains(webFetchTool20260209)
        assertThat(toolUnion.searchToolBm25_20251119()).isEmpty
        assertThat(toolUnion.searchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofWebFetchTool20260209Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofWebFetchTool20260209(
                WebFetchTool20260209.builder()
                    .addAllowedCaller(WebFetchTool20260209.AllowedCaller.DIRECT)
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

        val roundtrippedToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUnion),
                jacksonTypeRef<ToolUnion>(),
            )

        assertThat(roundtrippedToolUnion).isEqualTo(toolUnion)
    }

    @Test
    fun ofSearchToolBm25_20251119() {
        val searchToolBm25_20251119 =
            ToolSearchToolBm25_20251119.builder()
                .type(ToolSearchToolBm25_20251119.Type.TOOL_SEARCH_TOOL_BM25_20251119)
                .addAllowedCaller(ToolSearchToolBm25_20251119.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val toolUnion = ToolUnion.ofSearchToolBm25_20251119(searchToolBm25_20251119)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(toolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(toolUnion.memoryTool20250818()).isEmpty
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
        assertThat(toolUnion.webFetchTool20250910()).isEmpty
        assertThat(toolUnion.webSearchTool20260209()).isEmpty
        assertThat(toolUnion.webFetchTool20260209()).isEmpty
        assertThat(toolUnion.searchToolBm25_20251119()).contains(searchToolBm25_20251119)
        assertThat(toolUnion.searchToolRegex20251119()).isEmpty
    }

    @Test
    fun ofSearchToolBm25_20251119Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofSearchToolBm25_20251119(
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

        val roundtrippedToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUnion),
                jacksonTypeRef<ToolUnion>(),
            )

        assertThat(roundtrippedToolUnion).isEqualTo(toolUnion)
    }

    @Test
    fun ofSearchToolRegex20251119() {
        val searchToolRegex20251119 =
            ToolSearchToolRegex20251119.builder()
                .type(ToolSearchToolRegex20251119.Type.TOOL_SEARCH_TOOL_REGEX_20251119)
                .addAllowedCaller(ToolSearchToolRegex20251119.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val toolUnion = ToolUnion.ofSearchToolRegex20251119(searchToolRegex20251119)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250522()).isEmpty
        assertThat(toolUnion.codeExecutionTool20250825()).isEmpty
        assertThat(toolUnion.codeExecutionTool20260120()).isEmpty
        assertThat(toolUnion.memoryTool20250818()).isEmpty
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
        assertThat(toolUnion.webFetchTool20250910()).isEmpty
        assertThat(toolUnion.webSearchTool20260209()).isEmpty
        assertThat(toolUnion.webFetchTool20260209()).isEmpty
        assertThat(toolUnion.searchToolBm25_20251119()).isEmpty
        assertThat(toolUnion.searchToolRegex20251119()).contains(searchToolRegex20251119)
    }

    @Test
    fun ofSearchToolRegex20251119Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofSearchToolRegex20251119(
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

        val roundtrippedToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUnion),
                jacksonTypeRef<ToolUnion>(),
            )

        assertThat(roundtrippedToolUnion).isEqualTo(toolUnion)
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
        val toolUnion = jsonMapper().convertValue(testCase.value, jacksonTypeRef<ToolUnion>())

        val e = assertThrows<AnthropicInvalidDataException> { toolUnion.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
