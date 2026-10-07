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

        assertThat(browserToolUseBlock.navigate()).contains(navigate)
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).contains(listTabs)
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).contains(newTab)
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).contains(switchTab)
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).contains(closeTab)
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).contains(readPage)
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).contains(getPageText)
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).contains(readConsole)
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).contains(readNetwork)
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).contains(find)
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).contains(formInput)
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).contains(fileUpload)
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).contains(scrollTo)
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).contains(screenshot)
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).contains(zoom)
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).contains(leftClick)
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).contains(rightClick)
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).contains(middleClick)
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).contains(doubleClick)
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).contains(tripleClick)
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).contains(hover)
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).contains(leftClickDrag)
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).contains(leftMouseDown)
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).contains(leftMouseUp)
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).contains(mouseMove)
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).contains(scroll)
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).contains(type)
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).contains(key)
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).contains(holdKey)
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).contains(wait)
        assertThat(browserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(browserToolUseBlock.navigate()).isEmpty
        assertThat(browserToolUseBlock.listTabs()).isEmpty
        assertThat(browserToolUseBlock.newTab()).isEmpty
        assertThat(browserToolUseBlock.switchTab()).isEmpty
        assertThat(browserToolUseBlock.closeTab()).isEmpty
        assertThat(browserToolUseBlock.readPage()).isEmpty
        assertThat(browserToolUseBlock.getPageText()).isEmpty
        assertThat(browserToolUseBlock.readConsole()).isEmpty
        assertThat(browserToolUseBlock.readNetwork()).isEmpty
        assertThat(browserToolUseBlock.find()).isEmpty
        assertThat(browserToolUseBlock.formInput()).isEmpty
        assertThat(browserToolUseBlock.fileUpload()).isEmpty
        assertThat(browserToolUseBlock.scrollTo()).isEmpty
        assertThat(browserToolUseBlock.screenshot()).isEmpty
        assertThat(browserToolUseBlock.zoom()).isEmpty
        assertThat(browserToolUseBlock.leftClick()).isEmpty
        assertThat(browserToolUseBlock.rightClick()).isEmpty
        assertThat(browserToolUseBlock.middleClick()).isEmpty
        assertThat(browserToolUseBlock.doubleClick()).isEmpty
        assertThat(browserToolUseBlock.tripleClick()).isEmpty
        assertThat(browserToolUseBlock.hover()).isEmpty
        assertThat(browserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(browserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(browserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(browserToolUseBlock.mouseMove()).isEmpty
        assertThat(browserToolUseBlock.scroll()).isEmpty
        assertThat(browserToolUseBlock.type()).isEmpty
        assertThat(browserToolUseBlock.key()).isEmpty
        assertThat(browserToolUseBlock.holdKey()).isEmpty
        assertThat(browserToolUseBlock.wait()).isEmpty
        assertThat(browserToolUseBlock.javascriptExec()).contains(javascriptExec)
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
}
