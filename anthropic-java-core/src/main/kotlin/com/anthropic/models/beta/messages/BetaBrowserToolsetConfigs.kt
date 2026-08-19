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
 * Per-member configuration for ``browser_toolset_20260801``: one optional field per member tool,
 * keyed by the member name — the same name the member's ``tool_use`` blocks carry. Every member is
 * an accepted key, and a member's defaults apply wherever its key is absent. Unknown keys are
 * rejected: the field set is this toolset version's complete member set.
 */
class BetaBrowserToolsetConfigs
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val closeTab: JsonField<BetaBrowserCloseTabConfig>,
    private val doubleClick: JsonField<BetaBrowserDoubleClickConfig>,
    private val fileUpload: JsonField<BetaBrowserFileUploadConfig>,
    private val find: JsonField<BetaBrowserFindConfig>,
    private val formInput: JsonField<BetaBrowserFormInputConfig>,
    private val getPageText: JsonField<BetaBrowserGetPageTextConfig>,
    private val holdKey: JsonField<BetaBrowserHoldKeyConfig>,
    private val hover: JsonField<BetaBrowserHoverConfig>,
    private val javascriptExec: JsonField<BetaBrowserJavascriptExecConfig>,
    private val key: JsonField<BetaBrowserKeyConfig>,
    private val leftClick: JsonField<BetaBrowserLeftClickConfig>,
    private val leftClickDrag: JsonField<BetaBrowserLeftClickDragConfig>,
    private val leftMouseDown: JsonField<BetaBrowserLeftMouseDownConfig>,
    private val leftMouseUp: JsonField<BetaBrowserLeftMouseUpConfig>,
    private val listTabs: JsonField<BetaBrowserListTabsConfig>,
    private val middleClick: JsonField<BetaBrowserMiddleClickConfig>,
    private val mouseMove: JsonField<BetaBrowserMouseMoveConfig>,
    private val navigate: JsonField<BetaBrowserNavigateConfig>,
    private val newTab: JsonField<BetaBrowserNewTabConfig>,
    private val readConsole: JsonField<BetaBrowserReadConsoleConfig>,
    private val readNetwork: JsonField<BetaBrowserReadNetworkConfig>,
    private val readPage: JsonField<BetaBrowserReadPageConfig>,
    private val rightClick: JsonField<BetaBrowserRightClickConfig>,
    private val screenshot: JsonField<BetaBrowserScreenshotConfig>,
    private val scroll: JsonField<BetaBrowserScrollConfig>,
    private val scrollTo: JsonField<BetaBrowserScrollToConfig>,
    private val switchTab: JsonField<BetaBrowserSwitchTabConfig>,
    private val tripleClick: JsonField<BetaBrowserTripleClickConfig>,
    private val type: JsonField<BetaBrowserTypeConfig>,
    private val wait: JsonField<BetaBrowserWaitConfig>,
    private val zoom: JsonField<BetaBrowserZoomConfig>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("close_tab")
        @ExcludeMissing
        closeTab: JsonField<BetaBrowserCloseTabConfig> = JsonMissing.of(),
        @JsonProperty("double_click")
        @ExcludeMissing
        doubleClick: JsonField<BetaBrowserDoubleClickConfig> = JsonMissing.of(),
        @JsonProperty("file_upload")
        @ExcludeMissing
        fileUpload: JsonField<BetaBrowserFileUploadConfig> = JsonMissing.of(),
        @JsonProperty("find")
        @ExcludeMissing
        find: JsonField<BetaBrowserFindConfig> = JsonMissing.of(),
        @JsonProperty("form_input")
        @ExcludeMissing
        formInput: JsonField<BetaBrowserFormInputConfig> = JsonMissing.of(),
        @JsonProperty("get_page_text")
        @ExcludeMissing
        getPageText: JsonField<BetaBrowserGetPageTextConfig> = JsonMissing.of(),
        @JsonProperty("hold_key")
        @ExcludeMissing
        holdKey: JsonField<BetaBrowserHoldKeyConfig> = JsonMissing.of(),
        @JsonProperty("hover")
        @ExcludeMissing
        hover: JsonField<BetaBrowserHoverConfig> = JsonMissing.of(),
        @JsonProperty("javascript_exec")
        @ExcludeMissing
        javascriptExec: JsonField<BetaBrowserJavascriptExecConfig> = JsonMissing.of(),
        @JsonProperty("key")
        @ExcludeMissing
        key: JsonField<BetaBrowserKeyConfig> = JsonMissing.of(),
        @JsonProperty("left_click")
        @ExcludeMissing
        leftClick: JsonField<BetaBrowserLeftClickConfig> = JsonMissing.of(),
        @JsonProperty("left_click_drag")
        @ExcludeMissing
        leftClickDrag: JsonField<BetaBrowserLeftClickDragConfig> = JsonMissing.of(),
        @JsonProperty("left_mouse_down")
        @ExcludeMissing
        leftMouseDown: JsonField<BetaBrowserLeftMouseDownConfig> = JsonMissing.of(),
        @JsonProperty("left_mouse_up")
        @ExcludeMissing
        leftMouseUp: JsonField<BetaBrowserLeftMouseUpConfig> = JsonMissing.of(),
        @JsonProperty("list_tabs")
        @ExcludeMissing
        listTabs: JsonField<BetaBrowserListTabsConfig> = JsonMissing.of(),
        @JsonProperty("middle_click")
        @ExcludeMissing
        middleClick: JsonField<BetaBrowserMiddleClickConfig> = JsonMissing.of(),
        @JsonProperty("mouse_move")
        @ExcludeMissing
        mouseMove: JsonField<BetaBrowserMouseMoveConfig> = JsonMissing.of(),
        @JsonProperty("navigate")
        @ExcludeMissing
        navigate: JsonField<BetaBrowserNavigateConfig> = JsonMissing.of(),
        @JsonProperty("new_tab")
        @ExcludeMissing
        newTab: JsonField<BetaBrowserNewTabConfig> = JsonMissing.of(),
        @JsonProperty("read_console")
        @ExcludeMissing
        readConsole: JsonField<BetaBrowserReadConsoleConfig> = JsonMissing.of(),
        @JsonProperty("read_network")
        @ExcludeMissing
        readNetwork: JsonField<BetaBrowserReadNetworkConfig> = JsonMissing.of(),
        @JsonProperty("read_page")
        @ExcludeMissing
        readPage: JsonField<BetaBrowserReadPageConfig> = JsonMissing.of(),
        @JsonProperty("right_click")
        @ExcludeMissing
        rightClick: JsonField<BetaBrowserRightClickConfig> = JsonMissing.of(),
        @JsonProperty("screenshot")
        @ExcludeMissing
        screenshot: JsonField<BetaBrowserScreenshotConfig> = JsonMissing.of(),
        @JsonProperty("scroll")
        @ExcludeMissing
        scroll: JsonField<BetaBrowserScrollConfig> = JsonMissing.of(),
        @JsonProperty("scroll_to")
        @ExcludeMissing
        scrollTo: JsonField<BetaBrowserScrollToConfig> = JsonMissing.of(),
        @JsonProperty("switch_tab")
        @ExcludeMissing
        switchTab: JsonField<BetaBrowserSwitchTabConfig> = JsonMissing.of(),
        @JsonProperty("triple_click")
        @ExcludeMissing
        tripleClick: JsonField<BetaBrowserTripleClickConfig> = JsonMissing.of(),
        @JsonProperty("type")
        @ExcludeMissing
        type: JsonField<BetaBrowserTypeConfig> = JsonMissing.of(),
        @JsonProperty("wait")
        @ExcludeMissing
        wait: JsonField<BetaBrowserWaitConfig> = JsonMissing.of(),
        @JsonProperty("zoom")
        @ExcludeMissing
        zoom: JsonField<BetaBrowserZoomConfig> = JsonMissing.of(),
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
    fun closeTab(): Optional<BetaBrowserCloseTabConfig> = closeTab.getOptional("close_tab")

    /**
     * ``double_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun doubleClick(): Optional<BetaBrowserDoubleClickConfig> =
        doubleClick.getOptional("double_click")

    /**
     * ``file_upload``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun fileUpload(): Optional<BetaBrowserFileUploadConfig> = fileUpload.getOptional("file_upload")

    /**
     * ``find``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun find(): Optional<BetaBrowserFindConfig> = find.getOptional("find")

    /**
     * ``form_input``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun formInput(): Optional<BetaBrowserFormInputConfig> = formInput.getOptional("form_input")

    /**
     * ``get_page_text``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun getPageText(): Optional<BetaBrowserGetPageTextConfig> =
        getPageText.getOptional("get_page_text")

    /**
     * ``hold_key``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun holdKey(): Optional<BetaBrowserHoldKeyConfig> = holdKey.getOptional("hold_key")

    /**
     * ``hover``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun hover(): Optional<BetaBrowserHoverConfig> = hover.getOptional("hover")

    /**
     * ``javascript_exec``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun javascriptExec(): Optional<BetaBrowserJavascriptExecConfig> =
        javascriptExec.getOptional("javascript_exec")

    /**
     * ``key``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun key(): Optional<BetaBrowserKeyConfig> = key.getOptional("key")

    /**
     * ``left_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun leftClick(): Optional<BetaBrowserLeftClickConfig> = leftClick.getOptional("left_click")

    /**
     * ``left_click_drag``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun leftClickDrag(): Optional<BetaBrowserLeftClickDragConfig> =
        leftClickDrag.getOptional("left_click_drag")

    /**
     * ``left_mouse_down``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun leftMouseDown(): Optional<BetaBrowserLeftMouseDownConfig> =
        leftMouseDown.getOptional("left_mouse_down")

    /**
     * ``left_mouse_up``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun leftMouseUp(): Optional<BetaBrowserLeftMouseUpConfig> =
        leftMouseUp.getOptional("left_mouse_up")

    /**
     * ``list_tabs``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun listTabs(): Optional<BetaBrowserListTabsConfig> = listTabs.getOptional("list_tabs")

    /**
     * ``middle_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun middleClick(): Optional<BetaBrowserMiddleClickConfig> =
        middleClick.getOptional("middle_click")

    /**
     * ``mouse_move``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun mouseMove(): Optional<BetaBrowserMouseMoveConfig> = mouseMove.getOptional("mouse_move")

    /**
     * ``navigate``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun navigate(): Optional<BetaBrowserNavigateConfig> = navigate.getOptional("navigate")

    /**
     * ``new_tab``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun newTab(): Optional<BetaBrowserNewTabConfig> = newTab.getOptional("new_tab")

    /**
     * ``read_console``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun readConsole(): Optional<BetaBrowserReadConsoleConfig> =
        readConsole.getOptional("read_console")

    /**
     * ``read_network``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun readNetwork(): Optional<BetaBrowserReadNetworkConfig> =
        readNetwork.getOptional("read_network")

    /**
     * ``read_page``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun readPage(): Optional<BetaBrowserReadPageConfig> = readPage.getOptional("read_page")

    /**
     * ``right_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun rightClick(): Optional<BetaBrowserRightClickConfig> = rightClick.getOptional("right_click")

    /**
     * ``screenshot``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun screenshot(): Optional<BetaBrowserScreenshotConfig> = screenshot.getOptional("screenshot")

    /**
     * ``scroll``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun scroll(): Optional<BetaBrowserScrollConfig> = scroll.getOptional("scroll")

    /**
     * ``scroll_to``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun scrollTo(): Optional<BetaBrowserScrollToConfig> = scrollTo.getOptional("scroll_to")

    /**
     * ``switch_tab``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun switchTab(): Optional<BetaBrowserSwitchTabConfig> = switchTab.getOptional("switch_tab")

    /**
     * ``triple_click``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun tripleClick(): Optional<BetaBrowserTripleClickConfig> =
        tripleClick.getOptional("triple_click")

    /**
     * ``type``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun type(): Optional<BetaBrowserTypeConfig> = type.getOptional("type")

    /**
     * ``wait``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun wait(): Optional<BetaBrowserWaitConfig> = wait.getOptional("wait")

    /**
     * ``zoom``'s config overrides.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun zoom(): Optional<BetaBrowserZoomConfig> = zoom.getOptional("zoom")

    /**
     * Returns the raw JSON value of [closeTab].
     *
     * Unlike [closeTab], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("close_tab")
    @ExcludeMissing
    fun _closeTab(): JsonField<BetaBrowserCloseTabConfig> = closeTab

    /**
     * Returns the raw JSON value of [doubleClick].
     *
     * Unlike [doubleClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("double_click")
    @ExcludeMissing
    fun _doubleClick(): JsonField<BetaBrowserDoubleClickConfig> = doubleClick

    /**
     * Returns the raw JSON value of [fileUpload].
     *
     * Unlike [fileUpload], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("file_upload")
    @ExcludeMissing
    fun _fileUpload(): JsonField<BetaBrowserFileUploadConfig> = fileUpload

    /**
     * Returns the raw JSON value of [find].
     *
     * Unlike [find], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("find") @ExcludeMissing fun _find(): JsonField<BetaBrowserFindConfig> = find

    /**
     * Returns the raw JSON value of [formInput].
     *
     * Unlike [formInput], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("form_input")
    @ExcludeMissing
    fun _formInput(): JsonField<BetaBrowserFormInputConfig> = formInput

    /**
     * Returns the raw JSON value of [getPageText].
     *
     * Unlike [getPageText], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("get_page_text")
    @ExcludeMissing
    fun _getPageText(): JsonField<BetaBrowserGetPageTextConfig> = getPageText

    /**
     * Returns the raw JSON value of [holdKey].
     *
     * Unlike [holdKey], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("hold_key")
    @ExcludeMissing
    fun _holdKey(): JsonField<BetaBrowserHoldKeyConfig> = holdKey

    /**
     * Returns the raw JSON value of [hover].
     *
     * Unlike [hover], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("hover") @ExcludeMissing fun _hover(): JsonField<BetaBrowserHoverConfig> = hover

    /**
     * Returns the raw JSON value of [javascriptExec].
     *
     * Unlike [javascriptExec], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("javascript_exec")
    @ExcludeMissing
    fun _javascriptExec(): JsonField<BetaBrowserJavascriptExecConfig> = javascriptExec

    /**
     * Returns the raw JSON value of [key].
     *
     * Unlike [key], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("key") @ExcludeMissing fun _key(): JsonField<BetaBrowserKeyConfig> = key

    /**
     * Returns the raw JSON value of [leftClick].
     *
     * Unlike [leftClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("left_click")
    @ExcludeMissing
    fun _leftClick(): JsonField<BetaBrowserLeftClickConfig> = leftClick

    /**
     * Returns the raw JSON value of [leftClickDrag].
     *
     * Unlike [leftClickDrag], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("left_click_drag")
    @ExcludeMissing
    fun _leftClickDrag(): JsonField<BetaBrowserLeftClickDragConfig> = leftClickDrag

    /**
     * Returns the raw JSON value of [leftMouseDown].
     *
     * Unlike [leftMouseDown], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("left_mouse_down")
    @ExcludeMissing
    fun _leftMouseDown(): JsonField<BetaBrowserLeftMouseDownConfig> = leftMouseDown

    /**
     * Returns the raw JSON value of [leftMouseUp].
     *
     * Unlike [leftMouseUp], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("left_mouse_up")
    @ExcludeMissing
    fun _leftMouseUp(): JsonField<BetaBrowserLeftMouseUpConfig> = leftMouseUp

    /**
     * Returns the raw JSON value of [listTabs].
     *
     * Unlike [listTabs], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("list_tabs")
    @ExcludeMissing
    fun _listTabs(): JsonField<BetaBrowserListTabsConfig> = listTabs

    /**
     * Returns the raw JSON value of [middleClick].
     *
     * Unlike [middleClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("middle_click")
    @ExcludeMissing
    fun _middleClick(): JsonField<BetaBrowserMiddleClickConfig> = middleClick

    /**
     * Returns the raw JSON value of [mouseMove].
     *
     * Unlike [mouseMove], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mouse_move")
    @ExcludeMissing
    fun _mouseMove(): JsonField<BetaBrowserMouseMoveConfig> = mouseMove

    /**
     * Returns the raw JSON value of [navigate].
     *
     * Unlike [navigate], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("navigate")
    @ExcludeMissing
    fun _navigate(): JsonField<BetaBrowserNavigateConfig> = navigate

    /**
     * Returns the raw JSON value of [newTab].
     *
     * Unlike [newTab], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("new_tab")
    @ExcludeMissing
    fun _newTab(): JsonField<BetaBrowserNewTabConfig> = newTab

    /**
     * Returns the raw JSON value of [readConsole].
     *
     * Unlike [readConsole], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("read_console")
    @ExcludeMissing
    fun _readConsole(): JsonField<BetaBrowserReadConsoleConfig> = readConsole

    /**
     * Returns the raw JSON value of [readNetwork].
     *
     * Unlike [readNetwork], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("read_network")
    @ExcludeMissing
    fun _readNetwork(): JsonField<BetaBrowserReadNetworkConfig> = readNetwork

    /**
     * Returns the raw JSON value of [readPage].
     *
     * Unlike [readPage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("read_page")
    @ExcludeMissing
    fun _readPage(): JsonField<BetaBrowserReadPageConfig> = readPage

    /**
     * Returns the raw JSON value of [rightClick].
     *
     * Unlike [rightClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("right_click")
    @ExcludeMissing
    fun _rightClick(): JsonField<BetaBrowserRightClickConfig> = rightClick

    /**
     * Returns the raw JSON value of [screenshot].
     *
     * Unlike [screenshot], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("screenshot")
    @ExcludeMissing
    fun _screenshot(): JsonField<BetaBrowserScreenshotConfig> = screenshot

    /**
     * Returns the raw JSON value of [scroll].
     *
     * Unlike [scroll], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scroll")
    @ExcludeMissing
    fun _scroll(): JsonField<BetaBrowserScrollConfig> = scroll

    /**
     * Returns the raw JSON value of [scrollTo].
     *
     * Unlike [scrollTo], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scroll_to")
    @ExcludeMissing
    fun _scrollTo(): JsonField<BetaBrowserScrollToConfig> = scrollTo

    /**
     * Returns the raw JSON value of [switchTab].
     *
     * Unlike [switchTab], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("switch_tab")
    @ExcludeMissing
    fun _switchTab(): JsonField<BetaBrowserSwitchTabConfig> = switchTab

    /**
     * Returns the raw JSON value of [tripleClick].
     *
     * Unlike [tripleClick], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("triple_click")
    @ExcludeMissing
    fun _tripleClick(): JsonField<BetaBrowserTripleClickConfig> = tripleClick

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<BetaBrowserTypeConfig> = type

    /**
     * Returns the raw JSON value of [wait].
     *
     * Unlike [wait], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("wait") @ExcludeMissing fun _wait(): JsonField<BetaBrowserWaitConfig> = wait

    /**
     * Returns the raw JSON value of [zoom].
     *
     * Unlike [zoom], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("zoom") @ExcludeMissing fun _zoom(): JsonField<BetaBrowserZoomConfig> = zoom

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
         * Returns a mutable builder for constructing an instance of [BetaBrowserToolsetConfigs].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaBrowserToolsetConfigs]. */
    class Builder internal constructor() {

        private var closeTab: JsonField<BetaBrowserCloseTabConfig> = JsonMissing.of()
        private var doubleClick: JsonField<BetaBrowserDoubleClickConfig> = JsonMissing.of()
        private var fileUpload: JsonField<BetaBrowserFileUploadConfig> = JsonMissing.of()
        private var find: JsonField<BetaBrowserFindConfig> = JsonMissing.of()
        private var formInput: JsonField<BetaBrowserFormInputConfig> = JsonMissing.of()
        private var getPageText: JsonField<BetaBrowserGetPageTextConfig> = JsonMissing.of()
        private var holdKey: JsonField<BetaBrowserHoldKeyConfig> = JsonMissing.of()
        private var hover: JsonField<BetaBrowserHoverConfig> = JsonMissing.of()
        private var javascriptExec: JsonField<BetaBrowserJavascriptExecConfig> = JsonMissing.of()
        private var key: JsonField<BetaBrowserKeyConfig> = JsonMissing.of()
        private var leftClick: JsonField<BetaBrowserLeftClickConfig> = JsonMissing.of()
        private var leftClickDrag: JsonField<BetaBrowserLeftClickDragConfig> = JsonMissing.of()
        private var leftMouseDown: JsonField<BetaBrowserLeftMouseDownConfig> = JsonMissing.of()
        private var leftMouseUp: JsonField<BetaBrowserLeftMouseUpConfig> = JsonMissing.of()
        private var listTabs: JsonField<BetaBrowserListTabsConfig> = JsonMissing.of()
        private var middleClick: JsonField<BetaBrowserMiddleClickConfig> = JsonMissing.of()
        private var mouseMove: JsonField<BetaBrowserMouseMoveConfig> = JsonMissing.of()
        private var navigate: JsonField<BetaBrowserNavigateConfig> = JsonMissing.of()
        private var newTab: JsonField<BetaBrowserNewTabConfig> = JsonMissing.of()
        private var readConsole: JsonField<BetaBrowserReadConsoleConfig> = JsonMissing.of()
        private var readNetwork: JsonField<BetaBrowserReadNetworkConfig> = JsonMissing.of()
        private var readPage: JsonField<BetaBrowserReadPageConfig> = JsonMissing.of()
        private var rightClick: JsonField<BetaBrowserRightClickConfig> = JsonMissing.of()
        private var screenshot: JsonField<BetaBrowserScreenshotConfig> = JsonMissing.of()
        private var scroll: JsonField<BetaBrowserScrollConfig> = JsonMissing.of()
        private var scrollTo: JsonField<BetaBrowserScrollToConfig> = JsonMissing.of()
        private var switchTab: JsonField<BetaBrowserSwitchTabConfig> = JsonMissing.of()
        private var tripleClick: JsonField<BetaBrowserTripleClickConfig> = JsonMissing.of()
        private var type: JsonField<BetaBrowserTypeConfig> = JsonMissing.of()
        private var wait: JsonField<BetaBrowserWaitConfig> = JsonMissing.of()
        private var zoom: JsonField<BetaBrowserZoomConfig> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaBrowserToolsetConfigs: BetaBrowserToolsetConfigs) = apply {
            closeTab = betaBrowserToolsetConfigs.closeTab
            doubleClick = betaBrowserToolsetConfigs.doubleClick
            fileUpload = betaBrowserToolsetConfigs.fileUpload
            find = betaBrowserToolsetConfigs.find
            formInput = betaBrowserToolsetConfigs.formInput
            getPageText = betaBrowserToolsetConfigs.getPageText
            holdKey = betaBrowserToolsetConfigs.holdKey
            hover = betaBrowserToolsetConfigs.hover
            javascriptExec = betaBrowserToolsetConfigs.javascriptExec
            key = betaBrowserToolsetConfigs.key
            leftClick = betaBrowserToolsetConfigs.leftClick
            leftClickDrag = betaBrowserToolsetConfigs.leftClickDrag
            leftMouseDown = betaBrowserToolsetConfigs.leftMouseDown
            leftMouseUp = betaBrowserToolsetConfigs.leftMouseUp
            listTabs = betaBrowserToolsetConfigs.listTabs
            middleClick = betaBrowserToolsetConfigs.middleClick
            mouseMove = betaBrowserToolsetConfigs.mouseMove
            navigate = betaBrowserToolsetConfigs.navigate
            newTab = betaBrowserToolsetConfigs.newTab
            readConsole = betaBrowserToolsetConfigs.readConsole
            readNetwork = betaBrowserToolsetConfigs.readNetwork
            readPage = betaBrowserToolsetConfigs.readPage
            rightClick = betaBrowserToolsetConfigs.rightClick
            screenshot = betaBrowserToolsetConfigs.screenshot
            scroll = betaBrowserToolsetConfigs.scroll
            scrollTo = betaBrowserToolsetConfigs.scrollTo
            switchTab = betaBrowserToolsetConfigs.switchTab
            tripleClick = betaBrowserToolsetConfigs.tripleClick
            type = betaBrowserToolsetConfigs.type
            wait = betaBrowserToolsetConfigs.wait
            zoom = betaBrowserToolsetConfigs.zoom
            additionalProperties = betaBrowserToolsetConfigs.additionalProperties.toMutableMap()
        }

        /** ``close_tab``'s config overrides. */
        fun closeTab(closeTab: BetaBrowserCloseTabConfig?) =
            closeTab(JsonField.ofNullable(closeTab))

        /** Alias for calling [Builder.closeTab] with `closeTab.orElse(null)`. */
        fun closeTab(closeTab: Optional<BetaBrowserCloseTabConfig>) = closeTab(closeTab.getOrNull())

        /**
         * Sets [Builder.closeTab] to an arbitrary JSON value.
         *
         * You should usually call [Builder.closeTab] with a well-typed [BetaBrowserCloseTabConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun closeTab(closeTab: JsonField<BetaBrowserCloseTabConfig>) = apply {
            this.closeTab = closeTab
        }

        /** ``double_click``'s config overrides. */
        fun doubleClick(doubleClick: BetaBrowserDoubleClickConfig?) =
            doubleClick(JsonField.ofNullable(doubleClick))

        /** Alias for calling [Builder.doubleClick] with `doubleClick.orElse(null)`. */
        fun doubleClick(doubleClick: Optional<BetaBrowserDoubleClickConfig>) =
            doubleClick(doubleClick.getOrNull())

        /**
         * Sets [Builder.doubleClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.doubleClick] with a well-typed
         * [BetaBrowserDoubleClickConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun doubleClick(doubleClick: JsonField<BetaBrowserDoubleClickConfig>) = apply {
            this.doubleClick = doubleClick
        }

        /** ``file_upload``'s config overrides. */
        fun fileUpload(fileUpload: BetaBrowserFileUploadConfig?) =
            fileUpload(JsonField.ofNullable(fileUpload))

        /** Alias for calling [Builder.fileUpload] with `fileUpload.orElse(null)`. */
        fun fileUpload(fileUpload: Optional<BetaBrowserFileUploadConfig>) =
            fileUpload(fileUpload.getOrNull())

        /**
         * Sets [Builder.fileUpload] to an arbitrary JSON value.
         *
         * You should usually call [Builder.fileUpload] with a well-typed
         * [BetaBrowserFileUploadConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun fileUpload(fileUpload: JsonField<BetaBrowserFileUploadConfig>) = apply {
            this.fileUpload = fileUpload
        }

        /** ``find``'s config overrides. */
        fun find(find: BetaBrowserFindConfig?) = find(JsonField.ofNullable(find))

        /** Alias for calling [Builder.find] with `find.orElse(null)`. */
        fun find(find: Optional<BetaBrowserFindConfig>) = find(find.getOrNull())

        /**
         * Sets [Builder.find] to an arbitrary JSON value.
         *
         * You should usually call [Builder.find] with a well-typed [BetaBrowserFindConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun find(find: JsonField<BetaBrowserFindConfig>) = apply { this.find = find }

        /** ``form_input``'s config overrides. */
        fun formInput(formInput: BetaBrowserFormInputConfig?) =
            formInput(JsonField.ofNullable(formInput))

        /** Alias for calling [Builder.formInput] with `formInput.orElse(null)`. */
        fun formInput(formInput: Optional<BetaBrowserFormInputConfig>) =
            formInput(formInput.getOrNull())

        /**
         * Sets [Builder.formInput] to an arbitrary JSON value.
         *
         * You should usually call [Builder.formInput] with a well-typed
         * [BetaBrowserFormInputConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun formInput(formInput: JsonField<BetaBrowserFormInputConfig>) = apply {
            this.formInput = formInput
        }

        /** ``get_page_text``'s config overrides. */
        fun getPageText(getPageText: BetaBrowserGetPageTextConfig?) =
            getPageText(JsonField.ofNullable(getPageText))

        /** Alias for calling [Builder.getPageText] with `getPageText.orElse(null)`. */
        fun getPageText(getPageText: Optional<BetaBrowserGetPageTextConfig>) =
            getPageText(getPageText.getOrNull())

        /**
         * Sets [Builder.getPageText] to an arbitrary JSON value.
         *
         * You should usually call [Builder.getPageText] with a well-typed
         * [BetaBrowserGetPageTextConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun getPageText(getPageText: JsonField<BetaBrowserGetPageTextConfig>) = apply {
            this.getPageText = getPageText
        }

        /** ``hold_key``'s config overrides. */
        fun holdKey(holdKey: BetaBrowserHoldKeyConfig?) = holdKey(JsonField.ofNullable(holdKey))

        /** Alias for calling [Builder.holdKey] with `holdKey.orElse(null)`. */
        fun holdKey(holdKey: Optional<BetaBrowserHoldKeyConfig>) = holdKey(holdKey.getOrNull())

        /**
         * Sets [Builder.holdKey] to an arbitrary JSON value.
         *
         * You should usually call [Builder.holdKey] with a well-typed [BetaBrowserHoldKeyConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun holdKey(holdKey: JsonField<BetaBrowserHoldKeyConfig>) = apply { this.holdKey = holdKey }

        /** ``hover``'s config overrides. */
        fun hover(hover: BetaBrowserHoverConfig?) = hover(JsonField.ofNullable(hover))

        /** Alias for calling [Builder.hover] with `hover.orElse(null)`. */
        fun hover(hover: Optional<BetaBrowserHoverConfig>) = hover(hover.getOrNull())

        /**
         * Sets [Builder.hover] to an arbitrary JSON value.
         *
         * You should usually call [Builder.hover] with a well-typed [BetaBrowserHoverConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun hover(hover: JsonField<BetaBrowserHoverConfig>) = apply { this.hover = hover }

        /** ``javascript_exec``'s config overrides. */
        fun javascriptExec(javascriptExec: BetaBrowserJavascriptExecConfig?) =
            javascriptExec(JsonField.ofNullable(javascriptExec))

        /** Alias for calling [Builder.javascriptExec] with `javascriptExec.orElse(null)`. */
        fun javascriptExec(javascriptExec: Optional<BetaBrowserJavascriptExecConfig>) =
            javascriptExec(javascriptExec.getOrNull())

        /**
         * Sets [Builder.javascriptExec] to an arbitrary JSON value.
         *
         * You should usually call [Builder.javascriptExec] with a well-typed
         * [BetaBrowserJavascriptExecConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun javascriptExec(javascriptExec: JsonField<BetaBrowserJavascriptExecConfig>) = apply {
            this.javascriptExec = javascriptExec
        }

        /** ``key``'s config overrides. */
        fun key(key: BetaBrowserKeyConfig?) = key(JsonField.ofNullable(key))

        /** Alias for calling [Builder.key] with `key.orElse(null)`. */
        fun key(key: Optional<BetaBrowserKeyConfig>) = key(key.getOrNull())

        /**
         * Sets [Builder.key] to an arbitrary JSON value.
         *
         * You should usually call [Builder.key] with a well-typed [BetaBrowserKeyConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun key(key: JsonField<BetaBrowserKeyConfig>) = apply { this.key = key }

        /** ``left_click``'s config overrides. */
        fun leftClick(leftClick: BetaBrowserLeftClickConfig?) =
            leftClick(JsonField.ofNullable(leftClick))

        /** Alias for calling [Builder.leftClick] with `leftClick.orElse(null)`. */
        fun leftClick(leftClick: Optional<BetaBrowserLeftClickConfig>) =
            leftClick(leftClick.getOrNull())

        /**
         * Sets [Builder.leftClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.leftClick] with a well-typed
         * [BetaBrowserLeftClickConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun leftClick(leftClick: JsonField<BetaBrowserLeftClickConfig>) = apply {
            this.leftClick = leftClick
        }

        /** ``left_click_drag``'s config overrides. */
        fun leftClickDrag(leftClickDrag: BetaBrowserLeftClickDragConfig?) =
            leftClickDrag(JsonField.ofNullable(leftClickDrag))

        /** Alias for calling [Builder.leftClickDrag] with `leftClickDrag.orElse(null)`. */
        fun leftClickDrag(leftClickDrag: Optional<BetaBrowserLeftClickDragConfig>) =
            leftClickDrag(leftClickDrag.getOrNull())

        /**
         * Sets [Builder.leftClickDrag] to an arbitrary JSON value.
         *
         * You should usually call [Builder.leftClickDrag] with a well-typed
         * [BetaBrowserLeftClickDragConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun leftClickDrag(leftClickDrag: JsonField<BetaBrowserLeftClickDragConfig>) = apply {
            this.leftClickDrag = leftClickDrag
        }

        /** ``left_mouse_down``'s config overrides. */
        fun leftMouseDown(leftMouseDown: BetaBrowserLeftMouseDownConfig?) =
            leftMouseDown(JsonField.ofNullable(leftMouseDown))

        /** Alias for calling [Builder.leftMouseDown] with `leftMouseDown.orElse(null)`. */
        fun leftMouseDown(leftMouseDown: Optional<BetaBrowserLeftMouseDownConfig>) =
            leftMouseDown(leftMouseDown.getOrNull())

        /**
         * Sets [Builder.leftMouseDown] to an arbitrary JSON value.
         *
         * You should usually call [Builder.leftMouseDown] with a well-typed
         * [BetaBrowserLeftMouseDownConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun leftMouseDown(leftMouseDown: JsonField<BetaBrowserLeftMouseDownConfig>) = apply {
            this.leftMouseDown = leftMouseDown
        }

        /** ``left_mouse_up``'s config overrides. */
        fun leftMouseUp(leftMouseUp: BetaBrowserLeftMouseUpConfig?) =
            leftMouseUp(JsonField.ofNullable(leftMouseUp))

        /** Alias for calling [Builder.leftMouseUp] with `leftMouseUp.orElse(null)`. */
        fun leftMouseUp(leftMouseUp: Optional<BetaBrowserLeftMouseUpConfig>) =
            leftMouseUp(leftMouseUp.getOrNull())

        /**
         * Sets [Builder.leftMouseUp] to an arbitrary JSON value.
         *
         * You should usually call [Builder.leftMouseUp] with a well-typed
         * [BetaBrowserLeftMouseUpConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun leftMouseUp(leftMouseUp: JsonField<BetaBrowserLeftMouseUpConfig>) = apply {
            this.leftMouseUp = leftMouseUp
        }

        /** ``list_tabs``'s config overrides. */
        fun listTabs(listTabs: BetaBrowserListTabsConfig?) =
            listTabs(JsonField.ofNullable(listTabs))

        /** Alias for calling [Builder.listTabs] with `listTabs.orElse(null)`. */
        fun listTabs(listTabs: Optional<BetaBrowserListTabsConfig>) = listTabs(listTabs.getOrNull())

        /**
         * Sets [Builder.listTabs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.listTabs] with a well-typed [BetaBrowserListTabsConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun listTabs(listTabs: JsonField<BetaBrowserListTabsConfig>) = apply {
            this.listTabs = listTabs
        }

        /** ``middle_click``'s config overrides. */
        fun middleClick(middleClick: BetaBrowserMiddleClickConfig?) =
            middleClick(JsonField.ofNullable(middleClick))

        /** Alias for calling [Builder.middleClick] with `middleClick.orElse(null)`. */
        fun middleClick(middleClick: Optional<BetaBrowserMiddleClickConfig>) =
            middleClick(middleClick.getOrNull())

        /**
         * Sets [Builder.middleClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.middleClick] with a well-typed
         * [BetaBrowserMiddleClickConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun middleClick(middleClick: JsonField<BetaBrowserMiddleClickConfig>) = apply {
            this.middleClick = middleClick
        }

        /** ``mouse_move``'s config overrides. */
        fun mouseMove(mouseMove: BetaBrowserMouseMoveConfig?) =
            mouseMove(JsonField.ofNullable(mouseMove))

        /** Alias for calling [Builder.mouseMove] with `mouseMove.orElse(null)`. */
        fun mouseMove(mouseMove: Optional<BetaBrowserMouseMoveConfig>) =
            mouseMove(mouseMove.getOrNull())

        /**
         * Sets [Builder.mouseMove] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mouseMove] with a well-typed
         * [BetaBrowserMouseMoveConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun mouseMove(mouseMove: JsonField<BetaBrowserMouseMoveConfig>) = apply {
            this.mouseMove = mouseMove
        }

        /** ``navigate``'s config overrides. */
        fun navigate(navigate: BetaBrowserNavigateConfig?) =
            navigate(JsonField.ofNullable(navigate))

        /** Alias for calling [Builder.navigate] with `navigate.orElse(null)`. */
        fun navigate(navigate: Optional<BetaBrowserNavigateConfig>) = navigate(navigate.getOrNull())

        /**
         * Sets [Builder.navigate] to an arbitrary JSON value.
         *
         * You should usually call [Builder.navigate] with a well-typed [BetaBrowserNavigateConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun navigate(navigate: JsonField<BetaBrowserNavigateConfig>) = apply {
            this.navigate = navigate
        }

        /** ``new_tab``'s config overrides. */
        fun newTab(newTab: BetaBrowserNewTabConfig?) = newTab(JsonField.ofNullable(newTab))

        /** Alias for calling [Builder.newTab] with `newTab.orElse(null)`. */
        fun newTab(newTab: Optional<BetaBrowserNewTabConfig>) = newTab(newTab.getOrNull())

        /**
         * Sets [Builder.newTab] to an arbitrary JSON value.
         *
         * You should usually call [Builder.newTab] with a well-typed [BetaBrowserNewTabConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun newTab(newTab: JsonField<BetaBrowserNewTabConfig>) = apply { this.newTab = newTab }

        /** ``read_console``'s config overrides. */
        fun readConsole(readConsole: BetaBrowserReadConsoleConfig?) =
            readConsole(JsonField.ofNullable(readConsole))

        /** Alias for calling [Builder.readConsole] with `readConsole.orElse(null)`. */
        fun readConsole(readConsole: Optional<BetaBrowserReadConsoleConfig>) =
            readConsole(readConsole.getOrNull())

        /**
         * Sets [Builder.readConsole] to an arbitrary JSON value.
         *
         * You should usually call [Builder.readConsole] with a well-typed
         * [BetaBrowserReadConsoleConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun readConsole(readConsole: JsonField<BetaBrowserReadConsoleConfig>) = apply {
            this.readConsole = readConsole
        }

        /** ``read_network``'s config overrides. */
        fun readNetwork(readNetwork: BetaBrowserReadNetworkConfig?) =
            readNetwork(JsonField.ofNullable(readNetwork))

        /** Alias for calling [Builder.readNetwork] with `readNetwork.orElse(null)`. */
        fun readNetwork(readNetwork: Optional<BetaBrowserReadNetworkConfig>) =
            readNetwork(readNetwork.getOrNull())

        /**
         * Sets [Builder.readNetwork] to an arbitrary JSON value.
         *
         * You should usually call [Builder.readNetwork] with a well-typed
         * [BetaBrowserReadNetworkConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun readNetwork(readNetwork: JsonField<BetaBrowserReadNetworkConfig>) = apply {
            this.readNetwork = readNetwork
        }

        /** ``read_page``'s config overrides. */
        fun readPage(readPage: BetaBrowserReadPageConfig?) =
            readPage(JsonField.ofNullable(readPage))

        /** Alias for calling [Builder.readPage] with `readPage.orElse(null)`. */
        fun readPage(readPage: Optional<BetaBrowserReadPageConfig>) = readPage(readPage.getOrNull())

        /**
         * Sets [Builder.readPage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.readPage] with a well-typed [BetaBrowserReadPageConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun readPage(readPage: JsonField<BetaBrowserReadPageConfig>) = apply {
            this.readPage = readPage
        }

        /** ``right_click``'s config overrides. */
        fun rightClick(rightClick: BetaBrowserRightClickConfig?) =
            rightClick(JsonField.ofNullable(rightClick))

        /** Alias for calling [Builder.rightClick] with `rightClick.orElse(null)`. */
        fun rightClick(rightClick: Optional<BetaBrowserRightClickConfig>) =
            rightClick(rightClick.getOrNull())

        /**
         * Sets [Builder.rightClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rightClick] with a well-typed
         * [BetaBrowserRightClickConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun rightClick(rightClick: JsonField<BetaBrowserRightClickConfig>) = apply {
            this.rightClick = rightClick
        }

        /** ``screenshot``'s config overrides. */
        fun screenshot(screenshot: BetaBrowserScreenshotConfig?) =
            screenshot(JsonField.ofNullable(screenshot))

        /** Alias for calling [Builder.screenshot] with `screenshot.orElse(null)`. */
        fun screenshot(screenshot: Optional<BetaBrowserScreenshotConfig>) =
            screenshot(screenshot.getOrNull())

        /**
         * Sets [Builder.screenshot] to an arbitrary JSON value.
         *
         * You should usually call [Builder.screenshot] with a well-typed
         * [BetaBrowserScreenshotConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun screenshot(screenshot: JsonField<BetaBrowserScreenshotConfig>) = apply {
            this.screenshot = screenshot
        }

        /** ``scroll``'s config overrides. */
        fun scroll(scroll: BetaBrowserScrollConfig?) = scroll(JsonField.ofNullable(scroll))

        /** Alias for calling [Builder.scroll] with `scroll.orElse(null)`. */
        fun scroll(scroll: Optional<BetaBrowserScrollConfig>) = scroll(scroll.getOrNull())

        /**
         * Sets [Builder.scroll] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scroll] with a well-typed [BetaBrowserScrollConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun scroll(scroll: JsonField<BetaBrowserScrollConfig>) = apply { this.scroll = scroll }

        /** ``scroll_to``'s config overrides. */
        fun scrollTo(scrollTo: BetaBrowserScrollToConfig?) =
            scrollTo(JsonField.ofNullable(scrollTo))

        /** Alias for calling [Builder.scrollTo] with `scrollTo.orElse(null)`. */
        fun scrollTo(scrollTo: Optional<BetaBrowserScrollToConfig>) = scrollTo(scrollTo.getOrNull())

        /**
         * Sets [Builder.scrollTo] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scrollTo] with a well-typed [BetaBrowserScrollToConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun scrollTo(scrollTo: JsonField<BetaBrowserScrollToConfig>) = apply {
            this.scrollTo = scrollTo
        }

        /** ``switch_tab``'s config overrides. */
        fun switchTab(switchTab: BetaBrowserSwitchTabConfig?) =
            switchTab(JsonField.ofNullable(switchTab))

        /** Alias for calling [Builder.switchTab] with `switchTab.orElse(null)`. */
        fun switchTab(switchTab: Optional<BetaBrowserSwitchTabConfig>) =
            switchTab(switchTab.getOrNull())

        /**
         * Sets [Builder.switchTab] to an arbitrary JSON value.
         *
         * You should usually call [Builder.switchTab] with a well-typed
         * [BetaBrowserSwitchTabConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun switchTab(switchTab: JsonField<BetaBrowserSwitchTabConfig>) = apply {
            this.switchTab = switchTab
        }

        /** ``triple_click``'s config overrides. */
        fun tripleClick(tripleClick: BetaBrowserTripleClickConfig?) =
            tripleClick(JsonField.ofNullable(tripleClick))

        /** Alias for calling [Builder.tripleClick] with `tripleClick.orElse(null)`. */
        fun tripleClick(tripleClick: Optional<BetaBrowserTripleClickConfig>) =
            tripleClick(tripleClick.getOrNull())

        /**
         * Sets [Builder.tripleClick] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tripleClick] with a well-typed
         * [BetaBrowserTripleClickConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun tripleClick(tripleClick: JsonField<BetaBrowserTripleClickConfig>) = apply {
            this.tripleClick = tripleClick
        }

        /** ``type``'s config overrides. */
        fun type(type: BetaBrowserTypeConfig?) = type(JsonField.ofNullable(type))

        /** Alias for calling [Builder.type] with `type.orElse(null)`. */
        fun type(type: Optional<BetaBrowserTypeConfig>) = type(type.getOrNull())

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [BetaBrowserTypeConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun type(type: JsonField<BetaBrowserTypeConfig>) = apply { this.type = type }

        /** ``wait``'s config overrides. */
        fun wait(wait: BetaBrowserWaitConfig?) = wait(JsonField.ofNullable(wait))

        /** Alias for calling [Builder.wait] with `wait.orElse(null)`. */
        fun wait(wait: Optional<BetaBrowserWaitConfig>) = wait(wait.getOrNull())

        /**
         * Sets [Builder.wait] to an arbitrary JSON value.
         *
         * You should usually call [Builder.wait] with a well-typed [BetaBrowserWaitConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun wait(wait: JsonField<BetaBrowserWaitConfig>) = apply { this.wait = wait }

        /** ``zoom``'s config overrides. */
        fun zoom(zoom: BetaBrowserZoomConfig?) = zoom(JsonField.ofNullable(zoom))

        /** Alias for calling [Builder.zoom] with `zoom.orElse(null)`. */
        fun zoom(zoom: Optional<BetaBrowserZoomConfig>) = zoom(zoom.getOrNull())

        /**
         * Sets [Builder.zoom] to an arbitrary JSON value.
         *
         * You should usually call [Builder.zoom] with a well-typed [BetaBrowserZoomConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun zoom(zoom: JsonField<BetaBrowserZoomConfig>) = apply { this.zoom = zoom }

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
         * Returns an immutable instance of [BetaBrowserToolsetConfigs].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaBrowserToolsetConfigs =
            BetaBrowserToolsetConfigs(
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
    fun validate(): BetaBrowserToolsetConfigs = apply {
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

        return other is BetaBrowserToolsetConfigs &&
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
        "BetaBrowserToolsetConfigs{closeTab=$closeTab, doubleClick=$doubleClick, fileUpload=$fileUpload, find=$find, formInput=$formInput, getPageText=$getPageText, holdKey=$holdKey, hover=$hover, javascriptExec=$javascriptExec, key=$key, leftClick=$leftClick, leftClickDrag=$leftClickDrag, leftMouseDown=$leftMouseDown, leftMouseUp=$leftMouseUp, listTabs=$listTabs, middleClick=$middleClick, mouseMove=$mouseMove, navigate=$navigate, newTab=$newTab, readConsole=$readConsole, readNetwork=$readNetwork, readPage=$readPage, rightClick=$rightClick, screenshot=$screenshot, scroll=$scroll, scrollTo=$scrollTo, switchTab=$switchTab, tripleClick=$tripleClick, type=$type, wait=$wait, zoom=$zoom, additionalProperties=$additionalProperties}"
}
