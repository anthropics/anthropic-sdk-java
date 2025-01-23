// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaCitationsConfigParamTest {

    @Test
    fun createBetaCitationsConfigParam() {
        val betaCitationsConfigParam = BetaCitationsConfigParam.builder().enabled(true).build()
        assertThat(betaCitationsConfigParam).isNotNull
        assertThat(betaCitationsConfigParam.enabled()).contains(true)
    }
}
