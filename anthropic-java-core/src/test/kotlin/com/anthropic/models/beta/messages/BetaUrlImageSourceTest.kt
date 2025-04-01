// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUrlImageSourceTest {

    @Test
    fun create() {
        val betaUrlImageSource = BetaUrlImageSource.builder().url("url").build()

        assertThat(betaUrlImageSource.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaUrlImageSource = BetaUrlImageSource.builder().url("url").build()

        val roundtrippedBetaUrlImageSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUrlImageSource),
                jacksonTypeRef<BetaUrlImageSource>(),
            )

        assertThat(roundtrippedBetaUrlImageSource).isEqualTo(betaUrlImageSource)
    }
}
