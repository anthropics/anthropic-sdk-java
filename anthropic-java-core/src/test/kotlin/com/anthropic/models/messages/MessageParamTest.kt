// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageParamTest {

    @Test
    fun create() {
        val messageParam =
            MessageParam.builder().content("string").role(MessageParam.Role.USER).build()

        assertThat(messageParam.content()).isEqualTo(MessageParam.Content.ofString("string"))
        assertThat(messageParam.role()).isEqualTo(MessageParam.Role.USER)
    }
}
