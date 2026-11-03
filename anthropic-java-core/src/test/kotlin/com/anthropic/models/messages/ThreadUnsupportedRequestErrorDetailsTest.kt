package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThreadUnsupportedRequestErrorDetailsTest {

    @Test
    fun create() {
        val threadUnsupportedRequestErrorDetails =
            ThreadUnsupportedRequestErrorDetails.builder()
                .addUnsupported(ThreadUnsupportedFeatureName.CACHE_CONTROL)
                .build()

        assertThat(threadUnsupportedRequestErrorDetails.unsupported().getOrNull())
            .containsExactly(ThreadUnsupportedFeatureName.CACHE_CONTROL)
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseThreadUnsupportedRequestErrorDetails =
            ThreadUnsupportedRequestErrorDetails.builder().build()

        val threadUnsupportedRequestErrorDetails =
            baseThreadUnsupportedRequestErrorDetails
                .toBuilder()
                .addUnsupported(ThreadUnsupportedFeatureName.CACHE_CONTROL)
                .build()

        assertThat(threadUnsupportedRequestErrorDetails.unsupported().getOrNull())
            .containsExactly(ThreadUnsupportedFeatureName.CACHE_CONTROL)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val threadUnsupportedRequestErrorDetails =
            ThreadUnsupportedRequestErrorDetails.builder()
                .addUnsupported(ThreadUnsupportedFeatureName.CACHE_CONTROL)
                .build()

        val roundtrippedThreadUnsupportedRequestErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(threadUnsupportedRequestErrorDetails),
                jacksonTypeRef<ThreadUnsupportedRequestErrorDetails>(),
            )

        assertThat(roundtrippedThreadUnsupportedRequestErrorDetails)
            .isEqualTo(threadUnsupportedRequestErrorDetails)
    }
}
