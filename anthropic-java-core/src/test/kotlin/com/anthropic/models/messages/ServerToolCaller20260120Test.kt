// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServerToolCaller20260120Test {

    @Test
    fun create() {
        val serverToolCaller20260120 = ServerToolCaller20260120.of("srvtoolu_SQfNkl1n_JR_")

        assertThat(serverToolCaller20260120.toolId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val serverToolCaller20260120 = ServerToolCaller20260120.of("srvtoolu_SQfNkl1n_JR_")

        val roundtrippedServerToolCaller20260120 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(serverToolCaller20260120),
                jacksonTypeRef<ServerToolCaller20260120>(),
            )

        assertThat(roundtrippedServerToolCaller20260120).isEqualTo(serverToolCaller20260120)
    }
}
