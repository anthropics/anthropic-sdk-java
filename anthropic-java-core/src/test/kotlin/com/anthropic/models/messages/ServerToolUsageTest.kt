// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServerToolUsageTest {

    @Test
    fun create() {
        val serverToolUsage =
            ServerToolUsage.builder().webFetchRequests(2L).webSearchRequests(0L).build()

        assertThat(serverToolUsage.webFetchRequests()).isEqualTo(2L)
        assertThat(serverToolUsage.webSearchRequests()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val serverToolUsage =
            ServerToolUsage.builder().webFetchRequests(2L).webSearchRequests(0L).build()

        val roundtrippedServerToolUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(serverToolUsage),
                jacksonTypeRef<ServerToolUsage>(),
            )

        assertThat(roundtrippedServerToolUsage).isEqualTo(serverToolUsage)
    }
}
