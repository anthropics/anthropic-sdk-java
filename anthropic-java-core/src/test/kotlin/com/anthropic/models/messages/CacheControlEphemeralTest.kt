// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CacheControlEphemeralTest {

    @Test
    fun createCacheControlEphemeral() {
        val cacheControlEphemeral = CacheControlEphemeral.builder().build()
        assertThat(cacheControlEphemeral).isNotNull
    }
}
