package com.anthropic.models.beta.messages

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

internal class BetaBrowserToolUseBlockTest {

    @Test
    fun ofNavigate() {
        val navigate =
            BetaBrowserNavigateToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserNavigateInput.builder().url("url").tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofNavigate(navigate)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("navigate" to Optional.of(navigate)))
    }

    @Test
    fun ofNavigateRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofNavigate(
                BetaBrowserNavigateToolUseBlock.builder()
                    .id("id")
                    .input(BetaBrowserNavigateInput.builder().url("url").tabId("tab_id").build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofListTabs() {
        val listTabs =
            BetaBrowserListTabsToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserListTabsInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofListTabs(listTabs)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("listTabs" to Optional.of(listTabs)))
    }

    @Test
    fun ofListTabsRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofListTabs(
                BetaBrowserListTabsToolUseBlock.builder()
                    .id("id")
                    .input(BetaBrowserListTabsInput.builder().build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofNewTab() {
        val newTab =
            BetaBrowserNewTabToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserNewTabInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofNewTab(newTab)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("newTab" to Optional.of(newTab)))
    }

    @Test
    fun ofNewTabRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofNewTab(
                BetaBrowserNewTabToolUseBlock.builder()
                    .id("id")
                    .input(BetaBrowserNewTabInput.builder().build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofSwitchTab() {
        val switchTab =
            BetaBrowserSwitchTabToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserSwitchTabInput.of("tab_id"))
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofSwitchTab(switchTab)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("switchTab" to Optional.of(switchTab)))
    }

    @Test
    fun ofSwitchTabRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofSwitchTab(
                BetaBrowserSwitchTabToolUseBlock.builder()
                    .id("id")
                    .input(BetaBrowserSwitchTabInput.of("tab_id"))
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofCloseTab() {
        val closeTab =
            BetaBrowserCloseTabToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserCloseTabInput.of("tab_id"))
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofCloseTab(closeTab)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("closeTab" to Optional.of(closeTab)))
    }

    @Test
    fun ofCloseTabRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofCloseTab(
                BetaBrowserCloseTabToolUseBlock.builder()
                    .id("id")
                    .input(BetaBrowserCloseTabInput.of("tab_id"))
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofReadPage() {
        val readPage =
            BetaBrowserReadPageToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserReadPageInput.builder()
                        .depth(1L)
                        .filter(BetaBrowserReadPageFilter.ALL)
                        .ref("ref")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofReadPage(readPage)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("readPage" to Optional.of(readPage)))
    }

    @Test
    fun ofReadPageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofReadPage(
                BetaBrowserReadPageToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserReadPageInput.builder()
                            .depth(1L)
                            .filter(BetaBrowserReadPageFilter.ALL)
                            .ref("ref")
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofGetPageText() {
        val getPageText =
            BetaBrowserGetPageTextToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserGetPageTextInput.builder().tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofGetPageText(getPageText)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("getPageText" to Optional.of(getPageText)))
    }

    @Test
    fun ofGetPageTextRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofGetPageText(
                BetaBrowserGetPageTextToolUseBlock.builder()
                    .id("id")
                    .input(BetaBrowserGetPageTextInput.builder().tabId("tab_id").build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofReadConsole() {
        val readConsole =
            BetaBrowserReadConsoleToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserReadConsoleInput.builder().tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofReadConsole(readConsole)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("readConsole" to Optional.of(readConsole)))
    }

    @Test
    fun ofReadConsoleRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofReadConsole(
                BetaBrowserReadConsoleToolUseBlock.builder()
                    .id("id")
                    .input(BetaBrowserReadConsoleInput.builder().tabId("tab_id").build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofReadNetwork() {
        val readNetwork =
            BetaBrowserReadNetworkToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserReadNetworkInput.builder().tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofReadNetwork(readNetwork)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("readNetwork" to Optional.of(readNetwork)))
    }

    @Test
    fun ofReadNetworkRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofReadNetwork(
                BetaBrowserReadNetworkToolUseBlock.builder()
                    .id("id")
                    .input(BetaBrowserReadNetworkInput.builder().tabId("tab_id").build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofFind() {
        val find =
            BetaBrowserFindToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserFindInput.builder().query("query").tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofFind(find)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("find" to Optional.of(find)))
    }

    @Test
    fun ofFindRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofFind(
                BetaBrowserFindToolUseBlock.builder()
                    .id("id")
                    .input(BetaBrowserFindInput.builder().query("query").tabId("tab_id").build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofFormInput() {
        val formInput =
            BetaBrowserFormInputToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserFormInputInput.builder()
                        .target(BetaBrowserRefTarget.of("ref"))
                        .value("string")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofFormInput(formInput)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("formInput" to Optional.of(formInput)))
    }

    @Test
    fun ofFormInputRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofFormInput(
                BetaBrowserFormInputToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserFormInputInput.builder()
                            .target(BetaBrowserRefTarget.of("ref"))
                            .value("string")
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofFileUpload() {
        val fileUpload =
            BetaBrowserFileUploadToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserFileUploadInput.builder()
                        .target(BetaBrowserRefTarget.of("ref"))
                        .addDocumentId("string")
                        .addPath("string")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofFileUpload(fileUpload)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("fileUpload" to Optional.of(fileUpload)))
    }

    @Test
    fun ofFileUploadRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofFileUpload(
                BetaBrowserFileUploadToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserFileUploadInput.builder()
                            .target(BetaBrowserRefTarget.of("ref"))
                            .addDocumentId("string")
                            .addPath("string")
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofScrollTo() {
        val scrollTo =
            BetaBrowserScrollToToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserScrollToInput.builder()
                        .target(BetaBrowserRefTarget.of("ref"))
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofScrollTo(scrollTo)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("scrollTo" to Optional.of(scrollTo)))
    }

    @Test
    fun ofScrollToRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofScrollTo(
                BetaBrowserScrollToToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserScrollToInput.builder()
                            .target(BetaBrowserRefTarget.of("ref"))
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofScreenshot() {
        val screenshot =
            BetaBrowserScreenshotToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserScreenshotInput.builder().tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofScreenshot(screenshot)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("screenshot" to Optional.of(screenshot)))
    }

    @Test
    fun ofScreenshotRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofScreenshot(
                BetaBrowserScreenshotToolUseBlock.builder()
                    .id("id")
                    .input(BetaBrowserScreenshotInput.builder().tabId("tab_id").build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofZoom() {
        val zoom =
            BetaBrowserZoomToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserZoomInput.builder()
                        .region(listOf(0L, 0L, 0L, 0L))
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofZoom(zoom)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("zoom" to Optional.of(zoom)))
    }

    @Test
    fun ofZoomRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofZoom(
                BetaBrowserZoomToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserZoomInput.builder()
                            .region(listOf(0L, 0L, 0L, 0L))
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofLeftClick() {
        val leftClick =
            BetaBrowserLeftClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserLeftClickInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofLeftClick(leftClick)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("leftClick" to Optional.of(leftClick)))
    }

    @Test
    fun ofLeftClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofLeftClick(
                BetaBrowserLeftClickToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserLeftClickInput.builder()
                            .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .modifiers("modifiers")
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofRightClick() {
        val rightClick =
            BetaBrowserRightClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserRightClickInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofRightClick(rightClick)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("rightClick" to Optional.of(rightClick)))
    }

    @Test
    fun ofRightClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofRightClick(
                BetaBrowserRightClickToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserRightClickInput.builder()
                            .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .modifiers("modifiers")
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofMiddleClick() {
        val middleClick =
            BetaBrowserMiddleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserMiddleClickInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofMiddleClick(middleClick)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("middleClick" to Optional.of(middleClick)))
    }

    @Test
    fun ofMiddleClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofMiddleClick(
                BetaBrowserMiddleClickToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserMiddleClickInput.builder()
                            .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .modifiers("modifiers")
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofDoubleClick() {
        val doubleClick =
            BetaBrowserDoubleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserDoubleClickInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofDoubleClick(doubleClick)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("doubleClick" to Optional.of(doubleClick)))
    }

    @Test
    fun ofDoubleClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofDoubleClick(
                BetaBrowserDoubleClickToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserDoubleClickInput.builder()
                            .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .modifiers("modifiers")
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofTripleClick() {
        val tripleClick =
            BetaBrowserTripleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserTripleClickInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofTripleClick(tripleClick)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("tripleClick" to Optional.of(tripleClick)))
    }

    @Test
    fun ofTripleClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofTripleClick(
                BetaBrowserTripleClickToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserTripleClickInput.builder()
                            .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .modifiers("modifiers")
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofHover() {
        val hover =
            BetaBrowserHoverToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserHoverInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofHover(hover)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("hover" to Optional.of(hover)))
    }

    @Test
    fun ofHoverRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofHover(
                BetaBrowserHoverToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserHoverInput.builder()
                            .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofLeftClickDrag() {
        val leftClickDrag =
            BetaBrowserLeftClickDragToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserLeftClickDragInput.builder()
                        .from(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofLeftClickDrag(leftClickDrag)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("leftClickDrag" to Optional.of(leftClickDrag)))
    }

    @Test
    fun ofLeftClickDragRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofLeftClickDrag(
                BetaBrowserLeftClickDragToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserLeftClickDragInput.builder()
                            .from(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofLeftMouseDown() {
        val leftMouseDown =
            BetaBrowserLeftMouseDownToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserLeftMouseDownInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofLeftMouseDown(leftMouseDown)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("leftMouseDown" to Optional.of(leftMouseDown)))
    }

    @Test
    fun ofLeftMouseDownRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofLeftMouseDown(
                BetaBrowserLeftMouseDownToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserLeftMouseDownInput.builder()
                            .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofLeftMouseUp() {
        val leftMouseUp =
            BetaBrowserLeftMouseUpToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserLeftMouseUpInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofLeftMouseUp(leftMouseUp)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("leftMouseUp" to Optional.of(leftMouseUp)))
    }

    @Test
    fun ofLeftMouseUpRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofLeftMouseUp(
                BetaBrowserLeftMouseUpToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserLeftMouseUpInput.builder()
                            .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofMouseMove() {
        val mouseMove =
            BetaBrowserMouseMoveToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserMouseMoveInput.builder()
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofMouseMove(mouseMove)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("mouseMove" to Optional.of(mouseMove)))
    }

    @Test
    fun ofMouseMoveRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofMouseMove(
                BetaBrowserMouseMoveToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserMouseMoveInput.builder()
                            .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofScroll() {
        val scroll =
            BetaBrowserScrollToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserScrollInput.builder()
                        .scrollDirection(BetaBrowserScrollDirection.UP)
                        .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .scrollAmount(1L)
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofScroll(scroll)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("scroll" to Optional.of(scroll)))
    }

    @Test
    fun ofScrollRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofScroll(
                BetaBrowserScrollToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserScrollInput.builder()
                            .scrollDirection(BetaBrowserScrollDirection.UP)
                            .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .scrollAmount(1L)
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofType() {
        val type =
            BetaBrowserTypeToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserTypeInput.builder().text("text").tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofType(type)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("type" to Optional.of(type)))
    }

    @Test
    fun ofTypeRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofType(
                BetaBrowserTypeToolUseBlock.builder()
                    .id("id")
                    .input(BetaBrowserTypeInput.builder().text("text").tabId("tab_id").build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofKey() {
        val key =
            BetaBrowserKeyToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserKeyInput.builder().text("text").repeat(1L).tabId("tab_id").build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofKey(key)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("key" to Optional.of(key)))
    }

    @Test
    fun ofKeyRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofKey(
                BetaBrowserKeyToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserKeyInput.builder()
                            .text("text")
                            .repeat(1L)
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofHoldKey() {
        val holdKey =
            BetaBrowserHoldKeyToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserHoldKeyInput.builder()
                        .duration(0.0)
                        .text("text")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofHoldKey(holdKey)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("holdKey" to Optional.of(holdKey)))
    }

    @Test
    fun ofHoldKeyRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofHoldKey(
                BetaBrowserHoldKeyToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserHoldKeyInput.builder()
                            .duration(0.0)
                            .text("text")
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofWait() {
        val wait =
            BetaBrowserWaitToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserWaitInput.builder().duration(0.0).tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofWait(wait)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("wait" to Optional.of(wait)))
    }

    @Test
    fun ofWaitRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofWait(
                BetaBrowserWaitToolUseBlock.builder()
                    .id("id")
                    .input(BetaBrowserWaitInput.builder().duration(0.0).tabId("tab_id").build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun ofJavascriptExec() {
        val javascriptExec =
            BetaBrowserJavascriptExecToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserJavascriptExecInput.builder().text("text").tabId("tab_id").build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaBrowserToolUseBlock = BetaBrowserToolUseBlock.ofJavascriptExec(javascriptExec)

        assertThat(presentVariants(betaBrowserToolUseBlock))
            .isEqualTo(mapOf("javascriptExec" to Optional.of(javascriptExec)))
    }

    @Test
    fun ofJavascriptExecRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserToolUseBlock =
            BetaBrowserToolUseBlock.ofJavascriptExec(
                BetaBrowserJavascriptExecToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaBrowserJavascriptExecInput.builder()
                            .text("text")
                            .tabId("tab_id")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserToolUseBlock),
                jacksonTypeRef<BetaBrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserToolUseBlock).isEqualTo(betaBrowserToolUseBlock)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaBrowserToolUseBlock =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "name" to "unknown_variant",
                            "id" to "id",
                            "caller" to mapOf("type" to "direct"),
                        )
                    ),
                    jacksonTypeRef<BetaBrowserToolUseBlock>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { betaBrowserToolUseBlock.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaBrowserToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))

        val mismatchedBetaBrowserToolUseBlock =
            jsonMapper()
                .convertValue(
                    JsonValue.from(mapOf("name" to "unknown_variant", "id" to listOf("invalid"))),
                    jacksonTypeRef<BetaBrowserToolUseBlock>(),
                )

        assertThrows<AnthropicInvalidDataException> { mismatchedBetaBrowserToolUseBlock.id() }
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
        val betaBrowserToolUseBlock =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaBrowserToolUseBlock>())

        val e = assertThrows<AnthropicInvalidDataException> { betaBrowserToolUseBlock.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> { betaBrowserToolUseBlock.id() }
        assertThat(betaBrowserToolUseBlock.caller()).isEmpty
    }

    private fun presentVariants(
        betaBrowserToolUseBlock: BetaBrowserToolUseBlock
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "navigate" to betaBrowserToolUseBlock.navigate(),
                "listTabs" to betaBrowserToolUseBlock.listTabs(),
                "newTab" to betaBrowserToolUseBlock.newTab(),
                "switchTab" to betaBrowserToolUseBlock.switchTab(),
                "closeTab" to betaBrowserToolUseBlock.closeTab(),
                "readPage" to betaBrowserToolUseBlock.readPage(),
                "getPageText" to betaBrowserToolUseBlock.getPageText(),
                "readConsole" to betaBrowserToolUseBlock.readConsole(),
                "readNetwork" to betaBrowserToolUseBlock.readNetwork(),
                "find" to betaBrowserToolUseBlock.find(),
                "formInput" to betaBrowserToolUseBlock.formInput(),
                "fileUpload" to betaBrowserToolUseBlock.fileUpload(),
                "scrollTo" to betaBrowserToolUseBlock.scrollTo(),
                "screenshot" to betaBrowserToolUseBlock.screenshot(),
                "zoom" to betaBrowserToolUseBlock.zoom(),
                "leftClick" to betaBrowserToolUseBlock.leftClick(),
                "rightClick" to betaBrowserToolUseBlock.rightClick(),
                "middleClick" to betaBrowserToolUseBlock.middleClick(),
                "doubleClick" to betaBrowserToolUseBlock.doubleClick(),
                "tripleClick" to betaBrowserToolUseBlock.tripleClick(),
                "hover" to betaBrowserToolUseBlock.hover(),
                "leftClickDrag" to betaBrowserToolUseBlock.leftClickDrag(),
                "leftMouseDown" to betaBrowserToolUseBlock.leftMouseDown(),
                "leftMouseUp" to betaBrowserToolUseBlock.leftMouseUp(),
                "mouseMove" to betaBrowserToolUseBlock.mouseMove(),
                "scroll" to betaBrowserToolUseBlock.scroll(),
                "type" to betaBrowserToolUseBlock.type(),
                "key" to betaBrowserToolUseBlock.key(),
                "holdKey" to betaBrowserToolUseBlock.holdKey(),
                "wait" to betaBrowserToolUseBlock.wait(),
                "javascriptExec" to betaBrowserToolUseBlock.javascriptExec(),
            )
            .filterValues { it.isPresent }
}
