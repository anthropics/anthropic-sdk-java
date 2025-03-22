// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMessageParamTest {

    @Test
    fun create() {
        val betaMessageParam =
            BetaMessageParam.builder().content("string").role(BetaMessageParam.Role.USER).build()

        assertThat(betaMessageParam.content())
            .isEqualTo(BetaMessageParam.Content.ofString("string"))
        assertThat(betaMessageParam.role()).isEqualTo(BetaMessageParam.Role.USER)
    }
}
