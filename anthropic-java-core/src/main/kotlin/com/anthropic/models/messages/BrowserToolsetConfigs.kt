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
 * Per-member configuration for ``browser_toolset_20260801``: one optional field per member tool,
 * keyed by the member name — the same name the member's ``tool_use`` blocks carry. Every member is
 * an accepted key, and a member's defaults apply wherever its key is absent. Unknown keys are
 * rejected: the field set is this toolset version's complete member set.
 */
class BrowserToolsetConfigs
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val closeTab: JsonField<BrowserCloseTabConfig>,
    private val doubleClick: JsonField<BrowserDoubleClickConfig>,
    private val fileUpload: JsonField<BrowserFileUploadConfig>,
    private val find: JsonField<BrowserFindConfig>,
    private val formInput: JsonField<BrowserFormInputConfig>,
    private val getPageText: JsonField<BrowserGetPageTextConfig>,
    private val holdKey: JsonField<BrowserHoldKeyConfig>,
    private val hover: JsonField<BrowserHoverConfig>,
    private val javascriptExec: JsonField<BrowserJavascriptExecConfig>,
    private val key: JsonField<BrowserKeyConfig>,
    private val leftClick: JsonField<BrowserLeftClickConfig>,
    private val leftClickDrag: JsonField<BrowserLeftClickDragConfig>,
    private val leftMouseDown: JsonField<BrowserLeftMouseDownConfig>,
    private val leftMouseUp: JsonField<BrowserLeftMouseUpConfig>,
    private val listTabs: JsonField<BrowserListTabsConfig>,
    private val middleClick: JsonField<BrowserMiddleClickConfig>,
    private val mouseMove: JsonField<BrowserMouseMoveConfig>,
    private val navigate: JsonField<BrowserNavigateConfig>,
    private val newTab: JsonField<BrowserNewTabConfig>,
    private val readConsole: JsonField<BrowserReadConsoleConfig>,
    private val readNetwork: JsonField<BrowserReadNetworkConfig>,
    private val readPage: JsonField<BrowserReadPageConfig>,
    private val rightClick: JsonField<BrowserRightClickConfig>,
    private val screenshot: JsonField<BrowserScreenshotConfig>,
    private val scroll: JsonField<BrowserScrollConfig>,
    private val scrollTo: JsonField<BrowserScrollToConfig>,
    private val switchTab: JsonField<BrowserSwitchTabConfig>,
    private val tripleClick: JsonField<BrowserTripleClickConfig>,
    private val type: JsonField<BrowserTypeConfig>,
    private val wait: JsonField<BrowserWaitConfig>,
    private val zoom: JsonField<BrowserZoomConfig>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("close_tab")
        @ExcludeMissing
        closeTab: JsonField<BrowserCloseTabConfig> = JsonMissing.of(),
        @JsonProperty("double_click")
        @ExcludeMissing
        doubleClick: JsonField<BrowserDoubleClickConfig> = JsonMissing.of(),
        @JsonProperty("file_upload")
        @ExcludeMissing
        fileUpload: JsonField<BrowserFileUploadConfig> = JsonMissing.of(),
        @JsonProperty("find") @ExcludeMissing find: JsonField<BrowserFindConfig> = JsonMissing.of(),
        @JsonProperty("form_input")
        @ExcludeMissing
        formInput: JsonField<BrowserFormInputConfig> = JsonMissing.of(),
        @JsonProperty("get_page_text")
        @ExcludeMissing
        getPageText: JsonField<BrowserGetPageTextConfig> = JsonMissing.of(),
        @JsonProperty("hold_key")
        @ExcludeMissing
        holdKey: JsonField<BrowserHoldKeyConfig> = JsonMissing.of(),
        @JsonProperty("hover")
        @ExcludeMissing
        hover: JsonField<BrowserHoverConfig> = JsonMissing.of(),
        @JsonProperty("javascript_exec")
        @ExcludeMissing
        javascriptExec: JsonField<BrowserJavascriptExecConfig> = JsonMissing.of(),
        @JsonProperty("key") @ExcludeMissing key: JsonField<BrowserKeyConfig> = JsonMissing.of(),
        @JsonProperty("left_click")
        @ExcludeMissing
        leftClick: JsonField<BrowserLeftClickConfig> = JsonMissing.of(),
        @JsonProperty("left_click_drag")
        @ExcludeMissing
        leftClickDrag: JsonField<BrowserLeftClickDragConfig> = JsonMissing.of(),
        @JsonProperty("left_mouse_down")
        @ExcludeMissing
        leftMouseDown: JsonField<BrowserLeftMouseDownConfig> = JsonMissing.of(),
        @JsonProperty("left_mouse_up")
        @ExcludeMissing
        leftMouseUp: JsonField<BrowserLeftMouseUpConfig> = JsonMissing.of(),
        @JsonProperty("list_tabs")
        @ExcludeMissing
        listTabs: JsonField<BrowserListTabsConfig> = JsonMissing.of(),
        @JsonProperty("middle_click")
        @ExcludeMissing
        middleClick: JsonField<BrowserMiddleClickConfig> = JsonMissing.of(),
        @JsonProperty("mouse_move")
        @ExcludeMissing
        mouseMove: JsonField<BrowserMouseMoveConfig> = JsonMissing.of(),
        @JsonProperty("navigate")
        @ExcludeMissing
        navigate: JsonField<BrowserNavigateConfig> = JsonMissing.of(),
        @JsonProperty("new_tab")
        @ExcludeMissing
        newTab: JsonField<BrowserNewTabConfig> = JsonMissing.of(),
        @JsonProperty("read_console")
        @ExcludeMissing
        readConsole: JsonField<BrowserReadConsoleConfig> = JsonMissing.of(),
        @JsonProperty("read_network")
        @ExcludeMissing
        readNetwork: JsonField<BrowserReadNetworkConfig> = JsonMissing.of(),
        @JsonProperty("read_page")
        @ExcludeMissing
        readPage: JsonField<BrowserReadPageConfig> = JsonMissing.of(),
        @JsonProperty("right_click")
        @ExcludeMissing
        rightClick: JsonField<BrowserRightClickConfig> = JsonMissing.of(),
        @JsonProperty("screenshot")
        @ExcludeMissing
        screenshot: JsonField<BrowserScreenshotConfig> = JsonMissing.of(),
        @JsonProperty("scroll")
        @ExcludeMissing
        scroll: JsonField<BrowserScrollConfig> = JsonMissing.of(),
        @JsonProperty("scroll_to")
        @ExcludeMissing
        scrollTo: JsonField<BrowserScrollToConfig> = JsonMissing.of(),
        @JsonProperty("switch_tab")
        @ExcludeMissing
        switchTab: JsonField<BrowserSwitchTabConfig> = JsonMissing.of(),
        @JsonProperty("triple_click")
        @ExcludeMissing
        tripleClick: JsonField<BrowserTripleClickConfig> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<BrowserTypeConfig> = JsonMissing.of(),
        @JsonProperty("wait") @ExcludeMissing wait: JsonField<BrowserWaitConfig> = JsonMissing.of(),
        @JsonProperty("zoom") @ExcludeMissing zoom: JsonField<BrowserZoomConfig> = JsonMissing.of(),
    ) : this(
        closeTab,
        doubleClick,
        fileUpload,
        find,
        formInput,
        getPageText,
        holdKey,
        hover,
        javascriptExec,
        key,
        leftClick,
        leftClickDrag,
        leftMouseDown,
        leftMouseUp,
        listTabs,
        middleClick,
        mouseMove,
        navigate,
        newTab,
        readConsole,
        readNetwork,
        readPage,
        rightClick,
        screenshot,
        scroll,
        scrollTo,
        switchTab,
        tripleClick,
        type,
        wait,
        zoom,
        mutableMapOf(),
    )

    /**
     * ``close_tab``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun closeTab(): Optional<BrowserCloseTabConfig> = closeTab.getOptional("close_tab")

    /**
     * ``double_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun doubleClick(): Optional<BrowserDoubleClickConfig> = doubleClick.getOptional("double_click")

    /**
     * ``file_upload``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun fileUpload(): Optional<BrowserFileUploadConfig> = fileUpload.getOptional("file_upload")

    /**
     * ``find``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun find(): Optional<BrowserFindConfig> = find.getOptional("find")

    /**
     * ``form_input``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun formInput(): Optional<BrowserFormInputConfig> = formInput.getOptional("form_input")

    /**
     * ``get_page_text``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun getPageText(): Optional<BrowserGetPageTextConfig> = getPageText.getOptional("get_page_text")

    /**
     * ``hold_key``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun holdKey(): Optional<BrowserHoldKeyConfig> = holdKey.getOptional("hold_key")

    /**
     * ``hover``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun hover(): Optional<BrowserHoverConfig> = hover.getOptional("hover")

    /**
     * ``javascript_exec``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun javascriptExec(): Optional<BrowserJavascriptExecConfig> =
        javascriptExec.getOptional("javascript_exec")

    /**
     * ``key``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun key(): Optional<BrowserKeyConfig> = key.getOptional("key")

    /**
     * ``left_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun leftClick(): Optional<BrowserLeftClickConfig> = leftClick.getOptional("left_click")

    /**
     * ``left_click_drag``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun leftClickDrag(): Optional<BrowserLeftClickDragConfig> =
        leftClickDrag.getOptional("left_click_drag")

    /**
     * ``left_mouse_down``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun leftMouseDown(): Optional<BrowserLeftMouseDownConfig> =
        leftMouseDown.getOptional("left_mouse_down")

    /**
     * ``left_mouse_up``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun leftMouseUp(): Optional<BrowserLeftMouseUpConfig> = leftMouseUp.getOptional("left_mouse_up")

    /**
     * ``list_tabs``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun listTabs(): Optional<BrowserListTabsConfig> = listTabs.getOptional("list_tabs")

    /**
     * ``middle_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun middleClick(): Optional<BrowserMiddleClickConfig> = middleClick.getOptional("middle_click")

    /**
     * ``mouse_move``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun mouseMove(): Optional<BrowserMouseMoveConfig> = mouseMove.getOptional("mouse_move")

    /**
     * ``navigate``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun navigate(): Optional<BrowserNavigateConfig> = navigate.getOptional("navigate")

    /**
     * ``new_tab``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun newTab(): Optional<BrowserNewTabConfig> = newTab.getOptional("new_tab")

    /**
     * ``read_console``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun readConsole(): Optional<BrowserReadConsoleConfig> = readConsole.getOptional("read_console")

    /**
     * ``read_network``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun readNetwork(): Optional<BrowserReadNetworkConfig> = readNetwork.getOptional("read_network")

    /**
     * ``read_page``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun readPage(): Optional<BrowserReadPageConfig> = readPage.getOptional("read_page")

    /**
     * ``right_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun rightClick(): Optional<BrowserRightClickConfig> = rightClick.getOptional("right_click")

    /**
     * ``screenshot``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun screenshot(): Optional<BrowserScreenshotConfig> = screenshot.getOptional("screenshot")

    /**
     * ``scroll``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun scroll(): Optional<BrowserScrollConfig> = scroll.getOptional("scroll")

    /**
     * ``scroll_to``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun scrollTo(): Optional<BrowserScrollToConfig> = scrollTo.getOptional("scroll_to")

    /**
     * ``switch_tab``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun switchTab(): Optional<BrowserSwitchTabConfig> = switchTab.getOptional("switch_tab")

    /**
     * ``triple_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun tripleClick(): Optional<BrowserTripleClickConfig> = tripleClick.getOptional("triple_click")

    /**
     * ``type``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun type(): Optional<BrowserTypeConfig> = type.getOptional("type")

    /**
     * ``wait``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun wait(): Optional<BrowserWaitConfig> = wait.getOptional("wait")

    /**
     * ``zoom``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun zoom(): Optional<BrowserZoomConfig> = zoom.getOptional("zoom")

    /**
     * Returns the raw JSON value of [closeTab].
     *
     * Unlike [closeTab], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("close_tab")
    @ExcludeMissing
    fun _closeTab(): JsonField<BrowserCloseTabConfig> = closeTab

    /**
     * Returns the raw JSON value of [doubleClick].
     *
     * Unlike [doubleClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("double_click")
    @ExcludeMissing
    fun _doubleClick(): JsonField<BrowserDoubleClickConfig> = doubleClick

    /**
     * Returns the raw JSON value of [fileUpload].
     *
     * Unlike [fileUpload], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("file_upload")
    @ExcludeMissing
    fun _fileUpload(): JsonField<BrowserFileUploadConfig> = fileUpload

    /**
     * Returns the raw JSON value of [find].
     *
     * Unlike [find], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("find") @ExcludeMissing fun _find(): JsonField<BrowserFindConfig> = find

    /**
     * Returns the raw JSON value of [formInput].
     *
     * Unlike [formInput], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("form_input")
    @ExcludeMissing
    fun _formInput(): JsonField<BrowserFormInputConfig> = formInput

    /**
     * Returns the raw JSON value of [getPageText].
     *
     * Unlike [getPageText], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("get_page_text")
    @ExcludeMissing
    fun _getPageText(): JsonField<BrowserGetPageTextConfig> = getPageText

    /**
     * Returns the raw JSON value of [holdKey].
     *
     * Unlike [holdKey], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("hold_key")
    @ExcludeMissing
    fun _holdKey(): JsonField<BrowserHoldKeyConfig> = holdKey

    /**
     * Returns the raw JSON value of [hover].
     *
     * Unlike [hover], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("hover") @ExcludeMissing fun _hover(): JsonField<BrowserHoverConfig> = hover

    /**
     * Returns the raw JSON value of [javascriptExec].
     *
     * Unlike [javascriptExec], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("javascript_exec")
    @ExcludeMissing
    fun _javascriptExec(): JsonField<BrowserJavascriptExecConfig> = javascriptExec

    /**
     * Returns the raw JSON value of [key].
     *
     * Unlike [key], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("key") @ExcludeMissing fun _key(): JsonField<BrowserKeyConfig> = key

    /**
     * Returns the raw JSON value of [leftClick].
     *
     * Unlike [leftClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("left_click")
    @ExcludeMissing
    fun _leftClick(): JsonField<BrowserLeftClickConfig> = leftClick

    /**
     * Returns the raw JSON value of [leftClickDrag].
     *
     * Unlike [leftClickDrag], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("left_click_drag")
    @ExcludeMissing
    fun _leftClickDrag(): JsonField<BrowserLeftClickDragConfig> = leftClickDrag

    /**
     * Returns the raw JSON value of [leftMouseDown].
     *
     * Unlike [leftMouseDown], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("left_mouse_down")
    @ExcludeMissing
    fun _leftMouseDown(): JsonField<BrowserLeftMouseDownConfig> = leftMouseDown

    /**
     * Returns the raw JSON value of [leftMouseUp].
     *
     * Unlike [leftMouseUp], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("left_mouse_up")
    @ExcludeMissing
    fun _leftMouseUp(): JsonField<BrowserLeftMouseUpConfig> = leftMouseUp

    /**
     * Returns the raw JSON value of [listTabs].
     *
     * Unlike [listTabs], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("list_tabs")
    @ExcludeMissing
    fun _listTabs(): JsonField<BrowserListTabsConfig> = listTabs

    /**
     * Returns the raw JSON value of [middleClick].
     *
     * Unlike [middleClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("middle_click")
    @ExcludeMissing
    fun _middleClick(): JsonField<BrowserMiddleClickConfig> = middleClick

    /**
     * Returns the raw JSON value of [mouseMove].
     *
     * Unlike [mouseMove], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mouse_move")
    @ExcludeMissing
    fun _mouseMove(): JsonField<BrowserMouseMoveConfig> = mouseMove

    /**
     * Returns the raw JSON value of [navigate].
     *
     * Unlike [navigate], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("navigate")
    @ExcludeMissing
    fun _navigate(): JsonField<BrowserNavigateConfig> = navigate

    /**
     * Returns the raw JSON value of [newTab].
     *
     * Unlike [newTab], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("new_tab") @ExcludeMissing fun _newTab(): JsonField<BrowserNewTabConfig> = newTab

    /**
     * Returns the raw JSON value of [readConsole].
     *
     * Unlike [readConsole], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("read_console")
    @ExcludeMissing
    fun _readConsole(): JsonField<BrowserReadConsoleConfig> = readConsole

    /**
     * Returns the raw JSON value of [readNetwork].
     *
     * Unlike [readNetwork], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("read_network")
    @ExcludeMissing
    fun _readNetwork(): JsonField<BrowserReadNetworkConfig> = readNetwork

    /**
     * Returns the raw JSON value of [readPage].
     *
     * Unlike [readPage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("read_page")
    @ExcludeMissing
    fun _readPage(): JsonField<BrowserReadPageConfig> = readPage

    /**
     * Returns the raw JSON value of [rightClick].
     *
     * Unlike [rightClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("right_click")
    @ExcludeMissing
    fun _rightClick(): JsonField<BrowserRightClickConfig> = rightClick

    /**
     * Returns the raw JSON value of [screenshot].
     *
     * Unlike [screenshot], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("screenshot")
    @ExcludeMissing
    fun _screenshot(): JsonField<BrowserScreenshotConfig> = screenshot

    /**
     * Returns the raw JSON value of [scroll].
     *
     * Unlike [scroll], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scroll") @ExcludeMissing fun _scroll(): JsonField<BrowserScrollConfig> = scroll

    /**
     * Returns the raw JSON value of [scrollTo].
     *
     * Unlike [scrollTo], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scroll_to")
    @ExcludeMissing
    fun _scrollTo(): JsonField<BrowserScrollToConfig> = scrollTo

    /**
     * Returns the raw JSON value of [switchTab].
     *
     * Unlike [switchTab], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("switch_tab")
    @ExcludeMissing
    fun _switchTab(): JsonField<BrowserSwitchTabConfig> = switchTab

    /**
     * Returns the raw JSON value of [tripleClick].
     *
     * Unlike [tripleClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("triple_click")
    @ExcludeMissing
    fun _tripleClick(): JsonField<BrowserTripleClickConfig> = tripleClick

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<BrowserTypeConfig> = type

    /**
     * Returns the raw JSON value of [wait].
     *
     * Unlike [wait], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("wait") @ExcludeMissing fun _wait(): JsonField<BrowserWaitConfig> = wait

    /**
     * Returns the raw JSON value of [zoom].
     *
     * Unlike [zoom], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("zoom") @ExcludeMissing fun _zoom(): JsonField<BrowserZoomConfig> = zoom

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

        /** Returns a mutable builder for constructing an instance of [BrowserToolsetConfigs]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BrowserToolsetConfigs]. */
    class Builder internal constructor() {

        private var closeTab: JsonField<BrowserCloseTabConfig> = JsonMissing.of()
        private var doubleClick: JsonField<BrowserDoubleClickConfig> = JsonMissing.of()
        private var fileUpload: JsonField<BrowserFileUploadConfig> = JsonMissing.of()
        private var find: JsonField<BrowserFindConfig> = JsonMissing.of()
        private var formInput: JsonField<BrowserFormInputConfig> = JsonMissing.of()
        private var getPageText: JsonField<BrowserGetPageTextConfig> = JsonMissing.of()
        private var holdKey: JsonField<BrowserHoldKeyConfig> = JsonMissing.of()
        private var hover: JsonField<BrowserHoverConfig> = JsonMissing.of()
        private var javascriptExec: JsonField<BrowserJavascriptExecConfig> = JsonMissing.of()
        private var key: JsonField<BrowserKeyConfig> = JsonMissing.of()
        private var leftClick: JsonField<BrowserLeftClickConfig> = JsonMissing.of()
        private var leftClickDrag: JsonField<BrowserLeftClickDragConfig> = JsonMissing.of()
        private var leftMouseDown: JsonField<BrowserLeftMouseDownConfig> = JsonMissing.of()
        private var leftMouseUp: JsonField<BrowserLeftMouseUpConfig> = JsonMissing.of()
        private var listTabs: JsonField<BrowserListTabsConfig> = JsonMissing.of()
        private var middleClick: JsonField<BrowserMiddleClickConfig> = JsonMissing.of()
        private var mouseMove: JsonField<BrowserMouseMoveConfig> = JsonMissing.of()
        private var navigate: JsonField<BrowserNavigateConfig> = JsonMissing.of()
        private var newTab: JsonField<BrowserNewTabConfig> = JsonMissing.of()
        private var readConsole: JsonField<BrowserReadConsoleConfig> = JsonMissing.of()
        private var readNetwork: JsonField<BrowserReadNetworkConfig> = JsonMissing.of()
        private var readPage: JsonField<BrowserReadPageConfig> = JsonMissing.of()
        private var rightClick: JsonField<BrowserRightClickConfig> = JsonMissing.of()
        private var screenshot: JsonField<BrowserScreenshotConfig> = JsonMissing.of()
        private var scroll: JsonField<BrowserScrollConfig> = JsonMissing.of()
        private var scrollTo: JsonField<BrowserScrollToConfig> = JsonMissing.of()
        private var switchTab: JsonField<BrowserSwitchTabConfig> = JsonMissing.of()
        private var tripleClick: JsonField<BrowserTripleClickConfig> = JsonMissing.of()
        private var type: JsonField<BrowserTypeConfig> = JsonMissing.of()
        private var wait: JsonField<BrowserWaitConfig> = JsonMissing.of()
        private var zoom: JsonField<BrowserZoomConfig> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(browserToolsetConfigs: BrowserToolsetConfigs) = apply {
            closeTab = browserToolsetConfigs.closeTab
            doubleClick = browserToolsetConfigs.doubleClick
            fileUpload = browserToolsetConfigs.fileUpload
            find = browserToolsetConfigs.find
            formInput = browserToolsetConfigs.formInput
            getPageText = browserToolsetConfigs.getPageText
            holdKey = browserToolsetConfigs.holdKey
            hover = browserToolsetConfigs.hover
            javascriptExec = browserToolsetConfigs.javascriptExec
            key = browserToolsetConfigs.key
            leftClick = browserToolsetConfigs.leftClick
            leftClickDrag = browserToolsetConfigs.leftClickDrag
            leftMouseDown = browserToolsetConfigs.leftMouseDown
            leftMouseUp = browserToolsetConfigs.leftMouseUp
            listTabs = browserToolsetConfigs.listTabs
            middleClick = browserToolsetConfigs.middleClick
            mouseMove = browserToolsetConfigs.mouseMove
            navigate = browserToolsetConfigs.navigate
            newTab = browserToolsetConfigs.newTab
            readConsole = browserToolsetConfigs.readConsole
            readNetwork = browserToolsetConfigs.readNetwork
            readPage = browserToolsetConfigs.readPage
            rightClick = browserToolsetConfigs.rightClick
            screenshot = browserToolsetConfigs.screenshot
            scroll = browserToolsetConfigs.scroll
            scrollTo = browserToolsetConfigs.scrollTo
            switchTab = browserToolsetConfigs.switchTab
            tripleClick = browserToolsetConfigs.tripleClick
            type = browserToolsetConfigs.type
            wait = browserToolsetConfigs.wait
            zoom = browserToolsetConfigs.zoom
            additionalProperties = browserToolsetConfigs.additionalProperties.toMutableMap()
        }

        /** ``close_tab``'s config overrides. */
        fun closeTab(closeTab: BrowserCloseTabConfig?) = closeTab(JsonField.ofNullable(closeTab))

        /** Alias for calling [Builder.closeTab] with `closeTab.orElse(null)`. */
        fun closeTab(closeTab: Optional<BrowserCloseTabConfig>) = closeTab(closeTab.getOrNull())

        /**
         * Sets [Builder.closeTab] to an arbitrary JSON value.
         *
         * You should usually call [Builder.closeTab] with a well-typed [BrowserCloseTabConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun closeTab(closeTab: JsonField<BrowserCloseTabConfig>) = apply {
            this.closeTab = closeTab
        }

        /** ``double_click``'s config overrides. */
        fun doubleClick(doubleClick: BrowserDoubleClickConfig?) =
            doubleClick(JsonField.ofNullable(doubleClick))

        /** Alias for calling [Builder.doubleClick] with `doubleClick.orElse(null)`. */
        fun doubleClick(doubleClick: Optional<BrowserDoubleClickConfig>) =
            doubleClick(doubleClick.getOrNull())

        /**
         * Sets [Builder.doubleClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.doubleClick] with a well-typed
         * [BrowserDoubleClickConfig] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun doubleClick(doubleClick: JsonField<BrowserDoubleClickConfig>) = apply {
            this.doubleClick = doubleClick
        }

        /** ``file_upload``'s config overrides. */
        fun fileUpload(fileUpload: BrowserFileUploadConfig?) =
            fileUpload(JsonField.ofNullable(fileUpload))

        /** Alias for calling [Builder.fileUpload] with `fileUpload.orElse(null)`. */
        fun fileUpload(fileUpload: Optional<BrowserFileUploadConfig>) =
            fileUpload(fileUpload.getOrNull())

        /**
         * Sets [Builder.fileUpload] to an arbitrary JSON value.
         *
         * You should usually call [Builder.fileUpload] with a well-typed [BrowserFileUploadConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun fileUpload(fileUpload: JsonField<BrowserFileUploadConfig>) = apply {
            this.fileUpload = fileUpload
        }

        /** ``find``'s config overrides. */
        fun find(find: BrowserFindConfig?) = find(JsonField.ofNullable(find))

        /** Alias for calling [Builder.find] with `find.orElse(null)`. */
        fun find(find: Optional<BrowserFindConfig>) = find(find.getOrNull())

        /**
         * Sets [Builder.find] to an arbitrary JSON value.
         *
         * You should usually call [Builder.find] with a well-typed [BrowserFindConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun find(find: JsonField<BrowserFindConfig>) = apply { this.find = find }

        /** ``form_input``'s config overrides. */
        fun formInput(formInput: BrowserFormInputConfig?) =
            formInput(JsonField.ofNullable(formInput))

        /** Alias for calling [Builder.formInput] with `formInput.orElse(null)`. */
        fun formInput(formInput: Optional<BrowserFormInputConfig>) =
            formInput(formInput.getOrNull())

        /**
         * Sets [Builder.formInput] to an arbitrary JSON value.
         *
         * You should usually call [Builder.formInput] with a well-typed [BrowserFormInputConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun formInput(formInput: JsonField<BrowserFormInputConfig>) = apply {
            this.formInput = formInput
        }

        /** ``get_page_text``'s config overrides. */
        fun getPageText(getPageText: BrowserGetPageTextConfig?) =
            getPageText(JsonField.ofNullable(getPageText))

        /** Alias for calling [Builder.getPageText] with `getPageText.orElse(null)`. */
        fun getPageText(getPageText: Optional<BrowserGetPageTextConfig>) =
            getPageText(getPageText.getOrNull())

        /**
         * Sets [Builder.getPageText] to an arbitrary JSON value.
         *
         * You should usually call [Builder.getPageText] with a well-typed
         * [BrowserGetPageTextConfig] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun getPageText(getPageText: JsonField<BrowserGetPageTextConfig>) = apply {
            this.getPageText = getPageText
        }

        /** ``hold_key``'s config overrides. */
        fun holdKey(holdKey: BrowserHoldKeyConfig?) = holdKey(JsonField.ofNullable(holdKey))

        /** Alias for calling [Builder.holdKey] with `holdKey.orElse(null)`. */
        fun holdKey(holdKey: Optional<BrowserHoldKeyConfig>) = holdKey(holdKey.getOrNull())

        /**
         * Sets [Builder.holdKey] to an arbitrary JSON value.
         *
         * You should usually call [Builder.holdKey] with a well-typed [BrowserHoldKeyConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun holdKey(holdKey: JsonField<BrowserHoldKeyConfig>) = apply { this.holdKey = holdKey }

        /** ``hover``'s config overrides. */
        fun hover(hover: BrowserHoverConfig?) = hover(JsonField.ofNullable(hover))

        /** Alias for calling [Builder.hover] with `hover.orElse(null)`. */
        fun hover(hover: Optional<BrowserHoverConfig>) = hover(hover.getOrNull())

        /**
         * Sets [Builder.hover] to an arbitrary JSON value.
         *
         * You should usually call [Builder.hover] with a well-typed [BrowserHoverConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun hover(hover: JsonField<BrowserHoverConfig>) = apply { this.hover = hover }

        /** ``javascript_exec``'s config overrides. */
        fun javascriptExec(javascriptExec: BrowserJavascriptExecConfig?) =
            javascriptExec(JsonField.ofNullable(javascriptExec))

        /** Alias for calling [Builder.javascriptExec] with `javascriptExec.orElse(null)`. */
        fun javascriptExec(javascriptExec: Optional<BrowserJavascriptExecConfig>) =
            javascriptExec(javascriptExec.getOrNull())

        /**
         * Sets [Builder.javascriptExec] to an arbitrary JSON value.
         *
         * You should usually call [Builder.javascriptExec] with a well-typed
         * [BrowserJavascriptExecConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun javascriptExec(javascriptExec: JsonField<BrowserJavascriptExecConfig>) = apply {
            this.javascriptExec = javascriptExec
        }

        /** ``key``'s config overrides. */
        fun key(key: BrowserKeyConfig?) = key(JsonField.ofNullable(key))

        /** Alias for calling [Builder.key] with `key.orElse(null)`. */
        fun key(key: Optional<BrowserKeyConfig>) = key(key.getOrNull())

        /**
         * Sets [Builder.key] to an arbitrary JSON value.
         *
         * You should usually call [Builder.key] with a well-typed [BrowserKeyConfig] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun key(key: JsonField<BrowserKeyConfig>) = apply { this.key = key }

        /** ``left_click``'s config overrides. */
        fun leftClick(leftClick: BrowserLeftClickConfig?) =
            leftClick(JsonField.ofNullable(leftClick))

        /** Alias for calling [Builder.leftClick] with `leftClick.orElse(null)`. */
        fun leftClick(leftClick: Optional<BrowserLeftClickConfig>) =
            leftClick(leftClick.getOrNull())

        /**
         * Sets [Builder.leftClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.leftClick] with a well-typed [BrowserLeftClickConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun leftClick(leftClick: JsonField<BrowserLeftClickConfig>) = apply {
            this.leftClick = leftClick
        }

        /** ``left_click_drag``'s config overrides. */
        fun leftClickDrag(leftClickDrag: BrowserLeftClickDragConfig?) =
            leftClickDrag(JsonField.ofNullable(leftClickDrag))

        /** Alias for calling [Builder.leftClickDrag] with `leftClickDrag.orElse(null)`. */
        fun leftClickDrag(leftClickDrag: Optional<BrowserLeftClickDragConfig>) =
            leftClickDrag(leftClickDrag.getOrNull())

        /**
         * Sets [Builder.leftClickDrag] to an arbitrary JSON value.
         *
         * You should usually call [Builder.leftClickDrag] with a well-typed
         * [BrowserLeftClickDragConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun leftClickDrag(leftClickDrag: JsonField<BrowserLeftClickDragConfig>) = apply {
            this.leftClickDrag = leftClickDrag
        }

        /** ``left_mouse_down``'s config overrides. */
        fun leftMouseDown(leftMouseDown: BrowserLeftMouseDownConfig?) =
            leftMouseDown(JsonField.ofNullable(leftMouseDown))

        /** Alias for calling [Builder.leftMouseDown] with `leftMouseDown.orElse(null)`. */
        fun leftMouseDown(leftMouseDown: Optional<BrowserLeftMouseDownConfig>) =
            leftMouseDown(leftMouseDown.getOrNull())

        /**
         * Sets [Builder.leftMouseDown] to an arbitrary JSON value.
         *
         * You should usually call [Builder.leftMouseDown] with a well-typed
         * [BrowserLeftMouseDownConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun leftMouseDown(leftMouseDown: JsonField<BrowserLeftMouseDownConfig>) = apply {
            this.leftMouseDown = leftMouseDown
        }

        /** ``left_mouse_up``'s config overrides. */
        fun leftMouseUp(leftMouseUp: BrowserLeftMouseUpConfig?) =
            leftMouseUp(JsonField.ofNullable(leftMouseUp))

        /** Alias for calling [Builder.leftMouseUp] with `leftMouseUp.orElse(null)`. */
        fun leftMouseUp(leftMouseUp: Optional<BrowserLeftMouseUpConfig>) =
            leftMouseUp(leftMouseUp.getOrNull())

        /**
         * Sets [Builder.leftMouseUp] to an arbitrary JSON value.
         *
         * You should usually call [Builder.leftMouseUp] with a well-typed
         * [BrowserLeftMouseUpConfig] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun leftMouseUp(leftMouseUp: JsonField<BrowserLeftMouseUpConfig>) = apply {
            this.leftMouseUp = leftMouseUp
        }

        /** ``list_tabs``'s config overrides. */
        fun listTabs(listTabs: BrowserListTabsConfig?) = listTabs(JsonField.ofNullable(listTabs))

        /** Alias for calling [Builder.listTabs] with `listTabs.orElse(null)`. */
        fun listTabs(listTabs: Optional<BrowserListTabsConfig>) = listTabs(listTabs.getOrNull())

        /**
         * Sets [Builder.listTabs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.listTabs] with a well-typed [BrowserListTabsConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun listTabs(listTabs: JsonField<BrowserListTabsConfig>) = apply {
            this.listTabs = listTabs
        }

        /** ``middle_click``'s config overrides. */
        fun middleClick(middleClick: BrowserMiddleClickConfig?) =
            middleClick(JsonField.ofNullable(middleClick))

        /** Alias for calling [Builder.middleClick] with `middleClick.orElse(null)`. */
        fun middleClick(middleClick: Optional<BrowserMiddleClickConfig>) =
            middleClick(middleClick.getOrNull())

        /**
         * Sets [Builder.middleClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.middleClick] with a well-typed
         * [BrowserMiddleClickConfig] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun middleClick(middleClick: JsonField<BrowserMiddleClickConfig>) = apply {
            this.middleClick = middleClick
        }

        /** ``mouse_move``'s config overrides. */
        fun mouseMove(mouseMove: BrowserMouseMoveConfig?) =
            mouseMove(JsonField.ofNullable(mouseMove))

        /** Alias for calling [Builder.mouseMove] with `mouseMove.orElse(null)`. */
        fun mouseMove(mouseMove: Optional<BrowserMouseMoveConfig>) =
            mouseMove(mouseMove.getOrNull())

        /**
         * Sets [Builder.mouseMove] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mouseMove] with a well-typed [BrowserMouseMoveConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun mouseMove(mouseMove: JsonField<BrowserMouseMoveConfig>) = apply {
            this.mouseMove = mouseMove
        }

        /** ``navigate``'s config overrides. */
        fun navigate(navigate: BrowserNavigateConfig?) = navigate(JsonField.ofNullable(navigate))

        /** Alias for calling [Builder.navigate] with `navigate.orElse(null)`. */
        fun navigate(navigate: Optional<BrowserNavigateConfig>) = navigate(navigate.getOrNull())

        /**
         * Sets [Builder.navigate] to an arbitrary JSON value.
         *
         * You should usually call [Builder.navigate] with a well-typed [BrowserNavigateConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun navigate(navigate: JsonField<BrowserNavigateConfig>) = apply {
            this.navigate = navigate
        }

        /** ``new_tab``'s config overrides. */
        fun newTab(newTab: BrowserNewTabConfig?) = newTab(JsonField.ofNullable(newTab))

        /** Alias for calling [Builder.newTab] with `newTab.orElse(null)`. */
        fun newTab(newTab: Optional<BrowserNewTabConfig>) = newTab(newTab.getOrNull())

        /**
         * Sets [Builder.newTab] to an arbitrary JSON value.
         *
         * You should usually call [Builder.newTab] with a well-typed [BrowserNewTabConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun newTab(newTab: JsonField<BrowserNewTabConfig>) = apply { this.newTab = newTab }

        /** ``read_console``'s config overrides. */
        fun readConsole(readConsole: BrowserReadConsoleConfig?) =
            readConsole(JsonField.ofNullable(readConsole))

        /** Alias for calling [Builder.readConsole] with `readConsole.orElse(null)`. */
        fun readConsole(readConsole: Optional<BrowserReadConsoleConfig>) =
            readConsole(readConsole.getOrNull())

        /**
         * Sets [Builder.readConsole] to an arbitrary JSON value.
         *
         * You should usually call [Builder.readConsole] with a well-typed
         * [BrowserReadConsoleConfig] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun readConsole(readConsole: JsonField<BrowserReadConsoleConfig>) = apply {
            this.readConsole = readConsole
        }

        /** ``read_network``'s config overrides. */
        fun readNetwork(readNetwork: BrowserReadNetworkConfig?) =
            readNetwork(JsonField.ofNullable(readNetwork))

        /** Alias for calling [Builder.readNetwork] with `readNetwork.orElse(null)`. */
        fun readNetwork(readNetwork: Optional<BrowserReadNetworkConfig>) =
            readNetwork(readNetwork.getOrNull())

        /**
         * Sets [Builder.readNetwork] to an arbitrary JSON value.
         *
         * You should usually call [Builder.readNetwork] with a well-typed
         * [BrowserReadNetworkConfig] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun readNetwork(readNetwork: JsonField<BrowserReadNetworkConfig>) = apply {
            this.readNetwork = readNetwork
        }

        /** ``read_page``'s config overrides. */
        fun readPage(readPage: BrowserReadPageConfig?) = readPage(JsonField.ofNullable(readPage))

        /** Alias for calling [Builder.readPage] with `readPage.orElse(null)`. */
        fun readPage(readPage: Optional<BrowserReadPageConfig>) = readPage(readPage.getOrNull())

        /**
         * Sets [Builder.readPage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.readPage] with a well-typed [BrowserReadPageConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun readPage(readPage: JsonField<BrowserReadPageConfig>) = apply {
            this.readPage = readPage
        }

        /** ``right_click``'s config overrides. */
        fun rightClick(rightClick: BrowserRightClickConfig?) =
            rightClick(JsonField.ofNullable(rightClick))

        /** Alias for calling [Builder.rightClick] with `rightClick.orElse(null)`. */
        fun rightClick(rightClick: Optional<BrowserRightClickConfig>) =
            rightClick(rightClick.getOrNull())

        /**
         * Sets [Builder.rightClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rightClick] with a well-typed [BrowserRightClickConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun rightClick(rightClick: JsonField<BrowserRightClickConfig>) = apply {
            this.rightClick = rightClick
        }

        /** ``screenshot``'s config overrides. */
        fun screenshot(screenshot: BrowserScreenshotConfig?) =
            screenshot(JsonField.ofNullable(screenshot))

        /** Alias for calling [Builder.screenshot] with `screenshot.orElse(null)`. */
        fun screenshot(screenshot: Optional<BrowserScreenshotConfig>) =
            screenshot(screenshot.getOrNull())

        /**
         * Sets [Builder.screenshot] to an arbitrary JSON value.
         *
         * You should usually call [Builder.screenshot] with a well-typed [BrowserScreenshotConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun screenshot(screenshot: JsonField<BrowserScreenshotConfig>) = apply {
            this.screenshot = screenshot
        }

        /** ``scroll``'s config overrides. */
        fun scroll(scroll: BrowserScrollConfig?) = scroll(JsonField.ofNullable(scroll))

        /** Alias for calling [Builder.scroll] with `scroll.orElse(null)`. */
        fun scroll(scroll: Optional<BrowserScrollConfig>) = scroll(scroll.getOrNull())

        /**
         * Sets [Builder.scroll] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scroll] with a well-typed [BrowserScrollConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun scroll(scroll: JsonField<BrowserScrollConfig>) = apply { this.scroll = scroll }

        /** ``scroll_to``'s config overrides. */
        fun scrollTo(scrollTo: BrowserScrollToConfig?) = scrollTo(JsonField.ofNullable(scrollTo))

        /** Alias for calling [Builder.scrollTo] with `scrollTo.orElse(null)`. */
        fun scrollTo(scrollTo: Optional<BrowserScrollToConfig>) = scrollTo(scrollTo.getOrNull())

        /**
         * Sets [Builder.scrollTo] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scrollTo] with a well-typed [BrowserScrollToConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun scrollTo(scrollTo: JsonField<BrowserScrollToConfig>) = apply {
            this.scrollTo = scrollTo
        }

        /** ``switch_tab``'s config overrides. */
        fun switchTab(switchTab: BrowserSwitchTabConfig?) =
            switchTab(JsonField.ofNullable(switchTab))

        /** Alias for calling [Builder.switchTab] with `switchTab.orElse(null)`. */
        fun switchTab(switchTab: Optional<BrowserSwitchTabConfig>) =
            switchTab(switchTab.getOrNull())

        /**
         * Sets [Builder.switchTab] to an arbitrary JSON value.
         *
         * You should usually call [Builder.switchTab] with a well-typed [BrowserSwitchTabConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun switchTab(switchTab: JsonField<BrowserSwitchTabConfig>) = apply {
            this.switchTab = switchTab
        }

        /** ``triple_click``'s config overrides. */
        fun tripleClick(tripleClick: BrowserTripleClickConfig?) =
            tripleClick(JsonField.ofNullable(tripleClick))

        /** Alias for calling [Builder.tripleClick] with `tripleClick.orElse(null)`. */
        fun tripleClick(tripleClick: Optional<BrowserTripleClickConfig>) =
            tripleClick(tripleClick.getOrNull())

        /**
         * Sets [Builder.tripleClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tripleClick] with a well-typed
         * [BrowserTripleClickConfig] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun tripleClick(tripleClick: JsonField<BrowserTripleClickConfig>) = apply {
            this.tripleClick = tripleClick
        }

        /** ``type``'s config overrides. */
        fun type(type: BrowserTypeConfig?) = type(JsonField.ofNullable(type))

        /** Alias for calling [Builder.type] with `type.orElse(null)`. */
        fun type(type: Optional<BrowserTypeConfig>) = type(type.getOrNull())

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [BrowserTypeConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun type(type: JsonField<BrowserTypeConfig>) = apply { this.type = type }

        /** ``wait``'s config overrides. */
        fun wait(wait: BrowserWaitConfig?) = wait(JsonField.ofNullable(wait))

        /** Alias for calling [Builder.wait] with `wait.orElse(null)`. */
        fun wait(wait: Optional<BrowserWaitConfig>) = wait(wait.getOrNull())

        /**
         * Sets [Builder.wait] to an arbitrary JSON value.
         *
         * You should usually call [Builder.wait] with a well-typed [BrowserWaitConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun wait(wait: JsonField<BrowserWaitConfig>) = apply { this.wait = wait }

        /** ``zoom``'s config overrides. */
        fun zoom(zoom: BrowserZoomConfig?) = zoom(JsonField.ofNullable(zoom))

        /** Alias for calling [Builder.zoom] with `zoom.orElse(null)`. */
        fun zoom(zoom: Optional<BrowserZoomConfig>) = zoom(zoom.getOrNull())

        /**
         * Sets [Builder.zoom] to an arbitrary JSON value.
         *
         * You should usually call [Builder.zoom] with a well-typed [BrowserZoomConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun zoom(zoom: JsonField<BrowserZoomConfig>) = apply { this.zoom = zoom }

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
         * Returns an immutable instance of [BrowserToolsetConfigs].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BrowserToolsetConfigs =
            BrowserToolsetConfigs(
                closeTab,
                doubleClick,
                fileUpload,
                find,
                formInput,
                getPageText,
                holdKey,
                hover,
                javascriptExec,
                key,
                leftClick,
                leftClickDrag,
                leftMouseDown,
                leftMouseUp,
                listTabs,
                middleClick,
                mouseMove,
                navigate,
                newTab,
                readConsole,
                readNetwork,
                readPage,
                rightClick,
                screenshot,
                scroll,
                scrollTo,
                switchTab,
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
    fun validate(): BrowserToolsetConfigs = apply {
        if (validated) {
            return@apply
        }

        closeTab().ifPresent { it.validate() }
        doubleClick().ifPresent { it.validate() }
        fileUpload().ifPresent { it.validate() }
        find().ifPresent { it.validate() }
        formInput().ifPresent { it.validate() }
        getPageText().ifPresent { it.validate() }
        holdKey().ifPresent { it.validate() }
        hover().ifPresent { it.validate() }
        javascriptExec().ifPresent { it.validate() }
        key().ifPresent { it.validate() }
        leftClick().ifPresent { it.validate() }
        leftClickDrag().ifPresent { it.validate() }
        leftMouseDown().ifPresent { it.validate() }
        leftMouseUp().ifPresent { it.validate() }
        listTabs().ifPresent { it.validate() }
        middleClick().ifPresent { it.validate() }
        mouseMove().ifPresent { it.validate() }
        navigate().ifPresent { it.validate() }
        newTab().ifPresent { it.validate() }
        readConsole().ifPresent { it.validate() }
        readNetwork().ifPresent { it.validate() }
        readPage().ifPresent { it.validate() }
        rightClick().ifPresent { it.validate() }
        screenshot().ifPresent { it.validate() }
        scroll().ifPresent { it.validate() }
        scrollTo().ifPresent { it.validate() }
        switchTab().ifPresent { it.validate() }
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
        (closeTab.asKnown().getOrNull()?.validity() ?: 0) +
            (doubleClick.asKnown().getOrNull()?.validity() ?: 0) +
            (fileUpload.asKnown().getOrNull()?.validity() ?: 0) +
            (find.asKnown().getOrNull()?.validity() ?: 0) +
            (formInput.asKnown().getOrNull()?.validity() ?: 0) +
            (getPageText.asKnown().getOrNull()?.validity() ?: 0) +
            (holdKey.asKnown().getOrNull()?.validity() ?: 0) +
            (hover.asKnown().getOrNull()?.validity() ?: 0) +
            (javascriptExec.asKnown().getOrNull()?.validity() ?: 0) +
            (key.asKnown().getOrNull()?.validity() ?: 0) +
            (leftClick.asKnown().getOrNull()?.validity() ?: 0) +
            (leftClickDrag.asKnown().getOrNull()?.validity() ?: 0) +
            (leftMouseDown.asKnown().getOrNull()?.validity() ?: 0) +
            (leftMouseUp.asKnown().getOrNull()?.validity() ?: 0) +
            (listTabs.asKnown().getOrNull()?.validity() ?: 0) +
            (middleClick.asKnown().getOrNull()?.validity() ?: 0) +
            (mouseMove.asKnown().getOrNull()?.validity() ?: 0) +
            (navigate.asKnown().getOrNull()?.validity() ?: 0) +
            (newTab.asKnown().getOrNull()?.validity() ?: 0) +
            (readConsole.asKnown().getOrNull()?.validity() ?: 0) +
            (readNetwork.asKnown().getOrNull()?.validity() ?: 0) +
            (readPage.asKnown().getOrNull()?.validity() ?: 0) +
            (rightClick.asKnown().getOrNull()?.validity() ?: 0) +
            (screenshot.asKnown().getOrNull()?.validity() ?: 0) +
            (scroll.asKnown().getOrNull()?.validity() ?: 0) +
            (scrollTo.asKnown().getOrNull()?.validity() ?: 0) +
            (switchTab.asKnown().getOrNull()?.validity() ?: 0) +
            (tripleClick.asKnown().getOrNull()?.validity() ?: 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (wait.asKnown().getOrNull()?.validity() ?: 0) +
            (zoom.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BrowserToolsetConfigs &&
            closeTab == other.closeTab &&
            doubleClick == other.doubleClick &&
            fileUpload == other.fileUpload &&
            find == other.find &&
            formInput == other.formInput &&
            getPageText == other.getPageText &&
            holdKey == other.holdKey &&
            hover == other.hover &&
            javascriptExec == other.javascriptExec &&
            key == other.key &&
            leftClick == other.leftClick &&
            leftClickDrag == other.leftClickDrag &&
            leftMouseDown == other.leftMouseDown &&
            leftMouseUp == other.leftMouseUp &&
            listTabs == other.listTabs &&
            middleClick == other.middleClick &&
            mouseMove == other.mouseMove &&
            navigate == other.navigate &&
            newTab == other.newTab &&
            readConsole == other.readConsole &&
            readNetwork == other.readNetwork &&
            readPage == other.readPage &&
            rightClick == other.rightClick &&
            screenshot == other.screenshot &&
            scroll == other.scroll &&
            scrollTo == other.scrollTo &&
            switchTab == other.switchTab &&
            tripleClick == other.tripleClick &&
            type == other.type &&
            wait == other.wait &&
            zoom == other.zoom &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            closeTab,
            doubleClick,
            fileUpload,
            find,
            formInput,
            getPageText,
            holdKey,
            hover,
            javascriptExec,
            key,
            leftClick,
            leftClickDrag,
            leftMouseDown,
            leftMouseUp,
            listTabs,
            middleClick,
            mouseMove,
            navigate,
            newTab,
            readConsole,
            readNetwork,
            readPage,
            rightClick,
            screenshot,
            scroll,
            scrollTo,
            switchTab,
            tripleClick,
            type,
            wait,
            zoom,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BrowserToolsetConfigs{closeTab=$closeTab, doubleClick=$doubleClick, fileUpload=$fileUpload, find=$find, formInput=$formInput, getPageText=$getPageText, holdKey=$holdKey, hover=$hover, javascriptExec=$javascriptExec, key=$key, leftClick=$leftClick, leftClickDrag=$leftClickDrag, leftMouseDown=$leftMouseDown, leftMouseUp=$leftMouseUp, listTabs=$listTabs, middleClick=$middleClick, mouseMove=$mouseMove, navigate=$navigate, newTab=$newTab, readConsole=$readConsole, readNetwork=$readNetwork, readPage=$readPage, rightClick=$rightClick, screenshot=$screenshot, scroll=$scroll, scrollTo=$scrollTo, switchTab=$switchTab, tripleClick=$tripleClick, type=$type, wait=$wait, zoom=$zoom, additionalProperties=$additionalProperties}"
}
