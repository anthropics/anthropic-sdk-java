// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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
class BetaComputerToolsetConfigs
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val cursorPosition: JsonField<BetaComputerCursorPositionConfig>,
    private val doubleClick: JsonField<BetaComputerDoubleClickConfig>,
    private val holdKey: JsonField<BetaComputerHoldKeyConfig>,
    private val key: JsonField<BetaComputerKeyConfig>,
    private val leftClick: JsonField<BetaComputerLeftClickConfig>,
    private val leftClickDrag: JsonField<BetaComputerLeftClickDragConfig>,
    private val leftMouseDown: JsonField<BetaComputerLeftMouseDownConfig>,
    private val leftMouseUp: JsonField<BetaComputerLeftMouseUpConfig>,
    private val middleClick: JsonField<BetaComputerMiddleClickConfig>,
    private val mouseMove: JsonField<BetaComputerMouseMoveConfig>,
    private val rightClick: JsonField<BetaComputerRightClickConfig>,
    private val screenshot: JsonField<BetaComputerScreenshotConfig>,
    private val scroll: JsonField<BetaComputerScrollConfig>,
    private val tripleClick: JsonField<BetaComputerTripleClickConfig>,
    private val type: JsonField<BetaComputerTypeConfig>,
    private val wait: JsonField<BetaComputerWaitConfig>,
    private val zoom: JsonField<BetaComputerZoomConfig>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cursor_position")
        @ExcludeMissing
        cursorPosition: JsonField<BetaComputerCursorPositionConfig> = JsonMissing.of(),
        @JsonProperty("double_click")
        @ExcludeMissing
        doubleClick: JsonField<BetaComputerDoubleClickConfig> = JsonMissing.of(),
        @JsonProperty("hold_key")
        @ExcludeMissing
        holdKey: JsonField<BetaComputerHoldKeyConfig> = JsonMissing.of(),
        @JsonProperty("key")
        @ExcludeMissing
        key: JsonField<BetaComputerKeyConfig> = JsonMissing.of(),
        @JsonProperty("left_click")
        @ExcludeMissing
        leftClick: JsonField<BetaComputerLeftClickConfig> = JsonMissing.of(),
        @JsonProperty("left_click_drag")
        @ExcludeMissing
        leftClickDrag: JsonField<BetaComputerLeftClickDragConfig> = JsonMissing.of(),
        @JsonProperty("left_mouse_down")
        @ExcludeMissing
        leftMouseDown: JsonField<BetaComputerLeftMouseDownConfig> = JsonMissing.of(),
        @JsonProperty("left_mouse_up")
        @ExcludeMissing
        leftMouseUp: JsonField<BetaComputerLeftMouseUpConfig> = JsonMissing.of(),
        @JsonProperty("middle_click")
        @ExcludeMissing
        middleClick: JsonField<BetaComputerMiddleClickConfig> = JsonMissing.of(),
        @JsonProperty("mouse_move")
        @ExcludeMissing
        mouseMove: JsonField<BetaComputerMouseMoveConfig> = JsonMissing.of(),
        @JsonProperty("right_click")
        @ExcludeMissing
        rightClick: JsonField<BetaComputerRightClickConfig> = JsonMissing.of(),
        @JsonProperty("screenshot")
        @ExcludeMissing
        screenshot: JsonField<BetaComputerScreenshotConfig> = JsonMissing.of(),
        @JsonProperty("scroll")
        @ExcludeMissing
        scroll: JsonField<BetaComputerScrollConfig> = JsonMissing.of(),
        @JsonProperty("triple_click")
        @ExcludeMissing
        tripleClick: JsonField<BetaComputerTripleClickConfig> = JsonMissing.of(),
        @JsonProperty("type")
        @ExcludeMissing
        type: JsonField<BetaComputerTypeConfig> = JsonMissing.of(),
        @JsonProperty("wait")
        @ExcludeMissing
        wait: JsonField<BetaComputerWaitConfig> = JsonMissing.of(),
        @JsonProperty("zoom")
        @ExcludeMissing
        zoom: JsonField<BetaComputerZoomConfig> = JsonMissing.of(),
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
    fun cursorPosition(): Optional<BetaComputerCursorPositionConfig> =
        cursorPosition.getOptional("cursor_position")

    /**
     * ``double_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun doubleClick(): Optional<BetaComputerDoubleClickConfig> =
        doubleClick.getOptional("double_click")

    /**
     * ``hold_key``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun holdKey(): Optional<BetaComputerHoldKeyConfig> = holdKey.getOptional("hold_key")

    /**
     * ``key``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun key(): Optional<BetaComputerKeyConfig> = key.getOptional("key")

    /**
     * ``left_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun leftClick(): Optional<BetaComputerLeftClickConfig> = leftClick.getOptional("left_click")

    /**
     * ``left_click_drag``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun leftClickDrag(): Optional<BetaComputerLeftClickDragConfig> =
        leftClickDrag.getOptional("left_click_drag")

    /**
     * ``left_mouse_down``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun leftMouseDown(): Optional<BetaComputerLeftMouseDownConfig> =
        leftMouseDown.getOptional("left_mouse_down")

    /**
     * ``left_mouse_up``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun leftMouseUp(): Optional<BetaComputerLeftMouseUpConfig> =
        leftMouseUp.getOptional("left_mouse_up")

    /**
     * ``middle_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun middleClick(): Optional<BetaComputerMiddleClickConfig> =
        middleClick.getOptional("middle_click")

    /**
     * ``mouse_move``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun mouseMove(): Optional<BetaComputerMouseMoveConfig> = mouseMove.getOptional("mouse_move")

    /**
     * ``right_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun rightClick(): Optional<BetaComputerRightClickConfig> = rightClick.getOptional("right_click")

    /**
     * ``screenshot``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun screenshot(): Optional<BetaComputerScreenshotConfig> = screenshot.getOptional("screenshot")

    /**
     * ``scroll``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun scroll(): Optional<BetaComputerScrollConfig> = scroll.getOptional("scroll")

    /**
     * ``triple_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun tripleClick(): Optional<BetaComputerTripleClickConfig> =
        tripleClick.getOptional("triple_click")

    /**
     * ``type``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun type(): Optional<BetaComputerTypeConfig> = type.getOptional("type")

    /**
     * ``wait``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun wait(): Optional<BetaComputerWaitConfig> = wait.getOptional("wait")

    /**
     * ``zoom``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun zoom(): Optional<BetaComputerZoomConfig> = zoom.getOptional("zoom")

    /**
     * Returns the raw JSON value of [cursorPosition].
     *
     * Unlike [cursorPosition], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cursor_position")
    @ExcludeMissing
    fun _cursorPosition(): JsonField<BetaComputerCursorPositionConfig> = cursorPosition

    /**
     * Returns the raw JSON value of [doubleClick].
     *
     * Unlike [doubleClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("double_click")
    @ExcludeMissing
    fun _doubleClick(): JsonField<BetaComputerDoubleClickConfig> = doubleClick

    /**
     * Returns the raw JSON value of [holdKey].
     *
     * Unlike [holdKey], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("hold_key")
    @ExcludeMissing
    fun _holdKey(): JsonField<BetaComputerHoldKeyConfig> = holdKey

    /**
     * Returns the raw JSON value of [key].
     *
     * Unlike [key], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("key") @ExcludeMissing fun _key(): JsonField<BetaComputerKeyConfig> = key

    /**
     * Returns the raw JSON value of [leftClick].
     *
     * Unlike [leftClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("left_click")
    @ExcludeMissing
    fun _leftClick(): JsonField<BetaComputerLeftClickConfig> = leftClick

    /**
     * Returns the raw JSON value of [leftClickDrag].
     *
     * Unlike [leftClickDrag], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("left_click_drag")
    @ExcludeMissing
    fun _leftClickDrag(): JsonField<BetaComputerLeftClickDragConfig> = leftClickDrag

    /**
     * Returns the raw JSON value of [leftMouseDown].
     *
     * Unlike [leftMouseDown], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("left_mouse_down")
    @ExcludeMissing
    fun _leftMouseDown(): JsonField<BetaComputerLeftMouseDownConfig> = leftMouseDown

    /**
     * Returns the raw JSON value of [leftMouseUp].
     *
     * Unlike [leftMouseUp], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("left_mouse_up")
    @ExcludeMissing
    fun _leftMouseUp(): JsonField<BetaComputerLeftMouseUpConfig> = leftMouseUp

    /**
     * Returns the raw JSON value of [middleClick].
     *
     * Unlike [middleClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("middle_click")
    @ExcludeMissing
    fun _middleClick(): JsonField<BetaComputerMiddleClickConfig> = middleClick

    /**
     * Returns the raw JSON value of [mouseMove].
     *
     * Unlike [mouseMove], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mouse_move")
    @ExcludeMissing
    fun _mouseMove(): JsonField<BetaComputerMouseMoveConfig> = mouseMove

    /**
     * Returns the raw JSON value of [rightClick].
     *
     * Unlike [rightClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("right_click")
    @ExcludeMissing
    fun _rightClick(): JsonField<BetaComputerRightClickConfig> = rightClick

    /**
     * Returns the raw JSON value of [screenshot].
     *
     * Unlike [screenshot], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("screenshot")
    @ExcludeMissing
    fun _screenshot(): JsonField<BetaComputerScreenshotConfig> = screenshot

    /**
     * Returns the raw JSON value of [scroll].
     *
     * Unlike [scroll], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scroll")
    @ExcludeMissing
    fun _scroll(): JsonField<BetaComputerScrollConfig> = scroll

    /**
     * Returns the raw JSON value of [tripleClick].
     *
     * Unlike [tripleClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("triple_click")
    @ExcludeMissing
    fun _tripleClick(): JsonField<BetaComputerTripleClickConfig> = tripleClick

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<BetaComputerTypeConfig> = type

    /**
     * Returns the raw JSON value of [wait].
     *
     * Unlike [wait], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("wait") @ExcludeMissing fun _wait(): JsonField<BetaComputerWaitConfig> = wait

    /**
     * Returns the raw JSON value of [zoom].
     *
     * Unlike [zoom], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("zoom") @ExcludeMissing fun _zoom(): JsonField<BetaComputerZoomConfig> = zoom

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

        /**
         * Returns a mutable builder for constructing an instance of [BetaComputerToolsetConfigs].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaComputerToolsetConfigs]. */
    class Builder internal constructor() {

        private var cursorPosition: JsonField<BetaComputerCursorPositionConfig> = JsonMissing.of()
        private var doubleClick: JsonField<BetaComputerDoubleClickConfig> = JsonMissing.of()
        private var holdKey: JsonField<BetaComputerHoldKeyConfig> = JsonMissing.of()
        private var key: JsonField<BetaComputerKeyConfig> = JsonMissing.of()
        private var leftClick: JsonField<BetaComputerLeftClickConfig> = JsonMissing.of()
        private var leftClickDrag: JsonField<BetaComputerLeftClickDragConfig> = JsonMissing.of()
        private var leftMouseDown: JsonField<BetaComputerLeftMouseDownConfig> = JsonMissing.of()
        private var leftMouseUp: JsonField<BetaComputerLeftMouseUpConfig> = JsonMissing.of()
        private var middleClick: JsonField<BetaComputerMiddleClickConfig> = JsonMissing.of()
        private var mouseMove: JsonField<BetaComputerMouseMoveConfig> = JsonMissing.of()
        private var rightClick: JsonField<BetaComputerRightClickConfig> = JsonMissing.of()
        private var screenshot: JsonField<BetaComputerScreenshotConfig> = JsonMissing.of()
        private var scroll: JsonField<BetaComputerScrollConfig> = JsonMissing.of()
        private var tripleClick: JsonField<BetaComputerTripleClickConfig> = JsonMissing.of()
        private var type: JsonField<BetaComputerTypeConfig> = JsonMissing.of()
        private var wait: JsonField<BetaComputerWaitConfig> = JsonMissing.of()
        private var zoom: JsonField<BetaComputerZoomConfig> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaComputerToolsetConfigs: BetaComputerToolsetConfigs) = apply {
            cursorPosition = betaComputerToolsetConfigs.cursorPosition
            doubleClick = betaComputerToolsetConfigs.doubleClick
            holdKey = betaComputerToolsetConfigs.holdKey
            key = betaComputerToolsetConfigs.key
            leftClick = betaComputerToolsetConfigs.leftClick
            leftClickDrag = betaComputerToolsetConfigs.leftClickDrag
            leftMouseDown = betaComputerToolsetConfigs.leftMouseDown
            leftMouseUp = betaComputerToolsetConfigs.leftMouseUp
            middleClick = betaComputerToolsetConfigs.middleClick
            mouseMove = betaComputerToolsetConfigs.mouseMove
            rightClick = betaComputerToolsetConfigs.rightClick
            screenshot = betaComputerToolsetConfigs.screenshot
            scroll = betaComputerToolsetConfigs.scroll
            tripleClick = betaComputerToolsetConfigs.tripleClick
            type = betaComputerToolsetConfigs.type
            wait = betaComputerToolsetConfigs.wait
            zoom = betaComputerToolsetConfigs.zoom
            additionalProperties = betaComputerToolsetConfigs.additionalProperties.toMutableMap()
        }

        /** ``cursor_position``'s config overrides. */
        fun cursorPosition(cursorPosition: BetaComputerCursorPositionConfig?) =
            cursorPosition(JsonField.ofNullable(cursorPosition))

        /** Alias for calling [Builder.cursorPosition] with `cursorPosition.orElse(null)`. */
        fun cursorPosition(cursorPosition: Optional<BetaComputerCursorPositionConfig>) =
            cursorPosition(cursorPosition.getOrNull())

        /**
         * Sets [Builder.cursorPosition] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cursorPosition] with a well-typed
         * [BetaComputerCursorPositionConfig] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun cursorPosition(cursorPosition: JsonField<BetaComputerCursorPositionConfig>) = apply {
            this.cursorPosition = cursorPosition
        }

        /** ``double_click``'s config overrides. */
        fun doubleClick(doubleClick: BetaComputerDoubleClickConfig?) =
            doubleClick(JsonField.ofNullable(doubleClick))

        /** Alias for calling [Builder.doubleClick] with `doubleClick.orElse(null)`. */
        fun doubleClick(doubleClick: Optional<BetaComputerDoubleClickConfig>) =
            doubleClick(doubleClick.getOrNull())

        /**
         * Sets [Builder.doubleClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.doubleClick] with a well-typed
         * [BetaComputerDoubleClickConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun doubleClick(doubleClick: JsonField<BetaComputerDoubleClickConfig>) = apply {
            this.doubleClick = doubleClick
        }

        /** ``hold_key``'s config overrides. */
        fun holdKey(holdKey: BetaComputerHoldKeyConfig?) = holdKey(JsonField.ofNullable(holdKey))

        /** Alias for calling [Builder.holdKey] with `holdKey.orElse(null)`. */
        fun holdKey(holdKey: Optional<BetaComputerHoldKeyConfig>) = holdKey(holdKey.getOrNull())

        /**
         * Sets [Builder.holdKey] to an arbitrary JSON value.
         *
         * You should usually call [Builder.holdKey] with a well-typed [BetaComputerHoldKeyConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun holdKey(holdKey: JsonField<BetaComputerHoldKeyConfig>) = apply {
            this.holdKey = holdKey
        }

        /** ``key``'s config overrides. */
        fun key(key: BetaComputerKeyConfig?) = key(JsonField.ofNullable(key))

        /** Alias for calling [Builder.key] with `key.orElse(null)`. */
        fun key(key: Optional<BetaComputerKeyConfig>) = key(key.getOrNull())

        /**
         * Sets [Builder.key] to an arbitrary JSON value.
         *
         * You should usually call [Builder.key] with a well-typed [BetaComputerKeyConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun key(key: JsonField<BetaComputerKeyConfig>) = apply { this.key = key }

        /** ``left_click``'s config overrides. */
        fun leftClick(leftClick: BetaComputerLeftClickConfig?) =
            leftClick(JsonField.ofNullable(leftClick))

        /** Alias for calling [Builder.leftClick] with `leftClick.orElse(null)`. */
        fun leftClick(leftClick: Optional<BetaComputerLeftClickConfig>) =
            leftClick(leftClick.getOrNull())

        /**
         * Sets [Builder.leftClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.leftClick] with a well-typed
         * [BetaComputerLeftClickConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun leftClick(leftClick: JsonField<BetaComputerLeftClickConfig>) = apply {
            this.leftClick = leftClick
        }

        /** ``left_click_drag``'s config overrides. */
        fun leftClickDrag(leftClickDrag: BetaComputerLeftClickDragConfig?) =
            leftClickDrag(JsonField.ofNullable(leftClickDrag))

        /** Alias for calling [Builder.leftClickDrag] with `leftClickDrag.orElse(null)`. */
        fun leftClickDrag(leftClickDrag: Optional<BetaComputerLeftClickDragConfig>) =
            leftClickDrag(leftClickDrag.getOrNull())

        /**
         * Sets [Builder.leftClickDrag] to an arbitrary JSON value.
         *
         * You should usually call [Builder.leftClickDrag] with a well-typed
         * [BetaComputerLeftClickDragConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun leftClickDrag(leftClickDrag: JsonField<BetaComputerLeftClickDragConfig>) = apply {
            this.leftClickDrag = leftClickDrag
        }

        /** ``left_mouse_down``'s config overrides. */
        fun leftMouseDown(leftMouseDown: BetaComputerLeftMouseDownConfig?) =
            leftMouseDown(JsonField.ofNullable(leftMouseDown))

        /** Alias for calling [Builder.leftMouseDown] with `leftMouseDown.orElse(null)`. */
        fun leftMouseDown(leftMouseDown: Optional<BetaComputerLeftMouseDownConfig>) =
            leftMouseDown(leftMouseDown.getOrNull())

        /**
         * Sets [Builder.leftMouseDown] to an arbitrary JSON value.
         *
         * You should usually call [Builder.leftMouseDown] with a well-typed
         * [BetaComputerLeftMouseDownConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun leftMouseDown(leftMouseDown: JsonField<BetaComputerLeftMouseDownConfig>) = apply {
            this.leftMouseDown = leftMouseDown
        }

        /** ``left_mouse_up``'s config overrides. */
        fun leftMouseUp(leftMouseUp: BetaComputerLeftMouseUpConfig?) =
            leftMouseUp(JsonField.ofNullable(leftMouseUp))

        /** Alias for calling [Builder.leftMouseUp] with `leftMouseUp.orElse(null)`. */
        fun leftMouseUp(leftMouseUp: Optional<BetaComputerLeftMouseUpConfig>) =
            leftMouseUp(leftMouseUp.getOrNull())

        /**
         * Sets [Builder.leftMouseUp] to an arbitrary JSON value.
         *
         * You should usually call [Builder.leftMouseUp] with a well-typed
         * [BetaComputerLeftMouseUpConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun leftMouseUp(leftMouseUp: JsonField<BetaComputerLeftMouseUpConfig>) = apply {
            this.leftMouseUp = leftMouseUp
        }

        /** ``middle_click``'s config overrides. */
        fun middleClick(middleClick: BetaComputerMiddleClickConfig?) =
            middleClick(JsonField.ofNullable(middleClick))

        /** Alias for calling [Builder.middleClick] with `middleClick.orElse(null)`. */
        fun middleClick(middleClick: Optional<BetaComputerMiddleClickConfig>) =
            middleClick(middleClick.getOrNull())

        /**
         * Sets [Builder.middleClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.middleClick] with a well-typed
         * [BetaComputerMiddleClickConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun middleClick(middleClick: JsonField<BetaComputerMiddleClickConfig>) = apply {
            this.middleClick = middleClick
        }

        /** ``mouse_move``'s config overrides. */
        fun mouseMove(mouseMove: BetaComputerMouseMoveConfig?) =
            mouseMove(JsonField.ofNullable(mouseMove))

        /** Alias for calling [Builder.mouseMove] with `mouseMove.orElse(null)`. */
        fun mouseMove(mouseMove: Optional<BetaComputerMouseMoveConfig>) =
            mouseMove(mouseMove.getOrNull())

        /**
         * Sets [Builder.mouseMove] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mouseMove] with a well-typed
         * [BetaComputerMouseMoveConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun mouseMove(mouseMove: JsonField<BetaComputerMouseMoveConfig>) = apply {
            this.mouseMove = mouseMove
        }

        /** ``right_click``'s config overrides. */
        fun rightClick(rightClick: BetaComputerRightClickConfig?) =
            rightClick(JsonField.ofNullable(rightClick))

        /** Alias for calling [Builder.rightClick] with `rightClick.orElse(null)`. */
        fun rightClick(rightClick: Optional<BetaComputerRightClickConfig>) =
            rightClick(rightClick.getOrNull())

        /**
         * Sets [Builder.rightClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rightClick] with a well-typed
         * [BetaComputerRightClickConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun rightClick(rightClick: JsonField<BetaComputerRightClickConfig>) = apply {
            this.rightClick = rightClick
        }

        /** ``screenshot``'s config overrides. */
        fun screenshot(screenshot: BetaComputerScreenshotConfig?) =
            screenshot(JsonField.ofNullable(screenshot))

        /** Alias for calling [Builder.screenshot] with `screenshot.orElse(null)`. */
        fun screenshot(screenshot: Optional<BetaComputerScreenshotConfig>) =
            screenshot(screenshot.getOrNull())

        /**
         * Sets [Builder.screenshot] to an arbitrary JSON value.
         *
         * You should usually call [Builder.screenshot] with a well-typed
         * [BetaComputerScreenshotConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun screenshot(screenshot: JsonField<BetaComputerScreenshotConfig>) = apply {
            this.screenshot = screenshot
        }

        /** ``scroll``'s config overrides. */
        fun scroll(scroll: BetaComputerScrollConfig?) = scroll(JsonField.ofNullable(scroll))

        /** Alias for calling [Builder.scroll] with `scroll.orElse(null)`. */
        fun scroll(scroll: Optional<BetaComputerScrollConfig>) = scroll(scroll.getOrNull())

        /**
         * Sets [Builder.scroll] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scroll] with a well-typed [BetaComputerScrollConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun scroll(scroll: JsonField<BetaComputerScrollConfig>) = apply { this.scroll = scroll }

        /** ``triple_click``'s config overrides. */
        fun tripleClick(tripleClick: BetaComputerTripleClickConfig?) =
            tripleClick(JsonField.ofNullable(tripleClick))

        /** Alias for calling [Builder.tripleClick] with `tripleClick.orElse(null)`. */
        fun tripleClick(tripleClick: Optional<BetaComputerTripleClickConfig>) =
            tripleClick(tripleClick.getOrNull())

        /**
         * Sets [Builder.tripleClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tripleClick] with a well-typed
         * [BetaComputerTripleClickConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun tripleClick(tripleClick: JsonField<BetaComputerTripleClickConfig>) = apply {
            this.tripleClick = tripleClick
        }

        /** ``type``'s config overrides. */
        fun type(type: BetaComputerTypeConfig?) = type(JsonField.ofNullable(type))

        /** Alias for calling [Builder.type] with `type.orElse(null)`. */
        fun type(type: Optional<BetaComputerTypeConfig>) = type(type.getOrNull())

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [BetaComputerTypeConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun type(type: JsonField<BetaComputerTypeConfig>) = apply { this.type = type }

        /** ``wait``'s config overrides. */
        fun wait(wait: BetaComputerWaitConfig?) = wait(JsonField.ofNullable(wait))

        /** Alias for calling [Builder.wait] with `wait.orElse(null)`. */
        fun wait(wait: Optional<BetaComputerWaitConfig>) = wait(wait.getOrNull())

        /**
         * Sets [Builder.wait] to an arbitrary JSON value.
         *
         * You should usually call [Builder.wait] with a well-typed [BetaComputerWaitConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun wait(wait: JsonField<BetaComputerWaitConfig>) = apply { this.wait = wait }

        /** ``zoom``'s config overrides. */
        fun zoom(zoom: BetaComputerZoomConfig?) = zoom(JsonField.ofNullable(zoom))

        /** Alias for calling [Builder.zoom] with `zoom.orElse(null)`. */
        fun zoom(zoom: Optional<BetaComputerZoomConfig>) = zoom(zoom.getOrNull())

        /**
         * Sets [Builder.zoom] to an arbitrary JSON value.
         *
         * You should usually call [Builder.zoom] with a well-typed [BetaComputerZoomConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun zoom(zoom: JsonField<BetaComputerZoomConfig>) = apply { this.zoom = zoom }

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
         * Returns an immutable instance of [BetaComputerToolsetConfigs].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaComputerToolsetConfigs =
            BetaComputerToolsetConfigs(
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
    fun validate(): BetaComputerToolsetConfigs = apply {
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

        return other is BetaComputerToolsetConfigs &&
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
        "BetaComputerToolsetConfigs{cursorPosition=$cursorPosition, doubleClick=$doubleClick, holdKey=$holdKey, key=$key, leftClick=$leftClick, leftClickDrag=$leftClickDrag, leftMouseDown=$leftMouseDown, leftMouseUp=$leftMouseUp, middleClick=$middleClick, mouseMove=$mouseMove, rightClick=$rightClick, screenshot=$screenshot, scroll=$scroll, tripleClick=$tripleClick, type=$type, wait=$wait, zoom=$zoom, additionalProperties=$additionalProperties}"
}
