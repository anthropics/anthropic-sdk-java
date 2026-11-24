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

        assertThat(presentVariants(betaComputerToolUseBlock))
            .isEqualTo(mapOf("key" to Optional.of(key)))
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

        assertThat(presentVariants(betaComputerToolUseBlock))
            .isEqualTo(mapOf("holdKey" to Optional.of(holdKey)))
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

        assertThat(presentVariants(betaComputerToolUseBlock))
            .isEqualTo(mapOf("type" to Optional.of(type)))
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

        assertThat(presentVariants(betaComputerToolUseBlock))
            .isEqualTo(mapOf("cursorPosition" to Optional.of(cursorPosition)))
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

        assertThat(presentVariants(betaComputerToolUseBlock))
            .isEqualTo(mapOf("mouseMove" to Optional.of(mouseMove)))
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

        assertThat(presentVariants(betaComputerToolUseBlock))
            .isEqualTo(mapOf("leftMouseDown" to Optional.of(leftMouseDown)))
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

        assertThat(presentVariants(betaComputerToolUseBlock))
            .isEqualTo(mapOf("leftMouseUp" to Optional.of(leftMouseUp)))
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

        assertThat(presentVariants(betaComputerToolUseBlock))
            .isEqualTo(mapOf("leftClick" to Optional.of(leftClick)))
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

        assertThat(presentVariants(betaComputerToolUseBlock))
            .isEqualTo(mapOf("leftClickDrag" to Optional.of(leftClickDrag)))
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

        assertThat(presentVariants(betaComputerToolUseBlock))
            .isEqualTo(mapOf("rightClick" to Optional.of(rightClick)))
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

        assertThat(presentVariants(betaComputerToolUseBlock))
            .isEqualTo(mapOf("middleClick" to Optional.of(middleClick)))
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

        assertThat(presentVariants(betaComputerToolUseBlock))
            .isEqualTo(mapOf("doubleClick" to Optional.of(doubleClick)))
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

        assertThat(presentVariants(betaComputerToolUseBlock))
            .isEqualTo(mapOf("tripleClick" to Optional.of(tripleClick)))
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

        assertThat(presentVariants(betaComputerToolUseBlock))
            .isEqualTo(mapOf("scroll" to Optional.of(scroll)))
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

        assertThat(presentVariants(betaComputerToolUseBlock))
            .isEqualTo(mapOf("wait" to Optional.of(wait)))
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

        assertThat(presentVariants(betaComputerToolUseBlock))
            .isEqualTo(mapOf("screenshot" to Optional.of(screenshot)))
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

        assertThat(presentVariants(betaComputerToolUseBlock))
            .isEqualTo(mapOf("zoom" to Optional.of(zoom)))
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

    private fun presentVariants(
        betaComputerToolUseBlock: BetaComputerToolUseBlock
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "key" to betaComputerToolUseBlock.key(),
                "holdKey" to betaComputerToolUseBlock.holdKey(),
                "type" to betaComputerToolUseBlock.type(),
                "cursorPosition" to betaComputerToolUseBlock.cursorPosition(),
                "mouseMove" to betaComputerToolUseBlock.mouseMove(),
                "leftMouseDown" to betaComputerToolUseBlock.leftMouseDown(),
                "leftMouseUp" to betaComputerToolUseBlock.leftMouseUp(),
                "leftClick" to betaComputerToolUseBlock.leftClick(),
                "leftClickDrag" to betaComputerToolUseBlock.leftClickDrag(),
                "rightClick" to betaComputerToolUseBlock.rightClick(),
                "middleClick" to betaComputerToolUseBlock.middleClick(),
                "doubleClick" to betaComputerToolUseBlock.doubleClick(),
                "tripleClick" to betaComputerToolUseBlock.tripleClick(),
                "scroll" to betaComputerToolUseBlock.scroll(),
                "wait" to betaComputerToolUseBlock.wait(),
                "screenshot" to betaComputerToolUseBlock.screenshot(),
                "zoom" to betaComputerToolUseBlock.zoom(),
            )
            .filterValues { it.isPresent }
}
