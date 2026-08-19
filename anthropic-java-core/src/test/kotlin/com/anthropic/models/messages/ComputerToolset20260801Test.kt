// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerToolset20260801Test {

    @Test
    fun create() {
        val computerToolset20260801 =
            ComputerToolset20260801.builder()
                .addAllowedCaller(ComputerToolset20260801.AllowedCaller.DIRECT)
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

        assertThat(computerToolset20260801.allowedCallers().getOrNull())
            .containsExactly(ComputerToolset20260801.AllowedCaller.DIRECT)
        assertThat(computerToolset20260801.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(computerToolset20260801.configs())
            .contains(
                ComputerToolsetConfigs.builder()
                    .cursorPosition(
                        ComputerCursorPositionConfig.builder()
                            .deferLoading(true)
                            .enabled(true)
                            .build()
                    )
                    .doubleClick(
                        ComputerDoubleClickConfig.builder().deferLoading(true).enabled(true).build()
                    )
                    .holdKey(
                        ComputerHoldKeyConfig.builder().deferLoading(true).enabled(true).build()
                    )
                    .key(ComputerKeyConfig.builder().deferLoading(true).enabled(true).build())
                    .leftClick(
                        ComputerLeftClickConfig.builder().deferLoading(true).enabled(true).build()
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
            )
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseComputerToolset20260801 = ComputerToolset20260801.builder().build()

        val computerToolset20260801 =
            baseComputerToolset20260801
                .toBuilder()
                .addAllowedCaller(ComputerToolset20260801.AllowedCaller.DIRECT)
                .build()

        assertThat(computerToolset20260801.allowedCallers().getOrNull())
            .containsExactly(ComputerToolset20260801.AllowedCaller.DIRECT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerToolset20260801 =
            ComputerToolset20260801.builder()
                .addAllowedCaller(ComputerToolset20260801.AllowedCaller.DIRECT)
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

        val roundtrippedComputerToolset20260801 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerToolset20260801),
                jacksonTypeRef<ComputerToolset20260801>(),
            )

        assertThat(roundtrippedComputerToolset20260801).isEqualTo(computerToolset20260801)
    }
}
