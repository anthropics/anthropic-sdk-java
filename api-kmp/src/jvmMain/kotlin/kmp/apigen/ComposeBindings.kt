package kmp.apigen

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import java.io.File

/**
 * Target-specific Compose widget bindings.
 *
 * Each target (JVM/Desktop, JS/Web, iOS/Native) provides a different
 * set of composable widgets. The [ComposeBinding] interface abstracts
 * the widget generation so the same schema → UI logic works across all
 * Compose Multiplatform targets.
 *
 * Generated code imports from the binding's package, so the same
 * @Composable functions compile on each target with their native widgets.
 */
interface ComposeBinding {
    /** Target name for file suffix (e.g. "material3", "web", "ios") */
    val target: String

    /** Generate a text input field. */
    fun textField(propName: String, label: String): String

    /** Generate a checkbox/switch. */
    fun checkbox(propName: String, label: String): String

    /** Generate a button. */
    fun button(text: String, onClick: String): String

    /** Generate a text label. */
    fun text(content: String): String

    /** Generate a clickable text button (for FK navigation). */
    fun textButton(text: String, onClick: String): String

    /** Generate a list item. */
    fun listItem(headlineContent: String, onClick: String): String

    /** Outer container (Column). */
    fun column(content: String): String

    /** Row container. */
    fun row(content: String): String

    /** Lazy list container. */
    fun lazyColumn(itemsExpr: String, itemContent: String): String
}

/**
 * Material3 binding — JVM Desktop + Android + iOS (Compose Multiplatform).
 */
class Material3Binding : ComposeBinding {
    override val target = "material3"

    override fun textField(propName: String, label: String) =
        """    androidx.compose.material3.TextField(value = $propName, onValueChange = { $propName = it }, label = { androidx.compose.material3.Text("$label") })"""

    override fun checkbox(propName: String, label: String) =
        """    androidx.compose.foundation.layout.Row { androidx.compose.material3.Checkbox(checked = $propName, onCheckedChange = { $propName = it }); androidx.compose.material3.Text("$label") }"""

    override fun button(text: String, onClick: String) =
        """    androidx.compose.material3.Button(onClick = { $onClick }) { androidx.compose.material3.Text("$text") }"""

    override fun text(content: String) =
        """    androidx.compose.material3.Text($content)"""

    override fun textButton(text: String, onClick: String) =
        """    androidx.compose.material3.TextButton(onClick = { $onClick }) { androidx.compose.material3.Text($text) }"""

    override fun listItem(headlineContent: String, onClick: String) =
        """        androidx.compose.material3.ListItem(
              headlineContent = { androidx.compose.material3.Text($headlineContent) },
              modifier = androidx.compose.ui.Modifier.clickable { $onClick }
          )"""

    override fun column(content: String) =
        "androidx.compose.foundation.layout.Column {\n$content\n}"

    override fun row(content: String) =
        "androidx.compose.foundation.layout.Row {\n$content\n}"

    override fun lazyColumn(itemsExpr: String, itemContent: String) =
        """androidx.compose.foundation.lazy.LazyColumn {
    items($itemsExpr) { index ->
        $itemContent
    }
}"""
}

/**
 * Compose for Web (JS) binding — HTML DOM elements.
 */
class WebDomBinding : ComposeBinding {
    override val target = "web"

    override fun textField(propName: String, label: String) =
        """    org.jetbrains.compose.web.dom.Label { org.jetbrains.compose.web.dom.Text("$label") }
    org.jetbrains.compose.web.dom.Input(type = org.jetbrains.compose.web.attributes.InputType.Text) { value($propName); onInput { $propName = it.value } }"""

    override fun checkbox(propName: String, label: String) =
        """    org.jetbrains.compose.web.dom.Label {
        org.jetbrains.compose.web.dom.CheckboxInput(checked = $propName) { onInput { $propName = it.value } }
        org.jetbrains.compose.web.dom.Text("$label")
    }"""

    override fun button(text: String, onClick: String) =
        """    org.jetbrains.compose.web.dom.Button(attrs = { onClick { $onClick } }) { org.jetbrains.compose.web.dom.Text("$text") }"""

    override fun text(content: String) =
        """    org.jetbrains.compose.web.dom.P { org.jetbrains.compose.web.dom.Text($content) }"""

    override fun textButton(text: String, onClick: String) =
        """    org.jetbrains.compose.web.dom.A(attrs = { onClick { $onClick } }) { org.jetbrains.compose.web.dom.Text($text) }"""

    override fun listItem(headlineContent: String, onClick: String) =
        """        org.jetbrains.compose.web.dom.Li(attrs = { onClick { $onClick } }) {
            org.jetbrains.compose.web.dom.Text($headlineContent)
        }"""

    override fun column(content: String) =
        "org.jetbrains.compose.web.dom.Div(attrs = { style { display(org.jetbrains.compose.web.css.DisplayStyle.Flex); flexDirection(org.jetbrains.compose.web.css.FlexDirection.Column) } }) {\n$content\n}"

    override fun row(content: String) =
        "org.jetbrains.compose.web.dom.Div(attrs = { style { display(org.jetbrains.compose.web.css.DisplayStyle.Flex); flexDirection(org.jetbrains.compose.web.css.FlexDirection.Row) } }) {\n$content\n}"

    override fun lazyColumn(itemsExpr: String, itemContent: String) =
        """org.jetbrains.compose.web.dom.Ul {
    repeat($itemsExpr) { index ->
        $itemContent
    }
}"""
}
