// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CacheControlEphemeralTest {

    @Test
    fun createCacheControlEphemeral() {
        val cacheControlEphemeral =
            CacheControlEphemeral.builder().type(CacheControlEphemeral.Type.EPHEMERAL).build()
        assertThat(cacheControlEphemeral).isNotNull
        assertThat(cacheControlEphemeral.type()).isEqualTo(CacheControlEphemeral.Type.EPHEMERAL)
    }
}
