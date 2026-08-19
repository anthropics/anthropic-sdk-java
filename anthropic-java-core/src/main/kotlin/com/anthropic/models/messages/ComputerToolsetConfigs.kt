// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Per-member configuration for ``computer_toolset_20260801``: one optional field per member tool,
 * keyed by the member name — the same name the member's ``tool_use`` blocks carry. Every member is
 * an accepted key, and a member's defaults apply wherever its key is absent. Unknown keys are
 * rejected: the field set is this toolset version's complete member set.
 */
class ComputerToolsetConfigs
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val cursorPosition: JsonField<ComputerCursorPositionConfig>,
    private val doubleClick: JsonField<ComputerDoubleClickConfig>,
    private val holdKey: JsonField<ComputerHoldKeyConfig>,
    private val key: JsonField<ComputerKeyConfig>,
    private val leftClick: JsonField<ComputerLeftClickConfig>,
    private val leftClickDrag: JsonField<ComputerLeftClickDragConfig>,
    private val leftMouseDown: JsonField<ComputerLeftMouseDownConfig>,
    private val leftMouseUp: JsonField<ComputerLeftMouseUpConfig>,
    private val middleClick: JsonField<ComputerMiddleClickConfig>,
    private val mouseMove: JsonField<ComputerMouseMoveConfig>,
    private val rightClick: JsonField<ComputerRightClickConfig>,
    private val screenshot: JsonField<ComputerScreenshotConfig>,
    private val scroll: JsonField<ComputerScrollConfig>,
    private val tripleClick: JsonField<ComputerTripleClickConfig>,
    private val type: JsonField<ComputerTypeConfig>,
    private val wait: JsonField<ComputerWaitConfig>,
    private val zoom: JsonField<ComputerZoomConfig>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cursor_position")
        @ExcludeMissing
        cursorPosition: JsonField<ComputerCursorPositionConfig> = JsonMissing.of(),
        @JsonProperty("double_click")
        @ExcludeMissing
        doubleClick: JsonField<ComputerDoubleClickConfig> = JsonMissing.of(),
        @JsonProperty("hold_key")
        @ExcludeMissing
        holdKey: JsonField<ComputerHoldKeyConfig> = JsonMissing.of(),
        @JsonProperty("key") @ExcludeMissing key: JsonField<ComputerKeyConfig> = JsonMissing.of(),
        @JsonProperty("left_click")
        @ExcludeMissing
        leftClick: JsonField<ComputerLeftClickConfig> = JsonMissing.of(),
        @JsonProperty("left_click_drag")
        @ExcludeMissing
        leftClickDrag: JsonField<ComputerLeftClickDragConfig> = JsonMissing.of(),
        @JsonProperty("left_mouse_down")
        @ExcludeMissing
        leftMouseDown: JsonField<ComputerLeftMouseDownConfig> = JsonMissing.of(),
        @JsonProperty("left_mouse_up")
        @ExcludeMissing
        leftMouseUp: JsonField<ComputerLeftMouseUpConfig> = JsonMissing.of(),
        @JsonProperty("middle_click")
        @ExcludeMissing
        middleClick: JsonField<ComputerMiddleClickConfig> = JsonMissing.of(),
        @JsonProperty("mouse_move")
        @ExcludeMissing
        mouseMove: JsonField<ComputerMouseMoveConfig> = JsonMissing.of(),
        @JsonProperty("right_click")
        @ExcludeMissing
        rightClick: JsonField<ComputerRightClickConfig> = JsonMissing.of(),
        @JsonProperty("screenshot")
        @ExcludeMissing
        screenshot: JsonField<ComputerScreenshotConfig> = JsonMissing.of(),
        @JsonProperty("scroll")
        @ExcludeMissing
        scroll: JsonField<ComputerScrollConfig> = JsonMissing.of(),
        @JsonProperty("triple_click")
        @ExcludeMissing
        tripleClick: JsonField<ComputerTripleClickConfig> = JsonMissing.of(),
        @JsonProperty("type")
        @ExcludeMissing
        type: JsonField<ComputerTypeConfig> = JsonMissing.of(),
        @JsonProperty("wait")
        @ExcludeMissing
        wait: JsonField<ComputerWaitConfig> = JsonMissing.of(),
        @JsonProperty("zoom") @ExcludeMissing zoom: JsonField<ComputerZoomConfig> = JsonMissing.of(),
    ) : this(
        cursorPosition,
        doubleClick,
        holdKey,
        key,
        leftClick,
        leftClickDrag,
        leftMouseDown,
        leftMouseUp,
        middleClick,
        mouseMove,
        rightClick,
        screenshot,
        scroll,
        tripleClick,
        type,
        wait,
        zoom,
        mutableMapOf(),
    )

    /**
     * ``cursor_position``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cursorPosition(): Optional<ComputerCursorPositionConfig> =
        cursorPosition.getOptional("cursor_position")

    /**
     * ``double_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun doubleClick(): Optional<ComputerDoubleClickConfig> = doubleClick.getOptional("double_click")

    /**
     * ``hold_key``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun holdKey(): Optional<ComputerHoldKeyConfig> = holdKey.getOptional("hold_key")

    /**
     * ``key``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun key(): Optional<ComputerKeyConfig> = key.getOptional("key")

    /**
     * ``left_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun leftClick(): Optional<ComputerLeftClickConfig> = leftClick.getOptional("left_click")

    /**
     * ``left_click_drag``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun leftClickDrag(): Optional<ComputerLeftClickDragConfig> =
        leftClickDrag.getOptional("left_click_drag")

    /**
     * ``left_mouse_down``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun leftMouseDown(): Optional<ComputerLeftMouseDownConfig> =
        leftMouseDown.getOptional("left_mouse_down")

    /**
     * ``left_mouse_up``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun leftMouseUp(): Optional<ComputerLeftMouseUpConfig> =
        leftMouseUp.getOptional("left_mouse_up")

    /**
     * ``middle_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun middleClick(): Optional<ComputerMiddleClickConfig> = middleClick.getOptional("middle_click")

    /**
     * ``mouse_move``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun mouseMove(): Optional<ComputerMouseMoveConfig> = mouseMove.getOptional("mouse_move")

    /**
     * ``right_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun rightClick(): Optional<ComputerRightClickConfig> = rightClick.getOptional("right_click")

    /**
     * ``screenshot``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun screenshot(): Optional<ComputerScreenshotConfig> = screenshot.getOptional("screenshot")

    /**
     * ``scroll``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun scroll(): Optional<ComputerScrollConfig> = scroll.getOptional("scroll")

    /**
     * ``triple_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun tripleClick(): Optional<ComputerTripleClickConfig> = tripleClick.getOptional("triple_click")

    /**
     * ``type``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun type(): Optional<ComputerTypeConfig> = type.getOptional("type")

    /**
     * ``wait``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun wait(): Optional<ComputerWaitConfig> = wait.getOptional("wait")

    /**
     * ``zoom``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun zoom(): Optional<ComputerZoomConfig> = zoom.getOptional("zoom")

    /**
     * Returns the raw JSON value of [cursorPosition].
     *
     * Unlike [cursorPosition], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cursor_position")
    @ExcludeMissing
    fun _cursorPosition(): JsonField<ComputerCursorPositionConfig> = cursorPosition

    /**
     * Returns the raw JSON value of [doubleClick].
     *
     * Unlike [doubleClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("double_click")
    @ExcludeMissing
    fun _doubleClick(): JsonField<ComputerDoubleClickConfig> = doubleClick

    /**
     * Returns the raw JSON value of [holdKey].
     *
     * Unlike [holdKey], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("hold_key")
    @ExcludeMissing
    fun _holdKey(): JsonField<ComputerHoldKeyConfig> = holdKey

    /**
     * Returns the raw JSON value of [key].
     *
     * Unlike [key], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("key") @ExcludeMissing fun _key(): JsonField<ComputerKeyConfig> = key

    /**
     * Returns the raw JSON value of [leftClick].
     *
     * Unlike [leftClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("left_click")
    @ExcludeMissing
    fun _leftClick(): JsonField<ComputerLeftClickConfig> = leftClick

    /**
     * Returns the raw JSON value of [leftClickDrag].
     *
     * Unlike [leftClickDrag], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("left_click_drag")
    @ExcludeMissing
    fun _leftClickDrag(): JsonField<ComputerLeftClickDragConfig> = leftClickDrag

    /**
     * Returns the raw JSON value of [leftMouseDown].
     *
     * Unlike [leftMouseDown], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("left_mouse_down")
    @ExcludeMissing
    fun _leftMouseDown(): JsonField<ComputerLeftMouseDownConfig> = leftMouseDown

    /**
     * Returns the raw JSON value of [leftMouseUp].
     *
     * Unlike [leftMouseUp], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("left_mouse_up")
    @ExcludeMissing
    fun _leftMouseUp(): JsonField<ComputerLeftMouseUpConfig> = leftMouseUp

    /**
     * Returns the raw JSON value of [middleClick].
     *
     * Unlike [middleClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("middle_click")
    @ExcludeMissing
    fun _middleClick(): JsonField<ComputerMiddleClickConfig> = middleClick

    /**
     * Returns the raw JSON value of [mouseMove].
     *
     * Unlike [mouseMove], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mouse_move")
    @ExcludeMissing
    fun _mouseMove(): JsonField<ComputerMouseMoveConfig> = mouseMove

    /**
     * Returns the raw JSON value of [rightClick].
     *
     * Unlike [rightClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("right_click")
    @ExcludeMissing
    fun _rightClick(): JsonField<ComputerRightClickConfig> = rightClick

    /**
     * Returns the raw JSON value of [screenshot].
     *
     * Unlike [screenshot], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("screenshot")
    @ExcludeMissing
    fun _screenshot(): JsonField<ComputerScreenshotConfig> = screenshot

    /**
     * Returns the raw JSON value of [scroll].
     *
     * Unlike [scroll], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scroll") @ExcludeMissing fun _scroll(): JsonField<ComputerScrollConfig> = scroll

    /**
     * Returns the raw JSON value of [tripleClick].
     *
     * Unlike [tripleClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("triple_click")
    @ExcludeMissing
    fun _tripleClick(): JsonField<ComputerTripleClickConfig> = tripleClick

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<ComputerTypeConfig> = type

    /**
     * Returns the raw JSON value of [wait].
     *
     * Unlike [wait], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("wait") @ExcludeMissing fun _wait(): JsonField<ComputerWaitConfig> = wait

    /**
     * Returns the raw JSON value of [zoom].
     *
     * Unlike [zoom], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("zoom") @ExcludeMissing fun _zoom(): JsonField<ComputerZoomConfig> = zoom

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /** Returns a mutable builder for constructing an instance of [ComputerToolsetConfigs]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ComputerToolsetConfigs]. */
    class Builder internal constructor() {

        private var cursorPosition: JsonField<ComputerCursorPositionConfig> = JsonMissing.of()
        private var doubleClick: JsonField<ComputerDoubleClickConfig> = JsonMissing.of()
        private var holdKey: JsonField<ComputerHoldKeyConfig> = JsonMissing.of()
        private var key: JsonField<ComputerKeyConfig> = JsonMissing.of()
        private var leftClick: JsonField<ComputerLeftClickConfig> = JsonMissing.of()
        private var leftClickDrag: JsonField<ComputerLeftClickDragConfig> = JsonMissing.of()
        private var leftMouseDown: JsonField<ComputerLeftMouseDownConfig> = JsonMissing.of()
        private var leftMouseUp: JsonField<ComputerLeftMouseUpConfig> = JsonMissing.of()
        private var middleClick: JsonField<ComputerMiddleClickConfig> = JsonMissing.of()
        private var mouseMove: JsonField<ComputerMouseMoveConfig> = JsonMissing.of()
        private var rightClick: JsonField<ComputerRightClickConfig> = JsonMissing.of()
        private var screenshot: JsonField<ComputerScreenshotConfig> = JsonMissing.of()
        private var scroll: JsonField<ComputerScrollConfig> = JsonMissing.of()
        private var tripleClick: JsonField<ComputerTripleClickConfig> = JsonMissing.of()
        private var type: JsonField<ComputerTypeConfig> = JsonMissing.of()
        private var wait: JsonField<ComputerWaitConfig> = JsonMissing.of()
        private var zoom: JsonField<ComputerZoomConfig> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(computerToolsetConfigs: ComputerToolsetConfigs) = apply {
            cursorPosition = computerToolsetConfigs.cursorPosition
            doubleClick = computerToolsetConfigs.doubleClick
            holdKey = computerToolsetConfigs.holdKey
            key = computerToolsetConfigs.key
            leftClick = computerToolsetConfigs.leftClick
            leftClickDrag = computerToolsetConfigs.leftClickDrag
            leftMouseDown = computerToolsetConfigs.leftMouseDown
            leftMouseUp = computerToolsetConfigs.leftMouseUp
            middleClick = computerToolsetConfigs.middleClick
            mouseMove = computerToolsetConfigs.mouseMove
            rightClick = computerToolsetConfigs.rightClick
            screenshot = computerToolsetConfigs.screenshot
            scroll = computerToolsetConfigs.scroll
            tripleClick = computerToolsetConfigs.tripleClick
            type = computerToolsetConfigs.type
            wait = computerToolsetConfigs.wait
            zoom = computerToolsetConfigs.zoom
            additionalProperties = computerToolsetConfigs.additionalProperties.toMutableMap()
        }

        /** ``cursor_position``'s config overrides. */
        fun cursorPosition(cursorPosition: ComputerCursorPositionConfig?) =
            cursorPosition(JsonField.ofNullable(cursorPosition))

        /** Alias for calling [Builder.cursorPosition] with `cursorPosition.orElse(null)`. */
        fun cursorPosition(cursorPosition: Optional<ComputerCursorPositionConfig>) =
            cursorPosition(cursorPosition.getOrNull())

        /**
         * Sets [Builder.cursorPosition] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cursorPosition] with a well-typed
         * [ComputerCursorPositionConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun cursorPosition(cursorPosition: JsonField<ComputerCursorPositionConfig>) = apply {
            this.cursorPosition = cursorPosition
        }

        /** ``double_click``'s config overrides. */
        fun doubleClick(doubleClick: ComputerDoubleClickConfig?) =
            doubleClick(JsonField.ofNullable(doubleClick))

        /** Alias for calling [Builder.doubleClick] with `doubleClick.orElse(null)`. */
        fun doubleClick(doubleClick: Optional<ComputerDoubleClickConfig>) =
            doubleClick(doubleClick.getOrNull())

        /**
         * Sets [Builder.doubleClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.doubleClick] with a well-typed
         * [ComputerDoubleClickConfig] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun doubleClick(doubleClick: JsonField<ComputerDoubleClickConfig>) = apply {
            this.doubleClick = doubleClick
        }

        /** ``hold_key``'s config overrides. */
        fun holdKey(holdKey: ComputerHoldKeyConfig?) = holdKey(JsonField.ofNullable(holdKey))

        /** Alias for calling [Builder.holdKey] with `holdKey.orElse(null)`. */
        fun holdKey(holdKey: Optional<ComputerHoldKeyConfig>) = holdKey(holdKey.getOrNull())

        /**
         * Sets [Builder.holdKey] to an arbitrary JSON value.
         *
         * You should usually call [Builder.holdKey] with a well-typed [ComputerHoldKeyConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun holdKey(holdKey: JsonField<ComputerHoldKeyConfig>) = apply { this.holdKey = holdKey }

        /** ``key``'s config overrides. */
        fun key(key: ComputerKeyConfig?) = key(JsonField.ofNullable(key))

        /** Alias for calling [Builder.key] with `key.orElse(null)`. */
        fun key(key: Optional<ComputerKeyConfig>) = key(key.getOrNull())

        /**
         * Sets [Builder.key] to an arbitrary JSON value.
         *
         * You should usually call [Builder.key] with a well-typed [ComputerKeyConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun key(key: JsonField<ComputerKeyConfig>) = apply { this.key = key }

        /** ``left_click``'s config overrides. */
        fun leftClick(leftClick: ComputerLeftClickConfig?) =
            leftClick(JsonField.ofNullable(leftClick))

        /** Alias for calling [Builder.leftClick] with `leftClick.orElse(null)`. */
        fun leftClick(leftClick: Optional<ComputerLeftClickConfig>) =
            leftClick(leftClick.getOrNull())

        /**
         * Sets [Builder.leftClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.leftClick] with a well-typed [ComputerLeftClickConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun leftClick(leftClick: JsonField<ComputerLeftClickConfig>) = apply {
            this.leftClick = leftClick
        }

        /** ``left_click_drag``'s config overrides. */
        fun leftClickDrag(leftClickDrag: ComputerLeftClickDragConfig?) =
            leftClickDrag(JsonField.ofNullable(leftClickDrag))

        /** Alias for calling [Builder.leftClickDrag] with `leftClickDrag.orElse(null)`. */
        fun leftClickDrag(leftClickDrag: Optional<ComputerLeftClickDragConfig>) =
            leftClickDrag(leftClickDrag.getOrNull())

        /**
         * Sets [Builder.leftClickDrag] to an arbitrary JSON value.
         *
         * You should usually call [Builder.leftClickDrag] with a well-typed
         * [ComputerLeftClickDragConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun leftClickDrag(leftClickDrag: JsonField<ComputerLeftClickDragConfig>) = apply {
            this.leftClickDrag = leftClickDrag
        }

        /** ``left_mouse_down``'s config overrides. */
        fun leftMouseDown(leftMouseDown: ComputerLeftMouseDownConfig?) =
            leftMouseDown(JsonField.ofNullable(leftMouseDown))

        /** Alias for calling [Builder.leftMouseDown] with `leftMouseDown.orElse(null)`. */
        fun leftMouseDown(leftMouseDown: Optional<ComputerLeftMouseDownConfig>) =
            leftMouseDown(leftMouseDown.getOrNull())

        /**
         * Sets [Builder.leftMouseDown] to an arbitrary JSON value.
         *
         * You should usually call [Builder.leftMouseDown] with a well-typed
         * [ComputerLeftMouseDownConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun leftMouseDown(leftMouseDown: JsonField<ComputerLeftMouseDownConfig>) = apply {
            this.leftMouseDown = leftMouseDown
        }

        /** ``left_mouse_up``'s config overrides. */
        fun leftMouseUp(leftMouseUp: ComputerLeftMouseUpConfig?) =
            leftMouseUp(JsonField.ofNullable(leftMouseUp))

        /** Alias for calling [Builder.leftMouseUp] with `leftMouseUp.orElse(null)`. */
        fun leftMouseUp(leftMouseUp: Optional<ComputerLeftMouseUpConfig>) =
            leftMouseUp(leftMouseUp.getOrNull())

        /**
         * Sets [Builder.leftMouseUp] to an arbitrary JSON value.
         *
         * You should usually call [Builder.leftMouseUp] with a well-typed
         * [ComputerLeftMouseUpConfig] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun leftMouseUp(leftMouseUp: JsonField<ComputerLeftMouseUpConfig>) = apply {
            this.leftMouseUp = leftMouseUp
        }

        /** ``middle_click``'s config overrides. */
        fun middleClick(middleClick: ComputerMiddleClickConfig?) =
            middleClick(JsonField.ofNullable(middleClick))

        /** Alias for calling [Builder.middleClick] with `middleClick.orElse(null)`. */
        fun middleClick(middleClick: Optional<ComputerMiddleClickConfig>) =
            middleClick(middleClick.getOrNull())

        /**
         * Sets [Builder.middleClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.middleClick] with a well-typed
         * [ComputerMiddleClickConfig] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun middleClick(middleClick: JsonField<ComputerMiddleClickConfig>) = apply {
            this.middleClick = middleClick
        }

        /** ``mouse_move``'s config overrides. */
        fun mouseMove(mouseMove: ComputerMouseMoveConfig?) =
            mouseMove(JsonField.ofNullable(mouseMove))

        /** Alias for calling [Builder.mouseMove] with `mouseMove.orElse(null)`. */
        fun mouseMove(mouseMove: Optional<ComputerMouseMoveConfig>) =
            mouseMove(mouseMove.getOrNull())

        /**
         * Sets [Builder.mouseMove] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mouseMove] with a well-typed [ComputerMouseMoveConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun mouseMove(mouseMove: JsonField<ComputerMouseMoveConfig>) = apply {
            this.mouseMove = mouseMove
        }

        /** ``right_click``'s config overrides. */
        fun rightClick(rightClick: ComputerRightClickConfig?) =
            rightClick(JsonField.ofNullable(rightClick))

        /** Alias for calling [Builder.rightClick] with `rightClick.orElse(null)`. */
        fun rightClick(rightClick: Optional<ComputerRightClickConfig>) =
            rightClick(rightClick.getOrNull())

        /**
         * Sets [Builder.rightClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rightClick] with a well-typed [ComputerRightClickConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun rightClick(rightClick: JsonField<ComputerRightClickConfig>) = apply {
            this.rightClick = rightClick
        }

        /** ``screenshot``'s config overrides. */
        fun screenshot(screenshot: ComputerScreenshotConfig?) =
            screenshot(JsonField.ofNullable(screenshot))

        /** Alias for calling [Builder.screenshot] with `screenshot.orElse(null)`. */
        fun screenshot(screenshot: Optional<ComputerScreenshotConfig>) =
            screenshot(screenshot.getOrNull())

        /**
         * Sets [Builder.screenshot] to an arbitrary JSON value.
         *
         * You should usually call [Builder.screenshot] with a well-typed [ComputerScreenshotConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun screenshot(screenshot: JsonField<ComputerScreenshotConfig>) = apply {
            this.screenshot = screenshot
        }

        /** ``scroll``'s config overrides. */
        fun scroll(scroll: ComputerScrollConfig?) = scroll(JsonField.ofNullable(scroll))

        /** Alias for calling [Builder.scroll] with `scroll.orElse(null)`. */
        fun scroll(scroll: Optional<ComputerScrollConfig>) = scroll(scroll.getOrNull())

        /**
         * Sets [Builder.scroll] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scroll] with a well-typed [ComputerScrollConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun scroll(scroll: JsonField<ComputerScrollConfig>) = apply { this.scroll = scroll }

        /** ``triple_click``'s config overrides. */
        fun tripleClick(tripleClick: ComputerTripleClickConfig?) =
            tripleClick(JsonField.ofNullable(tripleClick))

        /** Alias for calling [Builder.tripleClick] with `tripleClick.orElse(null)`. */
        fun tripleClick(tripleClick: Optional<ComputerTripleClickConfig>) =
            tripleClick(tripleClick.getOrNull())

        /**
         * Sets [Builder.tripleClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tripleClick] with a well-typed
         * [ComputerTripleClickConfig] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun tripleClick(tripleClick: JsonField<ComputerTripleClickConfig>) = apply {
            this.tripleClick = tripleClick
        }

        /** ``type``'s config overrides. */
        fun type(type: ComputerTypeConfig?) = type(JsonField.ofNullable(type))

        /** Alias for calling [Builder.type] with `type.orElse(null)`. */
        fun type(type: Optional<ComputerTypeConfig>) = type(type.getOrNull())

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [ComputerTypeConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun type(type: JsonField<ComputerTypeConfig>) = apply { this.type = type }

        /** ``wait``'s config overrides. */
        fun wait(wait: ComputerWaitConfig?) = wait(JsonField.ofNullable(wait))

        /** Alias for calling [Builder.wait] with `wait.orElse(null)`. */
        fun wait(wait: Optional<ComputerWaitConfig>) = wait(wait.getOrNull())

        /**
         * Sets [Builder.wait] to an arbitrary JSON value.
         *
         * You should usually call [Builder.wait] with a well-typed [ComputerWaitConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun wait(wait: JsonField<ComputerWaitConfig>) = apply { this.wait = wait }

        /** ``zoom``'s config overrides. */
        fun zoom(zoom: ComputerZoomConfig?) = zoom(JsonField.ofNullable(zoom))

        /** Alias for calling [Builder.zoom] with `zoom.orElse(null)`. */
        fun zoom(zoom: Optional<ComputerZoomConfig>) = zoom(zoom.getOrNull())

        /**
         * Sets [Builder.zoom] to an arbitrary JSON value.
         *
         * You should usually call [Builder.zoom] with a well-typed [ComputerZoomConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun zoom(zoom: JsonField<ComputerZoomConfig>) = apply { this.zoom = zoom }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [ComputerToolsetConfigs].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): ComputerToolsetConfigs =
            ComputerToolsetConfigs(
                cursorPosition,
                doubleClick,
                holdKey,
                key,
                leftClick,
                leftClickDrag,
                leftMouseDown,
                leftMouseUp,
                middleClick,
                mouseMove,
                rightClick,
                screenshot,
                scroll,
                tripleClick,
                type,
                wait,
                zoom,
                additionalProperties.toMutableMap(),
            )
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
    fun validate(): ComputerToolsetConfigs = apply {
        if (validated) {
            return@apply
        }

        cursorPosition().ifPresent { it.validate() }
        doubleClick().ifPresent { it.validate() }
        holdKey().ifPresent { it.validate() }
        key().ifPresent { it.validate() }
        leftClick().ifPresent { it.validate() }
        leftClickDrag().ifPresent { it.validate() }
        leftMouseDown().ifPresent { it.validate() }
        leftMouseUp().ifPresent { it.validate() }
        middleClick().ifPresent { it.validate() }
        mouseMove().ifPresent { it.validate() }
        rightClick().ifPresent { it.validate() }
        screenshot().ifPresent { it.validate() }
        scroll().ifPresent { it.validate() }
        tripleClick().ifPresent { it.validate() }
        type().ifPresent { it.validate() }
        wait().ifPresent { it.validate() }
        zoom().ifPresent { it.validate() }
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
        (cursorPosition.asKnown().getOrNull()?.validity() ?: 0) +
            (doubleClick.asKnown().getOrNull()?.validity() ?: 0) +
            (holdKey.asKnown().getOrNull()?.validity() ?: 0) +
            (key.asKnown().getOrNull()?.validity() ?: 0) +
            (leftClick.asKnown().getOrNull()?.validity() ?: 0) +
            (leftClickDrag.asKnown().getOrNull()?.validity() ?: 0) +
            (leftMouseDown.asKnown().getOrNull()?.validity() ?: 0) +
            (leftMouseUp.asKnown().getOrNull()?.validity() ?: 0) +
            (middleClick.asKnown().getOrNull()?.validity() ?: 0) +
            (mouseMove.asKnown().getOrNull()?.validity() ?: 0) +
            (rightClick.asKnown().getOrNull()?.validity() ?: 0) +
            (screenshot.asKnown().getOrNull()?.validity() ?: 0) +
            (scroll.asKnown().getOrNull()?.validity() ?: 0) +
            (tripleClick.asKnown().getOrNull()?.validity() ?: 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (wait.asKnown().getOrNull()?.validity() ?: 0) +
            (zoom.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ComputerToolsetConfigs &&
            cursorPosition == other.cursorPosition &&
            doubleClick == other.doubleClick &&
            holdKey == other.holdKey &&
            key == other.key &&
            leftClick == other.leftClick &&
            leftClickDrag == other.leftClickDrag &&
            leftMouseDown == other.leftMouseDown &&
            leftMouseUp == other.leftMouseUp &&
            middleClick == other.middleClick &&
            mouseMove == other.mouseMove &&
            rightClick == other.rightClick &&
            screenshot == other.screenshot &&
            scroll == other.scroll &&
            tripleClick == other.tripleClick &&
            type == other.type &&
            wait == other.wait &&
            zoom == other.zoom &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            cursorPosition,
            doubleClick,
            holdKey,
            key,
            leftClick,
            leftClickDrag,
            leftMouseDown,
            leftMouseUp,
            middleClick,
            mouseMove,
            rightClick,
            screenshot,
            scroll,
            tripleClick,
            type,
            wait,
            zoom,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ComputerToolsetConfigs{cursorPosition=$cursorPosition, doubleClick=$doubleClick, holdKey=$holdKey, key=$key, leftClick=$leftClick, leftClickDrag=$leftClickDrag, leftMouseDown=$leftMouseDown, leftMouseUp=$leftMouseUp, middleClick=$middleClick, mouseMove=$mouseMove, rightClick=$rightClick, screenshot=$screenshot, scroll=$scroll, tripleClick=$tripleClick, type=$type, wait=$wait, zoom=$zoom, additionalProperties=$additionalProperties}"
}
