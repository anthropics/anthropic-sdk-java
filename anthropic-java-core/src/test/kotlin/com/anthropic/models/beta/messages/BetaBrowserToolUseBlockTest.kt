package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
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

        assertThat(betaBrowserToolUseBlock.navigate()).contains(navigate)
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).contains(listTabs)
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).contains(newTab)
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).contains(switchTab)
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).contains(closeTab)
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).contains(readPage)
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).contains(getPageText)
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).contains(readConsole)
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).contains(readNetwork)
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).contains(find)
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).contains(formInput)
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).contains(fileUpload)
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).contains(scrollTo)
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).contains(screenshot)
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).contains(zoom)
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).contains(leftClick)
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).contains(rightClick)
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).contains(middleClick)
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).contains(doubleClick)
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).contains(tripleClick)
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).contains(hover)
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).contains(leftClickDrag)
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).contains(leftMouseDown)
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).contains(leftMouseUp)
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).contains(mouseMove)
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).contains(scroll)
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).contains(type)
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).contains(key)
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).contains(holdKey)
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).contains(wait)
        assertThat(betaBrowserToolUseBlock.javascriptExec()).isEmpty
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

        assertThat(betaBrowserToolUseBlock.navigate()).isEmpty
        assertThat(betaBrowserToolUseBlock.listTabs()).isEmpty
        assertThat(betaBrowserToolUseBlock.newTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.switchTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.closeTab()).isEmpty
        assertThat(betaBrowserToolUseBlock.readPage()).isEmpty
        assertThat(betaBrowserToolUseBlock.getPageText()).isEmpty
        assertThat(betaBrowserToolUseBlock.readConsole()).isEmpty
        assertThat(betaBrowserToolUseBlock.readNetwork()).isEmpty
        assertThat(betaBrowserToolUseBlock.find()).isEmpty
        assertThat(betaBrowserToolUseBlock.formInput()).isEmpty
        assertThat(betaBrowserToolUseBlock.fileUpload()).isEmpty
        assertThat(betaBrowserToolUseBlock.scrollTo()).isEmpty
        assertThat(betaBrowserToolUseBlock.screenshot()).isEmpty
        assertThat(betaBrowserToolUseBlock.zoom()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.rightClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.middleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.doubleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.tripleClick()).isEmpty
        assertThat(betaBrowserToolUseBlock.hover()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaBrowserToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaBrowserToolUseBlock.mouseMove()).isEmpty
        assertThat(betaBrowserToolUseBlock.scroll()).isEmpty
        assertThat(betaBrowserToolUseBlock.type()).isEmpty
        assertThat(betaBrowserToolUseBlock.key()).isEmpty
        assertThat(betaBrowserToolUseBlock.holdKey()).isEmpty
        assertThat(betaBrowserToolUseBlock.wait()).isEmpty
        assertThat(betaBrowserToolUseBlock.javascriptExec()).contains(javascriptExec)
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
}
