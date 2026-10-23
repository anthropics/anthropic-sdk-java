package com.anthropic.core

import com.anthropic.core.http.Headers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UnwrapWebhookParamsTest {

    @Test
    fun toString_withSecret_hidesSecret() {
        val params =
            UnwrapWebhookParams.builder()
                .body("{}")
                .headers(Headers.builder().build())
                .secret("whsec_not_a_real_secret")
                .build()

        val string = params.toString()

        assertThat(string).doesNotContain("whsec_not_a_real_secret")
    }
}
