package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaToolUnionTest {

    @Test
    fun ofBetaTool() {
        val betaTool =
            BetaTool.builder()
                .inputSchema(
                    BetaTool.InputSchema.builder()
                        .properties(
                            BetaTool.InputSchema.Properties.builder()
                                .putAdditionalProperty("location", JsonValue.from("bar"))
                                .putAdditionalProperty("unit", JsonValue.from("bar"))
                                .build()
                        )
                        .addRequired("location")
                        .build()
                )
                .name("name")
                .addAllowedCaller(BetaTool.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .description("Get the current weather in a given location")
                .eagerInputStreaming(true)
                .addInputExample(
                    BetaTool.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .type(BetaTool.Type.CUSTOM)
                .build()

        val betaToolUnion = BetaToolUnion.ofBetaTool(betaTool)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("betaTool" to Optional.of(betaTool)))
    }

    @Test
    fun ofBetaToolRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofBetaTool(
                BetaTool.builder()
                    .inputSchema(
                        BetaTool.InputSchema.builder()
                            .properties(
                                BetaTool.InputSchema.Properties.builder()
                                    .putAdditionalProperty("location", JsonValue.from("bar"))
                                    .putAdditionalProperty("unit", JsonValue.from("bar"))
                                    .build()
                            )
                            .addRequired("location")
                            .build()
                    )
                    .name("name")
                    .addAllowedCaller(BetaTool.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .description("Get the current weather in a given location")
                    .eagerInputStreaming(true)
                    .addInputExample(
                        BetaTool.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .type(BetaTool.Type.CUSTOM)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofBash20241022() {
        val bash20241022 =
            BetaToolBash20241022.builder()
                .addAllowedCaller(BetaToolBash20241022.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolBash20241022.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofBash20241022(bash20241022)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("bash20241022" to Optional.of(bash20241022)))

        assertThat(betaToolUnion.name()).contains("bash")
    }

    @Test
    fun ofBash20241022Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofBash20241022(
                BetaToolBash20241022.builder()
                    .addAllowedCaller(BetaToolBash20241022.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        BetaToolBash20241022.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofBash20250124() {
        val bash20250124 =
            BetaToolBash20250124.builder()
                .addAllowedCaller(BetaToolBash20250124.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolBash20250124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofBash20250124(bash20250124)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("bash20250124" to Optional.of(bash20250124)))

        assertThat(betaToolUnion.name()).contains("bash")
    }

    @Test
    fun ofBash20250124Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofBash20250124(
                BetaToolBash20250124.builder()
                    .addAllowedCaller(BetaToolBash20250124.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        BetaToolBash20250124.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofCodeExecutionTool20250522() {
        val codeExecutionTool20250522 =
            BetaCodeExecutionTool20250522.builder()
                .addAllowedCaller(BetaCodeExecutionTool20250522.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("codeExecutionTool20250522" to Optional.of(codeExecutionTool20250522)))

        assertThat(betaToolUnion.name()).contains("code_execution")
    }

    @Test
    fun ofCodeExecutionTool20250522Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofCodeExecutionTool20250522(
                BetaCodeExecutionTool20250522.builder()
                    .addAllowedCaller(BetaCodeExecutionTool20250522.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofCodeExecutionTool20250825() {
        val codeExecutionTool20250825 =
            BetaCodeExecutionTool20250825.builder()
                .addAllowedCaller(BetaCodeExecutionTool20250825.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("codeExecutionTool20250825" to Optional.of(codeExecutionTool20250825)))

        assertThat(betaToolUnion.name()).contains("code_execution")
    }

    @Test
    fun ofCodeExecutionTool20250825Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofCodeExecutionTool20250825(
                BetaCodeExecutionTool20250825.builder()
                    .addAllowedCaller(BetaCodeExecutionTool20250825.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofCodeExecutionTool20260120() {
        val codeExecutionTool20260120 =
            BetaCodeExecutionTool20260120.builder()
                .addAllowedCaller(BetaCodeExecutionTool20260120.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofCodeExecutionTool20260120(codeExecutionTool20260120)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("codeExecutionTool20260120" to Optional.of(codeExecutionTool20260120)))

        assertThat(betaToolUnion.name()).contains("code_execution")
    }

    @Test
    fun ofCodeExecutionTool20260120Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofCodeExecutionTool20260120(
                BetaCodeExecutionTool20260120.builder()
                    .addAllowedCaller(BetaCodeExecutionTool20260120.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofCodeExecutionTool20260521() {
        val codeExecutionTool20260521 =
            BetaCodeExecutionTool20260521.builder()
                .addAllowedCaller(BetaCodeExecutionTool20260521.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofCodeExecutionTool20260521(codeExecutionTool20260521)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("codeExecutionTool20260521" to Optional.of(codeExecutionTool20260521)))

        assertThat(betaToolUnion.name()).contains("code_execution")
    }

    @Test
    fun ofCodeExecutionTool20260521Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofCodeExecutionTool20260521(
                BetaCodeExecutionTool20260521.builder()
                    .addAllowedCaller(BetaCodeExecutionTool20260521.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofBrowserToolset20260801() {
        val browserToolset20260801 =
            BetaBrowserToolset20260801.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .configs(
                    BetaBrowserToolsetConfigs.builder()
                        .closeTab(
                            BetaBrowserCloseTabConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .doubleClick(
                            BetaBrowserDoubleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .fileUpload(
                            BetaBrowserFileUploadConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .find(
                            BetaBrowserFindConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .formInput(
                            BetaBrowserFormInputConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .getPageText(
                            BetaBrowserGetPageTextConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .holdKey(
                            BetaBrowserHoldKeyConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .hover(
                            BetaBrowserHoverConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .javascriptExec(
                            BetaBrowserJavascriptExecConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .key(
                            BetaBrowserKeyConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .leftClick(
                            BetaBrowserLeftClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftClickDrag(
                            BetaBrowserLeftClickDragConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftMouseDown(
                            BetaBrowserLeftMouseDownConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftMouseUp(
                            BetaBrowserLeftMouseUpConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .listTabs(
                            BetaBrowserListTabsConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .middleClick(
                            BetaBrowserMiddleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .mouseMove(
                            BetaBrowserMouseMoveConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .navigate(
                            BetaBrowserNavigateConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .newTab(
                            BetaBrowserNewTabConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .readConsole(
                            BetaBrowserReadConsoleConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .readNetwork(
                            BetaBrowserReadNetworkConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .readPage(
                            BetaBrowserReadPageConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .rightClick(
                            BetaBrowserRightClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .screenshot(
                            BetaBrowserScreenshotConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .scroll(
                            BetaBrowserScrollConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .scrollTo(
                            BetaBrowserScrollToConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .switchTab(
                            BetaBrowserSwitchTabConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .tripleClick(
                            BetaBrowserTripleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .type(
                            BetaBrowserTypeConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .wait(
                            BetaBrowserWaitConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .zoom(
                            BetaBrowserZoomConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .build()
                )
                .build()

        val betaToolUnion = BetaToolUnion.ofBrowserToolset20260801(browserToolset20260801)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("browserToolset20260801" to Optional.of(browserToolset20260801)))
    }

    @Test
    fun ofBrowserToolset20260801Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofBrowserToolset20260801(
                BetaBrowserToolset20260801.builder()
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .configs(
                        BetaBrowserToolsetConfigs.builder()
                            .closeTab(
                                BetaBrowserCloseTabConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .doubleClick(
                                BetaBrowserDoubleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .fileUpload(
                                BetaBrowserFileUploadConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .find(
                                BetaBrowserFindConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .formInput(
                                BetaBrowserFormInputConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .getPageText(
                                BetaBrowserGetPageTextConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .holdKey(
                                BetaBrowserHoldKeyConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .hover(
                                BetaBrowserHoverConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .javascriptExec(
                                BetaBrowserJavascriptExecConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .key(
                                BetaBrowserKeyConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftClick(
                                BetaBrowserLeftClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftClickDrag(
                                BetaBrowserLeftClickDragConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftMouseDown(
                                BetaBrowserLeftMouseDownConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftMouseUp(
                                BetaBrowserLeftMouseUpConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .listTabs(
                                BetaBrowserListTabsConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .middleClick(
                                BetaBrowserMiddleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .mouseMove(
                                BetaBrowserMouseMoveConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .navigate(
                                BetaBrowserNavigateConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .newTab(
                                BetaBrowserNewTabConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .readConsole(
                                BetaBrowserReadConsoleConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .readNetwork(
                                BetaBrowserReadNetworkConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .readPage(
                                BetaBrowserReadPageConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .rightClick(
                                BetaBrowserRightClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .screenshot(
                                BetaBrowserScreenshotConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .scroll(
                                BetaBrowserScrollConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .scrollTo(
                                BetaBrowserScrollToConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .switchTab(
                                BetaBrowserSwitchTabConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .tripleClick(
                                BetaBrowserTripleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .type(
                                BetaBrowserTypeConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .wait(
                                BetaBrowserWaitConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .zoom(
                                BetaBrowserZoomConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofComputerUse20241022() {
        val computerUse20241022 =
            BetaToolComputerUse20241022.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .addAllowedCaller(BetaToolComputerUse20241022.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .displayNumber(0L)
                .addInputExample(
                    BetaToolComputerUse20241022.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofComputerUse20241022(computerUse20241022)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("computerUse20241022" to Optional.of(computerUse20241022)))

        assertThat(betaToolUnion.name()).contains("computer")
    }

    @Test
    fun ofComputerUse20241022Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofComputerUse20241022(
                BetaToolComputerUse20241022.builder()
                    .displayHeightPx(1L)
                    .displayWidthPx(1L)
                    .addAllowedCaller(BetaToolComputerUse20241022.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .displayNumber(0L)
                    .addInputExample(
                        BetaToolComputerUse20241022.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofMemoryTool20250818() {
        val memoryTool20250818 =
            BetaMemoryTool20250818.builder()
                .addAllowedCaller(BetaMemoryTool20250818.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaMemoryTool20250818.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofMemoryTool20250818(memoryTool20250818)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("memoryTool20250818" to Optional.of(memoryTool20250818)))

        assertThat(betaToolUnion.name()).contains("memory")
    }

    @Test
    fun ofMemoryTool20250818Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofMemoryTool20250818(
                BetaMemoryTool20250818.builder()
                    .addAllowedCaller(BetaMemoryTool20250818.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        BetaMemoryTool20250818.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofComputerUse20250124() {
        val computerUse20250124 =
            BetaToolComputerUse20250124.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .addAllowedCaller(BetaToolComputerUse20250124.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .displayNumber(0L)
                .addInputExample(
                    BetaToolComputerUse20250124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofComputerUse20250124(computerUse20250124)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("computerUse20250124" to Optional.of(computerUse20250124)))

        assertThat(betaToolUnion.name()).contains("computer")
    }

    @Test
    fun ofComputerUse20250124Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofComputerUse20250124(
                BetaToolComputerUse20250124.builder()
                    .displayHeightPx(1L)
                    .displayWidthPx(1L)
                    .addAllowedCaller(BetaToolComputerUse20250124.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .displayNumber(0L)
                    .addInputExample(
                        BetaToolComputerUse20250124.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofTextEditor20241022() {
        val textEditor20241022 =
            BetaToolTextEditor20241022.builder()
                .addAllowedCaller(BetaToolTextEditor20241022.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolTextEditor20241022.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofTextEditor20241022(textEditor20241022)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("textEditor20241022" to Optional.of(textEditor20241022)))

        assertThat(betaToolUnion.name()).contains("str_replace_editor")
    }

    @Test
    fun ofTextEditor20241022Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofTextEditor20241022(
                BetaToolTextEditor20241022.builder()
                    .addAllowedCaller(BetaToolTextEditor20241022.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        BetaToolTextEditor20241022.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofComputerUse20251124() {
        val computerUse20251124 =
            BetaToolComputerUse20251124.builder()
                .displayHeightPx(1L)
                .displayWidthPx(1L)
                .addAllowedCaller(BetaToolComputerUse20251124.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .displayNumber(0L)
                .enableZoom(true)
                .addInputExample(
                    BetaToolComputerUse20251124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofComputerUse20251124(computerUse20251124)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("computerUse20251124" to Optional.of(computerUse20251124)))

        assertThat(betaToolUnion.name()).contains("computer")
    }

    @Test
    fun ofComputerUse20251124Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofComputerUse20251124(
                BetaToolComputerUse20251124.builder()
                    .displayHeightPx(1L)
                    .displayWidthPx(1L)
                    .addAllowedCaller(BetaToolComputerUse20251124.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .displayNumber(0L)
                    .enableZoom(true)
                    .addInputExample(
                        BetaToolComputerUse20251124.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofComputerToolset20260801() {
        val computerToolset20260801 =
            BetaComputerToolset20260801.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .configs(
                    BetaComputerToolsetConfigs.builder()
                        .cursorPosition(
                            BetaComputerCursorPositionConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .doubleClick(
                            BetaComputerDoubleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .holdKey(
                            BetaComputerHoldKeyConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .key(
                            BetaComputerKeyConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .leftClick(
                            BetaComputerLeftClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftClickDrag(
                            BetaComputerLeftClickDragConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftMouseDown(
                            BetaComputerLeftMouseDownConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftMouseUp(
                            BetaComputerLeftMouseUpConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .middleClick(
                            BetaComputerMiddleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .mouseMove(
                            BetaComputerMouseMoveConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .rightClick(
                            BetaComputerRightClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .screenshot(
                            BetaComputerScreenshotConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .scroll(
                            BetaComputerScrollConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .tripleClick(
                            BetaComputerTripleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .type(
                            BetaComputerTypeConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .wait(
                            BetaComputerWaitConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .zoom(
                            BetaComputerZoomConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .build()
                )
                .build()

        val betaToolUnion = BetaToolUnion.ofComputerToolset20260801(computerToolset20260801)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("computerToolset20260801" to Optional.of(computerToolset20260801)))
    }

    @Test
    fun ofComputerToolset20260801Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofComputerToolset20260801(
                BetaComputerToolset20260801.builder()
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .configs(
                        BetaComputerToolsetConfigs.builder()
                            .cursorPosition(
                                BetaComputerCursorPositionConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .doubleClick(
                                BetaComputerDoubleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .holdKey(
                                BetaComputerHoldKeyConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .key(
                                BetaComputerKeyConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftClick(
                                BetaComputerLeftClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftClickDrag(
                                BetaComputerLeftClickDragConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftMouseDown(
                                BetaComputerLeftMouseDownConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftMouseUp(
                                BetaComputerLeftMouseUpConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .middleClick(
                                BetaComputerMiddleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .mouseMove(
                                BetaComputerMouseMoveConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .rightClick(
                                BetaComputerRightClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .screenshot(
                                BetaComputerScreenshotConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .scroll(
                                BetaComputerScrollConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .tripleClick(
                                BetaComputerTripleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .type(
                                BetaComputerTypeConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .wait(
                                BetaComputerWaitConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .zoom(
                                BetaComputerZoomConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofTextEditor20250124() {
        val textEditor20250124 =
            BetaToolTextEditor20250124.builder()
                .addAllowedCaller(BetaToolTextEditor20250124.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolTextEditor20250124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofTextEditor20250124(textEditor20250124)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("textEditor20250124" to Optional.of(textEditor20250124)))

        assertThat(betaToolUnion.name()).contains("str_replace_editor")
    }

    @Test
    fun ofTextEditor20250124Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofTextEditor20250124(
                BetaToolTextEditor20250124.builder()
                    .addAllowedCaller(BetaToolTextEditor20250124.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        BetaToolTextEditor20250124.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofTextEditor20250429() {
        val textEditor20250429 =
            BetaToolTextEditor20250429.builder()
                .addAllowedCaller(BetaToolTextEditor20250429.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolTextEditor20250429.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofTextEditor20250429(textEditor20250429)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("textEditor20250429" to Optional.of(textEditor20250429)))

        assertThat(betaToolUnion.name()).contains("str_replace_based_edit_tool")
    }

    @Test
    fun ofTextEditor20250429Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofTextEditor20250429(
                BetaToolTextEditor20250429.builder()
                    .addAllowedCaller(BetaToolTextEditor20250429.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        BetaToolTextEditor20250429.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofTextEditor20250728() {
        val textEditor20250728 =
            BetaToolTextEditor20250728.builder()
                .addAllowedCaller(BetaToolTextEditor20250728.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolTextEditor20250728.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .maxCharacters(1L)
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofTextEditor20250728(textEditor20250728)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("textEditor20250728" to Optional.of(textEditor20250728)))

        assertThat(betaToolUnion.name()).contains("str_replace_based_edit_tool")
    }

    @Test
    fun ofTextEditor20250728Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofTextEditor20250728(
                BetaToolTextEditor20250728.builder()
                    .addAllowedCaller(BetaToolTextEditor20250728.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .addInputExample(
                        BetaToolTextEditor20250728.InputExample.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .maxCharacters(1L)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofWebSearchTool20250305() {
        val webSearchTool20250305 =
            BetaWebSearchTool20250305.builder()
                .addAllowedCaller(BetaWebSearchTool20250305.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .strict(true)
                .userLocation(
                    BetaUserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val betaToolUnion = BetaToolUnion.ofWebSearchTool20250305(webSearchTool20250305)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("webSearchTool20250305" to Optional.of(webSearchTool20250305)))

        assertThat(betaToolUnion.name()).contains("web_search")
    }

    @Test
    fun ofWebSearchTool20250305Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofWebSearchTool20250305(
                BetaWebSearchTool20250305.builder()
                    .addAllowedCaller(BetaWebSearchTool20250305.AllowedCaller.DIRECT)
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .maxUses(1L)
                    .strict(true)
                    .userLocation(
                        BetaUserLocation.builder()
                            .city("New York")
                            .country("US")
                            .region("California")
                            .timezone("America/New_York")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofWebFetchTool20250910() {
        val webFetchTool20250910 =
            BetaWebFetchTool20250910.builder()
                .addAllowedCaller(BetaWebFetchTool20250910.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .deferLoading(true)
                .maxContentTokens(1L)
                .maxUses(1L)
                .strict(true)
                .urlSources(
                    BetaWebFetchUrlSources.builder()
                        .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                        .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                        .userInput(BetaWebFetchUrlSourceAll.builder().build())
                        .build()
                )
                .build()

        val betaToolUnion = BetaToolUnion.ofWebFetchTool20250910(webFetchTool20250910)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("webFetchTool20250910" to Optional.of(webFetchTool20250910)))

        assertThat(betaToolUnion.name()).contains("web_fetch")
    }

    @Test
    fun ofWebFetchTool20250910Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofWebFetchTool20250910(
                BetaWebFetchTool20250910.builder()
                    .addAllowedCaller(BetaWebFetchTool20250910.AllowedCaller.DIRECT)
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                    .deferLoading(true)
                    .maxContentTokens(1L)
                    .maxUses(1L)
                    .strict(true)
                    .urlSources(
                        BetaWebFetchUrlSources.builder()
                            .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                            .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                            .userInput(BetaWebFetchUrlSourceAll.builder().build())
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofWebSearchTool20260209() {
        val webSearchTool20260209 =
            BetaWebSearchTool20260209.builder()
                .addAllowedCaller(BetaWebSearchTool20260209.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .strict(true)
                .userLocation(
                    BetaUserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val betaToolUnion = BetaToolUnion.ofWebSearchTool20260209(webSearchTool20260209)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("webSearchTool20260209" to Optional.of(webSearchTool20260209)))

        assertThat(betaToolUnion.name()).contains("web_search")
    }

    @Test
    fun ofWebSearchTool20260209Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofWebSearchTool20260209(
                BetaWebSearchTool20260209.builder()
                    .addAllowedCaller(BetaWebSearchTool20260209.AllowedCaller.DIRECT)
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .maxUses(1L)
                    .strict(true)
                    .userLocation(
                        BetaUserLocation.builder()
                            .city("New York")
                            .country("US")
                            .region("California")
                            .timezone("America/New_York")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofWebFetchTool20260209() {
        val webFetchTool20260209 =
            BetaWebFetchTool20260209.builder()
                .addAllowedCaller(BetaWebFetchTool20260209.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .deferLoading(true)
                .maxContentTokens(1L)
                .maxUses(1L)
                .strict(true)
                .urlSources(
                    BetaWebFetchUrlSources.builder()
                        .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                        .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                        .userInput(BetaWebFetchUrlSourceAll.builder().build())
                        .build()
                )
                .build()

        val betaToolUnion = BetaToolUnion.ofWebFetchTool20260209(webFetchTool20260209)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("webFetchTool20260209" to Optional.of(webFetchTool20260209)))

        assertThat(betaToolUnion.name()).contains("web_fetch")
    }

    @Test
    fun ofWebFetchTool20260209Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofWebFetchTool20260209(
                BetaWebFetchTool20260209.builder()
                    .addAllowedCaller(BetaWebFetchTool20260209.AllowedCaller.DIRECT)
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                    .deferLoading(true)
                    .maxContentTokens(1L)
                    .maxUses(1L)
                    .strict(true)
                    .urlSources(
                        BetaWebFetchUrlSources.builder()
                            .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                            .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                            .userInput(BetaWebFetchUrlSourceAll.builder().build())
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofWebFetchTool20260309() {
        val webFetchTool20260309 =
            BetaWebFetchTool20260309.builder()
                .addAllowedCaller(BetaWebFetchTool20260309.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .deferLoading(true)
                .maxContentTokens(1L)
                .maxUses(1L)
                .strict(true)
                .urlSources(
                    BetaWebFetchUrlSources.builder()
                        .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                        .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                        .userInput(BetaWebFetchUrlSourceAll.builder().build())
                        .build()
                )
                .useCache(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofWebFetchTool20260309(webFetchTool20260309)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("webFetchTool20260309" to Optional.of(webFetchTool20260309)))

        assertThat(betaToolUnion.name()).contains("web_fetch")
    }

    @Test
    fun ofWebFetchTool20260309Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofWebFetchTool20260309(
                BetaWebFetchTool20260309.builder()
                    .addAllowedCaller(BetaWebFetchTool20260309.AllowedCaller.DIRECT)
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                    .deferLoading(true)
                    .maxContentTokens(1L)
                    .maxUses(1L)
                    .strict(true)
                    .urlSources(
                        BetaWebFetchUrlSources.builder()
                            .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                            .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                            .userInput(BetaWebFetchUrlSourceAll.builder().build())
                            .build()
                    )
                    .useCache(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofWebSearchTool20260318() {
        val webSearchTool20260318 =
            BetaWebSearchTool20260318.builder()
                .addAllowedCaller(BetaWebSearchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .responseInclusion(BetaWebSearchTool20260318.ResponseInclusion.FULL)
                .strict(true)
                .userLocation(
                    BetaUserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val betaToolUnion = BetaToolUnion.ofWebSearchTool20260318(webSearchTool20260318)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("webSearchTool20260318" to Optional.of(webSearchTool20260318)))

        assertThat(betaToolUnion.name()).contains("web_search")
    }

    @Test
    fun ofWebSearchTool20260318Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofWebSearchTool20260318(
                BetaWebSearchTool20260318.builder()
                    .addAllowedCaller(BetaWebSearchTool20260318.AllowedCaller.DIRECT)
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .maxUses(1L)
                    .responseInclusion(BetaWebSearchTool20260318.ResponseInclusion.FULL)
                    .strict(true)
                    .userLocation(
                        BetaUserLocation.builder()
                            .city("New York")
                            .country("US")
                            .region("California")
                            .timezone("America/New_York")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofWebFetchTool20260318() {
        val webFetchTool20260318 =
            BetaWebFetchTool20260318.builder()
                .addAllowedCaller(BetaWebFetchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .deferLoading(true)
                .maxContentTokens(1L)
                .maxUses(1L)
                .responseInclusion(BetaWebFetchTool20260318.ResponseInclusion.FULL)
                .strict(true)
                .urlSources(
                    BetaWebFetchUrlSources.builder()
                        .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                        .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                        .userInput(BetaWebFetchUrlSourceAll.builder().build())
                        .build()
                )
                .useCache(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofWebFetchTool20260318(webFetchTool20260318)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("webFetchTool20260318" to Optional.of(webFetchTool20260318)))

        assertThat(betaToolUnion.name()).contains("web_fetch")
    }

    @Test
    fun ofWebFetchTool20260318Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofWebFetchTool20260318(
                BetaWebFetchTool20260318.builder()
                    .addAllowedCaller(BetaWebFetchTool20260318.AllowedCaller.DIRECT)
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                    .deferLoading(true)
                    .maxContentTokens(1L)
                    .maxUses(1L)
                    .responseInclusion(BetaWebFetchTool20260318.ResponseInclusion.FULL)
                    .strict(true)
                    .urlSources(
                        BetaWebFetchUrlSources.builder()
                            .clientToolResults(BetaWebFetchUrlSourceAll.builder().build())
                            .serverToolResults(BetaWebFetchUrlSourceAll.builder().build())
                            .userInput(BetaWebFetchUrlSourceAll.builder().build())
                            .build()
                    )
                    .useCache(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofAdvisorTool20260301() {
        val advisorTool20260301 =
            BetaAdvisorTool20260301.builder()
                .model(Model.CLAUDE_FABLE_5_2)
                .addAllowedCaller(BetaAdvisorTool20260301.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .caching(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .maxTokens(1024L)
                .maxUses(1L)
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofAdvisorTool20260301(advisorTool20260301)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("advisorTool20260301" to Optional.of(advisorTool20260301)))

        assertThat(betaToolUnion.name()).contains("advisor")
    }

    @Test
    fun ofAdvisorTool20260301Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofAdvisorTool20260301(
                BetaAdvisorTool20260301.builder()
                    .model(Model.CLAUDE_FABLE_5_2)
                    .addAllowedCaller(BetaAdvisorTool20260301.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .caching(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .maxTokens(1024L)
                    .maxUses(1L)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofSearchToolBm25_20251119() {
        val searchToolBm25_20251119 =
            BetaToolSearchToolBm25_20251119.builder()
                .type(BetaToolSearchToolBm25_20251119.Type.TOOL_SEARCH_TOOL_BM25_20251119)
                .addAllowedCaller(BetaToolSearchToolBm25_20251119.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofSearchToolBm25_20251119(searchToolBm25_20251119)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("searchToolBm25_20251119" to Optional.of(searchToolBm25_20251119)))

        assertThat(betaToolUnion.name()).contains("tool_search_tool_bm25")
    }

    @Test
    fun ofSearchToolBm25_20251119Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofSearchToolBm25_20251119(
                BetaToolSearchToolBm25_20251119.builder()
                    .type(BetaToolSearchToolBm25_20251119.Type.TOOL_SEARCH_TOOL_BM25_20251119)
                    .addAllowedCaller(BetaToolSearchToolBm25_20251119.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofSearchToolRegex20251119() {
        val searchToolRegex20251119 =
            BetaToolSearchToolRegex20251119.builder()
                .type(BetaToolSearchToolRegex20251119.Type.TOOL_SEARCH_TOOL_REGEX_20251119)
                .addAllowedCaller(BetaToolSearchToolRegex20251119.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val betaToolUnion = BetaToolUnion.ofSearchToolRegex20251119(searchToolRegex20251119)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("searchToolRegex20251119" to Optional.of(searchToolRegex20251119)))

        assertThat(betaToolUnion.name()).contains("tool_search_tool_regex")
    }

    @Test
    fun ofSearchToolRegex20251119Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofSearchToolRegex20251119(
                BetaToolSearchToolRegex20251119.builder()
                    .type(BetaToolSearchToolRegex20251119.Type.TOOL_SEARCH_TOOL_REGEX_20251119)
                    .addAllowedCaller(BetaToolSearchToolRegex20251119.AllowedCaller.DIRECT)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .strict(true)
                    .build()
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
    }

    @Test
    fun ofMcpToolset() {
        val mcpToolset =
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

        val betaToolUnion = BetaToolUnion.ofMcpToolset(mcpToolset)

        assertThat(presentVariants(betaToolUnion))
            .isEqualTo(mapOf("mcpToolset" to Optional.of(mcpToolset)))
    }

    @Test
    fun ofMcpToolsetRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUnion =
            BetaToolUnion.ofMcpToolset(
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
            )

        val roundtrippedBetaToolUnion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUnion),
                jacksonTypeRef<BetaToolUnion>(),
            )

        assertThat(roundtrippedBetaToolUnion).isEqualTo(betaToolUnion)
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
        val betaToolUnion =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaToolUnion>())

        val e = assertThrows<AnthropicInvalidDataException> { betaToolUnion.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaToolUnion.name()).isEmpty
        assertThat(betaToolUnion.cacheControl()).isEmpty
        assertThat(betaToolUnion.deferLoading()).isEmpty
        assertThat(betaToolUnion.strict()).isEmpty
        assertThat(betaToolUnion.displayHeightPx()).isEmpty
        assertThat(betaToolUnion.displayWidthPx()).isEmpty
        assertThat(betaToolUnion.displayNumber()).isEmpty
        assertThat(betaToolUnion.allowedDomains()).isEmpty
        assertThat(betaToolUnion.blockedDomains()).isEmpty
        assertThat(betaToolUnion.maxUses()).isEmpty
        assertThat(betaToolUnion.userLocation()).isEmpty
        assertThat(betaToolUnion.citations()).isEmpty
        assertThat(betaToolUnion.maxContentTokens()).isEmpty
        assertThat(betaToolUnion.urlSources()).isEmpty
        assertThat(betaToolUnion.useCache()).isEmpty
    }

    private fun presentVariants(betaToolUnion: BetaToolUnion): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "betaTool" to betaToolUnion.betaTool(),
                "bash20241022" to betaToolUnion.bash20241022(),
                "bash20250124" to betaToolUnion.bash20250124(),
                "codeExecutionTool20250522" to betaToolUnion.codeExecutionTool20250522(),
                "codeExecutionTool20250825" to betaToolUnion.codeExecutionTool20250825(),
                "codeExecutionTool20260120" to betaToolUnion.codeExecutionTool20260120(),
                "codeExecutionTool20260521" to betaToolUnion.codeExecutionTool20260521(),
                "browserToolset20260801" to betaToolUnion.browserToolset20260801(),
                "computerUse20241022" to betaToolUnion.computerUse20241022(),
                "memoryTool20250818" to betaToolUnion.memoryTool20250818(),
                "computerUse20250124" to betaToolUnion.computerUse20250124(),
                "textEditor20241022" to betaToolUnion.textEditor20241022(),
                "computerUse20251124" to betaToolUnion.computerUse20251124(),
                "computerToolset20260801" to betaToolUnion.computerToolset20260801(),
                "textEditor20250124" to betaToolUnion.textEditor20250124(),
                "textEditor20250429" to betaToolUnion.textEditor20250429(),
                "textEditor20250728" to betaToolUnion.textEditor20250728(),
                "webSearchTool20250305" to betaToolUnion.webSearchTool20250305(),
                "webFetchTool20250910" to betaToolUnion.webFetchTool20250910(),
                "webSearchTool20260209" to betaToolUnion.webSearchTool20260209(),
                "webFetchTool20260209" to betaToolUnion.webFetchTool20260209(),
                "webFetchTool20260309" to betaToolUnion.webFetchTool20260309(),
                "webSearchTool20260318" to betaToolUnion.webSearchTool20260318(),
                "webFetchTool20260318" to betaToolUnion.webFetchTool20260318(),
                "advisorTool20260301" to betaToolUnion.advisorTool20260301(),
                "searchToolBm25_20251119" to betaToolUnion.searchToolBm25_20251119(),
                "searchToolRegex20251119" to betaToolUnion.searchToolRegex20251119(),
                "mcpToolset" to betaToolUnion.mcpToolset(),
            )
            .filterValues { it.isPresent }
}
