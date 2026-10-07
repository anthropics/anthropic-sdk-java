package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

@JsonDeserialize(using = BetaComputerToolUseBlock.Deserializer::class)
@JsonSerialize(using = BetaComputerToolUseBlock.Serializer::class)
class BetaComputerToolUseBlock
private constructor(
    private val key: BetaComputerKeyToolUseBlock? = null,
    private val holdKey: BetaComputerHoldKeyToolUseBlock? = null,
    private val type: BetaComputerTypeToolUseBlock? = null,
    private val cursorPosition: BetaComputerCursorPositionToolUseBlock? = null,
    private val mouseMove: BetaComputerMouseMoveToolUseBlock? = null,
    private val leftMouseDown: BetaComputerLeftMouseDownToolUseBlock? = null,
    private val leftMouseUp: BetaComputerLeftMouseUpToolUseBlock? = null,
    private val leftClick: BetaComputerLeftClickToolUseBlock? = null,
    private val leftClickDrag: BetaComputerLeftClickDragToolUseBlock? = null,
    private val rightClick: BetaComputerRightClickToolUseBlock? = null,
    private val middleClick: BetaComputerMiddleClickToolUseBlock? = null,
    private val doubleClick: BetaComputerDoubleClickToolUseBlock? = null,
    private val tripleClick: BetaComputerTripleClickToolUseBlock? = null,
    private val scroll: BetaComputerScrollToolUseBlock? = null,
    private val wait: BetaComputerWaitToolUseBlock? = null,
    private val screenshot: BetaComputerScreenshotToolUseBlock? = null,
    private val zoom: BetaComputerZoomToolUseBlock? = null,
    private val _json: JsonValue? = null,
) {

    fun name(): Name =
        when {
            key != null -> Name.KEY
            holdKey != null -> Name.HOLD_KEY
            type != null -> Name.TYPE
            cursorPosition != null -> Name.CURSOR_POSITION
            mouseMove != null -> Name.MOUSE_MOVE
            leftMouseDown != null -> Name.LEFT_MOUSE_DOWN
            leftMouseUp != null -> Name.LEFT_MOUSE_UP
            leftClick != null -> Name.LEFT_CLICK
            leftClickDrag != null -> Name.LEFT_CLICK_DRAG
            rightClick != null -> Name.RIGHT_CLICK
            middleClick != null -> Name.MIDDLE_CLICK
            doubleClick != null -> Name.DOUBLE_CLICK
            tripleClick != null -> Name.TRIPLE_CLICK
            scroll != null -> Name.SCROLL
            wait != null -> Name.WAIT
            screenshot != null -> Name.SCREENSHOT
            zoom != null -> Name.ZOOM
            else -> Name.of(_json?.asObject()?.getOrNull()?.get("name") ?: JsonMissing.of())
        }

    fun id(): String =
        when {
            key != null -> key.id()
            holdKey != null -> holdKey.id()
            type != null -> type.id()
            cursorPosition != null -> cursorPosition.id()
            mouseMove != null -> mouseMove.id()
            leftMouseDown != null -> leftMouseDown.id()
            leftMouseUp != null -> leftMouseUp.id()
            leftClick != null -> leftClick.id()
            leftClickDrag != null -> leftClickDrag.id()
            rightClick != null -> rightClick.id()
            middleClick != null -> middleClick.id()
            doubleClick != null -> doubleClick.id()
            tripleClick != null -> tripleClick.id()
            scroll != null -> scroll.id()
            wait != null -> wait.id()
            screenshot != null -> screenshot.id()
            zoom != null -> zoom.id()
            else -> _json.getProperty<String>("id").getRequired("id")
        }

    fun caller(): Optional<BetaToolUseCaller> =
        when {
            key != null -> key.caller()
            holdKey != null -> holdKey.caller()
            type != null -> type.caller()
            cursorPosition != null -> cursorPosition.caller()
            mouseMove != null -> mouseMove.caller()
            leftMouseDown != null -> leftMouseDown.caller()
            leftMouseUp != null -> leftMouseUp.caller()
            leftClick != null -> leftClick.caller()
            leftClickDrag != null -> leftClickDrag.caller()
            rightClick != null -> rightClick.caller()
            middleClick != null -> middleClick.caller()
            doubleClick != null -> doubleClick.caller()
            tripleClick != null -> tripleClick.caller()
            scroll != null -> scroll.caller()
            wait != null -> wait.caller()
            screenshot != null -> screenshot.caller()
            zoom != null -> zoom.caller()
            else -> _json.getProperty<BetaToolUseCaller>("caller").asKnown()
        }

    fun key(): Optional<BetaComputerKeyToolUseBlock> = Optional.ofNullable(key)

    fun holdKey(): Optional<BetaComputerHoldKeyToolUseBlock> = Optional.ofNullable(holdKey)

    fun type(): Optional<BetaComputerTypeToolUseBlock> = Optional.ofNullable(type)

    fun cursorPosition(): Optional<BetaComputerCursorPositionToolUseBlock> =
        Optional.ofNullable(cursorPosition)

    fun mouseMove(): Optional<BetaComputerMouseMoveToolUseBlock> = Optional.ofNullable(mouseMove)

    fun leftMouseDown(): Optional<BetaComputerLeftMouseDownToolUseBlock> =
        Optional.ofNullable(leftMouseDown)

    fun leftMouseUp(): Optional<BetaComputerLeftMouseUpToolUseBlock> =
        Optional.ofNullable(leftMouseUp)

    fun leftClick(): Optional<BetaComputerLeftClickToolUseBlock> = Optional.ofNullable(leftClick)

    fun leftClickDrag(): Optional<BetaComputerLeftClickDragToolUseBlock> =
        Optional.ofNullable(leftClickDrag)

    fun rightClick(): Optional<BetaComputerRightClickToolUseBlock> = Optional.ofNullable(rightClick)

    fun middleClick(): Optional<BetaComputerMiddleClickToolUseBlock> =
        Optional.ofNullable(middleClick)

    fun doubleClick(): Optional<BetaComputerDoubleClickToolUseBlock> =
        Optional.ofNullable(doubleClick)

    fun tripleClick(): Optional<BetaComputerTripleClickToolUseBlock> =
        Optional.ofNullable(tripleClick)

    fun scroll(): Optional<BetaComputerScrollToolUseBlock> = Optional.ofNullable(scroll)

    fun wait(): Optional<BetaComputerWaitToolUseBlock> = Optional.ofNullable(wait)

    fun screenshot(): Optional<BetaComputerScreenshotToolUseBlock> = Optional.ofNullable(screenshot)

    fun zoom(): Optional<BetaComputerZoomToolUseBlock> = Optional.ofNullable(zoom)

    fun isKey(): Boolean = key != null

    fun isHoldKey(): Boolean = holdKey != null

    fun isType(): Boolean = type != null

    fun isCursorPosition(): Boolean = cursorPosition != null

    fun isMouseMove(): Boolean = mouseMove != null

    fun isLeftMouseDown(): Boolean = leftMouseDown != null

    fun isLeftMouseUp(): Boolean = leftMouseUp != null

    fun isLeftClick(): Boolean = leftClick != null

    fun isLeftClickDrag(): Boolean = leftClickDrag != null

    fun isRightClick(): Boolean = rightClick != null

    fun isMiddleClick(): Boolean = middleClick != null

    fun isDoubleClick(): Boolean = doubleClick != null

    fun isTripleClick(): Boolean = tripleClick != null

    fun isScroll(): Boolean = scroll != null

    fun isWait(): Boolean = wait != null

    fun isScreenshot(): Boolean = screenshot != null

    fun isZoom(): Boolean = zoom != null

    fun asKey(): BetaComputerKeyToolUseBlock = key.getOrThrow("key")

    fun asHoldKey(): BetaComputerHoldKeyToolUseBlock = holdKey.getOrThrow("holdKey")

    fun asType(): BetaComputerTypeToolUseBlock = type.getOrThrow("type")

    fun asCursorPosition(): BetaComputerCursorPositionToolUseBlock =
        cursorPosition.getOrThrow("cursorPosition")

    fun asMouseMove(): BetaComputerMouseMoveToolUseBlock = mouseMove.getOrThrow("mouseMove")

    fun asLeftMouseDown(): BetaComputerLeftMouseDownToolUseBlock =
        leftMouseDown.getOrThrow("leftMouseDown")

    fun asLeftMouseUp(): BetaComputerLeftMouseUpToolUseBlock = leftMouseUp.getOrThrow("leftMouseUp")

    fun asLeftClick(): BetaComputerLeftClickToolUseBlock = leftClick.getOrThrow("leftClick")

    fun asLeftClickDrag(): BetaComputerLeftClickDragToolUseBlock =
        leftClickDrag.getOrThrow("leftClickDrag")

    fun asRightClick(): BetaComputerRightClickToolUseBlock = rightClick.getOrThrow("rightClick")

    fun asMiddleClick(): BetaComputerMiddleClickToolUseBlock = middleClick.getOrThrow("middleClick")

    fun asDoubleClick(): BetaComputerDoubleClickToolUseBlock = doubleClick.getOrThrow("doubleClick")

    fun asTripleClick(): BetaComputerTripleClickToolUseBlock = tripleClick.getOrThrow("tripleClick")

    fun asScroll(): BetaComputerScrollToolUseBlock = scroll.getOrThrow("scroll")

    fun asWait(): BetaComputerWaitToolUseBlock = wait.getOrThrow("wait")

    fun asScreenshot(): BetaComputerScreenshotToolUseBlock = screenshot.getOrThrow("screenshot")

    fun asZoom(): BetaComputerZoomToolUseBlock = zoom.getOrThrow("zoom")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    /**
     * Maps this instance's current variant to a value of type [T] using the given [visitor].
     *
     * Note that this method is _not_ forwards compatible with new variants from the API, unless
     * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of the
     * SDK gracefully, consider overriding [Visitor.unknown]:
     * ```java
     * import com.anthropic.core.JsonValue;
     * import java.util.Optional;
     *
     * Optional<String> result = betaComputerToolUseBlock.accept(new BetaComputerToolUseBlock.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitKey(BetaComputerKeyToolUseBlock key) {
     *         return Optional.of(key.toString());
     *     }
     *
     *     // ...
     *
     *     @Override
     *     public Optional<String> unknown(JsonValue json) {
     *         // Or inspect the `json`.
     *         return Optional.empty();
     *     }
     * });
     * ```
     *
     * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor] and
     *   the current variant is unknown.
     */
    fun <T> accept(visitor: Visitor<T>): T =
        when {
            key != null -> visitor.visitKey(key)
            holdKey != null -> visitor.visitHoldKey(holdKey)
            type != null -> visitor.visitType(type)
            cursorPosition != null -> visitor.visitCursorPosition(cursorPosition)
            mouseMove != null -> visitor.visitMouseMove(mouseMove)
            leftMouseDown != null -> visitor.visitLeftMouseDown(leftMouseDown)
            leftMouseUp != null -> visitor.visitLeftMouseUp(leftMouseUp)
            leftClick != null -> visitor.visitLeftClick(leftClick)
            leftClickDrag != null -> visitor.visitLeftClickDrag(leftClickDrag)
            rightClick != null -> visitor.visitRightClick(rightClick)
            middleClick != null -> visitor.visitMiddleClick(middleClick)
            doubleClick != null -> visitor.visitDoubleClick(doubleClick)
            tripleClick != null -> visitor.visitTripleClick(tripleClick)
            scroll != null -> visitor.visitScroll(scroll)
            wait != null -> visitor.visitWait(wait)
            screenshot != null -> visitor.visitScreenshot(screenshot)
            zoom != null -> visitor.visitZoom(zoom)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaComputerToolUseBlock = apply {
        if (validated) {
            return@apply
        }

        when {
            key != null -> key.validate()
            holdKey != null -> holdKey.validate()
            type != null -> type.validate()
            cursorPosition != null -> cursorPosition.validate()
            mouseMove != null -> mouseMove.validate()
            leftMouseDown != null -> leftMouseDown.validate()
            leftMouseUp != null -> leftMouseUp.validate()
            leftClick != null -> leftClick.validate()
            leftClickDrag != null -> leftClickDrag.validate()
            rightClick != null -> rightClick.validate()
            middleClick != null -> middleClick.validate()
            doubleClick != null -> doubleClick.validate()
            tripleClick != null -> tripleClick.validate()
            scroll != null -> scroll.validate()
            wait != null -> wait.validate()
            screenshot != null -> screenshot.validate()
            zoom != null -> zoom.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaComputerToolUseBlock: $_json")
        }
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        when {
            key != null -> key.validity()
            holdKey != null -> holdKey.validity()
            type != null -> type.validity()
            cursorPosition != null -> cursorPosition.validity()
            mouseMove != null -> mouseMove.validity()
            leftMouseDown != null -> leftMouseDown.validity()
            leftMouseUp != null -> leftMouseUp.validity()
            leftClick != null -> leftClick.validity()
            leftClickDrag != null -> leftClickDrag.validity()
            rightClick != null -> rightClick.validity()
            middleClick != null -> middleClick.validity()
            doubleClick != null -> doubleClick.validity()
            tripleClick != null -> tripleClick.validity()
            scroll != null -> scroll.validity()
            wait != null -> wait.validity()
            screenshot != null -> screenshot.validity()
            zoom != null -> zoom.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComputerToolUseBlock &&
            key == other.key &&
            holdKey == other.holdKey &&
            type == other.type &&
            cursorPosition == other.cursorPosition &&
            mouseMove == other.mouseMove &&
            leftMouseDown == other.leftMouseDown &&
            leftMouseUp == other.leftMouseUp &&
            leftClick == other.leftClick &&
            leftClickDrag == other.leftClickDrag &&
            rightClick == other.rightClick &&
            middleClick == other.middleClick &&
            doubleClick == other.doubleClick &&
            tripleClick == other.tripleClick &&
            scroll == other.scroll &&
            wait == other.wait &&
            screenshot == other.screenshot &&
            zoom == other.zoom
    }

    override fun hashCode(): Int =
        Objects.hash(
            key,
            holdKey,
            type,
            cursorPosition,
            mouseMove,
            leftMouseDown,
            leftMouseUp,
            leftClick,
            leftClickDrag,
            rightClick,
            middleClick,
            doubleClick,
            tripleClick,
            scroll,
            wait,
            screenshot,
            zoom,
        )

    override fun toString(): String =
        when {
            key != null -> "BetaComputerToolUseBlock{key=$key}"
            holdKey != null -> "BetaComputerToolUseBlock{holdKey=$holdKey}"
            type != null -> "BetaComputerToolUseBlock{type=$type}"
            cursorPosition != null -> "BetaComputerToolUseBlock{cursorPosition=$cursorPosition}"
            mouseMove != null -> "BetaComputerToolUseBlock{mouseMove=$mouseMove}"
            leftMouseDown != null -> "BetaComputerToolUseBlock{leftMouseDown=$leftMouseDown}"
            leftMouseUp != null -> "BetaComputerToolUseBlock{leftMouseUp=$leftMouseUp}"
            leftClick != null -> "BetaComputerToolUseBlock{leftClick=$leftClick}"
            leftClickDrag != null -> "BetaComputerToolUseBlock{leftClickDrag=$leftClickDrag}"
            rightClick != null -> "BetaComputerToolUseBlock{rightClick=$rightClick}"
            middleClick != null -> "BetaComputerToolUseBlock{middleClick=$middleClick}"
            doubleClick != null -> "BetaComputerToolUseBlock{doubleClick=$doubleClick}"
            tripleClick != null -> "BetaComputerToolUseBlock{tripleClick=$tripleClick}"
            scroll != null -> "BetaComputerToolUseBlock{scroll=$scroll}"
            wait != null -> "BetaComputerToolUseBlock{wait=$wait}"
            screenshot != null -> "BetaComputerToolUseBlock{screenshot=$screenshot}"
            zoom != null -> "BetaComputerToolUseBlock{zoom=$zoom}"
            _json != null -> "BetaComputerToolUseBlock{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaComputerToolUseBlock")
        }

    companion object {

        @JvmStatic fun ofKey(key: BetaComputerKeyToolUseBlock) = BetaComputerToolUseBlock(key = key)

        @JvmStatic
        fun ofHoldKey(holdKey: BetaComputerHoldKeyToolUseBlock) =
            BetaComputerToolUseBlock(holdKey = holdKey)

        @JvmStatic
        fun ofType(type: BetaComputerTypeToolUseBlock) = BetaComputerToolUseBlock(type = type)

        @JvmStatic
        fun ofCursorPosition(cursorPosition: BetaComputerCursorPositionToolUseBlock) =
            BetaComputerToolUseBlock(cursorPosition = cursorPosition)

        @JvmStatic
        fun ofMouseMove(mouseMove: BetaComputerMouseMoveToolUseBlock) =
            BetaComputerToolUseBlock(mouseMove = mouseMove)

        @JvmStatic
        fun ofLeftMouseDown(leftMouseDown: BetaComputerLeftMouseDownToolUseBlock) =
            BetaComputerToolUseBlock(leftMouseDown = leftMouseDown)

        @JvmStatic
        fun ofLeftMouseUp(leftMouseUp: BetaComputerLeftMouseUpToolUseBlock) =
            BetaComputerToolUseBlock(leftMouseUp = leftMouseUp)

        @JvmStatic
        fun ofLeftClick(leftClick: BetaComputerLeftClickToolUseBlock) =
            BetaComputerToolUseBlock(leftClick = leftClick)

        @JvmStatic
        fun ofLeftClickDrag(leftClickDrag: BetaComputerLeftClickDragToolUseBlock) =
            BetaComputerToolUseBlock(leftClickDrag = leftClickDrag)

        @JvmStatic
        fun ofRightClick(rightClick: BetaComputerRightClickToolUseBlock) =
            BetaComputerToolUseBlock(rightClick = rightClick)

        @JvmStatic
        fun ofMiddleClick(middleClick: BetaComputerMiddleClickToolUseBlock) =
            BetaComputerToolUseBlock(middleClick = middleClick)

        @JvmStatic
        fun ofDoubleClick(doubleClick: BetaComputerDoubleClickToolUseBlock) =
            BetaComputerToolUseBlock(doubleClick = doubleClick)

        @JvmStatic
        fun ofTripleClick(tripleClick: BetaComputerTripleClickToolUseBlock) =
            BetaComputerToolUseBlock(tripleClick = tripleClick)

        @JvmStatic
        fun ofScroll(scroll: BetaComputerScrollToolUseBlock) =
            BetaComputerToolUseBlock(scroll = scroll)

        @JvmStatic
        fun ofWait(wait: BetaComputerWaitToolUseBlock) = BetaComputerToolUseBlock(wait = wait)

        @JvmStatic
        fun ofScreenshot(screenshot: BetaComputerScreenshotToolUseBlock) =
            BetaComputerToolUseBlock(screenshot = screenshot)

        @JvmStatic
        fun ofZoom(zoom: BetaComputerZoomToolUseBlock) = BetaComputerToolUseBlock(zoom = zoom)
    }

    /**
     * An interface that defines how to map each variant of [BetaComputerToolUseBlock] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitKey(key: BetaComputerKeyToolUseBlock): T

        fun visitHoldKey(holdKey: BetaComputerHoldKeyToolUseBlock): T

        fun visitType(type: BetaComputerTypeToolUseBlock): T

        fun visitCursorPosition(cursorPosition: BetaComputerCursorPositionToolUseBlock): T

        fun visitMouseMove(mouseMove: BetaComputerMouseMoveToolUseBlock): T

        fun visitLeftMouseDown(leftMouseDown: BetaComputerLeftMouseDownToolUseBlock): T

        fun visitLeftMouseUp(leftMouseUp: BetaComputerLeftMouseUpToolUseBlock): T

        fun visitLeftClick(leftClick: BetaComputerLeftClickToolUseBlock): T

        fun visitLeftClickDrag(leftClickDrag: BetaComputerLeftClickDragToolUseBlock): T

        fun visitRightClick(rightClick: BetaComputerRightClickToolUseBlock): T

        fun visitMiddleClick(middleClick: BetaComputerMiddleClickToolUseBlock): T

        fun visitDoubleClick(doubleClick: BetaComputerDoubleClickToolUseBlock): T

        fun visitTripleClick(tripleClick: BetaComputerTripleClickToolUseBlock): T

        fun visitScroll(scroll: BetaComputerScrollToolUseBlock): T

        fun visitWait(wait: BetaComputerWaitToolUseBlock): T

        fun visitScreenshot(screenshot: BetaComputerScreenshotToolUseBlock): T

        fun visitZoom(zoom: BetaComputerZoomToolUseBlock): T

        /**
         * Maps an unknown variant of [BetaComputerToolUseBlock] to a value of type [T].
         *
         * An instance of [BetaComputerToolUseBlock] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaComputerToolUseBlock: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaComputerToolUseBlock>(BetaComputerToolUseBlock::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaComputerToolUseBlock {
            val json = JsonValue.fromJsonNode(node)
            val name = json.asObject().getOrNull()?.get("name")?.asString()?.getOrNull()

            when (name) {
                "key" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaComputerKeyToolUseBlock>())
                        ?.let { BetaComputerToolUseBlock(key = it, _json = json) }
                        ?: BetaComputerToolUseBlock(_json = json)
                }
                "hold_key" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaComputerHoldKeyToolUseBlock>())
                        ?.let { BetaComputerToolUseBlock(holdKey = it, _json = json) }
                        ?: BetaComputerToolUseBlock(_json = json)
                }
                "type" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaComputerTypeToolUseBlock>())
                        ?.let { BetaComputerToolUseBlock(type = it, _json = json) }
                        ?: BetaComputerToolUseBlock(_json = json)
                }
                "cursor_position" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaComputerCursorPositionToolUseBlock>(),
                        )
                        ?.let { BetaComputerToolUseBlock(cursorPosition = it, _json = json) }
                        ?: BetaComputerToolUseBlock(_json = json)
                }
                "mouse_move" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaComputerMouseMoveToolUseBlock>())
                        ?.let { BetaComputerToolUseBlock(mouseMove = it, _json = json) }
                        ?: BetaComputerToolUseBlock(_json = json)
                }
                "left_mouse_down" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaComputerLeftMouseDownToolUseBlock>(),
                        )
                        ?.let { BetaComputerToolUseBlock(leftMouseDown = it, _json = json) }
                        ?: BetaComputerToolUseBlock(_json = json)
                }
                "left_mouse_up" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaComputerLeftMouseUpToolUseBlock>(),
                        )
                        ?.let { BetaComputerToolUseBlock(leftMouseUp = it, _json = json) }
                        ?: BetaComputerToolUseBlock(_json = json)
                }
                "left_click" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaComputerLeftClickToolUseBlock>())
                        ?.let { BetaComputerToolUseBlock(leftClick = it, _json = json) }
                        ?: BetaComputerToolUseBlock(_json = json)
                }
                "left_click_drag" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaComputerLeftClickDragToolUseBlock>(),
                        )
                        ?.let { BetaComputerToolUseBlock(leftClickDrag = it, _json = json) }
                        ?: BetaComputerToolUseBlock(_json = json)
                }
                "right_click" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaComputerRightClickToolUseBlock>(),
                        )
                        ?.let { BetaComputerToolUseBlock(rightClick = it, _json = json) }
                        ?: BetaComputerToolUseBlock(_json = json)
                }
                "middle_click" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaComputerMiddleClickToolUseBlock>(),
                        )
                        ?.let { BetaComputerToolUseBlock(middleClick = it, _json = json) }
                        ?: BetaComputerToolUseBlock(_json = json)
                }
                "double_click" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaComputerDoubleClickToolUseBlock>(),
                        )
                        ?.let { BetaComputerToolUseBlock(doubleClick = it, _json = json) }
                        ?: BetaComputerToolUseBlock(_json = json)
                }
                "triple_click" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaComputerTripleClickToolUseBlock>(),
                        )
                        ?.let { BetaComputerToolUseBlock(tripleClick = it, _json = json) }
                        ?: BetaComputerToolUseBlock(_json = json)
                }
                "scroll" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaComputerScrollToolUseBlock>())
                        ?.let { BetaComputerToolUseBlock(scroll = it, _json = json) }
                        ?: BetaComputerToolUseBlock(_json = json)
                }
                "wait" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaComputerWaitToolUseBlock>())
                        ?.let { BetaComputerToolUseBlock(wait = it, _json = json) }
                        ?: BetaComputerToolUseBlock(_json = json)
                }
                "screenshot" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaComputerScreenshotToolUseBlock>(),
                        )
                        ?.let { BetaComputerToolUseBlock(screenshot = it, _json = json) }
                        ?: BetaComputerToolUseBlock(_json = json)
                }
                "zoom" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaComputerZoomToolUseBlock>())
                        ?.let { BetaComputerToolUseBlock(zoom = it, _json = json) }
                        ?: BetaComputerToolUseBlock(_json = json)
                }
            }

            return BetaComputerToolUseBlock(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaComputerToolUseBlock>(BetaComputerToolUseBlock::class) {

        override fun serialize(
            value: BetaComputerToolUseBlock,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.key != null -> generator.writeObject(value.key)
                value.holdKey != null -> generator.writeObject(value.holdKey)
                value.type != null -> generator.writeObject(value.type)
                value.cursorPosition != null -> generator.writeObject(value.cursorPosition)
                value.mouseMove != null -> generator.writeObject(value.mouseMove)
                value.leftMouseDown != null -> generator.writeObject(value.leftMouseDown)
                value.leftMouseUp != null -> generator.writeObject(value.leftMouseUp)
                value.leftClick != null -> generator.writeObject(value.leftClick)
                value.leftClickDrag != null -> generator.writeObject(value.leftClickDrag)
                value.rightClick != null -> generator.writeObject(value.rightClick)
                value.middleClick != null -> generator.writeObject(value.middleClick)
                value.doubleClick != null -> generator.writeObject(value.doubleClick)
                value.tripleClick != null -> generator.writeObject(value.tripleClick)
                value.scroll != null -> generator.writeObject(value.scroll)
                value.wait != null -> generator.writeObject(value.wait)
                value.screenshot != null -> generator.writeObject(value.screenshot)
                value.zoom != null -> generator.writeObject(value.zoom)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaComputerToolUseBlock")
            }
        }
    }

    class Name private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val KEY = Name(JsonField.of("key"))

            @JvmField val HOLD_KEY = Name(JsonField.of("hold_key"))

            @JvmField val TYPE = Name(JsonField.of("type"))

            @JvmField val CURSOR_POSITION = Name(JsonField.of("cursor_position"))

            @JvmField val MOUSE_MOVE = Name(JsonField.of("mouse_move"))

            @JvmField val LEFT_MOUSE_DOWN = Name(JsonField.of("left_mouse_down"))

            @JvmField val LEFT_MOUSE_UP = Name(JsonField.of("left_mouse_up"))

            @JvmField val LEFT_CLICK = Name(JsonField.of("left_click"))

            @JvmField val LEFT_CLICK_DRAG = Name(JsonField.of("left_click_drag"))

            @JvmField val RIGHT_CLICK = Name(JsonField.of("right_click"))

            @JvmField val MIDDLE_CLICK = Name(JsonField.of("middle_click"))

            @JvmField val DOUBLE_CLICK = Name(JsonField.of("double_click"))

            @JvmField val TRIPLE_CLICK = Name(JsonField.of("triple_click"))

            @JvmField val SCROLL = Name(JsonField.of("scroll"))

            @JvmField val WAIT = Name(JsonField.of("wait"))

            @JvmField val SCREENSHOT = Name(JsonField.of("screenshot"))

            @JvmField val ZOOM = Name(JsonField.of("zoom"))

            @JvmStatic
            fun of(value: String): Name =
                // Intern known values so `==` works
                when (value) {
                    "key" -> KEY
                    "hold_key" -> HOLD_KEY
                    "type" -> TYPE
                    "cursor_position" -> CURSOR_POSITION
                    "mouse_move" -> MOUSE_MOVE
                    "left_mouse_down" -> LEFT_MOUSE_DOWN
                    "left_mouse_up" -> LEFT_MOUSE_UP
                    "left_click" -> LEFT_CLICK
                    "left_click_drag" -> LEFT_CLICK_DRAG
                    "right_click" -> RIGHT_CLICK
                    "middle_click" -> MIDDLE_CLICK
                    "double_click" -> DOUBLE_CLICK
                    "triple_click" -> TRIPLE_CLICK
                    "scroll" -> SCROLL
                    "wait" -> WAIT
                    "screenshot" -> SCREENSHOT
                    "zoom" -> ZOOM
                    else -> Name(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Name =
                value.asString().getOrNull()?.let { of(it) } ?: Name(value)
        }

        /** An enum containing [Name]'s known values. */
        enum class Known {
            KEY,
            HOLD_KEY,
            TYPE,
            CURSOR_POSITION,
            MOUSE_MOVE,
            LEFT_MOUSE_DOWN,
            LEFT_MOUSE_UP,
            LEFT_CLICK,
            LEFT_CLICK_DRAG,
            RIGHT_CLICK,
            MIDDLE_CLICK,
            DOUBLE_CLICK,
            TRIPLE_CLICK,
            SCROLL,
            WAIT,
            SCREENSHOT,
            ZOOM,
        }

        /**
         * An enum containing [Name]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Name] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            KEY,
            HOLD_KEY,
            TYPE,
            CURSOR_POSITION,
            MOUSE_MOVE,
            LEFT_MOUSE_DOWN,
            LEFT_MOUSE_UP,
            LEFT_CLICK,
            LEFT_CLICK_DRAG,
            RIGHT_CLICK,
            MIDDLE_CLICK,
            DOUBLE_CLICK,
            TRIPLE_CLICK,
            SCROLL,
            WAIT,
            SCREENSHOT,
            ZOOM,
            /** An enum member indicating that [Name] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                KEY -> Value.KEY
                HOLD_KEY -> Value.HOLD_KEY
                TYPE -> Value.TYPE
                CURSOR_POSITION -> Value.CURSOR_POSITION
                MOUSE_MOVE -> Value.MOUSE_MOVE
                LEFT_MOUSE_DOWN -> Value.LEFT_MOUSE_DOWN
                LEFT_MOUSE_UP -> Value.LEFT_MOUSE_UP
                LEFT_CLICK -> Value.LEFT_CLICK
                LEFT_CLICK_DRAG -> Value.LEFT_CLICK_DRAG
                RIGHT_CLICK -> Value.RIGHT_CLICK
                MIDDLE_CLICK -> Value.MIDDLE_CLICK
                DOUBLE_CLICK -> Value.DOUBLE_CLICK
                TRIPLE_CLICK -> Value.TRIPLE_CLICK
                SCROLL -> Value.SCROLL
                WAIT -> Value.WAIT
                SCREENSHOT -> Value.SCREENSHOT
                ZOOM -> Value.ZOOM
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                KEY -> Known.KEY
                HOLD_KEY -> Known.HOLD_KEY
                TYPE -> Known.TYPE
                CURSOR_POSITION -> Known.CURSOR_POSITION
                MOUSE_MOVE -> Known.MOUSE_MOVE
                LEFT_MOUSE_DOWN -> Known.LEFT_MOUSE_DOWN
                LEFT_MOUSE_UP -> Known.LEFT_MOUSE_UP
                LEFT_CLICK -> Known.LEFT_CLICK
                LEFT_CLICK_DRAG -> Known.LEFT_CLICK_DRAG
                RIGHT_CLICK -> Known.RIGHT_CLICK
                MIDDLE_CLICK -> Known.MIDDLE_CLICK
                DOUBLE_CLICK -> Known.DOUBLE_CLICK
                TRIPLE_CLICK -> Known.TRIPLE_CLICK
                SCROLL -> Known.SCROLL
                WAIT -> Known.WAIT
                SCREENSHOT -> Known.SCREENSHOT
                ZOOM -> Known.ZOOM
                else -> throw AnthropicInvalidDataException("Unknown Name: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Name = apply {
            if (validated) {
                return@apply
            }

            known()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: AnthropicInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Name && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }
}
