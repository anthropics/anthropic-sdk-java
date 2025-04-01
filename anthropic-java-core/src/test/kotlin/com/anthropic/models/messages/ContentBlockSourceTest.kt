// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ContentBlockSourceTest {

    @Test
    fun create() {
        val contentBlockSource = ContentBlockSource.builder().content("string").build()

        assertThat(contentBlockSource.content())
            .isEqualTo(ContentBlockSource.Content.ofString("string"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockSource = ContentBlockSource.builder().content("string").build()

        val roundtrippedContentBlockSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlockSource),
                jacksonTypeRef<ContentBlockSource>(),
            )

        assertThat(roundtrippedContentBlockSource).isEqualTo(contentBlockSource)
    }
}
