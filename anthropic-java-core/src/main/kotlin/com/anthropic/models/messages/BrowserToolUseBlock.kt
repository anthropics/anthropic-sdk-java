package com.anthropic.models.messages

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

@JsonDeserialize(using = BrowserToolUseBlock.Deserializer::class)
@JsonSerialize(using = BrowserToolUseBlock.Serializer::class)
class BrowserToolUseBlock
private constructor(
    private val navigate: BrowserNavigateToolUseBlock? = null,
    private val listTabs: BrowserListTabsToolUseBlock? = null,
    private val newTab: BrowserNewTabToolUseBlock? = null,
    private val switchTab: BrowserSwitchTabToolUseBlock? = null,
    private val closeTab: BrowserCloseTabToolUseBlock? = null,
    private val readPage: BrowserReadPageToolUseBlock? = null,
    private val getPageText: BrowserGetPageTextToolUseBlock? = null,
    private val readConsole: BrowserReadConsoleToolUseBlock? = null,
    private val readNetwork: BrowserReadNetworkToolUseBlock? = null,
    private val find: BrowserFindToolUseBlock? = null,
    private val formInput: BrowserFormInputToolUseBlock? = null,
    private val fileUpload: BrowserFileUploadToolUseBlock? = null,
    private val scrollTo: BrowserScrollToToolUseBlock? = null,
    private val screenshot: BrowserScreenshotToolUseBlock? = null,
    private val zoom: BrowserZoomToolUseBlock? = null,
    private val leftClick: BrowserLeftClickToolUseBlock? = null,
    private val rightClick: BrowserRightClickToolUseBlock? = null,
    private val middleClick: BrowserMiddleClickToolUseBlock? = null,
    private val doubleClick: BrowserDoubleClickToolUseBlock? = null,
    private val tripleClick: BrowserTripleClickToolUseBlock? = null,
    private val hover: BrowserHoverToolUseBlock? = null,
    private val leftClickDrag: BrowserLeftClickDragToolUseBlock? = null,
    private val leftMouseDown: BrowserLeftMouseDownToolUseBlock? = null,
    private val leftMouseUp: BrowserLeftMouseUpToolUseBlock? = null,
    private val mouseMove: BrowserMouseMoveToolUseBlock? = null,
    private val scroll: BrowserScrollToolUseBlock? = null,
    private val type: BrowserTypeToolUseBlock? = null,
    private val key: BrowserKeyToolUseBlock? = null,
    private val holdKey: BrowserHoldKeyToolUseBlock? = null,
    private val wait: BrowserWaitToolUseBlock? = null,
    private val javascriptExec: BrowserJavascriptExecToolUseBlock? = null,
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

    fun caller(): ToolUseCaller =
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
            else -> _json.getProperty<ToolUseCaller>("caller").getRequired("caller")
        }

    fun navigate(): Optional<BrowserNavigateToolUseBlock> = Optional.ofNullable(navigate)

    fun listTabs(): Optional<BrowserListTabsToolUseBlock> = Optional.ofNullable(listTabs)

    fun newTab(): Optional<BrowserNewTabToolUseBlock> = Optional.ofNullable(newTab)

    fun switchTab(): Optional<BrowserSwitchTabToolUseBlock> = Optional.ofNullable(switchTab)

    fun closeTab(): Optional<BrowserCloseTabToolUseBlock> = Optional.ofNullable(closeTab)

    fun readPage(): Optional<BrowserReadPageToolUseBlock> = Optional.ofNullable(readPage)

    fun getPageText(): Optional<BrowserGetPageTextToolUseBlock> = Optional.ofNullable(getPageText)

    fun readConsole(): Optional<BrowserReadConsoleToolUseBlock> = Optional.ofNullable(readConsole)

    fun readNetwork(): Optional<BrowserReadNetworkToolUseBlock> = Optional.ofNullable(readNetwork)

    fun find(): Optional<BrowserFindToolUseBlock> = Optional.ofNullable(find)

    fun formInput(): Optional<BrowserFormInputToolUseBlock> = Optional.ofNullable(formInput)

    fun fileUpload(): Optional<BrowserFileUploadToolUseBlock> = Optional.ofNullable(fileUpload)

    fun scrollTo(): Optional<BrowserScrollToToolUseBlock> = Optional.ofNullable(scrollTo)

    fun screenshot(): Optional<BrowserScreenshotToolUseBlock> = Optional.ofNullable(screenshot)

    fun zoom(): Optional<BrowserZoomToolUseBlock> = Optional.ofNullable(zoom)

    fun leftClick(): Optional<BrowserLeftClickToolUseBlock> = Optional.ofNullable(leftClick)

    fun rightClick(): Optional<BrowserRightClickToolUseBlock> = Optional.ofNullable(rightClick)

    fun middleClick(): Optional<BrowserMiddleClickToolUseBlock> = Optional.ofNullable(middleClick)

    fun doubleClick(): Optional<BrowserDoubleClickToolUseBlock> = Optional.ofNullable(doubleClick)

    fun tripleClick(): Optional<BrowserTripleClickToolUseBlock> = Optional.ofNullable(tripleClick)

    fun hover(): Optional<BrowserHoverToolUseBlock> = Optional.ofNullable(hover)

    fun leftClickDrag(): Optional<BrowserLeftClickDragToolUseBlock> =
        Optional.ofNullable(leftClickDrag)

    fun leftMouseDown(): Optional<BrowserLeftMouseDownToolUseBlock> =
        Optional.ofNullable(leftMouseDown)

    fun leftMouseUp(): Optional<BrowserLeftMouseUpToolUseBlock> = Optional.ofNullable(leftMouseUp)

    fun mouseMove(): Optional<BrowserMouseMoveToolUseBlock> = Optional.ofNullable(mouseMove)

    fun scroll(): Optional<BrowserScrollToolUseBlock> = Optional.ofNullable(scroll)

    fun type(): Optional<BrowserTypeToolUseBlock> = Optional.ofNullable(type)

    fun key(): Optional<BrowserKeyToolUseBlock> = Optional.ofNullable(key)

    fun holdKey(): Optional<BrowserHoldKeyToolUseBlock> = Optional.ofNullable(holdKey)

    fun wait(): Optional<BrowserWaitToolUseBlock> = Optional.ofNullable(wait)

    fun javascriptExec(): Optional<BrowserJavascriptExecToolUseBlock> =
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

    fun asNavigate(): BrowserNavigateToolUseBlock = navigate.getOrThrow("navigate")

    fun asListTabs(): BrowserListTabsToolUseBlock = listTabs.getOrThrow("listTabs")

    fun asNewTab(): BrowserNewTabToolUseBlock = newTab.getOrThrow("newTab")

    fun asSwitchTab(): BrowserSwitchTabToolUseBlock = switchTab.getOrThrow("switchTab")

    fun asCloseTab(): BrowserCloseTabToolUseBlock = closeTab.getOrThrow("closeTab")

    fun asReadPage(): BrowserReadPageToolUseBlock = readPage.getOrThrow("readPage")

    fun asGetPageText(): BrowserGetPageTextToolUseBlock = getPageText.getOrThrow("getPageText")

    fun asReadConsole(): BrowserReadConsoleToolUseBlock = readConsole.getOrThrow("readConsole")

    fun asReadNetwork(): BrowserReadNetworkToolUseBlock = readNetwork.getOrThrow("readNetwork")

    fun asFind(): BrowserFindToolUseBlock = find.getOrThrow("find")

    fun asFormInput(): BrowserFormInputToolUseBlock = formInput.getOrThrow("formInput")

    fun asFileUpload(): BrowserFileUploadToolUseBlock = fileUpload.getOrThrow("fileUpload")

    fun asScrollTo(): BrowserScrollToToolUseBlock = scrollTo.getOrThrow("scrollTo")

    fun asScreenshot(): BrowserScreenshotToolUseBlock = screenshot.getOrThrow("screenshot")

    fun asZoom(): BrowserZoomToolUseBlock = zoom.getOrThrow("zoom")

    fun asLeftClick(): BrowserLeftClickToolUseBlock = leftClick.getOrThrow("leftClick")

    fun asRightClick(): BrowserRightClickToolUseBlock = rightClick.getOrThrow("rightClick")

    fun asMiddleClick(): BrowserMiddleClickToolUseBlock = middleClick.getOrThrow("middleClick")

    fun asDoubleClick(): BrowserDoubleClickToolUseBlock = doubleClick.getOrThrow("doubleClick")

    fun asTripleClick(): BrowserTripleClickToolUseBlock = tripleClick.getOrThrow("tripleClick")

    fun asHover(): BrowserHoverToolUseBlock = hover.getOrThrow("hover")

    fun asLeftClickDrag(): BrowserLeftClickDragToolUseBlock =
        leftClickDrag.getOrThrow("leftClickDrag")

    fun asLeftMouseDown(): BrowserLeftMouseDownToolUseBlock =
        leftMouseDown.getOrThrow("leftMouseDown")

    fun asLeftMouseUp(): BrowserLeftMouseUpToolUseBlock = leftMouseUp.getOrThrow("leftMouseUp")

    fun asMouseMove(): BrowserMouseMoveToolUseBlock = mouseMove.getOrThrow("mouseMove")

    fun asScroll(): BrowserScrollToolUseBlock = scroll.getOrThrow("scroll")

    fun asType(): BrowserTypeToolUseBlock = type.getOrThrow("type")

    fun asKey(): BrowserKeyToolUseBlock = key.getOrThrow("key")

    fun asHoldKey(): BrowserHoldKeyToolUseBlock = holdKey.getOrThrow("holdKey")

    fun asWait(): BrowserWaitToolUseBlock = wait.getOrThrow("wait")

    fun asJavascriptExec(): BrowserJavascriptExecToolUseBlock =
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
     * Optional<String> result = browserToolUseBlock.accept(new BrowserToolUseBlock.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitNavigate(BrowserNavigateToolUseBlock navigate) {
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
    fun validate(): BrowserToolUseBlock = apply {
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
            else -> throw AnthropicInvalidDataException("Unknown BrowserToolUseBlock: $_json")
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

        return other is BrowserToolUseBlock &&
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
            navigate != null -> "BrowserToolUseBlock{navigate=$navigate}"
            listTabs != null -> "BrowserToolUseBlock{listTabs=$listTabs}"
            newTab != null -> "BrowserToolUseBlock{newTab=$newTab}"
            switchTab != null -> "BrowserToolUseBlock{switchTab=$switchTab}"
            closeTab != null -> "BrowserToolUseBlock{closeTab=$closeTab}"
            readPage != null -> "BrowserToolUseBlock{readPage=$readPage}"
            getPageText != null -> "BrowserToolUseBlock{getPageText=$getPageText}"
            readConsole != null -> "BrowserToolUseBlock{readConsole=$readConsole}"
            readNetwork != null -> "BrowserToolUseBlock{readNetwork=$readNetwork}"
            find != null -> "BrowserToolUseBlock{find=$find}"
            formInput != null -> "BrowserToolUseBlock{formInput=$formInput}"
            fileUpload != null -> "BrowserToolUseBlock{fileUpload=$fileUpload}"
            scrollTo != null -> "BrowserToolUseBlock{scrollTo=$scrollTo}"
            screenshot != null -> "BrowserToolUseBlock{screenshot=$screenshot}"
            zoom != null -> "BrowserToolUseBlock{zoom=$zoom}"
            leftClick != null -> "BrowserToolUseBlock{leftClick=$leftClick}"
            rightClick != null -> "BrowserToolUseBlock{rightClick=$rightClick}"
            middleClick != null -> "BrowserToolUseBlock{middleClick=$middleClick}"
            doubleClick != null -> "BrowserToolUseBlock{doubleClick=$doubleClick}"
            tripleClick != null -> "BrowserToolUseBlock{tripleClick=$tripleClick}"
            hover != null -> "BrowserToolUseBlock{hover=$hover}"
            leftClickDrag != null -> "BrowserToolUseBlock{leftClickDrag=$leftClickDrag}"
            leftMouseDown != null -> "BrowserToolUseBlock{leftMouseDown=$leftMouseDown}"
            leftMouseUp != null -> "BrowserToolUseBlock{leftMouseUp=$leftMouseUp}"
            mouseMove != null -> "BrowserToolUseBlock{mouseMove=$mouseMove}"
            scroll != null -> "BrowserToolUseBlock{scroll=$scroll}"
            type != null -> "BrowserToolUseBlock{type=$type}"
            key != null -> "BrowserToolUseBlock{key=$key}"
            holdKey != null -> "BrowserToolUseBlock{holdKey=$holdKey}"
            wait != null -> "BrowserToolUseBlock{wait=$wait}"
            javascriptExec != null -> "BrowserToolUseBlock{javascriptExec=$javascriptExec}"
            _json != null -> "BrowserToolUseBlock{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BrowserToolUseBlock")
        }

    companion object {

        @JvmStatic
        fun ofNavigate(navigate: BrowserNavigateToolUseBlock) =
            BrowserToolUseBlock(navigate = navigate)

        @JvmStatic
        fun ofListTabs(listTabs: BrowserListTabsToolUseBlock) =
            BrowserToolUseBlock(listTabs = listTabs)

        @JvmStatic
        fun ofNewTab(newTab: BrowserNewTabToolUseBlock) = BrowserToolUseBlock(newTab = newTab)

        @JvmStatic
        fun ofSwitchTab(switchTab: BrowserSwitchTabToolUseBlock) =
            BrowserToolUseBlock(switchTab = switchTab)

        @JvmStatic
        fun ofCloseTab(closeTab: BrowserCloseTabToolUseBlock) =
            BrowserToolUseBlock(closeTab = closeTab)

        @JvmStatic
        fun ofReadPage(readPage: BrowserReadPageToolUseBlock) =
            BrowserToolUseBlock(readPage = readPage)

        @JvmStatic
        fun ofGetPageText(getPageText: BrowserGetPageTextToolUseBlock) =
            BrowserToolUseBlock(getPageText = getPageText)

        @JvmStatic
        fun ofReadConsole(readConsole: BrowserReadConsoleToolUseBlock) =
            BrowserToolUseBlock(readConsole = readConsole)

        @JvmStatic
        fun ofReadNetwork(readNetwork: BrowserReadNetworkToolUseBlock) =
            BrowserToolUseBlock(readNetwork = readNetwork)

        @JvmStatic fun ofFind(find: BrowserFindToolUseBlock) = BrowserToolUseBlock(find = find)

        @JvmStatic
        fun ofFormInput(formInput: BrowserFormInputToolUseBlock) =
            BrowserToolUseBlock(formInput = formInput)

        @JvmStatic
        fun ofFileUpload(fileUpload: BrowserFileUploadToolUseBlock) =
            BrowserToolUseBlock(fileUpload = fileUpload)

        @JvmStatic
        fun ofScrollTo(scrollTo: BrowserScrollToToolUseBlock) =
            BrowserToolUseBlock(scrollTo = scrollTo)

        @JvmStatic
        fun ofScreenshot(screenshot: BrowserScreenshotToolUseBlock) =
            BrowserToolUseBlock(screenshot = screenshot)

        @JvmStatic fun ofZoom(zoom: BrowserZoomToolUseBlock) = BrowserToolUseBlock(zoom = zoom)

        @JvmStatic
        fun ofLeftClick(leftClick: BrowserLeftClickToolUseBlock) =
            BrowserToolUseBlock(leftClick = leftClick)

        @JvmStatic
        fun ofRightClick(rightClick: BrowserRightClickToolUseBlock) =
            BrowserToolUseBlock(rightClick = rightClick)

        @JvmStatic
        fun ofMiddleClick(middleClick: BrowserMiddleClickToolUseBlock) =
            BrowserToolUseBlock(middleClick = middleClick)

        @JvmStatic
        fun ofDoubleClick(doubleClick: BrowserDoubleClickToolUseBlock) =
            BrowserToolUseBlock(doubleClick = doubleClick)

        @JvmStatic
        fun ofTripleClick(tripleClick: BrowserTripleClickToolUseBlock) =
            BrowserToolUseBlock(tripleClick = tripleClick)

        @JvmStatic fun ofHover(hover: BrowserHoverToolUseBlock) = BrowserToolUseBlock(hover = hover)

        @JvmStatic
        fun ofLeftClickDrag(leftClickDrag: BrowserLeftClickDragToolUseBlock) =
            BrowserToolUseBlock(leftClickDrag = leftClickDrag)

        @JvmStatic
        fun ofLeftMouseDown(leftMouseDown: BrowserLeftMouseDownToolUseBlock) =
            BrowserToolUseBlock(leftMouseDown = leftMouseDown)

        @JvmStatic
        fun ofLeftMouseUp(leftMouseUp: BrowserLeftMouseUpToolUseBlock) =
            BrowserToolUseBlock(leftMouseUp = leftMouseUp)

        @JvmStatic
        fun ofMouseMove(mouseMove: BrowserMouseMoveToolUseBlock) =
            BrowserToolUseBlock(mouseMove = mouseMove)

        @JvmStatic
        fun ofScroll(scroll: BrowserScrollToolUseBlock) = BrowserToolUseBlock(scroll = scroll)

        @JvmStatic fun ofType(type: BrowserTypeToolUseBlock) = BrowserToolUseBlock(type = type)

        @JvmStatic fun ofKey(key: BrowserKeyToolUseBlock) = BrowserToolUseBlock(key = key)

        @JvmStatic
        fun ofHoldKey(holdKey: BrowserHoldKeyToolUseBlock) = BrowserToolUseBlock(holdKey = holdKey)

        @JvmStatic fun ofWait(wait: BrowserWaitToolUseBlock) = BrowserToolUseBlock(wait = wait)

        @JvmStatic
        fun ofJavascriptExec(javascriptExec: BrowserJavascriptExecToolUseBlock) =
            BrowserToolUseBlock(javascriptExec = javascriptExec)
    }

    /**
     * An interface that defines how to map each variant of [BrowserToolUseBlock] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        fun visitNavigate(navigate: BrowserNavigateToolUseBlock): T

        fun visitListTabs(listTabs: BrowserListTabsToolUseBlock): T

        fun visitNewTab(newTab: BrowserNewTabToolUseBlock): T

        fun visitSwitchTab(switchTab: BrowserSwitchTabToolUseBlock): T

        fun visitCloseTab(closeTab: BrowserCloseTabToolUseBlock): T

        fun visitReadPage(readPage: BrowserReadPageToolUseBlock): T

        fun visitGetPageText(getPageText: BrowserGetPageTextToolUseBlock): T

        fun visitReadConsole(readConsole: BrowserReadConsoleToolUseBlock): T

        fun visitReadNetwork(readNetwork: BrowserReadNetworkToolUseBlock): T

        fun visitFind(find: BrowserFindToolUseBlock): T

        fun visitFormInput(formInput: BrowserFormInputToolUseBlock): T

        fun visitFileUpload(fileUpload: BrowserFileUploadToolUseBlock): T

        fun visitScrollTo(scrollTo: BrowserScrollToToolUseBlock): T

        fun visitScreenshot(screenshot: BrowserScreenshotToolUseBlock): T

        fun visitZoom(zoom: BrowserZoomToolUseBlock): T

        fun visitLeftClick(leftClick: BrowserLeftClickToolUseBlock): T

        fun visitRightClick(rightClick: BrowserRightClickToolUseBlock): T

        fun visitMiddleClick(middleClick: BrowserMiddleClickToolUseBlock): T

        fun visitDoubleClick(doubleClick: BrowserDoubleClickToolUseBlock): T

        fun visitTripleClick(tripleClick: BrowserTripleClickToolUseBlock): T

        fun visitHover(hover: BrowserHoverToolUseBlock): T

        fun visitLeftClickDrag(leftClickDrag: BrowserLeftClickDragToolUseBlock): T

        fun visitLeftMouseDown(leftMouseDown: BrowserLeftMouseDownToolUseBlock): T

        fun visitLeftMouseUp(leftMouseUp: BrowserLeftMouseUpToolUseBlock): T

        fun visitMouseMove(mouseMove: BrowserMouseMoveToolUseBlock): T

        fun visitScroll(scroll: BrowserScrollToolUseBlock): T

        fun visitType(type: BrowserTypeToolUseBlock): T

        fun visitKey(key: BrowserKeyToolUseBlock): T

        fun visitHoldKey(holdKey: BrowserHoldKeyToolUseBlock): T

        fun visitWait(wait: BrowserWaitToolUseBlock): T

        fun visitJavascriptExec(javascriptExec: BrowserJavascriptExecToolUseBlock): T

        /**
         * Maps an unknown variant of [BrowserToolUseBlock] to a value of type [T].
         *
         * An instance of [BrowserToolUseBlock] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BrowserToolUseBlock: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BrowserToolUseBlock>(BrowserToolUseBlock::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BrowserToolUseBlock {
            val json = JsonValue.fromJsonNode(node)
            val name = json.asObject().getOrNull()?.get("name")?.asString()?.getOrNull()

            when (name) {
                "navigate" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserNavigateToolUseBlock>())
                        ?.let { BrowserToolUseBlock(navigate = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "list_tabs" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserListTabsToolUseBlock>())
                        ?.let { BrowserToolUseBlock(listTabs = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "new_tab" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserNewTabToolUseBlock>())?.let {
                        BrowserToolUseBlock(newTab = it, _json = json)
                    } ?: BrowserToolUseBlock(_json = json)
                }
                "switch_tab" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserSwitchTabToolUseBlock>())
                        ?.let { BrowserToolUseBlock(switchTab = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "close_tab" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserCloseTabToolUseBlock>())
                        ?.let { BrowserToolUseBlock(closeTab = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "read_page" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserReadPageToolUseBlock>())
                        ?.let { BrowserToolUseBlock(readPage = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "get_page_text" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserGetPageTextToolUseBlock>())
                        ?.let { BrowserToolUseBlock(getPageText = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "read_console" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserReadConsoleToolUseBlock>())
                        ?.let { BrowserToolUseBlock(readConsole = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "read_network" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserReadNetworkToolUseBlock>())
                        ?.let { BrowserToolUseBlock(readNetwork = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "find" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserFindToolUseBlock>())?.let {
                        BrowserToolUseBlock(find = it, _json = json)
                    } ?: BrowserToolUseBlock(_json = json)
                }
                "form_input" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserFormInputToolUseBlock>())
                        ?.let { BrowserToolUseBlock(formInput = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "file_upload" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserFileUploadToolUseBlock>())
                        ?.let { BrowserToolUseBlock(fileUpload = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "scroll_to" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserScrollToToolUseBlock>())
                        ?.let { BrowserToolUseBlock(scrollTo = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "screenshot" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserScreenshotToolUseBlock>())
                        ?.let { BrowserToolUseBlock(screenshot = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "zoom" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserZoomToolUseBlock>())?.let {
                        BrowserToolUseBlock(zoom = it, _json = json)
                    } ?: BrowserToolUseBlock(_json = json)
                }
                "left_click" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserLeftClickToolUseBlock>())
                        ?.let { BrowserToolUseBlock(leftClick = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "right_click" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserRightClickToolUseBlock>())
                        ?.let { BrowserToolUseBlock(rightClick = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "middle_click" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserMiddleClickToolUseBlock>())
                        ?.let { BrowserToolUseBlock(middleClick = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "double_click" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserDoubleClickToolUseBlock>())
                        ?.let { BrowserToolUseBlock(doubleClick = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "triple_click" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserTripleClickToolUseBlock>())
                        ?.let { BrowserToolUseBlock(tripleClick = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "hover" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserHoverToolUseBlock>())?.let {
                        BrowserToolUseBlock(hover = it, _json = json)
                    } ?: BrowserToolUseBlock(_json = json)
                }
                "left_click_drag" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserLeftClickDragToolUseBlock>())
                        ?.let { BrowserToolUseBlock(leftClickDrag = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "left_mouse_down" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserLeftMouseDownToolUseBlock>())
                        ?.let { BrowserToolUseBlock(leftMouseDown = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "left_mouse_up" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserLeftMouseUpToolUseBlock>())
                        ?.let { BrowserToolUseBlock(leftMouseUp = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "mouse_move" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserMouseMoveToolUseBlock>())
                        ?.let { BrowserToolUseBlock(mouseMove = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
                "scroll" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserScrollToolUseBlock>())?.let {
                        BrowserToolUseBlock(scroll = it, _json = json)
                    } ?: BrowserToolUseBlock(_json = json)
                }
                "type" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserTypeToolUseBlock>())?.let {
                        BrowserToolUseBlock(type = it, _json = json)
                    } ?: BrowserToolUseBlock(_json = json)
                }
                "key" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserKeyToolUseBlock>())?.let {
                        BrowserToolUseBlock(key = it, _json = json)
                    } ?: BrowserToolUseBlock(_json = json)
                }
                "hold_key" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserHoldKeyToolUseBlock>())?.let {
                        BrowserToolUseBlock(holdKey = it, _json = json)
                    } ?: BrowserToolUseBlock(_json = json)
                }
                "wait" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserWaitToolUseBlock>())?.let {
                        BrowserToolUseBlock(wait = it, _json = json)
                    } ?: BrowserToolUseBlock(_json = json)
                }
                "javascript_exec" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserJavascriptExecToolUseBlock>())
                        ?.let { BrowserToolUseBlock(javascriptExec = it, _json = json) }
                        ?: BrowserToolUseBlock(_json = json)
                }
            }

            return BrowserToolUseBlock(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BrowserToolUseBlock>(BrowserToolUseBlock::class) {

        override fun serialize(
            value: BrowserToolUseBlock,
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
                else -> throw IllegalStateException("Invalid BrowserToolUseBlock")
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
