// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerToolsetConfigsTest {

    @Test
    fun create() {
        val computerToolsetConfigs =
            ComputerToolsetConfigs.builder()
                .cursorPosition(
                    ComputerCursorPositionConfig.builder().deferLoading(true).enabled(true).build()
                )
                .doubleClick(
                    ComputerDoubleClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .holdKey(ComputerHoldKeyConfig.builder().deferLoading(true).enabled(true).build())
                .key(ComputerKeyConfig.builder().deferLoading(true).enabled(true).build())
                .leftClick(
                    ComputerLeftClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .leftClickDrag(
                    ComputerLeftClickDragConfig.builder().deferLoading(true).enabled(true).build()
                )
                .leftMouseDown(
                    ComputerLeftMouseDownConfig.builder().deferLoading(true).enabled(true).build()
                )
                .leftMouseUp(
                    ComputerLeftMouseUpConfig.builder().deferLoading(true).enabled(true).build()
                )
                .middleClick(
                    ComputerMiddleClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .mouseMove(
                    ComputerMouseMoveConfig.builder().deferLoading(true).enabled(true).build()
                )
                .rightClick(
                    ComputerRightClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .screenshot(
                    ComputerScreenshotConfig.builder().deferLoading(true).enabled(true).build()
                )
                .scroll(ComputerScrollConfig.builder().deferLoading(true).enabled(true).build())
                .tripleClick(
                    ComputerTripleClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .type(ComputerTypeConfig.builder().deferLoading(true).enabled(true).build())
                .wait(ComputerWaitConfig.builder().deferLoading(true).enabled(true).build())
                .zoom(ComputerZoomConfig.builder().deferLoading(true).enabled(true).build())
                .build()

        assertThat(computerToolsetConfigs.cursorPosition())
            .contains(
                ComputerCursorPositionConfig.builder().deferLoading(true).enabled(true).build()
            )
        assertThat(computerToolsetConfigs.doubleClick())
            .contains(ComputerDoubleClickConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(computerToolsetConfigs.holdKey())
            .contains(ComputerHoldKeyConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(computerToolsetConfigs.key())
            .contains(ComputerKeyConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(computerToolsetConfigs.leftClick())
            .contains(ComputerLeftClickConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(computerToolsetConfigs.leftClickDrag())
            .contains(
                ComputerLeftClickDragConfig.builder().deferLoading(true).enabled(true).build()
            )
        assertThat(computerToolsetConfigs.leftMouseDown())
            .contains(
                ComputerLeftMouseDownConfig.builder().deferLoading(true).enabled(true).build()
            )
        assertThat(computerToolsetConfigs.leftMouseUp())
            .contains(ComputerLeftMouseUpConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(computerToolsetConfigs.middleClick())
            .contains(ComputerMiddleClickConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(computerToolsetConfigs.mouseMove())
            .contains(ComputerMouseMoveConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(computerToolsetConfigs.rightClick())
            .contains(ComputerRightClickConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(computerToolsetConfigs.screenshot())
            .contains(ComputerScreenshotConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(computerToolsetConfigs.scroll())
            .contains(ComputerScrollConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(computerToolsetConfigs.tripleClick())
            .contains(ComputerTripleClickConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(computerToolsetConfigs.type())
            .contains(ComputerTypeConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(computerToolsetConfigs.wait())
            .contains(ComputerWaitConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(computerToolsetConfigs.zoom())
            .contains(ComputerZoomConfig.builder().deferLoading(true).enabled(true).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolsetConfigs =
            ComputerToolsetConfigs.builder()
                .cursorPosition(
                    ComputerCursorPositionConfig.builder().deferLoading(true).enabled(true).build()
                )
                .doubleClick(
                    ComputerDoubleClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .holdKey(ComputerHoldKeyConfig.builder().deferLoading(true).enabled(true).build())
                .key(ComputerKeyConfig.builder().deferLoading(true).enabled(true).build())
                .leftClick(
                    ComputerLeftClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .leftClickDrag(
                    ComputerLeftClickDragConfig.builder().deferLoading(true).enabled(true).build()
                )
                .leftMouseDown(
                    ComputerLeftMouseDownConfig.builder().deferLoading(true).enabled(true).build()
                )
                .leftMouseUp(
                    ComputerLeftMouseUpConfig.builder().deferLoading(true).enabled(true).build()
                )
                .middleClick(
                    ComputerMiddleClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .mouseMove(
                    ComputerMouseMoveConfig.builder().deferLoading(true).enabled(true).build()
                )
                .rightClick(
                    ComputerRightClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .screenshot(
                    ComputerScreenshotConfig.builder().deferLoading(true).enabled(true).build()
                )
                .scroll(ComputerScrollConfig.builder().deferLoading(true).enabled(true).build())
                .tripleClick(
                    ComputerTripleClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .type(ComputerTypeConfig.builder().deferLoading(true).enabled(true).build())
                .wait(ComputerWaitConfig.builder().deferLoading(true).enabled(true).build())
                .zoom(ComputerZoomConfig.builder().deferLoading(true).enabled(true).build())
                .build()

        val roundtrippedComputerToolsetConfigs =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolsetConfigs),
                jacksonTypeRef<ComputerToolsetConfigs>(),
            )

        assertThat(roundtrippedComputerToolsetConfigs).isEqualTo(computerToolsetConfigs)
    }
}
