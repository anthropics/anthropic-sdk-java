// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaContentBlockParamTest {

    @Test
    fun ofText() {
        val text = BetaTextBlockParam.builder().text("x").build()

        val betaContentBlockParam = BetaContentBlockParam.ofText(text)

        assertThat(betaContentBlockParam.text()).contains(text)
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.base64PdfBlock()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofImage() {
        val image =
            BetaImageBlockParam.builder()
                .source(
                    BetaBase64ImageSource.builder()
                        .data("U3RhaW5sZXNzIHJvY2tz")
                        .mediaType(BetaBase64ImageSource.MediaType.IMAGE_JPEG)
                        .build()
                )
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofImage(image)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).contains(image)
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.base64PdfBlock()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofToolUse() {
        val toolUse =
            BetaToolUseBlockParam.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("name")
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofToolUse(toolUse)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).contains(toolUse)
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.base64PdfBlock()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofToolResult() {
        val toolResult = BetaToolResultBlockParam.builder().toolUseId("tool_use_id").build()

        val betaContentBlockParam = BetaContentBlockParam.ofToolResult(toolResult)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).contains(toolResult)
        assertThat(betaContentBlockParam.base64PdfBlock()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofBase64PdfBlock() {
        val base64PdfBlock =
            BetaBase64PdfBlock.builder().betaBase64PdfSource("U3RhaW5sZXNzIHJvY2tz").build()

        val betaContentBlockParam = BetaContentBlockParam.ofBase64PdfBlock(base64PdfBlock)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.base64PdfBlock()).contains(base64PdfBlock)
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofThinking() {
        val thinking =
            BetaThinkingBlockParam.builder().signature("signature").thinking("thinking").build()

        val betaContentBlockParam = BetaContentBlockParam.ofThinking(thinking)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.base64PdfBlock()).isEmpty
        assertThat(betaContentBlockParam.thinking()).contains(thinking)
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofRedactedThinking() {
        val redactedThinking = BetaRedactedThinkingBlockParam.builder().data("data").build()

        val betaContentBlockParam = BetaContentBlockParam.ofRedactedThinking(redactedThinking)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.base64PdfBlock()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).contains(redactedThinking)
    }
}
