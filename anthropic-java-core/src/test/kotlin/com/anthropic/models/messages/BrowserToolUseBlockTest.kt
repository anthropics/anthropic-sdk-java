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

internal class BrowserToolUseBlockTest {

    @Test
    fun ofNavigate() {
        val navigate =
            BrowserNavigateToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserNavigateInput.builder().url("url").tabId("tab_id").build())
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofNavigate(navigate)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("navigate" to Optional.of(navigate)))
    }

    @Test
    fun ofNavigateRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofNavigate(
                BrowserNavigateToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(BrowserNavigateInput.builder().url("url").tabId("tab_id").build())
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofListTabs() {
        val listTabs =
            BrowserListTabsToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserListTabsInput.builder().build())
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofListTabs(listTabs)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("listTabs" to Optional.of(listTabs)))
    }

    @Test
    fun ofListTabsRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofListTabs(
                BrowserListTabsToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(BrowserListTabsInput.builder().build())
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofNewTab() {
        val newTab =
            BrowserNewTabToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserNewTabInput.builder().build())
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofNewTab(newTab)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("newTab" to Optional.of(newTab)))
    }

    @Test
    fun ofNewTabRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofNewTab(
                BrowserNewTabToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(BrowserNewTabInput.builder().build())
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofSwitchTab() {
        val switchTab =
            BrowserSwitchTabToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserSwitchTabInput.of("tab_id"))
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofSwitchTab(switchTab)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("switchTab" to Optional.of(switchTab)))
    }

    @Test
    fun ofSwitchTabRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofSwitchTab(
                BrowserSwitchTabToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(BrowserSwitchTabInput.of("tab_id"))
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofCloseTab() {
        val closeTab =
            BrowserCloseTabToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserCloseTabInput.of("tab_id"))
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofCloseTab(closeTab)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("closeTab" to Optional.of(closeTab)))
    }

    @Test
    fun ofCloseTabRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofCloseTab(
                BrowserCloseTabToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(BrowserCloseTabInput.of("tab_id"))
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofReadPage() {
        val readPage =
            BrowserReadPageToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserReadPageInput.builder()
                        .depth(1L)
                        .filter(BrowserReadPageFilter.ALL)
                        .ref("ref")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofReadPage(readPage)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("readPage" to Optional.of(readPage)))
    }

    @Test
    fun ofReadPageRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofReadPage(
                BrowserReadPageToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserReadPageInput.builder()
                            .depth(1L)
                            .filter(BrowserReadPageFilter.ALL)
                            .ref("ref")
                            .tabId("tab_id")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofGetPageText() {
        val getPageText =
            BrowserGetPageTextToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserGetPageTextInput.builder().tabId("tab_id").build())
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofGetPageText(getPageText)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("getPageText" to Optional.of(getPageText)))
    }

    @Test
    fun ofGetPageTextRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofGetPageText(
                BrowserGetPageTextToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(BrowserGetPageTextInput.builder().tabId("tab_id").build())
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofReadConsole() {
        val readConsole =
            BrowserReadConsoleToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserReadConsoleInput.builder().tabId("tab_id").build())
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofReadConsole(readConsole)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("readConsole" to Optional.of(readConsole)))
    }

    @Test
    fun ofReadConsoleRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofReadConsole(
                BrowserReadConsoleToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(BrowserReadConsoleInput.builder().tabId("tab_id").build())
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofReadNetwork() {
        val readNetwork =
            BrowserReadNetworkToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserReadNetworkInput.builder().tabId("tab_id").build())
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofReadNetwork(readNetwork)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("readNetwork" to Optional.of(readNetwork)))
    }

    @Test
    fun ofReadNetworkRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofReadNetwork(
                BrowserReadNetworkToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(BrowserReadNetworkInput.builder().tabId("tab_id").build())
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofFind() {
        val find =
            BrowserFindToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserFindInput.builder().query("query").tabId("tab_id").build())
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofFind(find)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("find" to Optional.of(find)))
    }

    @Test
    fun ofFindRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofFind(
                BrowserFindToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(BrowserFindInput.builder().query("query").tabId("tab_id").build())
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofFormInput() {
        val formInput =
            BrowserFormInputToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserFormInputInput.builder()
                        .target(BrowserRefTarget.of("ref"))
                        .value("string")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofFormInput(formInput)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("formInput" to Optional.of(formInput)))
    }

    @Test
    fun ofFormInputRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofFormInput(
                BrowserFormInputToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserFormInputInput.builder()
                            .target(BrowserRefTarget.of("ref"))
                            .value("string")
                            .tabId("tab_id")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofFileUpload() {
        val fileUpload =
            BrowserFileUploadToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserFileUploadInput.builder()
                        .target(BrowserRefTarget.of("ref"))
                        .addDocumentId("string")
                        .addPath("string")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofFileUpload(fileUpload)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("fileUpload" to Optional.of(fileUpload)))
    }

    @Test
    fun ofFileUploadRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofFileUpload(
                BrowserFileUploadToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserFileUploadInput.builder()
                            .target(BrowserRefTarget.of("ref"))
                            .addDocumentId("string")
                            .addPath("string")
                            .tabId("tab_id")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofScrollTo() {
        val scrollTo =
            BrowserScrollToToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserScrollToInput.builder()
                        .target(BrowserRefTarget.of("ref"))
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofScrollTo(scrollTo)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("scrollTo" to Optional.of(scrollTo)))
    }

    @Test
    fun ofScrollToRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofScrollTo(
                BrowserScrollToToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserScrollToInput.builder()
                            .target(BrowserRefTarget.of("ref"))
                            .tabId("tab_id")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofScreenshot() {
        val screenshot =
            BrowserScreenshotToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserScreenshotInput.builder().tabId("tab_id").build())
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofScreenshot(screenshot)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("screenshot" to Optional.of(screenshot)))
    }

    @Test
    fun ofScreenshotRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofScreenshot(
                BrowserScreenshotToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(BrowserScreenshotInput.builder().tabId("tab_id").build())
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofZoom() {
        val zoom =
            BrowserZoomToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserZoomInput.builder()
                        .region(listOf(0L, 0L, 0L, 0L))
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofZoom(zoom)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("zoom" to Optional.of(zoom)))
    }

    @Test
    fun ofZoomRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofZoom(
                BrowserZoomToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserZoomInput.builder()
                            .region(listOf(0L, 0L, 0L, 0L))
                            .tabId("tab_id")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofLeftClick() {
        val leftClick =
            BrowserLeftClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserLeftClickInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofLeftClick(leftClick)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("leftClick" to Optional.of(leftClick)))
    }

    @Test
    fun ofLeftClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofLeftClick(
                BrowserLeftClickToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserLeftClickInput.builder()
                            .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .modifiers("modifiers")
                            .tabId("tab_id")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofRightClick() {
        val rightClick =
            BrowserRightClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserRightClickInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofRightClick(rightClick)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("rightClick" to Optional.of(rightClick)))
    }

    @Test
    fun ofRightClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofRightClick(
                BrowserRightClickToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserRightClickInput.builder()
                            .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .modifiers("modifiers")
                            .tabId("tab_id")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofMiddleClick() {
        val middleClick =
            BrowserMiddleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserMiddleClickInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofMiddleClick(middleClick)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("middleClick" to Optional.of(middleClick)))
    }

    @Test
    fun ofMiddleClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofMiddleClick(
                BrowserMiddleClickToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserMiddleClickInput.builder()
                            .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .modifiers("modifiers")
                            .tabId("tab_id")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofDoubleClick() {
        val doubleClick =
            BrowserDoubleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserDoubleClickInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofDoubleClick(doubleClick)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("doubleClick" to Optional.of(doubleClick)))
    }

    @Test
    fun ofDoubleClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofDoubleClick(
                BrowserDoubleClickToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserDoubleClickInput.builder()
                            .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .modifiers("modifiers")
                            .tabId("tab_id")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofTripleClick() {
        val tripleClick =
            BrowserTripleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserTripleClickInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .modifiers("modifiers")
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofTripleClick(tripleClick)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("tripleClick" to Optional.of(tripleClick)))
    }

    @Test
    fun ofTripleClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofTripleClick(
                BrowserTripleClickToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserTripleClickInput.builder()
                            .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .modifiers("modifiers")
                            .tabId("tab_id")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofHover() {
        val hover =
            BrowserHoverToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserHoverInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofHover(hover)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("hover" to Optional.of(hover)))
    }

    @Test
    fun ofHoverRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofHover(
                BrowserHoverToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserHoverInput.builder()
                            .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .tabId("tab_id")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofLeftClickDrag() {
        val leftClickDrag =
            BrowserLeftClickDragToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserLeftClickDragInput.builder()
                        .from(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofLeftClickDrag(leftClickDrag)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("leftClickDrag" to Optional.of(leftClickDrag)))
    }

    @Test
    fun ofLeftClickDragRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofLeftClickDrag(
                BrowserLeftClickDragToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserLeftClickDragInput.builder()
                            .from(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .tabId("tab_id")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofLeftMouseDown() {
        val leftMouseDown =
            BrowserLeftMouseDownToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserLeftMouseDownInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofLeftMouseDown(leftMouseDown)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("leftMouseDown" to Optional.of(leftMouseDown)))
    }

    @Test
    fun ofLeftMouseDownRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofLeftMouseDown(
                BrowserLeftMouseDownToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserLeftMouseDownInput.builder()
                            .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .tabId("tab_id")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofLeftMouseUp() {
        val leftMouseUp =
            BrowserLeftMouseUpToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserLeftMouseUpInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofLeftMouseUp(leftMouseUp)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("leftMouseUp" to Optional.of(leftMouseUp)))
    }

    @Test
    fun ofLeftMouseUpRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofLeftMouseUp(
                BrowserLeftMouseUpToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserLeftMouseUpInput.builder()
                            .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .tabId("tab_id")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofMouseMove() {
        val mouseMove =
            BrowserMouseMoveToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserMouseMoveInput.builder()
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofMouseMove(mouseMove)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("mouseMove" to Optional.of(mouseMove)))
    }

    @Test
    fun ofMouseMoveRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofMouseMove(
                BrowserMouseMoveToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserMouseMoveInput.builder()
                            .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .tabId("tab_id")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofScroll() {
        val scroll =
            BrowserScrollToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserScrollInput.builder()
                        .scrollDirection(BrowserScrollDirection.UP)
                        .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                        .scrollAmount(1L)
                        .tabId("tab_id")
                        .build()
                )
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofScroll(scroll)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("scroll" to Optional.of(scroll)))
    }

    @Test
    fun ofScrollRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofScroll(
                BrowserScrollToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserScrollInput.builder()
                            .scrollDirection(BrowserScrollDirection.UP)
                            .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                            .scrollAmount(1L)
                            .tabId("tab_id")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofType() {
        val type =
            BrowserTypeToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserTypeInput.builder().text("text").tabId("tab_id").build())
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofType(type)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("type" to Optional.of(type)))
    }

    @Test
    fun ofTypeRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofType(
                BrowserTypeToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(BrowserTypeInput.builder().text("text").tabId("tab_id").build())
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofKey() {
        val key =
            BrowserKeyToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserKeyInput.builder().text("text").repeat(1L).tabId("tab_id").build())
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofKey(key)

        assertThat(presentVariants(browserToolUseBlock)).isEqualTo(mapOf("key" to Optional.of(key)))
    }

    @Test
    fun ofKeyRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofKey(
                BrowserKeyToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserKeyInput.builder().text("text").repeat(1L).tabId("tab_id").build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofHoldKey() {
        val holdKey =
            BrowserHoldKeyToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    BrowserHoldKeyInput.builder().duration(0.0).text("text").tabId("tab_id").build()
                )
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofHoldKey(holdKey)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("holdKey" to Optional.of(holdKey)))
    }

    @Test
    fun ofHoldKeyRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofHoldKey(
                BrowserHoldKeyToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserHoldKeyInput.builder()
                            .duration(0.0)
                            .text("text")
                            .tabId("tab_id")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofWait() {
        val wait =
            BrowserWaitToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserWaitInput.builder().duration(0.0).tabId("tab_id").build())
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofWait(wait)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("wait" to Optional.of(wait)))
    }

    @Test
    fun ofWaitRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofWait(
                BrowserWaitToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(BrowserWaitInput.builder().duration(0.0).tabId("tab_id").build())
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun ofJavascriptExec() {
        val javascriptExec =
            BrowserJavascriptExecToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserJavascriptExecInput.builder().text("text").tabId("tab_id").build())
                .build()

        val browserToolUseBlock = BrowserToolUseBlock.ofJavascriptExec(javascriptExec)

        assertThat(presentVariants(browserToolUseBlock))
            .isEqualTo(mapOf("javascriptExec" to Optional.of(javascriptExec)))
    }

    @Test
    fun ofJavascriptExecRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserToolUseBlock =
            BrowserToolUseBlock.ofJavascriptExec(
                BrowserJavascriptExecToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        BrowserJavascriptExecInput.builder().text("text").tabId("tab_id").build()
                    )
                    .build()
            )

        val roundtrippedBrowserToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserToolUseBlock),
                jacksonTypeRef<BrowserToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserToolUseBlock).isEqualTo(browserToolUseBlock)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val browserToolUseBlock =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "name" to "unknown_variant",
                            "id" to "id",
                            "caller" to mapOf("type" to "direct"),
                        )
                    ),
                    jacksonTypeRef<BrowserToolUseBlock>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { browserToolUseBlock.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(browserToolUseBlock.id()).isEqualTo("id")
        assertThat(browserToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))

        val mismatchedBrowserToolUseBlock =
            jsonMapper()
                .convertValue(
                    JsonValue.from(mapOf("name" to "unknown_variant", "id" to listOf("invalid"))),
                    jacksonTypeRef<BrowserToolUseBlock>(),
                )

        assertThrows<AnthropicInvalidDataException> { mismatchedBrowserToolUseBlock.id() }
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
        val browserToolUseBlock =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BrowserToolUseBlock>())

        val e = assertThrows<AnthropicInvalidDataException> { browserToolUseBlock.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> { browserToolUseBlock.id() }
        assertThrows<AnthropicInvalidDataException> { browserToolUseBlock.caller() }
    }

    private fun presentVariants(
        browserToolUseBlock: BrowserToolUseBlock
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "navigate" to browserToolUseBlock.navigate(),
                "listTabs" to browserToolUseBlock.listTabs(),
                "newTab" to browserToolUseBlock.newTab(),
                "switchTab" to browserToolUseBlock.switchTab(),
                "closeTab" to browserToolUseBlock.closeTab(),
                "readPage" to browserToolUseBlock.readPage(),
                "getPageText" to browserToolUseBlock.getPageText(),
                "readConsole" to browserToolUseBlock.readConsole(),
                "readNetwork" to browserToolUseBlock.readNetwork(),
                "find" to browserToolUseBlock.find(),
                "formInput" to browserToolUseBlock.formInput(),
                "fileUpload" to browserToolUseBlock.fileUpload(),
                "scrollTo" to browserToolUseBlock.scrollTo(),
                "screenshot" to browserToolUseBlock.screenshot(),
                "zoom" to browserToolUseBlock.zoom(),
                "leftClick" to browserToolUseBlock.leftClick(),
                "rightClick" to browserToolUseBlock.rightClick(),
                "middleClick" to browserToolUseBlock.middleClick(),
                "doubleClick" to browserToolUseBlock.doubleClick(),
                "tripleClick" to browserToolUseBlock.tripleClick(),
                "hover" to browserToolUseBlock.hover(),
                "leftClickDrag" to browserToolUseBlock.leftClickDrag(),
                "leftMouseDown" to browserToolUseBlock.leftMouseDown(),
                "leftMouseUp" to browserToolUseBlock.leftMouseUp(),
                "mouseMove" to browserToolUseBlock.mouseMove(),
                "scroll" to browserToolUseBlock.scroll(),
                "type" to browserToolUseBlock.type(),
                "key" to browserToolUseBlock.key(),
                "holdKey" to browserToolUseBlock.holdKey(),
                "wait" to browserToolUseBlock.wait(),
                "javascriptExec" to browserToolUseBlock.javascriptExec(),
            )
            .filterValues { it.isPresent }
}
