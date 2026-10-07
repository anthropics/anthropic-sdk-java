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

internal class BetaComputerToolUseBlockTest {

    @Test
    fun ofKey() {
        val key =
            BetaComputerKeyToolUseBlock.builder()
                .id("id")
                .input(BetaComputerKeyInput.builder().text("text").repeat(1L).build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaComputerToolUseBlock = BetaComputerToolUseBlock.ofKey(key)

        assertThat(betaComputerToolUseBlock.key()).contains(key)
        assertThat(betaComputerToolUseBlock.holdKey()).isEmpty
        assertThat(betaComputerToolUseBlock.type()).isEmpty
        assertThat(betaComputerToolUseBlock.cursorPosition()).isEmpty
        assertThat(betaComputerToolUseBlock.mouseMove()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClick()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaComputerToolUseBlock.rightClick()).isEmpty
        assertThat(betaComputerToolUseBlock.middleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.doubleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.tripleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.scroll()).isEmpty
        assertThat(betaComputerToolUseBlock.wait()).isEmpty
        assertThat(betaComputerToolUseBlock.screenshot()).isEmpty
        assertThat(betaComputerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofKeyRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolUseBlock =
            BetaComputerToolUseBlock.ofKey(
                BetaComputerKeyToolUseBlock.builder()
                    .id("id")
                    .input(BetaComputerKeyInput.builder().text("text").repeat(1L).build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolUseBlock),
                jacksonTypeRef<BetaComputerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerToolUseBlock).isEqualTo(betaComputerToolUseBlock)
    }

    @Test
    fun ofHoldKey() {
        val holdKey =
            BetaComputerHoldKeyToolUseBlock.builder()
                .id("id")
                .input(BetaComputerHoldKeyInput.builder().duration(300L).text("text").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaComputerToolUseBlock = BetaComputerToolUseBlock.ofHoldKey(holdKey)

        assertThat(betaComputerToolUseBlock.key()).isEmpty
        assertThat(betaComputerToolUseBlock.holdKey()).contains(holdKey)
        assertThat(betaComputerToolUseBlock.type()).isEmpty
        assertThat(betaComputerToolUseBlock.cursorPosition()).isEmpty
        assertThat(betaComputerToolUseBlock.mouseMove()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClick()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaComputerToolUseBlock.rightClick()).isEmpty
        assertThat(betaComputerToolUseBlock.middleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.doubleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.tripleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.scroll()).isEmpty
        assertThat(betaComputerToolUseBlock.wait()).isEmpty
        assertThat(betaComputerToolUseBlock.screenshot()).isEmpty
        assertThat(betaComputerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofHoldKeyRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolUseBlock =
            BetaComputerToolUseBlock.ofHoldKey(
                BetaComputerHoldKeyToolUseBlock.builder()
                    .id("id")
                    .input(BetaComputerHoldKeyInput.builder().duration(300L).text("text").build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolUseBlock),
                jacksonTypeRef<BetaComputerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerToolUseBlock).isEqualTo(betaComputerToolUseBlock)
    }

    @Test
    fun ofType() {
        val type =
            BetaComputerTypeToolUseBlock.builder()
                .id("id")
                .input(BetaComputerTypeInput.of("text"))
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaComputerToolUseBlock = BetaComputerToolUseBlock.ofType(type)

        assertThat(betaComputerToolUseBlock.key()).isEmpty
        assertThat(betaComputerToolUseBlock.holdKey()).isEmpty
        assertThat(betaComputerToolUseBlock.type()).contains(type)
        assertThat(betaComputerToolUseBlock.cursorPosition()).isEmpty
        assertThat(betaComputerToolUseBlock.mouseMove()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClick()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaComputerToolUseBlock.rightClick()).isEmpty
        assertThat(betaComputerToolUseBlock.middleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.doubleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.tripleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.scroll()).isEmpty
        assertThat(betaComputerToolUseBlock.wait()).isEmpty
        assertThat(betaComputerToolUseBlock.screenshot()).isEmpty
        assertThat(betaComputerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofTypeRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolUseBlock =
            BetaComputerToolUseBlock.ofType(
                BetaComputerTypeToolUseBlock.builder()
                    .id("id")
                    .input(BetaComputerTypeInput.of("text"))
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolUseBlock),
                jacksonTypeRef<BetaComputerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerToolUseBlock).isEqualTo(betaComputerToolUseBlock)
    }

    @Test
    fun ofCursorPosition() {
        val cursorPosition =
            BetaComputerCursorPositionToolUseBlock.builder()
                .id("id")
                .input(BetaComputerCursorPositionInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaComputerToolUseBlock = BetaComputerToolUseBlock.ofCursorPosition(cursorPosition)

        assertThat(betaComputerToolUseBlock.key()).isEmpty
        assertThat(betaComputerToolUseBlock.holdKey()).isEmpty
        assertThat(betaComputerToolUseBlock.type()).isEmpty
        assertThat(betaComputerToolUseBlock.cursorPosition()).contains(cursorPosition)
        assertThat(betaComputerToolUseBlock.mouseMove()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClick()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaComputerToolUseBlock.rightClick()).isEmpty
        assertThat(betaComputerToolUseBlock.middleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.doubleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.tripleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.scroll()).isEmpty
        assertThat(betaComputerToolUseBlock.wait()).isEmpty
        assertThat(betaComputerToolUseBlock.screenshot()).isEmpty
        assertThat(betaComputerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofCursorPositionRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolUseBlock =
            BetaComputerToolUseBlock.ofCursorPosition(
                BetaComputerCursorPositionToolUseBlock.builder()
                    .id("id")
                    .input(BetaComputerCursorPositionInput.builder().build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolUseBlock),
                jacksonTypeRef<BetaComputerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerToolUseBlock).isEqualTo(betaComputerToolUseBlock)
    }

    @Test
    fun ofMouseMove() {
        val mouseMove =
            BetaComputerMouseMoveToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerMouseMoveInput.builder().addCoordinate(0L).addCoordinate(0L).build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaComputerToolUseBlock = BetaComputerToolUseBlock.ofMouseMove(mouseMove)

        assertThat(betaComputerToolUseBlock.key()).isEmpty
        assertThat(betaComputerToolUseBlock.holdKey()).isEmpty
        assertThat(betaComputerToolUseBlock.type()).isEmpty
        assertThat(betaComputerToolUseBlock.cursorPosition()).isEmpty
        assertThat(betaComputerToolUseBlock.mouseMove()).contains(mouseMove)
        assertThat(betaComputerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClick()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaComputerToolUseBlock.rightClick()).isEmpty
        assertThat(betaComputerToolUseBlock.middleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.doubleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.tripleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.scroll()).isEmpty
        assertThat(betaComputerToolUseBlock.wait()).isEmpty
        assertThat(betaComputerToolUseBlock.screenshot()).isEmpty
        assertThat(betaComputerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofMouseMoveRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolUseBlock =
            BetaComputerToolUseBlock.ofMouseMove(
                BetaComputerMouseMoveToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaComputerMouseMoveInput.builder()
                            .addCoordinate(0L)
                            .addCoordinate(0L)
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolUseBlock),
                jacksonTypeRef<BetaComputerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerToolUseBlock).isEqualTo(betaComputerToolUseBlock)
    }

    @Test
    fun ofLeftMouseDown() {
        val leftMouseDown =
            BetaComputerLeftMouseDownToolUseBlock.builder()
                .id("id")
                .input(BetaComputerLeftMouseDownInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaComputerToolUseBlock = BetaComputerToolUseBlock.ofLeftMouseDown(leftMouseDown)

        assertThat(betaComputerToolUseBlock.key()).isEmpty
        assertThat(betaComputerToolUseBlock.holdKey()).isEmpty
        assertThat(betaComputerToolUseBlock.type()).isEmpty
        assertThat(betaComputerToolUseBlock.cursorPosition()).isEmpty
        assertThat(betaComputerToolUseBlock.mouseMove()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseDown()).contains(leftMouseDown)
        assertThat(betaComputerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClick()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaComputerToolUseBlock.rightClick()).isEmpty
        assertThat(betaComputerToolUseBlock.middleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.doubleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.tripleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.scroll()).isEmpty
        assertThat(betaComputerToolUseBlock.wait()).isEmpty
        assertThat(betaComputerToolUseBlock.screenshot()).isEmpty
        assertThat(betaComputerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofLeftMouseDownRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolUseBlock =
            BetaComputerToolUseBlock.ofLeftMouseDown(
                BetaComputerLeftMouseDownToolUseBlock.builder()
                    .id("id")
                    .input(BetaComputerLeftMouseDownInput.builder().build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolUseBlock),
                jacksonTypeRef<BetaComputerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerToolUseBlock).isEqualTo(betaComputerToolUseBlock)
    }

    @Test
    fun ofLeftMouseUp() {
        val leftMouseUp =
            BetaComputerLeftMouseUpToolUseBlock.builder()
                .id("id")
                .input(BetaComputerLeftMouseUpInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaComputerToolUseBlock = BetaComputerToolUseBlock.ofLeftMouseUp(leftMouseUp)

        assertThat(betaComputerToolUseBlock.key()).isEmpty
        assertThat(betaComputerToolUseBlock.holdKey()).isEmpty
        assertThat(betaComputerToolUseBlock.type()).isEmpty
        assertThat(betaComputerToolUseBlock.cursorPosition()).isEmpty
        assertThat(betaComputerToolUseBlock.mouseMove()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseUp()).contains(leftMouseUp)
        assertThat(betaComputerToolUseBlock.leftClick()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaComputerToolUseBlock.rightClick()).isEmpty
        assertThat(betaComputerToolUseBlock.middleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.doubleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.tripleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.scroll()).isEmpty
        assertThat(betaComputerToolUseBlock.wait()).isEmpty
        assertThat(betaComputerToolUseBlock.screenshot()).isEmpty
        assertThat(betaComputerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofLeftMouseUpRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolUseBlock =
            BetaComputerToolUseBlock.ofLeftMouseUp(
                BetaComputerLeftMouseUpToolUseBlock.builder()
                    .id("id")
                    .input(BetaComputerLeftMouseUpInput.builder().build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolUseBlock),
                jacksonTypeRef<BetaComputerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerToolUseBlock).isEqualTo(betaComputerToolUseBlock)
    }

    @Test
    fun ofLeftClick() {
        val leftClick =
            BetaComputerLeftClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerLeftClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaComputerToolUseBlock = BetaComputerToolUseBlock.ofLeftClick(leftClick)

        assertThat(betaComputerToolUseBlock.key()).isEmpty
        assertThat(betaComputerToolUseBlock.holdKey()).isEmpty
        assertThat(betaComputerToolUseBlock.type()).isEmpty
        assertThat(betaComputerToolUseBlock.cursorPosition()).isEmpty
        assertThat(betaComputerToolUseBlock.mouseMove()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClick()).contains(leftClick)
        assertThat(betaComputerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaComputerToolUseBlock.rightClick()).isEmpty
        assertThat(betaComputerToolUseBlock.middleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.doubleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.tripleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.scroll()).isEmpty
        assertThat(betaComputerToolUseBlock.wait()).isEmpty
        assertThat(betaComputerToolUseBlock.screenshot()).isEmpty
        assertThat(betaComputerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofLeftClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolUseBlock =
            BetaComputerToolUseBlock.ofLeftClick(
                BetaComputerLeftClickToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaComputerLeftClickInput.builder()
                            .addCoordinate(0L)
                            .addCoordinate(0L)
                            .text("text")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolUseBlock),
                jacksonTypeRef<BetaComputerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerToolUseBlock).isEqualTo(betaComputerToolUseBlock)
    }

    @Test
    fun ofLeftClickDrag() {
        val leftClickDrag =
            BetaComputerLeftClickDragToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerLeftClickDragInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .addStartCoordinate(0L)
                        .addStartCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaComputerToolUseBlock = BetaComputerToolUseBlock.ofLeftClickDrag(leftClickDrag)

        assertThat(betaComputerToolUseBlock.key()).isEmpty
        assertThat(betaComputerToolUseBlock.holdKey()).isEmpty
        assertThat(betaComputerToolUseBlock.type()).isEmpty
        assertThat(betaComputerToolUseBlock.cursorPosition()).isEmpty
        assertThat(betaComputerToolUseBlock.mouseMove()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClick()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClickDrag()).contains(leftClickDrag)
        assertThat(betaComputerToolUseBlock.rightClick()).isEmpty
        assertThat(betaComputerToolUseBlock.middleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.doubleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.tripleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.scroll()).isEmpty
        assertThat(betaComputerToolUseBlock.wait()).isEmpty
        assertThat(betaComputerToolUseBlock.screenshot()).isEmpty
        assertThat(betaComputerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofLeftClickDragRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolUseBlock =
            BetaComputerToolUseBlock.ofLeftClickDrag(
                BetaComputerLeftClickDragToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaComputerLeftClickDragInput.builder()
                            .addCoordinate(0L)
                            .addCoordinate(0L)
                            .addStartCoordinate(0L)
                            .addStartCoordinate(0L)
                            .text("text")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolUseBlock),
                jacksonTypeRef<BetaComputerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerToolUseBlock).isEqualTo(betaComputerToolUseBlock)
    }

    @Test
    fun ofRightClick() {
        val rightClick =
            BetaComputerRightClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerRightClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaComputerToolUseBlock = BetaComputerToolUseBlock.ofRightClick(rightClick)

        assertThat(betaComputerToolUseBlock.key()).isEmpty
        assertThat(betaComputerToolUseBlock.holdKey()).isEmpty
        assertThat(betaComputerToolUseBlock.type()).isEmpty
        assertThat(betaComputerToolUseBlock.cursorPosition()).isEmpty
        assertThat(betaComputerToolUseBlock.mouseMove()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClick()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaComputerToolUseBlock.rightClick()).contains(rightClick)
        assertThat(betaComputerToolUseBlock.middleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.doubleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.tripleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.scroll()).isEmpty
        assertThat(betaComputerToolUseBlock.wait()).isEmpty
        assertThat(betaComputerToolUseBlock.screenshot()).isEmpty
        assertThat(betaComputerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofRightClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolUseBlock =
            BetaComputerToolUseBlock.ofRightClick(
                BetaComputerRightClickToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaComputerRightClickInput.builder()
                            .addCoordinate(0L)
                            .addCoordinate(0L)
                            .text("text")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolUseBlock),
                jacksonTypeRef<BetaComputerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerToolUseBlock).isEqualTo(betaComputerToolUseBlock)
    }

    @Test
    fun ofMiddleClick() {
        val middleClick =
            BetaComputerMiddleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerMiddleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaComputerToolUseBlock = BetaComputerToolUseBlock.ofMiddleClick(middleClick)

        assertThat(betaComputerToolUseBlock.key()).isEmpty
        assertThat(betaComputerToolUseBlock.holdKey()).isEmpty
        assertThat(betaComputerToolUseBlock.type()).isEmpty
        assertThat(betaComputerToolUseBlock.cursorPosition()).isEmpty
        assertThat(betaComputerToolUseBlock.mouseMove()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClick()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaComputerToolUseBlock.rightClick()).isEmpty
        assertThat(betaComputerToolUseBlock.middleClick()).contains(middleClick)
        assertThat(betaComputerToolUseBlock.doubleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.tripleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.scroll()).isEmpty
        assertThat(betaComputerToolUseBlock.wait()).isEmpty
        assertThat(betaComputerToolUseBlock.screenshot()).isEmpty
        assertThat(betaComputerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofMiddleClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolUseBlock =
            BetaComputerToolUseBlock.ofMiddleClick(
                BetaComputerMiddleClickToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaComputerMiddleClickInput.builder()
                            .addCoordinate(0L)
                            .addCoordinate(0L)
                            .text("text")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolUseBlock),
                jacksonTypeRef<BetaComputerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerToolUseBlock).isEqualTo(betaComputerToolUseBlock)
    }

    @Test
    fun ofDoubleClick() {
        val doubleClick =
            BetaComputerDoubleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerDoubleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaComputerToolUseBlock = BetaComputerToolUseBlock.ofDoubleClick(doubleClick)

        assertThat(betaComputerToolUseBlock.key()).isEmpty
        assertThat(betaComputerToolUseBlock.holdKey()).isEmpty
        assertThat(betaComputerToolUseBlock.type()).isEmpty
        assertThat(betaComputerToolUseBlock.cursorPosition()).isEmpty
        assertThat(betaComputerToolUseBlock.mouseMove()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClick()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaComputerToolUseBlock.rightClick()).isEmpty
        assertThat(betaComputerToolUseBlock.middleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.doubleClick()).contains(doubleClick)
        assertThat(betaComputerToolUseBlock.tripleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.scroll()).isEmpty
        assertThat(betaComputerToolUseBlock.wait()).isEmpty
        assertThat(betaComputerToolUseBlock.screenshot()).isEmpty
        assertThat(betaComputerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofDoubleClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolUseBlock =
            BetaComputerToolUseBlock.ofDoubleClick(
                BetaComputerDoubleClickToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaComputerDoubleClickInput.builder()
                            .addCoordinate(0L)
                            .addCoordinate(0L)
                            .text("text")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolUseBlock),
                jacksonTypeRef<BetaComputerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerToolUseBlock).isEqualTo(betaComputerToolUseBlock)
    }

    @Test
    fun ofTripleClick() {
        val tripleClick =
            BetaComputerTripleClickToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerTripleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaComputerToolUseBlock = BetaComputerToolUseBlock.ofTripleClick(tripleClick)

        assertThat(betaComputerToolUseBlock.key()).isEmpty
        assertThat(betaComputerToolUseBlock.holdKey()).isEmpty
        assertThat(betaComputerToolUseBlock.type()).isEmpty
        assertThat(betaComputerToolUseBlock.cursorPosition()).isEmpty
        assertThat(betaComputerToolUseBlock.mouseMove()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClick()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaComputerToolUseBlock.rightClick()).isEmpty
        assertThat(betaComputerToolUseBlock.middleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.doubleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.tripleClick()).contains(tripleClick)
        assertThat(betaComputerToolUseBlock.scroll()).isEmpty
        assertThat(betaComputerToolUseBlock.wait()).isEmpty
        assertThat(betaComputerToolUseBlock.screenshot()).isEmpty
        assertThat(betaComputerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofTripleClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolUseBlock =
            BetaComputerToolUseBlock.ofTripleClick(
                BetaComputerTripleClickToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaComputerTripleClickInput.builder()
                            .addCoordinate(0L)
                            .addCoordinate(0L)
                            .text("text")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolUseBlock),
                jacksonTypeRef<BetaComputerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerToolUseBlock).isEqualTo(betaComputerToolUseBlock)
    }

    @Test
    fun ofScroll() {
        val scroll =
            BetaComputerScrollToolUseBlock.builder()
                .id("id")
                .input(
                    BetaComputerScrollInput.builder()
                        .scrollAmount(0L)
                        .scrollDirection(BetaComputerScrollDirection.UP)
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaComputerToolUseBlock = BetaComputerToolUseBlock.ofScroll(scroll)

        assertThat(betaComputerToolUseBlock.key()).isEmpty
        assertThat(betaComputerToolUseBlock.holdKey()).isEmpty
        assertThat(betaComputerToolUseBlock.type()).isEmpty
        assertThat(betaComputerToolUseBlock.cursorPosition()).isEmpty
        assertThat(betaComputerToolUseBlock.mouseMove()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClick()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaComputerToolUseBlock.rightClick()).isEmpty
        assertThat(betaComputerToolUseBlock.middleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.doubleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.tripleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.scroll()).contains(scroll)
        assertThat(betaComputerToolUseBlock.wait()).isEmpty
        assertThat(betaComputerToolUseBlock.screenshot()).isEmpty
        assertThat(betaComputerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofScrollRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolUseBlock =
            BetaComputerToolUseBlock.ofScroll(
                BetaComputerScrollToolUseBlock.builder()
                    .id("id")
                    .input(
                        BetaComputerScrollInput.builder()
                            .scrollAmount(0L)
                            .scrollDirection(BetaComputerScrollDirection.UP)
                            .addCoordinate(0L)
                            .addCoordinate(0L)
                            .text("text")
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolUseBlock),
                jacksonTypeRef<BetaComputerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerToolUseBlock).isEqualTo(betaComputerToolUseBlock)
    }

    @Test
    fun ofWait() {
        val wait =
            BetaComputerWaitToolUseBlock.builder()
                .id("id")
                .input(BetaComputerWaitInput.of(300L))
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaComputerToolUseBlock = BetaComputerToolUseBlock.ofWait(wait)

        assertThat(betaComputerToolUseBlock.key()).isEmpty
        assertThat(betaComputerToolUseBlock.holdKey()).isEmpty
        assertThat(betaComputerToolUseBlock.type()).isEmpty
        assertThat(betaComputerToolUseBlock.cursorPosition()).isEmpty
        assertThat(betaComputerToolUseBlock.mouseMove()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClick()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaComputerToolUseBlock.rightClick()).isEmpty
        assertThat(betaComputerToolUseBlock.middleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.doubleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.tripleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.scroll()).isEmpty
        assertThat(betaComputerToolUseBlock.wait()).contains(wait)
        assertThat(betaComputerToolUseBlock.screenshot()).isEmpty
        assertThat(betaComputerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofWaitRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolUseBlock =
            BetaComputerToolUseBlock.ofWait(
                BetaComputerWaitToolUseBlock.builder()
                    .id("id")
                    .input(BetaComputerWaitInput.of(300L))
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolUseBlock),
                jacksonTypeRef<BetaComputerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerToolUseBlock).isEqualTo(betaComputerToolUseBlock)
    }

    @Test
    fun ofScreenshot() {
        val screenshot =
            BetaComputerScreenshotToolUseBlock.builder()
                .id("id")
                .input(BetaComputerScreenshotInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaComputerToolUseBlock = BetaComputerToolUseBlock.ofScreenshot(screenshot)

        assertThat(betaComputerToolUseBlock.key()).isEmpty
        assertThat(betaComputerToolUseBlock.holdKey()).isEmpty
        assertThat(betaComputerToolUseBlock.type()).isEmpty
        assertThat(betaComputerToolUseBlock.cursorPosition()).isEmpty
        assertThat(betaComputerToolUseBlock.mouseMove()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClick()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaComputerToolUseBlock.rightClick()).isEmpty
        assertThat(betaComputerToolUseBlock.middleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.doubleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.tripleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.scroll()).isEmpty
        assertThat(betaComputerToolUseBlock.wait()).isEmpty
        assertThat(betaComputerToolUseBlock.screenshot()).contains(screenshot)
        assertThat(betaComputerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofScreenshotRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolUseBlock =
            BetaComputerToolUseBlock.ofScreenshot(
                BetaComputerScreenshotToolUseBlock.builder()
                    .id("id")
                    .input(BetaComputerScreenshotInput.builder().build())
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolUseBlock),
                jacksonTypeRef<BetaComputerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerToolUseBlock).isEqualTo(betaComputerToolUseBlock)
    }

    @Test
    fun ofZoom() {
        val zoom =
            BetaComputerZoomToolUseBlock.builder()
                .id("id")
                .input(BetaComputerZoomInput.of(listOf(0L, 0L, 0L, 0L)))
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaComputerToolUseBlock = BetaComputerToolUseBlock.ofZoom(zoom)

        assertThat(betaComputerToolUseBlock.key()).isEmpty
        assertThat(betaComputerToolUseBlock.holdKey()).isEmpty
        assertThat(betaComputerToolUseBlock.type()).isEmpty
        assertThat(betaComputerToolUseBlock.cursorPosition()).isEmpty
        assertThat(betaComputerToolUseBlock.mouseMove()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(betaComputerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClick()).isEmpty
        assertThat(betaComputerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(betaComputerToolUseBlock.rightClick()).isEmpty
        assertThat(betaComputerToolUseBlock.middleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.doubleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.tripleClick()).isEmpty
        assertThat(betaComputerToolUseBlock.scroll()).isEmpty
        assertThat(betaComputerToolUseBlock.wait()).isEmpty
        assertThat(betaComputerToolUseBlock.screenshot()).isEmpty
        assertThat(betaComputerToolUseBlock.zoom()).contains(zoom)
    }

    @Test
    fun ofZoomRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolUseBlock =
            BetaComputerToolUseBlock.ofZoom(
                BetaComputerZoomToolUseBlock.builder()
                    .id("id")
                    .input(BetaComputerZoomInput.of(listOf(0L, 0L, 0L, 0L)))
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolUseBlock),
                jacksonTypeRef<BetaComputerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaComputerToolUseBlock).isEqualTo(betaComputerToolUseBlock)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaComputerToolUseBlock =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "name" to "unknown_variant",
                            "id" to "id",
                            "caller" to mapOf("type" to "direct"),
                        )
                    ),
                    jacksonTypeRef<BetaComputerToolUseBlock>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { betaComputerToolUseBlock.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaComputerToolUseBlock.id()).isEqualTo("id")
        assertThat(betaComputerToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))

        val mismatchedBetaComputerToolUseBlock =
            jsonMapper()
                .convertValue(
                    JsonValue.from(mapOf("name" to "unknown_variant", "id" to listOf("invalid"))),
                    jacksonTypeRef<BetaComputerToolUseBlock>(),
                )

        assertThrows<AnthropicInvalidDataException> { mismatchedBetaComputerToolUseBlock.id() }
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
        val betaComputerToolUseBlock =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaComputerToolUseBlock>())

        val e = assertThrows<AnthropicInvalidDataException> { betaComputerToolUseBlock.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> { betaComputerToolUseBlock.id() }
        assertThat(betaComputerToolUseBlock.caller()).isEmpty
    }
}
