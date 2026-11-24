package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
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

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("tool" to Optional.of(tool)))
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

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("toolBash20250124" to Optional.of(toolBash20250124)))

        assertThat(messageCountTokensTool.name()).contains("bash")
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

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("codeExecutionTool20250522" to Optional.of(codeExecutionTool20250522)))

        assertThat(messageCountTokensTool.name()).contains("code_execution")
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

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("codeExecutionTool20250825" to Optional.of(codeExecutionTool20250825)))

        assertThat(messageCountTokensTool.name()).contains("code_execution")
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
            CodeExecutionTool20260120.builder()
                .addAllowedCaller(CodeExecutionTool20260120.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofCodeExecutionTool20260120(codeExecutionTool20260120)

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("codeExecutionTool20260120" to Optional.of(codeExecutionTool20260120)))

        assertThat(messageCountTokensTool.name()).contains("code_execution")
    }

    @Test
    fun ofCodeExecutionTool20260120Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofCodeExecutionTool20260120(
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

        val roundtrippedMessageCountTokensTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCountTokensTool),
                jacksonTypeRef<MessageCountTokensTool>(),
            )

        assertThat(roundtrippedMessageCountTokensTool).isEqualTo(messageCountTokensTool)
    }

    @Test
    fun ofCodeExecutionTool20260521() {
        val codeExecutionTool20260521 =
            CodeExecutionTool20260521.builder()
                .addAllowedCaller(CodeExecutionTool20260521.AllowedCaller.DIRECT)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofCodeExecutionTool20260521(codeExecutionTool20260521)

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("codeExecutionTool20260521" to Optional.of(codeExecutionTool20260521)))

        assertThat(messageCountTokensTool.name()).contains("code_execution")
    }

    @Test
    fun ofCodeExecutionTool20260521Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofCodeExecutionTool20260521(
                CodeExecutionTool20260521.builder()
                    .addAllowedCaller(CodeExecutionTool20260521.AllowedCaller.DIRECT)
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
    fun ofBrowserToolset20260801() {
        val browserToolset20260801 =
            BrowserToolset20260801.builder()
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .configs(
                    BrowserToolsetConfigs.builder()
                        .closeTab(
                            BrowserCloseTabConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .doubleClick(
                            BrowserDoubleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .fileUpload(
                            BrowserFileUploadConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .find(BrowserFindConfig.builder().deferLoading(true).enabled(true).build())
                        .formInput(
                            BrowserFormInputConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .getPageText(
                            BrowserGetPageTextConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .holdKey(
                            BrowserHoldKeyConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .hover(
                            BrowserHoverConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .javascriptExec(
                            BrowserJavascriptExecConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .key(BrowserKeyConfig.builder().deferLoading(true).enabled(true).build())
                        .leftClick(
                            BrowserLeftClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftClickDrag(
                            BrowserLeftClickDragConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftMouseDown(
                            BrowserLeftMouseDownConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftMouseUp(
                            BrowserLeftMouseUpConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .listTabs(
                            BrowserListTabsConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .middleClick(
                            BrowserMiddleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .mouseMove(
                            BrowserMouseMoveConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .navigate(
                            BrowserNavigateConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .newTab(
                            BrowserNewTabConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .readConsole(
                            BrowserReadConsoleConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .readNetwork(
                            BrowserReadNetworkConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .readPage(
                            BrowserReadPageConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .rightClick(
                            BrowserRightClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .screenshot(
                            BrowserScreenshotConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .scroll(
                            BrowserScrollConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .scrollTo(
                            BrowserScrollToConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .switchTab(
                            BrowserSwitchTabConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .tripleClick(
                            BrowserTripleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .type(BrowserTypeConfig.builder().deferLoading(true).enabled(true).build())
                        .wait(BrowserWaitConfig.builder().deferLoading(true).enabled(true).build())
                        .zoom(BrowserZoomConfig.builder().deferLoading(true).enabled(true).build())
                        .build()
                )
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofBrowserToolset20260801(browserToolset20260801)

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("browserToolset20260801" to Optional.of(browserToolset20260801)))
    }

    @Test
    fun ofBrowserToolset20260801Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofBrowserToolset20260801(
                BrowserToolset20260801.builder()
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .configs(
                        BrowserToolsetConfigs.builder()
                            .closeTab(
                                BrowserCloseTabConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .doubleClick(
                                BrowserDoubleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .fileUpload(
                                BrowserFileUploadConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .find(
                                BrowserFindConfig.builder().deferLoading(true).enabled(true).build()
                            )
                            .formInput(
                                BrowserFormInputConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .getPageText(
                                BrowserGetPageTextConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .holdKey(
                                BrowserHoldKeyConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .hover(
                                BrowserHoverConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .javascriptExec(
                                BrowserJavascriptExecConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .key(
                                BrowserKeyConfig.builder().deferLoading(true).enabled(true).build()
                            )
                            .leftClick(
                                BrowserLeftClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftClickDrag(
                                BrowserLeftClickDragConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftMouseDown(
                                BrowserLeftMouseDownConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftMouseUp(
                                BrowserLeftMouseUpConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .listTabs(
                                BrowserListTabsConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .middleClick(
                                BrowserMiddleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .mouseMove(
                                BrowserMouseMoveConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .navigate(
                                BrowserNavigateConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .newTab(
                                BrowserNewTabConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .readConsole(
                                BrowserReadConsoleConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .readNetwork(
                                BrowserReadNetworkConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .readPage(
                                BrowserReadPageConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .rightClick(
                                BrowserRightClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .screenshot(
                                BrowserScreenshotConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .scroll(
                                BrowserScrollConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .scrollTo(
                                BrowserScrollToConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .switchTab(
                                BrowserSwitchTabConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .tripleClick(
                                BrowserTripleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .type(
                                BrowserTypeConfig.builder().deferLoading(true).enabled(true).build()
                            )
                            .wait(
                                BrowserWaitConfig.builder().deferLoading(true).enabled(true).build()
                            )
                            .zoom(
                                BrowserZoomConfig.builder().deferLoading(true).enabled(true).build()
                            )
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

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("memoryTool20250818" to Optional.of(memoryTool20250818)))

        assertThat(messageCountTokensTool.name()).contains("memory")
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
    fun ofComputerToolset20260801() {
        val computerToolset20260801 =
            ComputerToolset20260801.builder()
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .configs(
                    ComputerToolsetConfigs.builder()
                        .cursorPosition(
                            ComputerCursorPositionConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .doubleClick(
                            ComputerDoubleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .holdKey(
                            ComputerHoldKeyConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .key(ComputerKeyConfig.builder().deferLoading(true).enabled(true).build())
                        .leftClick(
                            ComputerLeftClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftClickDrag(
                            ComputerLeftClickDragConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftMouseDown(
                            ComputerLeftMouseDownConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .leftMouseUp(
                            ComputerLeftMouseUpConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .middleClick(
                            ComputerMiddleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .mouseMove(
                            ComputerMouseMoveConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .rightClick(
                            ComputerRightClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .screenshot(
                            ComputerScreenshotConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .scroll(
                            ComputerScrollConfig.builder().deferLoading(true).enabled(true).build()
                        )
                        .tripleClick(
                            ComputerTripleClickConfig.builder()
                                .deferLoading(true)
                                .enabled(true)
                                .build()
                        )
                        .type(ComputerTypeConfig.builder().deferLoading(true).enabled(true).build())
                        .wait(ComputerWaitConfig.builder().deferLoading(true).enabled(true).build())
                        .zoom(ComputerZoomConfig.builder().deferLoading(true).enabled(true).build())
                        .build()
                )
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofComputerToolset20260801(computerToolset20260801)

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("computerToolset20260801" to Optional.of(computerToolset20260801)))
    }

    @Test
    fun ofComputerToolset20260801Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofComputerToolset20260801(
                ComputerToolset20260801.builder()
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .configs(
                        ComputerToolsetConfigs.builder()
                            .cursorPosition(
                                ComputerCursorPositionConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .doubleClick(
                                ComputerDoubleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .holdKey(
                                ComputerHoldKeyConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .key(
                                ComputerKeyConfig.builder().deferLoading(true).enabled(true).build()
                            )
                            .leftClick(
                                ComputerLeftClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftClickDrag(
                                ComputerLeftClickDragConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftMouseDown(
                                ComputerLeftMouseDownConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .leftMouseUp(
                                ComputerLeftMouseUpConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .middleClick(
                                ComputerMiddleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .mouseMove(
                                ComputerMouseMoveConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .rightClick(
                                ComputerRightClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .screenshot(
                                ComputerScreenshotConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .scroll(
                                ComputerScrollConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .tripleClick(
                                ComputerTripleClickConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .type(
                                ComputerTypeConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .wait(
                                ComputerWaitConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
                            .zoom(
                                ComputerZoomConfig.builder()
                                    .deferLoading(true)
                                    .enabled(true)
                                    .build()
                            )
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

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("toolTextEditor20250124" to Optional.of(toolTextEditor20250124)))

        assertThat(messageCountTokensTool.name()).contains("str_replace_editor")
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

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("toolTextEditor20250429" to Optional.of(toolTextEditor20250429)))

        assertThat(messageCountTokensTool.name()).contains("str_replace_based_edit_tool")
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

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("toolTextEditor20250728" to Optional.of(toolTextEditor20250728)))

        assertThat(messageCountTokensTool.name()).contains("str_replace_based_edit_tool")
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
                    UserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofWebSearchTool20250305(webSearchTool20250305)

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("webSearchTool20250305" to Optional.of(webSearchTool20250305)))

        assertThat(messageCountTokensTool.name()).contains("web_search")
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
                        UserLocation.builder()
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
                .urlSources(
                    WebFetchUrlSources.builder()
                        .clientToolResults(WebFetchUrlSourceAll.builder().build())
                        .serverToolResults(WebFetchUrlSourceAll.builder().build())
                        .userInput(WebFetchUrlSourceAll.builder().build())
                        .build()
                )
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofWebFetchTool20250910(webFetchTool20250910)

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("webFetchTool20250910" to Optional.of(webFetchTool20250910)))

        assertThat(messageCountTokensTool.name()).contains("web_fetch")
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
                    .urlSources(
                        WebFetchUrlSources.builder()
                            .clientToolResults(WebFetchUrlSourceAll.builder().build())
                            .serverToolResults(WebFetchUrlSourceAll.builder().build())
                            .userInput(WebFetchUrlSourceAll.builder().build())
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
                    UserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofWebSearchTool20260209(webSearchTool20260209)

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("webSearchTool20260209" to Optional.of(webSearchTool20260209)))

        assertThat(messageCountTokensTool.name()).contains("web_search")
    }

    @Test
    fun ofWebSearchTool20260209Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofWebSearchTool20260209(
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
                        UserLocation.builder()
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
                .urlSources(
                    WebFetchUrlSources.builder()
                        .clientToolResults(WebFetchUrlSourceAll.builder().build())
                        .serverToolResults(WebFetchUrlSourceAll.builder().build())
                        .userInput(WebFetchUrlSourceAll.builder().build())
                        .build()
                )
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofWebFetchTool20260209(webFetchTool20260209)

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("webFetchTool20260209" to Optional.of(webFetchTool20260209)))

        assertThat(messageCountTokensTool.name()).contains("web_fetch")
    }

    @Test
    fun ofWebFetchTool20260209Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofWebFetchTool20260209(
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
                    .urlSources(
                        WebFetchUrlSources.builder()
                            .clientToolResults(WebFetchUrlSourceAll.builder().build())
                            .serverToolResults(WebFetchUrlSourceAll.builder().build())
                            .userInput(WebFetchUrlSourceAll.builder().build())
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
    fun ofWebFetchTool20260309() {
        val webFetchTool20260309 =
            WebFetchTool20260309.builder()
                .addAllowedCaller(WebFetchTool20260309.AllowedCaller.DIRECT)
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
                .urlSources(
                    WebFetchUrlSources.builder()
                        .clientToolResults(WebFetchUrlSourceAll.builder().build())
                        .serverToolResults(WebFetchUrlSourceAll.builder().build())
                        .userInput(WebFetchUrlSourceAll.builder().build())
                        .build()
                )
                .useCache(true)
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofWebFetchTool20260309(webFetchTool20260309)

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("webFetchTool20260309" to Optional.of(webFetchTool20260309)))

        assertThat(messageCountTokensTool.name()).contains("web_fetch")
    }

    @Test
    fun ofWebFetchTool20260309Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofWebFetchTool20260309(
                WebFetchTool20260309.builder()
                    .addAllowedCaller(WebFetchTool20260309.AllowedCaller.DIRECT)
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
                    .urlSources(
                        WebFetchUrlSources.builder()
                            .clientToolResults(WebFetchUrlSourceAll.builder().build())
                            .serverToolResults(WebFetchUrlSourceAll.builder().build())
                            .userInput(WebFetchUrlSourceAll.builder().build())
                            .build()
                    )
                    .useCache(true)
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
    fun ofWebSearchTool20260318() {
        val webSearchTool20260318 =
            WebSearchTool20260318.builder()
                .addAllowedCaller(WebSearchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .responseInclusion(WebSearchTool20260318.ResponseInclusion.FULL)
                .strict(true)
                .userLocation(
                    UserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofWebSearchTool20260318(webSearchTool20260318)

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("webSearchTool20260318" to Optional.of(webSearchTool20260318)))

        assertThat(messageCountTokensTool.name()).contains("web_search")
    }

    @Test
    fun ofWebSearchTool20260318Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofWebSearchTool20260318(
                WebSearchTool20260318.builder()
                    .addAllowedCaller(WebSearchTool20260318.AllowedCaller.DIRECT)
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .deferLoading(true)
                    .maxUses(1L)
                    .responseInclusion(WebSearchTool20260318.ResponseInclusion.FULL)
                    .strict(true)
                    .userLocation(
                        UserLocation.builder()
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
    fun ofWebFetchTool20260318() {
        val webFetchTool20260318 =
            WebFetchTool20260318.builder()
                .addAllowedCaller(WebFetchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .citations(CitationsConfigParam.builder().enabled(true).build())
                .deferLoading(true)
                .maxContentTokens(1L)
                .maxUses(1L)
                .responseInclusion(WebFetchTool20260318.ResponseInclusion.FULL)
                .strict(true)
                .urlSources(
                    WebFetchUrlSources.builder()
                        .clientToolResults(WebFetchUrlSourceAll.builder().build())
                        .serverToolResults(WebFetchUrlSourceAll.builder().build())
                        .userInput(WebFetchUrlSourceAll.builder().build())
                        .build()
                )
                .useCache(true)
                .build()

        val messageCountTokensTool =
            MessageCountTokensTool.ofWebFetchTool20260318(webFetchTool20260318)

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(mapOf("webFetchTool20260318" to Optional.of(webFetchTool20260318)))

        assertThat(messageCountTokensTool.name()).contains("web_fetch")
    }

    @Test
    fun ofWebFetchTool20260318Roundtrip() {
        val jsonMapper = jsonMapper()
        val messageCountTokensTool =
            MessageCountTokensTool.ofWebFetchTool20260318(
                WebFetchTool20260318.builder()
                    .addAllowedCaller(WebFetchTool20260318.AllowedCaller.DIRECT)
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
                    .responseInclusion(WebFetchTool20260318.ResponseInclusion.FULL)
                    .strict(true)
                    .urlSources(
                        WebFetchUrlSources.builder()
                            .clientToolResults(WebFetchUrlSourceAll.builder().build())
                            .serverToolResults(WebFetchUrlSourceAll.builder().build())
                            .userInput(WebFetchUrlSourceAll.builder().build())
                            .build()
                    )
                    .useCache(true)
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

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(
                mapOf("toolSearchToolBm25_20251119" to Optional.of(toolSearchToolBm25_20251119))
            )

        assertThat(messageCountTokensTool.name()).contains("tool_search_tool_bm25")
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

        assertThat(presentVariants(messageCountTokensTool))
            .isEqualTo(
                mapOf("toolSearchToolRegex20251119" to Optional.of(toolSearchToolRegex20251119))
            )

        assertThat(messageCountTokensTool.name()).contains("tool_search_tool_regex")
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

        assertThat(messageCountTokensTool.name()).isEmpty
        assertThat(messageCountTokensTool.cacheControl()).isEmpty
        assertThat(messageCountTokensTool.deferLoading()).isEmpty
        assertThat(messageCountTokensTool.strict()).isEmpty
        assertThat(messageCountTokensTool.allowedDomains()).isEmpty
        assertThat(messageCountTokensTool.blockedDomains()).isEmpty
        assertThat(messageCountTokensTool.maxUses()).isEmpty
        assertThat(messageCountTokensTool.userLocation()).isEmpty
        assertThat(messageCountTokensTool.citations()).isEmpty
        assertThat(messageCountTokensTool.maxContentTokens()).isEmpty
        assertThat(messageCountTokensTool.urlSources()).isEmpty
        assertThat(messageCountTokensTool.useCache()).isEmpty
    }

    private fun presentVariants(
        messageCountTokensTool: MessageCountTokensTool
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "tool" to messageCountTokensTool.tool(),
                "toolBash20250124" to messageCountTokensTool.toolBash20250124(),
                "codeExecutionTool20250522" to messageCountTokensTool.codeExecutionTool20250522(),
                "codeExecutionTool20250825" to messageCountTokensTool.codeExecutionTool20250825(),
                "codeExecutionTool20260120" to messageCountTokensTool.codeExecutionTool20260120(),
                "codeExecutionTool20260521" to messageCountTokensTool.codeExecutionTool20260521(),
                "browserToolset20260801" to messageCountTokensTool.browserToolset20260801(),
                "memoryTool20250818" to messageCountTokensTool.memoryTool20250818(),
                "computerToolset20260801" to messageCountTokensTool.computerToolset20260801(),
                "toolTextEditor20250124" to messageCountTokensTool.toolTextEditor20250124(),
                "toolTextEditor20250429" to messageCountTokensTool.toolTextEditor20250429(),
                "toolTextEditor20250728" to messageCountTokensTool.toolTextEditor20250728(),
                "webSearchTool20250305" to messageCountTokensTool.webSearchTool20250305(),
                "webFetchTool20250910" to messageCountTokensTool.webFetchTool20250910(),
                "webSearchTool20260209" to messageCountTokensTool.webSearchTool20260209(),
                "webFetchTool20260209" to messageCountTokensTool.webFetchTool20260209(),
                "webFetchTool20260309" to messageCountTokensTool.webFetchTool20260309(),
                "webSearchTool20260318" to messageCountTokensTool.webSearchTool20260318(),
                "webFetchTool20260318" to messageCountTokensTool.webFetchTool20260318(),
                "toolSearchToolBm25_20251119" to
                    messageCountTokensTool.toolSearchToolBm25_20251119(),
                "toolSearchToolRegex20251119" to
                    messageCountTokensTool.toolSearchToolRegex20251119(),
            )
            .filterValues { it.isPresent }
}
