package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMcpToolsetTest {

    @Test
    fun create() {
        val betaMcpToolset =
            BetaMcpToolset.builder()
                .mcpServerName("x")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .configs(
                    BetaMcpToolset.Configs.builder()
                        .putAdditionalProperty(
                            "foo",
                            JsonValue.from(mapOf("defer_loading" to true, "enabled" to true)),
                        )
                        .build()
                )
                .defaultConfig(
                    BetaMcpToolDefaultConfig.builder().deferLoading(true).enabled(true).build()
                )
                .addTool(
                    BetaMcpToolParam.builder()
                        .inputSchema(
                            BetaMcpToolParam.InputSchema.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .name("x")
                        .description("description")
                        .build()
                )
                .build()

        assertThat(betaMcpToolset.mcpServerName()).isEqualTo("x")
        assertThat(betaMcpToolset.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaMcpToolset.configs())
            .contains(
                BetaMcpToolset.Configs.builder()
                    .putAdditionalProperty(
                        "foo",
                        JsonValue.from(mapOf("defer_loading" to true, "enabled" to true)),
                    )
                    .build()
            )
        assertThat(betaMcpToolset.defaultConfig())
            .contains(BetaMcpToolDefaultConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(betaMcpToolset.tools().getOrNull())
            .containsExactly(
                BetaMcpToolParam.builder()
                    .inputSchema(
                        BetaMcpToolParam.InputSchema.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .name("x")
                    .description("description")
                    .build()
            )
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaMcpToolset = BetaMcpToolset.of("x")

        val betaMcpToolset =
            baseBetaMcpToolset
                .toBuilder()
                .addTool(
                    BetaMcpToolParam.builder()
                        .inputSchema(
                            BetaMcpToolParam.InputSchema.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .name("x")
                        .description("description")
                        .build()
                )
                .build()

        assertThat(betaMcpToolset.tools().getOrNull())
            .containsExactly(
                BetaMcpToolParam.builder()
                    .inputSchema(
                        BetaMcpToolParam.InputSchema.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .name("x")
                    .description("description")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMcpToolset =
            BetaMcpToolset.builder()
                .mcpServerName("x")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .configs(
                    BetaMcpToolset.Configs.builder()
                        .putAdditionalProperty(
                            "foo",
                            JsonValue.from(mapOf("defer_loading" to true, "enabled" to true)),
                        )
                        .build()
                )
                .defaultConfig(
                    BetaMcpToolDefaultConfig.builder().deferLoading(true).enabled(true).build()
                )
                .addTool(
                    BetaMcpToolParam.builder()
                        .inputSchema(
                            BetaMcpToolParam.InputSchema.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .name("x")
                        .description("description")
                        .build()
                )
                .build()

        val roundtrippedBetaMcpToolset =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMcpToolset),
                jacksonTypeRef<BetaMcpToolset>(),
            )

        assertThat(roundtrippedBetaMcpToolset).isEqualTo(betaMcpToolset)
    }
}
