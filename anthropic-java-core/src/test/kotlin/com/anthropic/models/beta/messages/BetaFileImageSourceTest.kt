// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFileImageSourceTest {

    @Test
    fun create() {
        val betaFileImageSource = BetaFileImageSource.builder().fileId("file_id").build()

        assertThat(betaFileImageSource.fileId()).isEqualTo("file_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFileImageSource = BetaFileImageSource.builder().fileId("file_id").build()

        val roundtrippedBetaFileImageSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFileImageSource),
                jacksonTypeRef<BetaFileImageSource>(),
            )

        assertThat(roundtrippedBetaFileImageSource).isEqualTo(betaFileImageSource)
    }
}
