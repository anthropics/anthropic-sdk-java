// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerToolsetConfigsTest {

    @Test
    fun create() {
        val betaComputerToolsetConfigs =
            BetaComputerToolsetConfigs.builder()
                .cursorPosition(
                    BetaComputerCursorPositionConfig.builder()
                        .deferLoading(true)
                        .enabled(true)
                        .build()
                )
                .doubleClick(
                    BetaComputerDoubleClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .holdKey(
                    BetaComputerHoldKeyConfig.builder().deferLoading(true).enabled(true).build()
                )
                .key(BetaComputerKeyConfig.builder().deferLoading(true).enabled(true).build())
                .leftClick(
                    BetaComputerLeftClickConfig.builder().deferLoading(true).enabled(true).build()
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
                    BetaComputerLeftMouseUpConfig.builder().deferLoading(true).enabled(true).build()
                )
                .middleClick(
                    BetaComputerMiddleClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .mouseMove(
                    BetaComputerMouseMoveConfig.builder().deferLoading(true).enabled(true).build()
                )
                .rightClick(
                    BetaComputerRightClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .screenshot(
                    BetaComputerScreenshotConfig.builder().deferLoading(true).enabled(true).build()
                )
                .scroll(BetaComputerScrollConfig.builder().deferLoading(true).enabled(true).build())
                .tripleClick(
                    BetaComputerTripleClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .type(BetaComputerTypeConfig.builder().deferLoading(true).enabled(true).build())
                .wait(BetaComputerWaitConfig.builder().deferLoading(true).enabled(true).build())
                .zoom(BetaComputerZoomConfig.builder().deferLoading(true).enabled(true).build())
                .build()

        assertThat(betaComputerToolsetConfigs.cursorPosition())
            .contains(
                BetaComputerCursorPositionConfig.builder().deferLoading(true).enabled(true).build()
            )
        assertThat(betaComputerToolsetConfigs.doubleClick())
            .contains(
                BetaComputerDoubleClickConfig.builder().deferLoading(true).enabled(true).build()
            )
        assertThat(betaComputerToolsetConfigs.holdKey())
            .contains(BetaComputerHoldKeyConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(betaComputerToolsetConfigs.key())
            .contains(BetaComputerKeyConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(betaComputerToolsetConfigs.leftClick())
            .contains(
                BetaComputerLeftClickConfig.builder().deferLoading(true).enabled(true).build()
            )
        assertThat(betaComputerToolsetConfigs.leftClickDrag())
            .contains(
                BetaComputerLeftClickDragConfig.builder().deferLoading(true).enabled(true).build()
            )
        assertThat(betaComputerToolsetConfigs.leftMouseDown())
            .contains(
                BetaComputerLeftMouseDownConfig.builder().deferLoading(true).enabled(true).build()
            )
        assertThat(betaComputerToolsetConfigs.leftMouseUp())
            .contains(
                BetaComputerLeftMouseUpConfig.builder().deferLoading(true).enabled(true).build()
            )
        assertThat(betaComputerToolsetConfigs.middleClick())
            .contains(
                BetaComputerMiddleClickConfig.builder().deferLoading(true).enabled(true).build()
            )
        assertThat(betaComputerToolsetConfigs.mouseMove())
            .contains(
                BetaComputerMouseMoveConfig.builder().deferLoading(true).enabled(true).build()
            )
        assertThat(betaComputerToolsetConfigs.rightClick())
            .contains(
                BetaComputerRightClickConfig.builder().deferLoading(true).enabled(true).build()
            )
        assertThat(betaComputerToolsetConfigs.screenshot())
            .contains(
                BetaComputerScreenshotConfig.builder().deferLoading(true).enabled(true).build()
            )
        assertThat(betaComputerToolsetConfigs.scroll())
            .contains(BetaComputerScrollConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(betaComputerToolsetConfigs.tripleClick())
            .contains(
                BetaComputerTripleClickConfig.builder().deferLoading(true).enabled(true).build()
            )
        assertThat(betaComputerToolsetConfigs.type())
            .contains(BetaComputerTypeConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(betaComputerToolsetConfigs.wait())
            .contains(BetaComputerWaitConfig.builder().deferLoading(true).enabled(true).build())
        assertThat(betaComputerToolsetConfigs.zoom())
            .contains(BetaComputerZoomConfig.builder().deferLoading(true).enabled(true).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerToolsetConfigs =
            BetaComputerToolsetConfigs.builder()
                .cursorPosition(
                    BetaComputerCursorPositionConfig.builder()
                        .deferLoading(true)
                        .enabled(true)
                        .build()
                )
                .doubleClick(
                    BetaComputerDoubleClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .holdKey(
                    BetaComputerHoldKeyConfig.builder().deferLoading(true).enabled(true).build()
                )
                .key(BetaComputerKeyConfig.builder().deferLoading(true).enabled(true).build())
                .leftClick(
                    BetaComputerLeftClickConfig.builder().deferLoading(true).enabled(true).build()
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
                    BetaComputerLeftMouseUpConfig.builder().deferLoading(true).enabled(true).build()
                )
                .middleClick(
                    BetaComputerMiddleClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .mouseMove(
                    BetaComputerMouseMoveConfig.builder().deferLoading(true).enabled(true).build()
                )
                .rightClick(
                    BetaComputerRightClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .screenshot(
                    BetaComputerScreenshotConfig.builder().deferLoading(true).enabled(true).build()
                )
                .scroll(BetaComputerScrollConfig.builder().deferLoading(true).enabled(true).build())
                .tripleClick(
                    BetaComputerTripleClickConfig.builder().deferLoading(true).enabled(true).build()
                )
                .type(BetaComputerTypeConfig.builder().deferLoading(true).enabled(true).build())
                .wait(BetaComputerWaitConfig.builder().deferLoading(true).enabled(true).build())
                .zoom(BetaComputerZoomConfig.builder().deferLoading(true).enabled(true).build())
                .build()

        val roundtrippedBetaComputerToolsetConfigs =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerToolsetConfigs),
                jacksonTypeRef<BetaComputerToolsetConfigs>(),
            )

        assertThat(roundtrippedBetaComputerToolsetConfigs).isEqualTo(betaComputerToolsetConfigs)
    }
}
