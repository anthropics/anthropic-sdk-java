// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMessageParamTest {

    @Test
    fun createBetaMessageParam() {
        val betaMessageParam =
            BetaMessageParam.builder().content("string").role(BetaMessageParam.Role.USER).build()
        assertThat(betaMessageParam).isNotNull
        assertThat(betaMessageParam.content())
            .isEqualTo(BetaMessageParam.Content.ofString("string"))
        assertThat(betaMessageParam.role()).isEqualTo(BetaMessageParam.Role.USER)
    }
}
