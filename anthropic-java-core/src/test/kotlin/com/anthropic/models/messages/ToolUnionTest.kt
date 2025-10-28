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
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .description("Get the current weather in a given location")
                .type(Tool.Type.CUSTOM)
                .build()

        val toolUnion = ToolUnion.ofTool(tool)

        assertThat(toolUnion.tool()).contains(tool)
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
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
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .description("Get the current weather in a given location")
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
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val toolUnion = ToolUnion.ofBash20250124(bash20250124)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).contains(bash20250124)
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
    }

    @Test
    fun ofBash20250124Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofBash20250124(
                ToolBash20250124.builder()
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
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
    fun ofTextEditor20250124() {
        val textEditor20250124 =
            ToolTextEditor20250124.builder()
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val toolUnion = ToolUnion.ofTextEditor20250124(textEditor20250124)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.textEditor20250124()).contains(textEditor20250124)
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
    }

    @Test
    fun ofTextEditor20250124Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofTextEditor20250124(
                ToolTextEditor20250124.builder()
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
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
    fun ofTextEditor20250429() {
        val textEditor20250429 =
            ToolTextEditor20250429.builder()
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val toolUnion = ToolUnion.ofTextEditor20250429(textEditor20250429)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).contains(textEditor20250429)
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
    }

    @Test
    fun ofTextEditor20250429Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofTextEditor20250429(
                ToolTextEditor20250429.builder()
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
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
    fun ofTextEditor20250728() {
        val textEditor20250728 =
            ToolTextEditor20250728.builder()
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .maxCharacters(1L)
                .build()

        val toolUnion = ToolUnion.ofTextEditor20250728(textEditor20250728)

        assertThat(toolUnion.tool()).isEmpty
        assertThat(toolUnion.bash20250124()).isEmpty
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).contains(textEditor20250728)
        assertThat(toolUnion.webSearchTool20250305()).isEmpty
    }

    @Test
    fun ofTextEditor20250728Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofTextEditor20250728(
                ToolTextEditor20250728.builder()
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .maxCharacters(1L)
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
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .maxUses(1L)
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
        assertThat(toolUnion.textEditor20250124()).isEmpty
        assertThat(toolUnion.textEditor20250429()).isEmpty
        assertThat(toolUnion.textEditor20250728()).isEmpty
        assertThat(toolUnion.webSearchTool20250305()).contains(webSearchTool20250305)
    }

    @Test
    fun ofWebSearchTool20250305Roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUnion =
            ToolUnion.ofWebSearchTool20250305(
                WebSearchTool20250305.builder()
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .maxUses(1L)
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
