// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ContainerUploadBlockParamTest {

    @Test
    fun create() {
        val containerUploadBlockParam =
            ContainerUploadBlockParam.builder()
                .fileId("file_id")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        assertThat(containerUploadBlockParam.fileId()).isEqualTo("file_id")
        assertThat(containerUploadBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val containerUploadBlockParam =
            ContainerUploadBlockParam.builder()
                .fileId("file_id")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val roundtrippedContainerUploadBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(containerUploadBlockParam),
                jacksonTypeRef<ContainerUploadBlockParam>(),
            )

        assertThat(roundtrippedContainerUploadBlockParam).isEqualTo(containerUploadBlockParam)
    }
}
