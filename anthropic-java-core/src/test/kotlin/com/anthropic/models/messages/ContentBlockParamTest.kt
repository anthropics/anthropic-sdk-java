// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ContentBlockParamTest {

    @Test
    fun ofText() {
        val text = TextBlockParam.builder().text("x").build()

        val contentBlockParam = ContentBlockParam.ofText(text)

        assertThat(contentBlockParam.text()).contains(text)
        assertThat(contentBlockParam.image()).isEmpty
        assertThat(contentBlockParam.toolUse()).isEmpty
        assertThat(contentBlockParam.toolResult()).isEmpty
        assertThat(contentBlockParam.document()).isEmpty
        assertThat(contentBlockParam.thinking()).isEmpty
        assertThat(contentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofImage() {
        val image =
            ImageBlockParam.builder()
                .source(
                    Base64ImageSource.builder()
                        .data("U3RhaW5sZXNzIHJvY2tz")
                        .mediaType(Base64ImageSource.MediaType.IMAGE_JPEG)
                        .build()
                )
                .build()

        val contentBlockParam = ContentBlockParam.ofImage(image)

        assertThat(contentBlockParam.text()).isEmpty
        assertThat(contentBlockParam.image()).contains(image)
        assertThat(contentBlockParam.toolUse()).isEmpty
        assertThat(contentBlockParam.toolResult()).isEmpty
        assertThat(contentBlockParam.document()).isEmpty
        assertThat(contentBlockParam.thinking()).isEmpty
        assertThat(contentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofToolUse() {
        val toolUse =
            ToolUseBlockParam.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("name")
                .build()

        val contentBlockParam = ContentBlockParam.ofToolUse(toolUse)

        assertThat(contentBlockParam.text()).isEmpty
        assertThat(contentBlockParam.image()).isEmpty
        assertThat(contentBlockParam.toolUse()).contains(toolUse)
        assertThat(contentBlockParam.toolResult()).isEmpty
        assertThat(contentBlockParam.document()).isEmpty
        assertThat(contentBlockParam.thinking()).isEmpty
        assertThat(contentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofToolResult() {
        val toolResult = ToolResultBlockParam.builder().toolUseId("tool_use_id").build()

        val contentBlockParam = ContentBlockParam.ofToolResult(toolResult)

        assertThat(contentBlockParam.text()).isEmpty
        assertThat(contentBlockParam.image()).isEmpty
        assertThat(contentBlockParam.toolUse()).isEmpty
        assertThat(contentBlockParam.toolResult()).contains(toolResult)
        assertThat(contentBlockParam.document()).isEmpty
        assertThat(contentBlockParam.thinking()).isEmpty
        assertThat(contentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofDocument() {
        val document = DocumentBlockParam.builder().base64PdfSource("U3RhaW5sZXNzIHJvY2tz").build()

        val contentBlockParam = ContentBlockParam.ofDocument(document)

        assertThat(contentBlockParam.text()).isEmpty
        assertThat(contentBlockParam.image()).isEmpty
        assertThat(contentBlockParam.toolUse()).isEmpty
        assertThat(contentBlockParam.toolResult()).isEmpty
        assertThat(contentBlockParam.document()).contains(document)
        assertThat(contentBlockParam.thinking()).isEmpty
        assertThat(contentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofThinking() {
        val thinking =
            ThinkingBlockParam.builder().signature("signature").thinking("thinking").build()

        val contentBlockParam = ContentBlockParam.ofThinking(thinking)

        assertThat(contentBlockParam.text()).isEmpty
        assertThat(contentBlockParam.image()).isEmpty
        assertThat(contentBlockParam.toolUse()).isEmpty
        assertThat(contentBlockParam.toolResult()).isEmpty
        assertThat(contentBlockParam.document()).isEmpty
        assertThat(contentBlockParam.thinking()).contains(thinking)
        assertThat(contentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofRedactedThinking() {
        val redactedThinking = RedactedThinkingBlockParam.builder().data("data").build()

        val contentBlockParam = ContentBlockParam.ofRedactedThinking(redactedThinking)

        assertThat(contentBlockParam.text()).isEmpty
        assertThat(contentBlockParam.image()).isEmpty
        assertThat(contentBlockParam.toolUse()).isEmpty
        assertThat(contentBlockParam.toolResult()).isEmpty
        assertThat(contentBlockParam.document()).isEmpty
        assertThat(contentBlockParam.thinking()).isEmpty
        assertThat(contentBlockParam.redactedThinking()).contains(redactedThinking)
    }
}
