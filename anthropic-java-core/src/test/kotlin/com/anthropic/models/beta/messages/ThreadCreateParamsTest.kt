package com.anthropic.models.beta.messages

import com.anthropic.models.beta.messages.ThreadCreateParams.UnsupportedRequestBehavior
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThreadCreateParamsTest {

    @Test
    fun `the messages are optional and the behavior defaults to an error`() {
        val params = ThreadCreateParams.builder().model("claude-sonnet-4-5").maxTokens(1024).build()

        assertThat(params.initialMessageParams.messages()).isEmpty()
        assertThat(params.unsupportedRequestBehavior()).isEqualTo(UnsupportedRequestBehavior.ERROR)
    }

    @Test
    fun `params built from the same values are equal`() {
        val builder =
            ThreadCreateParams.builder()
                .model("claude-sonnet-4-5")
                .maxTokens(1024)
                .addUserMessage("Hello")
                .unsupportedRequestBehavior(UnsupportedRequestBehavior.CONTINUE_UNTHREADED)
        val params = builder.build()

        assertThat(params).isEqualTo(builder.build()).isEqualTo(params.toBuilder().build())
        assertThat(params).hasSameHashCodeAs(builder.build())
        assertThat(params).isNotEqualTo(params.toBuilder().maxTokens(512).build())
        assertThat(params.toString())
            .startsWith("ThreadCreateParams{initialMessageParams=")
            .contains("unsupportedRequestBehavior=CONTINUE_UNTHREADED")
    }
}
