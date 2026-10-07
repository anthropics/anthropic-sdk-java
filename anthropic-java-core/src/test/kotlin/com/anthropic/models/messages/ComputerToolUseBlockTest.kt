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

internal class ComputerToolUseBlockTest {

    @Test
    fun ofKey() {
        val key =
            ComputerKeyToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerKeyInput.builder().text("text").repeat(1L).build())
                .build()

        val computerToolUseBlock = ComputerToolUseBlock.ofKey(key)

        assertThat(computerToolUseBlock.key()).contains(key)
        assertThat(computerToolUseBlock.holdKey()).isEmpty
        assertThat(computerToolUseBlock.type()).isEmpty
        assertThat(computerToolUseBlock.cursorPosition()).isEmpty
        assertThat(computerToolUseBlock.mouseMove()).isEmpty
        assertThat(computerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(computerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(computerToolUseBlock.leftClick()).isEmpty
        assertThat(computerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(computerToolUseBlock.rightClick()).isEmpty
        assertThat(computerToolUseBlock.middleClick()).isEmpty
        assertThat(computerToolUseBlock.doubleClick()).isEmpty
        assertThat(computerToolUseBlock.tripleClick()).isEmpty
        assertThat(computerToolUseBlock.scroll()).isEmpty
        assertThat(computerToolUseBlock.wait()).isEmpty
        assertThat(computerToolUseBlock.screenshot()).isEmpty
        assertThat(computerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofKeyRoundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolUseBlock =
            ComputerToolUseBlock.ofKey(
                ComputerKeyToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(ComputerKeyInput.builder().text("text").repeat(1L).build())
                    .build()
            )

        val roundtrippedComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolUseBlock),
                jacksonTypeRef<ComputerToolUseBlock>(),
            )

        assertThat(roundtrippedComputerToolUseBlock).isEqualTo(computerToolUseBlock)
    }

    @Test
    fun ofHoldKey() {
        val holdKey =
            ComputerHoldKeyToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerHoldKeyInput.builder().duration(300L).text("text").build())
                .build()

        val computerToolUseBlock = ComputerToolUseBlock.ofHoldKey(holdKey)

        assertThat(computerToolUseBlock.key()).isEmpty
        assertThat(computerToolUseBlock.holdKey()).contains(holdKey)
        assertThat(computerToolUseBlock.type()).isEmpty
        assertThat(computerToolUseBlock.cursorPosition()).isEmpty
        assertThat(computerToolUseBlock.mouseMove()).isEmpty
        assertThat(computerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(computerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(computerToolUseBlock.leftClick()).isEmpty
        assertThat(computerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(computerToolUseBlock.rightClick()).isEmpty
        assertThat(computerToolUseBlock.middleClick()).isEmpty
        assertThat(computerToolUseBlock.doubleClick()).isEmpty
        assertThat(computerToolUseBlock.tripleClick()).isEmpty
        assertThat(computerToolUseBlock.scroll()).isEmpty
        assertThat(computerToolUseBlock.wait()).isEmpty
        assertThat(computerToolUseBlock.screenshot()).isEmpty
        assertThat(computerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofHoldKeyRoundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolUseBlock =
            ComputerToolUseBlock.ofHoldKey(
                ComputerHoldKeyToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(ComputerHoldKeyInput.builder().duration(300L).text("text").build())
                    .build()
            )

        val roundtrippedComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolUseBlock),
                jacksonTypeRef<ComputerToolUseBlock>(),
            )

        assertThat(roundtrippedComputerToolUseBlock).isEqualTo(computerToolUseBlock)
    }

    @Test
    fun ofType() {
        val type =
            ComputerTypeToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerTypeInput.of("text"))
                .build()

        val computerToolUseBlock = ComputerToolUseBlock.ofType(type)

        assertThat(computerToolUseBlock.key()).isEmpty
        assertThat(computerToolUseBlock.holdKey()).isEmpty
        assertThat(computerToolUseBlock.type()).contains(type)
        assertThat(computerToolUseBlock.cursorPosition()).isEmpty
        assertThat(computerToolUseBlock.mouseMove()).isEmpty
        assertThat(computerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(computerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(computerToolUseBlock.leftClick()).isEmpty
        assertThat(computerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(computerToolUseBlock.rightClick()).isEmpty
        assertThat(computerToolUseBlock.middleClick()).isEmpty
        assertThat(computerToolUseBlock.doubleClick()).isEmpty
        assertThat(computerToolUseBlock.tripleClick()).isEmpty
        assertThat(computerToolUseBlock.scroll()).isEmpty
        assertThat(computerToolUseBlock.wait()).isEmpty
        assertThat(computerToolUseBlock.screenshot()).isEmpty
        assertThat(computerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofTypeRoundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolUseBlock =
            ComputerToolUseBlock.ofType(
                ComputerTypeToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(ComputerTypeInput.of("text"))
                    .build()
            )

        val roundtrippedComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolUseBlock),
                jacksonTypeRef<ComputerToolUseBlock>(),
            )

        assertThat(roundtrippedComputerToolUseBlock).isEqualTo(computerToolUseBlock)
    }

    @Test
    fun ofCursorPosition() {
        val cursorPosition =
            ComputerCursorPositionToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerCursorPositionInput.builder().build())
                .build()

        val computerToolUseBlock = ComputerToolUseBlock.ofCursorPosition(cursorPosition)

        assertThat(computerToolUseBlock.key()).isEmpty
        assertThat(computerToolUseBlock.holdKey()).isEmpty
        assertThat(computerToolUseBlock.type()).isEmpty
        assertThat(computerToolUseBlock.cursorPosition()).contains(cursorPosition)
        assertThat(computerToolUseBlock.mouseMove()).isEmpty
        assertThat(computerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(computerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(computerToolUseBlock.leftClick()).isEmpty
        assertThat(computerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(computerToolUseBlock.rightClick()).isEmpty
        assertThat(computerToolUseBlock.middleClick()).isEmpty
        assertThat(computerToolUseBlock.doubleClick()).isEmpty
        assertThat(computerToolUseBlock.tripleClick()).isEmpty
        assertThat(computerToolUseBlock.scroll()).isEmpty
        assertThat(computerToolUseBlock.wait()).isEmpty
        assertThat(computerToolUseBlock.screenshot()).isEmpty
        assertThat(computerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofCursorPositionRoundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolUseBlock =
            ComputerToolUseBlock.ofCursorPosition(
                ComputerCursorPositionToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(ComputerCursorPositionInput.builder().build())
                    .build()
            )

        val roundtrippedComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolUseBlock),
                jacksonTypeRef<ComputerToolUseBlock>(),
            )

        assertThat(roundtrippedComputerToolUseBlock).isEqualTo(computerToolUseBlock)
    }

    @Test
    fun ofMouseMove() {
        val mouseMove =
            ComputerMouseMoveToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerMouseMoveInput.builder().addCoordinate(0L).addCoordinate(0L).build())
                .build()

        val computerToolUseBlock = ComputerToolUseBlock.ofMouseMove(mouseMove)

        assertThat(computerToolUseBlock.key()).isEmpty
        assertThat(computerToolUseBlock.holdKey()).isEmpty
        assertThat(computerToolUseBlock.type()).isEmpty
        assertThat(computerToolUseBlock.cursorPosition()).isEmpty
        assertThat(computerToolUseBlock.mouseMove()).contains(mouseMove)
        assertThat(computerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(computerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(computerToolUseBlock.leftClick()).isEmpty
        assertThat(computerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(computerToolUseBlock.rightClick()).isEmpty
        assertThat(computerToolUseBlock.middleClick()).isEmpty
        assertThat(computerToolUseBlock.doubleClick()).isEmpty
        assertThat(computerToolUseBlock.tripleClick()).isEmpty
        assertThat(computerToolUseBlock.scroll()).isEmpty
        assertThat(computerToolUseBlock.wait()).isEmpty
        assertThat(computerToolUseBlock.screenshot()).isEmpty
        assertThat(computerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofMouseMoveRoundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolUseBlock =
            ComputerToolUseBlock.ofMouseMove(
                ComputerMouseMoveToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        ComputerMouseMoveInput.builder().addCoordinate(0L).addCoordinate(0L).build()
                    )
                    .build()
            )

        val roundtrippedComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolUseBlock),
                jacksonTypeRef<ComputerToolUseBlock>(),
            )

        assertThat(roundtrippedComputerToolUseBlock).isEqualTo(computerToolUseBlock)
    }

    @Test
    fun ofLeftMouseDown() {
        val leftMouseDown =
            ComputerLeftMouseDownToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerLeftMouseDownInput.builder().build())
                .build()

        val computerToolUseBlock = ComputerToolUseBlock.ofLeftMouseDown(leftMouseDown)

        assertThat(computerToolUseBlock.key()).isEmpty
        assertThat(computerToolUseBlock.holdKey()).isEmpty
        assertThat(computerToolUseBlock.type()).isEmpty
        assertThat(computerToolUseBlock.cursorPosition()).isEmpty
        assertThat(computerToolUseBlock.mouseMove()).isEmpty
        assertThat(computerToolUseBlock.leftMouseDown()).contains(leftMouseDown)
        assertThat(computerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(computerToolUseBlock.leftClick()).isEmpty
        assertThat(computerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(computerToolUseBlock.rightClick()).isEmpty
        assertThat(computerToolUseBlock.middleClick()).isEmpty
        assertThat(computerToolUseBlock.doubleClick()).isEmpty
        assertThat(computerToolUseBlock.tripleClick()).isEmpty
        assertThat(computerToolUseBlock.scroll()).isEmpty
        assertThat(computerToolUseBlock.wait()).isEmpty
        assertThat(computerToolUseBlock.screenshot()).isEmpty
        assertThat(computerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofLeftMouseDownRoundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolUseBlock =
            ComputerToolUseBlock.ofLeftMouseDown(
                ComputerLeftMouseDownToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(ComputerLeftMouseDownInput.builder().build())
                    .build()
            )

        val roundtrippedComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolUseBlock),
                jacksonTypeRef<ComputerToolUseBlock>(),
            )

        assertThat(roundtrippedComputerToolUseBlock).isEqualTo(computerToolUseBlock)
    }

    @Test
    fun ofLeftMouseUp() {
        val leftMouseUp =
            ComputerLeftMouseUpToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerLeftMouseUpInput.builder().build())
                .build()

        val computerToolUseBlock = ComputerToolUseBlock.ofLeftMouseUp(leftMouseUp)

        assertThat(computerToolUseBlock.key()).isEmpty
        assertThat(computerToolUseBlock.holdKey()).isEmpty
        assertThat(computerToolUseBlock.type()).isEmpty
        assertThat(computerToolUseBlock.cursorPosition()).isEmpty
        assertThat(computerToolUseBlock.mouseMove()).isEmpty
        assertThat(computerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(computerToolUseBlock.leftMouseUp()).contains(leftMouseUp)
        assertThat(computerToolUseBlock.leftClick()).isEmpty
        assertThat(computerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(computerToolUseBlock.rightClick()).isEmpty
        assertThat(computerToolUseBlock.middleClick()).isEmpty
        assertThat(computerToolUseBlock.doubleClick()).isEmpty
        assertThat(computerToolUseBlock.tripleClick()).isEmpty
        assertThat(computerToolUseBlock.scroll()).isEmpty
        assertThat(computerToolUseBlock.wait()).isEmpty
        assertThat(computerToolUseBlock.screenshot()).isEmpty
        assertThat(computerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofLeftMouseUpRoundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolUseBlock =
            ComputerToolUseBlock.ofLeftMouseUp(
                ComputerLeftMouseUpToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(ComputerLeftMouseUpInput.builder().build())
                    .build()
            )

        val roundtrippedComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolUseBlock),
                jacksonTypeRef<ComputerToolUseBlock>(),
            )

        assertThat(roundtrippedComputerToolUseBlock).isEqualTo(computerToolUseBlock)
    }

    @Test
    fun ofLeftClick() {
        val leftClick =
            ComputerLeftClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerLeftClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        val computerToolUseBlock = ComputerToolUseBlock.ofLeftClick(leftClick)

        assertThat(computerToolUseBlock.key()).isEmpty
        assertThat(computerToolUseBlock.holdKey()).isEmpty
        assertThat(computerToolUseBlock.type()).isEmpty
        assertThat(computerToolUseBlock.cursorPosition()).isEmpty
        assertThat(computerToolUseBlock.mouseMove()).isEmpty
        assertThat(computerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(computerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(computerToolUseBlock.leftClick()).contains(leftClick)
        assertThat(computerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(computerToolUseBlock.rightClick()).isEmpty
        assertThat(computerToolUseBlock.middleClick()).isEmpty
        assertThat(computerToolUseBlock.doubleClick()).isEmpty
        assertThat(computerToolUseBlock.tripleClick()).isEmpty
        assertThat(computerToolUseBlock.scroll()).isEmpty
        assertThat(computerToolUseBlock.wait()).isEmpty
        assertThat(computerToolUseBlock.screenshot()).isEmpty
        assertThat(computerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofLeftClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolUseBlock =
            ComputerToolUseBlock.ofLeftClick(
                ComputerLeftClickToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        ComputerLeftClickInput.builder()
                            .addCoordinate(0L)
                            .addCoordinate(0L)
                            .text("text")
                            .build()
                    )
                    .build()
            )

        val roundtrippedComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolUseBlock),
                jacksonTypeRef<ComputerToolUseBlock>(),
            )

        assertThat(roundtrippedComputerToolUseBlock).isEqualTo(computerToolUseBlock)
    }

    @Test
    fun ofLeftClickDrag() {
        val leftClickDrag =
            ComputerLeftClickDragToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerLeftClickDragInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .addStartCoordinate(0L)
                        .addStartCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        val computerToolUseBlock = ComputerToolUseBlock.ofLeftClickDrag(leftClickDrag)

        assertThat(computerToolUseBlock.key()).isEmpty
        assertThat(computerToolUseBlock.holdKey()).isEmpty
        assertThat(computerToolUseBlock.type()).isEmpty
        assertThat(computerToolUseBlock.cursorPosition()).isEmpty
        assertThat(computerToolUseBlock.mouseMove()).isEmpty
        assertThat(computerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(computerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(computerToolUseBlock.leftClick()).isEmpty
        assertThat(computerToolUseBlock.leftClickDrag()).contains(leftClickDrag)
        assertThat(computerToolUseBlock.rightClick()).isEmpty
        assertThat(computerToolUseBlock.middleClick()).isEmpty
        assertThat(computerToolUseBlock.doubleClick()).isEmpty
        assertThat(computerToolUseBlock.tripleClick()).isEmpty
        assertThat(computerToolUseBlock.scroll()).isEmpty
        assertThat(computerToolUseBlock.wait()).isEmpty
        assertThat(computerToolUseBlock.screenshot()).isEmpty
        assertThat(computerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofLeftClickDragRoundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolUseBlock =
            ComputerToolUseBlock.ofLeftClickDrag(
                ComputerLeftClickDragToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        ComputerLeftClickDragInput.builder()
                            .addCoordinate(0L)
                            .addCoordinate(0L)
                            .addStartCoordinate(0L)
                            .addStartCoordinate(0L)
                            .text("text")
                            .build()
                    )
                    .build()
            )

        val roundtrippedComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolUseBlock),
                jacksonTypeRef<ComputerToolUseBlock>(),
            )

        assertThat(roundtrippedComputerToolUseBlock).isEqualTo(computerToolUseBlock)
    }

    @Test
    fun ofRightClick() {
        val rightClick =
            ComputerRightClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerRightClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        val computerToolUseBlock = ComputerToolUseBlock.ofRightClick(rightClick)

        assertThat(computerToolUseBlock.key()).isEmpty
        assertThat(computerToolUseBlock.holdKey()).isEmpty
        assertThat(computerToolUseBlock.type()).isEmpty
        assertThat(computerToolUseBlock.cursorPosition()).isEmpty
        assertThat(computerToolUseBlock.mouseMove()).isEmpty
        assertThat(computerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(computerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(computerToolUseBlock.leftClick()).isEmpty
        assertThat(computerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(computerToolUseBlock.rightClick()).contains(rightClick)
        assertThat(computerToolUseBlock.middleClick()).isEmpty
        assertThat(computerToolUseBlock.doubleClick()).isEmpty
        assertThat(computerToolUseBlock.tripleClick()).isEmpty
        assertThat(computerToolUseBlock.scroll()).isEmpty
        assertThat(computerToolUseBlock.wait()).isEmpty
        assertThat(computerToolUseBlock.screenshot()).isEmpty
        assertThat(computerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofRightClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolUseBlock =
            ComputerToolUseBlock.ofRightClick(
                ComputerRightClickToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        ComputerRightClickInput.builder()
                            .addCoordinate(0L)
                            .addCoordinate(0L)
                            .text("text")
                            .build()
                    )
                    .build()
            )

        val roundtrippedComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolUseBlock),
                jacksonTypeRef<ComputerToolUseBlock>(),
            )

        assertThat(roundtrippedComputerToolUseBlock).isEqualTo(computerToolUseBlock)
    }

    @Test
    fun ofMiddleClick() {
        val middleClick =
            ComputerMiddleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerMiddleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        val computerToolUseBlock = ComputerToolUseBlock.ofMiddleClick(middleClick)

        assertThat(computerToolUseBlock.key()).isEmpty
        assertThat(computerToolUseBlock.holdKey()).isEmpty
        assertThat(computerToolUseBlock.type()).isEmpty
        assertThat(computerToolUseBlock.cursorPosition()).isEmpty
        assertThat(computerToolUseBlock.mouseMove()).isEmpty
        assertThat(computerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(computerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(computerToolUseBlock.leftClick()).isEmpty
        assertThat(computerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(computerToolUseBlock.rightClick()).isEmpty
        assertThat(computerToolUseBlock.middleClick()).contains(middleClick)
        assertThat(computerToolUseBlock.doubleClick()).isEmpty
        assertThat(computerToolUseBlock.tripleClick()).isEmpty
        assertThat(computerToolUseBlock.scroll()).isEmpty
        assertThat(computerToolUseBlock.wait()).isEmpty
        assertThat(computerToolUseBlock.screenshot()).isEmpty
        assertThat(computerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofMiddleClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolUseBlock =
            ComputerToolUseBlock.ofMiddleClick(
                ComputerMiddleClickToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        ComputerMiddleClickInput.builder()
                            .addCoordinate(0L)
                            .addCoordinate(0L)
                            .text("text")
                            .build()
                    )
                    .build()
            )

        val roundtrippedComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolUseBlock),
                jacksonTypeRef<ComputerToolUseBlock>(),
            )

        assertThat(roundtrippedComputerToolUseBlock).isEqualTo(computerToolUseBlock)
    }

    @Test
    fun ofDoubleClick() {
        val doubleClick =
            ComputerDoubleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerDoubleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        val computerToolUseBlock = ComputerToolUseBlock.ofDoubleClick(doubleClick)

        assertThat(computerToolUseBlock.key()).isEmpty
        assertThat(computerToolUseBlock.holdKey()).isEmpty
        assertThat(computerToolUseBlock.type()).isEmpty
        assertThat(computerToolUseBlock.cursorPosition()).isEmpty
        assertThat(computerToolUseBlock.mouseMove()).isEmpty
        assertThat(computerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(computerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(computerToolUseBlock.leftClick()).isEmpty
        assertThat(computerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(computerToolUseBlock.rightClick()).isEmpty
        assertThat(computerToolUseBlock.middleClick()).isEmpty
        assertThat(computerToolUseBlock.doubleClick()).contains(doubleClick)
        assertThat(computerToolUseBlock.tripleClick()).isEmpty
        assertThat(computerToolUseBlock.scroll()).isEmpty
        assertThat(computerToolUseBlock.wait()).isEmpty
        assertThat(computerToolUseBlock.screenshot()).isEmpty
        assertThat(computerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofDoubleClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolUseBlock =
            ComputerToolUseBlock.ofDoubleClick(
                ComputerDoubleClickToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        ComputerDoubleClickInput.builder()
                            .addCoordinate(0L)
                            .addCoordinate(0L)
                            .text("text")
                            .build()
                    )
                    .build()
            )

        val roundtrippedComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolUseBlock),
                jacksonTypeRef<ComputerToolUseBlock>(),
            )

        assertThat(roundtrippedComputerToolUseBlock).isEqualTo(computerToolUseBlock)
    }

    @Test
    fun ofTripleClick() {
        val tripleClick =
            ComputerTripleClickToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerTripleClickInput.builder()
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        val computerToolUseBlock = ComputerToolUseBlock.ofTripleClick(tripleClick)

        assertThat(computerToolUseBlock.key()).isEmpty
        assertThat(computerToolUseBlock.holdKey()).isEmpty
        assertThat(computerToolUseBlock.type()).isEmpty
        assertThat(computerToolUseBlock.cursorPosition()).isEmpty
        assertThat(computerToolUseBlock.mouseMove()).isEmpty
        assertThat(computerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(computerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(computerToolUseBlock.leftClick()).isEmpty
        assertThat(computerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(computerToolUseBlock.rightClick()).isEmpty
        assertThat(computerToolUseBlock.middleClick()).isEmpty
        assertThat(computerToolUseBlock.doubleClick()).isEmpty
        assertThat(computerToolUseBlock.tripleClick()).contains(tripleClick)
        assertThat(computerToolUseBlock.scroll()).isEmpty
        assertThat(computerToolUseBlock.wait()).isEmpty
        assertThat(computerToolUseBlock.screenshot()).isEmpty
        assertThat(computerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofTripleClickRoundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolUseBlock =
            ComputerToolUseBlock.ofTripleClick(
                ComputerTripleClickToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        ComputerTripleClickInput.builder()
                            .addCoordinate(0L)
                            .addCoordinate(0L)
                            .text("text")
                            .build()
                    )
                    .build()
            )

        val roundtrippedComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolUseBlock),
                jacksonTypeRef<ComputerToolUseBlock>(),
            )

        assertThat(roundtrippedComputerToolUseBlock).isEqualTo(computerToolUseBlock)
    }

    @Test
    fun ofScroll() {
        val scroll =
            ComputerScrollToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ComputerScrollInput.builder()
                        .scrollAmount(0L)
                        .scrollDirection(ComputerScrollDirection.UP)
                        .addCoordinate(0L)
                        .addCoordinate(0L)
                        .text("text")
                        .build()
                )
                .build()

        val computerToolUseBlock = ComputerToolUseBlock.ofScroll(scroll)

        assertThat(computerToolUseBlock.key()).isEmpty
        assertThat(computerToolUseBlock.holdKey()).isEmpty
        assertThat(computerToolUseBlock.type()).isEmpty
        assertThat(computerToolUseBlock.cursorPosition()).isEmpty
        assertThat(computerToolUseBlock.mouseMove()).isEmpty
        assertThat(computerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(computerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(computerToolUseBlock.leftClick()).isEmpty
        assertThat(computerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(computerToolUseBlock.rightClick()).isEmpty
        assertThat(computerToolUseBlock.middleClick()).isEmpty
        assertThat(computerToolUseBlock.doubleClick()).isEmpty
        assertThat(computerToolUseBlock.tripleClick()).isEmpty
        assertThat(computerToolUseBlock.scroll()).contains(scroll)
        assertThat(computerToolUseBlock.wait()).isEmpty
        assertThat(computerToolUseBlock.screenshot()).isEmpty
        assertThat(computerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofScrollRoundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolUseBlock =
            ComputerToolUseBlock.ofScroll(
                ComputerScrollToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(
                        ComputerScrollInput.builder()
                            .scrollAmount(0L)
                            .scrollDirection(ComputerScrollDirection.UP)
                            .addCoordinate(0L)
                            .addCoordinate(0L)
                            .text("text")
                            .build()
                    )
                    .build()
            )

        val roundtrippedComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolUseBlock),
                jacksonTypeRef<ComputerToolUseBlock>(),
            )

        assertThat(roundtrippedComputerToolUseBlock).isEqualTo(computerToolUseBlock)
    }

    @Test
    fun ofWait() {
        val wait =
            ComputerWaitToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerWaitInput.of(300L))
                .build()

        val computerToolUseBlock = ComputerToolUseBlock.ofWait(wait)

        assertThat(computerToolUseBlock.key()).isEmpty
        assertThat(computerToolUseBlock.holdKey()).isEmpty
        assertThat(computerToolUseBlock.type()).isEmpty
        assertThat(computerToolUseBlock.cursorPosition()).isEmpty
        assertThat(computerToolUseBlock.mouseMove()).isEmpty
        assertThat(computerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(computerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(computerToolUseBlock.leftClick()).isEmpty
        assertThat(computerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(computerToolUseBlock.rightClick()).isEmpty
        assertThat(computerToolUseBlock.middleClick()).isEmpty
        assertThat(computerToolUseBlock.doubleClick()).isEmpty
        assertThat(computerToolUseBlock.tripleClick()).isEmpty
        assertThat(computerToolUseBlock.scroll()).isEmpty
        assertThat(computerToolUseBlock.wait()).contains(wait)
        assertThat(computerToolUseBlock.screenshot()).isEmpty
        assertThat(computerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofWaitRoundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolUseBlock =
            ComputerToolUseBlock.ofWait(
                ComputerWaitToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(ComputerWaitInput.of(300L))
                    .build()
            )

        val roundtrippedComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolUseBlock),
                jacksonTypeRef<ComputerToolUseBlock>(),
            )

        assertThat(roundtrippedComputerToolUseBlock).isEqualTo(computerToolUseBlock)
    }

    @Test
    fun ofScreenshot() {
        val screenshot =
            ComputerScreenshotToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerScreenshotInput.builder().build())
                .build()

        val computerToolUseBlock = ComputerToolUseBlock.ofScreenshot(screenshot)

        assertThat(computerToolUseBlock.key()).isEmpty
        assertThat(computerToolUseBlock.holdKey()).isEmpty
        assertThat(computerToolUseBlock.type()).isEmpty
        assertThat(computerToolUseBlock.cursorPosition()).isEmpty
        assertThat(computerToolUseBlock.mouseMove()).isEmpty
        assertThat(computerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(computerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(computerToolUseBlock.leftClick()).isEmpty
        assertThat(computerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(computerToolUseBlock.rightClick()).isEmpty
        assertThat(computerToolUseBlock.middleClick()).isEmpty
        assertThat(computerToolUseBlock.doubleClick()).isEmpty
        assertThat(computerToolUseBlock.tripleClick()).isEmpty
        assertThat(computerToolUseBlock.scroll()).isEmpty
        assertThat(computerToolUseBlock.wait()).isEmpty
        assertThat(computerToolUseBlock.screenshot()).contains(screenshot)
        assertThat(computerToolUseBlock.zoom()).isEmpty
    }

    @Test
    fun ofScreenshotRoundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolUseBlock =
            ComputerToolUseBlock.ofScreenshot(
                ComputerScreenshotToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(ComputerScreenshotInput.builder().build())
                    .build()
            )

        val roundtrippedComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolUseBlock),
                jacksonTypeRef<ComputerToolUseBlock>(),
            )

        assertThat(roundtrippedComputerToolUseBlock).isEqualTo(computerToolUseBlock)
    }

    @Test
    fun ofZoom() {
        val zoom =
            ComputerZoomToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(ComputerZoomInput.of(listOf(0L, 0L, 0L, 0L)))
                .build()

        val computerToolUseBlock = ComputerToolUseBlock.ofZoom(zoom)

        assertThat(computerToolUseBlock.key()).isEmpty
        assertThat(computerToolUseBlock.holdKey()).isEmpty
        assertThat(computerToolUseBlock.type()).isEmpty
        assertThat(computerToolUseBlock.cursorPosition()).isEmpty
        assertThat(computerToolUseBlock.mouseMove()).isEmpty
        assertThat(computerToolUseBlock.leftMouseDown()).isEmpty
        assertThat(computerToolUseBlock.leftMouseUp()).isEmpty
        assertThat(computerToolUseBlock.leftClick()).isEmpty
        assertThat(computerToolUseBlock.leftClickDrag()).isEmpty
        assertThat(computerToolUseBlock.rightClick()).isEmpty
        assertThat(computerToolUseBlock.middleClick()).isEmpty
        assertThat(computerToolUseBlock.doubleClick()).isEmpty
        assertThat(computerToolUseBlock.tripleClick()).isEmpty
        assertThat(computerToolUseBlock.scroll()).isEmpty
        assertThat(computerToolUseBlock.wait()).isEmpty
        assertThat(computerToolUseBlock.screenshot()).isEmpty
        assertThat(computerToolUseBlock.zoom()).contains(zoom)
    }

    @Test
    fun ofZoomRoundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolUseBlock =
            ComputerToolUseBlock.ofZoom(
                ComputerZoomToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(ComputerZoomInput.of(listOf(0L, 0L, 0L, 0L)))
                    .build()
            )

        val roundtrippedComputerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolUseBlock),
                jacksonTypeRef<ComputerToolUseBlock>(),
            )

        assertThat(roundtrippedComputerToolUseBlock).isEqualTo(computerToolUseBlock)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val computerToolUseBlock =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "name" to "unknown_variant",
                            "id" to "id",
                            "caller" to mapOf("type" to "direct"),
                        )
                    ),
                    jacksonTypeRef<ComputerToolUseBlock>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { computerToolUseBlock.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(computerToolUseBlock.id()).isEqualTo("id")
        assertThat(computerToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))

        val mismatchedComputerToolUseBlock =
            jsonMapper()
                .convertValue(
                    JsonValue.from(mapOf("name" to "unknown_variant", "id" to listOf("invalid"))),
                    jacksonTypeRef<ComputerToolUseBlock>(),
                )

        assertThrows<AnthropicInvalidDataException> { mismatchedComputerToolUseBlock.id() }
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
        val computerToolUseBlock =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<ComputerToolUseBlock>())

        val e = assertThrows<AnthropicInvalidDataException> { computerToolUseBlock.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> { computerToolUseBlock.id() }
        assertThrows<AnthropicInvalidDataException> { computerToolUseBlock.caller() }
    }
}
