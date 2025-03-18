// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCitationsConfigParamTest {

    @Test
    fun create() {
        val betaCitationsConfigParam = BetaCitationsConfigParam.builder().enabled(true).build()

        assertThat(betaCitationsConfigParam.enabled()).contains(true)
    }
}
