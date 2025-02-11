// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CacheControlEphemeralTest {

    @Test
    fun createCacheControlEphemeral() {
        val cacheControlEphemeral = CacheControlEphemeral.builder().build()
        assertThat(cacheControlEphemeral).isNotNull
    }
}
