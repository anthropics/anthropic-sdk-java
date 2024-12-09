// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PromptCachingBetaImageBlockParamTest {

    @Test
    fun createPromptCachingBetaImageBlockParam() {
        val promptCachingBetaImageBlockParam =
            PromptCachingBetaImageBlockParam.builder()
                .source(
                    PromptCachingBetaImageBlockParam.Source.builder()
                        .data("U3RhaW5sZXNzIHJvY2tz")
                        .mediaType(PromptCachingBetaImageBlockParam.Source.MediaType.IMAGE_JPEG)
                        .type(PromptCachingBetaImageBlockParam.Source.Type.BASE64)
                        .build()
                )
                .type(PromptCachingBetaImageBlockParam.Type.IMAGE)
                .cacheControl(
                    PromptCachingBetaCacheControlEphemeral.builder()
                        .type(PromptCachingBetaCacheControlEphemeral.Type.EPHEMERAL)
                        .build()
                )
                .build()
        assertThat(promptCachingBetaImageBlockParam).isNotNull
        assertThat(promptCachingBetaImageBlockParam.source())
            .isEqualTo(
                PromptCachingBetaImageBlockParam.Source.builder()
                    .data("U3RhaW5sZXNzIHJvY2tz")
                    .mediaType(PromptCachingBetaImageBlockParam.Source.MediaType.IMAGE_JPEG)
                    .type(PromptCachingBetaImageBlockParam.Source.Type.BASE64)
                    .build()
            )
        assertThat(promptCachingBetaImageBlockParam.type())
            .isEqualTo(PromptCachingBetaImageBlockParam.Type.IMAGE)
        assertThat(promptCachingBetaImageBlockParam.cacheControl())
            .contains(
                PromptCachingBetaCacheControlEphemeral.builder()
                    .type(PromptCachingBetaCacheControlEphemeral.Type.EPHEMERAL)
                    .build()
            )
    }
}
