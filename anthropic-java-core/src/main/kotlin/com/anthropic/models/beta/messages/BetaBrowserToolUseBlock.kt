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

@JsonDeserialize(using = BetaBrowserToolUseBlock.Deserializer::class)
@JsonSerialize(using = BetaBrowserToolUseBlock.Serializer::class)
class BetaBrowserToolUseBlock
private constructor(
    private val navigate: BetaBrowserNavigateToolUseBlock? = null,
    private val listTabs: BetaBrowserListTabsToolUseBlock? = null,
    private val newTab: BetaBrowserNewTabToolUseBlock? = null,
    private val switchTab: BetaBrowserSwitchTabToolUseBlock? = null,
    private val closeTab: BetaBrowserCloseTabToolUseBlock? = null,
    private val readPage: BetaBrowserReadPageToolUseBlock? = null,
    private val getPageText: BetaBrowserGetPageTextToolUseBlock? = null,
    private val readConsole: BetaBrowserReadConsoleToolUseBlock? = null,
    private val readNetwork: BetaBrowserReadNetworkToolUseBlock? = null,
    private val find: BetaBrowserFindToolUseBlock? = null,
    private val formInput: BetaBrowserFormInputToolUseBlock? = null,
    private val fileUpload: BetaBrowserFileUploadToolUseBlock? = null,
    private val scrollTo: BetaBrowserScrollToToolUseBlock? = null,
    private val screenshot: BetaBrowserScreenshotToolUseBlock? = null,
    private val zoom: BetaBrowserZoomToolUseBlock? = null,
    private val leftClick: BetaBrowserLeftClickToolUseBlock? = null,
    private val rightClick: BetaBrowserRightClickToolUseBlock? = null,
    private val middleClick: BetaBrowserMiddleClickToolUseBlock? = null,
    private val doubleClick: BetaBrowserDoubleClickToolUseBlock? = null,
    private val tripleClick: BetaBrowserTripleClickToolUseBlock? = null,
    private val hover: BetaBrowserHoverToolUseBlock? = null,
    private val leftClickDrag: BetaBrowserLeftClickDragToolUseBlock? = null,
    private val leftMouseDown: BetaBrowserLeftMouseDownToolUseBlock? = null,
    private val leftMouseUp: BetaBrowserLeftMouseUpToolUseBlock? = null,
    private val mouseMove: BetaBrowserMouseMoveToolUseBlock? = null,
    private val scroll: BetaBrowserScrollToolUseBlock? = null,
    private val type: BetaBrowserTypeToolUseBlock? = null,
    private val key: BetaBrowserKeyToolUseBlock? = null,
    private val holdKey: BetaBrowserHoldKeyToolUseBlock? = null,
    private val wait: BetaBrowserWaitToolUseBlock? = null,
    private val javascriptExec: BetaBrowserJavascriptExecToolUseBlock? = null,
    private val _json: JsonValue? = null,
) {

    fun name(): Name =
        when {
            navigate != null -> Name.NAVIGATE
            listTabs != null -> Name.LIST_TABS
            newTab != null -> Name.NEW_TAB
            switchTab != null -> Name.SWITCH_TAB
            closeTab != null -> Name.CLOSE_TAB
            readPage != null -> Name.READ_PAGE
            getPageText != null -> Name.GET_PAGE_TEXT
            readConsole != null -> Name.READ_CONSOLE
            readNetwork != null -> Name.READ_NETWORK
            find != null -> Name.FIND
            formInput != null -> Name.FORM_INPUT
            fileUpload != null -> Name.FILE_UPLOAD
            scrollTo != null -> Name.SCROLL_TO
            screenshot != null -> Name.SCREENSHOT
            zoom != null -> Name.ZOOM
            leftClick != null -> Name.LEFT_CLICK
            rightClick != null -> Name.RIGHT_CLICK
            middleClick != null -> Name.MIDDLE_CLICK
            doubleClick != null -> Name.DOUBLE_CLICK
            tripleClick != null -> Name.TRIPLE_CLICK
            hover != null -> Name.HOVER
            leftClickDrag != null -> Name.LEFT_CLICK_DRAG
            leftMouseDown != null -> Name.LEFT_MOUSE_DOWN
            leftMouseUp != null -> Name.LEFT_MOUSE_UP
            mouseMove != null -> Name.MOUSE_MOVE
            scroll != null -> Name.SCROLL
            type != null -> Name.TYPE
            key != null -> Name.KEY
            holdKey != null -> Name.HOLD_KEY
            wait != null -> Name.WAIT
            javascriptExec != null -> Name.JAVASCRIPT_EXEC
            else -> Name.of(_json?.asObject()?.getOrNull()?.get("name") ?: JsonMissing.of())
        }

    fun id(): String =
        when {
            navigate != null -> navigate.id()
            listTabs != null -> listTabs.id()
            newTab != null -> newTab.id()
            switchTab != null -> switchTab.id()
            closeTab != null -> closeTab.id()
            readPage != null -> readPage.id()
            getPageText != null -> getPageText.id()
            readConsole != null -> readConsole.id()
            readNetwork != null -> readNetwork.id()
            find != null -> find.id()
            formInput != null -> formInput.id()
            fileUpload != null -> fileUpload.id()
            scrollTo != null -> scrollTo.id()
            screenshot != null -> screenshot.id()
            zoom != null -> zoom.id()
            leftClick != null -> leftClick.id()
            rightClick != null -> rightClick.id()
            middleClick != null -> middleClick.id()
            doubleClick != null -> doubleClick.id()
            tripleClick != null -> tripleClick.id()
            hover != null -> hover.id()
            leftClickDrag != null -> leftClickDrag.id()
            leftMouseDown != null -> leftMouseDown.id()
            leftMouseUp != null -> leftMouseUp.id()
            mouseMove != null -> mouseMove.id()
            scroll != null -> scroll.id()
            type != null -> type.id()
            key != null -> key.id()
            holdKey != null -> holdKey.id()
            wait != null -> wait.id()
            javascriptExec != null -> javascriptExec.id()
            else -> _json.getProperty<String>("id").getRequired("id")
        }

    fun caller(): Optional<BetaToolUseCaller> =
        when {
            navigate != null -> navigate.caller()
            listTabs != null -> listTabs.caller()
            newTab != null -> newTab.caller()
            switchTab != null -> switchTab.caller()
            closeTab != null -> closeTab.caller()
            readPage != null -> readPage.caller()
            getPageText != null -> getPageText.caller()
            readConsole != null -> readConsole.caller()
            readNetwork != null -> readNetwork.caller()
            find != null -> find.caller()
            formInput != null -> formInput.caller()
            fileUpload != null -> fileUpload.caller()
            scrollTo != null -> scrollTo.caller()
            screenshot != null -> screenshot.caller()
            zoom != null -> zoom.caller()
            leftClick != null -> leftClick.caller()
            rightClick != null -> rightClick.caller()
            middleClick != null -> middleClick.caller()
            doubleClick != null -> doubleClick.caller()
            tripleClick != null -> tripleClick.caller()
            hover != null -> hover.caller()
            leftClickDrag != null -> leftClickDrag.caller()
            leftMouseDown != null -> leftMouseDown.caller()
            leftMouseUp != null -> leftMouseUp.caller()
            mouseMove != null -> mouseMove.caller()
            scroll != null -> scroll.caller()
            type != null -> type.caller()
            key != null -> key.caller()
            holdKey != null -> holdKey.caller()
            wait != null -> wait.caller()
            javascriptExec != null -> javascriptExec.caller()
            else -> _json.getProperty<BetaToolUseCaller>("caller").asKnown()
        }

    fun navigate(): Optional<BetaBrowserNavigateToolUseBlock> = Optional.ofNullable(navigate)

    fun listTabs(): Optional<BetaBrowserListTabsToolUseBlock> = Optional.ofNullable(listTabs)

    fun newTab(): Optional<BetaBrowserNewTabToolUseBlock> = Optional.ofNullable(newTab)

    fun switchTab(): Optional<BetaBrowserSwitchTabToolUseBlock> = Optional.ofNullable(switchTab)

    fun closeTab(): Optional<BetaBrowserCloseTabToolUseBlock> = Optional.ofNullable(closeTab)

    fun readPage(): Optional<BetaBrowserReadPageToolUseBlock> = Optional.ofNullable(readPage)

    fun getPageText(): Optional<BetaBrowserGetPageTextToolUseBlock> =
        Optional.ofNullable(getPageText)

    fun readConsole(): Optional<BetaBrowserReadConsoleToolUseBlock> =
        Optional.ofNullable(readConsole)

    fun readNetwork(): Optional<BetaBrowserReadNetworkToolUseBlock> =
        Optional.ofNullable(readNetwork)

    fun find(): Optional<BetaBrowserFindToolUseBlock> = Optional.ofNullable(find)

    fun formInput(): Optional<BetaBrowserFormInputToolUseBlock> = Optional.ofNullable(formInput)

    fun fileUpload(): Optional<BetaBrowserFileUploadToolUseBlock> = Optional.ofNullable(fileUpload)

    fun scrollTo(): Optional<BetaBrowserScrollToToolUseBlock> = Optional.ofNullable(scrollTo)

    fun screenshot(): Optional<BetaBrowserScreenshotToolUseBlock> = Optional.ofNullable(screenshot)

    fun zoom(): Optional<BetaBrowserZoomToolUseBlock> = Optional.ofNullable(zoom)

    fun leftClick(): Optional<BetaBrowserLeftClickToolUseBlock> = Optional.ofNullable(leftClick)

    fun rightClick(): Optional<BetaBrowserRightClickToolUseBlock> = Optional.ofNullable(rightClick)

    fun middleClick(): Optional<BetaBrowserMiddleClickToolUseBlock> =
        Optional.ofNullable(middleClick)

    fun doubleClick(): Optional<BetaBrowserDoubleClickToolUseBlock> =
        Optional.ofNullable(doubleClick)

    fun tripleClick(): Optional<BetaBrowserTripleClickToolUseBlock> =
        Optional.ofNullable(tripleClick)

    fun hover(): Optional<BetaBrowserHoverToolUseBlock> = Optional.ofNullable(hover)

    fun leftClickDrag(): Optional<BetaBrowserLeftClickDragToolUseBlock> =
        Optional.ofNullable(leftClickDrag)

    fun leftMouseDown(): Optional<BetaBrowserLeftMouseDownToolUseBlock> =
        Optional.ofNullable(leftMouseDown)

    fun leftMouseUp(): Optional<BetaBrowserLeftMouseUpToolUseBlock> =
        Optional.ofNullable(leftMouseUp)

    fun mouseMove(): Optional<BetaBrowserMouseMoveToolUseBlock> = Optional.ofNullable(mouseMove)

    fun scroll(): Optional<BetaBrowserScrollToolUseBlock> = Optional.ofNullable(scroll)

    fun type(): Optional<BetaBrowserTypeToolUseBlock> = Optional.ofNullable(type)

    fun key(): Optional<BetaBrowserKeyToolUseBlock> = Optional.ofNullable(key)

    fun holdKey(): Optional<BetaBrowserHoldKeyToolUseBlock> = Optional.ofNullable(holdKey)

    fun wait(): Optional<BetaBrowserWaitToolUseBlock> = Optional.ofNullable(wait)

    fun javascriptExec(): Optional<BetaBrowserJavascriptExecToolUseBlock> =
        Optional.ofNullable(javascriptExec)

    fun isNavigate(): Boolean = navigate != null

    fun isListTabs(): Boolean = listTabs != null

    fun isNewTab(): Boolean = newTab != null

    fun isSwitchTab(): Boolean = switchTab != null

    fun isCloseTab(): Boolean = closeTab != null

    fun isReadPage(): Boolean = readPage != null

    fun isGetPageText(): Boolean = getPageText != null

    fun isReadConsole(): Boolean = readConsole != null

    fun isReadNetwork(): Boolean = readNetwork != null

    fun isFind(): Boolean = find != null

    fun isFormInput(): Boolean = formInput != null

    fun isFileUpload(): Boolean = fileUpload != null

    fun isScrollTo(): Boolean = scrollTo != null

    fun isScreenshot(): Boolean = screenshot != null

    fun isZoom(): Boolean = zoom != null

    fun isLeftClick(): Boolean = leftClick != null

    fun isRightClick(): Boolean = rightClick != null

    fun isMiddleClick(): Boolean = middleClick != null

    fun isDoubleClick(): Boolean = doubleClick != null

    fun isTripleClick(): Boolean = tripleClick != null

    fun isHover(): Boolean = hover != null

    fun isLeftClickDrag(): Boolean = leftClickDrag != null

    fun isLeftMouseDown(): Boolean = leftMouseDown != null

    fun isLeftMouseUp(): Boolean = leftMouseUp != null

    fun isMouseMove(): Boolean = mouseMove != null

    fun isScroll(): Boolean = scroll != null

    fun isType(): Boolean = type != null

    fun isKey(): Boolean = key != null

    fun isHoldKey(): Boolean = holdKey != null

    fun isWait(): Boolean = wait != null

    fun isJavascriptExec(): Boolean = javascriptExec != null

    fun asNavigate(): BetaBrowserNavigateToolUseBlock = navigate.getOrThrow("navigate")

    fun asListTabs(): BetaBrowserListTabsToolUseBlock = listTabs.getOrThrow("listTabs")

    fun asNewTab(): BetaBrowserNewTabToolUseBlock = newTab.getOrThrow("newTab")

    fun asSwitchTab(): BetaBrowserSwitchTabToolUseBlock = switchTab.getOrThrow("switchTab")

    fun asCloseTab(): BetaBrowserCloseTabToolUseBlock = closeTab.getOrThrow("closeTab")

    fun asReadPage(): BetaBrowserReadPageToolUseBlock = readPage.getOrThrow("readPage")

    fun asGetPageText(): BetaBrowserGetPageTextToolUseBlock = getPageText.getOrThrow("getPageText")

    fun asReadConsole(): BetaBrowserReadConsoleToolUseBlock = readConsole.getOrThrow("readConsole")

    fun asReadNetwork(): BetaBrowserReadNetworkToolUseBlock = readNetwork.getOrThrow("readNetwork")

    fun asFind(): BetaBrowserFindToolUseBlock = find.getOrThrow("find")

    fun asFormInput(): BetaBrowserFormInputToolUseBlock = formInput.getOrThrow("formInput")

    fun asFileUpload(): BetaBrowserFileUploadToolUseBlock = fileUpload.getOrThrow("fileUpload")

    fun asScrollTo(): BetaBrowserScrollToToolUseBlock = scrollTo.getOrThrow("scrollTo")

    fun asScreenshot(): BetaBrowserScreenshotToolUseBlock = screenshot.getOrThrow("screenshot")

    fun asZoom(): BetaBrowserZoomToolUseBlock = zoom.getOrThrow("zoom")

    fun asLeftClick(): BetaBrowserLeftClickToolUseBlock = leftClick.getOrThrow("leftClick")

    fun asRightClick(): BetaBrowserRightClickToolUseBlock = rightClick.getOrThrow("rightClick")

    fun asMiddleClick(): BetaBrowserMiddleClickToolUseBlock = middleClick.getOrThrow("middleClick")

    fun asDoubleClick(): BetaBrowserDoubleClickToolUseBlock = doubleClick.getOrThrow("doubleClick")

    fun asTripleClick(): BetaBrowserTripleClickToolUseBlock = tripleClick.getOrThrow("tripleClick")

    fun asHover(): BetaBrowserHoverToolUseBlock = hover.getOrThrow("hover")

    fun asLeftClickDrag(): BetaBrowserLeftClickDragToolUseBlock =
        leftClickDrag.getOrThrow("leftClickDrag")

    fun asLeftMouseDown(): BetaBrowserLeftMouseDownToolUseBlock =
        leftMouseDown.getOrThrow("leftMouseDown")

    fun asLeftMouseUp(): BetaBrowserLeftMouseUpToolUseBlock = leftMouseUp.getOrThrow("leftMouseUp")

    fun asMouseMove(): BetaBrowserMouseMoveToolUseBlock = mouseMove.getOrThrow("mouseMove")

    fun asScroll(): BetaBrowserScrollToolUseBlock = scroll.getOrThrow("scroll")

    fun asType(): BetaBrowserTypeToolUseBlock = type.getOrThrow("type")

    fun asKey(): BetaBrowserKeyToolUseBlock = key.getOrThrow("key")

    fun asHoldKey(): BetaBrowserHoldKeyToolUseBlock = holdKey.getOrThrow("holdKey")

    fun asWait(): BetaBrowserWaitToolUseBlock = wait.getOrThrow("wait")

    fun asJavascriptExec(): BetaBrowserJavascriptExecToolUseBlock =
        javascriptExec.getOrThrow("javascriptExec")

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
     * Optional<String> result = betaBrowserToolUseBlock.accept(new BetaBrowserToolUseBlock.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitNavigate(BetaBrowserNavigateToolUseBlock navigate) {
     *         return Optional.of(navigate.toString());
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
            navigate != null -> visitor.visitNavigate(navigate)
            listTabs != null -> visitor.visitListTabs(listTabs)
            newTab != null -> visitor.visitNewTab(newTab)
            switchTab != null -> visitor.visitSwitchTab(switchTab)
            closeTab != null -> visitor.visitCloseTab(closeTab)
            readPage != null -> visitor.visitReadPage(readPage)
            getPageText != null -> visitor.visitGetPageText(getPageText)
            readConsole != null -> visitor.visitReadConsole(readConsole)
            readNetwork != null -> visitor.visitReadNetwork(readNetwork)
            find != null -> visitor.visitFind(find)
            formInput != null -> visitor.visitFormInput(formInput)
            fileUpload != null -> visitor.visitFileUpload(fileUpload)
            scrollTo != null -> visitor.visitScrollTo(scrollTo)
            screenshot != null -> visitor.visitScreenshot(screenshot)
            zoom != null -> visitor.visitZoom(zoom)
            leftClick != null -> visitor.visitLeftClick(leftClick)
            rightClick != null -> visitor.visitRightClick(rightClick)
            middleClick != null -> visitor.visitMiddleClick(middleClick)
            doubleClick != null -> visitor.visitDoubleClick(doubleClick)
            tripleClick != null -> visitor.visitTripleClick(tripleClick)
            hover != null -> visitor.visitHover(hover)
            leftClickDrag != null -> visitor.visitLeftClickDrag(leftClickDrag)
            leftMouseDown != null -> visitor.visitLeftMouseDown(leftMouseDown)
            leftMouseUp != null -> visitor.visitLeftMouseUp(leftMouseUp)
            mouseMove != null -> visitor.visitMouseMove(mouseMove)
            scroll != null -> visitor.visitScroll(scroll)
            type != null -> visitor.visitType(type)
            key != null -> visitor.visitKey(key)
            holdKey != null -> visitor.visitHoldKey(holdKey)
            wait != null -> visitor.visitWait(wait)
            javascriptExec != null -> visitor.visitJavascriptExec(javascriptExec)
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
    fun validate(): BetaBrowserToolUseBlock = apply {
        if (validated) {
            return@apply
        }

        when {
            navigate != null -> navigate.validate()
            listTabs != null -> listTabs.validate()
            newTab != null -> newTab.validate()
            switchTab != null -> switchTab.validate()
            closeTab != null -> closeTab.validate()
            readPage != null -> readPage.validate()
            getPageText != null -> getPageText.validate()
            readConsole != null -> readConsole.validate()
            readNetwork != null -> readNetwork.validate()
            find != null -> find.validate()
            formInput != null -> formInput.validate()
            fileUpload != null -> fileUpload.validate()
            scrollTo != null -> scrollTo.validate()
            screenshot != null -> screenshot.validate()
            zoom != null -> zoom.validate()
            leftClick != null -> leftClick.validate()
            rightClick != null -> rightClick.validate()
            middleClick != null -> middleClick.validate()
            doubleClick != null -> doubleClick.validate()
            tripleClick != null -> tripleClick.validate()
            hover != null -> hover.validate()
            leftClickDrag != null -> leftClickDrag.validate()
            leftMouseDown != null -> leftMouseDown.validate()
            leftMouseUp != null -> leftMouseUp.validate()
            mouseMove != null -> mouseMove.validate()
            scroll != null -> scroll.validate()
            type != null -> type.validate()
            key != null -> key.validate()
            holdKey != null -> holdKey.validate()
            wait != null -> wait.validate()
            javascriptExec != null -> javascriptExec.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaBrowserToolUseBlock: $_json")
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
            navigate != null -> navigate.validity()
            listTabs != null -> listTabs.validity()
            newTab != null -> newTab.validity()
            switchTab != null -> switchTab.validity()
            closeTab != null -> closeTab.validity()
            readPage != null -> readPage.validity()
            getPageText != null -> getPageText.validity()
            readConsole != null -> readConsole.validity()
            readNetwork != null -> readNetwork.validity()
            find != null -> find.validity()
            formInput != null -> formInput.validity()
            fileUpload != null -> fileUpload.validity()
            scrollTo != null -> scrollTo.validity()
            screenshot != null -> screenshot.validity()
            zoom != null -> zoom.validity()
            leftClick != null -> leftClick.validity()
            rightClick != null -> rightClick.validity()
            middleClick != null -> middleClick.validity()
            doubleClick != null -> doubleClick.validity()
            tripleClick != null -> tripleClick.validity()
            hover != null -> hover.validity()
            leftClickDrag != null -> leftClickDrag.validity()
            leftMouseDown != null -> leftMouseDown.validity()
            leftMouseUp != null -> leftMouseUp.validity()
            mouseMove != null -> mouseMove.validity()
            scroll != null -> scroll.validity()
            type != null -> type.validity()
            key != null -> key.validity()
            holdKey != null -> holdKey.validity()
            wait != null -> wait.validity()
            javascriptExec != null -> javascriptExec.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaBrowserToolUseBlock &&
            navigate == other.navigate &&
            listTabs == other.listTabs &&
            newTab == other.newTab &&
            switchTab == other.switchTab &&
            closeTab == other.closeTab &&
            readPage == other.readPage &&
            getPageText == other.getPageText &&
            readConsole == other.readConsole &&
            readNetwork == other.readNetwork &&
            find == other.find &&
            formInput == other.formInput &&
            fileUpload == other.fileUpload &&
            scrollTo == other.scrollTo &&
            screenshot == other.screenshot &&
            zoom == other.zoom &&
            leftClick == other.leftClick &&
            rightClick == other.rightClick &&
            middleClick == other.middleClick &&
            doubleClick == other.doubleClick &&
            tripleClick == other.tripleClick &&
            hover == other.hover &&
            leftClickDrag == other.leftClickDrag &&
            leftMouseDown == other.leftMouseDown &&
            leftMouseUp == other.leftMouseUp &&
            mouseMove == other.mouseMove &&
            scroll == other.scroll &&
            type == other.type &&
            key == other.key &&
            holdKey == other.holdKey &&
            wait == other.wait &&
            javascriptExec == other.javascriptExec
    }

    override fun hashCode(): Int =
        Objects.hash(
            navigate,
            listTabs,
            newTab,
            switchTab,
            closeTab,
            readPage,
            getPageText,
            readConsole,
            readNetwork,
            find,
            formInput,
            fileUpload,
            scrollTo,
            screenshot,
            zoom,
            leftClick,
            rightClick,
            middleClick,
            doubleClick,
            tripleClick,
            hover,
            leftClickDrag,
            leftMouseDown,
            leftMouseUp,
            mouseMove,
            scroll,
            type,
            key,
            holdKey,
            wait,
            javascriptExec,
        )

    override fun toString(): String =
        when {
            navigate != null -> "BetaBrowserToolUseBlock{navigate=$navigate}"
            listTabs != null -> "BetaBrowserToolUseBlock{listTabs=$listTabs}"
            newTab != null -> "BetaBrowserToolUseBlock{newTab=$newTab}"
            switchTab != null -> "BetaBrowserToolUseBlock{switchTab=$switchTab}"
            closeTab != null -> "BetaBrowserToolUseBlock{closeTab=$closeTab}"
            readPage != null -> "BetaBrowserToolUseBlock{readPage=$readPage}"
            getPageText != null -> "BetaBrowserToolUseBlock{getPageText=$getPageText}"
            readConsole != null -> "BetaBrowserToolUseBlock{readConsole=$readConsole}"
            readNetwork != null -> "BetaBrowserToolUseBlock{readNetwork=$readNetwork}"
            find != null -> "BetaBrowserToolUseBlock{find=$find}"
            formInput != null -> "BetaBrowserToolUseBlock{formInput=$formInput}"
            fileUpload != null -> "BetaBrowserToolUseBlock{fileUpload=$fileUpload}"
            scrollTo != null -> "BetaBrowserToolUseBlock{scrollTo=$scrollTo}"
            screenshot != null -> "BetaBrowserToolUseBlock{screenshot=$screenshot}"
            zoom != null -> "BetaBrowserToolUseBlock{zoom=$zoom}"
            leftClick != null -> "BetaBrowserToolUseBlock{leftClick=$leftClick}"
            rightClick != null -> "BetaBrowserToolUseBlock{rightClick=$rightClick}"
            middleClick != null -> "BetaBrowserToolUseBlock{middleClick=$middleClick}"
            doubleClick != null -> "BetaBrowserToolUseBlock{doubleClick=$doubleClick}"
            tripleClick != null -> "BetaBrowserToolUseBlock{tripleClick=$tripleClick}"
            hover != null -> "BetaBrowserToolUseBlock{hover=$hover}"
            leftClickDrag != null -> "BetaBrowserToolUseBlock{leftClickDrag=$leftClickDrag}"
            leftMouseDown != null -> "BetaBrowserToolUseBlock{leftMouseDown=$leftMouseDown}"
            leftMouseUp != null -> "BetaBrowserToolUseBlock{leftMouseUp=$leftMouseUp}"
            mouseMove != null -> "BetaBrowserToolUseBlock{mouseMove=$mouseMove}"
            scroll != null -> "BetaBrowserToolUseBlock{scroll=$scroll}"
            type != null -> "BetaBrowserToolUseBlock{type=$type}"
            key != null -> "BetaBrowserToolUseBlock{key=$key}"
            holdKey != null -> "BetaBrowserToolUseBlock{holdKey=$holdKey}"
            wait != null -> "BetaBrowserToolUseBlock{wait=$wait}"
            javascriptExec != null -> "BetaBrowserToolUseBlock{javascriptExec=$javascriptExec}"
            _json != null -> "BetaBrowserToolUseBlock{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaBrowserToolUseBlock")
        }

    companion object {

        @JvmStatic
        fun ofNavigate(navigate: BetaBrowserNavigateToolUseBlock) =
            BetaBrowserToolUseBlock(navigate = navigate)

        @JvmStatic
        fun ofListTabs(listTabs: BetaBrowserListTabsToolUseBlock) =
            BetaBrowserToolUseBlock(listTabs = listTabs)

        @JvmStatic
        fun ofNewTab(newTab: BetaBrowserNewTabToolUseBlock) =
            BetaBrowserToolUseBlock(newTab = newTab)

        @JvmStatic
        fun ofSwitchTab(switchTab: BetaBrowserSwitchTabToolUseBlock) =
            BetaBrowserToolUseBlock(switchTab = switchTab)

        @JvmStatic
        fun ofCloseTab(closeTab: BetaBrowserCloseTabToolUseBlock) =
            BetaBrowserToolUseBlock(closeTab = closeTab)

        @JvmStatic
        fun ofReadPage(readPage: BetaBrowserReadPageToolUseBlock) =
            BetaBrowserToolUseBlock(readPage = readPage)

        @JvmStatic
        fun ofGetPageText(getPageText: BetaBrowserGetPageTextToolUseBlock) =
            BetaBrowserToolUseBlock(getPageText = getPageText)

        @JvmStatic
        fun ofReadConsole(readConsole: BetaBrowserReadConsoleToolUseBlock) =
            BetaBrowserToolUseBlock(readConsole = readConsole)

        @JvmStatic
        fun ofReadNetwork(readNetwork: BetaBrowserReadNetworkToolUseBlock) =
            BetaBrowserToolUseBlock(readNetwork = readNetwork)

        @JvmStatic
        fun ofFind(find: BetaBrowserFindToolUseBlock) = BetaBrowserToolUseBlock(find = find)

        @JvmStatic
        fun ofFormInput(formInput: BetaBrowserFormInputToolUseBlock) =
            BetaBrowserToolUseBlock(formInput = formInput)

        @JvmStatic
        fun ofFileUpload(fileUpload: BetaBrowserFileUploadToolUseBlock) =
            BetaBrowserToolUseBlock(fileUpload = fileUpload)

        @JvmStatic
        fun ofScrollTo(scrollTo: BetaBrowserScrollToToolUseBlock) =
            BetaBrowserToolUseBlock(scrollTo = scrollTo)

        @JvmStatic
        fun ofScreenshot(screenshot: BetaBrowserScreenshotToolUseBlock) =
            BetaBrowserToolUseBlock(screenshot = screenshot)

        @JvmStatic
        fun ofZoom(zoom: BetaBrowserZoomToolUseBlock) = BetaBrowserToolUseBlock(zoom = zoom)

        @JvmStatic
        fun ofLeftClick(leftClick: BetaBrowserLeftClickToolUseBlock) =
            BetaBrowserToolUseBlock(leftClick = leftClick)

        @JvmStatic
        fun ofRightClick(rightClick: BetaBrowserRightClickToolUseBlock) =
            BetaBrowserToolUseBlock(rightClick = rightClick)

        @JvmStatic
        fun ofMiddleClick(middleClick: BetaBrowserMiddleClickToolUseBlock) =
            BetaBrowserToolUseBlock(middleClick = middleClick)

        @JvmStatic
        fun ofDoubleClick(doubleClick: BetaBrowserDoubleClickToolUseBlock) =
            BetaBrowserToolUseBlock(doubleClick = doubleClick)

        @JvmStatic
        fun ofTripleClick(tripleClick: BetaBrowserTripleClickToolUseBlock) =
            BetaBrowserToolUseBlock(tripleClick = tripleClick)

        @JvmStatic
        fun ofHover(hover: BetaBrowserHoverToolUseBlock) = BetaBrowserToolUseBlock(hover = hover)

        @JvmStatic
        fun ofLeftClickDrag(leftClickDrag: BetaBrowserLeftClickDragToolUseBlock) =
            BetaBrowserToolUseBlock(leftClickDrag = leftClickDrag)

        @JvmStatic
        fun ofLeftMouseDown(leftMouseDown: BetaBrowserLeftMouseDownToolUseBlock) =
            BetaBrowserToolUseBlock(leftMouseDown = leftMouseDown)

        @JvmStatic
        fun ofLeftMouseUp(leftMouseUp: BetaBrowserLeftMouseUpToolUseBlock) =
            BetaBrowserToolUseBlock(leftMouseUp = leftMouseUp)

        @JvmStatic
        fun ofMouseMove(mouseMove: BetaBrowserMouseMoveToolUseBlock) =
            BetaBrowserToolUseBlock(mouseMove = mouseMove)

        @JvmStatic
        fun ofScroll(scroll: BetaBrowserScrollToolUseBlock) =
            BetaBrowserToolUseBlock(scroll = scroll)

        @JvmStatic
        fun ofType(type: BetaBrowserTypeToolUseBlock) = BetaBrowserToolUseBlock(type = type)

        @JvmStatic fun ofKey(key: BetaBrowserKeyToolUseBlock) = BetaBrowserToolUseBlock(key = key)

        @JvmStatic
        fun ofHoldKey(holdKey: BetaBrowserHoldKeyToolUseBlock) =
            BetaBrowserToolUseBlock(holdKey = holdKey)

        @JvmStatic
        fun ofWait(wait: BetaBrowserWaitToolUseBlock) = BetaBrowserToolUseBlock(wait = wait)

        @JvmStatic
        fun ofJavascriptExec(javascriptExec: BetaBrowserJavascriptExecToolUseBlock) =
            BetaBrowserToolUseBlock(javascriptExec = javascriptExec)
    }

    /**
     * An interface that defines how to map each variant of [BetaBrowserToolUseBlock] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitNavigate(navigate: BetaBrowserNavigateToolUseBlock): T

        fun visitListTabs(listTabs: BetaBrowserListTabsToolUseBlock): T

        fun visitNewTab(newTab: BetaBrowserNewTabToolUseBlock): T

        fun visitSwitchTab(switchTab: BetaBrowserSwitchTabToolUseBlock): T

        fun visitCloseTab(closeTab: BetaBrowserCloseTabToolUseBlock): T

        fun visitReadPage(readPage: BetaBrowserReadPageToolUseBlock): T

        fun visitGetPageText(getPageText: BetaBrowserGetPageTextToolUseBlock): T

        fun visitReadConsole(readConsole: BetaBrowserReadConsoleToolUseBlock): T

        fun visitReadNetwork(readNetwork: BetaBrowserReadNetworkToolUseBlock): T

        fun visitFind(find: BetaBrowserFindToolUseBlock): T

        fun visitFormInput(formInput: BetaBrowserFormInputToolUseBlock): T

        fun visitFileUpload(fileUpload: BetaBrowserFileUploadToolUseBlock): T

        fun visitScrollTo(scrollTo: BetaBrowserScrollToToolUseBlock): T

        fun visitScreenshot(screenshot: BetaBrowserScreenshotToolUseBlock): T

        fun visitZoom(zoom: BetaBrowserZoomToolUseBlock): T

        fun visitLeftClick(leftClick: BetaBrowserLeftClickToolUseBlock): T

        fun visitRightClick(rightClick: BetaBrowserRightClickToolUseBlock): T

        fun visitMiddleClick(middleClick: BetaBrowserMiddleClickToolUseBlock): T

        fun visitDoubleClick(doubleClick: BetaBrowserDoubleClickToolUseBlock): T

        fun visitTripleClick(tripleClick: BetaBrowserTripleClickToolUseBlock): T

        fun visitHover(hover: BetaBrowserHoverToolUseBlock): T

        fun visitLeftClickDrag(leftClickDrag: BetaBrowserLeftClickDragToolUseBlock): T

        fun visitLeftMouseDown(leftMouseDown: BetaBrowserLeftMouseDownToolUseBlock): T

        fun visitLeftMouseUp(leftMouseUp: BetaBrowserLeftMouseUpToolUseBlock): T

        fun visitMouseMove(mouseMove: BetaBrowserMouseMoveToolUseBlock): T

        fun visitScroll(scroll: BetaBrowserScrollToolUseBlock): T

        fun visitType(type: BetaBrowserTypeToolUseBlock): T

        fun visitKey(key: BetaBrowserKeyToolUseBlock): T

        fun visitHoldKey(holdKey: BetaBrowserHoldKeyToolUseBlock): T

        fun visitWait(wait: BetaBrowserWaitToolUseBlock): T

        fun visitJavascriptExec(javascriptExec: BetaBrowserJavascriptExecToolUseBlock): T

        /**
         * Maps an unknown variant of [BetaBrowserToolUseBlock] to a value of type [T].
         *
         * An instance of [BetaBrowserToolUseBlock] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaBrowserToolUseBlock: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaBrowserToolUseBlock>(BetaBrowserToolUseBlock::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaBrowserToolUseBlock {
            val json = JsonValue.fromJsonNode(node)
            val name = json.asObject().getOrNull()?.get("name")?.asString()?.getOrNull()

            when (name) {
                "navigate" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserNavigateToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(navigate = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "list_tabs" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserListTabsToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(listTabs = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "new_tab" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserNewTabToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(newTab = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "switch_tab" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserSwitchTabToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(switchTab = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "close_tab" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserCloseTabToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(closeTab = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "read_page" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserReadPageToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(readPage = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "get_page_text" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaBrowserGetPageTextToolUseBlock>(),
                        )
                        ?.let { BetaBrowserToolUseBlock(getPageText = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "read_console" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaBrowserReadConsoleToolUseBlock>(),
                        )
                        ?.let { BetaBrowserToolUseBlock(readConsole = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "read_network" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaBrowserReadNetworkToolUseBlock>(),
                        )
                        ?.let { BetaBrowserToolUseBlock(readNetwork = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "find" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserFindToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(find = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "form_input" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserFormInputToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(formInput = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "file_upload" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserFileUploadToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(fileUpload = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "scroll_to" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserScrollToToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(scrollTo = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "screenshot" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserScreenshotToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(screenshot = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "zoom" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserZoomToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(zoom = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "left_click" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserLeftClickToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(leftClick = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "right_click" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserRightClickToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(rightClick = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "middle_click" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaBrowserMiddleClickToolUseBlock>(),
                        )
                        ?.let { BetaBrowserToolUseBlock(middleClick = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "double_click" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaBrowserDoubleClickToolUseBlock>(),
                        )
                        ?.let { BetaBrowserToolUseBlock(doubleClick = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "triple_click" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaBrowserTripleClickToolUseBlock>(),
                        )
                        ?.let { BetaBrowserToolUseBlock(tripleClick = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "hover" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserHoverToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(hover = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "left_click_drag" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaBrowserLeftClickDragToolUseBlock>(),
                        )
                        ?.let { BetaBrowserToolUseBlock(leftClickDrag = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "left_mouse_down" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaBrowserLeftMouseDownToolUseBlock>(),
                        )
                        ?.let { BetaBrowserToolUseBlock(leftMouseDown = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "left_mouse_up" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaBrowserLeftMouseUpToolUseBlock>(),
                        )
                        ?.let { BetaBrowserToolUseBlock(leftMouseUp = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "mouse_move" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserMouseMoveToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(mouseMove = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "scroll" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserScrollToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(scroll = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "type" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserTypeToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(type = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "key" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserKeyToolUseBlock>())?.let {
                        BetaBrowserToolUseBlock(key = it, _json = json)
                    } ?: BetaBrowserToolUseBlock(_json = json)
                }
                "hold_key" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserHoldKeyToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(holdKey = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "wait" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBrowserWaitToolUseBlock>())
                        ?.let { BetaBrowserToolUseBlock(wait = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
                "javascript_exec" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaBrowserJavascriptExecToolUseBlock>(),
                        )
                        ?.let { BetaBrowserToolUseBlock(javascriptExec = it, _json = json) }
                        ?: BetaBrowserToolUseBlock(_json = json)
                }
            }

            return BetaBrowserToolUseBlock(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaBrowserToolUseBlock>(BetaBrowserToolUseBlock::class) {

        override fun serialize(
            value: BetaBrowserToolUseBlock,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.navigate != null -> generator.writeObject(value.navigate)
                value.listTabs != null -> generator.writeObject(value.listTabs)
                value.newTab != null -> generator.writeObject(value.newTab)
                value.switchTab != null -> generator.writeObject(value.switchTab)
                value.closeTab != null -> generator.writeObject(value.closeTab)
                value.readPage != null -> generator.writeObject(value.readPage)
                value.getPageText != null -> generator.writeObject(value.getPageText)
                value.readConsole != null -> generator.writeObject(value.readConsole)
                value.readNetwork != null -> generator.writeObject(value.readNetwork)
                value.find != null -> generator.writeObject(value.find)
                value.formInput != null -> generator.writeObject(value.formInput)
                value.fileUpload != null -> generator.writeObject(value.fileUpload)
                value.scrollTo != null -> generator.writeObject(value.scrollTo)
                value.screenshot != null -> generator.writeObject(value.screenshot)
                value.zoom != null -> generator.writeObject(value.zoom)
                value.leftClick != null -> generator.writeObject(value.leftClick)
                value.rightClick != null -> generator.writeObject(value.rightClick)
                value.middleClick != null -> generator.writeObject(value.middleClick)
                value.doubleClick != null -> generator.writeObject(value.doubleClick)
                value.tripleClick != null -> generator.writeObject(value.tripleClick)
                value.hover != null -> generator.writeObject(value.hover)
                value.leftClickDrag != null -> generator.writeObject(value.leftClickDrag)
                value.leftMouseDown != null -> generator.writeObject(value.leftMouseDown)
                value.leftMouseUp != null -> generator.writeObject(value.leftMouseUp)
                value.mouseMove != null -> generator.writeObject(value.mouseMove)
                value.scroll != null -> generator.writeObject(value.scroll)
                value.type != null -> generator.writeObject(value.type)
                value.key != null -> generator.writeObject(value.key)
                value.holdKey != null -> generator.writeObject(value.holdKey)
                value.wait != null -> generator.writeObject(value.wait)
                value.javascriptExec != null -> generator.writeObject(value.javascriptExec)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaBrowserToolUseBlock")
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

            @JvmField val NAVIGATE = Name(JsonField.of("navigate"))

            @JvmField val LIST_TABS = Name(JsonField.of("list_tabs"))

            @JvmField val NEW_TAB = Name(JsonField.of("new_tab"))

            @JvmField val SWITCH_TAB = Name(JsonField.of("switch_tab"))

            @JvmField val CLOSE_TAB = Name(JsonField.of("close_tab"))

            @JvmField val READ_PAGE = Name(JsonField.of("read_page"))

            @JvmField val GET_PAGE_TEXT = Name(JsonField.of("get_page_text"))

            @JvmField val READ_CONSOLE = Name(JsonField.of("read_console"))

            @JvmField val READ_NETWORK = Name(JsonField.of("read_network"))

            @JvmField val FIND = Name(JsonField.of("find"))

            @JvmField val FORM_INPUT = Name(JsonField.of("form_input"))

            @JvmField val FILE_UPLOAD = Name(JsonField.of("file_upload"))

            @JvmField val SCROLL_TO = Name(JsonField.of("scroll_to"))

            @JvmField val SCREENSHOT = Name(JsonField.of("screenshot"))

            @JvmField val ZOOM = Name(JsonField.of("zoom"))

            @JvmField val LEFT_CLICK = Name(JsonField.of("left_click"))

            @JvmField val RIGHT_CLICK = Name(JsonField.of("right_click"))

            @JvmField val MIDDLE_CLICK = Name(JsonField.of("middle_click"))

            @JvmField val DOUBLE_CLICK = Name(JsonField.of("double_click"))

            @JvmField val TRIPLE_CLICK = Name(JsonField.of("triple_click"))

            @JvmField val HOVER = Name(JsonField.of("hover"))

            @JvmField val LEFT_CLICK_DRAG = Name(JsonField.of("left_click_drag"))

            @JvmField val LEFT_MOUSE_DOWN = Name(JsonField.of("left_mouse_down"))

            @JvmField val LEFT_MOUSE_UP = Name(JsonField.of("left_mouse_up"))

            @JvmField val MOUSE_MOVE = Name(JsonField.of("mouse_move"))

            @JvmField val SCROLL = Name(JsonField.of("scroll"))

            @JvmField val TYPE = Name(JsonField.of("type"))

            @JvmField val KEY = Name(JsonField.of("key"))

            @JvmField val HOLD_KEY = Name(JsonField.of("hold_key"))

            @JvmField val WAIT = Name(JsonField.of("wait"))

            @JvmField val JAVASCRIPT_EXEC = Name(JsonField.of("javascript_exec"))

            @JvmStatic
            fun of(value: String): Name =
                // Intern known values so `==` works
                when (value) {
                    "navigate" -> NAVIGATE
                    "list_tabs" -> LIST_TABS
                    "new_tab" -> NEW_TAB
                    "switch_tab" -> SWITCH_TAB
                    "close_tab" -> CLOSE_TAB
                    "read_page" -> READ_PAGE
                    "get_page_text" -> GET_PAGE_TEXT
                    "read_console" -> READ_CONSOLE
                    "read_network" -> READ_NETWORK
                    "find" -> FIND
                    "form_input" -> FORM_INPUT
                    "file_upload" -> FILE_UPLOAD
                    "scroll_to" -> SCROLL_TO
                    "screenshot" -> SCREENSHOT
                    "zoom" -> ZOOM
                    "left_click" -> LEFT_CLICK
                    "right_click" -> RIGHT_CLICK
                    "middle_click" -> MIDDLE_CLICK
                    "double_click" -> DOUBLE_CLICK
                    "triple_click" -> TRIPLE_CLICK
                    "hover" -> HOVER
                    "left_click_drag" -> LEFT_CLICK_DRAG
                    "left_mouse_down" -> LEFT_MOUSE_DOWN
                    "left_mouse_up" -> LEFT_MOUSE_UP
                    "mouse_move" -> MOUSE_MOVE
                    "scroll" -> SCROLL
                    "type" -> TYPE
                    "key" -> KEY
                    "hold_key" -> HOLD_KEY
                    "wait" -> WAIT
                    "javascript_exec" -> JAVASCRIPT_EXEC
                    else -> Name(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Name =
                value.asString().getOrNull()?.let { of(it) } ?: Name(value)
        }

        /** An enum containing [Name]'s known values. */
        enum class Known {
            NAVIGATE,
            LIST_TABS,
            NEW_TAB,
            SWITCH_TAB,
            CLOSE_TAB,
            READ_PAGE,
            GET_PAGE_TEXT,
            READ_CONSOLE,
            READ_NETWORK,
            FIND,
            FORM_INPUT,
            FILE_UPLOAD,
            SCROLL_TO,
            SCREENSHOT,
            ZOOM,
            LEFT_CLICK,
            RIGHT_CLICK,
            MIDDLE_CLICK,
            DOUBLE_CLICK,
            TRIPLE_CLICK,
            HOVER,
            LEFT_CLICK_DRAG,
            LEFT_MOUSE_DOWN,
            LEFT_MOUSE_UP,
            MOUSE_MOVE,
            SCROLL,
            TYPE,
            KEY,
            HOLD_KEY,
            WAIT,
            JAVASCRIPT_EXEC,
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
            NAVIGATE,
            LIST_TABS,
            NEW_TAB,
            SWITCH_TAB,
            CLOSE_TAB,
            READ_PAGE,
            GET_PAGE_TEXT,
            READ_CONSOLE,
            READ_NETWORK,
            FIND,
            FORM_INPUT,
            FILE_UPLOAD,
            SCROLL_TO,
            SCREENSHOT,
            ZOOM,
            LEFT_CLICK,
            RIGHT_CLICK,
            MIDDLE_CLICK,
            DOUBLE_CLICK,
            TRIPLE_CLICK,
            HOVER,
            LEFT_CLICK_DRAG,
            LEFT_MOUSE_DOWN,
            LEFT_MOUSE_UP,
            MOUSE_MOVE,
            SCROLL,
            TYPE,
            KEY,
            HOLD_KEY,
            WAIT,
            JAVASCRIPT_EXEC,
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
                NAVIGATE -> Value.NAVIGATE
                LIST_TABS -> Value.LIST_TABS
                NEW_TAB -> Value.NEW_TAB
                SWITCH_TAB -> Value.SWITCH_TAB
                CLOSE_TAB -> Value.CLOSE_TAB
                READ_PAGE -> Value.READ_PAGE
                GET_PAGE_TEXT -> Value.GET_PAGE_TEXT
                READ_CONSOLE -> Value.READ_CONSOLE
                READ_NETWORK -> Value.READ_NETWORK
                FIND -> Value.FIND
                FORM_INPUT -> Value.FORM_INPUT
                FILE_UPLOAD -> Value.FILE_UPLOAD
                SCROLL_TO -> Value.SCROLL_TO
                SCREENSHOT -> Value.SCREENSHOT
                ZOOM -> Value.ZOOM
                LEFT_CLICK -> Value.LEFT_CLICK
                RIGHT_CLICK -> Value.RIGHT_CLICK
                MIDDLE_CLICK -> Value.MIDDLE_CLICK
                DOUBLE_CLICK -> Value.DOUBLE_CLICK
                TRIPLE_CLICK -> Value.TRIPLE_CLICK
                HOVER -> Value.HOVER
                LEFT_CLICK_DRAG -> Value.LEFT_CLICK_DRAG
                LEFT_MOUSE_DOWN -> Value.LEFT_MOUSE_DOWN
                LEFT_MOUSE_UP -> Value.LEFT_MOUSE_UP
                MOUSE_MOVE -> Value.MOUSE_MOVE
                SCROLL -> Value.SCROLL
                TYPE -> Value.TYPE
                KEY -> Value.KEY
                HOLD_KEY -> Value.HOLD_KEY
                WAIT -> Value.WAIT
                JAVASCRIPT_EXEC -> Value.JAVASCRIPT_EXEC
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
                NAVIGATE -> Known.NAVIGATE
                LIST_TABS -> Known.LIST_TABS
                NEW_TAB -> Known.NEW_TAB
                SWITCH_TAB -> Known.SWITCH_TAB
                CLOSE_TAB -> Known.CLOSE_TAB
                READ_PAGE -> Known.READ_PAGE
                GET_PAGE_TEXT -> Known.GET_PAGE_TEXT
                READ_CONSOLE -> Known.READ_CONSOLE
                READ_NETWORK -> Known.READ_NETWORK
                FIND -> Known.FIND
                FORM_INPUT -> Known.FORM_INPUT
                FILE_UPLOAD -> Known.FILE_UPLOAD
                SCROLL_TO -> Known.SCROLL_TO
                SCREENSHOT -> Known.SCREENSHOT
                ZOOM -> Known.ZOOM
                LEFT_CLICK -> Known.LEFT_CLICK
                RIGHT_CLICK -> Known.RIGHT_CLICK
                MIDDLE_CLICK -> Known.MIDDLE_CLICK
                DOUBLE_CLICK -> Known.DOUBLE_CLICK
                TRIPLE_CLICK -> Known.TRIPLE_CLICK
                HOVER -> Known.HOVER
                LEFT_CLICK_DRAG -> Known.LEFT_CLICK_DRAG
                LEFT_MOUSE_DOWN -> Known.LEFT_MOUSE_DOWN
                LEFT_MOUSE_UP -> Known.LEFT_MOUSE_UP
                MOUSE_MOVE -> Known.MOUSE_MOVE
                SCROLL -> Known.SCROLL
                TYPE -> Known.TYPE
                KEY -> Known.KEY
                HOLD_KEY -> Known.HOLD_KEY
                WAIT -> Known.WAIT
                JAVASCRIPT_EXEC -> Known.JAVASCRIPT_EXEC
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
